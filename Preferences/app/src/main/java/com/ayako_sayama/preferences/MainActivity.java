package com.ayako_sayama.preferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import static com.ayako_sayama.preferences.R.id.settings;

public class MainActivity extends AppCompatActivity {

    SharedPreferences preferences;
    //shared preferences among different activities
    SharedPreferences colorsPref;


    public static final String SHARED_PREF = "com.ayako_sayama.preferences.Settings";
    public static final String SHARED_PREF2 = "com.ayako_sayama.preferences.Colors";


    RelativeLayout relative;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        RelativeLayout rl = (RelativeLayout)findViewById(R.id.activity_main);
        preferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        colorsPref = getSharedPreferences(SHARED_PREF2, MODE_PRIVATE);

        boolean isDarkTheme = preferences.getBoolean("dark_theme",false);


        //you must set the theme before onCreate, you cant't do onResume
//        if (isDarkTheme){
//
//            setTheme(R.style.AppThemeDark);
//            //theme_black already exists
//
//
//        } else{
//            setTheme(R.style.AppTheme);
//
//        }


        //check pref file(xml) for dark
        //if dark theme is true
        //make it dark
        //make it light
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    protected void onResume() {

        boolean isDarkTheme2 = colorsPref.getBoolean("dark_theme2",false);
        super.onResume();
        relative = (RelativeLayout)findViewById(R.id.activity_main);

        if (isDarkTheme2){

            relative.setBackgroundColor(Color.BLACK);

        } else{
            relative.setBackgroundColor(Color.WHITE);
        }
    }

    @Override

    //calls the header menu button
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case settings:
                startActivity(new Intent(this,Settings.class));
                //Toast.makeText(this, "HI!!", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }


}
