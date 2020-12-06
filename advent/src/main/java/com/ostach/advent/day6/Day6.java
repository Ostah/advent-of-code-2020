package com.ostach.advent.day6;


import com.google.common.collect.Sets;
import com.google.common.primitives.Chars;
import org.apache.commons.collections4.SetUtils;

import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;
import static java.util.stream.IntStream.rangeClosed;

class Day6 {

    private static final Set<Character> ALL_CHARACTERS =
            rangeClosed('a', 'z').mapToObj(i -> (char) i).collect(toSet());

    public int run1(String input) {
        return Stream.of(input.split("\n\n"))
                .map(this::getAllLettersThatOccuredInGroup)
                .map(Set::size)
                .reduce(0, Integer::sum);
    }

    public int run2(String input) {
        return Stream.of(input.split("\n\n"))
                .map(this::getLettersChosenByEveryPerson)
                .map(Set::size)
                .reduce(0, Integer::sum);
    }

    private Set<Character> getAllLettersThatOccuredInGroup(String group) {
        String normalized = getRidOfWhitechars(group);
        return normalized.chars()
                .mapToObj(i -> (char) i)
                .collect(toSet());
    }

    private Set<Character> getLettersChosenByEveryPerson(String group) {
        Stream<Set<Character>> hashSetStream = Stream.of(group.split("\n"))
                .map(this::getRidOfWhitechars)
                .map(String::toCharArray)
                .map(Chars::asList)
                .map(Sets::newHashSet);
        return hashSetStream
                .reduce(ALL_CHARACTERS, SetUtils::intersection);
    }

    private String getRidOfWhitechars(String group) {
        return group.trim().replaceAll("\\s+", "");
    }
}
