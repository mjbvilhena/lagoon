import org.gradle.api.artifacts.component.ModuleComponentIdentifier
import org.gradle.api.provider.Provider

/**
 * Writes the resolved modules US-88 scans.
 * Each project resolves only its own configurations, which is what Gradle 9 allows.
 * A missing configuration or a failed resolve fails the build. That is not a clean scan.
 */
gradle.projectsEvaluated {
    val root = rootProject
    val partials = mutableListOf<Provider<List<String>>>()

    fun Project.capture(configurationName: String, label: String) {
        val configuration =
            configurations.findByName(configurationName)
                ?: throw GradleException("no graph: $path has no $configurationName")
        if (!configuration.isCanBeResolved) {
            throw GradleException("no graph: $path $configurationName cannot be resolved")
        }
        val modules =
            configurations.named(configurationName).map { selected ->
                selected.incoming.resolutionResult.allComponents.mapNotNull { component ->
                    val identifier = component.id
                    if (identifier !is ModuleComponentIdentifier) {
                        return@mapNotNull null
                    }
                    if (identifier.version.isEmpty()) {
                        throw GradleException(
                            "no graph: ${identifier.group}:${identifier.module} has no version",
                        )
                    }
                    "${identifier.group}:${identifier.module}:${identifier.version}"
                }
            }
        val output = root.layout.buildDirectory.file("ci/partial-$label.txt")
        val task =
            tasks.register("capture$label") {
                inputs.property("modules", modules)
                outputs.file(output)
                doLast {
                    val lines = modules.get().distinct().sorted()
                    val file = output.get().asFile
                    file.parentFile.mkdirs()
                    file.writeText(lines.joinToString(separator = "\n", postfix = "\n"))
                }
            }
        partials.add(task.map { output.get().asFile.readLines().filter { it.isNotBlank() } })
    }

    fun Project.captureBuildscript(label: String) {
        val configuration =
            buildscript.configurations.findByName("classpath")
                ?: throw GradleException("no graph: $path has no buildscript classpath")
        val modules =
            buildscript.configurations.named("classpath").map { selected ->
                selected.incoming.resolutionResult.allComponents.mapNotNull { component ->
                    val identifier = component.id
                    if (identifier !is ModuleComponentIdentifier) {
                        return@mapNotNull null
                    }
                    if (identifier.version.isEmpty()) {
                        throw GradleException(
                            "no graph: buildscript ${identifier.group}:${identifier.module} has no version",
                        )
                    }
                    "${identifier.group}:${identifier.module}:${identifier.version}"
                }
            }
        val output = root.layout.buildDirectory.file("ci/partial-$label.txt")
        val task =
            tasks.register("capture$label") {
                inputs.property("modules", modules)
                outputs.file(output)
                doLast {
                    val lines = modules.get().distinct().sorted()
                    val file = output.get().asFile
                    file.parentFile.mkdirs()
                    file.writeText(lines.joinToString(separator = "\n", postfix = "\n"))
                }
            }
        partials.add(task.map { output.get().asFile.readLines().filter { it.isNotBlank() } })
    }

    root.project(":app").capture("releaseRuntimeClasspath", "AppRuntime")
    root.project(":ui").capture("releaseRuntimeClasspath", "UiRuntime")
    root.project(":data").capture("releaseRuntimeClasspath", "DataRuntime")
    root.project(":domain").capture("runtimeClasspath", "DomainRuntime")
    root.captureBuildscript("RootBuildscript")
    root.subprojects.forEach { subproject ->
        val label = "Buildscript" + subproject.path.trim(':').replace(":", "")
        subproject.captureBuildscript(label)
    }

    root.tasks.register("exportResolvedGraph") {
        dependsOn(root.tasks.matching { it.name.startsWith("capture") })
        root.subprojects.forEach { subproject ->
            dependsOn(subproject.tasks.matching { it.name.startsWith("capture") })
        }
        val pieces = partials.toList()
        doLast {
            val records = linkedSetOf<String>()
            pieces.forEach { provider ->
                records.addAll(provider.get())
            }
            if (records.isEmpty()) {
                throw GradleException("no graph: resolver produced no external modules")
            }
            val output = root.layout.buildDirectory.file("ci/gradle.lockfile").get().asFile
            output.parentFile.mkdirs()
            output.writeText(
                records.sorted().joinToString(separator = "\n", postfix = "\n") { "$it=resolved" } +
                    "empty=\n",
            )
            println("resolved ${records.size} modules to ${output.absolutePath}")
        }
    }
}
