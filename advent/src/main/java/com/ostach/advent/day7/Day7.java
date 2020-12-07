package com.ostach.advent.day7;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.ostach.advent.FileUtils.getLinesStream;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

class Day7 {

    private static final Pattern INITIAL_DIVIDE = Pattern.compile("(.*) bags contain (.*).");
    private static final Pattern CHILD_PATTERN = Pattern.compile("(\\d) (.*) (bags|bag)");
    private static final String LOOKING_FOR = "shiny gold";
    private static final String NO_BAGS_INSIDE = "no other bags";

    private Map<String, Bag> repository = new HashMap<>();

    long run1(String path) {
        repository.clear();
        Stream<String> lines = getLinesStream(path);

        List<Bag> rootBags = lines.map(this::parseLine).collect(toList());
        Set<String> collect = rootBags.stream()
                .filter(bag -> bag.contains(LOOKING_FOR))
                .map(Bag::getColor)
                .collect(Collectors.toSet());

        return collect.size();
    }

    long run2(String path) {
        repository.clear();
        Stream<String> lines = getLinesStream(path);

        lines.map(this::parseLine).collect(toList());

        return getBag(LOOKING_FOR).howManyChildrenEaches();

    }

    private Bag parseLine(String line) {
        Matcher matcher = INITIAL_DIVIDE.matcher(line);
        if (matcher.find()) {
            String parentString = matcher.group(1).trim();

            String childrenString = matcher.group(2).trim();
            Bag parentBag = getBag(parentString);
            parentBag.addChildren(getChildren(childrenString));
            return parentBag;
        } else {
            throw new RuntimeException("INITIAL_DIVIDE didn't match in line " + line);
        }
    }

    private List<ChildEntry> getChildren(String childrenGroup) {
        if (NO_BAGS_INSIDE.equals(childrenGroup)) {
            return emptyList();
        } else {
            String[] children = childrenGroup.split(", ");
            return Arrays.stream(children)
                    .map(String::trim)
                    .map(this::toChildBag)
                    .collect(toList());
        }
    }

    private ChildEntry toChildBag(String line) {
        Matcher matcher = CHILD_PATTERN.matcher(line);
        if (matcher.find()) {
            int quantity = Integer.parseInt(matcher.group(1).trim());
            String color = matcher.group(2).trim();
            return new ChildEntry(color, quantity);
        } else {
            throw new RuntimeException("CHILD_PATTERN didn't match in line " + line);
        }
    }

    private Bag getBag(String color) {
        if (repository.containsKey(color)) {
            return repository.get(color);
        } else {
            Bag newBag = new Bag(color);
            repository.put(color, newBag);
            return newBag;
        }
    }

    @Data
    @RequiredArgsConstructor
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    private class Bag {
        List<Bag> parents = new LinkedList<>();
        Map<Bag, Integer> children = new HashMap<>();

        @EqualsAndHashCode.Include final String color;

        protected void addChildren(List<ChildEntry> newChildren) {
            for (ChildEntry child : newChildren) {
                Bag childBag = getBag(child.getColor());
                childBag.addParent(this);
                children.put(childBag, child.getHowMany());
            }
        }

        protected void addParent(Bag bag) {
            parents.add(bag);
        }

        protected boolean contains(String lookingFor) {
            return children.keySet().stream()
                    .anyMatch(child -> child.isOrContains(lookingFor));
        }

        private boolean isOrContains(String bagColor) {
            if (color.equals(bagColor)) {
                return true;
            } else {
                return contains(bagColor);
            }
        }

        protected long howManyChildrenEaches() {
            Integer firstsum = children.values().stream().reduce(Integer::sum).orElse(0);
            Long aLong = children.entrySet().stream()
                    .map(entry -> (entry.getValue() * entry.getKey().howManyChildrenEaches()))
                    .reduce(Long::sum)
                    .orElse(0L);
            return aLong + firstsum;
        }
    }


    @Value
    private static class ChildEntry {
        String color;
        int howMany;
    }
}
