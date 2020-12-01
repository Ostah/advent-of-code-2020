package com.ostach.advent.day1;


import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

public class Day1 {

    public void run() {
        Set<Integer> inputList = prepareInputData();

        Stream<Touple> productOfNumbers = cartesian(inputList, inputList);
        productOfNumbers
                .filter(Touple::sumsTo2020)
                .map(Touple::getProduct)
                .distinct()
                .forEach(System.out::println);
    }

    private Set<Integer> prepareInputData() {
        Set<Integer> inputList = FileUtils.getFileLines("day1/input.txt")
                .stream()
                .map(Integer::parseInt)
                .collect(toSet());
        return inputList;
    }

    private Stream<Touple> cartesian(Collection<Integer> collection1, Collection<Integer> collection2) {
        return collection1.stream().flatMap(element1 ->
                collection2.stream().map(element2 -> new Touple(element1, element2)));
    }

    @Data
    @RequiredArgsConstructor
    private static class Touple {
        private final int number1;
        private final int number2;

        boolean sumsTo2020() {
            return number1 + number2 == 2020;
        }

        long getProduct() {
            return number1 * number2;
        }
    }
}
