package com.example.libor.cardgame;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.media.MediaPlayer;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
MediaPlayer samp, menu;
    Button HLgame, guessgame, luckyNine,exit, about;
    Animation myAnim;
    public static boolean shouldPlay = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        samp = MediaPlayer.create(MainActivity.this, R.raw.bg_music);
        menu = MediaPlayer.create(MainActivity.this, R.raw.btns);
        samp.setLooping(true);
        samp.start();

        myAnim = AnimationUtils.loadAnimation(this, R.anim.alpha);

        HLgame = (Button)findViewById(R.id.btnHLgame);
        guessgame = (Button)findViewById(R.id.btnGuessGame);
        luckyNine = (Button)findViewById(R.id.btnLucky);

        exit = (Button)findViewById(R.id.btnExit);
        about = (Button)findViewById(R.id.btnAbout);

        luckyNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                luckyNine.startAnimation(myAnim);
                menu.start();
                Intent play3 = new Intent(MainActivity.this, Baccarat.class);
                shouldPlay = true;
                startActivity(play3);
            }
        });

        HLgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HLgame.startAnimation(myAnim);
                menu.start();
                Intent play1 = new Intent(MainActivity.this, HighandLow.class);
                shouldPlay = true;
                startActivity(play1);
            }
        });
        guessgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guessgame.startAnimation(myAnim);
                menu.start();
                Intent play2 = new Intent(MainActivity.this, GuessGame.class);
                shouldPlay = true;
                startActivity(play2);
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exit.startAnimation(myAnim);
                menu.start();
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Are you sure?");
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                menu.start();
                                dialog.dismiss();
                                System.exit(0);
                                MainActivity.this.finish();
                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                menu.start();
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                about.startAnimation(myAnim);
                menu.start();
                Intent about = new Intent(MainActivity.this, About.class);
                shouldPlay = true;
                startActivity(about);
            }
        });
    }
    /**@Override
    public void onPause(){
        super.onPause();
        samp.release();
        finish();
    }**/

    @Override
    public void onStop() {
        super.onStop();
        if (!shouldPlay) { // it won't pause music if shouldPlay is true
            samp.pause();
            samp = null;
        }
    }
}
