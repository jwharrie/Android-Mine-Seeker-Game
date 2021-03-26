package a276.cmpt.mineseeker.model;

import android.content.Context;
import android.content.SharedPreferences;

import a276.cmpt.mineseeker.R;

//model Singleton class containing game settings
//can be changed on OptionScreenActivity
public class GameOptions {

    private int rows;
    private int columns;
    private int mines;
    private int timesStarted;

    //needed to make class a Singleton
    private static GameOptions instance;
    //prevent others from instantiating
    private GameOptions() {

    }
    //makes GameOptions a Singleton class
    public static GameOptions getInstance() {
        if (instance == null) {
            instance = new GameOptions();
        }
        return instance;
    }

    //grabs saved row, column and mine number settings to set in GameOptions instance
    public void setUpSavedOptions(Context context) {
        setRows(getSavedRow(context));
        setColumns(getSavedColumn(context));
        setMines(getSavedNumberOfMines(context));
        setTimesStarted(getSavedTimesStarted(context));
    }

    //grabs saved row value from system
    private int getSavedRow(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);
        int defaultNumber = context.getResources().getInteger(R.integer.default_row_radio);
        return prefs.getInt("row", defaultNumber);
    }

    //grabs saved column value from system
    private int getSavedColumn(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);
        int defaultNumber = context.getResources().getInteger(R.integer.default_column_radio);
        return prefs.getInt("col", defaultNumber);
    }

    //grabs saved value of number of mines from system
    private int getSavedNumberOfMines(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);
        int defaultNumber = context.getResources().getInteger(R.integer.default_number_mines_radio);
        return prefs.getInt("mines", defaultNumber);
    }

    //grabs saved value of number of times a game was started
    private int getSavedTimesStarted(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);
        return prefs.getInt("timesPlayed", 0);
    }

    //saves row value to system
    public void saveRow(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("row", rows);
        editor.apply();
    }

    //saves column value to system
    public void saveColumn(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("col", columns);
        editor.apply();
    }

    //saves value of number of mines to system
    public void saveNumberOfMines(Context context){
        SharedPreferences prefs = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);;
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("mines", mines);
        editor.apply();
    }

    //saves value of number of times a game was started
    public void saveTimesStarted(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("timesPlayed", timesStarted);
        editor.apply();
    }

    //getter for number of rows
    public int getRows() {
        return rows;
    }

    //setter for number of rows
    public void setRows(int rows) {
        this.rows = rows;
    }

    //getter for number of columns
    public int getColumns() {
        return columns;
    }

    //setter for number of columns
    public void setColumns(int columns) {
        this.columns = columns;
    }

    //getter for number of mines
    public int getMines() {
        return mines;
    }

    //setter for number of mines
    public void setMines(int mines) {
        this.mines = mines;
    }

    //getter for number of times a game has started
    public int getTimesStarted() {
        return timesStarted;
    }

    //setter for number of times a game has started
    public void setTimesStarted(int times) {this.timesStarted = times;}

    //increment number of times a game has started by 1
    public void incrementTimesStarted() {
        this.timesStarted++;
    }

}