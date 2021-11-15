package com.example.libor.cardgame;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Locale;
import pl.droidsonroids.gif.GifTextView;

public class Baccarat extends AppCompatActivity {
    Boolean val, pick, flip;
    TextToSpeech tts;
    GifTextView gif;
    ObjectAnimator Uflip1, Uflip2, Uflip3, Cflip1, Cflip2, Cflip3;
    Animation myAnim, animation;
    MediaPlayer start, menu, fail, win, fireworks;
    Intent home;
    String output;
    ImageView cardMach1, cardMach2, cardMach3, cardUser1, cardUser2, cardUser3;
    ImageButton restart;
    TextView actionText, comp, user;
    Button play, end;
    String text;
    int cardC1, cardC2, cardC3, cardU1, cardU2, cardU3;
    int scoreUser, scoreMach;
    int Ushape3, Ushape1, Ushape2, Cshape1, Cshape2, Cshape3;
    int MachineCard1, MachineCard2, MachineCard3, UserCard1, UserCard2, UserCard3;
    int UserCard, MachineCard;
    int U1, U2, U3, C1, C2, C3;
    int prob, num;
    int sw;
    public int cards[][] = {{R.drawable.ace_of_hearts, R.drawable.ace_of_clubs, R.drawable.ace_of_diamonds, R.drawable.ace_of_spades2},
            {R.drawable.two_of_diamonds, R.drawable.two_of_clubs, R.drawable.two_of_hearts, R.drawable.two_of_spades},
            {R.drawable.three_of_clubs, R.drawable.three_of_diamonds, R.drawable.three_of_hearts, R.drawable.three_of_spades},
            {R.drawable.four_of_clubs, R.drawable.four_of_diamonds, R.drawable.four_of_hearts, R.drawable.four_of_spades},
            {R.drawable.five_of_clubs, R.drawable.five_of_diamonds, R.drawable.five_of_hearts, R.drawable.five_of_spades},
            {R.drawable.six_of_clubs, R.drawable.six_of_diamonds, R.drawable.six_of_hearts, R.drawable.six_of_spades},
            {R.drawable.seven_of_clubs, R.drawable.seven_of_diamonds, R.drawable.seven_of_hearts, R.drawable.seven_of_spades},
            {R.drawable.eight_of_clubs, R.drawable.eight_of_diamonds, R.drawable.eight_of_hearts, R.drawable.eight_of_spades},
            {R.drawable.nine_of_clubs, R.drawable.nine_of_diamonds, R.drawable.nine_of_hearts, R.drawable.nine_of_spades},
            {R.drawable.ten_of_clubs, R.drawable.ten_of_diamonds, R.drawable.ten_of_hearts, R.drawable.ten_of_spades},
            {R.drawable.jack_of_clubs, R.drawable.jack_of_diamonds2, R.drawable.jack_of_hearts2, R.drawable.jack_of_spades2},
            {R.drawable.queen_of_clubs2, R.drawable.queen_of_diamonds2, R.drawable.queen_of_hearts2, R.drawable.queen_of_spades2},
            {R.drawable.king_of_clubs2, R.drawable.king_of_diamonds2, R.drawable.king_of_hearts2, R.drawable.king_of_spades2}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baccarat);
        gif = (GifTextView) findViewById(R.id.gif);
        play = (Button) findViewById(R.id.btnPLay);
        end = (Button) findViewById(R.id.end);
        actionText = (TextView) findViewById(R.id.addtext);
        comp = (TextView) findViewById(R.id.comp);
        user = (TextView) findViewById(R.id.user);
        restart = (ImageButton) findViewById(R.id.btnRestart);
        cardUser3 = (ImageView) findViewById(R.id.cardUser3);
        cardUser2 = (ImageView) findViewById(R.id.cardUser2);
        cardUser1 = (ImageView) findViewById(R.id.cardUser1);
        cardMach3 = (ImageView) findViewById(R.id.cardMach3);
        cardMach2 = (ImageView) findViewById(R.id.cardMach2);
        cardMach1 = (ImageView) findViewById(R.id.cardMach1);
        home = new Intent(Baccarat.this, MainActivity.class);
        fireworks = MediaPlayer.create(Baccarat.this, R.raw.fireworks);
        start = MediaPlayer.create(Baccarat.this, R.raw.play);
        menu = MediaPlayer.create(Baccarat.this, R.raw.btns);
        fail = MediaPlayer.create(Baccarat.this, R.raw.fail);
        win = MediaPlayer.create(Baccarat.this, R.raw.win);
        myAnim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        tts = new TextToSpeech(Baccarat.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS){
                    int result=tts.setLanguage(Locale.US);
                    if(result==TextToSpeech.LANG_MISSING_DATA ||
                            result==TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("error", "This Language is not supported");
                    }
                    else{
                        text = "Press play to start a new game";
                        TTS(text);
                    }
                }
                else
                    Log.e("error", "Initialization Failed!");
            }
        });
        cardUser3.setClickable(false);
        actionText.setText("Press play to start a new game");
        play();
        end();
        refresh();
        setAnimation();
        val = false;
        flip = false;
        sw = 0;
    }
    private void refresh()
    {
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restart.startAnimation(myAnim);
                clearCards();
                menu.start();
                play.setText("PLAY");
                cardUser3.setClickable(false);
                setNewScore();
                text = "Press play to start a new game";
                actionText.setText(text);
                TTS(text);

            }
        });
    }
    private void setNewScore()
    {
        scoreUser = 0;
        scoreMach = 0;
        comp.setText(" :Enemy's points");
        user.setText("Your points: ");
    }
    private void end()
    {
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tts.stop();
                end.startAnimation(myAnim);
                menu.start();
                Baccarat.this.finish();
                System.exit(0);
                startActivity(home);
            }
        });
    }
    public void play()
    {
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play.startAnimation(myAnim);
                if (sw==0){
                    tts.stop();
                    start.start();
                    clearCards();
                    play.setText("DEAL");
                    text = "Press DEAL to deal new cards";
                    actionText.setText(text);
                    TTS(text);
                    if(val == true)
                    {
                        setNewScore();
                    }
                    sw=1;
                }
                else if  (sw==1){
                    tts.stop();
                    deal();
                    menu.start();
                    play.setText("LAY");
                    text = "Press LAY to lay all cards or pick another card";
                    actionText.setText(text);
                    TTS(text);
                    do
                    {
                        num = 10;
                        if(UserCard%10 == 9 || MachineCard%10 == 9 || UserCard == 9 || MachineCard == 9)
                        {
                            lay();
                            gameover();
                            play.setText("PLAY");
                            sw=0;
                            break;
                        }
                        else {

                            for(int x = 0; x < MachineCard%10; x++)
                            {
                                num = num - 1;
                            }

                            prob = (int) (Math.random() * num + 1);

                            switch (prob)
                            {
                                case 3 : pick = true; break;
                                case 4 : pick = true; break;
                                case 5 : pick = true; break;
                                case 6 : pick = true; break;
                                case 7 : pick = true; break;
                                case 8 : pick = true; break;
                                case 9 : pick = true; break;
                                case 10 : pick = true; break;
                                default: pick = false; break;
                            }

                            if(pick == true)
                            {
                                cardMach3();
                                MachineCard = MachineCard1 + MachineCard2 + MachineCard3;
                            }
                            else
                            {
                                pick = false;
                                Toast.makeText(Baccarat.this, "Machine refused to pick another card",Toast.LENGTH_SHORT).show();
                            }
                            sw=2;
                        }
                    }while(MachineCard%10 == 9 || MachineCard == 9);

                }
                else if  (sw==2){
                    tts.stop();
                    lay();
                    menu.start();
                    gameover();
                    play.setText("PLAY");
                    sw=0;
                }
            }
        });

    }
    public void lay()
    {
        output = "\nPlayer score is " + UserCard%10 + "\nMachine score is " + MachineCard%10 + "\nPress PLAY to \n\tcontinue";

        if (UserCard%10 == MachineCard%10)
        {
            win.start();
            text = "A stand was made!" + output;
            scoreUser++;
            scoreMach++;

        }
        else if(UserCard%10 > MachineCard%10)
        {
            win.start();
            text = "You win!" + output;
            scoreUser++;
        }
        else if (UserCard%10 < MachineCard%10)
        {
            fail.start();
            text = "You lose!" + output;
            scoreMach++;
        }
        comp.setText( scoreMach + " :Enemy's points");
        user.setText("Your points: " + scoreUser);
        actionText.setText(text);
        TTS(text);
        Cflip1.start();
        Cflip2.start();
        Cflip3.start();
        cardMach1.setImageResource(C1);
        cardMach2.setImageResource(C2);
        if(flip == true)
        {
            cardMach3.setImageResource(C3);
            flip = false;
        }
        cardUser3.setClickable(false);
    }
    public void gameover()
    {
        if(scoreUser == 5)
        {
            setFireworks();
            Toast.makeText(Baccarat.this,"YOU WIN!" , Toast.LENGTH_LONG).show();
            actionText.setText("GAME OVER");
            text = "YOU WIN!";
            TTS("game over " + text);
            val = true;
        }
        else if (scoreMach == 5)
        {
            setFireworks();
            Toast.makeText(Baccarat.this,"MACHINE WIN!" , Toast.LENGTH_LONG).show();
            actionText.setText("GAME OVER");
            text = "MACHINE WIN!";
            TTS("game over " + text);
            val = true;
        }
        else if (scoreUser == 5 && scoreMach == 5)
        {
            setFireworks();
            Toast.makeText(Baccarat.this,"DRAW!" , Toast.LENGTH_LONG).show();
            actionText.setText("GAME OVER");
            text = "DRAW!";
            TTS("game over " + text);
            val = true;
        }
        else
        {
            val = false;
        }
    }
    public void cardUser3()
    {
        cardUser3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardUser3.startAnimation(myAnim);
                start.start();
                tts.stop();
                animation = new TranslateAnimation(0, -238,0, 150);
                animation.setDuration(500);
                animation.setFillAfter(true);
                cardUser3.startAnimation(animation);
                UserCard = UserCard1 + UserCard2 + UserCard3;
                actionText.setText("Press LAY to lay all cards");
                text = "You decided to pick another card";
                TTS(text);
                cardUser3.setClickable(false);
                Uflip3.start();
                cardUser3.setImageResource(U3);
            }
        });
    }
    public void deal()
    {
        do {
            cardUser3();

            cardU1 = (int) (Math.random() * 13);
            cardU2 = (int) (Math.random() * 13);
            cardU3 = (int) (Math.random() * 13);
            Ushape1 = (int) (Math.random() * 4);
            Ushape2 = (int) (Math.random() * 4);
            Ushape3 = (int) (Math.random() * 4);
            UserCard1 = cardU1 + 1;
            UserCard2 = cardU2 + 1;
            UserCard3 = cardU3 + 1;
            U1 = cards[cardU1][Ushape1];
            U2 = cards[cardU2][Ushape2];
            U3 = cards[cardU3][Ushape3];

            if(UserCard1 >= 10)
            {
                UserCard1 = 0;
            }
            if (UserCard2 >= 10)
            {
                UserCard2 = 0;
            }
            if (UserCard3 >= 10)
            {
                UserCard3 = 0;
            }
            UserCard = UserCard1 + UserCard2;
            Uflip1.start();
            Uflip2.start();

            animation = new TranslateAnimation(0, -299,0, 195);
            animation.setDuration(500);
            animation.setFillAfter(true);
            cardUser1.startAnimation(animation);

            animation = new TranslateAnimation(0, -147,0, 195);
            animation.setDuration(500);
            animation.setFillAfter(true);
            cardUser2.startAnimation(animation);

            cardUser1.setImageResource(U1);
            cardUser2.setImageResource(U2);

            cardC1 = (int) (Math.random() * 13);
            cardC2 = (int) (Math.random() * 13);
            cardC3 = (int) (Math.random() * 13);
            Cshape1 = (int) (Math.random() * 4);
            Cshape2 = (int) (Math.random() * 4);
            Cshape3 = (int) (Math.random() * 4);
            MachineCard1 = cardC1 + 1;
            MachineCard2 = cardC2 + 1;
            MachineCard3 = cardC3 + 1;
            C1 = cards[cardC1][Cshape1];
            C2 = cards[cardC2][Cshape2];
            C3 = cards[cardC3][Cshape3];

            animation = new TranslateAnimation(0, -299,0, -120);
            animation.setDuration(500);
            animation.setFillAfter(true);
            cardMach1.startAnimation(animation);

            animation = new TranslateAnimation(0, -147,0, -120);
            animation.setDuration(500);
            animation.setFillAfter(true);
            cardMach2.startAnimation(animation);

            if(MachineCard1 >= 10)
            {
                MachineCard1 = 0;
            }
            if(MachineCard2 >= 10)
            {
                MachineCard2 = 0;
            }
            if(MachineCard3 >= 10)
            {
                MachineCard3 = 0;
            }
            MachineCard = MachineCard1 + MachineCard2;
        }while(U1 == U2 || U1 == U3 || U1 == C1 || U1 == C2 || U1 == C3
                || U2 == U3 || U2 == C1 || U2 == C2 || U2 == C3
                || U3 == C1 || U3 == C2 || U3 == C3
                || C1 == C2 || C1 == C3 || C2 == C3);
    }
    public void cardMach3()
    {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                animation = new TranslateAnimation(0, -238,0, -160);
                animation.setDuration(500);
                animation.setFillAfter(true);
                cardMach3.startAnimation(animation);
                flip = true;
                Toast.makeText(Baccarat.this, "Machine decided to pick another card",Toast.LENGTH_SHORT).show();
            }
        }, 200);

    }
    public void clearCards()
    {
        tts.stop();
        Cflip1.start();
        Cflip2.start();
        Cflip3.start();
        Uflip1.start();
        Uflip2.start();
        Uflip3.start();
        cardUser1.setImageResource(R.drawable.backcard);
        cardUser2.setImageResource(R.drawable.backcard);
        cardUser3.setImageResource(R.drawable.backcard);
        cardMach1.setImageResource(R.drawable.backcard);
        cardMach2.setImageResource(R.drawable.backcard);
        cardMach3.setImageResource(R.drawable.backcard);
        cardUser1.clearAnimation();
        cardUser2.clearAnimation();
        cardUser3.clearAnimation();
        cardMach1.clearAnimation();
        cardMach2.clearAnimation();
        cardMach3.clearAnimation();
        gif.setVisibility(View.GONE);
    }
    public void setAnimation()
    {
        Cflip1 = ObjectAnimator.ofFloat(cardMach1, "rotationY", 180f, 0f);
        Cflip2 = ObjectAnimator.ofFloat(cardMach2, "rotationY", 180f, 0f);
        Cflip3 = ObjectAnimator.ofFloat(cardMach3, "rotationY", 180f, 0f);
        Uflip1 = ObjectAnimator.ofFloat(cardUser1, "rotationY", 180f, 0f);
        Uflip2 = ObjectAnimator.ofFloat(cardUser2, "rotationY", 180f, 0f);
        Uflip3 = ObjectAnimator.ofFloat(cardUser3, "rotationY", 180f, 0f);
        Cflip1.setDuration(100);
        Cflip2.setDuration(100);
        Cflip3.setDuration(100);
        Uflip1.setDuration(100);
        Uflip2.setDuration(100);
        Uflip3.setDuration(100);
    }
    public void setFireworks()
    {
        fireworks.start();
        gif.setVisibility(View.VISIBLE);
    }
    private String TTS(String voice)
    {
        tts.speak(voice, TextToSpeech.QUEUE_ADD, null);
        return voice;
    }
}
