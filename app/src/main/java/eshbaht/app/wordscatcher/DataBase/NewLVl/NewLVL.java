package eshbaht.app.wordscatcher.DataBase.NewLVl;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "NewLVL")
public class NewLVL {
    @PrimaryKey(autoGenerate = true)
    public int ID;
    public String NEXTLVL;
    public String NEEDEXP;
}
