package com.ostach.advent.day10;


import com.google.common.collect.Streams;
import com.ostach.advent.FileUtils;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Day10 {

    private final Map<Key, Long> cache = new HashMap<>();

    public long run1(String path) {
        List<Integer> adapters = prepareAdapters(path);

        int buildInAdapterJoltage = adapters.stream().max(Integer::compareTo).get() + 3;

        adapters = Streams.concat(Stream.of(0), adapters.stream(), Stream.of(buildInAdapterJoltage))
                .collect(Collectors.toList());

        List<Integer> foundAdapterSequence = adapters.stream()
                .sorted(Integer::compareTo)
                .collect(Collectors.toList());

        Map<Integer, Integer> resultDifferences = new HashMap<>(Map.of(1, 0, 2, 0, 3, 0));
        for (int i = 0; i < foundAdapterSequence.size() - 1; i++) {
            Integer integer = foundAdapterSequence.get(i + 1) - foundAdapterSequence.get(i);
            resultDifferences.merge(integer, 1, Integer::sum);
        }

        return resultDifferences.get(1) * resultDifferences.get(3);
    }

    public long run2(String path) {
        List<Integer> adapters = prepareAdapters(path);

        return findWorkingSequence2(adapters, 0);
    }

    private long findWorkingSequence2(List<Integer> remaining, int previous) {

        if (remaining.isEmpty()) {
            return 1;
        }

        Long fromCache = cache.get(new Key(previous, remaining));
        if (fromCache != null) return fromCache;


        Integer current = remaining.remove(0);
        long count = findWorkingSequence2(remaining, current);

        if (!remaining.isEmpty() && canAttachTo(remaining.get(0), previous)) {
            count += findWorkingSequence2(remaining, previous);
        }
        remaining.add(0, current);

        addToCache(previous, remaining, count);
        return count;

    }

    private List<Integer> prepareAdapters(String path) {
        return FileUtils.getLinesStream(path)
                .map(Integer::parseInt)
                .sorted()
                .collect(Collectors.toList());

    }

    private void addToCache(int previous, List<Integer> remainingAdapters, Long count) {
        cache.put(new Key(previous, remainingAdapters), count);
    }

    boolean canAttachTo(Integer first, Integer second) {
        int difference = first - second;
        return difference <= 3 && difference > 0;

    }

    @Value
    @EqualsAndHashCode
    class Key {
        int previous;
        List<Integer> remaining;
    }

}
