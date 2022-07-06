package com.woorifis.vroom.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class CollectUtilsTest {

	@Test
	void removeNewLine() {
		String text = "ABC\nDEF";
		assertEquals("ABCDEF", CollectUtils.remove(text, "\\R"));
		assertEquals("ABCDEF", CollectUtils.remove(text, "\\n"));
	}

	@Test
	void removeRange() {
		String text = "(123)ABC";
		assertEquals("ABC", CollectUtils.removeRange(text, '(', ')'));
	}

	@Test
	void includeRange() {
		String text = "13년06월(13년형)";
		assertEquals("13", CollectUtils.includeRange(text, 0, 1));
	}

}