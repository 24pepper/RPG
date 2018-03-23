package src.pinball2;

import java.awt.event.KeyEvent;

import java.util.ArrayList;

import java.io.IOException;

public class Player extends Sprite {
	
	int dx;                //keeps track of potential movements for player
	int dy;
	int savedX;
	int savedY;
	int offensiveEquiptedWeaponIndicy;
	int defensiveEquiptedWeaponIndicy;
	int [] potionEquiptedIndicy = new int[3];
	boolean restrictLeft;
	boolean restrictRight;
	boolean restrictUp;
	boolean restrictDown;
	//boolean turn;
	boolean collision;

	public Player(int x, int y) throws IOException {
		super(x,y);
		initPlayer();
	}
	
	private void initPlayer() throws IOException {
		loadOverWorldImage("right3.png");
		loadBattleModeImage("Hero.png");
		getImageDimensions();
		health = 100;
		initialHealth = 100;
		fatigue = 25;
		initialFatigue = 25;
		speed = 2;
		attack = 0;
		offensiveEquiptedWeaponIndicy = -1;
		defensiveEquiptedWeaponIndicy = -1;
    	for(int i = 0; i <= 2; ++i){
    		potionEquiptedIndicy[i] = -1;
    	}
	}
	
	/*
	 * Alters the x and y coordinates of the player based on keystrokes and if a restriction is not
	 * in place for that particular direction. 
	 */
	public void move(){
		if(dx == -2 & restrictLeft != true){
			x += dx;
		}
		if(dx == 2 & restrictRight != true){
			x += dx;
		}
		if (dy == -2 & restrictUp != true){
			y += dy;
		}
	    if(dy == 2 & restrictDown != true){
			y += dy;
		}
	}
	
	/*
	 * When a key is pressed, this function is called and if a particular key is pressed that 
	 * is specified here, than the contents are done.
	 */
	public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {          //if the left arrow is pressed...
            dx = -2;                            // than dx is -2 and when the players move function is called and there is no restriction, than the players x variable is subtracted by 2.
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 2;   
        }

        if (key == KeyEvent.VK_UP) {
            dy = -2;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 2;
        }
    }
	
	/*
	 * When the key that was pressed is released, this function is called.
	 * 
	 */
	public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
        	dx = 0;                       //dx (and dy in UP and DOWN) are changed to 0.
        }

        if (key == KeyEvent.VK_RIGHT) {
        	dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
        	dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
        	dy = 0;
        }
    }
	
	/*
	 * The four restrictions on the player. Right now, when a player collides with a tree,
	 * one of these restritions is called and restricts the player from moving through the tree.
	 */
	public void restrictLeft(){
		restrictLeft = true;
	}
	
	public void restrictRight(){
		restrictRight = true;
	}
	
	public void restrictUp(){
		restrictUp = true;
	}
	
	public void restrictDown(){
		//System.out.println("in restrict down");
		restrictDown = true;
		//System.out.println("restrict down just got set too" + restrictDown);
	}
	
	/*
	 * Function that is needed to flip the restriction variables back over to false so
	 * the player can move freely when not in contact.
	 */
	public void noCollision(){
		restrictLeft = false;
		restrictRight = false;
		restrictUp = false;
		restrictDown = false;
	}
	
	public void savePlace(){
		savedX = x;
		savedY = y;
	}
	
	public void restorePlace(){
		x = savedX;
		y = savedY;
	}
	
//    public void setTurn(boolean b){
//    	turn = b;
//    }
//    
//    public boolean getTurn(){
//    	return turn;
//    }
    
    public boolean getRestrictDown(){
    	return restrictDown;
    }
    
    public void setCollision(boolean b){
    	collision = b;
    }
    
    public boolean getCollision(){
    	return collision;
    }
    
    public void setOffensiveEquipedWeaponIndicy(int value){
    	offensiveEquiptedWeaponIndicy = value;
    }
    
    public int getOffensiveEquipedWeaponIndicy(){
    	return offensiveEquiptedWeaponIndicy;
    }
    
    public void setDefensiveEquipedWeaponIndicy(int value){
    	defensiveEquiptedWeaponIndicy = value;
    }
    
    public int getDefensiveEquipedWeaponIndicy(){
    	return defensiveEquiptedWeaponIndicy;
    }
    
    public void setPotionEquipedIndicy(int value, int which){
//    	if(potionEquiptedIndicy[2] < 0){
//    		return false;
//    	}
//    	for(int i = 0; i < 2; ++i){
//    		if(potionEquiptedIndicy[i] < 0){
//    			potionEquiptedIndicy[i] = value;
//    			return true;
//    		}
//    	}
//    	return false;
    	potionEquiptedIndicy[which] = value;
    	System.out.println("pei[" + which + "] = " + value);
    }
    
    public int getPotionEquipedIndicy(int value){
    	return potionEquiptedIndicy[value];
    }
    
    public int getSavedX(){
    	return savedX;
    }
    public int getSavedY(){
    	return savedY;
    }
}
