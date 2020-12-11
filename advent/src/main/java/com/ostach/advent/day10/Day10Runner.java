package com.ostach.advent.day10;

import lombok.experimental.UtilityClass;

@UtilityClass
class Day10Runner {

    public static void main(String[] args) {
        Day10 day = new Day10();
        System.out.println("Result 1: "+ day.run1("day10/input.txt"));
        System.out.println("Result 2: "+ day.run2("day10/input.txt"));
    }
}
