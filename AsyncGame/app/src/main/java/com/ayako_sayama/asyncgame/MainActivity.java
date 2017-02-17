package com.ayako_sayama.asyncgame;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTimer();

        PlayerOne syncOne = new PlayerOne(findViewById(R.id.playerOne));
        syncOne.randomAnswer();
        syncOne.shuffleNumbers();
        syncOne.setClick();


        PlayerOne syncTwo = new PlayerOne(findViewById(R.id.playerTwo));
        syncTwo.randomAnswer();
        syncTwo.shuffleNumbers();
        syncTwo.setClick();
    }

    private void setTimer() {
        time = (TextView)findViewById(R.id.txtTime);
        CountDownTimer timer = new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
                time.setText("Time: "+ millisUntilFinished / 1000);
            }
            public void onFinish() {
                time.setText("Time UP!!");
            }
        };
        timer.start();
    }

}


