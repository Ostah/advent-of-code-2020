package com.ostach.advent.day10;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day10Test {
    Day10 day = new Day10();

    @Test
    void shouldPassExample1_1() {
        long result = day.run1("day10/testInput1.txt");
        assertThat(result).isEqualTo(7*5);

    }
    @Test
    void shouldPassExample1_2() {
        long result = day.run2("day10/testInput1.txt");
        assertThat(result).isEqualTo(8);

    }

    @Test
    void shouldPassExample2_1() {
        long result = day.run1("day10/testInput2.txt");

        assertThat(result).isEqualTo(220);
    }

    @Test
    void shouldPassExample2_2() {
        long result = day.run2("day10/testInput2.txt");

        assertThat(result).isEqualTo(19208);
    }
}