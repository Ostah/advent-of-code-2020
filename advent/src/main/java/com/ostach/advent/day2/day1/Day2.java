package com.ostach.advent.day2.day1;


import com.ostach.advent.FileUtils;
import com.ostach.advent.Numbers;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

public class Day2 {

    public void run() {
        Set<Integer> inputList = prepareInputData();

        Stream<Numbers> productOfNumbers = cartesian3(inputList, inputList, inputList);
        productOfNumbers
                .filter(Numbers::sumsTo2020)
                .map(Numbers::getProduct)
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

    private Stream<Numbers> cartesian3(Collection<Integer> collection1, Collection<Integer> collection2, Collection<Integer> collection3) {
        return collection1.stream().flatMap(element1 ->
                collection2.stream().map(element2 -> new Numbers(element1, element2))
                .flatMap(numbers -> collection3.stream().map(element3 -> new Numbers(numbers, element3)))
        );
    }

}
