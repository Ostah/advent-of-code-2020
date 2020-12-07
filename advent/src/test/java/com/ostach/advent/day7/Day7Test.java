package com.ostach.advent.day7;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day7Test {

    Day7 day = new Day7();

    @Test
    void shouldPassExample1_1() {
        long l = day.run1("day7/testInput.txt");

        assertThat(l).isEqualTo(4);

    }

    @Test
    void shouldPassExample1_2() {
        long l = day.run2("day7/testInput.txt");

        assertThat(l).isEqualTo(32);

    }

    @Test
    void shouldPassExample2_2() {
        long l = day.run2("day7/testInput2.txt");

        assertThat(l).isEqualTo(126);

    }
}