package com.example.libor.cardgame;

import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.*;

public class HighandLow extends AppCompatActivity {
    Random ran = new Random();
    int EN,PL, comp, player, pic, flagCard, comp_score, player_score;
    Animation myAnim;
    Button btnHigh, btnLow, btnEnd, btnPlay;
    TextView enemy_score, user_score;
    ImageButton btnrestart;
    ImageView icon, enemy, user;
    AlertDialog alertDialog;
    Intent home;
    MediaPlayer play, menu, fail, win;

    int[] cards = {R.drawable.twoheart, R.drawable.threeheart
            , R.drawable.fourheart, R.drawable.fiveheart, R.drawable.sixheart, R.drawable.sevenheart, R.drawable.eightheart,
            R.drawable.nineheart, R.drawable.tenheart, R.drawable.jackheart, R.drawable.queenheart,
            R.drawable.kingheart, R.drawable.aceheart};

    int[] dp = {R.drawable.first, R.drawable.second, R.drawable.third, R.drawable.fourth, R.drawable.fifth};

    String [] names = {"Two red hearts", "Three red hearts", "Four red hearts", "Five red hearts", "Six red hearts"
    , "Seven red hearts", "Eight red hearts", "Nine red hearts", "Ten red hearts", "Jack red hearts", "Queen red hearts"
    , "King red hearts", "Ace red hearts"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highand_low);

        comp_score = 1;
        player_score = 1;
        flagCard = 0;

        btnHigh = (Button)findViewById(R.id.high);
        btnLow = (Button)findViewById(R.id.low);
        btnEnd = (Button)findViewById(R.id.end);
        btnPlay = (Button)findViewById(R.id.btnPLay);
        btnrestart = (ImageButton)findViewById(R.id.btnRestart);

        enemy_score = (TextView)findViewById(R.id.comp);
        user_score = (TextView)findViewById(R.id.user);

        enemy = (ImageView)findViewById(R.id.computer);
        user = (ImageView)findViewById(R.id.player);
        icon = (ImageView)findViewById(R.id.character);

        alertDialog = new AlertDialog.Builder(HighandLow.this).create();
        home = new Intent(HighandLow.this, MainActivity.class);

        play = MediaPlayer.create(HighandLow.this, R.raw.play);
        menu = MediaPlayer.create(HighandLow.this, R.raw.btns);
        fail = MediaPlayer.create(HighandLow.this, R.raw.fail);
        win = MediaPlayer.create(HighandLow.this, R.raw.win);

        myAnim = AnimationUtils.loadAnimation(this, R.anim.alpha);

        character();
        notEnabled();

        btnrestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnrestart.startAnimation(myAnim);
                Restart();
            }
        });

        btnHigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnHigh.startAnimation(myAnim);
                menu.start();

                ObjectAnimator flip = ObjectAnimator.ofFloat(user, "rotationY", 180f, 0f);
                flip.setDuration(100);
                flip.start();

                user.setImageResource(cards[player]);

                ObjectAnimator flip2 = ObjectAnimator.ofFloat(enemy, "rotationY", 180f, 0f);
                flip2.setDuration(100);
                flip2.start();

                enemy.setImageResource(cards[comp]);

                if(comp > player)
                {
                    fail.start();
                    EN = comp_score++;

                    alertDialog.setTitle("You lose!");
                    alertDialog.setMessage("Point goes to enemy" + "\nEnemy's Card: " + names[comp] + "\nPlayer's Card: " + names[player]);
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Continue",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    menu.start();
                                    enemy_score.setText(EN + " :Enemy's score");
                                    dialog.dismiss();
                                    notEnabled();
                                    score();
                                    btnPlay.setVisibility(View.VISIBLE);
                                }
                            });
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Home",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    menu.start();
                                    dialog.dismiss();
                                    end();
                                }
                            });
                    alertDialog.show();

                }
                else if(comp < player)
                {
                    win.start();
                    PL = player_score++;

                    alertDialog.setTitle("You Won!");
                    alertDialog.setMessage("You earned +1 point" + "\nEnemy's Card: " + names[comp] + "\nPlayer's Card: " + names[player]);
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Continue",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    menu.start();
                                    user_score.setText("Your Score: " + PL);
                                    dialog.dismiss();
                                    notEnabled();
                                    score();
                                    btnPlay.setVisibility(View.VISIBLE);
                                }
                            });
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Home",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    menu.start();
                                    dialog.dismiss();
                                    end();
                                }
                            });
                    alertDialog.show();
                }
                else if(comp == player)
                {
                    win.start();
                    PL = player_score++;
                    EN = comp_score++;

                    alertDialog.setTitle("You both won!");
                    alertDialog.setMessage("You both earned +1 point" + "\nEnemy's Card: " + names[comp] + "\nPlayer's Card: " + names[player]);
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Continue",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    menu.start();
                                    user_score.setText("Your Score: " + PL);
                                    enemy_score.setText(EN + " :Enemy's score");
                                    dialog.dismiss();
                                    notEnabled();
                                    score();
                                    btnPlay.setVisibility(View.VISIBLE);
                                }
                            });
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Home",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    menu.start();
                                    dialog.dismiss();
                                    end();
                                }
                            });
                    alertDialog.show();
                }
            }
        });

        btnLow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnLow.startAnimation(myAnim);
                menu.start();

                ObjectAnimator flip = ObjectAnimator.ofFloat(user, "rotationY", 180f, 0f);
                flip.setDuration(100);
                flip.start();

                user.setImageResource(cards[player]);

                ObjectAnimator flip2 = ObjectAnimator.ofFloat(enemy, "rotationY", 180f, 0f);
                flip2.setDuration(100);
                flip2.start();

                enemy.setImageResource(cards[comp]);

                if(comp > player)
                {
                    win.start();
                    PL = player_score++;

                    alertDialog.setTitle("You Won!");
                    alertDialog.setMessage("You earned +1 point" + "\nEnemy's Card: " + names[comp] + "\nPlayer's Card: " + names[player]);
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Continue",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    menu.start();
                                    user_score.setText("Your Score: " + PL);
                                    dialog.dismiss();
                                    notEnabled();
                                    score();
                                    btnPlay.setVisibility(View.VISIBLE);
                                }
                            });
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Home",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    menu.start();
                                    dialog.dismiss();
                                    end();
                                }
                            });
                    alertDialog.show();
                }
                else if(comp < player)
                {
                    fail.start();
                    EN = comp_score++;

                    alertDialog.setTitle("You lose!");
                    alertDialog.setMessage("Point goes to enemy" + "\nEnemy's Card: " + names[comp] + "\nPlayer's Card: " + names[player]);
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Continue",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    menu.start();
                                    enemy_score.setText(EN + " :Enemy's score");
                                    dialog.dismiss();
                                    notEnabled();
                                    score();
                                    btnPlay.setVisibility(View.VISIBLE);
                                }
                            });
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Home",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    menu.start();
                                    dialog.dismiss();
                                    end();
                                }
                            });
                    alertDialog.show();
                }
                else if(comp == player)
                {
                    win.start();
                    PL = player_score++;
                    EN = comp_score++;

                    alertDialog.setTitle("You both won!");
                    alertDialog.setMessage("You both earned +1 point" + "\nEnemy's Card: " + names[comp] + "\nPlayer's Card: " + names[player]);
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Continue",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    menu.start();
                                    user_score.setText("Your Score: " + PL);
                                    enemy_score.setText(EN + " :Enemy's score");
                                    dialog.dismiss();
                                    notEnabled();
                                    score();
                                    btnPlay.setVisibility(View.VISIBLE);

                                }
                            });
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Home",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    menu.start();
                                    dialog.dismiss();
                                    end();
                                }
                            });
                    alertDialog.show();
                }
            }
        });

        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnEnd.startAnimation(myAnim);
                menu.start();
                end();
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnPlay.startAnimation(myAnim);
                play.start();

                enabled();

                btnPlay.setVisibility(View.GONE);

                comp = ran.nextInt(cards.length);

                player = ran.nextInt(cards.length);

                    ObjectAnimator flip = ObjectAnimator.ofFloat(user, "rotationY", 180f, 0f);
                    flip.setDuration(100);
                    flip.start();

                    ObjectAnimator flip2 = ObjectAnimator.ofFloat(enemy, "rotationY", 180f, 0f);
                    flip2.setDuration(100);
                    flip2.start();

                backCard();
            }
        });

    }
    public void score()
    {
        if(PL == 5)
        {
            Restart();
            Toast.makeText(HighandLow.this, "Player won!", Toast.LENGTH_LONG).show();
        }
        else if (EN == 5)
        {
            Restart();
            Toast.makeText(HighandLow.this, "Enemy won!", Toast.LENGTH_LONG).show();
        }
        else if (PL == 5 && EN == 5)
        {
            Restart();
            Toast.makeText(HighandLow.this, "Game is draw!", Toast.LENGTH_LONG).show();
        }
    }
    public void Restart()
    {
        win.start();

        EN = PL = 0;

        btnPlay.setVisibility(View.VISIBLE);

        comp_score = player_score = 1;

        ObjectAnimator flip = ObjectAnimator.ofFloat(user, "rotationY", 180f, 0f);
        flip.setDuration(100);
        flip.start();

        ObjectAnimator flip2 = ObjectAnimator.ofFloat(enemy, "rotationY", 180f, 0f);
        flip2.setDuration(100);
        flip2.start();

        backCard();
        notEnabled();
        character();

        enemy_score.setText(" :Enemy's score");
        user_score.setText("Your Score: ");
    }
    public void character()
    {
        pic = ran.nextInt(dp.length);
        icon.setImageResource(dp[pic]);
    }
    public void enabled()
    {
        btnHigh.setEnabled(true);
        btnLow.setEnabled(true);
    }
    public void notEnabled()
    {
        btnHigh.setEnabled(false);
        btnLow.setEnabled(false);
    }
    public void backCard()
    {
        enemy.setImageResource(R.drawable.unknowncard1);
        user.setImageResource(R.drawable.unknowncard2);
    }
    public void end()
    {
        HighandLow.this.finish();
        System.exit(0);
        startActivity(home);
    }

}
