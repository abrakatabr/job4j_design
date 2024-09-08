package ru.job4j.tasks.algo;

import java.util.HashSet;
import java.util.Objects;

public class BattleShip {
    public int countAliveShips(int[][] sea) {
        HashSet<Cell> ships = new HashSet<>();
        int result = 0;
        for (int row = 0; row < sea.length; row++) {
            for (int column = 0; column < sea[row].length; column++) {
              if (sea[row][column] == 1) {
                  if (ships.contains(new Cell(row, column))) {
                      continue;
                  }
                  ships.add(new Cell(row, column));
                  int tempRow = row;
                  int tempColumn = column;
                  while (tempColumn >= 0 && tempColumn < sea[row].length && sea[row][tempColumn] == 1) {
                      ships.add(new Cell(row, tempColumn));
                      tempColumn++;
                  }
                  while (tempRow < sea.length && sea[tempRow][column] == 1) {
                      ships.add(new Cell(tempRow, column));
                      tempRow++;
                  }
                result++;
            }
        }
    }
        return result;
    }

    private class Cell {
        int i;
        int j;

        public Cell(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Cell cell = (Cell) o;
            return i == cell.i && j == cell.j;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i, j);
        }
    }
}
