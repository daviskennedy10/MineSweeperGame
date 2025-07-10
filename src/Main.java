public class Main {
    public static void main(String[] args) {
        // Create a sample minefield using boolean[][] (true represents a mine, false represents an empty square)
        boolean[][] testMineField = {
                {true, false, true},
                {false, false, false},
                {true, false, true}
        };

        // Create MineField instance using the test boolean array
        MineField mineField = new MineField(testMineField);

        // Create VisibleField instance
        VisibleField visibleField = new VisibleField(mineField);

        // Example 1: Check the initial state of the game
        System.out.println("Initial state:");
        for (int i = 0; i < mineField.numRows(); i++) {
            for (int j = 0; j < mineField.numCols(); j++) {
                System.out.print(visibleField.getStatus(i, j) + " ");
            }
            System.out.println();
        }

        // Example 2: Make a guess
        System.out.println("After making a guess (0,0):");
        visibleField.cycleGuess(0, 0);  // Cycle guess on (0, 0)
        System.out.println("Status at (0,0): " + visibleField.getStatus(0, 0));

        // Example 3: Uncover a square
        System.out.println("After uncovering (1, 1):");
        boolean result = visibleField.uncover(1, 1);  // Uncover a non-mine square
        System.out.println("Uncover result: " + result);

        // Displaying the updated visible field
        for (int i = 0; i < mineField.numRows(); i++) {
            for (int j = 0; j < mineField.numCols(); j++) {
                System.out.print(visibleField.getStatus(i, j) + " ");
            }
            System.out.println();
        }

        // Example 4: Reset the game
        System.out.println("After resetting the game:");
        visibleField.resetGameDisplay();  // Reset the visible field
        for (int i = 0; i < mineField.numRows(); i++) {
            for (int j = 0; j < mineField.numCols(); j++) {
                System.out.print(visibleField.getStatus(i, j) + " ");
            }
            System.out.println();
        }

        // Example 5: Check if the game is over
        System.out.println("Is the game over? " + visibleField.isGameOver());
    }
}
