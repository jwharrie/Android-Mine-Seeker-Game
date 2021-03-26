package a276.cmpt.mineseeker;

import android.media.MediaPlayer;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import a276.cmpt.mineseeker.model.GameLogic;
import a276.cmpt.mineseeker.model.GameOptions;

//object class for game screen
public class GameScreenActivity extends AppCompatActivity {

    private GameOptions options;
    private GameLogic game;
    private String totalMines;
    private Button buttonTable[][];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        //contains game settings
        options = GameOptions.getInstance();
        // update timesStarted date member
        options.incrementTimesStarted();
        options.saveTimesStarted(this);

        setUpGamesPlayedText();

        //makes GameLogic instance when game screen opens
        game = new GameLogic(options.getRows(),options.getColumns(),options.getMines());
        //get total number of mines to find in game
        totalMines = Integer.toString(game.getMinesRemaining());
        //set up button table
        buttonTable = new Button[game.getRowDimension()][game.getColDimension()];

        updateMineCount();
        updateScanCount();
        makeTable();

    }

    //allows user to open game screen from main menu
    public static Intent makeIntent(Context context) {
        return new Intent(context, GameScreenActivity.class);
    }

    //set up text showing count on number games started
    public void setUpGamesPlayedText() {
        TextView text = (TextView) findViewById(R.id.times_played_text);
        text.setText("Number of games started: " + Integer.toString(options.getTimesStarted()));
    }

    //updates text showing mines found and total mines in game
    public void updateMineCount() {
        TextView mineCounterText = (TextView) findViewById(R.id.mine_counter);
        mineCounterText.setText("Found " + Integer.toString(game.getMinesFound()) + " of " + totalMines + " Files");
    }

    //updates text showing number of scans performed
    public void updateScanCount() {
        TextView scanCounterText = (TextView) findViewById(R.id.scan_counter);
        scanCounterText.setText("# Hacker-scans used: " + Integer.toString(game.getScanCount()) );
    }

    //makes button table based on values from GameOptions instance and programs actions on button click
    public void makeTable() {
        TableLayout table = (TableLayout) findViewById(R.id.table_buttons);
        for (int i = 0; i < game.getRowDimension(); i++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));
            table.addView(tableRow);
            for (int j = 0; j < game.getColDimension(); j++) {
                Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));
                buttonTable[i][j] = button;
                whenButtonClicked(button,i,j);
                tableRow.addView(button);
            }
        }
    }

    //performs actions when button pressed
    //when mine found, mine count is updated and numbers on buttons decrease by 1
    //when mine not found, add number to button and update scan count
    //always updates text on scanned buttons
    //when all mines have been found, opens alert dialog to congratulate player then closes game screen to return to main menu
    public void whenButtonClicked(final Button button, final int row, final int col) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MediaPlayer mp = MediaPlayer.create(GameScreenActivity.this,R.raw.scan_sound_effect);
                if (game.checkForMine(row,col)) {

                    mp.start();
                    lockButtonSize();

                    int newWidth = button.getWidth();
                    int newHeight = button.getHeight();
                    Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.top_secret_file_icon);
                    Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
                    Resources resource = getResources();
                    button.setBackground(new BitmapDrawable(resource, scaledBitmap));

                    updateMineCount();
                }
                else if (!game.getIfButtonScanned(row,col)){
                    mp.start();
                    startPulseAnimations(row,col);
                    game.markButtonScanned(row,col);
                    game.incrementScanCount();
                    updateScanCount();
                }
                updateButtonText();
                if (game.getMinesRemaining() == 0) {
                    FragmentManager manager = getSupportFragmentManager();
                    MessageFragment dialog = new MessageFragment();
                    dialog.show(manager, "end game message");

                }
            }
        });
    }

    //prevents buttons from changing size when mine is found and image on button appears
    public void lockButtonSize() {
        for (int i = 0; i < game.getRowDimension(); i++) {
            for (int j = 0; j < game.getColDimension(); j++) {
                Button button = buttonTable[i][j];

                int width = button.getWidth();
                button.setMinWidth(width);
                button.setMaxWidth(width);
            }
        }
    }

    //updates numbers on buttons if they have been scanned
    public void updateButtonText() {
        for (int i = 0; i < game.getRowDimension(); i++) {
            for (int j = 0; j < game.getColDimension(); j++) {
                if (game.getIfButtonScanned(i,j)) {
                    buttonTable[i][j].setText(Integer.toString(game.getMineCount(i,j)));
                }
            }
        }
    }

    //pulsing animation for buttons along same row and column of button scanned
    public void startPulseAnimations(int row, int col) {
        Animation animation = AnimationUtils.loadAnimation(GameScreenActivity.this,R.anim.pulse);
        for (int i = 0; i < game.getColDimension(); i++) {
            buttonTable[row][i].startAnimation(animation);
        }
        for (int i = 0; i < game.getRowDimension(); i++) {
            buttonTable[i][col].startAnimation(animation);
        }
    }
}