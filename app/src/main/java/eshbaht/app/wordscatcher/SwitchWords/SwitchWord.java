package eshbaht.app.wordscatcher.SwitchWords;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import eshbaht.app.wordscatcher.DataBase.DataBase;
import eshbaht.app.wordscatcher.DataBase.MaxLenghtWord.WordMax;
import eshbaht.app.wordscatcher.DataBase.Player.Player;
import eshbaht.app.wordscatcher.DataBase.WordCollect.CollectWords;
import eshbaht.app.wordscatcher.MyCollection.Adapter;
import eshbaht.app.wordscatcher.MyCollection.MyCollectionWords;
import eshbaht.app.wordscatcher.R;
import eshbaht.app.wordscatcher.databinding.ActivityMyCollectionWordsBinding;
import eshbaht.app.wordscatcher.databinding.ActivitySwitchWordBinding;

public class SwitchWord extends AppCompatActivity {

    private ActivitySwitchWordBinding ui;
    private AdapterChange adapterChange;
    private DataBase dataBase;
    private WordMax wordMax;
    private Intent collecScreen;
    private AdapterChange.OnItemClickListener onLeftClickListener;
    AppCompatTextView confirm_choise;
    TextView lvl_1;

    protected List<String> word_list_shuddle_1, word_list_shuddle_2;

    ArrayList<String> wordsCh;

    String word_1 = "test";
    String word_2 = "tester";

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_word);
        int lvl = 2;
        wordsCh = new ArrayList<>();

        confirm_choise = findViewById(R.id.confirm_choise);

        wordMax = new WordMax();
        dataBase = DataBase.getInstance(getApplication());
        ui = ActivitySwitchWordBinding.inflate(getLayoutInflater());
        setContentView(ui.getRoot());

        onLeftClickListener = (position, wordmax)->{
            String choiseWord = wordmax.MAXWORD; //получаем слово из карточки при нажатии
            int choise_word_id = wordmax.ID; //получаем id слова из карточки при нажатии
            long playerId_current = (int) dataBase.playerDAO().selectPlayerRESERVFIELDByID1(1); //получаем ИД текущего юзера

            Log.d("tag", "ID: "+ choise_word_id  + " слово: " + choiseWord);
            ui.confirmChoise.setText(choiseWord); //установка слова на экран

            if (lvl<=position){
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Уровень мал, требуется уровень ", Toast.LENGTH_SHORT);
                toast.show();
                Log.d("tag", "хрен тебе");
            } else {
                dataBase.playerDAO().updateCurentWord(choiseWord, playerId_current);
            }
        };


        adapterChange = new AdapterChange(this, onLeftClickListener);
        ui.wordCollections.setLayoutManager(new LinearLayoutManager((this)));
        ui.wordCollections.setAdapter(adapterChange);
        long playerId = (int) dataBase.playerDAO().selectPlayerRESERVFIELDByID1(1); //получаем ИД текущего юзера
        String curent_lvl = String.valueOf(dataBase.playerDAO().selectPlayerLvlByID(playerId)); // получаем lvl текущего юзера в Стринг
        long lvla = Integer.parseInt(curent_lvl)+1;// получаем ЭКСПУ текущего юзера в лонг и прибавляем +1. получается: виден уровен +1 доп слово.

        adapterChange.setMaxWords((List<WordMax>) dataBase.wordMaxDAO().maxWordsLIst(lvla));

        wordsCh.add("Перманентно");
        wordsCh.add("Волшебно");
//        switch_choose_word(lvl);

//




//        checkBox_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(isChecked) {
//                    checkBox_2.setChecked(false);
//                    dataBase.playerDAO().updateCurentWord(word_1, 1);
//                }else if (!checkBox_2.isChecked()) {
//                    checkBox_1.setChecked(true);
//                }
//            }
//        });
//
//        checkBox_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    checkBox_1.setChecked(false);
//                    dataBase.playerDAO().updateCurentWord(word_2, 1);
//                } else if (!checkBox_2.isChecked()) {
//                    checkBox_1.setChecked(true);
//                }
//            }
//        });
}
//    public void switch_choose_word(int my_lvl){
//        switch (my_lvl){
//            case 1: shuffle_word_1();
//                choosen_word_2.setText("Уровень мал");
//                checkBox_2.setClickable(false);
//                break;
//            case 2: shuffle_word_1(); shuffle_word_2();
//                checkBox_2.setClickable(true);
//                break;
//        }
//    } // вывод слова в поле в зависимости от уровня, а так же возможность нажимать на чекБоксы


   public void shuffle_word_1(){
       word_list_shuddle_1 = MyShuffle_1(); //назначение на перемешивание
       Collections.shuffle(word_list_shuddle_1);//присвамвание перемешанных букв
       for (int word_1=0;word_1<word_list_shuddle_1.size();word_1++);
       //    choosen_word_1.append(word_list_shuddle_1.get(word_1)); //назначение в техстовое поле перемешанных букв
   } //назначение слова 1

    public void shuffle_word_2(){
        word_list_shuddle_2 = MyShuffle_2(); //назначение на перемешивание
        Collections.shuffle(word_list_shuddle_2);//присвамвание перемешанных букв
        for (int word_2 = 0; word_2 < word_list_shuddle_2.size(); word_2++);
           // choosen_word_2.append(word_list_shuddle_2.get(word_2)); //назначение в техстовое поле перемешанных букв
    } //назначение слова 2


    public List<String> MyShuffle_1(){
        String text = word_1; //слово
        Pattern pattern = Pattern.compile("\\S{1}"); //разбиваем слово по "1" букве
        Matcher m = pattern.matcher(text); // присваиваем
            List<String> triples = new ArrayList<>();
            while (m.find()) {
                triples.add(m.group());
            } //группировка букв в список
            return triples;
   } //перемешение слова_1

   public List<String> MyShuffle_2(){
        String text = word_2; //слово
        Pattern pattern = Pattern.compile("\\S{1}"); //разбиваем слово по "1" букве
        Matcher m = pattern.matcher(text); // присваиваем
            List<String> triples = new ArrayList<>();
            while (m.find()) {
                triples.add(m.group());
            } //группировка букв в список
            return triples;
   } //перемешение слова_2

    public void toaster(){
        Toast toast = Toast.makeText(getApplicationContext(),
                "Уровень слишком мал!",
                Toast.LENGTH_SHORT);
        toast.show();
    }


    public void openCollectWords_by_word_1(){
        collecScreen = new Intent(this, MyCollectionWords.class);
        startActivity(collecScreen); //нужен второй адаптер и экран коллекции слов
        //при нажати на стрелку открывается реплика основного списка собранных слов и через ПутЭкстра передается
        // базовое слово.
        //Каждый раз при открытии передается то базовое слово, которое было нажато.
        // Нужно для просмотра собранных слов, без переклчюения на основное слово
    }




}