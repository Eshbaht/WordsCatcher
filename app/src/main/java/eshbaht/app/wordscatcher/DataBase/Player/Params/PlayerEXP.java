package eshbaht.app.wordscatcher.DataBase.Player.Params;

import androidx.room.ColumnInfo;


public class  PlayerEXP {
    @ColumnInfo(name = "EXP")
    public String EXP;

    @Override
    public String toString(){
        return EXP;
    }
}