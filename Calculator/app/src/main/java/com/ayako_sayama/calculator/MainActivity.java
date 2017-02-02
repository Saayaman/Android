package com.ayako_sayama.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.text.NumberFormat;

public class MainActivity extends Activity {
    //action bar disappears if you put Activity instead of AppCompatibility


    private static final String TAG = "mainActivity" ;
    CustomButtonView numbers0;
    CustomButtonView numbers1;
    CustomButtonView numbers2;
    CustomButtonView numbers3;
    CustomButtonView numbers4;
    CustomButtonView numbers5;
    CustomButtonView numbers6;
    CustomButtonView numbers7;
    CustomButtonView numbers8;
    CustomButtonView numbers9;
    CustomButtonView numbersDot;

    CustomButtonView operatorPlus;
    CustomButtonView operatorMinus;
    CustomButtonView operatorDivide;
    CustomButtonView operatorTimes;

    CustomButtonView equal;
    CustomButtonView clear;

    CustomTextView textView;
    CustomTextView calculation;

    String getTextView;
    Double parseTextView;

    Double firstNum;
    Double secondNum;
    Double answerNum;
    String calViewAnswerNum;


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

        numbers0 = (CustomButtonView)findViewById(R.id.button0);
        numbers1 = (CustomButtonView)findViewById(R.id.button1);
        numbers2 = (CustomButtonView)findViewById(R.id.button2);
        numbers3 = (CustomButtonView)findViewById(R.id.button3);
        numbers4 = (CustomButtonView)findViewById(R.id.button4);
        numbers5 = (CustomButtonView)findViewById(R.id.button5);
        numbers6 = (CustomButtonView)findViewById(R.id.button6);
        numbers7 = (CustomButtonView)findViewById(R.id.button7);
        numbers8 = (CustomButtonView)findViewById(R.id.button8);
        numbers9 = (CustomButtonView)findViewById(R.id.button9);
        numbersDot = (CustomButtonView)findViewById(R.id.buttonDot);


        operatorDivide = (CustomButtonView)findViewById(R.id.buttonDivide);
        operatorMinus = (CustomButtonView)findViewById(R.id.buttonMinus);
        operatorPlus = (CustomButtonView)findViewById(R.id.buttonPlus);
        operatorTimes = (CustomButtonView)findViewById(R.id.buttonTimes);

        equal = (CustomButtonView)findViewById(R.id.buttonEqual);
        clear = (CustomButtonView)findViewById(R.id.buttonC);

        textView = (CustomTextView) findViewById(R.id.textView);
        calculation =(CustomTextView) findViewById(R.id.calculation);

        numberAppend = false;
    }

    public void doClear(View view) {
        textView.setText(null);
        answerNum = null;
        firstNum = null;
        secondNum = null;
        calculation.setText(null);
        calViewAnswerNum = null;
        operatorFlag = null;
    }

    public void doEqual(View view){

        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(10);
        //if first num is filled, then show the first num
        // else: if someone pressed = without filling the second
        if (firstNum != null){

            textChangeDouble();

            secondNum = parseTextView;
            operatorSwitch3();

            //setting texts on screens
            calculation.setText("= " + answerNum + " ");
            textView.setText(nf.format(answerNum) );

            //after calculation, clear the second number
            secondNum = null;
            numberAppend = false;
            firstNum = null;


        } else {

            //if you press = after first number, you get the first number
            answerNum = firstNum;
            textView.setText(answerNum  + " ");
        }
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
                calculation.append("0");
                break;
            case "1":
                textView.append("1");
                calculation.append("1");
                break;
            case "2":
                textView.append("2");
                calculation.append("2");
                break;
            case "3":
                textView.append("3");
                calculation.append("3");
                break;
            case "4":
                textView.append("4");
                calculation.append("4");
                break;
            case "5":
                textView.append("5");
                calculation.append("5");
                break;
            case "6":
                textView.append("6");
                calculation.append("6");
                break;
            case "7":
                textView.append("7");
                calculation.append("7");
                break;
            case "8":
                textView.append("8");
                calculation.append("8");
                break;
            case "9":
                textView.append("9");
                calculation.append("9");
                break;
            case ".":
                textView.append(".");
                calculation.append(".");
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

                calculation.setText("= " + answerNum + " " + operatorFlag + " ");


                //delete value of secondNum and show the calculation
                secondNum = null;
                numberAppend = false;


                textView.setText(String.valueOf(answerNum));
                calViewAnswerNum = (answerNum + " " + operatorFlag + " ").toString();
                answerNum = null;

            } else {

                //after adding value to firstNum, delete the textView
                //store value to the flag
                textChangeDouble();
                firstNum = parseTextView;

                calculation.setText(firstNum.toString() + " ");
                operatorSwitch2 ();
                calculation.append(operatorFlag + " ");

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
