package com.ostach.advent.day11;

import lombok.experimental.UtilityClass;

@UtilityClass
class Day11Runner {

    public static void main(String[] args) {
        Day11 day = new Day11();
        System.out.println("Result 1: "+ day.run1("day11/input.txt"));
        System.out.println("Result 2: "+ day.run2("day11/input.txt"));
    }
}
