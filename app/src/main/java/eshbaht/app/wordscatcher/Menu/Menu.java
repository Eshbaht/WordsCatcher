package eshbaht.app.wordscatcher.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import eshbaht.app.wordscatcher.MainGame.MainGame;
import eshbaht.app.wordscatcher.MyAchives.Achives;
import eshbaht.app.wordscatcher.MyCollection.MyCollectionWords;
import eshbaht.app.wordscatcher.R;

public class Menu extends AppCompatActivity {

   private AppCompatButton butCloseOption, faq, my_collect, myAcive, about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
            butCloseOption = findViewById(R.id.butCloseOption);
            faq = findViewById(R.id.faq);
            my_collect = findViewById(R.id.my_collect);
            myAcive = findViewById(R.id.myAcive);
            about = findViewById(R.id.about);
    }

    public void achives_collect(View v) {
        Intent achive = new Intent(this, Achives.class);
        startActivity(achive);
    }

    public void collect_words(View v){
        Intent collect = new Intent(this, MyCollectionWords.class);
        startActivity(collect);
    }

    public void back_to_game(View v){
        Intent backgame = new Intent(this, MainGame.class);
        startActivity(backgame);
    }
}