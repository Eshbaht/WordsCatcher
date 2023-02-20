package eshbaht.app.wordscatcher.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import eshbaht.app.wordscatcher.DataBase.DataBase;
import eshbaht.app.wordscatcher.DataBase.MinLenghtWord.ShortWords;
import eshbaht.app.wordscatcher.DataBase.Player.Player;
import eshbaht.app.wordscatcher.FAQ.AboutGame;
import eshbaht.app.wordscatcher.MainGame.MainGame;
import eshbaht.app.wordscatcher.R;
import eshbaht.app.wordscatcher.Registration.RegistartionScreen;

public class LoginScreen extends AppCompatActivity {


    protected EditText enter_name;
    protected Button start_game, regestry;
    private DataBase dataBase;
    private Intent mainGameScreen;
    private Intent RegistartionScreens;

    private String myName; // имя, которое вводит пользователь, что бы залогиниться
    public String getMyName() {
        return myName;
    }
    public void setMyName(String myName) {
        this.myName = myName;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        dataBase = DataBase.getInstance(getApplication());

        enter_name = findViewById(R.id.enter_name);
        start_game = findViewById(R.id.start_game);
        regestry= findViewById(R.id.regestry);

    }

    public void enterNameAndstartGame(View v) {
        setMyName(enter_name.getText().toString());
        int addd = dataBase.playerDAO().selectPlayerIDByName(getMyName());
        if (dataBase.playerDAO().selectPlayerIDByName(getMyName()) != 0) {

            dataBase.playerDAO().updateReservfield(String.valueOf(addd), 1); //записываем найденный id

            Log.d("qwe", String.valueOf(dataBase.playerDAO().selectPlayerRESERVFIELDByID(1)));

            mainGameScreen = new Intent(this, MainGame.class);
            startActivity(mainGameScreen); //НАЙДЕН УСПЕШНО ЗАПУСКАЕМ ИГРУ
        } else if (dataBase.playerDAO().selectPlayerIDByName(getMyName()) == 0) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Пользователь не найден", Toast.LENGTH_SHORT);
            toast.show();
        }
    }


    public void registartion(View v){
        RegistartionScreens= new Intent(this, RegistartionScreen.class);
        startActivity(RegistartionScreens); //НАЙДЕН УСПЕШНО ЗАПУСКАЕМ ИГРУ
    }


    public void onBackPressed() {
        // super.onBackPressed();
        openQuitDialog();
    }

    private void openQuitDialog() {
        AlertDialog.Builder quitDialog = new AlertDialog.Builder(
                LoginScreen.this);
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



//    Логика:
//
//    кнопка входа и кнопка регистрации
//    Нажимаешь на вход, если не верно ввел имя - появялется кнопка регистрации
//
//    Если зарегался - напдись об успешной региистрации и перербасывает по нажатию ОК на страницу входа.
//
//    ИД пользователя сохрнаяется в переменную. Потом обращафсь к этой переменной из других классов можно узнать ИД юзера
    // Добавить ресайклер вью со спиком всех юзеров и их очков


}