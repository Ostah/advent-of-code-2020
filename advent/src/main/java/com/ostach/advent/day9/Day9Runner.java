package com.ostach.advent.day9;

import lombok.experimental.UtilityClass;

@UtilityClass
class Day9Runner {

    public static void main(String[] args) {
        Day9 day = new Day9();
        System.out.println("Result 1: "+ day.run1("day9/input.txt", 25));
        System.out.println("Result 2: "+ day.run2("day9/input.txt", 25));
    }
}
