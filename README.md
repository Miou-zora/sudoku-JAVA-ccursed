# sudoku-JAVA-ccursed

```java
    static private boolean is_valid_sodoku(int [][] grid) {
        return is_valid_predicate(grid, (row, col) -> col, (row, col) -> row) &&
                is_valid_predicate(grid, (row, col) -> row, (row, col) -> col) &&
                is_valid_predicate(grid, (row, col) -> (row / 3 + 3 * (col / 3)), (row, col) -> (row % 3 + 3 * (col % 3)));
    }

    static private boolean is_valid_predicate(int [][] grid, BiFunction<Integer, Integer, Integer> predi_row, BiFunction<Integer, Integer, Integer> predi_col) {
        return IntStream.range(0, grid.length)
                .mapToObj(colIndex -> IntStream.range(0, grid[colIndex].length)
                        .mapToObj(rowIndex -> grid[predi_row.apply(rowIndex, colIndex)][predi_col.apply(rowIndex, colIndex)]))
                .allMatch((row) -> row.distinct().mapToInt(i -> i).mapToObj(i -> i >= 1 && i <= 9).filter(i -> i).count() == 9);
    }
```

This project is a sudoku resolver written in Java using only streams.
