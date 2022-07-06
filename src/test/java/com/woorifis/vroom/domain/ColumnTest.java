package com.woorifis.vroom.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ColumnTest {

	@Test
	void test() {
		assertEquals(Column.COLOR, Column.of("색상"));
		assertThrows(IllegalArgumentException.class, () -> Column.of("none"));
	}
}