package eshbaht.app.wordscatcher.DataBase.Player.Params;

import androidx.room.ColumnInfo;


public class  PlayerCurrentWord {
    @ColumnInfo(name = "CURRENTWORD")
    public String CURRENTWORD;

    @Override
    public String toString(){
        return CURRENTWORD;
    }
}