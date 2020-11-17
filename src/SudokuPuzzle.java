import java.io.File;
import java.util.Scanner;

/**
 * The SudokuPuzzle class contains the actial board in its private 2-D
 * array. It has many helper methods used in the SudokuSolver's Solve() mehthod!
 * 
 * @author finnbergquist
 *
 */

public class SudokuPuzzle {
	private int[][] grid = new int[9][9];// 2-Dimensional Array grid int[row][column]

	/**
	 * Constructor for SudokuPuzzle. It reads in a file name and imports the integers
	 * one by one into the cells of the grid.
	 * @param filename
	 */
	public SudokuPuzzle(String filename) {// constructor
		Scanner scan;
		try {
			scan = new Scanner(new File(filename));
		} catch (Exception e) {
			System.err.println("ERROR WITH FILE!");
			return;
		}
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				grid[i][j] = scan.nextInt();
			}
		}
	}

	/**
	 * Returns string representation of the board.
	 * @return string of grid
	 */
	public String toString() {
		String gridString = "";
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if(grid[i][j] == 0) {
					gridString += " _ ";
				}else {
					gridString += " " + grid[i][j] + " ";
				}
				
			}
			gridString += "\n";
		}
		return gridString;
	}

	/**
	 * Evaluates if two boards are the same
	 * 
	 * @param similairBoard
	 * @return true if they are the same
	 */
	public boolean Equals(SudokuPuzzle similairBoard) {
		int numberOfDifferences = 0;// traces number of unique grid spots
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (!(grid[i][j] == similairBoard.getNumberAt(i, j))) {
					numberOfDifferences += 1;
				}
			}
		}
		System.out.println("number of differences = " + numberOfDifferences);
		return numberOfDifferences == 0;
	}

	/**
	 * Evaluates to make sure a given move is valid; meaning that there are no other
	 * instances of the specific digit horizontally, vertically, or in the local 3x3
	 * box
	 * 
	 * @param sampleMove
	 * @return boolean true if the move is valid
	 */
	public boolean isValid(SudokuMove sampleMove) {
		int[] horizontalArray = new int[9];
		int[] verticalArray = new int[9];
		int[] box = new int[9];

		for (int i = 0; i < 9; i++) {// creating array with all all elements horizontally alligned with move
			verticalArray[i] = grid[i][sampleMove.getColumn()];
		}
		for (int i = 0; i < 9; i++) {// creating array with all all elements vertically alligned with move
			horizontalArray[i] = grid[sampleMove.getRow()][i];
		}
		int f = 0;// creating array with all elements within 3x3 subbox
		for (int i = sampleMove.getRowCorner(); i < sampleMove.getRowCorner() + 3; i++) {
			for (int j = sampleMove.getColumnCorner(); j < sampleMove.getColumnCorner() + 3; j++) {
				box[f] = grid[i][j];
				f++;
			}
		}
		boolean condition1 = SudokuPuzzle.contains(horizontalArray, sampleMove.getDigit());
		boolean condition2 = SudokuPuzzle.contains(verticalArray, sampleMove.getDigit());
		boolean condition3 = SudokuPuzzle.contains(box, sampleMove.getDigit());
		boolean condition4 = sampleMove.getDigit() < 10;

		if (!condition1 & !condition2 & !condition3 & condition4) {// if all four conditions are true, then the move is
																	// valid
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns true if given array contains the specified integer
	 * 
	 * @param array
	 * @param integer
	 * @return boolean
	 */
	private static boolean contains(int[] arr, int digit) {// used in isValid method
		for (int n : arr) {
			if (digit == n) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Getter method to return the digit in a specified cell
	 * 
	 * @param row
	 * @param column
	 * @return digit
	 */
	public int getNumberAt(int row, int column) {
		return grid[row][column];
	}

	/***
	 * This method actually alter's the SudokuMove's grid
	 * 
	 * @param approvedMove
	 */
	public void doMove(SudokuMove approvedMove) {
		grid[approvedMove.getRow()][approvedMove.getColumn()] = approvedMove.getDigit();
	}

	/**
	 * returns an array with two integers. The first is the row, and the second is
	 * the column.
	 * 
	 * @return int[(row integer),(column integer)]
	 */
	public int[] nextOpenSpot() {
		for (int i = 0; i < 9; i++) {// iterating through rows
			for (int j = 0; j < 9; j++) {// iterating through columns
				if (grid[i][j] == 0) {
					int[] rowColumn = new int[] { i, j };
					return rowColumn;
				}
			}
		}
		return null;
	}

	/**
	 * Sets the sigit at a specified location to zero. Used in backtracking.
	 * 
	 * @param row
	 * @param column
	 */
	public void setDigitZero(int row, int column) {
		grid[row][column] = 0;
	}
}
