package com.ostach.advent.day9;


import com.ostach.advent.FileUtils;

import java.util.List;
import java.util.stream.Collectors;

class Day9 {

    public long run1(String path, int preamble) {
        List<Long> input = FileUtils.getLinesStream(path)
                .map(Long::parseLong)
                .collect(Collectors.toList());

        return findSum(preamble, input);
    }

    private long findSum(int preamble, List<Long> input) {
        for (int i = preamble; i < input.size(); i++) {
            long numberToCheck = input.get(i);
            List<Long> possibleValues = input.subList(i - preamble, i);
            if (!canFind2NumbersThatSumTo(numberToCheck, possibleValues)) return numberToCheck;
        }
        throw new RuntimeException("didnt found the number");
    }

    private boolean canFind2NumbersThatSumTo(long numberToCheck, List<Long> possibleValues) {
        return possibleValues.stream()
                .map(i1 -> possibleValues.stream()
                        .anyMatch(i2 -> !i2.equals(i1) && (i2 + i1 == numberToCheck)))
                .anyMatch(value -> value);
    }

    public long run2(String path, int preamble) {
        List<Long> input = FileUtils.getLinesStream(path)
                .map(Long::parseLong)
                .collect(Collectors.toList());
        long valueToFind = findSum(preamble, input);

        List<Long> range = findRange(input, valueToFind);
        return range.stream().min(Long::compareTo).get() + range.stream().max(Long::compareTo).get();
    }

    private List<Long> findRange(List<Long> input, long valueToFind) {
        for (int i = 0; i < input.size(); i++) {
            int sum = 0;
            for (int j = 0; i + j < input.size(); j++) {
                sum += input.get(i + j);
                if (sum == valueToFind) {
                    return input.subList(i, i + j + 1);
                }
                if (sum > valueToFind) break;
            }
        }
        throw new RuntimeException("didnt found the number 2");
    }

}
