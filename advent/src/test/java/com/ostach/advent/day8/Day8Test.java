package com.ostach.advent.day8;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day8Test {

    Day8 day = new Day8();

    @Test
    void shouldPassExample1_1() {
        long accumulator = day.run1("day8/testInput.txt");

        assertThat(accumulator).isEqualTo(5);

    }

    @Test
    void shouldPassExample1_2() {
        long accumulator = day.run2("day8/testInput.txt");

        assertThat(accumulator).isEqualTo(8);
    }

}