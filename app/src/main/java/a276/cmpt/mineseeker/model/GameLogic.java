package a276.cmpt.mineseeker.model;

import java.util.Random;

//model class containing information of game currently being played
public class GameLogic {

    private int rowDimension;
    private int colDimension;
    private int scanCount;
    private int minesFound;
    private int minesRemaining;
    //contains values displayed for each button for number of hidden mines in same row and same column
    private int[][] mineCounts;
    //contains boolean value for each button to tell game if mine is hidden there
    private boolean[][] mineLocations;
    //contains boolean value for each button to tell game if button was scanned
    private boolean[][] ifButtonScanned;


    //constructor initializing settings and stats of game
    //arguments rows, cols and mines must be greater than 0, otherwise throws IllegalArgumentException
    public GameLogic(int rows, int cols, int mines) {
        if (rows <= 0 || cols <= 0 || mines <= 0) {
            throw new IllegalArgumentException();
        }
        rowDimension = rows;
        colDimension = cols;
        scanCount = 0;
        minesFound = 0;
        minesRemaining = mines;
        mineCounts = new int[rows][cols];
        mineLocations = new boolean[rows][cols];
        ifButtonScanned = new boolean[rows][cols];
        addMines();
    }

    //places mines by changing randomly-selected boolean values in mineLocations to true
    //number of mines based on minesRemaining data member
    private void addMines() {
        int i = minesRemaining;
        Random r = new Random();
        int temp_row;
        int temp_col;
        while (i > 0) {
            temp_row = r.nextInt(rowDimension);
            temp_col = r.nextInt(colDimension);
            if (!mineLocations[temp_row][temp_col]) {
                mineLocations[temp_row][temp_col] = true;
                addMineCounts(temp_row, temp_col);
                i--;
            }
        }
    }

    // after a mine has been placed, increment mineCounts values in same row and column as placed hidden mine
    private void addMineCounts(int row, int col){
        for (int i = 0; i < colDimension; i++) {
            mineCounts[row][i]++;
        }
        for (int i = 0; i < rowDimension; i++) {
            mineCounts[i][col]++;
        }
    }

    //getter for rowDimension
    public int getRowDimension() {
        return rowDimension;
    }

    //getter for colDimension
    public int getColDimension() {
        return colDimension;
    }

    //getter for scanCount
    public int getScanCount() {
        return scanCount;
    }

    //increments value of number of scans performed
    public void incrementScanCount() {
        scanCount++;
    }

    //getter for minesFound
    public int getMinesFound() {
        return minesFound;
    }

    //getter for minesRemaining
    public int getMinesRemaining() {
        return minesRemaining;
    }

    //returns value of number of mines hidden on same row and column as button
    public int getMineCount(int row, int col) {
        return mineCounts[row][col];
    }

    //checks if button was scanned by accessing ifButtonScanned[row][col]
    //returns true if it was, otherwise returns false
    public boolean getIfButtonScanned(int row, int col) {
        return ifButtonScanned[row][col];
    }

    //checks for mine
    //if mine found, mineLocation[row][col] changes to false, then mineCounts numbers with same row and col value are decremented by 1
    //minesFound and minesRemaining are updated too
    //arguments row and col must be less than rowDimension and colDimension respectively, otherwise throws IllegalArgumentException
    //return true if mine is hidden at button, otherwise returns false
    public boolean checkForMine(int row, int col) {
        if (row >= rowDimension || col >= colDimension) {
            throw new IllegalArgumentException();
        }
        if (mineLocations[row][col]) {
            mineLocations[row][col] = false;
            subtractMineCounts(row,col);
            minesFound++;
            minesRemaining--;
            return true;
        }
        else {
            return false;
        }
    }

    //called when mine is found; subtracts mineCounts for each value sharing same row and column by 1
    private void subtractMineCounts(int row, int col) {
        for (int i = 0; i < colDimension; i++) {
            mineCounts[row][i]--;
        }
        for (int i = 0; i < rowDimension; i++) {
            mineCounts[i][col]--;
        }
    }

    //marks a button as pressed by changing ifButtonScanned[row][col] to true
    public void markButtonScanned(int row, int col) {
        if (!ifButtonScanned[row][col]) {
            ifButtonScanned[row][col] = true;
        }
    }

}
