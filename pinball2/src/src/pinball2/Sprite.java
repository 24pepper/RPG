package src.pinball2;


import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/*
 * The super class for all sprites. 
 * 
 */
public class Sprite {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean vis;
    protected Image overWorldImage;
    protected Image battleModeImage;
	protected int health;
	protected int fatigue;
	protected int initialHealth;
	protected int initialFatigue;
	protected int speed;
	protected int attack;
	protected int fatigue2;
	protected int block;
	protected Button2 menuSlot;
	protected boolean beginAttack;
	boolean turn;
	protected ArrayList<Item> offensiveInventory;
	protected ArrayList<Item> defensiveInventory;
	protected ArrayList<Item> potionInventory;

    public Sprite(int x, int y) {

        this.x = x;
        this.y = y;
        vis = true;
        beginAttack = false;
        offensiveInventory = new ArrayList<>();
        defensiveInventory = new ArrayList<>();
        potionInventory = new ArrayList<>();
    }

    /*
     * Stores the width and Height of the image, used for sprite. Needed for Rectangle class.
     * 
     */
    protected void getImageDimensions() {

        width = overWorldImage.getWidth(null);        
        height = overWorldImage.getHeight(null);      
    }

    /*
     * Stores the image to be used by sprite.
     * 
     */
	protected void loadOverWorldImage(String imageName) throws IOException {
		ImageIcon ii = new ImageIcon(imageName);
		overWorldImage = ii.getImage();
//		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//		InputStream input = classLoader.getResourceAsStream(imageName);
//	    overWorldImage = ImageIO.read(input);
	}

    public Image getOverWorldImage() {
        return overWorldImage;
    }

    /*
     * Stores the image to be used by sprite.
     * 
     */
	protected void loadBattleModeImage(String imageName) throws IOException {
		ImageIcon ii = new ImageIcon(imageName);
		battleModeImage = ii.getImage();
//		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//		InputStream input = classLoader.getResourceAsStream(imageName);
//	    battleModeImage = ImageIO.read(input);
	}

    public Image getBattleModeImage() {
        return battleModeImage;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public void setX(int howMuch){
    	x = howMuch;
    }
    
    public void setY(int howMuch){
    	y = howMuch;
    }
    
    public void incrementX(int howMuch){
    	x += howMuch;
    }

    public boolean isVisible() {
        return vis;
    }

    public void setVisible(Boolean visible) {
        vis = visible;
    }
    
	public int getHealth(){
		return health;
	}
	
	public void setHealth(int howMuch){
		health += howMuch;
	}
	
	public void setExplicitHealth(int howMuch){
		health = howMuch;
	}
	
	public int getFatigue(){
		return fatigue;
	}
	
	public void setFatigue(int howMuch){
		fatigue += howMuch;
	}
	
	public int getInitialHealth(){
		return initialHealth;
	}
	
	public int getInitialFatigue(){
		return initialFatigue;
	}
	
	public void setInitialHealth(int h){
		initialHealth = h;
	}
	
	public void setInitialFatigue(int f){
		initialFatigue = f;
	}
	
	public int getSpeed(){
		return speed;
	}
	
	public void setSpeed(int howMuch){
		speed = howMuch;
	}
	
	public void setBeginAttack(boolean b){
		beginAttack = b;
	}
	
	public boolean getBeginAttack(){
		return beginAttack;
	}
	
	public void addToInventory(Item thing){
		if(thing.getType() == "offensive"){
			offensiveInventory.add(thing);
		}
		else if(thing.getType() == "defensive"){
			defensiveInventory.add(thing);
		}
		else if(thing.getType() == "potion"){
			potionInventory.add(thing);
		}
	}
	
	public void addExplicitToInventory(Item thing, int place){
		if(thing.getType() == "offensive"){
			offensiveInventory.add(place, thing);
		}
		else if(thing.getType() == "defensive"){
			defensiveInventory.add(place, thing);
		}
		else if(thing.getType() == "potion"){
			potionInventory.add(place, thing);
		}
	}
		
	
	public void removeFromInventory(Item thing){
		if(thing.getType() == "offensive"){
			offensiveInventory.remove(thing);
		}
		else if(thing.getType() == "defensive"){
			defensiveInventory.remove(thing);
		}
		else if(thing.getType() == "potion"){
			potionInventory.remove(thing);
		}
	}
	
	public void removeExplicitFromInventory(int index, int type){
		if(type == 1){
			offensiveInventory.remove(index);
		}
		else if(type == 2){
			defensiveInventory.remove(index);
		}
		else if(type == 3){
			potionInventory.remove(index);
		}
	}
	
	public Item getFromInventory(int index, int which){
		if(which == 1){
			return offensiveInventory.get(index);
		}
		else if(which == 2){
			return defensiveInventory.get(index);
		}
		else{
			return potionInventory.get(index);
		}
	}
	
	public int offensiveInventorySize(){
		return offensiveInventory.size();
	}
	
	public int defensiveInventorySize(){
		return defensiveInventory.size();
	}
	
	public int potionInventorySize(){
		return potionInventory.size();
	}
	
    public int getAttack(){
    	return attack;
    }

    public int getFatigue2(){
    	return fatigue2;
    }
    
    public void setAttack(int howMuch){
    	attack = howMuch;
    }
    
    public void setFatigue2(int howMuch){
    	fatigue2 = howMuch;
    }
    
    public void setBlock(int howMuch){
    	block = howMuch;
    }
    
    public int getBlock(){
    	return block;
    }
    
    public void setMenuSlot(Button2 ms){
    	menuSlot = ms;
    }
    
    public Button2 getMenuSlot(){
    	return menuSlot;
    }
    
    public void setTurn(boolean b){
    	turn = b;
    }
    
    public boolean getTurn(){
    	return turn;
    }
	                     

    /*
     * Returns a rectangle that surrounds the sprite. Used for hit detection.
     * 
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
