package com.example.williammordohay.melodieandroidv44.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.williammordohay.melodieandroidv44.R;

public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        final ImageView ivFsa = (ImageView) findViewById(R.id.logoView);
        final ImageView ivMelodie = (ImageView) findViewById(R.id.melodieView);

        final Animation alphaAnimation = AnimationUtils.loadAnimation(getBaseContext(),R.anim.alpha_effect);
        final Animation animationLeave = AnimationUtils.loadAnimation(getBaseContext(),R.anim.abc_fade_out);
        //final Animation animationTranslate = AnimationUtils.loadAnimation(getBaseContext(),R.anim.translate_effect);
        final Animation animationScale = AnimationUtils.loadAnimation(getBaseContext(),R.anim.scale_effect);

        ivMelodie.setVisibility(ivMelodie.INVISIBLE);
        Toast.makeText(this, R.string.welcoming_message, Toast.LENGTH_SHORT).show();
        ivFsa.startAnimation(alphaAnimation);
        ivMelodie.setVisibility(ivMelodie.VISIBLE);
        ivMelodie.startAnimation(animationScale);



        alphaAnimation.setAnimationListener(new Animation.AnimationListener(){
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ivFsa.startAnimation(animationLeave);
                finish();
                Intent myIntent = new Intent(SplashActivity.this, WebLoginActivity.class);
                startActivity(myIntent);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }


}
