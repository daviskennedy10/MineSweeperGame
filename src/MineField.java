import java.util.Random;
// Name: Chidubem Ezenna
// February 2025


/**
 MineField
 Class with locations of mines for a minesweeper game.
 This class is mutable, because we sometimes need to change it once it's created.
 Mutators: populateMineField, resetEmpty
 Includes convenience method to tell the number of mines adjacent to a location.
 */
public class MineField {

    // <put instance variables here>
    private boolean[][] minebox;
    private int mines;
    private boolean isfixed;



    /**
     Create a minefield with same dimensions as the given array, and populate it with the mines in
     the array such that if mineData[row][col] is true, then hasMine(row,col) will be true and vice
     versa.  numMines() for this minefield will corresponds to the number of 'true' values in
     mineData.
     @param mineData  the data for the mines; must have at least one row and one col,
     and must be rectangular (i.e., every row is the same length)
     */
    public MineField(boolean[][] mineData) {
        this.minebox = new boolean[mineData.length][mineData[0].length];
        mines = 0;
        isfixed = true;

        // Copy the mineData into minebox and count the number of mines
        for (int i = 0; i < mineData.length; i++) {
            for (int j = 0; j < mineData[0].length; j++) {
                this.minebox[i][j] = mineData[i][j];
                if (mineData[i][j]) {
                    this.mines++;
                }
            }
        }
    }


    /**
     Create an empty minefield (i.e. no mines anywhere), that may later have numMines mines (once
     populateMineField is called on this object).  Until populateMineField is called on such a
     MineField, numMines() will not correspond to the number of mines currently in the MineField.
     @param numRows  number of rows this minefield will have, must be positive
     @param numCols  number of columns this minefield will have, must be positive
     @param numMines   number of mines this minefield will have,  once we populate it.
     PRE: numRows > 0 and numCols > 0 and 0 <= numMines < (1/3 of total number of field locations).
     */
    public MineField(int numRows, int numCols, int numMines) {
        this.minebox = new boolean[numRows][numCols];
        mines = numMines;
        isfixed = false;
    }


    /**
     Removes any current mines on the minefield, and puts numMines() mines in random locations on
     the minefield, ensuring that no mine is placed at (row, col).
     @param row the row of the location to avoid placing a mine
     @param col the column of the location to avoid placing a mine
     PRE: inRange(row, col) and numMines() < (1/3 * numRows() * numCols())
     */
    public void populateMineField(int row, int col) {
        if(isfixed) {
            return;
        }

        resetEmpty();


        setupMines(row, col, mines);

    }

    /**
     Recursively places mines on the minefield.
     @param avoidRow the row to avoid placing a mine
     @param avoidCol the column to avoid placing a mine
     @param minesRemaining the number of mines left to place
     */
    private void setupMines(int avoidRow, int avoidCol, int minesRemaining) {
        if (minesRemaining == 0) {
            return;  // Base case: all mines have been placed
        }

        Random rand = new Random();
        int randomRow = rand.nextInt(minebox.length);       // Random row index
        int randomCol = rand.nextInt(minebox[0].length);    // Random column index

        // Ensure the cell is not already a mine and is not the avoided location (avoidRow, avoidCol)
        if (!minebox[randomRow][randomCol] && !(randomRow == avoidRow && randomCol == avoidCol)) {
            minebox[randomRow][randomCol] = true;  // Place a mine

            setupMines(avoidRow, avoidCol, minesRemaining - 1);  // Recurse with one less mine to place
        } else {
            // Try again if the cell is invalid (already a mine or the avoided location)
            setupMines(avoidRow, avoidCol, minesRemaining);
        }
    }

    /**
     Reset the minefield to all empty squares.  This does not affect numMines(), numRows() or
     numCols().  Thus, after this call, the actual number of mines in the minefield does not match
     numMines().
     Note: This is the state a minefield created with the three-arg constructor is in at the
     beginning of a game.
     */
    public void resetEmpty() {
        for(int i = 0; i < minebox.length; i++){
            for(int j = 0; j < minebox[0].length; j++){
                minebox[i][j] = false;
            }
        }

    }


    /**
     Returns the number of mines adjacent to the specified location (not counting a possible
     mine at (row, col) itself).
     Diagonals are also considered adjacent, so the return value will be in the range [0,8]
     @param row  row of the location to check
     @param col  column of the location to check
     @return  the number of mines adjacent to the square at (row, col)
     PRE: inRange(row, col)
     */
    public int numAdjacentMines(int row, int col) {
        if (!inRange(row, col)) {
            throw new IllegalArgumentException("Location (" + row + ", " + col + ") is out of bounds.");
        }
        int count = 0;

        // Loop over all 8 possible adjacent cells
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                // Skip the cell itself and check if the adjacent cell is in bounds
                if ((i != row || j != col) && inRange(i, j)) {
                    if (minebox[i][j]) {  // Check if there is a mine
                        count++;
                    }
                }
            }
        }

        return count;
    }


    /**
     Returns true iff (row,col) is a valid field location.  Row numbers and column numbers
     start from 0.
     @param row  row of the location to consider
     @param col  column of the location to consider
     @return whether (row, col) is a valid field location
     */
    public boolean inRange(int row, int col) {
        return ((row >= 0 && row < minebox.length) && (col >= 0 && col < minebox[0].length));       // DUMMY CODE so skeleton compiles
    }


    /**
     Returns the number of rows in the field.
     @return number of rows in the field
     */
    public int numRows() {
        return minebox.length;       // DUMMY CODE so skeleton compiles
    }


    /**
     Returns the number of columns in the field.
     @return number of columns in the field
     */
    public int numCols() {
        return minebox[0].length;       // DUMMY CODE so skeleton compiles
    }


    /**
     Returns whether there is a mine in this square
     @param row  row of the location to check
     @param col  column of the location to check
     @return whether there is a mine in this square
     PRE: inRange(row, col)
     */
    public boolean hasMine(int row, int col) {
        if (!inRange(row, col)) {
            throw new IllegalArgumentException("Invalid coordinates: (" + row + ", " + col + ")");
        }
        return minebox[row][col];
    }


    /**
     Returns the number of mines you can have in this minefield.  For mines created with the 3-arg
     constructor, some of the time this value does not match the actual number of mines currently
     on the field.  See doc for that constructor, resetEmpty, and populateMineField for more
     details.
     @return number of mines
     */
    public int numMines() {
        return mines;       // DUMMY CODE so skeleton compiles
    }



    // <put private methods here>


}


