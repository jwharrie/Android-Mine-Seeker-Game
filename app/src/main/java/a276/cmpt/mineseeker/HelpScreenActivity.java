package a276.cmpt.mineseeker;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

//control object for help screen
public class HelpScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_screen);

        setUpHyperlinkText();
    }

    //allows user to open help screen from main menu
    public static Intent makeIntent(Context context) {
        return new Intent(context, HelpScreenActivity.class);
    }

    //allows hyperlink text to work
    public void setUpHyperlinkText() {

        TextView authorText = (TextView) findViewById(R.id.author_info);
        authorText.setMovementMethod(LinkMovementMethod.getInstance());

        TextView citationText = (TextView) findViewById(R.id.citations);
        citationText.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
