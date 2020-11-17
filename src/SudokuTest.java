import java.util.Scanner;

/**
 * This is the driver class. In it's main method, it will ask for an unsolved puzzle and 
 * a solution(optional). It will then evaluate to see if the solved puzzle is equal 
 * to the solution, if given. Either way, it will print out the solved solution and print
 * out how many backtracks were used(generally denoting how difficult the puzzle was).
 * 
 * @author finnbergquist
 *
 */

public class SudokuTest {
	public static void main(String[] args) {
		// First, the main method will get the file names
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter filename of puzzle: ");
		String unsolvedPuzzleName = scan.nextLine();
		System.out.println("Enter filename of solution (optional): ");
		String optionalPuzzleSolution = scan.nextLine();

		SudokuPuzzle puzzle1 = new SudokuPuzzle(unsolvedPuzzleName);

		if (optionalPuzzleSolution.length() > 0) { // If there was a solution puzzle entered
			SudokuPuzzle puzzle2 = new SudokuPuzzle(optionalPuzzleSolution);

			SudokuSolver puzzle1Solver = new SudokuSolver(puzzle1);

			// unsolved puzzle initially
			System.out.println("Starting Puzzle");
			System.out.println(puzzle1.toString());

			// solves puzzle
			puzzle1Solver.solve();

			System.out.println("Solved Puzzle: ");
			System.out.println(puzzle1.toString());

			if (puzzle1.Equals(puzzle2)) {
				System.out.println(" The solution is correct!!! ");
			}

		} else {// if no solution file was entered
			SudokuSolver puzzle1Solver = new SudokuSolver(puzzle1);

			// unsolved puzzle initially
			System.out.println("Starting Puzzle");
			System.out.println(puzzle1.toString());

			// solves puzzle
			puzzle1Solver.solve();

			System.out.println("Solved Puzzle: ");
			System.out.println(puzzle1.toString());
		}
	}
}
