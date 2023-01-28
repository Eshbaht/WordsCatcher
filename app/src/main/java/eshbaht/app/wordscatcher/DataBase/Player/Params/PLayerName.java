package eshbaht.app.wordscatcher.DataBase.Player.Params;

import androidx.room.ColumnInfo;


public class PLayerName {
    @ColumnInfo(name = "NAME")
    public String CountWordShort;

    @Override
    public String toString(){
        return CountWordShort;
    }
}