package com.ostach.advent.day6;

import com.ostach.advent.FileUtils;
import lombok.experimental.UtilityClass;

@UtilityClass
class Day6Runner {

    public static void main(String[] args) {
        String input = FileUtils.getString("day6/input.txt");

        System.out.println("Sum: " + new Day6().run1(input));
        System.out.println("Cross: " + new Day6().run2(input));
    }
}
