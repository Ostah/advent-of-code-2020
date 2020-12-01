package com.ostach.advent;

import com.google.common.collect.ImmutableList;

public class Numbers {
    private ImmutableList<Integer> values;

    public Numbers(Integer i1, Integer i2) {
        values = ImmutableList.of(i1, i2);
    }

    public Numbers(Numbers numbers, Integer i) {
       this.values = ImmutableList.<Integer>builder()
               .addAll(numbers.values)
               .add(i)
               .build();
    }


    public boolean sumsTo2020() {
        return values.stream().reduce(0, Integer::sum) == 2020;
    }

    public long getProduct() {
        return values.stream().reduce(1, (a, b) -> a * b);
    }
}
