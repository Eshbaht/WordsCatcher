package eshbaht.app.wordscatcher.DataBase.Player.Params;

import androidx.room.ColumnInfo;


public class  PLayerLVL {
    @ColumnInfo(name = "LVL")
    public String LVL;

    @Override
    public String toString(){
        return LVL;
    }
}