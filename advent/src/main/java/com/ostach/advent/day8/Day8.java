package com.ostach.advent.day8;


import lombok.Value;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.ostach.advent.FileUtils.getLinesStream;
import static java.util.stream.Collectors.toList;

class Day8 {
    private static final Pattern LINE_REGEX = Pattern.compile("([a-z]{3}) ((?:\\+|-)\\d*)");

    public long run1(String path) {
        List<Expression> expressions = getLinesStream(path)
                .map(this::toExpression)
                .collect(toList());

        return invoke(expressions).getAccumulator();
    }

    public long run2(String path) {
        List<Expression> expressions = getLinesStream(path)
                .map(this::toExpression)
                .collect(toList());

        for (int i = 0; i < expressions.size(); i++) {
            if (expressions.get(i).getCommand() == Command.acc) continue;
            List<Expression> replaced = copyWithReplacedExpression(expressions, i);
            Return invocationResult = invoke(replaced);
            if (invocationResult.isFinished()) return invocationResult.getAccumulator();
        }
        throw new RuntimeException("Didn't found expresion replacemen which fixes the program");
    }

    private List<Expression> copyWithReplacedExpression(List<Expression> expressions, int i) {
        List<Expression> copy = new ArrayList<>(expressions);

        Expression newExpression = changeExpression(expressions.get(i));
        copy.set(i, newExpression);
        return copy;
    }

    private Expression changeExpression(Expression expression) {
        switch (expression.getCommand()) {
            case acc:
                throw new RuntimeException("Shouldnt enter here");
            case nop:
                return new Expression(Command.jmp, expression.getValue());
            case jmp:
                return new Expression(Command.nop, expression.getValue());
        }
        throw new RuntimeException("Shouldn't get here");
    }

    private Return invoke(List<Expression> expressions) {
        long accumulator = 0L;
        int position = 0;
        Set<Integer> usedCommands = new HashSet<>();

        while (true) {
            usedCommands.add(position);
            Expression expression = expressions.get(position);
            switch (expression.getCommand()) {
                case acc:
                    accumulator += expression.getValue();
                    position++;
                    break;
                case nop:
                    position++;
                    break;
                case jmp:
                    position += expression.getValue();
                    break;
            }
            if (usedCommands.contains(position)) return new Return(false, accumulator);
            if (position == expressions.size()) return new Return(true, accumulator);
        }
    }

    private Expression toExpression(String line) {
        Matcher matcher = LINE_REGEX.matcher(line);
        if (matcher.find()) {
            String commandString = matcher.group(1).trim();
            String valueString = matcher.group(2).trim();
            return new Expression(Command.valueOf(commandString), Integer.parseInt(valueString));
        } else {
            throw new RuntimeException("LINE_REGEX didn't match in line " + line);
        }
    }

    @Value
    private static class Expression {
        Command command;
        int value;
    }

    @Value
    private static class Return {
        boolean finished;
        long accumulator;
    }


    private enum Command {
        acc,
        nop,
        jmp
    }
}
