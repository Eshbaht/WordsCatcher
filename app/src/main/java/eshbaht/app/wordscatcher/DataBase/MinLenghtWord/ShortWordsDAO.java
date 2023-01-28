package eshbaht.app.wordscatcher.DataBase.MinLenghtWord;

import androidx.room.Dao;
import androidx.room.Query;

import eshbaht.app.wordscatcher.DataBase.MinLenghtWord.Params.SameShortWord;


@Dao
public interface ShortWordsDAO {
    @Query("SELECT ShortWord FROM ShortWords WHERE ID = :ids")
    SameShortWord selectShortWordByID(long ids); // выбираем конкретное короткое слово

    @Query("SELECT COUNT (ShortWord) FROM ShortWords WHERE LENGTH(ShortWord) =:lenght")
    int CountShortWordBylenght(long lenght); // подсчитывем сколько слов данной длинные есть

    @Query("SELECT COUNT (ShortWord) FROM ShortWords")
    int maxShortWords(); // подсчитывем сколько слов данной длинные есть
}
