package com.ayako_sayama.mycustombroadcast;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText editText;
    private static final String TAG = "broadcast";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.sendIntent);
        editText = (EditText)findViewById(R.id.editText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sendString = editText.getText().toString();
                Log.i(TAG, "onClick: " + sendString);

                Intent intent = new Intent("com.ayako_sayama.android.SEND_STRING");
                intent.putExtra("data",sendString);
                Log.i(TAG, "intent: "+intent);
                sendBroadcast(intent);
            }
        });


    }
}
