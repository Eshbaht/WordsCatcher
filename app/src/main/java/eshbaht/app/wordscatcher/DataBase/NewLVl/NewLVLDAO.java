package eshbaht.app.wordscatcher.DataBase.NewLVl;

import androidx.room.Dao;
import androidx.room.Query;

import eshbaht.app.wordscatcher.DataBase.NewLVl.Params.NeedExp;
import eshbaht.app.wordscatcher.DataBase.NewLVl.Params.NeedLVL;
import eshbaht.app.wordscatcher.DataBase.Player.Params.PLayerName;
import eshbaht.app.wordscatcher.DataBase.Player.Params.PlayerEXP;


@Dao
public interface NewLVLDAO {

    @Query("SELECT NEXTLVL FROM NewLVL WHERE ID = :ids")
    NeedLVL selectNextLvlByID(long ids);

    @Query("SELECT NEEDEXP FROM NewLVL WHERE ID = :ids")
    NeedExp selectNeedExpByID(long ids);


    @Query("SELECT NEEDEXP from NewLVL where NEXTLVL = :lvl")
    String howMuchEXPtoLvlUP(long lvl);

    @Query("SELECT NEEDEXP from NewLVL where NEXTLVL = :lvl")
    int howMuchEXPtoLvlUPint(long lvl);

}