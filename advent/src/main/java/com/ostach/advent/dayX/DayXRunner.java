package com.ostach.advent.dayX;

import lombok.experimental.UtilityClass;

@UtilityClass
class DayXRunner {

    public static void main(String[] args) {
        DayX day = new DayX();
        System.out.println("Result 1: "+ day.run1("dayX/input.txt"));
        System.out.println("Result 2: "+ day.run2("dayX/input.txt"));
    }
}
