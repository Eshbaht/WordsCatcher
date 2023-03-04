package eshbaht.app.wordscatcher.MainGame;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.core.widget.ContentLoadingProgressBar;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
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
import eshbaht.app.wordscatcher.MainActivity;
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

    private Button task, quest, faq, butCloseOption, butClose_mission; // кнопки меню модалки

    private AppCompatButton starter; // основная кнопка начать игру

    private AlertDialog OptionDialog; // модалка с меню по центру экрана

    private RelativeLayout chars_layouts, //слой с кнопками куда попадают собранные буквы. Используется для нажатия подврждения сбора слова
                           engine_buttons; // слой с кнопками меню и сброса
    private MotionLayout Colo;  // слой с двигающимися кнопками

    protected List<String> word_list_shuddle_1; // список букв, перемешенное слово

    String[] ArrayListWord; //массив, в которй объединяется буквы из собранного слова

    private String KeyWord; // словов в которое собираются символы из  ArrayListWord
        public String getKeyWord() {
            return KeyWord;
        }
        public void setKeyWord(String keyWord) {
            KeyWord = keyWord;
        }

    private int search_word_in_all_bd; //результата поиска короткова слова в БД на предмет есть такое в общем списке и доступно ли для сборки
        public int getSearch_word_in_all_bd() {
            return search_word_in_all_bd;
        }
        public void setSearch_word_in_all_bd(int search_word_in_all_bd) {
            this.search_word_in_all_bd = search_word_in_all_bd;
        }


    private int search_word_in_collect_list; // результат поиска слова в списке уже сорбанных слов
        public int getSearch_word_in_collect_list() {
            return search_word_in_collect_list;
        }
        public void setSearch_word_in_collect_list(int search_word_in_collect_list) {
            this.search_word_in_collect_list = search_word_in_collect_list;
        }

    private ArrayList<String> MainListWord = new ArrayList<>();// при нажатии кнопки собисрются слово в этот список

    private TextView char_1, char_2, char_3, char_4, char_5, char_6, char_7, char_8, char_9,
            char_10, char_11, char_12, char_13, char_14;// кнопки, на которые назначаются сборанные буквы

    private ProgressBar indicator;
    private TextView  textLvl, //слово уровень
            lvlview, // отображаемый уровень
            textScore, // слово очки
            score, //отображаемые очки
            scoreMax; //максимальные очки для достижения следующегео уровня

    private ImageView thrue_word, //картинка верного слова
                   img_miss_word,// картинга не верного слова
            img_nextlvl; //картинка новый лвл

    private ContentLoadingProgressBar progressBar; //прогресс бар очков

    private RelativeLayout topLayout, //верхний слой со статистикой
            intod_latout; // слой самой статистики

    private int match_word;  // совпадение слова
    private int list_word; // уже есть в списке собранных

    public int getMatch_word() {
        return match_word;
    }
    public void setMatch_word(int match_word) {
        this.match_word = match_word;
    }
    public int getList_word() {
        return list_word;
    }
    public void setList_word(int list_word) {
        this.list_word = list_word;
    }


    private int expUser; //экспа
    public int getexpUser() {
        return expUser;
    }
    public void setexpUser(int expUser) {
        this.expUser = expUser;
    }

    private int lvlUser; // лвл
    public int getLvlUser() {
        return lvlUser;
    }
    public void setLvlUser(int lvlUser) {
        this.lvlUser = lvlUser;
    }

    private int maxExpToNextLvl; //очки до след лвл
    public int getMaxExpToNextLvl() {
        return maxExpToNextLvl;
    }
    public void setMaxExpToNextLvl(int maxExpToNextLvl) {
        this.maxExpToNextLvl = maxExpToNextLvl;
    }

    private int newLVL; //назначение нового лвл. Если оно не совпаадет с текущем уровнем ,то теккущий уровень повышается
    public int getNewLVL() {
        return newLVL;
    }
    public void setNewLVL(int newLVL) {
        this.newLVL = newLVL;
    }


    int exp_to_1, exp_to_2, exp_to_3,  exp_to_4, exp_to_5, exp_to_6,  exp_to_7, exp_to_8, exp_to_9, exp_to_10; // очки до след ровня
    public int getExp_to_1() {
        return exp_to_1;
    }
    public void setExp_to_1(int exp_to_1) {
        this.exp_to_1 = exp_to_1;
    }
    public int getExp_to_2() {
        return exp_to_2;
    }
    public void setExp_to_2(int exp_to_2) {
        this.exp_to_2 = exp_to_2;
    }
    public int getExp_to_3() {
        return exp_to_3;
    }
    public void setExp_to_3(int exp_to_3) {
        this.exp_to_3 = exp_to_3;
    }
    public int getExp_to_4() {
        return exp_to_4;
    }
    public void setExp_to_4(int exp_to_4) {
        this.exp_to_4 = exp_to_4;
    }
    public int getExp_to_5() {
        return exp_to_5;
    }
    public void setExp_to_5(int exp_to_5) {
        this.exp_to_5 = exp_to_5;
    }
    public int getExp_to_6() {
        return exp_to_6;
    }
    public void setExp_to_6(int exp_to_6) {
        this.exp_to_6 = exp_to_6;
    }
    public int getExp_to_7() {
        return exp_to_7;
    }
    public void setExp_to_7(int exp_to_7) {
        this.exp_to_7 = exp_to_7;
    }
    public int getExp_to_8() {
        return exp_to_8;
    }
    public void setExp_to_8(int exp_to_8) {
        this.exp_to_8 = exp_to_8;
    }
    public int getExp_to_9() {
        return exp_to_9;
    }
    public void setExp_to_9(int exp_to_9) {
        this.exp_to_9 = exp_to_9;
    }
    public int getExp_to_10() {
        return exp_to_10;
    }
    public void setExp_to_10(int exp_to_10) {
        this.exp_to_10 = exp_to_10;
    }


    private DataBase dataBase;


    private String curWord;
    public String getCurWord() {
        return curWord;
    }
    public void setCurWord(String curWord) {
        this.curWord = curWord;
    }


    private int score_fore_word; // сколько очков получаешь за слово собранное правильно, зависит от его длинны
        public int getScore_fore_word() {
            return score_fore_word;
        }
        public void setScore_fore_word(int score_fore_word) {
            this.score_fore_word = score_fore_word;
        }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        dataBase = DataBase.getInstance(getApplication());

        thrue_word = findViewById(R.id.thrue_word);
        img_miss_word= findViewById(R.id.img_miss_word);
        img_nextlvl = findViewById(R.id.img_nextlvl);

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
        scoreMax = findViewById(R.id.scoreMax);

//        long player_Id_current = (int) dataBase.playerDAO().selectPlayerRESERVFIELDByID1(1); //получаем ИД текущего юзера
//        setCurWord(String.valueOf(dataBase.playerDAO().selectCurrentWordByID(player_Id_current)));

        long curUser = dataBase.playerDAO().selectPlayerRESERVFIELDByID1(1);

        setCurWord(String.valueOf(dataBase.playerDAO().selectCurrentWordByID(curUser)));


        initialing_methods();
        setExpMaxPlayer();
    }

    private void initialing_methods(){
        setExpPlayer();
        //setLVLPlayer();
        shuffle_word_1();
        SetLiteralsonButtons();
        Log.d("tyu", getCurWord());
        GoneButnnons();
        setMaxExpToNextLvls();
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

        ArrayListWord = MainListWord.toArray(new String[1]);
       // KeyWord = (String.join("", ArrayListWord));

        setKeyWord(String.join("", ArrayListWord));

        searchInBase_new_word();
        Log.d("ooo", "подходит ли это слово? : " + getSearch_word_in_all_bd() + ". Можно записать в список собранных? "+
                getSearch_word_in_collect_list());


    MainListWord.clear();
        EneblendButtonsAffterPress();
        setLVLPlayer();
        lvl();
        setExpPlayer();
        setExpMaxPlayer();


    }//проверка собранного слова


    public void searchInBase_new_word(){
        String note2;
        if (dataBase.shortWordsDAO().selectIDshortWord(getKeyWord())==0){
            setSearch_word_in_all_bd(0);
//            note2 = "Нет. Недопустимое";//удалить
//            setMatch_word(0);//удалить
            ShowMissWord(); // создать картинку "нет такого слова"
            Log.d("tyui", "НЕт, словов не допустимое");
        } else {
            setSearch_word_in_all_bd(1);
//            note2 = "Да. Допустимое";
//            setMatch_word(1); //удалить
            search_in_my_collect_list_new_word();
            Log.d("tyui", "Да. словов подходит и добавляем его в БД");
        }
    } //проверка - можно ли такое слово составить

    public void search_in_my_collect_list_new_word(){
        String note3;
        long curUser = dataBase.playerDAO().selectPlayerRESERVFIELDByID1(1);

        String currentUser = String.valueOf(dataBase.playerDAO().selectPlayerNameByID(curUser));
        String currentWord = String.valueOf(dataBase.playerDAO().selectCurrentWordByID(curUser));
        if (KeyWord.equalsIgnoreCase(dataBase.collectWordsDAO().mutchthisWordInCollect(currentWord, currentUser, getKeyWord()))){
            setSearch_word_in_collect_list(0);
            ShowMissWord(); // Создать картинку "такое слово уже собрано"
//            note3 = "Нет. Уже собрано";
//            setList_word(0);
            Log.d("tyui", "словов верное, но ранее уже было собрано");
        } else {
//            note3 = "Да. Новое слово";
//            setList_word(1);
            setSearch_word_in_collect_list(1);
            Log.d("tyui", "Да, словов такое такое можно собрать и оно новое. Поздравляем!");
            ShowTrueWord();

            dataBase.collectWordsDAO().insertNewWord(currentWord, getKeyWord(), currentUser);
            how_score(); //сколько очков начисленно
            dataBase.playerDAO().updateEXP(curUser, getexpUser()+getScore_fore_word());
            setExpPlayer();
            setExpMaxPlayer();
            indicator.setProgress(getexpUser());
            //how_lvl();
            // если достаточен уровень, то + лвл
            //  увеличение прогрессБара
        }
    } // проверка можно ли такое слово добавить или оно уже собрано


    protected void ShowTrueWord(){
        ObjectAnimator scaleXAnimation_wow = ObjectAnimator.ofFloat(thrue_word, "scaleX", 0.3f, 2.5f);
        scaleXAnimation_wow.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleXAnimation_wow.setDuration(1900);
        ObjectAnimator scaleYAnimation_wow = ObjectAnimator.ofFloat(thrue_word, "scaleY", 0.3f, 2.5f);
        scaleYAnimation_wow.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleYAnimation_wow.setDuration(1900);
        ObjectAnimator alphaAnimation_wow = ObjectAnimator.ofFloat(thrue_word, "alpha", 1F, 0F);
        alphaAnimation_wow.setInterpolator(new AccelerateDecelerateInterpolator());
        alphaAnimation_wow.setDuration(2500);
        AnimatorSet animationSet_wow = new AnimatorSet();
        animationSet_wow.play(scaleXAnimation_wow).with(scaleYAnimation_wow).with(alphaAnimation_wow);
        animationSet_wow.start();
    } //показать уведомление о верном слове


    protected void ShowMissWord(){
        ObjectAnimator scaleXAnimation_miss = ObjectAnimator.ofFloat(img_miss_word, "scaleX", 0.3f, 2.5f);
        scaleXAnimation_miss.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleXAnimation_miss.setDuration(1900);
        ObjectAnimator scaleYAnimation_miss = ObjectAnimator.ofFloat(img_miss_word, "scaleY", 0.3f, 2.5f);
        scaleYAnimation_miss.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleYAnimation_miss.setDuration(1900);
        ObjectAnimator alphaAnimation_miss = ObjectAnimator.ofFloat(img_miss_word, "alpha", 1F, 0F);
        alphaAnimation_miss.setInterpolator(new AccelerateDecelerateInterpolator());
        alphaAnimation_miss.setDuration(2500);
        AnimatorSet animationSet_miss = new AnimatorSet();
        animationSet_miss.play(scaleXAnimation_miss).with(scaleYAnimation_miss).with(alphaAnimation_miss);
        animationSet_miss.start();
    } //показать уведомление о верном слове

    protected void ShowNewLvl(){
        ObjectAnimator scaleXAnimation = ObjectAnimator.ofFloat(img_nextlvl, "scaleX", 0.3f, 2.5f);
        scaleXAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleXAnimation.setDuration(1900);
        ObjectAnimator scaleYAnimation = ObjectAnimator.ofFloat(img_nextlvl, "scaleY", 0.3f, 2.5f);
        scaleYAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleYAnimation.setDuration(1900);
        ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(img_nextlvl, "alpha", 1F, 0F);
        alphaAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        alphaAnimation.setDuration(2500);
        AnimatorSet animationSet = new AnimatorSet();
        animationSet.play(scaleXAnimation).with(scaleYAnimation).with(alphaAnimation);
        animationSet.start();
    } //показать уведомление о новом уровне


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

    //scoreMax
    private void setExpMaxPlayer(){
        long playerId = (int) dataBase.playerDAO().selectPlayerRESERVFIELDByID1(1); //получаем ИД текущего юзера
        String lvl = String.valueOf(dataBase.playerDAO().selectPlayerLvlByID(playerId)); // получаем lvl текущего юзера в Стринг
        int lvla = Integer.parseInt(lvl);// получаем kvl текущего юзера в инт

        setMaxExpToNextLvl(Integer.parseInt(dataBase.newLVLDAO().howMuchEXPtoLvlUP(lvla+1)));

        scoreMax.setText("/"+getMaxExpToNextLvl());
        indicator.setMax(getMaxExpToNextLvl());
    } //установка очков юзера до след лвла юзера


    private void setLVLPlayer(){
        long curUser = dataBase.playerDAO().selectPlayerRESERVFIELDByID1(1); // ИД текущего юзера
        Log.d("lcc", " "+ curUser);
        long playerId = (int) dataBase.playerDAO().selectPlayerRESERVFIELDByID1(1); //получаем ИД текущего юзера
        String lvl = String.valueOf(dataBase.playerDAO().selectPlayerLvlByID(curUser)); // получаем lvl текущего юзера в Стринг
        int lvla = Integer.parseInt(lvl);// получаем kvl текущего юзера в инт
        Log.d("qwe", "лвл " +lvl);
        if (Integer.parseInt(lvl)<0){
            setLvlUser(0);
        } else {
            setLvlUser(Integer.parseInt(lvl));
        }
        dataBase.playerDAO().updateLVL(curUser, getLvlUser());
        lvlview.setText(""+getLvlUser());
    } //установка уровня юзера
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

    protected void EneblendButtonsAffterPress(){
        pr1.setEnabled(true);
        pr1.setBackgroundResource(R.drawable.main_note_press);
        pr2.setEnabled(true);
        pr2.setBackgroundResource(R.drawable.main_note_press);
        pr3.setEnabled(true);
        pr3.setBackgroundResource(R.drawable.main_note_press);
        pr4.setEnabled(true);
        pr4.setBackgroundResource(R.drawable.main_note_press);
        pr5.setEnabled(true);
        pr5.setBackgroundResource(R.drawable.main_note_press);
        pr6.setEnabled(true);
        pr6.setBackgroundResource(R.drawable.main_note_press);
        pr7.setEnabled(true);
        pr7.setBackgroundResource(R.drawable.main_note_press);
        pr8.setEnabled(true);
        pr8.setBackgroundResource(R.drawable.main_note_press);
        pr9.setEnabled(true);
        pr9.setBackgroundResource(R.drawable.main_note_press);
        pr10.setEnabled(true);
        pr10.setBackgroundResource(R.drawable.main_note_press);
        pr11.setEnabled(true);
        pr11.setBackgroundResource(R.drawable.main_note_press);
        pr12.setEnabled(true);
        pr12.setBackgroundResource(R.drawable.main_note_press);
        pr13.setEnabled(true);
        pr13.setBackgroundResource(R.drawable.main_note_press);
        pr14.setEnabled(true);
        pr14.setBackgroundResource(R.drawable.main_note_press);
        //
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
    } //кнопки снова активны и имеют исходный стиль



    public void how_score(){
        int lenghtWor = getKeyWord().length();
        switch (lenghtWor){
            case 3:
                setScore_fore_word(1);
                break;
            case 4:
                setScore_fore_word(2);
                break;
            case 5:
                setScore_fore_word(3);
                break;
            case 6:
                setScore_fore_word(4);
                break;
        }
    } //сколько очков начисляется за правильно сборанное слово



    public void setMaxExpToNextLvls(){
            setExp_to_1(dataBase.newLVLDAO().howMuchEXPtoLvlUPint(1));
            setExp_to_2(dataBase.newLVLDAO().howMuchEXPtoLvlUPint(2));
            setExp_to_3(dataBase.newLVLDAO().howMuchEXPtoLvlUPint(3));
            setExp_to_4(dataBase.newLVLDAO().howMuchEXPtoLvlUPint(4));
            setExp_to_5(dataBase.newLVLDAO().howMuchEXPtoLvlUPint(5));
            setExp_to_6(dataBase.newLVLDAO().howMuchEXPtoLvlUPint(6));
            setExp_to_7(dataBase.newLVLDAO().howMuchEXPtoLvlUPint(7));
            setExp_to_8(dataBase.newLVLDAO().howMuchEXPtoLvlUPint(8));
            setExp_to_9(dataBase.newLVLDAO().howMuchEXPtoLvlUPint(9));
            setExp_to_10(dataBase.newLVLDAO().howMuchEXPtoLvlUPint(10));
    } //назначение макс кол-во экспы для достижения нового уровня. Инициализация

    public void lvl(){
//        if (getexpUser() >=10 & getexpUser()<= 30){
//            setNewLVL(2);
//            whyitswork();
//            indicator.setMax(30);
//        } else if (getexpUser() >=30 & getexpUser()<= 50){
//            setNewLVL(3);
//            whyitswork();
//            indicator.setMax(50);
//        }

        if (getexpUser() >=0 & getexpUser()< getExp_to_1()){
            setNewLVL(0);
            whyitswork();
            indicator.setMax(getExp_to_1()); // нулевой уровень
        } else if(getexpUser() >=getExp_to_1() & getexpUser()< getExp_to_2()){
            setNewLVL(1);
            whyitswork();
            indicator.setMax(getExp_to_2()); // первый уровень
        } else if(getexpUser() >=getExp_to_2() & getexpUser()< getExp_to_3()){
            setNewLVL(2);
            whyitswork();
            indicator.setMax(getExp_to_3()); // третий уровень
        } else if(getexpUser() >=getExp_to_3() & getexpUser()< getExp_to_4()){
            setNewLVL(3);
            whyitswork();
            indicator.setMax(getExp_to_4()); // второй уровень
        } else if(getexpUser() >=getExp_to_4() & getexpUser()< getExp_to_5()){
            setNewLVL(4);
            whyitswork();
            indicator.setMax(getExp_to_5()); // второй уровень
        } else if(getexpUser() >=getExp_to_5() & getexpUser()< getExp_to_6()){
            setNewLVL(5);
            whyitswork();
            indicator.setMax(getExp_to_6()); // второй уровень
        }else if(getexpUser() >=getExp_to_6() & getexpUser()< getExp_to_7()){
            setNewLVL(6);
            whyitswork();
            indicator.setMax(getExp_to_7()); // второй уровень
        }else if(getexpUser() >=getExp_to_7() & getexpUser()< getExp_to_8()){
            setNewLVL(7);
            whyitswork();
            indicator.setMax(getExp_to_8()); // второй уровень
        }else if(getexpUser() >=getExp_to_8() & getexpUser()< getExp_to_9()){
            setNewLVL(8);
            whyitswork();
            indicator.setMax(getExp_to_9()); // второй уровень
        }else if(getexpUser() >=getExp_to_9() & getexpUser()< getExp_to_10()){
            setNewLVL(9);
            whyitswork();
            indicator.setMax(getExp_to_10()); // второй уровень
        }else if(getexpUser() >=getExp_to_10() & getexpUser()< 100){
            setNewLVL(10);
            whyitswork();
            indicator.setMax(100); // второй уровень
        }
    }

    public void ResetField(View v){
        ressetChars();
    } //сброс кнопок

    private void ressetChars(){
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

        EneblendButtonsAffterPress();

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
    } //сброс кнопок






    public void whyitswork(){
        long playerId = (int) dataBase.playerDAO().selectPlayerRESERVFIELDByID1(1); //получаем ИД текущего юзера
        if (getNewLVL()!=getLvlUser()){
            setLvlUser(getNewLVL());
            dataBase.playerDAO().updateLVL(playerId, getLvlUser());
            setInfo();
            ShowNewLvl();
            Log.d("lvl_new", "Новый уровень!");
        } else {
            Log.d("lvl", "такой уже есть");
        }
    }

    public void setInfo(){
        lvlview.setText(""+getLvlUser());
    }




    public void open_menu_modal(View v){
        OptionMenu();
    }
    public void OptionMenu(){
        OptionDialog = new AlertDialog.Builder(this).create();
        LayoutInflater menuOpt = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = menuOpt.inflate(R.layout.option_menu, null, false);
        butCloseOption =  v.findViewById(R.id.butCloseOption);

        task = v.findViewById(R.id.task);
        quest = v.findViewById(R.id.quest);
        faq = v.findViewById(R.id.faq);

        OptionDialog.setView(v);

        butCloseOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OptionDialog.dismiss();
            }
        }); //закрыть модалку
        OptionDialog.show();
    }  // меню модалка

    public void achives_collect(View v) {
        Intent achive = new Intent(this, Achives.class);
        startActivity(achive);
    }

    public void collect_words(View v){
        Intent collect = new Intent(this, MyCollectionWords.class);
        startActivity(collect);
    }



    public void onBackPressed() {
        // super.onBackPressed();
        openQuitDialog();
    }
    private void openQuitDialog() {
        AlertDialog.Builder quitDialog = new AlertDialog.Builder(
                MainGame.this);
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

