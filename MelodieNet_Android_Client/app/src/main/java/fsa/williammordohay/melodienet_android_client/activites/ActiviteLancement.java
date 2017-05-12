package fsa.williammordohay.melodienet_android_client.activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import fsa.williammordohay.melodienet_android_client.R;

public class ActiviteLancement extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_lancement);
        //Cache la barre d'en-tete
        getSupportActionBar().hide();

        //On récupère les deux images dans le xml
        final ImageView ivFsa = (ImageView) findViewById(R.id.logoView);
        final ImageView ivMelodie = (ImageView) findViewById(R.id.melodieView);

        //On affiche une petite fenetre de dialogue pour souhaiter la bienvenue à l'utilisateur
        Toast.makeText(this, R.string.message_bienvenue, Toast.LENGTH_SHORT).show();

        //On crée les animations que nous allons utiliser
        final Animation animApparition = AnimationUtils.loadAnimation(getBaseContext(),R.anim.effet_apparition);
        final Animation animZoom = AnimationUtils.loadAnimation(getBaseContext(),R.anim.effet_zoom);

        genereAnim(animApparition,ivFsa);
        genereAnim(animZoom,ivMelodie);

        quitteAnim(animApparition, ivFsa);
    }
    public void genereAnim(Animation animation, final ImageView image)
    {
        //On applique les animations aux images
        image.startAnimation(animation);
    }

    public void quitteAnim(final Animation animFin, final ImageView imgFin)
    {
        final Animation animQuitter = AnimationUtils.loadAnimation(getBaseContext(),R.anim.abc_fade_out);
        animFin.setAnimationListener(new Animation.AnimationListener(){

            @Override
            public void onAnimationStart(Animation animation)
            {

            }

            @Override
            // à la fin de l'animation, on  lance l'animation "animQuitter" et on va à l'activité suivante
            public void onAnimationEnd(Animation animation)
            {
                imgFin.startAnimation(animQuitter);
                finish();
                Intent demarrage = new Intent(ActiviteLancement.this, ActiviteMenu.class);
                startActivity(demarrage);
            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {

            }
        });
    }
}
