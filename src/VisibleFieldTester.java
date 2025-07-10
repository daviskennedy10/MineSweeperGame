public class VisibleFieldTester {
    public static void main(String[] args) {
        // Create a fixed 3x3 MineField for testing
        boolean[][] mineData = {
                {false, false, false},
                {true, false, false},
                {false, false, true}
        };

        // Initialize MineField and VisibleField
        MineField mf = new MineField(mineData);
        VisibleField vf = new VisibleField(mf);

        System.out.println("Initial VisibleField (All Covered):");
        printVisibleField(vf);

        // Test uncovering a safe square (0,0)
        vf.uncover(0, 0);
        System.out.println("\nAfter uncovering (0,0):");
        printVisibleField(vf);

        // Test uncovering a mine (1,0)
        boolean survived = vf.uncover(1, 0);
        System.out.println("\nAfter uncovering mine (1,0):");
        printVisibleField(vf);
        System.out.println("Game Over Status: " + vf.isGameOver() + " (Expected: true)");
        System.out.println("Uncovering mine result: " + survived + " (Expected: false)");

        // Reset the game
        vf.resetGameDisplay();
        System.out.println("\nAfter Reset:");
        printVisibleField(vf);

        // Test uncovering a large empty area (0,2) - should recursively uncover more squares
        vf.uncover(0, 2);
        System.out.println("\nAfter uncovering (0,2) - Testing recursive reveal:");
        printVisibleField(vf);

        // Check if the game correctly identifies a win
        boolean win = testWinCondition(vf);
        System.out.println("\nWin Condition Met? " + win + " (Expected: true)");
    }

    /**
     * Helper method to print the visible field status.
     * Uses symbols for different states:
     * - 'C' = Covered
     * - 'M' = Mine Guess
     * - '?' = Question Mark
     * - 'X' = Exploded Mine
     * - 'I' = Incorrect Mine Guess
     * - Numbers = Adjacent Mines Count
     */
    public static void printVisibleField(VisibleField vf) {
        int rows = vf.getMineField().numRows();
        int cols = vf.getMineField().numCols();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int status = vf.getStatus(r, c);

                if (status == VisibleField.COVERED) {
                    System.out.print("C ");  // Covered
                } else if (status == VisibleField.MINE_GUESS) {
                    System.out.print("M ");  // Mine Guess
                } else if (status == VisibleField.QUESTION) {
                    System.out.print("? ");  // Question mark state
                } else if (status == VisibleField.EXPLODED_MINE) {
                    System.out.print("X! ");  // Exploded mine
                } else if (status == VisibleField.INCORRECT_GUESS) {
                    System.out.print("I ");  // Incorrect mine guess
                } else if (status == VisibleField.MINE) {
                    System.out.print("X ");  // Mine
                } else {
                    System.out.print(status + " "); // Number of adjacent mines
                }
            }
            System.out.println();
        }
    }

    /**
     * Simulates uncovering all safe squares to test the winning condition.
     */
    public static boolean testWinCondition(VisibleField vf) {
        int rows = vf.getMineField().numRows();
        int cols = vf.getMineField().numCols();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (!vf.getMineField().hasMine(r, c)) {
                    vf.uncover(r, c); // Uncover all safe squares
                }
            }
        }
        return vf.isGameOver(); // Game should end in a win
    }
}
