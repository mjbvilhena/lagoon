package com.mjbvilhena.lagoon

import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File

class ModuleBoundaryTest {
    @Test
    fun domainUiAndDataKeepTheirImports() {
        val root = File(requiredProperty("repoRoot"))
        assertNoForbiddenImport(
            File(root, "domain/src/main"),
            listOf(
                "import android.",
                "import androidx.",
                "import com.mjbvilhena.lagoon.ui",
                "import com.mjbvilhena.lagoon.data",
            ),
        )
        assertNoForbiddenImport(
            File(root, "ui/src/main"),
            listOf(
                "import com.mjbvilhena.lagoon.data",
                "import androidx.room",
                "import net.zetetic",
            ),
        )
        assertNoForbiddenImport(
            File(root, "data/src/main"),
            listOf(
                "import com.mjbvilhena.lagoon.ui",
                "import androidx.compose",
            ),
        )
        val lowRam = ArrayList<String>()
        listOf(File(root, "domain/src/main"), File(root, "data/src/main")).forEach { dir ->
            dir.walkTopDown().filter { it.extension == "kt" }.forEach { file ->
                if (file.readText().contains("isLowRamDevice")) {
                    lowRam.add(file.path)
                }
            }
        }
        assertTrue(lowRam.joinToString(), lowRam.isEmpty())
    }

    private fun assertNoForbiddenImport(
        dir: File,
        forbidden: List<String>,
    ) {
        val hits = ArrayList<String>()
        dir.walkTopDown().filter { it.isFile && it.extension == "kt" }.forEach { file ->
            file.readLines().forEach { line ->
                val trimmed = line.trimStart()
                if (forbidden.any { trimmed.startsWith(it) }) {
                    hits.add("${file.name}: $trimmed")
                }
            }
        }
        assertTrue(hits.joinToString("\n"), hits.isEmpty())
    }

    private fun requiredProperty(name: String): String {
        val value = System.getProperty(name)
        check(!value.isNullOrBlank()) { "$name is not set" }
        return value
    }
}
