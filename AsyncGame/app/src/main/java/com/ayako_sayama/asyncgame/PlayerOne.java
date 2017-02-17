package com.ayako_sayama.asyncgame;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by ayako_sayama on 2017/02/16.
 */
public class PlayerOne {

    private static final String TAG = "MainActivity";
    GuessRandom guessRandom;
    int randomAnswer;
    int rate = 1000;
    int minValue = 0;

    int max = 10000000;
    int min = 1;

    View player1;

    View p1BtnOne;
    View p1BtnTwo;
    View p1BtnThree;
    View p1BtnFour;

    Button p1BtnNext;

    TextView scoreOne;
    TextView calOne;
    int p1Score = 0;

    int valueOne;
    int valueTwo;
    int valueThree;
    int valueFour;

    PlayerOne(View rootView) {
        this.player1 = rootView;
        p1Score = 0;

        p1BtnOne = player1.findViewById(R.id.btnOne);
        p1BtnTwo = player1.findViewById(R.id.btnTwo);
        p1BtnThree = player1.findViewById(R.id.btnThree);
        p1BtnFour = player1.findViewById(R.id.btnFour);

        p1BtnNext = (Button)player1.findViewById(R.id.btnNext);
        p1BtnNext.setVisibility(View.INVISIBLE);

        scoreOne = (TextView) player1.findViewById(R.id.txtScoreOne);
        calOne = (TextView) player1.findViewById(R.id.calculationOne);
    }

    public int getP1Score() {
        return p1Score;
    }

    public void setP1Score(int p1Score) {
        this.p1Score = p1Score;
    }


    public void setClick() {

        p1BtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p1BtnNext.setVisibility(View.INVISIBLE);
                shuffleNumbers();
                randomAnswer();
            }
        });

        p1BtnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guessRandom = new GuessRandom(1);
                p1BtnOne.setPressed(true);
                guessRandom.execute();
            }
        });
        p1BtnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guessRandom = new GuessRandom(2);
                p1BtnTwo.setPressed(true);
                guessRandom.execute();
            }
        });
        p1BtnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guessRandom = new GuessRandom(3);
                guessRandom.execute();
            }
        });
        p1BtnFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guessRandom = new GuessRandom(4);
                guessRandom.execute();
            }
        });
    }

    public void randomAnswer() {
        randomAnswer = (int) (Math.random() * max + min);
        Log.i(TAG, "onCreate: "+ randomAnswer);
    }

    public void shuffleNumbers() {

        int parts = 4;
        Integer[] array = new Integer[parts];
        int size = array.length;
        Log.i(TAG, "array.length is: "+size);

        int x = 0;

        for(int i = 0; i < parts; i++){
            x = x + (max/parts);
            int t = (int) (Math.random()*parts);

            while (array[t] != null){
                t = (int) (Math.random()*parts);
                Log.i(TAG, "Random t: while loop "+t);
            }
            array[t] = x;
            Log.i(TAG, "array["+t+"] is "+array[t]);
        }

        valueOne = array[0];
        valueTwo = array[1];
        valueThree = array[2];
        valueFour = array[3];
    }

    public class GuessRandom extends AsyncTask<Void, String, Integer> {

        private int type = 0;
        private int targetNum;

        GuessRandom(int type){
            this.type = type;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            switch (type) {
                case 1:targetNum = valueOne; break;
                case 2: targetNum = valueTwo; break;
                case 3: targetNum = valueThree; break;
                case 4: targetNum = valueFour; break;
                default: break;
            }

            minValue = targetNum - (max/4);
            Log.i(TAG, "onPreExecute: minValue= "+minValue+ " targetNum= "+targetNum);
        }

        @Override
        protected void onPostExecute(Integer aLong) {
            super.onPostExecute(aLong);
            Log.i(TAG, "aLong is: "+aLong);
            if(aLong == randomAnswer){
                calOne.setText("Found Answer! "+randomAnswer);
                p1Score++;
                scoreOne.setText(p1Score+"");
                p1BtnNext.setVisibility(View.VISIBLE);

            } else {
                calOne.setText("No answer here! Try next button.");
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            calOne.setText("Calculating..." + values[0]);
        }
        @Override
        protected Integer doInBackground(Void... params) {

            int i;

            outerloop:
            for (i=minValue; i < targetNum; i++){

                if (i % rate == 0){
                    publishProgress(" " + i);
                }
                if (i == randomAnswer){
                    break outerloop;
                }
            }

            return i;
        }

    }

}
