package com.ostach.advent.day4;


import com.ostach.advent.FileUtils;

import java.util.AbstractMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

class Day4 {

    private static final Pattern fourDigits = Pattern.compile("[0-9]{4}");
    private static final Pattern heigth = Pattern.compile("[0-9]{2,3}(cm|in)");
    private static final Pattern hairColor = Pattern.compile("#[0-9a-f]{6}");
    private static final Pattern eyeColor = Pattern.compile("(amb|blu|brn|gry|grn|hzl|oth)");
    private static final Pattern pidMatcher = Pattern.compile("[0-9]{9}");

    public void run1() {
        String input = FileUtils.getString("day4/input1.txt");

        long count = Stream.of(input.split("\n\n"))
                .map(this::toDocumentProperties)
                .filter(this::isValidDocument)
                .count();

        System.out.println(count);
    }

    public void run2() {
        String input = FileUtils.getString("day4/input1.txt");

        long count = Stream.of(input.split("\n\n"))
                .map(this::toDocumentProperties)
                .filter(this::isValidDocument2)
                .count();

        System.out.println(count);
    }


    private boolean isValidDocument(Map<String, String> documentProperties) {
        return documentProperties.containsKey("byr")
                && documentProperties.containsKey("iyr")
                && documentProperties.containsKey("eyr")
                && documentProperties.containsKey("hgt")
                && documentProperties.containsKey("hcl")
                && documentProperties.containsKey("ecl")
                && documentProperties.containsKey("pid");

    }

    private boolean isValidDocument2(Map<String, String> documentProperties) {
        return hasValidByr(documentProperties)
                && hasValidIyr(documentProperties)
                && hasValidEyr(documentProperties)
                && hasValidHgt(documentProperties)
                && hasValidHcl(documentProperties)
                && hasValidEcl(documentProperties)
                && hasValidPid(documentProperties);
    }

    private boolean hasValidPid(Map<String, String> documentProperties) {
        String pid = documentProperties.get("pid");
        if (isNull(pid)) return false;
        return pidMatcher.matcher(pid).matches();
    }

    private boolean hasValidEcl(Map<String, String> documentProperties) {
        String ecl = documentProperties.get("ecl");
        if (isNull(ecl)) return false;
        return eyeColor.matcher(ecl).matches();
    }

    private boolean hasValidHcl(Map<String, String> documentProperties) {
        String hcl = documentProperties.get("hcl");
        if (isNull(hcl)) return false;
        return hairColor.matcher(hcl).matches();
    }

    private boolean hasValidHgt(Map<String, String> documentProperties) {
        String hgt = documentProperties.get("hgt");
        if (isNull(hgt)) return false;
        if (!heigth.matcher(hgt).matches()) return false;
        String unit = hgt.substring(hgt.length() - 2);
        int height = Integer.parseInt(removeLastChars(hgt, 2));
        if ("cm".equals(unit)) {
            return height >= 150 && height <= 193;
        } else if ("in".equals(unit)) {
            return height >= 59 && height <= 76;
        } else {
            throw new RuntimeException("Wrong unit");
        }
    }

    private static String removeLastChars(String str, int chars) {
        return str.substring(0, str.length() - chars);
    }

    private boolean hasValidEyr(Map<String, String> documentProperties) {
        String eyr = documentProperties.get("eyr");
        if (isNull(eyr)) return false;
        if (!fourDigits.matcher(eyr).matches()) return false;
        int integerValue = Integer.parseInt(eyr);
        return integerValue >= 2020 && integerValue <= 2030;
    }

    private boolean hasValidIyr(Map<String, String> documentProperties) {
        String iyr = documentProperties.get("iyr");
        if (isNull(iyr)) return false;
        if (!fourDigits.matcher(iyr).matches()) return false;
        int integerValue = Integer.parseInt(iyr);
        return integerValue >= 2010 && integerValue <= 2020;
    }

    private boolean hasValidByr(Map<String, String> documentProperties) {
        String byr = documentProperties.get("byr");
        if (isNull(byr)) return false;
        if (!fourDigits.matcher(byr).matches()) return false;
        int integerValue = Integer.parseInt(byr);
        return integerValue >= 1920 && integerValue <= 2002;
    }

    private Map<String, String> toDocumentProperties(String s) {
        return Stream.of(s.split("[\\n\\r\\s]"))
                .map(String::trim)
                .map(this::toMapEntry)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private Map.Entry<String, String> toMapEntry(String s) {
        String[] split = s.split(":");
        return new AbstractMap.SimpleEntry<>(split[0], split[1]);
    }


}
