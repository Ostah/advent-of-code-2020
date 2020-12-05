package com.ostach.advent.day5;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

class Day5Test {

    Day5 day = new Day5();

    @Test
    void decodeBFFFBBFRRR() {
        String coded = "BFFFBBFRRR";

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(day.getRow(coded)).isEqualTo(70);
        softly.assertThat(day.getColumn(coded)).isEqualTo(7);
        softly.assertThat(day.decodeSeatNumber(coded)).isEqualTo(567);
        softly.assertAll();
    }

    @Test
    void decodeFFFBBBFRRR() {
        String coded = "FFFBBBFRRR";

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(day.getRow(coded)).isEqualTo(14);
        softly.assertThat(day.getColumn(coded)).isEqualTo(7);
        softly.assertThat(day.decodeSeatNumber(coded)).isEqualTo(119);
        softly.assertAll();
    }
    @Test
    void decodeBBFFBBFRLL() {
        String coded = "BBFFBBFRLL";

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(day.getRow(coded)).isEqualTo(102);
        softly.assertThat(day.getColumn(coded)).isEqualTo(4);
        softly.assertThat(day.decodeSeatNumber(coded)).isEqualTo(820);
        softly.assertAll();
    }
}