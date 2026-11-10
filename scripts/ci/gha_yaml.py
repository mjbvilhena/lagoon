"""Indent parser for the GitHub Actions YAML in this repository.

Keys such as ``on`` stay strings. The parser does not run workflow steps.
A file it cannot read raises ``ParseError`` so the caller fails closed.
"""

from __future__ import annotations


class ParseError(Exception):
    """The workflow text is not in the subset this checker reads."""


def parse_workflow(text: str):
    if text.startswith("\ufeff"):
        text = text[1:]
    lines = text.splitlines()
    value, index = _parse_map(lines, 0, 0)
    while index < len(lines):
        raw = lines[index]
        if raw.strip() == "" or _is_full_comment(raw):
            index += 1
            continue
        raise ParseError(f"trailing content on line {index + 1}")
    if not isinstance(value, dict):
        raise ParseError("workflow is not a mapping")
    return value


def _parse_map(lines: list[str], index: int, indent: int):
    result: dict = {}
    while index < len(lines):
        index = _skip_noise(lines, index)
        if index >= len(lines):
            break
        raw = lines[index]
        current = _indent(raw)
        if current < indent:
            break
        if current > indent:
            raise ParseError(f"unexpected indent on line {index + 1}")
        stripped = _strip_inline_comment(raw.strip())
        if stripped.startswith("- "):
            raise ParseError(f"list item where a key was required on line {index + 1}")
        key, rest = _split_key(stripped, index)
        if rest in {"|", "|-", "|+", ">", ">-", ">+"}:
            value, index = _parse_block(lines, index + 1, indent, rest)
        elif rest == "":
            index += 1
            value, index = _parse_nested(lines, index, indent)
        else:
            value = _parse_flow(rest, index)
            index += 1
        if key in result:
            raise ParseError(f"duplicate key {key} on line {index}")
        result[key] = value
    return result, index


def _parse_nested(lines: list[str], index: int, parent_indent: int):
    index = _skip_noise(lines, index)
    if index >= len(lines):
        return None, index
    current = _indent(lines[index])
    if current <= parent_indent:
        return None, index
    stripped = _strip_inline_comment(lines[index].strip())
    if stripped.startswith("- ") or stripped == "-":
        return _parse_list(lines, index, current)
    return _parse_map(lines, index, current)


def _parse_list(lines: list[str], index: int, indent: int):
    items = []
    while index < len(lines):
        index = _skip_noise(lines, index)
        if index >= len(lines):
            break
        raw = lines[index]
        current = _indent(raw)
        if current < indent:
            break
        if current > indent:
            raise ParseError(f"unexpected indent on line {index + 1}")
        stripped = raw.strip()
        if not stripped.startswith("-"):
            break
        body = _strip_inline_comment(stripped[1:].strip())
        if body == "":
            index += 1
            value, index = _parse_nested(lines, index, indent)
            items.append(value)
            continue
        if _looks_like_key(body):
            key, rest = _split_key(body, index)
            item: dict = {}
            key_indent = _content_column(raw)
            if rest in {"|", "|-", "|+", ">", ">-", ">+"}:
                value, index = _parse_block(lines, index + 1, key_indent, rest)
            elif rest == "":
                index += 1
                value, index = _parse_nested(lines, index, key_indent)
            else:
                value = _parse_flow(rest, index)
                index += 1
            item[key] = value
            extra, index = _parse_map(lines, index, key_indent)
            overlap = set(item).intersection(extra)
            if overlap:
                raise ParseError(f"duplicate key in list item: {sorted(overlap)}")
            item.update(extra)
            items.append(item)
            continue
        items.append(_parse_flow(body, index))
        index += 1
    if not items:
        raise ParseError(f"empty list on line {index + 1}")
    return items, index


def _parse_block(lines: list[str], index: int, parent_indent: int, marker: str):
    block: list[str] = []
    while index < len(lines):
        raw = lines[index]
        if raw.strip() == "":
            block.append("")
            index += 1
            continue
        current = _indent(raw)
        if current <= parent_indent:
            break
        block.append(raw[current:])
        index += 1
    text = "\n".join(block)
    if marker.endswith("-"):
        text = text.rstrip("\n")
    elif not text.endswith("\n"):
        text += "\n"
    return text, index


def _parse_flow(text: str, index: int):
    value = text.strip()
    if len(value) >= 2 and value[0] == value[-1] and value[0] in {"'", '"'}:
        return value[1:-1]
    if value.startswith("[") and value.endswith("]"):
        return _parse_flow_list(value[1:-1], index)
    if value in {"|", "|-", "|+", ">", ">-", ">+", "{", "}", "null", "~"}:
        raise ParseError(f"unsupported scalar on line {index + 1}")
    return value


def _parse_flow_list(text: str, index: int):
    if text.strip() == "":
        return []
    parts: list[str] = []
    current: list[str] = []
    quote = ""
    for char in text:
        if quote:
            current.append(char)
            if char == quote:
                quote = ""
            continue
        if char in {"'", '"'}:
            quote = char
            current.append(char)
            continue
        if char == ",":
            parts.append("".join(current).strip())
            current = []
            continue
        current.append(char)
    if quote:
        raise ParseError(f"unclosed quote in flow list on line {index + 1}")
    tail = "".join(current).strip()
    if tail:
        parts.append(tail)
    return [_parse_flow(part, index) for part in parts]


def _split_key(stripped: str, index: int):
    if ":" not in stripped:
        raise ParseError(f"expected key on line {index + 1}")
    key, rest = stripped.split(":", 1)
    key = key.strip()
    if key == "" or key.startswith(("'", '"')):
        raise ParseError(f"unsupported key on line {index + 1}")
    return key, rest.strip()


def _looks_like_key(text: str) -> bool:
    if ":" not in text:
        return False
    key = text.split(":", 1)[0]
    return key != "" and " " not in key and not key.startswith(("'", '"'))


def _content_column(raw: str) -> int:
    stripped = raw.lstrip(" ")
    dash = stripped[1:].lstrip(" ")
    return len(raw) - len(stripped) + (len(stripped) - len(dash))


def _strip_inline_comment(text: str) -> str:
    quote = ""
    for index, char in enumerate(text):
        if quote:
            if char == quote:
                quote = ""
            continue
        if char in {"'", '"'}:
            quote = char
            continue
        if char == "#" and (index == 0 or text[index - 1].isspace()):
            return text[:index].rstrip()
    return text.rstrip()


def _is_full_comment(raw: str) -> bool:
    return raw.lstrip(" ").startswith("#")


def _skip_noise(lines: list[str], index: int) -> int:
    while index < len(lines):
        raw = lines[index]
        if raw.strip() == "" or _is_full_comment(raw):
            index += 1
            continue
        if "\t" in raw[: _indent(raw) + 1]:
            raise ParseError(f"tab indent on line {index + 1}")
        return index
    return index


def _indent(raw: str) -> int:
    return len(raw) - len(raw.lstrip(" "))
