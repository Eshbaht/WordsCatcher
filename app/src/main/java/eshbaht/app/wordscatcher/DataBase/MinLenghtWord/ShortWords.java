package eshbaht.app.wordscatcher.DataBase.MinLenghtWord;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "ShortWords")
public class ShortWords {
    @PrimaryKey(autoGenerate = true)
    public int ID;
    public String ShortWord;
}

