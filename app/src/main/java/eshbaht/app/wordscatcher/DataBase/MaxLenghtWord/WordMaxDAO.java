package eshbaht.app.wordscatcher.DataBase.MaxLenghtWord;

import androidx.room.Dao;
import androidx.room.Query;

import eshbaht.app.wordscatcher.DataBase.MaxLenghtWord.Params.MaxWordSelect;


@Dao
public interface WordMaxDAO {
    @Query("SELECT MAXWORD FROM WordMax WHERE ID = :ids")
    MaxWordSelect selectMaxWordByID(long ids);
} //выбор слова для разбивки на буквы
