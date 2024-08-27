# Sudoku Solver

This project is a Sudoku solver implemented in Java. It reads an unsolved Sudoku puzzle from a file, optionally reads a solution from another file, solves the puzzle, and verifies the solution if provided. It also prints the number of backtracks used, which generally denotes the difficulty of the puzzle.

## Features

- **Read Unsolved Puzzle**: Reads an unsolved Sudoku puzzle from a file.
- **Optional Solution Verification**: Optionally reads a solution from another file and verifies the solved puzzle against it.
- **Solve Puzzle**: Solves the Sudoku puzzle.
- **Print Backtracks**: Prints the number of backtracks used during the solving process.

## Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/finnbergquist/Sudoku-Solver
    ```

2. Ensure you have Java installed on your system.

## Usage

1. Compile the Java files:
    ```sh
    javac src/*.java
    ```

2. Run the `SudokuTest` class:
    ```sh
    java -cp src SudokuTest
    ```

3. Follow the prompts to enter the filename of the unsolved puzzle and optionally the filename of the solution.

## Example

```sh
Enter filename of puzzle: 
unsolved_puzzle.txt
Enter filename of solution (optional): 
solved_puzzle.txt
