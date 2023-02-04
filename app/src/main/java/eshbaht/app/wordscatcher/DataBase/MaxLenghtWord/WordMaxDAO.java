package eshbaht.app.wordscatcher.DataBase.MaxLenghtWord;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import eshbaht.app.wordscatcher.DataBase.MaxLenghtWord.Params.MaxWordSelect;
import eshbaht.app.wordscatcher.DataBase.WordCollect.CollectWords;


@Dao
public interface WordMaxDAO {
    @Query("SELECT MAXWORD FROM WordMax WHERE ID = :ids")
    MaxWordSelect selectMaxWordByID(long ids);

    @Query("SELECT * FROM WordMax limit :limit")
    List<WordMax> maxWordsLIst(long limit); // все слова собранные из опредленного слова

    @Query("SELECT LENGTH(MAXWORD) FROM WordMax WHERE MAXWORD = :word")
    int lenghtWords(String word); // подсчитывем длинные выбранного слова
} //выбор слова для разбивки на буквы
