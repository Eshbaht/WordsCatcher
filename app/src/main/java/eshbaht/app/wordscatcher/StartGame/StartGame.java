package eshbaht.app.wordscatcher.StartGame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

import eshbaht.app.wordscatcher.MainGame.MainGame;
import eshbaht.app.wordscatcher.MySettings.MySettings;
import eshbaht.app.wordscatcher.R;

public class StartGame extends AppCompatActivity {
    private Intent startGame;
    private Intent settings;

    AppCompatButton play, settings_game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);

        play = findViewById(R.id.play);
        settings_game = findViewById(R.id.settings_game);
    }

    public void Play(View v) {
        startGame = new Intent(this, MainGame.class);
        startActivity(startGame);
    }

    public void Settings(View v) {
        settings = new Intent(this, MySettings.class);
        startActivity(settings);
    }


}