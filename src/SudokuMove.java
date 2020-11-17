/**
 * Very basic class for a SudokuMove. All atrributes are private and they have getter
 * and setter methods.
 * 
 * @author finnbergquist
 *
 */

public class SudokuMove {
	private int digit;
	private int row;
	private int column;
	/**
	 * Constructor for SudokuMove class with digit, row, and column
	 * @param digit
	 * @param row
	 * @param column
	 */
	public SudokuMove(int digit, int row, int column) {
		this.digit= digit;
		this.row = row;
		this.column = column;
	}
	
	/**
	 * simple getter for row variable
	 * @return row
	 */
	public int getRow() {
		return row;
	}
	/**
	 * simple getter for digit variable
	 * @return digit
	 */
	public int getDigit() {
		return digit;
	}
	/**
	 * simple getter for column variable
	 * @return column
	 */
	public int getColumn() {
		return column;
	}
	/**
	 * simple setter for digit variable
	 * @return void
	 */
	public void setDigit(int dig) {
		digit = dig;
	}
	/**
	 * Adds one to the digit variable as long as it is less than 10.
	 * Used in the SudokuSolver's solve method.
	 * @return
	 */
	public void incrementDigit() {
		if(digit <= 10) {
			digit++;
		} 
	}
	
	/**
	 * returns the column number corresponding to the top left corner of a sudoku board
	 * location's local square(one of the 9 3x3 subsections)
	 * @return column 
	 */
	public int getColumnCorner() {
		if(column/3 < 1) {
			return 0;
		}
		else if(column/3 < 2) {
			return 3;
		}
		else {
			return 6;
		}
	}
	/**
	 * returns the row number corresponding to the top left corner of a sudoku board
	 * location's local square(one of the 9 3x3 subsections)
	 * @return row 
	 */
	public int getRowCorner() {
		if(row/3 < 1) {
			return 0;
		}
		else if(row/3 < 2) {
			return 3;
		}
		else {
			return 6;
		}
	}

}
