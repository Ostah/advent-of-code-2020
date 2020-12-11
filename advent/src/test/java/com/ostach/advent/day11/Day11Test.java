package com.ostach.advent.day11;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day11Test {

    Day11 day = new Day11();

    @Test
    void shouldPassExample1_1() {
        long result = day.run1("day11/testInput1.txt");
        assertThat(result).isEqualTo(37);

    }
}