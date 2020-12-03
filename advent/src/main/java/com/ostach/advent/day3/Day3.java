package com.ostach.advent.day3;


import com.ostach.advent.FileUtils;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.experimental.NonFinal;

class Day3 {

    public void run1() {
        FlightMap flightMap = new FlightMap(FileUtils.getCharactersArray("day3/input.txt"));

        FlightMapCheck flightMapCheck = new FlightMapCheck(flightMap, new Slope(3, 1));

        int trees = flightMapCheck.findTrees();
        System.out.println(trees);
    }

    public void run2() {
        FlightMap flightMap = new FlightMap(FileUtils.getCharactersArray("day3/input.txt"));

        long multipliedChecks = (long)
                new FlightMapCheck(flightMap, new Slope(1, 1)).findTrees() *
                new FlightMapCheck(flightMap, new Slope(3, 1)).findTrees() *
                new FlightMapCheck(flightMap, new Slope(5, 1)).findTrees() *
                new FlightMapCheck(flightMap, new Slope(7, 1)).findTrees() *
                new FlightMapCheck(flightMap, new Slope(1, 2)).findTrees();

        System.out.println(multipliedChecks);
    }

    @RequiredArgsConstructor
    private static class FlightMapCheck {
        private static final char TREE = '#';

        private final FlightMap flightMap;
        private final Slope slope;
        @NonFinal Position position = new Position();
        @NonFinal int count = 0;

        int findTrees() {
            do {
                if (isTree()) {
                    count++;
                }
                position = position.advance(slope);
            } while (flightMap.isValidPosition(position));
            return count;
        }

        private boolean isTree() {
            return flightMap.charAtPosition(position) == TREE;
        }
    }

    @Value
    private static class FlightMap {
        char[][] matrix;

        public char charAtPosition(Position position) {
            if (position.getY() >= getYLength()) {
                throw new IllegalArgumentException("Y out of matrix bounds: Y:" + position.getY() + " : array length: " + matrix.length);
            }
            Position normalizedPosition = normalize(position);
            return matrix[position.getY()][normalizedPosition.getX()];
        }

        private Position normalize(Position position) {
            if (position.getX() < getXLength()) {
                return position;
            } else {
                int x = position.getX() % getXLength();
                return new Position(x, position.getY());
            }
        }

        private int getYLength() {
            return matrix.length;
        }

        private int getXLength() {
            return matrix[0].length;
        }

        public boolean isValidPosition(Position position) {
            return position.getY() < getYLength();
        }
    }

    @Value
    @AllArgsConstructor
    private static class Position {
        int x;
        int y;

        public Position() {
            x = 0;
            y = 0;
        }

        public Position advance(Slope slope) {
            return new Position(x + slope.getDx(), y + slope.getDy());
        }
    }

    @Value
    @RequiredArgsConstructor
    private static class Slope {
        int dx;
        int dy;
    }
}
