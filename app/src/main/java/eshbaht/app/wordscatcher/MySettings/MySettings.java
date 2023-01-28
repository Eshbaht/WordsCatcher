package eshbaht.app.wordscatcher.MySettings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.SwitchCompat;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import eshbaht.app.wordscatcher.MainGame.MainGame;
import eshbaht.app.wordscatcher.R;

public class MySettings extends AppCompatActivity {


    AppCompatButton back, change_my_acount, my_avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_settings);

            back= findViewById(R.id.back);
            change_my_acount= findViewById(R.id.change_my_acount);
            my_avatar= findViewById(R.id.my_avatar);
    }

    public void notNow(View v){
        Toast toast = Toast.makeText(this, "В разработке!",Toast.LENGTH_SHORT);
        toast.show();
    }


    public void sentRecent(View v){
        Intent telegram = new Intent(Intent.ACTION_VIEW , Uri.parse("https://t.me/Freijer"));
        startActivity(telegram);
    }

    public void Back(View v){
        Intent intentBack = new Intent(this, MainGame.class);
        startActivity(intentBack);
    }

}