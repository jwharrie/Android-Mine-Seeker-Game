package a276.cmpt.mineseeker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

//control object for welcome screen
public class WelcomeActivity extends AppCompatActivity {

    private Button skipButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        setUpSkipButton();

        animateJet();

    }

    //making skip button that opens main menu, then closes welcome screen
    private void setUpSkipButton() {
        skipButton = (Button) findViewById(R.id.skip_button);
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = MainMenuActivity.makeIntent(WelcomeActivity.this);
                startActivity(intent);
                finish();
            }
        });
    }

    private void animateJet() {
        final ImageView image = findViewById(R.id.jet_welcome_image);
        Animation rotate = AnimationUtils.loadAnimation(this,R.anim.rotate_full_circle);
        final Animation grow = AnimationUtils.loadAnimation(this,R.anim.grow_jet);
        rotate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                image.startAnimation(grow);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        image.startAnimation(rotate);
    }
}
