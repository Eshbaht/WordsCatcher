package eshbaht.app.wordscatcher.DataBase.Player;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "Player")
public class Player {
	@PrimaryKey(autoGenerate = true)
	public int ID;
	public String NAME;
	public String EXP;
	public String LVL;
	public String RESERVFIELD;
	public String CURRENTWORD;
}