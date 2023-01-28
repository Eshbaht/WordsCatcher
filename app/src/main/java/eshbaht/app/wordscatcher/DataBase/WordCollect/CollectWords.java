package eshbaht.app.wordscatcher.DataBase.WordCollect;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "CollectWords")
public class CollectWords  {
    @PrimaryKey(autoGenerate = true)
    public int ID;
    public String BASEWORD;
    public String WORDCOLLECT;
    public String CURRENTUSER;


    public CollectWords() {
    }

    public CollectWords(String BASEWORD, String WORDCOLLECT, String CURRENTUSER) {
        this.BASEWORD = BASEWORD;
        this.WORDCOLLECT = WORDCOLLECT;
        this.CURRENTUSER = CURRENTUSER;
    }
}