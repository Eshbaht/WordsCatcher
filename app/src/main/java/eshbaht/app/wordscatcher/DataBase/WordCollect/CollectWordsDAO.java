package eshbaht.app.wordscatcher.DataBase.WordCollect;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;


@Dao
public interface CollectWordsDAO {

    @Query("SELECT COUNT (WORDCOLLECT) FROM CollectWords where LENGTH(WORDCOLLECT) = :lenght AND BASEWORD = :baseWord and CURRENTUSER = :user")
    int collectWordsLenght(long lenght, String baseWord, String user); //сколько слов собранно определнной длинны у опредленного юзера, вывод списка

    @Query("SELECT * FROM CollectWords WHERE BASEWORD = :baseWord and CURRENTUSER = :user")
    List<CollectWords> collectWordsLIst(String baseWord, String user); // все слова собранные из опредленного слова

    @Query("SELECT WORDCOLLECT FROM CollectWords WHERE BASEWORD = :baseWord and CURRENTUSER = :user and WORDCOLLECT = :collword")
    String mutchthisWordInCollect(String baseWord, String user, String collword); // поиск такого слова в списке уже сорбанных словах за конкертным юезром из конкретного слова

    @Query("SELECT COUNT (WORDCOLLECT) FROM CollectWords WHERE BASEWORD = :baseWord and CURRENTUSER = :user")
    int collectWords(String baseWord, String user); // все слова собранные из опредленного слова

    @Query("INSERT INTO CollectWords (BASEWORD, WORDCOLLECT, CURRENTUSER) VALUES (:baseWord, :newWord, :curUser) ")
    void insertNewWord (String baseWord, String newWord, String curUser); // вставить собранное слово


}
