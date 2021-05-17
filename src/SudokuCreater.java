import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class SudokuCreater {
	private char[][] A = new char[9][9];

	/* initialize the two dimensional array and size */
	static int board[][] = new int[9][9];
	

	/* fill the two dimensional array with randomly generated numbers */
	public boolean fillBoard() {
		return this.fillBoard(0, 0);
	}

	public boolean fillBoard(int row, int col) {

		Random rand = new Random();
		int cand = rand.nextInt(8) + 1;
		int attempts = 0;

		if (col > 8) {
			return fillBoard(row + 1, 0);
		}

		if (row > 8) {
			return true;
		}

		/* determines if a candidate is valid and not already existing in the matrix */
		while (attempts < 9) {
			if (isValid(row, col, cand) == true) {
				board[row][col] = cand;
				if (fillBoard(row, col + 1) == true) {
					return true;
				}
				board[row][col] = 0;
			}
			cand++;
			if (cand == 10)
				cand = 1;
			attempts++;
		}

		return false;
	}

	public char[][] change() {

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				switch (board[i][j]) {
				case (0):
					A[i][j] = '0';
					break;
				case (1):
					A[i][j] = 'C';
					break;
				case (2):
					A[i][j] = 'N';
					break;
				case (3):
					A[i][j] = 'G';
					break;
				case (4):
					A[i][j] = 'B';
					break;
				case (5):
					A[i][j] = 'I';
					break;
				case (6):
					A[i][j] = 'M';
					break;
				case (7):
					A[i][j] = '1';
					break;
				case (8):
					A[i][j] = '2';
					break;
				case (9):
					A[i][j] = '3';
					break;

				}
			}
		}

		return A;

	}

	/*
	 * this function checks the columns and rows to make sure the generated number
	 * does not exist
	 */
	public boolean isValid(int row, int col, int cand) {
		// checks if candidate exists in the column
		for (int i = 0; i < 9; i++) {
			if (board[i][col] == cand) {
				return false;
			}

		}

		// checks if candidate exists in the row
		for (int j = 0; j < 9; j++) {
			if (board[row][j] == cand) {
				return false;
			}
		}

		// check here if candidate exists in the box; NEED TO COMPLETE
		for (int k = (row - (row % 3)); k < (row - (row % 3) + 3); k++) {
			for (int l = (col - (col % 3)); l < (col - (col % 3) + 3); l++) {
				if (board[k][l] == cand) {
					return false;
				}
			}
		}

		return true;
	}

	/* this function prints out the board */
	public void printBoard() {
		change();
		System.out.println(" -----------------------");
		for (int i = 0; i < 3; i++) {
			System.out.print("| ");
			for (int j = 0; j < 3; j++) {
				System.out.print(A[i][j] + " ");
			}
			System.out.print("| ");
			for (int j = 3; j < 6; j++) {
				System.out.print(A[i][j] + " ");
			}
			System.out.print("| ");
			for (int j = 6; j < 9; j++) {
				System.out.print(A[i][j] + " ");
			}
			System.out.println("| ");
		}
		System.out.println(" -----------------------");
		for (int i = 3; i < 6; i++) {
			System.out.print("| ");
			for (int j = 0; j < 3; j++) {
				System.out.print(A[i][j] + " ");
			}
			System.out.print("| ");
			for (int j = 3; j < 6; j++) {
				System.out.print(A[i][j] + " ");
			}
			System.out.print("| ");
			for (int j = 6; j < 9; j++) {
				System.out.print(A[i][j] + " ");
			}
			System.out.println("| ");
		}
		System.out.println(" -----------------------");
		for (int i = 6; i < 9; i++) {
			System.out.print("| ");
			for (int j = 0; j < 3; j++) {
				System.out.print(A[i][j] + " ");
			}
			System.out.print("| ");
			for (int j = 3; j < 6; j++) {
				System.out.print(A[i][j] + " ");
			}
			System.out.print("| ");
			for (int j = 6; j < 9; j++) {
				System.out.print(A[i][j] + " ");
			}
			System.out.println("| ");
		}
		System.out.println(" -----------------------");
	}

	public int[][] EraseCell(int[][] matrix) {
		if (5 <= board[0].length) {
			ArrayList<Integer> exclude = new ArrayList<>();
			for (int i = 0; i < board.length; i++) {
				int lvl = random(2, 5);
				for (int j = 0; j < lvl; j++) {
					int rnd = random(0, (board[0].length) - 1, exclude);
					exclude.add(rnd);
					board[i][rnd - 1] = 0;
				}
				exclude.clear();
			}
			return board;
		} else {
			System.out.println("Error in matrix level!!!");
			return board;
		}
	}

	int random(int start, int end, ArrayList<Integer> exclude) {
		Random rand = new Random();
		int range = end - start + 1;
		int random = rand.nextInt(range) + 1;
		while (exclude.contains(random)) {
			random = rand.nextInt(range) + 1;
		}
		return random;
	}

	int random(int start, int end) {
		Random rand = new Random();
		int random = rand.nextInt((end + 1) - start) + start;
		return random;
	}

	public static void main(String[] args) {

		SudokuCreater C = new SudokuCreater();
		SudokuSolver S = new SudokuSolver(board);


		System.out.println(
				"Welcome the sudoku program: \n" + "To create a sudoku press C \n" + "To solve the sudoku press S\n");
	
		while (true) {
			
			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine();
			
			if(input.equals("C")) {
			if (C.fillBoard()) {
				System.out.println("Created sudoku : ");
				C.EraseCell(board);
				C.printBoard();

			}
			}
			else if(input.equals("S")) {
			System.out.println("Solved sudoku");
			S.solveSudoku();
			C.printBoard();
		
			return;
			}
			
		}
	}

}
