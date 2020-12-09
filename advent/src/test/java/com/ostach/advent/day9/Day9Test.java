package com.ostach.advent.day9;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day9Test {
    Day9 day = new Day9();

    @Test
    void shouldPassExample1_1() {
        long result = day.run1("day9/testInput.txt", 5);

        assertThat(result).isEqualTo(127);

    }

    @Test
    void shouldPassExample1_2() {
        long result = day.run2("day9/testInput.txt", 5);

        assertThat(result).isEqualTo(62);

    }
}