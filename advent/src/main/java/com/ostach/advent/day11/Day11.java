package com.ostach.advent.day11;


import com.ostach.advent.FileUtils;

import java.util.Arrays;

class Day11 {
    private final char FREE = 'L';
    private final char OCCUPIED = '#';
    private final char FLOOR = '.';

    public long run1(String path) {
        char[][] map = FileUtils.getCharactersArray(path);

        int oldOccupied=0;
        int newOcupied = 0;
        do {

            oldOccupied = newOcupied;

//            System.out.println("");
            map = step(map);
//            printMap(map);
            newOcupied = countOccupied(map);
//            System.out.println("Old: " + oldOccupied+", new: "+newOcupied);

        } while (oldOccupied != newOcupied);
        return newOcupied;
    }

    private void printMap(char[][] newMap) {
        for (char[] x : newMap)
        {
            for (char y : x)
            {
                System.out.print(y + " ");
            }
            System.out.println();
        }
    }

    private char[][] step(char[][] originalMap) {
        char[][] map = copy(originalMap);
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                char currentSeat = originalMap[i][j];
                if (currentSeat == FLOOR) continue;
                int neighbours = getNeighbours(i, j, originalMap);
                if (currentSeat == FREE && neighbours == 0) {
                    map[i][j] = OCCUPIED;
                }
                else if (currentSeat == OCCUPIED && neighbours >= 4) map[i][j] = FREE;
            }
        }
        return map;
    }

    private int getNeighbours(int i, int j, char[][] map) {
        int neighbours = 0;
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if(x == 0 && y == 0) continue;
                if(i+x >=map.length || i+x <0) continue;
                if(j+y >= map[i+x].length || j+y<0) continue;
                if(map[i+x][j+y] == OCCUPIED) neighbours++;
            }
        }
        return neighbours;
    }

    private int countOccupied(char[][] map) {
        int occupied = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {

                if (map[i][j] == OCCUPIED) occupied++;
            }
        }
        return occupied;
    }



    private char[][] copy(char[][] map) {
        return Arrays.stream(map).map(char[]::clone).toArray(char[][]::new);
    }

    public long run2(String path) {
        return 0;
    }

}
