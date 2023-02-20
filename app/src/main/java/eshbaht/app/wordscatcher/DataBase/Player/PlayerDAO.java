package eshbaht.app.wordscatcher.DataBase.Player;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import eshbaht.app.wordscatcher.DataBase.Player.Params.PLayerLVL;
import eshbaht.app.wordscatcher.DataBase.Player.Params.PLayerName;
import eshbaht.app.wordscatcher.DataBase.Player.Params.PlayerCurrentWord;
import eshbaht.app.wordscatcher.DataBase.Player.Params.PlayerEXP;
import eshbaht.app.wordscatcher.DataBase.Player.Params.PlayerReserv;


@Dao
public interface PlayerDAO {

    @Query("SELECT NAME FROM Player WHERE ID = :ids")
    PLayerName selectPlayerNameByID(long ids);

    @Query("SELECT id FROM Player WHERE Name = :name")
    int selectPlayerIDByName(String name);

    @Query("SELECT EXP FROM Player WHERE ID = :ids")
    PlayerEXP selectPlayerExpByID(long ids);

    @Query("SELECT LVL FROM Player WHERE ID = :ids")
    PLayerLVL selectPlayerLvlByID(long ids);

    @Query("SELECT RESERVFIELD FROM Player WHERE ID = :ids")
    PlayerReserv selectPlayerRESERVFIELDByID(long ids);

    @Query("SELECT RESERVFIELD FROM Player WHERE ID = :ids")
    long selectPlayerRESERVFIELDByID1(long ids);

    @Query("UPDATE Player SET RESERVFIELD = :word WHERE id = :ids")
    void updateReservfield(String word, long ids); //обновляем поле с id юзера. И всегда оперируем к этому полю, когда хотим узнать текущего пользователя. ИСПОЛЬЗУЕМ ид ТОЛЬКО 1

    @Query("UPDATE Player SET EXP = :score WHERE id = :ids")
    void updateEXP(long ids, int score); //обновляем экспу

    @Query("UPDATE Player SET LVL = :lvl WHERE id = :ids")
    void updateLVL(long ids, int lvl); //обновляем экспу

    @Query("SELECT CURRENTWORD FROM Player WHERE ID = :ids")
    PlayerCurrentWord selectCurrentWordByID(long ids);

    @Query("UPDATE Player SET CURRENTWORD = :word WHERE id = :ids")
    void updateCurentWord(String word, long ids);

    @Query("INSERT INTO Player (name, exp, lvl, CURRENTWORD) VALUES (:NAME, :EXP, :LVL, :CURV )")
    void insertName(String NAME, String EXP, String LVL, String CURV);
}