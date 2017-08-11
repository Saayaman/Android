package com.ayako_sayama.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.ayako_sayama.calculator.R.id.button0;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "mainActivity" ;
    Button numbers0;
    Button numbers1;
    Button numbers2;
    Button numbers3;
    Button numbers4;
    Button numbers5;
    Button numbers6;
    Button numbers7;
    Button numbers8;
    Button numbers9;
    Button numbersDot;

    Button operatorPlus;
    Button operatorMinus;
    Button operatorDivide;
    Button operatorTimes;

    Button equal;
    Button clear;

    TextView textView;
    String getTextView;
    Double parseTextView;

    Double firstNum;
    Double secondNum;
    //double answerNum;
    Double answerNum;

    String operatorFlag;
    String operatorText;

    boolean numberAppend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void initView(){

        numbers0 = (Button)findViewById(button0);
        numbers1 = (Button)findViewById(R.id.button1);
        numbers2 = (Button)findViewById(R.id.button2);
        numbers3 = (Button)findViewById(R.id.button3);
        numbers4 = (Button)findViewById(R.id.button4);
        numbers5 = (Button)findViewById(R.id.button5);
        numbers6 = (Button)findViewById(R.id.button6);
        numbers7 = (Button)findViewById(R.id.button7);
        numbers8 = (Button)findViewById(R.id.button8);
        numbers9 = (Button)findViewById(R.id.button9);
        numbersDot = (Button)findViewById(R.id.buttonDot);


        operatorDivide = (Button)findViewById(R.id.buttonDivide);
        operatorMinus = (Button)findViewById(R.id.buttonMinus);
        operatorPlus = (Button)findViewById(R.id.buttonPlus);
        operatorTimes = (Button)findViewById(R.id.buttonTimes);

        equal = (Button)findViewById(R.id.buttonEqual);
        clear = (Button)findViewById(R.id.buttonC);

        textView = (TextView)findViewById(R.id.textView);

        numberAppend = false;
        //parseNull = null;

        Log.i(TAG, "initView: 0");

    }

    public void doClear(View view) {
        textView.setText(null);
        answerNum = null;
        firstNum = null;
        secondNum = null;
    }

    public void doEqual(View view){

        //if first num is filled, then show the first num
        // else: if someone pressed = without filling the second
        if (firstNum != null){

            textChangeDouble();

            secondNum = parseTextView;
            Log.i(TAG, "doEqual: secondNum= " + secondNum);

            operatorSwitch3();

            textView.setText(String.valueOf(answerNum));

            //after calculation, clear the second number
            secondNum = null;
            numberAppend = false;
            firstNum = null;


        } else {

            answerNum = firstNum;
            textView.setText(String.valueOf(answerNum));
        }

        //set Answer for all Equals
    }


    public void doNumber(View view){

        Button number = (Button)view;
        String numberText = (number.getText().toString());


        //clear text for the second number.
        if (firstNum != null && numberAppend == false){
            textView.setText(null);
            numberAppend = true;
        }


        switch (numberText){
            case "0":
                textView.append("0");
                break;
            case "1":
                textView.append("1");
                break;
            case "2":
                textView.append("2");
                break;
            case "3":
                textView.append("3");
                break;
            case "4":
                textView.append("4");
                break;
            case "5":
                textView.append("5");
                break;
            case "6":
                textView.append("6");
                break;
            case "7":
                textView.append("7");
                break;
            case "8":
                textView.append("8");
                break;
            case "9":
                textView.append("9");
                break;
            case ".":
                textView.append(".");
                break;
            default:
                Log.i(TAG, "doNumber: number is not showing");
        }

    }

    public void doOperator(View view){

        Button operator = (Button)view;
        operatorText = operator.getText().toString();

            //if first number is filled, do this
            if (firstNum != null){

                textChangeDouble();
                secondNum = parseTextView;
                //if there is a firstNum, then put value to second num
                //and calculate firstNum and secondNum
                Log.i(TAG, "doOperator: this is secondNum =" + secondNum);
                operatorSwitch1();

                //delete value of secondNum and show the calculation
                secondNum = null;
                numberAppend = false;

                Log.i(TAG, "doOperator: anserNum= " + answerNum);

                textView.setText(String.valueOf(answerNum));
                answerNum = null;


            } else {

                //after adding value to firstNum, delete the textView
                //store value to the flag
                textChangeDouble();
                firstNum = parseTextView;
                Log.i(TAG, "doOperator: this is first Num =" + firstNum);
                //textView.setText(null);
                operatorSwitch2 ();

            }
    }

    private void operatorSwitch1() {
        switch (operatorFlag){
            case "+":
                firstNum += secondNum;
                break;
            case "-":
                firstNum -= secondNum;
                break;
            case "*":
                firstNum *= secondNum;
                break;
            case "/":
                firstNum /= secondNum;
                break;
            default:
                Log.i(TAG, "doOperator: Missing");
        }
        answerNum = firstNum;
        operatorSwitch2();
    }


    private void operatorSwitch2() {
        switch (operatorText){
            case "+":
                operatorFlag = "+";
                break;
            case "-":
                operatorFlag = "-";
                break;
            case "*":
                operatorFlag = "*";
                break;
            case "/":
                operatorFlag = "/";
                break;
            default:
                Log.i(TAG, "doOperator: Missing2");
        }
    }

    private void operatorSwitch3() {
        switch (operatorFlag){
            case "+":
                answerNum = firstNum+secondNum;
                break;
            case "-":
                answerNum = firstNum-secondNum;
                break;
            case "*":
                answerNum = firstNum*secondNum;
                break;
            case "/":
                answerNum = firstNum/secondNum;
                break;
            default:
                Log.i(TAG, "doOperator: Missing3");
        }
    }


    public void textChangeDouble() {

        getTextView = textView.getText().toString();
        parseTextView = Double.parseDouble(getTextView);
    }

}
