package eshbaht.app.wordscatcher.DataBase.Player.Params;

import androidx.room.ColumnInfo;



public class  PlayerReserv {
    @ColumnInfo(name = "RESERVFIELD")
    public String RESERVFIELD;

    @Override
    public String toString(){
        return RESERVFIELD;
    }
}