package com.ostach.advent.day7;

import lombok.experimental.UtilityClass;

@UtilityClass
class Day7Runner {

    public static void main(String[] args) {
        Day7 day = new Day7();
        System.out.println("How many can contain golden: "  + day.run1("day7/input.txt"));
        System.out.println("How many inside: "  + day.run2("day7/input.txt"));
    }
}
