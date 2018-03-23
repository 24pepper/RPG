package src.pinball2;

import java.io.IOException;

public class Enemy extends Sprite{
	
	int typ;
	

	public Enemy(int x, int y, int type) throws IOException{

		super(x,y);
		initBackground(type);
	}

	private void initBackground(int type) throws IOException{
		typ = type;
		if(type == 1){
			loadOverWorldImage("pacman.png");
			loadBattleModeImage("toLong.png");
			getImageDimensions();
		}
		else if(type == 2){
			loadOverWorldImage("enemy2.png");
			loadBattleModeImage("enemy2BattleMode.png");
			getImageDimensions();
		}
		defineEnemy(type);
	}
	
	public void defineEnemy(int type){
		if(type == 1){
			speed = 1;
			health = 25;
			initialHealth = 25;
			fatigue = 10;
			initialFatigue = 10;
			attack = -5;
		}
		else if(type == 2){
			speed = 2;
			health = 50;
			initialHealth = 50;
			fatigue = 15;
			initialFatigue = 15;
			attack = -10;
		}
		
	}
	
	public String toString(){
		if(typ == 1){
			return("ToLong: the Destroyer of Villages");
		}
		else if(typ == 2){
			return("Ogre Women");
		}
		else{
			return ("an enemy of type " + typ);
		}
	}
	
	public int getType(){
		return typ;
	}
}

//differences: speed


