package eshbaht.app.wordscatcher.DataBase.NewLVl.Params;

import androidx.room.ColumnInfo;


public class NeedExp {
    @ColumnInfo(name = "NEEDEXP")
    public String NEEDEXP;

    @Override
    public String toString(){
        return NEEDEXP;
    }
}
