package a276.cmpt.mineseeker;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import a276.cmpt.mineseeker.model.GameOptions;

//control object for main menu screen
public class MainMenuActivity extends AppCompatActivity {

    private Button gameButton;
    private Button optionsButton;
    private Button helpButton;

    private GameOptions options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //contains game settings
        options = GameOptions.getInstance();
        options.setUpSavedOptions(this);

        setUpGameButton();

        setUpOptionsButton();

        setUpHelpButton();

    }

    //allows welcome screen to open menu screen
    public static Intent makeIntent(Context context) {
        return new Intent(context, MainMenuActivity.class);
    }

    //sets up button to open and start a new game
    private void setUpGameButton() {
        gameButton = (Button) findViewById(R.id.game_button);
        gameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = GameScreenActivity.makeIntent(MainMenuActivity.this);
                startActivity(intent);
            }
        });
    }

    //sets up button to open options screen
    private void setUpOptionsButton() {
        optionsButton = (Button) findViewById(R.id.options_button);
        optionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = OptionScreenActivity.makeIntent(MainMenuActivity.this);
                startActivity(intent);
            }
        });
    }

    //sets up button to open help screen
    private void setUpHelpButton() {
        helpButton = (Button) findViewById(R.id.help_button);
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = HelpScreenActivity.makeIntent(MainMenuActivity.this);
                startActivity(intent);
            }
        });
    }

}
