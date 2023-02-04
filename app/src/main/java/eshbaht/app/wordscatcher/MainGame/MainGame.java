package eshbaht.app.wordscatcher.MainGame;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.core.widget.ContentLoadingProgressBar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eshbaht.app.wordscatcher.DataBase.DataBase;
import eshbaht.app.wordscatcher.Menu.Menu;
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

    private RelativeLayout chars_layouts, //слой с кнопками куда попадают собранные буквы. Используется для нажатия подврждения сбора слова
                           engine_buttons; // слой с кнопками меню и сброса
    private MotionLayout Colo;  // слой с двигающимися кнопками

    protected List<String> word_list_shuddle_1; // список букв, перемешенное слово

    private ArrayList<String> MainListWord = new ArrayList<>();// при нажатии кнопки собисрются слово в этот список

    private TextView char_1, char_2, char_3, char_4, char_5, char_6, char_7, char_8, char_9,
            char_10, char_11, char_12, char_13, char_14;// кнопки, на которые назначаются сборанные буквы

    private ProgressBar indicator;
    private TextView  textLvl, //слово уровень
            lvlview, // отображаемый уровень
            textScore, // слово очки
            score, //отображаемые очки
            scoreMax; //максимальные очки для достижения следующегео уровня



    private ContentLoadingProgressBar progressBar; //прогресс бар очков

    private RelativeLayout topLayout, //верхний слой со статистикой
            intod_latout; // слой самой статистики


    int expUser;
    public int getexpUser() {
        return expUser;
    }
    public void setexpUser(int expUser) {
        this.expUser = expUser;
    }

    int lvlUser;
    public int getLvlUser() {
        return lvlUser;
    }
    public void setLvlUser(int lvlUser) {
        this.lvlUser = lvlUser;
    }

    private DataBase dataBase;


    private String curWord;
    public String getCurWord() {
        return curWord;
    }
    public void setCurWord(String curWord) {
        this.curWord = curWord;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        dataBase = DataBase.getInstance(getApplication());

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
        chars_layouts = findViewById(R.id.chars_layouts);
        engine_buttons = findViewById(R.id.engine_buttons);
        topLayout = findViewById(R.id.topLayout);
        Colo = findViewById(R.id.Colo);
        //
        textLvl = findViewById(R.id.textLvl);
        lvlview = findViewById(R.id.lvlview);
        textScore = findViewById(R.id.textScore);
        score = findViewById(R.id.score);
        indicator = findViewById(R.id.indicator);

//        long player_Id_current = (int) dataBase.playerDAO().selectPlayerRESERVFIELDByID1(1); //получаем ИД текущего юзера
//        setCurWord(String.valueOf(dataBase.playerDAO().selectCurrentWordByID(player_Id_current)));

        long curUser = dataBase.playerDAO().selectPlayerRESERVFIELDByID1(1);

        setCurWord(String.valueOf(dataBase.playerDAO().selectCurrentWordByID(curUser)));


        initialing_methods();
    }

    private void initialing_methods(){
        setExpPlayer();
        setLVLPlayer();
        shuffle_word_1();
        SetLiteralsonButtons();
        Log.d("tyu", getCurWord());
        GoneButnnons();
    } //запуск методов при старте активити

    public void LLetsGo_game(View view) {
        SetLiteralsonButtons();
        ShowButtons();
    }

    public void open_menu(View v){
//        OptionMenu();
        Intent collect = new Intent(this, Menu.class);
        startActivity(collect);
    }



    public void Chek_1(View v) {

        MainListWord.clear();
        char_1.setText("");
        char_2.setText("");
        char_3.setText("");
        char_4.setText("");
        char_5.setText("");
        char_6.setText("");
        char_7.setText("");
        char_8.setText("");
        char_9.setText("");
        char_10.setText("");
        char_11.setText("");
        char_12.setText("");
        char_13.setText("");
        char_14.setText("");
    }//проверка собранного слова



    public void ResetField(View v){
        Intent achive = new Intent(this, Achives.class);
        startActivity(achive);
    } //сброс кнопок

    @Override
    protected void onResume() {
        super.onResume();
        shuffle_word_1();
        long player_Id_current = (int) dataBase.playerDAO().selectPlayerRESERVFIELDByID1(1); //получаем ИД текущего юзера
        setCurWord(String.valueOf(dataBase.playerDAO().selectCurrentWordByID(player_Id_current)));
        shuffle_word_1();
        SetLiteralsonButtons();
        Log.d("tyu", getCurWord());
    }


    public void SwitchShow(View v){
        Intent switchMyWords = new Intent(this, SwitchWord.class);
        startActivity(switchMyWords);

    } //экран смены слова


    private void setExpPlayer(){
        long playerId = (int) dataBase.playerDAO().selectPlayerRESERVFIELDByID1(1); //получаем ИД текущего юзера
        String expr = String.valueOf(dataBase.playerDAO().selectPlayerExpByID(playerId)); // получаем ЭКСПУ текущего юзера в Стринг
        Log.d("qwe", "экспа "+expr);
        int expa = Integer.parseInt(expr);// получаем ЭКСПУ текущего юзера в инт

        if (Integer.parseInt(expr)<0){
            setexpUser(0);
        } else {
            setexpUser(Integer.parseInt(expr));
        }
        score.setText(""+getexpUser());
    } //установка очки юзера
    private void setLVLPlayer(){
        long playerId = (int) dataBase.playerDAO().selectPlayerRESERVFIELDByID1(1); //получаем ИД текущего юзера
        String lvl = String.valueOf(dataBase.playerDAO().selectPlayerLvlByID(playerId)); // получаем lvl текущего юзера в Стринг
        int lvla = Integer.parseInt(lvl);// получаем ЭКСПУ текущего юзера в инт
        Log.d("qwe", "лвл " +lvl);
        if (Integer.parseInt(lvl)<0){
            setLvlUser(0);
        } else {
            setLvlUser(Integer.parseInt(lvl));
        }
        lvlview.setText(""+getLvlUser());
    } //установка очки юзера
    private void shuffle_word_1(){
        word_list_shuddle_1 = MyShuffle_1(); //назначение на перемешивание
        Collections.shuffle(word_list_shuddle_1);//присвамвание перемешанных букв
        for (int word_1=0;word_1<word_list_shuddle_1.size();word_1++);
    } //назначение слова 1
    private List<String> MyShuffle_1(){
        String text = takeCurrenword(); //слово
        Pattern pattern = Pattern.compile("\\S{1}"); //разбиваем слово по "1" букве
        Matcher m = pattern.matcher(text); // присваиваем
        List<String> triples = new ArrayList<>();
        while (m.find()) {
            triples.add(m.group());
        } //группировка букв в список
        return triples;
    } //перемешение слова_1
    public String takeCurrenword(){
//        long playerId_current = (int) dataBase.playerDAO().selectPlayerRESERVFIELDByID1(1); //получаем ИД текущего юзера
        String curword = getCurWord();
        return curword;
    } //получение текущего выбранного слова, которое разбивается на буквы
    private int lenghtCurrentWord(){
        long playerId_curr = (int) dataBase.playerDAO().selectPlayerRESERVFIELDByID1(1); //получаем ИД текущего юзера
        String myword = String.valueOf(dataBase.playerDAO().selectCurrentWordByID(playerId_curr)); //получаем текущее слово выбранного юзера.
        int lenghtOfCurrentWord = dataBase.wordMaxDAO().lenghtWords(myword);
        return lenghtOfCurrentWord;
    } // определние длины текущего слова


    public void ClickButton1(View v){
        MainListWord.add(pr1.getText().toString());
        pr1.setBackgroundResource(R.drawable.main_press);
        pr1.setEnabled(false);
        SwitchTextField();
    } //кнопка 1
    public void ClickButton2(View v){
        MainListWord.add(pr2.getText().toString());
        pr2.setBackgroundResource(R.drawable.main_press);
        pr2.setEnabled(false);
        SwitchTextField();
    } //кнопка 2
    public void ClickButton3(View v){
        MainListWord.add(pr3.getText().toString());
        pr3.setBackgroundResource(R.drawable.main_press);
        pr3.setEnabled(false);
        SwitchTextField();
    } //кнопка 3
    public void ClickButton4(View v){
        MainListWord.add(pr4.getText().toString());
        pr4.setBackgroundResource(R.drawable.main_press);
        pr4.setEnabled(false);
        SwitchTextField();
    } //кнопка 4
    public void ClickButton5(View v){
        MainListWord.add(pr5.getText().toString());
        pr5.setBackgroundResource(R.drawable.main_press);
        pr5.setEnabled(false);
        SwitchTextField();
    } //кнопка 5
    public void ClickButton6(View v){
        MainListWord.add(pr6.getText().toString());
        pr6.setBackgroundResource(R.drawable.main_press);
        pr6.setEnabled(false);
        SwitchTextField();
    } //кнопка 6
    public void ClickButton7(View v){
        MainListWord.add(pr7.getText().toString());
        pr7.setBackgroundResource(R.drawable.main_press);
        pr7.setEnabled(false);
        SwitchTextField();
    } //кнопка 7
    public void ClickButton8(View v){
        MainListWord.add(pr8.getText().toString());
        pr8.setBackgroundResource(R.drawable.main_press);
        pr8.setEnabled(false);
        SwitchTextField();
    } //кнопка 8
    public void ClickButton9(View v){
        MainListWord.add(pr9.getText().toString());
        pr9.setBackgroundResource(R.drawable.main_press);
        pr9.setEnabled(false);
        SwitchTextField();
    } //кнопка 9
    public void ClickButton10(View v){
        MainListWord.add(pr10.getText().toString());
        pr10.setBackgroundResource(R.drawable.main_press);
        pr10.setEnabled(false);
        SwitchTextField();
    } //кнопка 10
    public void ClickButton11(View v){
        MainListWord.add(pr11.getText().toString());
        pr11.setBackgroundResource(R.drawable.main_press);
        pr11.setEnabled(false);
        SwitchTextField();
    } //кнопка 11
    public void ClickButton12(View v){
        MainListWord.add(pr12.getText().toString());
        pr12.setBackgroundResource(R.drawable.main_press);
        pr12.setEnabled(false);
        SwitchTextField();
    } //кнопка 12
    public void ClickButton13(View v){
        MainListWord.add(pr13.getText().toString());
        pr13.setBackgroundResource(R.drawable.main_press);
        pr13.setEnabled(false);
        SwitchTextField();
    } //кнопка 13
    public void ClickButton14(View v){
        MainListWord.add(pr14.getText().toString());
        pr14.setBackgroundResource(R.drawable.main_press);
        pr14.setEnabled(false);
        SwitchTextField();
    } //кнопка 14



    private void SetLiteralsonButtons(){
        switch (lenghtCurrentWord()) {
            case 11:
                pr1.setText(String.valueOf(word_list_shuddle_1.get(0)));
                pr2.setText(String.valueOf(word_list_shuddle_1.get(1)));
                pr3.setText(String.valueOf(word_list_shuddle_1.get(2)));
                pr4.setText(String.valueOf(word_list_shuddle_1.get(3)));
                pr5.setText(String.valueOf(word_list_shuddle_1.get(4)));
                pr6.setText(String.valueOf(word_list_shuddle_1.get(5)));
                pr7.setText(String.valueOf(word_list_shuddle_1.get(6)));
                pr8.setText(String.valueOf(word_list_shuddle_1.get(7)));
                pr9.setText(String.valueOf(word_list_shuddle_1.get(8)));
                pr10.setText(String.valueOf(word_list_shuddle_1.get(9)));
                pr11.setText(String.valueOf(word_list_shuddle_1.get(10)));
                pr12.setVisibility(GONE);
                pr13.setVisibility(GONE);
                pr14.setVisibility(GONE);
                break;
            case 14:
                pr12.setVisibility(VISIBLE);
                pr13.setVisibility(VISIBLE);
                pr14.setVisibility(VISIBLE);
                pr1.setText(String.valueOf(word_list_shuddle_1.get(0)));
                pr2.setText(String.valueOf(word_list_shuddle_1.get(1)));
                pr3.setText(String.valueOf(word_list_shuddle_1.get(2)));
                pr4.setText(String.valueOf(word_list_shuddle_1.get(3)));
                pr5.setText(String.valueOf(word_list_shuddle_1.get(4)));
                pr6.setText(String.valueOf(word_list_shuddle_1.get(5)));
                pr7.setText(String.valueOf(word_list_shuddle_1.get(6)));
                pr8.setText(String.valueOf(word_list_shuddle_1.get(7)));
                pr9.setText(String.valueOf(word_list_shuddle_1.get(8)));
                pr10.setText(String.valueOf(word_list_shuddle_1.get(9)));
                pr11.setText(String.valueOf(word_list_shuddle_1.get(10)));
                pr12.setText(String.valueOf(word_list_shuddle_1.get(11)));
                pr13.setText(String.valueOf(word_list_shuddle_1.get(12)));
                pr14.setText(String.valueOf(word_list_shuddle_1.get(13)));
                break;
        }
    } //назначаем буквы основного слова на кнопки

    public void GoneButnnons(){
        Colo.setVisibility(GONE);
        topLayout.setVisibility(GONE);
        chars_layouts.setVisibility(GONE);
        engine_buttons.setVisibility(GONE);
        //-
        pr1.setVisibility(GONE);
        pr2.setVisibility(GONE);
        pr3.setVisibility(GONE);
        pr4.setVisibility(GONE);
        pr5.setVisibility(GONE);
        pr6.setVisibility(GONE);
        pr7.setVisibility(GONE);
        pr8.setVisibility(GONE);
        pr9.setVisibility(GONE);
        pr10.setVisibility(GONE);
        pr11.setVisibility(GONE);
        pr12.setVisibility(GONE);
        pr13.setVisibility(GONE);
        pr14.setVisibility(GONE);
            char_1.setVisibility(GONE);
            char_2.setVisibility(GONE);
            char_3.setVisibility(GONE);
            char_4.setVisibility(GONE);
            char_5.setVisibility(GONE);
            char_6.setVisibility(GONE);
            char_7.setVisibility(GONE);
            char_8.setVisibility(GONE);
            char_9.setVisibility(GONE);
            char_10.setVisibility(GONE);
            char_11.setVisibility(GONE);
            char_12.setVisibility(GONE);
            char_13.setVisibility(GONE);
            char_14.setVisibility(GONE);
    } //убрать кнопки с экрана на которые назначаются буквы и кнопки на которые попадают выбранные буквы

    private void ShowButtons(){
        Colo.setVisibility(VISIBLE);
        topLayout.setVisibility(VISIBLE);
        chars_layouts.setVisibility(VISIBLE);
        engine_buttons.setVisibility(VISIBLE);
        switch (lenghtCurrentWord()){
            case 11:
                pr1.setVisibility(VISIBLE);
                pr2.setVisibility(VISIBLE);
                pr3.setVisibility(VISIBLE);
                pr4.setVisibility(VISIBLE);
                pr5.setVisibility(VISIBLE);
                pr6.setVisibility(VISIBLE);
                pr7.setVisibility(VISIBLE);
                pr8.setVisibility(VISIBLE);
                pr9.setVisibility(VISIBLE);
                pr10.setVisibility(VISIBLE);
                pr11.setVisibility(VISIBLE);
                break;
            case 14:
                pr1.setVisibility(VISIBLE);
                pr2.setVisibility(VISIBLE);
                pr3.setVisibility(VISIBLE);
                pr4.setVisibility(VISIBLE);
                pr5.setVisibility(VISIBLE);
                pr6.setVisibility(VISIBLE);
                pr7.setVisibility(VISIBLE);
                pr8.setVisibility(VISIBLE);
                pr9.setVisibility(VISIBLE);
                pr10.setVisibility(VISIBLE);
                pr11.setVisibility(VISIBLE);
                pr12.setVisibility(VISIBLE);
                pr13.setVisibility(VISIBLE);
                pr14.setVisibility(VISIBLE);
                break;
        }
        starter.setVisibility(GONE);
        char_1.setVisibility(VISIBLE);
        char_2.setVisibility(VISIBLE);
        char_3.setVisibility(VISIBLE);
    } // показ кнопок на экране

    protected void SwitchTextField(){
        if (MainListWord.size() == 1){
            char_1.setText(MainListWord.get(0));
            char_2.setText("");
            char_3.setText("");
            char_4.setText("");
            char_5.setText("");
            char_6.setText("");
            char_7.setText("");
            char_8.setText("");
            char_9.setText("");
            char_10.setText("");
            char_11.setText("");
            char_12.setText("");
            char_13.setText("");
            char_14.setText("");
        } else if (MainListWord.size() == 2){
            char_1.setText(MainListWord.get(0));
            char_2.setText(MainListWord.get(1));
            char_3.setText("");
            char_4.setText("");
            char_5.setText("");
            char_6.setText("");
            char_7.setText("");
            char_8.setText("");
            char_9.setText("");
            char_10.setText("");
            char_11.setText("");
            char_12.setText("");
            char_13.setText("");
            char_14.setText("");
        } else if (MainListWord.size() == 3){
            char_1.setText(MainListWord.get(0));
            char_2.setText(MainListWord.get(1));
            char_3.setText(MainListWord.get(2));
            char_4.setText("");
            char_5.setText("");
            char_6.setText("");
            char_7.setText("");
            char_8.setText("");
            char_9.setText("");
            char_10.setText("");
            char_11.setText("");
            char_12.setText("");
            char_13.setText("");
            char_14.setText("");
            char_4.setVisibility(VISIBLE);
        } else if (MainListWord.size() == 4){
            char_1.setText(MainListWord.get(0));
            char_2.setText(MainListWord.get(1));
            char_3.setText(MainListWord.get(2));
            char_4.setText(MainListWord.get(3));
            char_5.setText("");
            char_6.setText("");
            char_7.setText("");
            char_8.setText("");
            char_9.setText("");
            char_10.setText("");
            char_11.setText("");
            char_12.setText("");
            char_13.setText("");
            char_14.setText("");
            char_5.setVisibility(VISIBLE);
        } else if (MainListWord.size() == 5){
            char_1.setText(MainListWord.get(0));
            char_2.setText(MainListWord.get(1));
            char_3.setText(MainListWord.get(2));
            char_4.setText(MainListWord.get(3));
            char_5.setText(MainListWord.get(4));
            char_6.setText("");
            char_7.setText("");
            char_8.setText("");
            char_9.setText("");
            char_10.setText("");
            char_11.setText("");
            char_12.setText("");
            char_13.setText("");
            char_14.setText("");
            char_6.setVisibility(VISIBLE);
        } else if (MainListWord.size() == 6){
            char_1.setText(MainListWord.get(0));
            char_2.setText(MainListWord.get(1));
            char_3.setText(MainListWord.get(2));
            char_4.setText(MainListWord.get(3));
            char_5.setText(MainListWord.get(4));
            char_6.setText(MainListWord.get(5));
            char_7.setText("");
            char_8.setText("");
            char_9.setText("");
            char_10.setText("");
            char_11.setText("");
            char_12.setText("");
            char_13.setText("");
            char_14.setText("");
            char_7.setVisibility(VISIBLE);
        } else if (MainListWord.size() == 7){
            char_1.setText(MainListWord.get(0));
            char_2.setText(MainListWord.get(1));
            char_3.setText(MainListWord.get(2));
            char_4.setText(MainListWord.get(3));
            char_5.setText(MainListWord.get(4));
            char_6.setText(MainListWord.get(5));
            char_7.setText(MainListWord.get(6));
            char_8.setText("");
            char_9.setText("");
            char_10.setText("");
            char_11.setText("");
            char_12.setText("");
            char_13.setText("");
            char_14.setText("");
            char_8.setVisibility(VISIBLE);
        } else if (MainListWord.size() == 8){
            char_1.setText(MainListWord.get(0));
            char_2.setText(MainListWord.get(1));
            char_3.setText(MainListWord.get(2));
            char_4.setText(MainListWord.get(3));
            char_5.setText(MainListWord.get(4));
            char_6.setText(MainListWord.get(5));
            char_7.setText(MainListWord.get(6));
            char_8.setText(MainListWord.get(7));
            char_9.setText("");
            char_10.setText("");
            char_11.setText("");
            char_12.setText("");
            char_13.setText("");
            char_14.setText("");
            char_9.setVisibility(VISIBLE);
        } else if (MainListWord.size() == 9){
            char_1.setText(MainListWord.get(0));
            char_2.setText(MainListWord.get(1));
            char_3.setText(MainListWord.get(2));
            char_4.setText(MainListWord.get(3));
            char_5.setText(MainListWord.get(4));
            char_6.setText(MainListWord.get(5));
            char_7.setText(MainListWord.get(6));
            char_8.setText(MainListWord.get(7));
            char_9.setText(MainListWord.get(8));
            char_10.setText("");
            char_11.setText("");
            char_12.setText("");
            char_13.setText("");
            char_14.setText("");
            char_10.setVisibility(VISIBLE);
        }else if (MainListWord.size() == 10){
            char_1.setText(MainListWord.get(0));
            char_2.setText(MainListWord.get(1));
            char_3.setText(MainListWord.get(2));
            char_4.setText(MainListWord.get(3));
            char_5.setText(MainListWord.get(4));
            char_6.setText(MainListWord.get(5));
            char_7.setText(MainListWord.get(6));
            char_8.setText(MainListWord.get(7));
            char_9.setText(MainListWord.get(8));
            char_10.setText(MainListWord.get(9));
            char_11.setText("");
            char_12.setText("");
            char_13.setText("");
            char_14.setText("");
            char_11.setVisibility(VISIBLE);
        } else if (MainListWord.size() == 11){
            char_1.setText(MainListWord.get(0));
            char_2.setText(MainListWord.get(1));
            char_3.setText(MainListWord.get(2));
            char_4.setText(MainListWord.get(3));
            char_5.setText(MainListWord.get(4));
            char_6.setText(MainListWord.get(5));
            char_7.setText(MainListWord.get(6));
            char_8.setText(MainListWord.get(7));
            char_9.setText(MainListWord.get(8));
            char_10.setText(MainListWord.get(9));
            char_11.setText(MainListWord.get(10));
            char_12.setText("");
            char_13.setText("");
            char_14.setText("");
            char_12.setVisibility(VISIBLE);
        } else if (MainListWord.size() == 12){
            char_1.setText(MainListWord.get(0));
            char_2.setText(MainListWord.get(1));
            char_3.setText(MainListWord.get(2));
            char_4.setText(MainListWord.get(3));
            char_5.setText(MainListWord.get(4));
            char_6.setText(MainListWord.get(5));
            char_7.setText(MainListWord.get(6));
            char_8.setText(MainListWord.get(7));
            char_9.setText(MainListWord.get(8));
            char_10.setText(MainListWord.get(9));
            char_11.setText(MainListWord.get(10));
            char_12.setText(MainListWord.get(11));
            char_13.setText("");
            char_14.setText("");
            char_13.setVisibility(VISIBLE);
        } else if (MainListWord.size() == 13){
            char_1.setText(MainListWord.get(0));
            char_2.setText(MainListWord.get(1));
            char_3.setText(MainListWord.get(2));
            char_4.setText(MainListWord.get(3));
            char_5.setText(MainListWord.get(4));
            char_6.setText(MainListWord.get(5));
            char_7.setText(MainListWord.get(6));
            char_8.setText(MainListWord.get(7));
            char_9.setText(MainListWord.get(8));
            char_10.setText(MainListWord.get(9));
            char_11.setText(MainListWord.get(10));
            char_12.setText(MainListWord.get(11));
            char_13.setText(MainListWord.get(12));
            char_14.setText("");
            char_14.setVisibility(VISIBLE);
        } else if (MainListWord.size() == 14){
            char_1.setText(MainListWord.get(0));
            char_2.setText(MainListWord.get(1));
            char_3.setText(MainListWord.get(2));
            char_4.setText(MainListWord.get(3));
            char_5.setText(MainListWord.get(4));
            char_6.setText(MainListWord.get(5));
            char_7.setText(MainListWord.get(6));
            char_8.setText(MainListWord.get(7));
            char_9.setText(MainListWord.get(8));
            char_10.setText(MainListWord.get(9));
            char_11.setText(MainListWord.get(10));
            char_12.setText(MainListWord.get(11));
            char_13.setText(MainListWord.get(12));
            char_14.setText(MainListWord.get(13));
            char_14.setVisibility(VISIBLE);
        }
    } // изменение флага печати текста

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