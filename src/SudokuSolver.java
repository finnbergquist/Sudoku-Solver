import java.util.LinkedList;

/**
 * The SudokuSolver class has only one method and one attribute. It stores a
 * puzzle when it is constructed, and when the solve method is applied it solves
 * the board(see solve() method.
 * 
 * @author finnbergquist
 *
 */

public class SudokuSolver {

	private static SudokuPuzzle unsolvedPuzzle;

	public SudokuSolver(SudokuPuzzle unsolvedPuzzle) { // constructor
		this.unsolvedPuzzle = unsolvedPuzzle;
	}

	/**
	 * This method will solve the sudoku puzzle. First, it will look to see if there
	 * is an open cell(labeled step 1). Then it will create a new move at that
	 * spot(step2). If the move is valid, the move will be done by a SudokuPuzzle
	 * method called doMove()(step3). If the move isn't valid, then it will
	 * backtrack(step4) until it can find a previous move that is still valid at a
	 * higher digit, assigning those cells to 0 the whole time(step5). Then it will
	 * go back and try it again using the retryingMove() method(step 6). Also, every
	 * time a move is completed, it is stored into the LinkedList moveStack, and
	 * every time it is set back to zero, it is popped off. When the method retries
	 * a previous move, it attempts to modify the top SudokuMove on the stack to see
	 * if that is valid(step 7).
	 * 
	 * @return void
	 */
	public void solve() {
		LinkedList<SudokuMove> moveStack = new LinkedList<SudokuMove>();
		int pushesMade = 0;
		int popsMade = 0;
		int backtracksUsed = 0;

		while (!(unsolvedPuzzle.nextOpenSpot() == null)) {// step1
			SudokuMove nextMove = SudokuSolver.nextMove(unsolvedPuzzle.nextOpenSpot()[0], // step2
					unsolvedPuzzle.nextOpenSpot()[1]);
			if (nextMove == null) {// step 4
				backtracksUsed ++;
	
				while ((retryingMove(moveStack.peek()) == null)) {// step 7. evaluates if retrying a move is possible
					SudokuMove lastMove = moveStack.pop();
					popsMade ++;
					unsolvedPuzzle.setDigitZero(lastMove.getRow(), lastMove.getColumn());// step5
				}
				
				SudokuMove backtrackedMove = retryingMove(moveStack.peek());// step6
				moveStack.pop();
				popsMade ++;
				unsolvedPuzzle.doMove(backtrackedMove);
				moveStack.push(backtrackedMove);
				pushesMade ++;
			} else {
				unsolvedPuzzle.doMove(nextMove); // step3		
				moveStack.push(nextMove);
				pushesMade ++;
			}
		}
		System.out.println("The puzzle was solved. Number of Pushes: " + pushesMade + "  Number of Pops: " + popsMade);
		System.out.println("Number of backtracks: " + backtracksUsed + "  \n ");
	}

	/**
	 * This method will create and return a SudokuMove with the lowest possible
	 * digit that will still be valid in the specified location. If there are no
	 * valid moves, then it will return null!
	 * 
	 * @param row
	 * @param column
	 * @return a valid SudokuMove or null
	 */
	private static SudokuMove nextMove(int row, int column) {
		SudokuMove nextMove = new SudokuMove(1, row, column);
		while (!unsolvedPuzzle.isValid(nextMove)) {
			nextMove.incrementDigit();
			if (nextMove.getDigit() > 9) {				
				return null;//not valid move
			}
		}
		return nextMove;
	}

	/**
	 * Very similair to nextMove method; however, it will try to update a move with
	 * a higher digit that will also be a valid move. Similairly to nextMove(), if
	 * there are no higher digits that will result in a valid move, null will be
	 * returned.
	 * 
	 * @param lastMove
	 * @return
	 */
	private static SudokuMove retryingMove(SudokuMove lastMove) {
		SudokuMove alteredMove = new SudokuMove(lastMove.getDigit(), lastMove.getRow(), lastMove.getColumn());
		while (!unsolvedPuzzle.isValid(alteredMove)) {
			alteredMove.incrementDigit();//testing to see if higher digits will result in a  valid move
			if (alteredMove.getDigit() > 9) {
				return null;//not a valid move
			}
		}
		return alteredMove;
	}

}
