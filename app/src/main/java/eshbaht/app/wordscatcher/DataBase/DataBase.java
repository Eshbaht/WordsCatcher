package eshbaht.app.wordscatcher.DataBase;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import eshbaht.app.wordscatcher.DataBase.MaxLenghtWord.WordMax;
import eshbaht.app.wordscatcher.DataBase.MaxLenghtWord.WordMaxDAO;
import eshbaht.app.wordscatcher.DataBase.MinLenghtWord.ShortWords;
import eshbaht.app.wordscatcher.DataBase.MinLenghtWord.ShortWordsDAO;
import eshbaht.app.wordscatcher.DataBase.NewLVl.NewLVL;
import eshbaht.app.wordscatcher.DataBase.NewLVl.NewLVLDAO;
import eshbaht.app.wordscatcher.DataBase.Player.Player;
import eshbaht.app.wordscatcher.DataBase.Player.PlayerDAO;
import eshbaht.app.wordscatcher.DataBase.WordCollect.CollectWords;
import eshbaht.app.wordscatcher.DataBase.WordCollect.CollectWordsDAO;

@Database(entities = {WordMax.class, ShortWords.class, NewLVL.class, Player.class, CollectWords.class}, version = 1, exportSchema = false)
public abstract class DataBase extends RoomDatabase {
    public abstract WordMaxDAO wordMaxDAO();
    public abstract ShortWordsDAO shortWordsDAO();
    public abstract NewLVLDAO newLVLDAO();
    public abstract PlayerDAO playerDAO();
    public abstract CollectWordsDAO collectWordsDAO();

    private static final String DB_NAME = "CatchTheWord.db";
    private static DataBase instance = null;


    public static DataBase getInstance(Application application){
        if (instance == null){
            instance = Room.databaseBuilder(
                            application,
                            DataBase.class,
                            DB_NAME
                    )
                    .createFromAsset("DB/CatchTheWord.db")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}