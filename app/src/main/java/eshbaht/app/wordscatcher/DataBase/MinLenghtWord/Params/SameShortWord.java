package eshbaht.app.wordscatcher.DataBase.MinLenghtWord.Params;

import androidx.room.ColumnInfo;

public class SameShortWord {
    @ColumnInfo(name = "ShortWord")
    public String ShortWord;

    @Override
    public String toString(){
        return ShortWord;
    }
}
