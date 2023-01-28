package eshbaht.app.wordscatcher.DataBase.MaxLenghtWord;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "WordMax")
public class WordMax  {
    @PrimaryKey(autoGenerate = true)
    public int ID;
    public String MAXWORD;

}