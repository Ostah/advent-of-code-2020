package com.ostach.advent.day8;

import lombok.experimental.UtilityClass;

@UtilityClass
class Day8Runner {

    public static void main(String[] args) {
        Day8 day = new Day8();
        System.out.println("Result 1: "+ day.run1("day8/input.txt"));
        System.out.println("Result 2: "+ day.run2("day8/input.txt"));
    }
}
