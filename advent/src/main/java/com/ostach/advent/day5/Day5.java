package com.ostach.advent.day5;


import com.ostach.advent.FileUtils;
import lombok.Value;

import java.util.List;
import java.util.stream.Collectors;

class Day5 {

    private static final int ROWS = 128;
    private static final int COLUMNS = 8;

    public void run1() {
        Long max = FileUtils.getLinesStream("day5/input.txt")
                .map(this::decodeSeatNumber)
                .max(Long::compareTo)
                .orElseThrow(() -> new RuntimeException("Didn't found any seat numberer"));

        System.out.println("Max seat number: " + max);
    }

    public void run2() {
        List<Long> allSeats = FileUtils.getLinesStream("day5/input.txt")
                .map(this::decodeSeatNumber)
                .sorted()
                .collect(Collectors.toList());

        for (int i = 0; i < allSeats.size() - 1; i++) {
            if (allSeats.get(i) + 1 != allSeats.get(i + 1)) {
                System.out.println("My seat number: " + (allSeats.get(i) + 1));
                return;
            }
        }
    }

    public long decodeSeatNumber(String coded) {
        return 8L * getRow(coded) + getColumn(coded);
    }

    public int getRow(String coded) {
        return decode(coded, 0, ROWS, 'F', 'B');
    }

    public int getColumn(String coded) {
        return decode(coded, log2(ROWS), COLUMNS, 'L', 'R');
    }

    private int decode(String coded, int start, int elements, char lowerHalfChar, char higherHalfChar) {
        Range currentRange = new Range(0, elements - 1);
        for (int i = 0; i < log2(elements); i++) {
            char operation = coded.charAt(start + i);
            if (operation == lowerHalfChar) currentRange = currentRange.getLowerHalf();
            else if (operation == higherHalfChar) currentRange = currentRange.getHigherHalf();
            else throw new IllegalArgumentException("Incorrect operation character (Not 'F' or 'B'): " + operation);
        }
        if (currentRange.isOneElementRange()) {
            return currentRange.getStartInclusive();
        } else {
            throw new RuntimeException("Range didn't finish as one-element range: " + currentRange);
        }
    }

    private static int log2(int n) {
        return (int) (Math.log(n) / Math.log(2));
    }

    @Value
    private static final class Range {
        int startInclusive;
        int endInclusive;

        protected Range getLowerHalf() {
            return new Range(startInclusive, endInclusive - half());
        }

        protected Range getHigherHalf() {
            return new Range(startInclusive + half(), endInclusive);
        }

        protected boolean isOneElementRange() {
            return startInclusive == endInclusive;
        }

        private int half() {
            return (endInclusive - startInclusive) / 2 + 1;
        }
    }
}
