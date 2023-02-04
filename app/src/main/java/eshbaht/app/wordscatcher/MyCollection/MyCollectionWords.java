package eshbaht.app.wordscatcher.MyCollection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import eshbaht.app.wordscatcher.DataBase.DataBase;
import eshbaht.app.wordscatcher.DataBase.Player.Player;
import eshbaht.app.wordscatcher.DataBase.WordCollect.CollectWords;
import eshbaht.app.wordscatcher.R;
import eshbaht.app.wordscatcher.databinding.ActivityMyCollectionWordsBinding;

public class MyCollectionWords extends AppCompatActivity {


    private ActivityMyCollectionWordsBinding ui;
    private Adapter adapter;
    private DataBase dataBase;
    private RecyclerView word_collections;
    private CollectWords collectWords;
    private TextView emptyList;

    private String currentWord;
    private String currentUser;

    private String maxWord_max;
    public String getMaxWord_max() {
        return maxWord_max;
    }
    public void setMaxWord_max(String maxWord_max) {
        this.maxWord_max = maxWord_max;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collection_words);

        emptyList = findViewById(R.id.emptyList);
        word_collections = findViewById(R.id.word_collections);
        collectWords = new CollectWords();
        dataBase = DataBase.getInstance(getApplication());


        ui = ActivityMyCollectionWordsBinding.inflate(getLayoutInflater());
        setContentView(ui.getRoot());

        adapter = new Adapter(this);
        ui.wordCollections.setLayoutManager(new LinearLayoutManager((this)));
        ui.wordCollections.setAdapter(adapter);

        setMaxWord_max(String.valueOf(dataBase.shortWordsDAO().maxShortWords()));

        long curUser = dataBase.playerDAO().selectPlayerRESERVFIELDByID1(1);
        currentUser = String.valueOf(dataBase.playerDAO().selectPlayerNameByID(curUser));
        currentWord = String.valueOf(dataBase.playerDAO().selectCurrentWordByID(curUser));
        Log.d("ret","Текущий ользователь: " + currentUser);
        Log.d("ret", "Текущее слово: " + currentWord);

        setMaxWord_max(String.valueOf(dataBase.shortWordsDAO().maxShortWords()));

        ui.emptyList.setVisibility(View.GONE);
        long vbn = dataBase.collectWordsDAO().collectWords(currentWord, currentUser);
        if (vbn==0){
            ui.emptyList.setVisibility(View.VISIBLE);
        }

        Log.d("qqw", "слов: " + vbn);
        adapter.setCollectWords((List<CollectWords>) dataBase.collectWordsDAO().collectWordsLIst(currentWord, currentUser)); // вывод собранных слов пользователем из КОНКРЕТНОГО СЛОВА
    }
}




//Подсчет собрнных слов: опредленной длинны и из какого слова они сборраны
//  SELECT WORDCOLLECT FROM CollectWords where LENGTH(WORDCOLLECT) = 3 AND BASEWORD = "test" and CURRENTUSER = "Dio";