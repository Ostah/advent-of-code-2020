package com.ostach.advent.day2.day1.two;


import com.ostach.advent.FileUtils;
import lombok.Builder;
import lombok.Value;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;

public class Day2_2 {

    Pattern pattern = Pattern.compile("(\\d*)-(\\d*) (\\w): (\\w*)");

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
                    .firstPosition(parseInt(matcher.group(1)))
                    .secondPosition(parseInt(matcher.group(2)))
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
        int firstPosition;
        int secondPosition;
        char letter;
        String password;

        boolean isValidPassword() {
            return xor(
                    charAtPositionEqualTo(password, firstPosition, letter),
                    charAtPositionEqualTo(password, secondPosition, letter)
            );
        }

        private boolean charAtPositionEqualTo(String string, int position, char character) {
            return position > string.length()
                    ? false
                    : string.charAt(position - 1) == character;
        }

        private boolean xor(boolean x, boolean y) {
            return ( ( x || y ) && ! ( x && y ) );
        }
    }
}
