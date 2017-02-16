package com.ayako_sayama.threads;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    final long MYPASSWORD = 1122333;
    TextView txtStatus, txtResults;
    ProgressBar bar;
    PasswordGuesser guesser = new PasswordGuesser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtResults = (TextView)findViewById(R.id.txtAnswer);
        txtStatus = (TextView)findViewById(R.id.txtProgress);
        bar = (ProgressBar)findViewById(R.id.progressBar);
        bar.setVisibility(View.INVISIBLE);
    }

    public void guess(View view) {

        final long RATE = 1000;
        final long RANGE = 10000000;
        guesser.execute(RATE,RANGE);
    }

    public void displayResults(Long answer) {
        txtResults.setText(answer+"");
    }


    private void displayProgress(String message) {
        txtStatus.setText(message);
    }



    public class PasswordGuesser extends AsyncTask<Long, String, Long> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            bar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Long answer) {
            super.onPostExecute(answer);
            bar.setVisibility(View.INVISIBLE);
            displayResults(answer);
        }


        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            String message = "";
            for (String str : values){
                message += str + " ,";
                displayProgress(message);
            }

        }

        @Override
        protected Long doInBackground(Long... params) {
            long rate = params[0];
            long range = params[1];
            long count = 0;
            long guess = 0;
            Random rand = new Random();

            while(guess != MYPASSWORD){

                //この意味は？？
                guess = Math.abs(rand.nextLong())%range;
                count++;

                if (count % rate == 0){
                    publishProgress("Guess Number: " + count + "Last Guess: " + guess);
                }

            }

            return guess;
        }
    }


}

