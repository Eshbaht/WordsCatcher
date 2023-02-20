package eshbaht.app.wordscatcher.StartGame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

import eshbaht.app.wordscatcher.MainGame.MainGame;
import eshbaht.app.wordscatcher.MySettings.MySettings;
import eshbaht.app.wordscatcher.R;
import eshbaht.app.wordscatcher.Registration.RegistartionScreen;

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


    public void onBackPressed() {
        // super.onBackPressed();
        openQuitDialog();
    }

    private void openQuitDialog() {
        AlertDialog.Builder quitDialog = new AlertDialog.Builder(
                StartGame.this);
        quitDialog.setTitle("Выход: Вы уверены?");

        quitDialog.setPositiveButton("Да =(", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        quitDialog.setNegativeButton("Нет =)", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
            }
        });

        quitDialog.show();
    }

}