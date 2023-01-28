package eshbaht.app.wordscatcher.MainGame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.core.widget.ContentLoadingProgressBar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import eshbaht.app.wordscatcher.MyAchives.Achives;
import eshbaht.app.wordscatcher.MyCollection.MyCollectionWords;
import eshbaht.app.wordscatcher.R;
import eshbaht.app.wordscatcher.StartGame.StartGame;
import eshbaht.app.wordscatcher.SwitchWords.SwitchWord;

public class MainGame extends AppCompatActivity {


    private AppCompatButton pr1, pr2, pr3, pr4, pr5, pr6, pr7, pr8, pr9, pr10, pr11, pr12, pr13, pr14; //кнопки движущиеся по экрану
    private AppCompatButton menu_pause, //переход в меню
                            resetWord, // сбросить слово
                            reset; //сменить слово
    private AppCompatButton starter; // основная кнопка начать игру

    private RelativeLayout chars_layout, //слой с кнопками куда попадают собранные буквы. Используется для нажатия подврждения сбора слова
                           engine_buttons; // слой с кнопками меню и сброса
    private MotionLayout Colo;  // слой с двигающимися кнопками

    private TextView char_1, char_2, char_3, char_4, char_5, char_6, char_7, char_8, char_9,
            char_10, char_11, char_12, char_13, char_14;// кнопки, на которые назначаются сборанные буквы

    private TextView  textLvl, //слово уровень
            lvlview, // отображаемый уровень
            textScore, // слово очки
            score, //отображаемые очки
            scoreMax; //максимальные очки для достижения следующегео уровня

    private ContentLoadingProgressBar progressBar; //прогресс бар очков

    private RelativeLayout topLayout, //верхний слой со статистикой
            intod_latout; // слой самой статистики



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        pr1 = findViewById(R.id.pr1);
        pr2 = findViewById(R.id.pr2);
        pr3 = findViewById(R.id.pr3);
        pr4 = findViewById(R.id.pr4);
        pr5 = findViewById(R.id.pr5);
        pr6 = findViewById(R.id.pr6);
        pr7 = findViewById(R.id.pr7);
        pr8 = findViewById(R.id.pr8);
        pr9 = findViewById(R.id.pr9);
        pr10 = findViewById(R.id.pr10);
        pr11 = findViewById(R.id.pr11);
        pr12 = findViewById(R.id.pr12);
        pr13 = findViewById(R.id.pr13);
        pr14 = findViewById(R.id.pr14);
        //
        char_1 = findViewById(R.id.char_1);
        char_2 = findViewById(R.id.char_2);
        char_3 = findViewById(R.id.char_3);
        char_4 = findViewById(R.id.char_4);
        char_5 = findViewById(R.id.char_5);
        char_6 = findViewById(R.id.char_6);
        char_7 = findViewById(R.id.char_7);
        char_8 = findViewById(R.id.char_8);
        char_9 = findViewById(R.id.char_9);
        char_10 = findViewById(R.id.char_10);
        char_11 = findViewById(R.id.char_11);
        char_12 = findViewById(R.id.char_12);
        char_13 = findViewById(R.id.char_13);
        char_14 = findViewById(R.id.char_14);
        //
        menu_pause = findViewById(R.id.menu_pause);
        resetWord = findViewById(R.id.resetWord);
        reset = findViewById(R.id.reset);
        starter = findViewById(R.id.starter);
        //
        chars_layout = findViewById(R.id.chars_layout);
        engine_buttons = findViewById(R.id.engine_buttons);
        topLayout = findViewById(R.id.topLayout);
        Colo = findViewById(R.id.Colo);
        intod_latout = findViewById(R.id.intod_latout);
        //
        textLvl = findViewById(R.id.textLvl);
        lvlview = findViewById(R.id.lvlview);
        textScore = findViewById(R.id.textScore);
        score = findViewById(R.id.score);
        scoreMax = findViewById(R.id.scoreMax);
        progressBar = findViewById(R.id.progressBar);


    }

    public void LetsGo(View v){
//        Lets_Go();
    }

    public void open_menu(View v){
//        OptionMenu();
        Intent collect = new Intent(this, MyCollectionWords.class);
        startActivity(collect);
    }


    public void ClickButton1(View v){
    } //кнопка 1
    public void ClickButton2(View v){
    } //кнопка 2
    public void ClickButton3(View v){
    } //кнопка 3
    public void ClickButton4(View v){
    } //кнопка 4
    public void ClickButton5(View v){
    } //кнопка 5
    public void ClickButton6(View v){
    } //кнопка 6
    public void ClickButton7(View v){
    } //кнопка 7
    public void ClickButton8(View v){
    } //кнопка 8
    public void ClickButton9(View v){
    } //кнопка 9
    public void ClickButton10(View v){
    } //кнопка 10
    public void ClickButton11(View v){
    } //кнопка 11
    public void ClickButton12(View v){
    } //кнопка 12
    public void ClickButton13(View v){
    } //кнопка 13
    public void ClickButton14(View v){
    } //кнопка 14

    public void Chek_1(View v) {
    }//проверка собранного слова

    public void ResetField(View v){
        Intent achive = new Intent(this, Achives.class);
        startActivity(achive);
    } //сброс кнопок


    public void SwitchShow(View v){
        Intent switchMyWords = new Intent(this, SwitchWord.class);
        startActivity(switchMyWords);

    } //экран смены слова



//    @Override
//    public void onBackPressed() {
//        new AlertDialog.Builder(this)
//                .setTitle("Выйти из приложения на главный жкран?")
//                .setMessage("Вы действительно хотите выйти?")
//                .setNegativeButton(android.R.string.no, null)
//                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface arg0, int arg1) {
//                        Intent intent_back = new Intent(MainGame.this, StartGame.class);
//                        startActivity(intent_back);
//                        finish();
//                    }
//                }).create().show();
//    } //переназнчание кнопки назад
}