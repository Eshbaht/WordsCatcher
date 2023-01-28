package eshbaht.app.wordscatcher.DataBase.NewLVl.Params;

import androidx.room.ColumnInfo;


public class NeedLVL {
    @ColumnInfo(name = "NEXTLVL")
    public String NEXTLVL;

    @Override
    public String toString(){
        return NEXTLVL;
    }
}