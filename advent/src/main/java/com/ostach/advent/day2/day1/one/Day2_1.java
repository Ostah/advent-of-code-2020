package com.ostach.advent.day2.day1.one;


import com.google.common.base.CharMatcher;
import com.ostach.advent.FileUtils;
import lombok.Builder;
import lombok.Value;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;

class Day2_1 {

    private Pattern pattern = Pattern.compile("(\\d*)-(\\d*) (\\w): (\\w*)");

    public void run() {
        Stream<String> lines = FileUtils.getLinesStream("day2/input.txt");

        long count = lines
                .map(this::toPasswordEntry)
                .filter(PasswordEntry::isValidPassword)
                .count();
        System.out.println(count);
    }

    private PasswordEntry toPasswordEntry(String line) {
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            return PasswordEntry.builder()
                    .minimumOccurences(parseInt(matcher.group(1)))
                    .maximumOccurences(parseInt(matcher.group(2)))
                    .letter(matcher.group(3).charAt(0))
                    .password(matcher.group(4))
                    .build();
        } else {
            throw new RuntimeException("Matcher didn't match in line " + line);
        }
    }

    @Builder
    @Value
    private static class PasswordEntry {
        int minimumOccurences;
        int maximumOccurences;
        char letter;
        String password;

        boolean isValidPassword() {
            int occurences = CharMatcher.is(letter).countIn(password);
            return occurences >= minimumOccurences && occurences <= maximumOccurences;
        }
    }

}
