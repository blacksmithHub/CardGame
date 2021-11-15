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
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static com.example.libor.cardgame.R.id.user;

public class GuessGame extends AppCompatActivity {
    Random ran = new Random();
    Button btnplay, btnend, btnguess;
    ImageButton player_card, btnrestart;
    ImageView comp, icon, l1, l2, l3;
    SeekBar user_card;
    TextView player_score, comp_score;
    int pic, enemy, player, PL, EN, CP_score, PL_score, tries, life, seek, ranseek;
    Animation myAnim;
    AlertDialog alertDialog;
    MediaPlayer play, menu, fail, win;

    int[] cards = {R.drawable.twoheart, R.drawable.threeheart
            , R.drawable.fourheart, R.drawable.fiveheart, R.drawable.sixheart, R.drawable.sevenheart, R.drawable.eightheart,
            R.drawable.nineheart, R.drawable.tenheart, R.drawable.jackheart, R.drawable.queenheart,
            R.drawable.kingheart, R.drawable.aceheart};

    int[] dp = {R.drawable.first, R.drawable.second, R.drawable.third, R.drawable.fourth, R.drawable.fifth};

    String [] names = {"Two red hearts", "Three red hearts", "Four red hearts", "Five red hearts", "Six red hearts"
            , "Seven red hearts", "Eight red hearts", "Nine red hearts", "Ten red hearts", "Jack red hearts", "Queen red hearts"
            , "King red hearts", "Ace red hearts"};

    Intent home = new Intent(GuessGame.this, MainActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_game);

        myAnim = AnimationUtils.loadAnimation(this, R.anim.alpha);

        alertDialog = new AlertDialog.Builder(GuessGame.this).create();

        CP_score = 1;
        PL_score = 1;
        tries = 1;
        player = 0;

        btnplay = (Button) findViewById(R.id.btnPLay);
        btnguess = (Button) findViewById(R.id.btnGuess);
        btnend = (Button) findViewById(R.id.end);

        btnrestart = (ImageButton) findViewById(R.id.btnRestart);
        player_card = (ImageButton) findViewById(R.id.player);

        comp = (ImageView) findViewById(R.id.computer);
        icon = (ImageView) findViewById(R.id.character);
        l1 = (ImageView) findViewById(R.id.L1);
        l2 = (ImageView) findViewById(R.id.L2);
        l3 = (ImageView) findViewById(R.id.L3);

        user_card = (SeekBar) findViewById(R.id.seekBar_card);

        player_score = (TextView) findViewById(user);
        comp_score = (TextView) findViewById(R.id.comp);

        play = MediaPlayer.create(GuessGame.this, R.raw.play);
        menu = MediaPlayer.create(GuessGame.this, R.raw.btns);
        fail = MediaPlayer.create(GuessGame.this, R.raw.fail);
        win = MediaPlayer.create(GuessGame.this, R.raw.win);

        pic = ran.nextInt(dp.length);
        icon.setImageResource(dp[pic]);

        player_card.setEnabled(false);
        user_card.setEnabled(false);

        //seek bar
        user_card.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                //flip animation
                ObjectAnimator flip = ObjectAnimator.ofFloat(player_card, "rotationY", 180f, 0f);
                flip.setDuration(100);
                flip.start();

                //sound effect
                menu.start();

                btnguess.setVisibility(View.VISIBLE);


                switch (i) {
                    case 0:
                        player_card.setImageResource(cards[0]);
                        player = 0;
                        break;
                    case 1:
                        player_card.setImageResource(cards[1]);
                        player = 1;
                        break;
                    case 2:
                        player_card.setImageResource(cards[2]);
                        player = 2;
                        break;
                    case 3:
                        player_card.setImageResource(cards[3]);
                        player = 3;
                        break;
                    case 4:
                        player_card.setImageResource(cards[4]);
                        player = 4;
                        break;
                    case 5:
                        player_card.setImageResource(cards[5]);
                        player = 5;
                        break;
                    case 6:
                        player_card.setImageResource(cards[6]);
                        player = 6;
                        break;
                    case 7:
                        player_card.setImageResource(cards[7]);
                        player = 7;
                        break;
                    case 8:
                        player_card.setImageResource(cards[8]);
                        player = 8;
                        break;
                    case 9:
                        player_card.setImageResource(cards[9]);
                        player = 9;
                        break;
                    case 10:
                        player_card.setImageResource(cards[10]);
                        player = 10;
                        break;
                    case 11:
                        player_card.setImageResource(cards[11]);
                        player = 11;
                        break;
                    case 12:
                        player_card.setImageResource(cards[12]);
                        player = 12;
                        break;
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        player_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //flip animation
                ObjectAnimator flip = ObjectAnimator.ofFloat(player_card, "rotationY", 180f, 0f);
                flip.setDuration(100);
                flip.start();

                btnguess.setVisibility(View.VISIBLE);

                player++;
                player = player % 13;
                switch (player) {
                    case 0:
                        player_card.setImageResource(cards[0]);
                        user_card.setProgress(0);
                        break;
                    case 1:
                        player_card.setImageResource(cards[1]);
                        user_card.setProgress(1);
                        break;
                    case 2:
                        player_card.setImageResource(cards[2]);
                        user_card.setProgress(2);
                        break;
                    case 3:
                        player_card.setImageResource(cards[3]);
                        user_card.setProgress(3);
                        break;
                    case 4:
                        player_card.setImageResource(cards[4]);
                        user_card.setProgress(4);
                        break;
                    case 5:
                        player_card.setImageResource(cards[5]);
                        user_card.setProgress(5);
                        break;
                    case 6:
                        player_card.setImageResource(cards[6]);
                        user_card.setProgress(6);
                        break;
                    case 7:
                        player_card.setImageResource(cards[7]);
                        user_card.setProgress(7);
                        break;
                    case 8:
                        player_card.setImageResource(cards[8]);
                        user_card.setProgress(8);
                        break;
                    case 9:
                        player_card.setImageResource(cards[9]);
                        user_card.setProgress(9);
                        break;
                    case 10:
                        player_card.setImageResource(cards[10]);
                        user_card.setProgress(10);
                        break;
                    case 11:
                        player_card.setImageResource(cards[11]);
                        user_card.setProgress(11);
                        break;
                    case 12:
                        player_card.setImageResource(cards[12]);
                        user_card.setProgress(12);
                        break;
                }
            }
        });

        btnrestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //button animation
                btnrestart.startAnimation(myAnim);
                //restart method
                Restart();

            }
        });

        btnend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //button animation
                btnend.startAnimation(myAnim);
                //alertDialog dialog
                alertDialog.setTitle("Do you wish to exit?");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //sound effect
                                menu.start();
                                dialog.dismiss();
                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //sound effect
                                menu.start();
                                dialog.dismiss();
                                //end to main menu
                                GuessGame.this.finish();
                                System.exit(0);
                                startActivity(home);
                            }
                        });
                alertDialog.show();
                //sound effect
                menu.start();
            }
        });
        btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //button animation
                btnplay.startAnimation(myAnim);
                //sound effect
                play.start();
                tries = 1;
                life = 0;
                player_card.setEnabled(true);
                user_card.setEnabled(true);
                btnplay.setVisibility(View.GONE);
                l1.setVisibility(View.VISIBLE);
                l2.setVisibility(View.VISIBLE);
                l3.setVisibility(View.VISIBLE);
                //generate new enemy card
                enemy = ran.nextInt(cards.length);
                //flip animation
                ObjectAnimator flip = ObjectAnimator.ofFloat(comp, "rotationY", 180f, 0f);
                flip.setDuration(100);
                flip.start();

                comp.setImageResource(R.drawable.unknowncard1);
                //flip animation 2
                ObjectAnimator flip2 = ObjectAnimator.ofFloat(player_card, "rotationY", 180f, 0f);
                flip2.setDuration(100);
                flip2.start();

                player_card.setImageResource(R.drawable.unknowncard2);

            }
        });
        btnguess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //button animation
                btnguess.startAnimation(myAnim);
                //sound effect
                menu.start();
                btnguess.setVisibility(View.GONE);
                //if two cards are the same
                if (player == enemy) {
                    //sound effect
                    win.start();
                    //increment player's score
                    PL = PL_score++;
                    //flip animation
                    ObjectAnimator flip2 = ObjectAnimator.ofFloat(comp, "rotationY", 180f, 0f);
                    flip2.setDuration(100);
                    flip2.start();
                    comp.setImageResource(cards[enemy]);
                    //alertDialog dialog
                    alertDialog.setTitle("You Won!");
                    alertDialog.setMessage("You earned +1 point" + "\nEnemy's Card: " + names[enemy] + "\nPlayer's Card: " + names[player]);
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Continue",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    //sound effect
                                    menu.start();
                                    //scoring
                                    player_score.setText("Your Score: " + PL);
                                    dialog.dismiss();
                                    //score method
                                    score();

                                    btnplay.setVisibility(View.VISIBLE);
                                    player_card.setEnabled(false);
                                    user_card.setEnabled(false);
                                }
                            });
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Home",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    //sound effect
                                    menu.start();
                                    dialog.dismiss();
                                    //end to main menu
                                    GuessGame.this.finish();
                                    System.exit(0);
                                    startActivity(home);
                                }
                            });
                    alertDialog.show();

                } else if (enemy != player) {
                    //increment tries
                    life = tries++;
                    //life countdown
                    switch(life)
                    {
                        case 1: l3.setVisibility(View.GONE);break;
                        case 2: l2.setVisibility(View.GONE);break;
                        case 3: l1.setVisibility(View.GONE);break;
                    }
                    //end of 3 tries
                    if (life == 3) {
                        //sound effect
                        fail.start();
                        //increment enemy score
                        EN = CP_score++;
                        //flip animation
                        ObjectAnimator flip2 = ObjectAnimator.ofFloat(comp, "rotationY", 180f, 0f);
                        flip2.setDuration(100);
                        flip2.start();
                        comp.setImageResource(cards[enemy]);
                        //alertDialog dialog
                        alertDialog.setTitle("You lose!");
                        alertDialog.setMessage("Point goes to enemy" + "\nEnemy's Card: " + names[enemy] + "\nPlayer's Card: " + names[player]);
                        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Continue",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        //sound effect
                                        menu.start();
                                        comp_score.setText(EN + " :Enemy's score");
                                        dialog.dismiss();
                                        tries = 1;
                                        life = 0;
                                        //score method
                                        score();
                                        //reveal enemy card
                                        comp.setImageResource(cards[enemy]);
                                        btnplay.setVisibility(View.VISIBLE);
                                        player_card.setEnabled(false);
                                        user_card.setEnabled(false);
                                    }
                                });
                        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Home",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        //sound effect
                                        menu.start();
                                        dialog.dismiss();
                                        //end to main menu
                                        GuessGame.this.finish();
                                        System.exit(0);
                                        startActivity(home);
                                    }
                                });
                        alertDialog.show();
                    } else {
                        //sound effect
                        fail.start();
                        //alertDialog dialog
                        alertDialog.setTitle("Wrong Card!");
                        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Continue",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        //sound effect
                                        menu.start();
                                        dialog.dismiss();
                                        //score method
                                        score();

                                        btnguess.setVisibility(View.VISIBLE);
                                        player_card.setEnabled(true);
                                        user_card.setEnabled(true);
                                    }
                                });
                        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Home",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        //sound effect
                                        menu.start();
                                        dialog.dismiss();
                                        //end to main menu
                                        GuessGame.this.finish();
                                        System.exit(0);
                                        startActivity(home);
                                    }
                                });
                        alertDialog.show();
                    }
                }
            }
        });
    }
    //score method
    public void score()
    {
        if(PL == 5)
        {
            Restart();
            Toast.makeText(GuessGame.this, "Player won!", Toast.LENGTH_LONG).show();
        }
        else if (EN == 5)
        {
            Restart();
            Toast.makeText(GuessGame.this, "Enemy won!", Toast.LENGTH_LONG).show();
        }
        else if (PL == 5 && EN == 5)
        {
            Restart();
            Toast.makeText(GuessGame.this, "Game is draw!", Toast.LENGTH_LONG).show();
        }
    }
    //restarts the whole game
    public void Restart()
    {
        //sound effect
        play.start();

        tries = 1;
        CP_score = 1;
        player = 0;
        PL = 0;
        EN = 0;
        PL_score = 1;
        seek = 0;
        ranseek = 0;
        life = 0;

        //generate new enemy hero
        pic = ran.nextInt(dp.length);
        icon.setImageResource(dp[pic]);

        player_score.setText("Your Score: ");
        comp_score.setText(" :Enemy's score");

        player_card.setEnabled(false);
        user_card.setEnabled(false);

        l1.setVisibility(View.VISIBLE);
        l2.setVisibility(View.VISIBLE);
        l3.setVisibility(View.VISIBLE);

        btnplay.setVisibility(View.VISIBLE);
        btnguess.setVisibility(View.GONE);
        //generate new enemy card
        enemy = ran.nextInt(cards.length);
        //flip animation
        ObjectAnimator flip = ObjectAnimator.ofFloat(comp, "rotationY", 180f, 0f);
        flip.setDuration(100);
        flip.start();
        comp.setImageResource(R.drawable.unknowncard1);
        //flip animation 2
        ObjectAnimator flip2 = ObjectAnimator.ofFloat(player_card, "rotationY", 180f, 0f);
        flip2.setDuration(100);
        flip2.start();
        player_card.setImageResource(R.drawable.unknowncard2);
    }
    }

