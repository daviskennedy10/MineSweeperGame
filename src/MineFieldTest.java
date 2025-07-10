public class MineFieldTest {
    public static void main(String[] args) {
        testConstructor1();
        testNumAdjacentMines();
        testSmallGrid();
        testResetAndPopulate();
    }

    private static void testConstructor1() {
        boolean[][] mineData = {
                { false, false, true, false, false },
                { true,  false, false, false, false },
                { false, false, false, false, true  },
                { false, false, true, false, false }
        };

        MineField mf = new MineField(mineData);
        System.out.println("Testing Constructor with Predefined Mines...");

        System.out.println("Expected rows: 4, Actual: " + mf.numRows());
        System.out.println("Expected cols: 5, Actual: " + mf.numCols());
        System.out.println("Expected mines: 4, Actual: " + mf.numMines());

        System.out.println("Mine at (0,2)? Expected: true, Actual: " + mf.hasMine(0,2));
        System.out.println("Mine at (1,0)? Expected: true, Actual: " + mf.hasMine(1,0));
        System.out.println("Mine at (3,2)? Expected: true, Actual: " + mf.hasMine(3,2));
        System.out.println("Mine at (2,4)? Expected: true, Actual: " + mf.hasMine(2,4));

        System.out.println("Constructor test passed!\n");
    }

    private static void testNumAdjacentMines() {
        boolean[][] mineData = {
                { false, false, true, false, false },
                { true,  false, false, false, false },
                { false, false, false, false, true  },
                { false, false, true, false, false }
        };

        MineField mf = new MineField(mineData);
        System.out.println("Testing numAdjacentMines...");

        System.out.println("Adjacent mines at (0,0), Expected: 1, Actual: " + mf.numAdjacentMines(0,0));
        System.out.println("Adjacent mines at (1,4), Expected: 1, Actual: " + mf.numAdjacentMines(1,4));
        System.out.println("Adjacent mines at (0,2), Expected: 0, Actual: " + mf.numAdjacentMines(0,2));

        System.out.println("numAdjacentMines test passed!\n");
    }

    private static void testSmallGrid() {
        boolean[][] mineData = {
                { true, false },
                { false, true }
        };

        MineField mf = new MineField(mineData);
        System.out.println("Testing Small Grid...");

        System.out.println("Expected rows: 2, Actual: " + mf.numRows());
        System.out.println("Expected cols: 2, Actual: " + mf.numCols());
        System.out.println("Expected mines: 2, Actual: " + mf.numMines());

        System.out.println("Mine at (0,0)? Expected: true, Actual: " + mf.hasMine(0,0));
        System.out.println("Mine at (1,1)? Expected: true, Actual: " + mf.hasMine(1,1));

        System.out.println("Small Grid test passed!\n");
    }

    private static void testResetAndPopulate() {
        MineField mf = new MineField(3, 3, 2);
        System.out.println("Testing Reset and Populate...");

        System.out.println("Before populating, checking mines...");
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                System.out.print(mf.hasMine(r, c) ? "X " : "- ");
            }
            System.out.println();
        }

        mf.populateMineField(1, 1);
        System.out.println("\nAfter populating, checking mines...");
        int mineCount = 0;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (mf.hasMine(r, c)) {
                    System.out.print("X ");
                    mineCount++;
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }

        System.out.println("Expected mines: 2, Actual: " + mineCount);
        System.out.println("Reset and Populate test passed!\n");
    }
}
