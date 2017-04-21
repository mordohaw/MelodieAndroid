package com.example.williammordohay.melodieandroidv44;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final ImageView iv = (ImageView) findViewById(R.id.logoView);
        final Animation animation = AnimationUtils.loadAnimation(getBaseContext(),R.anim.alpha_effect);
        final Animation animationLeave = AnimationUtils.loadAnimation(getBaseContext(),R.anim.abc_fade_out);

        iv.startAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener(){
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                iv.startAnimation(animationLeave);
                finish();
                Intent myIntent = new Intent(SplashActivity.this, Menu.class);
                startActivity(myIntent);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
