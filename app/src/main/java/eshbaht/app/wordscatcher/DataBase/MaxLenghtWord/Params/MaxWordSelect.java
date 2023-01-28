package eshbaht.app.wordscatcher.DataBase.MaxLenghtWord.Params;

import androidx.room.ColumnInfo;

public class MaxWordSelect {
    @ColumnInfo(name = "MAXWORD")
    public String MAXWORD;

    @Override
    public String toString(){
        return MAXWORD;
    }
}
