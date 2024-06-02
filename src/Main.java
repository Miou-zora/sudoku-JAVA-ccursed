import java.util.function.BiFunction;
import java.util.stream.IntStream;

/**
 * Disclamer: this code is awful and I know it. The `is_valid_sodoku` function check if a sodoku is valid using only ~~cursed~~ functional paradigm
 */

public class Main {
    public static void main(String[] args) {
        int [][] valid_grid = {
                { 4, 1, 5, 6, 3, 8, 9, 7, 2 },
                { 3, 6, 2, 4, 7, 9, 1, 8, 5 },
                { 7, 8, 9, 2, 1, 5, 3, 6, 4 },
                { 9, 2, 6, 3, 4, 1, 7, 5, 8 },
                { 1, 3, 8, 7, 5, 6, 4, 2, 9 },
                { 5, 7, 4, 9, 8, 2, 6, 3, 1 },
                { 2, 5, 7, 1, 6, 4, 8, 9, 3 },
                { 8, 4, 3, 5, 9, 7, 2, 1, 6 },
                { 6, 9, 1, 8, 2, 3, 5, 4, 7 },
        };
        int [][] invalid_col_grid = {
                { 1, 4, 5, 6, 3, 8, 9, 7, 2 },
                { 3, 6, 2, 4, 7, 9, 1, 8, 5 },
                { 7, 8, 9, 2, 1, 5, 3, 6, 4 },
                { 9, 2, 6, 3, 4, 1, 7, 5, 8 },
                { 1, 3, 8, 7, 5, 6, 4, 2, 9 },
                { 5, 7, 4, 9, 8, 2, 6, 3, 1 },
                { 2, 5, 7, 1, 6, 4, 8, 9, 3 },
                { 8, 4, 3, 5, 9, 7, 2, 1, 6 },
                { 6, 9, 1, 8, 2, 3, 5, 4, 7 },
        };
        int [][] invalid_line_grid = {
                { 4, 1, 5, 4, 3, 8, 9, 7, 2 },
                { 3, 6, 2, 6, 7, 9, 1, 8, 5 },
                { 7, 8, 9, 2, 1, 5, 3, 6, 4 },
                { 9, 2, 6, 3, 4, 1, 7, 5, 8 },
                { 1, 3, 8, 7, 5, 6, 4, 2, 9 },
                { 5, 7, 4, 9, 8, 2, 6, 3, 1 },
                { 2, 5, 7, 1, 6, 4, 8, 9, 3 },
                { 8, 4, 3, 5, 9, 7, 2, 1, 6 },
                { 6, 9, 1, 8, 2, 3, 5, 4, 7 },
        };
        int [][] invalid_square_grid = {
                { 4, 1, 5, 6, 3, 8, 9, 7, 2 },
                { 3, 6, 2, 4, 7, 9, 1, 8, 5 },
                { 7, 8, 9, 2, 1, 5, 3, 6, 4 },
                { 9, 2, 6, 3, 4, 1, 8, 5, 7 },
                { 1, 3, 7, 8, 5, 6, 4, 2, 9 },
                { 5, 7, 4, 9, 8, 2, 6, 3, 1 },
                { 2, 5, 8, 1, 6, 4, 7, 9, 3 },
                { 8, 4, 3, 5, 9, 7, 2, 1, 6 },
                { 6, 9, 1, 7, 2, 3, 5, 4, 8 },
        };
        System.out.println("Valid");
        System.out.println(is_valid_predicate(valid_grid, (row, col) -> col, (row, col) -> row));
        System.out.println(is_valid_predicate(valid_grid, (row, col) -> row, (row, col) -> col));
        System.out.println(is_valid_predicate(valid_grid, (row, col) -> (row / 3 + 3 * (col / 3)), (row, col) -> (row % 3 + 3 * (col % 3))));
        System.out.println(is_valid_sodoku(invalid_square_grid));

        System.out.println("invalid_line_grid");
        System.out.println(is_valid_predicate(invalid_line_grid, (row, col) -> col, (row, col) -> row));
        System.out.println(is_valid_predicate(invalid_line_grid, (row, col) -> row, (row, col) -> col));
        System.out.println(is_valid_predicate(invalid_line_grid, (row, col) -> (row / 3 + 3 * (col / 3)), (row, col) -> (row % 3 + 3 * (col % 3))));
        System.out.println(is_valid_sodoku(invalid_square_grid));

        System.out.println("invalid_col_grid");
        System.out.println(is_valid_predicate(invalid_col_grid, (row, col) -> col, (row, col) -> row));
        System.out.println(is_valid_predicate(invalid_col_grid, (row, col) -> row, (row, col) -> col));
        System.out.println(is_valid_predicate(invalid_col_grid, (row, col) -> (row / 3 + 3 * (col / 3)), (row, col) -> (row % 3 + 3 * (col % 3))));
        System.out.println(is_valid_sodoku(invalid_square_grid));

        System.out.println("invalid_square_grid");
        System.out.println(is_valid_predicate(invalid_square_grid, (row, col) -> col, (row, col) -> row));
        System.out.println(is_valid_predicate(invalid_square_grid, (row, col) -> row, (row, col) -> col));
        System.out.println(is_valid_predicate(invalid_square_grid, (row, col) -> (row / 3 + 3 * (col / 3)), (row, col) -> (row % 3 + 3 * (col % 3))));
        System.out.println(is_valid_sodoku(invalid_square_grid));
    }

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
}