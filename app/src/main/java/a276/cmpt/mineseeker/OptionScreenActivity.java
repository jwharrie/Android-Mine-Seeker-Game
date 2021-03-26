package a276.cmpt.mineseeker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import a276.cmpt.mineseeker.model.GameOptions;

//control object for options screen
public class OptionScreenActivity extends AppCompatActivity {

    private GameOptions options;
    private Button resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_screen);

        //contains game settings
        options = GameOptions.getInstance();

        options.setUpSavedOptions(this);

        createBoardSizeRadioButtons();

        createMinesRadioButtons();

        setUpResetButton();

    }

    //allows user to open options menu from main menu
    public static Intent makeIntent(Context context) {
        return new Intent(context, OptionScreenActivity.class);
    }

    //creates and sets up radio buttons for board size on options screen
    private void createBoardSizeRadioButtons(){
        RadioGroup group = (RadioGroup) findViewById(R.id.radio_group_board_size);

        int[] sizeNumbers = getResources().getIntArray(R.array.board_size_radio);

        for (int i = 0; i < sizeNumbers.length; i = i + 2) {
            final int row = sizeNumbers[i];
            final int col = sizeNumbers[i+1];

            RadioButton button = new RadioButton(this);
            button.setText(row + " x " + col + "  ");
            button.setBackgroundColor(Color.WHITE);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    options.setRows(row);
                    options.setColumns(col);
                    options.saveRow(OptionScreenActivity.this);
                    options.saveColumn(OptionScreenActivity.this);
                }
            });

            group.addView(button);

            if (row == options.getRows() && col == options.getColumns()) {
                button.setChecked(true);
            }
        }
    }

    //creates and sets up radio buttons for number of mines on options screen
    private void createMinesRadioButtons(){
        RadioGroup group = (RadioGroup) findViewById(R.id.radio_group_mines);

        int[] mineNumbers = getResources().getIntArray(R.array.number_mines_radio);

        for (int i = 0; i < mineNumbers.length; i++) {
            final int mine = mineNumbers[i];

            RadioButton button = new RadioButton(this);
            button.setText("" + mine + "  ");
            button.setBackgroundColor(Color.WHITE);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    options.setMines(mine);
                    options.saveNumberOfMines(OptionScreenActivity.this);
                }
            });

            group.addView(button);

            if (mine == options.getMines()) {
                button.setChecked(true);
            }
        }
    }

    //set up reset button to allow user to reset number times a game has started and high scores for each configuration
    public void setUpResetButton() {
        resetButton = (Button) findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                options.setTimesStarted(0);
                options.saveTimesStarted(OptionScreenActivity.this);
            }
        });
    }

}
