
public class SudokuSolver {
	// declare constants
	public static final int SIZE = 9;

	// declare grid
	private int[][] grid;

	/**
	 * Constructor: SudokuSolver Parameters: int[][] grid
	 */
	public SudokuSolver(int[][] grid) {
		this.grid = grid;
	}

	/**
	  Checks throug the given row to see if it already contains the given num 
	 */
	private boolean usedInRow(int row, int num) {
		for (int y = 0; y < SIZE; y++) {
			if (grid[row][y] == num) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Method: usedInCol Parameters: int col, int num Description: Checks through
	 * the given col to see if it already contains the given num Return: true if
	 * given num is found, false otherwise
	 */
	private boolean usedInCol(int col, int num) {
		for (int x = 0; x < SIZE; x++) {
			if (grid[x][col] == num) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * Description: Calculates which of the nine 3x3 sub-grids the coordinates (r,c)
	 * fall in, then searches that sub-grid to see if it already contains the given
	 * num
	 */
	private boolean usedInCube(int r, int c, int num) {
		int row = r - r % 3;
		int col = c - c % 3;
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				if (grid[x + row][y + col] == num) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Description: Calls each of the usedIn methods to determine if the given num
	 * can be assigned to the given row, col
	 */
	public boolean checkIfSafe(int row, int col, int num) {
		return !usedInRow(row, num) && !usedInCol(col, num) && !usedInCube(row, col, num);
	}

	
	public boolean solveSudoku() {
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				// we search an empty cell
				if (grid[row][col] == 0) {
					// we try possible number
					for (int number = 1; number <= SIZE; number++) {
						if (checkIfSafe(row, col, number)) {
							// number is okay. it respect sudoku contain it
							grid[row][col] = number;
							if (solveSudoku()) {// we start backtacking recursivly
								return true;

							} else {// if not a solution, we have an empty the cell and continue
								grid[row][col] = 0;

							}
						}
					}
					return false;
				}
			}
		}
		return true;

	}

}
