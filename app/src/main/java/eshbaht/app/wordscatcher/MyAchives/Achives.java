package eshbaht.app.wordscatcher.MyAchives;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import eshbaht.app.wordscatcher.DataBase.DataBase;
import eshbaht.app.wordscatcher.DataBase.MinLenghtWord.ShortWords;
import eshbaht.app.wordscatcher.DataBase.Player.Params.PlayerReserv;
import eshbaht.app.wordscatcher.DataBase.Player.Player;
import eshbaht.app.wordscatcher.Login.LoginScreen;
import eshbaht.app.wordscatcher.MainActivity;
import eshbaht.app.wordscatcher.R;

public class Achives extends AppCompatActivity {

    private TextView word_stat_max_all, word_stat_max_3, word_stat_max_4, word_stat_max_5, word_stat_max_6, word_stat_max_7, word_stat_max_8, word_stat_max_9, word_stat_max_10,
            word_stat_max_11, word_stat_max_12, word_stat_max_13, word_stat_max_14; //значения максимальной длинны слова
    private TextView  word_stat_lit_all, word_stat_lit_3, word_stat_lit_4, word_stat_lit_5, word_stat_lit_6, word_stat_lit_7, word_stat_lit_8, word_stat_lit_9,
            word_stat_lit_10, word_stat_lit_11, word_stat_lit_12, word_stat_lit_13, word_stat_lit_14; //колличество собранных слов этой длинны

    private Player player;
    private DataBase dataBase;
    private ShortWords shortWords;

    private String currentWord;
    private String currentUser;

    private String maxWord_3,  maxWord_4,  maxWord_5,  maxWord_6,  maxWord_7,  maxWord_8,  maxWord_9,  maxWord_10,  maxWord_11,  maxWord_12,
            maxWord_13,  maxWord_14, maxWord_max;

    public String getMaxWord_max() {
        return maxWord_max;
    }
    public void setMaxWord_max(String maxWord_max) {
        this.maxWord_max = maxWord_max;
    }
    public String getMaxWord_3() {
        return maxWord_3;
    }
    public void setMaxWord_3(String maxWord_3) {
        this.maxWord_3 = maxWord_3;
    }
    public String getMaxWord_4() {
        return maxWord_4;
    }
    public void setMaxWord_4(String maxWord_4) {
        this.maxWord_4 = maxWord_4;
    }
    public String getMaxWord_5() {
        return maxWord_5;
    }
    public void setMaxWord_5(String maxWord_5) {
        this.maxWord_5 = maxWord_5;
    }
    public String getMaxWord_6() {
        return maxWord_6;
    }
    public void setMaxWord_6(String maxWord_6) {
        this.maxWord_6 = maxWord_6;
    }
    public String getMaxWord_7() {
        return maxWord_7;
    }
    public void setMaxWord_7(String maxWord_7) {
        this.maxWord_7 = maxWord_7;
    }
    public String getMaxWord_8() {
        return maxWord_8;
    }
    public void setMaxWord_8(String maxWord_8) {
        this.maxWord_8 = maxWord_8;
    }
    public String getMaxWord_9() {
        return maxWord_9;
    }
    public void setMaxWord_9(String maxWord_9) {
        this.maxWord_9 = maxWord_9;
    }
    public String getMaxWord_10() {
        return maxWord_10;
    }
    public void setMaxWord_10(String maxWord_10) {
        this.maxWord_10 = maxWord_10;
    }
    public String getMaxWord_11() {
        return maxWord_11;
    }
    public void setMaxWord_11(String maxWord_11) {
        this.maxWord_11 = maxWord_11;
    }
    public String getMaxWord_12() {
        return maxWord_12;
    }
    public void setMaxWord_12(String maxWord_12) {
        this.maxWord_12 = maxWord_12;
    }
    public String getMaxWord_13() {
        return maxWord_13;
    }
    public void setMaxWord_13(String maxWord_13) {
        this.maxWord_13 = maxWord_13;
    }
    public String getMaxWord_14() {
        return maxWord_14;
    }
    public void setMaxWord_14(String maxWord_14) {
        this.maxWord_14 = maxWord_14;
    }

    private String collectWord_3, collectWord_4, collectWord_5, collectWord_6, collectWord_7, collectWord_8, collectWord_9, collectWord_10,
            collectWord_11, collectWord_12, collectWord_13, collectWord_14, collectword_max; //собранные слова данной длинны

    public void setCollectword_max(String collectword_max) {
        this.collectword_max = collectword_max;
    }
    public String getCollectword_max() {
        return collectword_max;
    }
    public String getCollectWord_3() {
        return collectWord_3;
    }
    public void setCollectWord_3(String collectWord_3) {
        this.collectWord_3 = collectWord_3;
    }
    public String getCollectWord_4() {
        return collectWord_4;
    }
    public void setCollectWord_4(String collectWord_4) {
        this.collectWord_4 = collectWord_4;
    }
    public String getCollectWord_5() {
        return collectWord_5;
    }
    public void setCollectWord_5(String collectWord_5) {
        this.collectWord_5 = collectWord_5;
    }
    public String getCollectWord_6() {
        return collectWord_6;
    }
    public void setCollectWord_6(String collectWord_6) {
        this.collectWord_6 = collectWord_6;
    }
    public String getCollectWord_7() {
        return collectWord_7;
    }
    public void setCollectWord_7(String collectWord_7) {
        this.collectWord_7 = collectWord_7;
    }
    public String getCollectWord_8() {
        return collectWord_8;
    }
    public void setCollectWord_8(String collectWord_8) {
        this.collectWord_8 = collectWord_8;
    }
    public String getCollectWord_9() {
        return collectWord_9;
    }
    public void setCollectWord_9(String collectWord_9) {
        this.collectWord_9 = collectWord_9;
    }
    public String getCollectWord_10() {
        return collectWord_10;
    }
    public void setCollectWord_10(String collectWord_10) {
        this.collectWord_10 = collectWord_10;
    }
    public String getCollectWord_11() {
        return collectWord_11;
    }
    public void setCollectWord_11(String collectWord_11) {
        this.collectWord_11 = collectWord_11;
    }
    public String getCollectWord_12() {
        return collectWord_12;
    }
    public void setCollectWord_12(String collectWord_12) {
        this.collectWord_12 = collectWord_12;
    }
    public String getCollectWord_13() {
        return collectWord_13;
    }
    public void setCollectWord_13(String collectWord_13) {
        this.collectWord_13 = collectWord_13;
    }
    public String getCollectWord_14() {
        return collectWord_14;
    }
    public void setCollectWord_14(String collectWord_14) {
        this.collectWord_14 = collectWord_14;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achives);

        word_stat_max_all = findViewById(R.id.word_stat_max_all);
        word_stat_max_3 = findViewById(R.id.word_stat_max_3);
        word_stat_max_4 = findViewById(R.id.word_stat_max_4);
        word_stat_max_5 = findViewById(R.id.word_stat_max_5);
        word_stat_max_6 = findViewById(R.id.word_stat_max_6);
        word_stat_max_7 = findViewById(R.id.word_stat_max_7);
        word_stat_max_8 = findViewById(R.id.word_stat_max_8);
        word_stat_max_9 = findViewById(R.id.word_stat_max_9);
        word_stat_max_10 = findViewById(R.id.word_stat_max_10);
        word_stat_max_11 = findViewById(R.id.word_stat_max_11);
        word_stat_max_12 = findViewById(R.id.word_stat_max_12);
        word_stat_max_13 = findViewById(R.id.word_stat_max_13);
        word_stat_max_14 = findViewById(R.id.word_stat_max_14);
                word_stat_lit_all = findViewById(R.id.word_stat_lit_all);
                word_stat_lit_3 = findViewById(R.id.word_stat_lit_3);
                word_stat_lit_4 = findViewById(R.id.word_stat_lit_4);
                word_stat_lit_5 = findViewById(R.id.word_stat_lit_5);
                word_stat_lit_6 = findViewById(R.id.word_stat_lit_6);
                word_stat_lit_7 = findViewById(R.id.word_stat_lit_7);
                word_stat_lit_8 = findViewById(R.id.word_stat_lit_8);
                word_stat_lit_9 = findViewById(R.id.word_stat_lit_9);
                word_stat_lit_10 = findViewById(R.id.word_stat_lit_10);
                word_stat_lit_11 = findViewById(R.id.word_stat_lit_11);
                word_stat_lit_12 = findViewById(R.id.word_stat_lit_12);
                word_stat_lit_13 = findViewById(R.id.word_stat_lit_13);
                word_stat_lit_14 = findViewById(R.id.word_stat_lit_14);

        player = new Player();
        dataBase = DataBase.getInstance(getApplication());
        shortWords = new ShortWords();

        //long userIDS = dataBase.playerDAO().selectPlayerRESERVFIELDByID1(1);

        currentUser = String.valueOf(dataBase.playerDAO().selectPlayerNameByID(dataBase.playerDAO().selectPlayerRESERVFIELDByID1(1))); // получение текущего юзера
        currentWord = String.valueOf(dataBase.playerDAO().selectCurrentWordByID(dataBase.playerDAO().selectPlayerRESERVFIELDByID1(1))); //получение текущего слова

        genericMaxCounter(); // запрос к БД на кол-во максимальных слов ,которые можно собрать
        setMaxcouner(); //заполнение полей соответствующими данными на макс. возмаонжость собрать из букв слова

        genericCollectWord(); //запрос к БД: сколько собранно слов опредленной длинны
        setCollectWord(); // заполнение полей кол-вом собранных полей опедленной длинны

    }

    public void genericMaxCounter(){
        setMaxWord_max(String.valueOf(dataBase.shortWordsDAO().maxShortWords()));
        setMaxWord_3(String.valueOf(dataBase.shortWordsDAO().CountShortWordBylenght(3)));
        setMaxWord_4(String.valueOf(dataBase.shortWordsDAO().CountShortWordBylenght(4)));
        setMaxWord_5(String.valueOf(dataBase.shortWordsDAO().CountShortWordBylenght(5)));
        setMaxWord_6(String.valueOf(dataBase.shortWordsDAO().CountShortWordBylenght(6)));
        setMaxWord_7(String.valueOf(dataBase.shortWordsDAO().CountShortWordBylenght(7)));
        setMaxWord_8(String.valueOf(dataBase.shortWordsDAO().CountShortWordBylenght(8)));
        setMaxWord_9(String.valueOf(dataBase.shortWordsDAO().CountShortWordBylenght(9)));
        setMaxWord_10(String.valueOf(dataBase.shortWordsDAO().CountShortWordBylenght(10)));
        setMaxWord_11(String.valueOf(dataBase.shortWordsDAO().CountShortWordBylenght(11)));
        setMaxWord_12(String.valueOf(dataBase.shortWordsDAO().CountShortWordBylenght(12)));
        setMaxWord_13(String.valueOf(dataBase.shortWordsDAO().CountShortWordBylenght(13)));
        setMaxWord_14(String.valueOf(dataBase.shortWordsDAO().CountShortWordBylenght(14)));
    }
    @SuppressLint("SetTextI18n")
    public void setMaxcouner(){
        word_stat_max_all.setText("/"+getMaxWord_max());
        word_stat_max_3.setText("/"+getMaxWord_3());
        word_stat_max_4.setText("/"+getMaxWord_4());
        word_stat_max_5.setText("/"+getMaxWord_5());
        word_stat_max_6.setText("/"+getMaxWord_6());
        word_stat_max_7.setText("/"+getMaxWord_7());
        word_stat_max_8.setText("/"+getMaxWord_8());
        word_stat_max_9.setText("/"+getMaxWord_9());
        word_stat_max_10.setText("/"+getMaxWord_10());
        word_stat_max_11.setText("/"+getMaxWord_11());
        word_stat_max_12.setText("/"+getMaxWord_12());
        word_stat_max_13.setText("/"+getMaxWord_13());
        word_stat_max_14.setText("/"+getMaxWord_14());
    }


    public void genericCollectWord(){
        setMaxWord_max(String.valueOf(dataBase.collectWordsDAO().collectWords(currentWord, currentUser)));
        setCollectWord_3(String.valueOf(dataBase.collectWordsDAO().collectWordsLenght(3, currentWord, currentUser)));
        setCollectWord_4(String.valueOf(dataBase.collectWordsDAO().collectWordsLenght(4, currentWord, currentUser)));
        setCollectWord_5(String.valueOf(dataBase.collectWordsDAO().collectWordsLenght(5, currentWord, currentUser)));
        setCollectWord_6(String.valueOf(dataBase.collectWordsDAO().collectWordsLenght(6, currentWord, currentUser)));
        setCollectWord_7(String.valueOf(dataBase.collectWordsDAO().collectWordsLenght(7, currentWord, currentUser)));
        setCollectWord_8(String.valueOf(dataBase.collectWordsDAO().collectWordsLenght(8, currentWord, currentUser)));
        setCollectWord_9(String.valueOf(dataBase.collectWordsDAO().collectWordsLenght(9, currentWord, currentUser)));
        setCollectWord_10(String.valueOf(dataBase.collectWordsDAO().collectWordsLenght(10, currentWord, currentUser)));
        setCollectWord_11(String.valueOf(dataBase.collectWordsDAO().collectWordsLenght(11, currentWord, currentUser)));
        setCollectWord_12(String.valueOf(dataBase.collectWordsDAO().collectWordsLenght(12, currentWord, currentUser)));
        setCollectWord_13(String.valueOf(dataBase.collectWordsDAO().collectWordsLenght(13, currentWord, currentUser)));
        setCollectWord_14(String.valueOf(dataBase.collectWordsDAO().collectWordsLenght(14, currentWord, currentUser)));
    }
    public void setCollectWord(){
        word_stat_lit_all.setText(getMaxWord_max());
        word_stat_lit_3.setText(getCollectWord_3());
        word_stat_lit_4.setText(getCollectWord_4());
        word_stat_lit_5.setText(getCollectWord_5());
        word_stat_lit_6.setText(getCollectWord_6());
        word_stat_lit_7.setText(getCollectWord_7());
        word_stat_lit_8.setText(getCollectWord_8());
        word_stat_lit_9.setText(getCollectWord_9());
        word_stat_lit_10.setText(getCollectWord_10());
        word_stat_lit_11.setText(getCollectWord_11());
        word_stat_lit_12.setText(getCollectWord_12());
        word_stat_lit_13.setText(getCollectWord_13());
        word_stat_lit_14.setText(getCollectWord_14());
    }

}



//опредление сколько слов из букв можно сборать максимум опредленной длинны
//        word_stat_max_3 =
//        SELECT COUNT (ShortWord)
//        FROM ShortWords
//        WHERE LENGTH(ShortWord) = 3;
//        String mySt = String.valueOf(dataBase.shortWordsDAO().CountShortWordBylenght(3));
//        String myTs = String.valueOf(dataBase.collectWordsDAO().collectWordsLenght(3, currentWord, currentUser));
//        //word_stat_max_3.setText(mySt);
//        //word_stat_max_3.setText(getMaxWord_3());
//        word_stat_lit_3.setText(myTs+"/");