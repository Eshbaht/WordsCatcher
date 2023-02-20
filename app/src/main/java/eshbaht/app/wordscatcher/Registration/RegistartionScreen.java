package eshbaht.app.wordscatcher.Registration;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import eshbaht.app.wordscatcher.DataBase.DataBase;
import eshbaht.app.wordscatcher.Login.LoginScreen;
import eshbaht.app.wordscatcher.MainGame.MainGame;
import eshbaht.app.wordscatcher.MySettings.MySettings;
import eshbaht.app.wordscatcher.R;

public class RegistartionScreen extends AppCompatActivity {

    private Intent loginScreen;
    private DataBase dataBase;
    private EditText reg_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registartion_screen);

        reg_name = findViewById(R.id.reg_name);
        dataBase = DataBase.getInstance(getApplication());
    }


    public void backToLogin(View v){
        String myName = reg_name.getText().toString();
        int asd = dataBase.playerDAO().selectPlayerIDByName(myName);
        Log.d("tyu", ""+asd);
        if (dataBase.playerDAO().selectPlayerIDByName(myName) !=0){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Такой юзер уже есть", Toast.LENGTH_SHORT);
            toast.show();
        } else if(reg_name.getText().toString().length()<2){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Ваше имя должно быть минимум 2 символа", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            dataBase.playerDAO().insertName(myName, "0", "0", "котлисаслон");
            loginScreen = new Intent(this, LoginScreen.class);
            startActivity(loginScreen);
        }
    }

    public void onBackPressed() {
        // super.onBackPressed();
        openQuitDialog();
    }

    private void openQuitDialog() {
        AlertDialog.Builder quitDialog = new AlertDialog.Builder(
                RegistartionScreen.this);
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