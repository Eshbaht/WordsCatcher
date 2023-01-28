package eshbaht.app.wordscatcher.MyCollection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import eshbaht.app.wordscatcher.DataBase.DataBase;
import eshbaht.app.wordscatcher.DataBase.Player.Player;
import eshbaht.app.wordscatcher.DataBase.WordCollect.CollectWords;
import eshbaht.app.wordscatcher.R;
import eshbaht.app.wordscatcher.databinding.ActivityMyCollectionWordsBinding;

public class MyCollectionWords extends AppCompatActivity {


    private ActivityMyCollectionWordsBinding ui;
    private Adapter adapter;

    private CollectWords collectWords;
    private DataBase dataBase;
    private Player player;
    private String currentWord;
    private String currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collection_words);

        collectWords = new CollectWords();
        player = new Player();
        dataBase = DataBase.getInstance(getApplication());


        ui = ActivityMyCollectionWordsBinding.inflate(getLayoutInflater());
        setContentView(ui.getRoot());

        adapter = new Adapter(this);
        ui.wordCollections.setLayoutManager(new LinearLayoutManager((this)));
        ui.wordCollections.setAdapter(adapter);

        currentUser = String.valueOf(dataBase.playerDAO().selectPlayerNameByID(1));
        currentWord = String.valueOf(dataBase.playerDAO().selectCurrentWordByID(1));
        Log.d("ter",currentUser);
        Log.d("ter",currentWord);
        adapter.setCollectWords((List<CollectWords>) dataBase.collectWordsDAO().collectWordsLIst(currentWord, currentUser)); // вывод собранных слов пользователем из КОНКРЕТНОГО СЛОВА

    }
}




//Подсчет собрнных слов: опредленной длинны и из какого слова они сборраны
//  SELECT WORDCOLLECT FROM CollectWords where LENGTH(WORDCOLLECT) = 3 AND BASEWORD = "test" and CURRENTUSER = "Dio";