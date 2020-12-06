package com.ostach.advent.day6;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day6Test {

    Day6 day = new Day6();
    private static final String INPUT = "abc\n" +
            "\n" +
            "a\n" +
            "b\n" +
            "c\n" +
            "\n" +
            "ab\n" +
            "ac\n" +
            "\n" +
            "a\n" +
            "a\n" +
            "a\n" +
            "a\n" +
            "\n" +
            "b";

    @Test
    void shouldReturn11ForWebsiteTestCaseForRun1() {
        int output = day.run1(INPUT);

        assertThat(output).isEqualTo(11);
    }

    @Test
    void shouldReturn6ForWebsiteTestCaseForRun2() {
        int output = day.run2(INPUT);

        assertThat(output).isEqualTo(6);
    }
}