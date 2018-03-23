package src.pinball2;

import java.awt.*;

import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import javax.swing.*;
import java.util.ArrayList;


public class Game extends JPanel implements ActionListener{

	private Timer timer;
	private Background backGround1;
	private Background battleModeBackground;
	private Player plr;
	private Enemy enemyToBattle;
	private int world;
	private int delay;
	private int delay2;
	private int num;
	private int attackMenuNum;
	private int offensiveMenuNum;
	private int defensiveMenuNum;
	private int potionMenuNum;
	private Button2 attackButton;
	private Button2 inventoryButton;
	private Button2 statButton;
	private Button2 menuSlot1;
	private Button2 menuSlot2;
	private Button2 menuSlot3;
	private Button2 menuSlot4;
	private Button2 menuSlot5;
	private Button2 menuSlot6;
	private Button2 menuSlot7;
	private Button2 menuSlot8;
	private Button2 menuSlot9;
	private Button2 submitButton;
	private Button2 upArrowButton;
	private Button2 downArrowButton;
	private Button2 offensiveButton;
	private Button2 defensiveButton;
	private Button2 potionButton;
	private Button2 equippedButton1;
	private Button2 equippedButton2;
	private Button2 equippedButton3;
	private boolean pressed;
	private boolean mouseListener;
	private boolean finishedBattle;
	private boolean menuOpen;
	private boolean menuSlotPressed;
	private Item offensiveItem;
	private Item defensiveItem;
	private Item potionItem;
	private Color menuColor = new Color(47,47,47,100);
	private Color menuSlotColor = new Color(60,70,200,200);     //200,50,50,150
	private ArrayList<Enemy> enemies;
	//private ArrayList<Enemy> enemyInBattle;
	private ArrayList<Tree> trees;
	private ArrayList<Button2> menuSlots;
	private ArrayList<Button2> equippedButtons;
	private final int[][] defineEnemies ={
			{200,300,1}, {250,350,2}, {400,400,1}, {450,450,2}
	};

	private final int[][] defineTrees ={
			{300,300}, {350,350}, {100,100}, {500,500}
	};

	public Game() throws IOException {

		initGame();
	}

	private void initGame() throws IOException {

		world = 1; 

		addKeyListener(new TAdapter());

		addMouseMotionListener(new listener());

		addMouseListener(new listener());

		mouseListener = false; 

		setFocusable(true);

		setPreferredSize(new Dimension(800, 600));

		backGround1 = new Background(0, 0, 1);          //creates background object

		plr = new Player(100,175);                  // creates player object

		Item swd = new Item(1);
		Item shd = new Item(2);
		Item hpot = new Item(3);
		Item mace = new Item(4);
		Item dgr = new Item(5);
		Item iSwd = new Item(6);
		Item spot = new Item(7);
		Item spot2 = new Item(8);
		Item hpot2 = new Item(9);
		
		swd.setIndex(plr.offensiveInventorySize());
		plr.addToInventory(swd);
		shd.setIndex(plr.defensiveInventorySize());
		plr.addToInventory(shd);
		hpot.setIndex(plr.potionInventorySize());
		plr.addToInventory(hpot);
		mace.setIndex(plr.offensiveInventorySize());
		plr.addToInventory(mace);
		dgr.setIndex(plr.offensiveInventorySize());
		plr.addToInventory(dgr);
		iSwd.setIndex(plr.offensiveInventorySize());
		plr.addToInventory(iSwd);
		spot.setIndex(plr.potionInventorySize());
		plr.addToInventory(spot);
		spot2.setIndex(plr.potionInventorySize());
		plr.addToInventory(spot2);
		hpot2.setIndex(plr.potionInventorySize());
		plr.addToInventory(hpot2);

		initEnemies();

		initTrees();
		
		menuSlots = new ArrayList<>();
		
		equippedButtons = new ArrayList<>();

		//enemyInBattle = new ArrayList<>();

		pressed = true;

		finishedBattle = false;

		delay = 0;
		delay2 = 0;
		
		Sound.BACK.loop();

		timer = new Timer(15, this);                //timer for the game loop. restarts every 15 milliseconds 
		timer.start();                              // starts game
	}

	private void initEnemies() throws IOException {

		enemies = new ArrayList<>();                    // initialize arraylist for floor pieces

		for (int[] i : defineEnemies) {                         // populate it
			enemies.add(new Enemy(i[0], i[1], i[2]));
		}
	}

	private void initTrees() throws IOException {

		trees = new ArrayList<>();                    // initialize arraylist for floor pieces

		for (int[] i : defineTrees) {                         // populate it
			trees.add(new Tree(i[0], i[1]));
		}
	}

	public void create_Battle(Enemy enemy, int bk) throws IOException{
		battleModeBackground = new Background(0,0,2);
		finishedBattle = false;
		delay2 = 0;
		world = 2;
		//enemies.remove(num);
		world = bk;
		enemyToBattle = enemy;
		enemyToBattle.setX(500);
		enemyToBattle.setY(200);
		attackMenuNum = 1;
		offensiveMenuNum = 1;
		defensiveMenuNum = 1;
		potionMenuNum = 1;
		plr.savePlace();
		plr.setX(100);
		plr.setY(200);
		//plr.setOffensiveEquipedWeaponIndicy(0);            //will change
		//plr.setDefensiveEquipedWeaponIndicy(0);
		attackButton = new Button2(250,526,50,100,1);
		inventoryButton = new Button2(350,526,50,100,2);
		statButton = new Button2(450,526,50,100,3);
		menuSlot1 = new Button2(255,446,20,265,1);
		menuSlot2 = new Button2(255,466,20,265,1);
		menuSlot3 = new Button2(255,486,20,265,1);
		menuSlot4 = new Button2(255,446,20,265,1);
		menuSlot5 = new Button2(255,466,20,265,1);
		menuSlot6 = new Button2(255,486,20,265,1);
		menuSlot7 = new Button2(255,446,20,265,1);
		menuSlot8 = new Button2(255,466,20,265,1);
		menuSlot9 = new Button2(255,486,20,265,1);
		submitButton = new Button2(250,381,20,300,1);               
		upArrowButton = new Button2(525,405,20,18,1);
		downArrowButton = new Button2(525,500,20,18,1);
		offensiveButton = new Button2(250,381,20,100,1);
		defensiveButton = new Button2(350,381,20,100,1);
		potionButton = new Button2(450,381,20,100,1);
		equippedButton1 = new Button2(485,428,18,18,1);
		equippedButton2 = new Button2(485,458,18,18,1);
		equippedButton3 = new Button2(485,488,18,18,1);
		menuSlotPressed = false;
		offensiveButton.setPressed(true);
		menuSlots.add(menuSlot1);
		menuSlots.add(menuSlot2);
		menuSlots.add(menuSlot3);
		menuSlots.add(menuSlot4);
		menuSlots.add(menuSlot5);
		menuSlots.add(menuSlot6);
		menuSlots.add(menuSlot7);
		menuSlots.add(menuSlot8);
		menuSlots.add(menuSlot9);
		equippedButtons.add(equippedButton1);
		equippedButtons.add(equippedButton2);
		equippedButtons.add(equippedButton3);
		mouseListener = true;
		plr.setTurn(true);
		enemyToBattle.setTurn(false);
		Sound.BACK.stop();
		Sound.BATTLE.loop();

	}

	public void backToOverWorld(){
		++delay2;
		if(delay2 == 50){
			//enemyInBattle.clear();
			battleModeBackground = null;
			attackButton = null;
			inventoryButton = null;
			statButton = null;
			mouseListener = false;
			enemyToBattle = null;
			submitButton = null;
			offensiveButton = null;
			defensiveButton = null;
			potionButton = null;
			menuSlots.clear();
			equippedButtons.clear();
			delay = 0;
			world = 1;
			plr.setAttack(0);
			plr.setBeginAttack(false);
			plr.setX(plr.getSavedX());
			plr.setY(plr.getSavedY());
			Sound.BATTLE.stop();
			Sound.BACK.loop();
		}

	}

	class listener extends MouseAdapter {

		@Override
		public void mousePressed(MouseEvent e) {
			if(mouseListener){
				Point point = e.getPoint();	
				Rectangle rec = attackButton.getBounds();
				Rectangle rec2 = inventoryButton.getBounds();
				Rectangle rec3 = statButton.getBounds();
				Rectangle rec4 = menuSlot1.getBounds();         //and menuSlot4
				Rectangle rec5 = menuSlot2.getBounds();         //and menuSlot5
				Rectangle rec6 = menuSlot3.getBounds();         // and menuSlot6
				Rectangle rec7 = submitButton.getBounds();
				Rectangle rec8 = upArrowButton.getBounds();
				Rectangle rec9 = downArrowButton.getBounds();
				Rectangle rec10 = offensiveButton.getBounds();
				Rectangle rec11 = defensiveButton.getBounds();
				Rectangle rec12 = potionButton.getBounds();
				Rectangle rec13 = equippedButtons.get(0).getBounds();
				Rectangle rec14 = equippedButtons.get(1).getBounds();
				Rectangle rec15 = equippedButtons.get(2).getBounds();

				if(rec.contains(point)){
					buttonPressed(attackButton, inventoryButton, statButton);
				}
				else if(rec2.contains(point)){
					buttonPressed(inventoryButton, attackButton, statButton);
				}
				else if(rec3.contains(point)){
					buttonPressed(statButton, attackButton, inventoryButton);
				}
				else if(rec8.contains(point)){
					if(pressed){
						upArrowButton.setPressed(pressed);
					}
					pressed = false;
				}
				else if(rec9.contains(point)){
					if(pressed){
						downArrowButton.setPressed(pressed);
					}
					pressed = false;
				}
				else if(attackButton.getMenu()){
					if(rec4.contains(point)){
						if(attackMenuNum == 1 & plr.getOffensiveEquipedWeaponIndicy() != -1){
							menuSlotPressed(menuSlot1, menuSlot2, menuSlot3, 0);
						}
						else if(attackMenuNum == 2 & plr.getDefensiveEquipedWeaponIndicy() != -1){
							menuSlotPressed(menuSlot4, menuSlot5, menuSlot6, 3);
						}
						else if(attackMenuNum == 3 & plr.getPotionEquipedIndicy(0) != -1){
							menuSlotPressed(menuSlot7, menuSlot8, menuSlot9, 6);
						}
					}
					else if(rec5.contains(point)){
						if(attackMenuNum == 1 & plr.getOffensiveEquipedWeaponIndicy() != -1){
							menuSlotPressed(menuSlot2, menuSlot1, menuSlot3, 1);
						}
						else if(attackMenuNum == 2 & plr.getDefensiveEquipedWeaponIndicy() != -1){
							menuSlotPressed(menuSlot5, menuSlot4, menuSlot6, 4);
						}
						else if(attackMenuNum == 3 & plr.getPotionEquipedIndicy(1) != -1){
							menuSlotPressed(menuSlot8, menuSlot7, menuSlot9, 7);
						}
					}
					else if(rec6.contains(point)){
						if(attackMenuNum == 1 & plr.getOffensiveEquipedWeaponIndicy() != -1){
							menuSlotPressed(menuSlot3, menuSlot1, menuSlot2, 2);
						}
						else if(attackMenuNum == 2 & plr.getDefensiveEquipedWeaponIndicy() != -1){
							menuSlotPressed(menuSlot6, menuSlot4, menuSlot5, 5);
						}
						else if(attackMenuNum == 3 & plr.getPotionEquipedIndicy(2) != -1){
							menuSlotPressed(menuSlot9, menuSlot7, menuSlot8, 8);
						}
					}
					else if(rec7.contains(point)){
						if(pressed){
							if(attackButton.getMenu()){
								submitButton.setPressed(true);
							}
							pressed = false;
						}
					}
				}
				else if(inventoryButton.getMenu()){
					if(rec10.contains(point)){
						tabPressed(offensiveButton,defensiveButton,potionButton);
					}
					else if(rec11.contains(point)){
						tabPressed(defensiveButton,offensiveButton,potionButton);
					}
					else if(rec12.contains(point)){
						tabPressed(potionButton,defensiveButton,offensiveButton);
					}
					else if(rec13.contains(point)){
						checkEquippedButton(0);
					}
					else if(rec14.contains(point)){
						checkEquippedButton(1);
					}
					else if(rec15.contains(point)){
						checkEquippedButton(2);
					}
					pressed = false;
				}
			}
		}
		//When button is pressed 
		public void buttonPressed(Button2 button1, Button2 button2, Button2 button3) {
            if(pressed){
                if(button1.getMenu()){
                    button1.setMenu(false);
                    button1.setPressed(false);
                    menuOpen = false;
                }
                else{
                    if(button2.getMenu()  || button3.getMenu()){
                        button2.setMenu(false);
                        button2.setPressed(false);
                        button3.setMenu(false);
                        button3.setPressed(false);
                    }
                    button1.setMenu(true);
                    button1.setPressed(true);
                    menuOpen = true;
                }
                pressed = false;

            }
        }
		
		public void menuSlotPressed(Button2 menuSlot1, Button2 menuSlot2, Button2 menuSlot3, int which){
			if(pressed){
				if(!(menuSlot1.getPressed())){
					menuSlot1.setPressed(true);
					menuSlot2.setPressed(false);
					menuSlot3.setPressed(false);                               //"enem health 5"
					plr.setMenuSlot(menuSlot1);
					if(menuSlots.get(which).getType() != 3){
//						if(menuSlots.get(which).getType() == 1){
//							plr.setAttack(-Integer.parseInt(menuSlots.get(which).getItemHeld().getMoveListIndice(0,1)));
//							plr.setFatigue2(-Integer.parseInt(menuSlots.get(which).getItemHeld().getMoveListIndice(0,2)));
//							plr.setBlock(0);
//						}
//						else if(menuSlots.get(which).getType() == 2){
//							plr.setAttack(-Integer.parseInt(menuSlots.get(which).getItemHeld().getMoveListIndice(0,3)));
//							plr.setFatigue2(-Integer.parseInt(menuSlots.get(which).getItemHeld().getMoveListIndice(0,2)));
//							plr.setBlock(Integer.parseInt(menuSlots.get(which).getItemHeld().getMoveListIndice(0,1)));
//						}
						plr.setAttack(menuSlots.get(which).getInfo1());
						plr.setFatigue2(menuSlots.get(which).getInfo2());

						plr.setBlock(menuSlots.get(which).getInfo3());
					}
					menuSlotPressed = true;
					for(int i = 0; i < menuSlots.size(); ++i){
						if(menuSlots.get(i) != menuSlot1){
							menuSlots.get(i).setPressed(false);
						}
					}
				}
				else{
					menuSlot1.setPressed(false);
					menuSlotPressed = false;
				}
				pressed = false;
			}
		}

		public void tabPressed(Button2 tab1, Button2 tab2, Button2 tab3){
			if(pressed){
				if(!tab1.getPressed()){
					tab1.setPressed(true);
					tab2.setPressed(false);
					tab3.setPressed(false);
				}
				pressed = false;
			}
		}
		
		public void checkEquippedButton(int numnum){
			if(pressed){
				//if(equippedButtons.get(numnum).getInfo2() == 1){                         // if the equip button is for an offensive weapon
				if(equippedButtons.get(numnum).getType() == 1){ 
					System.out.println(equippedButtons.get(numnum).getInfo1());
					System.out.println(equippedButtons.get(numnum).getInfo2());
					if(plr.getFromInventory(equippedButtons.get(numnum).getInfo1(), 1).getEquipped()){       // if the item attempting to be equipped is currently equipped
					//if(plr.getFromInventory(equippedButtons.get(numnum).getItemHeld().getIndex(), 1).getEquipped()){
						plr.setOffensiveEquipedWeaponIndicy(-1);
						Item temp = plr.getFromInventory(equippedButtons.get(numnum).getInfo1(), equippedButtons.get(numnum).getInfo2());
						//Item temp = plr.getFromInventory(equippedButtons.get(numnum).getItemHeld().getIndex(), equippedButtons.get(numnum).getType());
						temp.setEquipped(false);
						if(menuSlot1.getPressed() == true || menuSlot2.getPressed() == true || menuSlot3.getPressed() == true){
							menuSlotPressed = false;
						}
						plr.removeExplicitFromInventory(equippedButtons.get(numnum).getInfo1(),equippedButtons.get(numnum).getInfo2());
						//plr.removeExplicitFromInventory(equippedButtons.get(numnum).getItemHeld().getIndex(),equippedButtons.get(numnum).getType());
						plr.addExplicitToInventory(temp,equippedButtons.get(numnum).getInfo1());
						//plr.addExplicitToInventory(temp,equippedButtons.get(numnum).getItemHeld().getIndex());
						temp = null;
					}
					else{                                                            // the weapon is not already equipped
						if(plr.getOffensiveEquipedWeaponIndicy() == -1){             // if the player does not have an offensive equipped weapon 
							plr.setOffensiveEquipedWeaponIndicy(equippedButtons.get(numnum).getInfo1());
							//plr.setOffensiveEquipedWeaponIndicy(equippedButtons.get(numnum).getItemHeld().getIndex());
							Item temp = plr.getFromInventory(equippedButtons.get(numnum).getInfo1(), equippedButtons.get(numnum).getInfo2());
							//Item temp = plr.getFromInventory(equippedButtons.get(numnum).getItemHeld().getIndex(), equippedButtons.get(numnum).getType());
							temp.setEquipped(true);
							menuSlot1.setPressed(false);
							menuSlot2.setPressed(false);
							menuSlot3.setPressed(false);
							plr.removeExplicitFromInventory(equippedButtons.get(numnum).getInfo1(),equippedButtons.get(numnum).getInfo2());
							//plr.removeExplicitFromInventory(equippedButtons.get(numnum).getItemHeld().getIndex(),equippedButtons.get(numnum).getType());
							plr.addExplicitToInventory(temp,equippedButtons.get(numnum).getInfo1());
							//plr.addExplicitToInventory(temp,equippedButtons.get(numnum).getItemHeld().getIndex());
							temp = null;
						}
						else{                                                       // the player already has an offensive equipped weapon
							Item temp = plr.getFromInventory(plr.getOffensiveEquipedWeaponIndicy(), 1);
							temp.setEquipped(false);
							plr.removeExplicitFromInventory(plr.getOffensiveEquipedWeaponIndicy(),1);
							plr.addExplicitToInventory(temp,plr.getOffensiveEquipedWeaponIndicy());
							plr.setOffensiveEquipedWeaponIndicy(equippedButtons.get(numnum).getInfo1());
							//plr.setOffensiveEquipedWeaponIndicy(equippedButtons.get(numnum).getItemHeld().getIndex());
							temp = plr.getFromInventory(equippedButtons.get(numnum).getInfo1(), equippedButtons.get(numnum).getInfo2());
							//temp = plr.getFromInventory(equippedButtons.get(numnum).getItemHeld().getIndex(), equippedButtons.get(numnum).getType());
							temp.setEquipped(true);
							plr.removeExplicitFromInventory(equippedButtons.get(numnum).getInfo1(),equippedButtons.get(numnum).getInfo2());
							//plr.removeExplicitFromInventory(equippedButtons.get(numnum).getItemHeld().getIndex(),equippedButtons.get(numnum).getType());
							plr.addExplicitToInventory(temp,equippedButtons.get(numnum).getInfo1());
							//plr.addExplicitToInventory(temp,equippedButtons.get(numnum).getItemHeld().getIndex());
							temp = null;
						}
					}
				}
				else if(equippedButtons.get(numnum).getInfo2() == 2){
				//else if(equippedButtons.get(numnum).getType() == 2){
					if(plr.getFromInventory(equippedButtons.get(numnum).getInfo1(), 2).getEquipped()){
					//if(plr.getFromInventory(equippedButtons.get(numnum).getItemHeld().getIndex(), 2).getEquipped()){
						plr.setDefensiveEquipedWeaponIndicy(-1);
						Item temp = plr.getFromInventory(equippedButtons.get(numnum).getInfo1(), equippedButtons.get(numnum).getInfo2());
						//Item temp = plr.getFromInventory(equippedButtons.get(numnum).getItemHeld().getIndex(), equippedButtons.get(numnum).getType());
						temp.setEquipped(false);
						if(menuSlot4.getPressed() == true || menuSlot5.getPressed() == true || menuSlot6.getPressed() == true){
							menuSlotPressed = false;
						}
						plr.removeExplicitFromInventory(equippedButtons.get(numnum).getInfo1(),equippedButtons.get(numnum).getInfo2());
						//plr.removeExplicitFromInventory(equippedButtons.get(numnum).getItemHeld().getIndex(),equippedButtons.get(numnum).getType());
						plr.addExplicitToInventory(temp,equippedButtons.get(numnum).getInfo1());
						//plr.addExplicitToInventory(temp,equippedButtons.get(numnum).getItemHeld().getIndex());
						temp = null;
					}
					else{
						if(plr.getDefensiveEquipedWeaponIndicy() == -1){
							plr.setDefensiveEquipedWeaponIndicy(equippedButtons.get(numnum).getInfo1());
							//plr.setDefensiveEquipedWeaponIndicy(equippedButtons.get(numnum).getItemHeld().getIndex());
							Item temp = plr.getFromInventory(equippedButtons.get(numnum).getInfo1(), equippedButtons.get(numnum).getInfo2());
							//Item temp = plr.getFromInventory(equippedButtons.get(numnum).getItemHeld().getIndex(), equippedButtons.get(numnum).getType());
							temp.setEquipped(true);
							menuSlot4.setPressed(false);
							menuSlot5.setPressed(false);
							menuSlot6.setPressed(false);
							plr.removeExplicitFromInventory(equippedButtons.get(numnum).getInfo1(),equippedButtons.get(numnum).getInfo2());
							//plr.removeExplicitFromInventory(equippedButtons.get(numnum).getItemHeld().getIndex(),equippedButtons.get(numnum).getType());
							plr.addExplicitToInventory(temp,equippedButtons.get(numnum).getInfo1());
							//plr.addExplicitToInventory(temp,equippedButtons.get(numnum).getItemHeld().getIndex());
							temp = null;
						}
						else{
							Item temp = plr.getFromInventory(plr.getDefensiveEquipedWeaponIndicy(), 2);
							temp.setEquipped(false);
							plr.removeExplicitFromInventory(plr.getDefensiveEquipedWeaponIndicy(),2);
							plr.addExplicitToInventory(temp,plr.getDefensiveEquipedWeaponIndicy());
							plr.setDefensiveEquipedWeaponIndicy(equippedButtons.get(numnum).getInfo1());
							//plr.setDefensiveEquipedWeaponIndicy(equippedButtons.get(numnum).getItemHeld().getIndex());
							temp = plr.getFromInventory(equippedButtons.get(numnum).getInfo1(), equippedButtons.get(numnum).getInfo2());
							//temp = plr.getFromInventory(equippedButtons.get(numnum).getItemHeld().getIndex(), equippedButtons.get(numnum).getType());
							temp.setEquipped(true);
							plr.removeExplicitFromInventory(equippedButtons.get(numnum).getInfo1(),equippedButtons.get(numnum).getInfo2());
							//plr.removeExplicitFromInventory(equippedButtons.get(numnum).getItemHeld().getIndex(),equippedButtons.get(numnum).getType());
							plr.addExplicitToInventory(temp,equippedButtons.get(numnum).getInfo1());
							//plr.addExplicitToInventory(temp,equippedButtons.get(numnum).getItemHeld().getIndex());
							temp = null;
						}
					}
				}
				else if(equippedButtons.get(numnum).getInfo2() == 3){                                    // if the equip button is for a potion
				//else if(equippedButtons.get(numnum).getType() == 3){
					 																				  //equippedButtons info contains items index place and type
																									  //menuSlots contains the weapons values
					if(plr.getFromInventory(equippedButtons.get(numnum).getInfo1(), 3).getEquipped()){     //if the potion is already equipped
					//if(plr.getFromInventory(equippedButtons.get(numnum).getItemHeld().getIndex(), 3).getEquipped()){
						plr.setPotionEquipedIndicy(-1, plr.getFromInventory(equippedButtons.get(numnum).getInfo1(), 3).getPotionIndex());
						//plr.setPotionEquipedIndicy(-1, plr.getFromInventory(equippedButtons.get(numnum).getItemHeld().getIndex(), 3).getPotionIndex());
						Item temp = plr.getFromInventory(equippedButtons.get(numnum).getInfo1(), equippedButtons.get(numnum).getInfo2());
						//Item temp = plr.getFromInventory(equippedButtons.get(numnum).getItemHeld().getIndex(), equippedButtons.get(numnum).getType());
						temp.setEquipped(false);
						menuSlots.get(plr.getFromInventory(equippedButtons.get(numnum).getInfo1(), 3).getPotionIndex() + 6).setPressed(false);
						//menuSlots.get(plr.getFromInventory(equippedButtons.get(numnum).getItemHeld().getIndex(), 3).getPotionIndex() + 6).setPressed(false);
						if(menuSlot7.getPressed() == false & menuSlot8.getPressed() == false & menuSlot9.getPressed() == false){
							menuSlotPressed = false;
						}
						plr.removeExplicitFromInventory(equippedButtons.get(numnum).getInfo1(),equippedButtons.get(numnum).getInfo2());
						//plr.removeExplicitFromInventory(equippedButtons.get(numnum).getItemHeld().getIndex(),equippedButtons.get(numnum).getType());
						plr.addExplicitToInventory(temp,equippedButtons.get(numnum).getInfo1());
						//plr.addExplicitToInventory(temp,equippedButtons.get(numnum).getItemHeld().getIndex());
						temp = null;
					}
					else{
						int temppy = -1;
						for(int i = 0; i < 3; ++i){                                                       
							if(plr.getPotionEquipedIndicy(i) == -1){
								temppy = i;
								break;
							}
						}
						if(temppy == -1){	                                                       // the player already has potions equipped
							Item temp = plr.getFromInventory(plr.getPotionEquipedIndicy(numnum), 3);        //equipped button info1 is items index place						                                                                          
							temp.setEquipped(false);                                                   //equipped button info2 is number for item type (1 is offensive)
							temp.setPotionIndex(-1);
							plr.removeExplicitFromInventory(plr.getPotionEquipedIndicy(numnum),3);
							plr.addExplicitToInventory(temp,plr.getPotionEquipedIndicy(numnum));
							plr.setPotionEquipedIndicy(equippedButtons.get(numnum).getInfo1(), numnum);
							//plr.setPotionEquipedIndicy(equippedButtons.get(numnum).getItemHeld().getIndex(), numnum);
							temp = plr.getFromInventory(equippedButtons.get(numnum).getInfo1(), equippedButtons.get(numnum).getInfo2());
							//temp = plr.getFromInventory(equippedButtons.get(numnum).getItemHeld().getIndex(), equippedButtons.get(numnum).getType());
							temp.setEquipped(true);
							temp.setPotionIndex(3);
							plr.removeExplicitFromInventory(equippedButtons.get(numnum).getInfo1(),equippedButtons.get(numnum).getInfo2());
							//plr.removeExplicitFromInventory(equippedButtons.get(numnum).getItemHeld().getIndex(),equippedButtons.get(numnum).getType());
							plr.addExplicitToInventory(temp,equippedButtons.get(numnum).getInfo1());
							//plr.addExplicitToInventory(temp,equippedButtons.get(numnum).getItemHeld().getIndex());
							temp = null;
																								
						}
						else{    																	     // the player has a free potion slot
							plr.setPotionEquipedIndicy(equippedButtons.get(numnum).getInfo1(), temppy); 
							//plr.setPotionEquipedIndicy(equippedButtons.get(numnum).getItemHeld().getIndex(), temppy);
							Item temp = plr.getFromInventory(equippedButtons.get(numnum).getInfo1(), equippedButtons.get(numnum).getInfo2());
							//Item temp = plr.getFromInventory(equippedButtons.get(numnum).getItemHeld().getIndex(), equippedButtons.get(numnum).getType());
							temp.setEquipped(true);
							temp.setPotionIndex(temppy);
							plr.removeExplicitFromInventory(equippedButtons.get(numnum).getInfo1(),equippedButtons.get(numnum).getInfo2());
							//plr.removeExplicitFromInventory(equippedButtons.get(numnum).getItemHeld().getIndex(),equippedButtons.get(numnum).getType());
							plr.addExplicitToInventory(temp,equippedButtons.get(numnum).getInfo1());
							//plr.addExplicitToInventory(temp,equippedButtons.get(numnum).getItemHeld().getIndex());
							temp = null;	
						}
						
					}
				}
			}
		}


		public void mouseReleased(MouseEvent e){
			if(mouseListener){
				Point point = e.getPoint();	
				Rectangle rec = submitButton.getBounds();
				Rectangle rec2 = upArrowButton.getBounds();
				Rectangle rec3 = downArrowButton.getBounds();

				if(rec.contains(point)){
					if(attackButton.getMenu()){
						if(menuSlotPressed){
							if(plr.getTurn() & !(enemyToBattle.getTurn()) & (plr.getFatigue() + (+plr.getFatigue2())) >= 0 & plr.getMenuSlot().getType() != 3){
								plr.setBeginAttack(true);
								Sound.PLAYER_ATTACK.play();
							}
						}
					}
				}
				else if(rec2.contains(point)){
					if(attackButton.getMenu()){
						if(attackMenuNum > 1){
							attackMenuNum -= 1;
						}
					}
					else if(inventoryButton.getMenu() & offensiveButton.getPressed()){
						if(offensiveMenuNum > 1){
							offensiveMenuNum -= 1;
						}
					}
					else if(inventoryButton.getMenu() & defensiveButton.getPressed()){
						if(defensiveMenuNum > 1){
							defensiveMenuNum -= 1;
						}
					}
					else if(inventoryButton.getMenu() & potionButton.getPressed()){
						if(potionMenuNum > 1){
							potionMenuNum -= 1;
						}
					}
				}
				else if(rec3.contains(point)){
					if(attackButton.getMenu()){
						if(attackMenuNum < 3){
							attackMenuNum += 1;
						}
					}
					else if(inventoryButton.getMenu() & offensiveButton.getPressed()){
						if(offensiveMenuNum < 3){
							offensiveMenuNum += 1;
						}
					}
					else if(inventoryButton.getMenu() & defensiveButton.getPressed()){
						if(defensiveMenuNum < 3){
							defensiveMenuNum += 1;
						}
					}
					else if(inventoryButton.getMenu() & potionButton.getPressed()){
						if(potionMenuNum < 3){
							potionMenuNum += 1;
						}
					}
				}
				

				pressed = true;
				submitButton.setPressed(false);
				upArrowButton.setPressed(false);
				downArrowButton.setPressed(false);
			}
		}

		public void mouseMoved(MouseEvent e){
			if(mouseListener){
				Point point = e.getPoint();                        // create point for mouse
				Rectangle rec = menuSlot1.getBounds();
				Rectangle rec2 = menuSlot2.getBounds();
				Rectangle rec3 = menuSlot3.getBounds();
				Rectangle rec4 = submitButton.getBounds();
				Rectangle rec5 = upArrowButton.getBounds();
				Rectangle rec6 = downArrowButton.getBounds();
				Rectangle rec7 = attackButton.getBounds();
				Rectangle rec8 = inventoryButton.getBounds();
				Rectangle rec9 = statButton.getBounds();
				Rectangle rec10 = offensiveButton.getBounds();
				Rectangle rec11 = defensiveButton.getBounds();
				Rectangle rec12 = potionButton.getBounds();
				Rectangle rec13 = equippedButtons.get(0).getBounds();
				Rectangle rec14 = equippedButtons.get(1).getBounds();
				Rectangle rec15 = equippedButtons.get(2).getBounds();
				Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
				Cursor defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);
				
				setCursor(defaultCursor);
				if(rec7.contains(point)){
					setCursor(handCursor);
				}
				else if(rec8.contains(point)){
					setCursor(handCursor);
				}
				else if(rec9.contains(point)){
					setCursor(handCursor);
				}
				else{
					//setCursor(defaultCursor);
				}
				if(attackButton.getMenu()){
					if(rec5.contains(point)){
						setCursor(handCursor); 
					}
					else if(rec6.contains(point)){
						setCursor(handCursor);
					}
					else if(rec4.contains(point)){
						setCursor(handCursor);
					}
					if(attackMenuNum == 1){
						if(rec.contains(point) & plr.getOffensiveEquipedWeaponIndicy() != -1){
							menuSlot1.setHover(true);
							setCursor(handCursor);

						}
						else{
							menuSlot1.setHover(false);
						}

						if(rec2.contains(point) & plr.getOffensiveEquipedWeaponIndicy() != -1){
							menuSlot2.setHover(true);
							setCursor(handCursor);

						}
						else{
							menuSlot2.setHover(false);
						}

						if(rec3.contains(point) & plr.getOffensiveEquipedWeaponIndicy() != -1){
							menuSlot3.setHover(true);
							setCursor(handCursor);

						}
						else{
							menuSlot3.setHover(false);
						}
						if(menuSlot1.getHover() == false & menuSlot2.getHover() == false & menuSlot3.getHover() == false){
							//setCursor(defaultCursor);
						}
					}
					else if(attackMenuNum == 2){
						if(rec.contains(point)  & plr.getDefensiveEquipedWeaponIndicy() != -1){
							menuSlot4.setHover(true);
							setCursor(handCursor);

						}
						else{
							menuSlot4.setHover(false);
						}

						if(rec2.contains(point) & plr.getDefensiveEquipedWeaponIndicy() != -1){
							menuSlot5.setHover(true);
							setCursor(handCursor);

						}
						else{
							menuSlot5.setHover(false);
						}

						if(rec3.contains(point) & plr.getDefensiveEquipedWeaponIndicy() != -1){
							menuSlot6.setHover(true);
							setCursor(handCursor);

						}
						else{
							menuSlot6.setHover(false);
						}
						if(menuSlot4.getHover() == false & menuSlot5.getHover() == false & menuSlot6.getHover() == false){
							setCursor(defaultCursor);
						}
					}
					else if(attackMenuNum == 3){
						if(rec.contains(point)  & plr.getPotionEquipedIndicy(0) != -1){
							menuSlot7.setHover(true);
							setCursor(handCursor);

						}
						else{
							menuSlot7.setHover(false);
						}

						if(rec2.contains(point) & plr.getPotionEquipedIndicy(1) != -1){
							menuSlot8.setHover(true);
							setCursor(handCursor);

						}
						else{
							menuSlot8.setHover(false);
						}

						if(rec3.contains(point) & plr.getPotionEquipedIndicy(2) != -1){
							menuSlot9.setHover(true);
							setCursor(handCursor);

						}
						else{
							menuSlot9.setHover(false);
						}
						if(menuSlot7.getHover() == false & menuSlot8.getHover() == false & menuSlot9.getHover() == false){
							setCursor(defaultCursor);
						}
					}

				}
				else if(inventoryButton.getMenu()){
					if(rec10.contains(point)){
						setCursor(handCursor);
					}
					else if(rec11.contains(point)){
						setCursor(handCursor);
					}
					else if(rec12.contains(point)){
						setCursor(handCursor);
					}
					else if(rec13.contains(point)){
						setCursor(handCursor);
					}
					else if(rec14.contains(point)){
						setCursor(handCursor);
					}
					else if(rec15.contains(point)){
						setCursor(handCursor);
					}
					else if(rec5.contains(point)){
						setCursor(handCursor); 
					}
					else if(rec6.contains(point)){
						setCursor(handCursor);
					}
					else{
						//setCursor(defaultCursor);
					}
				}
			}

		}
	}


	/*
	 * function that paints graphics in game panel
	 * 
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if(world == 1){
			drawOverWorldObjects(g);                         // calls on function that draws overworld objects
		}

		if(world == 2){
			drawBattleModeObjects(g);
		}

		Toolkit.getDefaultToolkit().sync();
	}


	/*
	 * function that draws game objects. Uses painComponent
	 * 
	 */
	private void drawOverWorldObjects(Graphics g) {

		g.drawImage(backGround1.getOverWorldImage(), backGround1.getX(), backGround1.getY(), this);  //draws background
		for(int i = 0; i < trees.size(); ++i){
			g.drawImage(trees.get(i).getOverWorldImage(), trees.get(i).getX(), trees.get(i).getY(), this);
			if(i < enemies.size()){
				g.drawImage(enemies.get(i).getOverWorldImage(),enemies.get(i).getX(), enemies.get(i).getY(), this);
			}
		}                      
		g.drawImage(plr.getOverWorldImage(), plr.getX(), plr.getY(), this);                          // draws player

	}

	private void drawBattleModeObjects(Graphics g){
		Graphics2D g2d = (Graphics2D)g.create();
		g2d.drawImage(battleModeBackground.getOverWorldImage(), battleModeBackground.getX(), battleModeBackground.getY(), this);

		if(plr.getBeginAttack() & finishedBattle == false){
			plr.incrementX(5);
			g2d.drawImage(plr.getBattleModeImage(), plr.getX(), plr.getY(), this);
			if(plr.getX() > 150){
				plr.setBeginAttack(false); 
				if((enemyToBattle.getHealth() - (-plr.getAttack())) <= 0){
					enemyToBattle.setExplicitHealth(0);
					plr.setFatigue(plr.getFatigue2());
					plr.setX(100);
					enemies.remove(num);
					finishedBattle = true;
				}
				else if(plr.getMenuSlot().getInfo4() != null){
				//else if(plr.getMenuSlot().getItemHeld().getType().equals("potion")){
					if(plr.getMenuSlot().getInfo4().substring(0, 4).equals("enem")){
					//if(plr.getMenuSlot().getItemHeld().getMoveListIndice(0,1).equals("Enemy")){
							if(plr.getMenuSlot().getInfo4().substring(5, 11).equals("health")){
						    //if(plr.getMenuSlot().getItemHeld().getMoveListIndice(0,0).equals("Off health")){
								enemyToBattle.setHealth(plr.getMenuSlot().getInfo2());
								//enemyToBattle.setHealth(Integer.parseInt(plr.getMenuSlot().getItemHeld().getMoveListIndice(0,3)));
							}
							else if(plr.getMenuSlot().getInfo4().substring(5, 11).equals("fatigu")){
							//else if(plr.getMenuSlot().getItemHeld().getMoveListIndice(0,0).equals("Off fatigue")){
								enemyToBattle.setFatigue(plr.getMenuSlot().getInfo2());
								//enemyToBattle.setFatigue(Integer.parseInt(plr.getMenuSlot().getItemHeld().getMoveListIndice(0,3)));
							}
					}
					else if(plr.getMenuSlot().getInfo4().substring(0, 4).equals("self")){
					//else if(plr.getMenuSlot().getItemHeld().getMoveListIndice(0,1).equals("Self")){
						if(plr.getMenuSlot().getInfo4().substring(5, 11).equals("health")){
						//if(plr.getMenuSlot().getItemHeld().getMoveListIndice(0,0).equals("Def health")){
							plr.setHealth(-plr.getMenuSlot().getInfo1());
							//plr.setHealth(-Integer.parseInt(plr.getMenuSlot().getItemHeld().getMoveListIndice(0,2)));
						}
						else if(plr.getMenuSlot().getInfo4().substring(5, 11).equals("fatigu")){
						//else if(plr.getMenuSlot().getItemHeld().getMoveListIndice(0,0).equals("Def fatigue")){
							plr.setFatigue(-plr.getMenuSlot().getInfo1());
							//plr.setFatigue(-Integer.parseInt(plr.getMenuSlot().getItemHeld().getMoveListIndice(0,2)));
						}

					}
					plr.setX(100);
					plr.setTurn(false);
					enemyToBattle.setTurn(true);
				}
				else{
					enemyToBattle.setHealth(plr.getAttack());
					plr.setFatigue(plr.getFatigue2());
					plr.setX(100);
					plr.setTurn(false);
					enemyToBattle.setTurn(true);
				}
			}
		}
		else{
			g2d.drawImage(plr.getBattleModeImage(), plr.getX(), plr.getY(), this);
		}

		if(enemyToBattle.getBeginAttack()){
			if(enemyToBattle.getX() == 500){
				if(enemyToBattle.getType() == 1){
					Sound.Enemy1_ATTACK.play();
				}
				else if(enemyToBattle.getType() == 2){
					Sound.Enemy2_ATTACK.play();
				}
			}
			enemyToBattle.incrementX(-5);
			g2d.drawImage(enemyToBattle.getBattleModeImage(), enemyToBattle.getX(), enemyToBattle.getY(), this);
			if(enemyToBattle.getX() < 450){
				enemyToBattle.setBeginAttack(false);
				if(enemyToBattle.getAttack() - plr.getBlock() < 0){
					plr.setHealth(enemyToBattle.getAttack() - plr.getBlock());
				}
				enemyToBattle.setFatigue(-1);
				enemyToBattle.setX(500);
				plr.setTurn(true);
				enemyToBattle.setTurn(false);
			}
		}
		else{
			g2d.drawImage(enemyToBattle.getBattleModeImage(), enemyToBattle.getX(), enemyToBattle.getY(), this);
		}


		checkButtonPressed(attackButton, g2d);
		checkButtonPressed(inventoryButton, g2d);
		checkButtonPressed(statButton, g2d);
		
		if(menuOpen = true){
			if(attackButton.getMenu()){
				drawDropDown(attackButton.getWhich(), g2d);
			}
			else if(inventoryButton.getMenu()){
				drawDropDown(inventoryButton.getWhich(),g2d);
			}
			else if(statButton.getMenu()){
				drawDropDown(statButton.getWhich(),g2d);
			}
		}


		g2d.setColor(Color.BLACK);
		g2d.drawString("Buttcheeks Megee", 40, 30);
		g2d.drawString("-----------------------", 40, 40);  
		g2d.drawString("Health", 40, 50);
		g2d.drawString(Integer.toString(plr.getHealth()) + "/" + Integer.toString(plr.getInitialHealth()), (95 + plr.getInitialHealth()), 50);
		g2d.drawRect(87, 40, plr.getInitialHealth(), 10);
		g2d.setColor(Color.RED);
		g2d.fillRect(87, 40, plr.getHealth(), 10);

		g2d.setColor(Color.BLACK);
		g2d.drawString("Fatigue", 40, 65);
		g2d.drawString(Integer.toString(plr.getFatigue()) + "/" + Integer.toString(plr.getInitialFatigue()), (95 + plr.getInitialFatigue()), 65);
		g2d.drawRect(87, 55, plr.getInitialFatigue(), 10);
		g2d.setColor(Color.BLUE);
		g2d.fillRect(87, 55, plr.getFatigue(), 10);

		//opponent stats

		g2d.setColor(Color.BLACK);
		g2d.drawString(enemyToBattle.toString(), 550, 30);
		g2d.drawString("-----------------------", 550, 40); 
		g2d.drawString("Health", 550, 50);
		g2d.drawString(Integer.toString(enemyToBattle.getHealth()) + "/" + Integer.toString(enemyToBattle.getInitialHealth()), (605 + enemyToBattle.getInitialHealth()), 50);
		g2d.drawRect(600, 40, enemyToBattle.getInitialHealth(), 10);
		g2d.setColor(Color.RED);
		g2d.fillRect(600, 40, enemyToBattle.getHealth(), 10);


		g2d.setColor(Color.BLACK);
		g2d.drawString("Fatigue", 550, 65);
		g2d.drawString(Integer.toString(enemyToBattle.getFatigue()) + "/" + Integer.toString(enemyToBattle.getInitialFatigue()), (605 + enemyToBattle.getInitialFatigue()), 65);
		g2d.drawRect(600, 55, enemyToBattle.getInitialFatigue(), 10);
		g2d.setColor(Color.BLUE);
		g2d.fillRect(600, 55, enemyToBattle.getFatigue(), 10);



	}
	public void checkButtonPressed(Button2 button, Graphics g2d) {
		if(button.getPressed()){
			g2d.setColor(menuColor);
		}
		else{
			g2d.setColor(Color.LIGHT_GRAY);
		}
		g2d.fillRect(button.getX(), button.getY(), button.getWidth(), button.getHeight());
		g2d.setColor(Color.BLACK);
		g2d.drawString(button.toString(), button.getX() + 30, button.getY() + 30);
	}

	public void drawDropDown(int which, Graphics g2d){
		g2d.setColor(menuColor);
		g2d.drawRect(250, 401, 300, 125);
		g2d.fillRect(250, 401, 300, 125);
		g2d.setColor(Color.BLACK);
		g2d.drawRect(upArrowButton.getX(), upArrowButton.getY(), upArrowButton.getWidth(), upArrowButton.getHeight()); 
		g2d.drawRect(downArrowButton.getX(), downArrowButton.getY(), downArrowButton.getWidth(), downArrowButton.getHeight());
		
		if(upArrowButton.getPressed()){
			g2d.setColor(Color.LIGHT_GRAY);
			g2d.fillRect(upArrowButton.getX(), upArrowButton.getY(), upArrowButton.getWidth(), upArrowButton.getHeight());
		}
		else{
			g2d.setColor(Color.GRAY);
			g2d.fillRect(upArrowButton.getX(), upArrowButton.getY(), upArrowButton.getWidth(), upArrowButton.getHeight());
		}
		if(downArrowButton.getPressed()){
			g2d.setColor(Color.LIGHT_GRAY);
			g2d.fillRect(downArrowButton.getX(), downArrowButton.getY(), downArrowButton.getWidth(), downArrowButton.getHeight());
		}
		else{
			g2d.setColor(Color.GRAY);
			g2d.fillRect(downArrowButton.getX(), downArrowButton.getY(), downArrowButton.getWidth(), downArrowButton.getHeight());
		}
		
		g2d.setColor(Color.WHITE);
		
		if(which == 1){
			fillAttackDropDown(g2d);
		}
		else if(which == 2){
			fillInventoryDropDown(g2d);
		}
		else if(which == 3){
		//	fillStatusDropDown(g2d);
		}
		
		g2d.setColor(Color.BLACK);
		g2d.drawString("^", 530, 420);      
		g2d.drawString("v", 530, 513);
	}

	public void fillAttackDropDown(Graphics g2d){
		g2d.drawLine(254, 418, 516, 418);
		g2d.drawString("---------------------------------", 254, 446);
		if(submitButton.getPressed()){
			g2d.setColor(Color.lightGray);
		}
		else{
			g2d.setColor(Color.gray);
		}
		g2d.fillRect(submitButton.getX(),submitButton.getY(),submitButton.getWidth(),submitButton.getHeight());
		g2d.setColor(Color.BLACK);
		g2d.drawRect(submitButton.getX(),submitButton.getY(),submitButton.getWidth(),submitButton.getHeight());
		g2d.drawString("Submit", 375, 395);
		
		if(attackMenuNum == 1){
			g2d.setColor(Color.WHITE);
//			g2d.drawString("Move", 254, 436);
//			g2d.drawString("Dmg", 360, 436);
//			g2d.drawString("Ftg", 480, 436);
			if(plr.getOffensiveEquipedWeaponIndicy() != -1){
				offensiveItem = plr.getFromInventory(plr.getOffensiveEquipedWeaponIndicy(),1);
				if(menuSlots.get(0).getPressed()){
					g2d.setColor(menuSlotColor);
					g2d.fillRect(menuSlots.get(0).getX(), menuSlots.get(0).getY(), menuSlots.get(0).getWidth(), menuSlots.get(0).getHeight());
				}
				else if(menuSlots.get(0).getHover()){
					g2d.setColor(menuSlotColor);
					g2d.fillRect(menuSlot1.getX(), menuSlot1.getY(), menuSlot1.getWidth(), menuSlot1.getHeight());
				}

				if(menuSlots.get(1).getPressed()){
					g2d.setColor(menuSlotColor);
					g2d.fillRect(menuSlot2.getX(), menuSlot2.getY(), menuSlot2.getWidth(), menuSlot2.getHeight());
				}
				else if(menuSlots.get(1).getHover()){
					g2d.setColor(menuSlotColor);
					g2d.fillRect(menuSlot2.getX(), menuSlot2.getY(), menuSlot2.getWidth(), menuSlot2.getHeight());
				}

				if(menuSlots.get(2).getPressed()){
					g2d.setColor(menuSlotColor);
					g2d.fillRect(menuSlot3.getX(), menuSlot3.getY(), menuSlot3.getWidth(), menuSlot3.getHeight());
				}
				else if(menuSlots.get(2).getHover()){
					g2d.setColor(menuSlotColor);
					g2d.fillRect(menuSlot3.getX(), menuSlot3.getY(), menuSlot3.getWidth(), menuSlot3.getHeight());
				}
				g2d.setColor(Color.WHITE);
				g2d.drawString(offensiveItem.toString(),350, 416);
				g2d.drawString("Move", 254, 436);
				g2d.drawString("Dmg", 360, 436);
				g2d.drawString("Ftg", 480, 436);
				int x = 254;
				int y = 441;
				for(int i = 0; i < 3; ++i){
					for(int j = 0; j < 3; ++j){
						if(j == 0){
							//menuSlots.get(i).setItemHeld(offensiveItem);
							//menuSlots.get(i).setType(1);
							x = 254;
							y += 20;
							g2d.drawString(offensiveItem.getMoveListIndice(i, j), x, y);
						}
						else{
							if(j == 1){
								menuSlots.get(i).setInfo1(Integer.parseInt(offensiveItem.getMoveListIndice(i, j)));
							}
							else if(j == 2){
								menuSlots.get(i).setInfo2(Integer.parseInt(offensiveItem.getMoveListIndice(i, j)));
							}

							x += 115;
							g2d.drawString(offensiveItem.getMoveListIndice(i, j), x, y);
						}
					}
				}
			}
			else{
				g2d.setColor(Color.WHITE);
				g2d.drawString("No offensive weapon equipped", 300, 416);
			}
		}
		else if(attackMenuNum == 2){
			g2d.setColor(Color.WHITE);
//			g2d.drawString("Move", 254, 436);
//			g2d.drawString("Blk", 361, 436);
//			g2d.drawString("Ftg", 413, 436);
//			g2d.drawString("Dmg", 463, 436);
			if(plr.getDefensiveEquipedWeaponIndicy() != -1){
				defensiveItem = plr.getFromInventory(plr.getDefensiveEquipedWeaponIndicy(),2);
				if(menuSlots.get(3).getPressed()){
					g2d.setColor(menuSlotColor);
					g2d.fillRect(menuSlot1.getX(), menuSlot1.getY(), menuSlot1.getWidth(), menuSlot1.getHeight());
				}
				else if(menuSlots.get(3).getHover()){
					g2d.setColor(menuSlotColor);
					g2d.fillRect(menuSlot1.getX(), menuSlot1.getY(), menuSlot1.getWidth(), menuSlot1.getHeight());
				}

				if(menuSlots.get(4).getPressed()){
					g2d.setColor(menuSlotColor);
					g2d.fillRect(menuSlot2.getX(), menuSlot2.getY(), menuSlot2.getWidth(), menuSlot2.getHeight());
				}
				else if(menuSlots.get(4).getHover()){
					g2d.setColor(menuSlotColor);
					g2d.fillRect(menuSlot2.getX(), menuSlot2.getY(), menuSlot2.getWidth(), menuSlot2.getHeight());
				}

				if(menuSlots.get(5).getPressed()){
					g2d.setColor(menuSlotColor);
					g2d.fillRect(menuSlot3.getX(), menuSlot3.getY(), menuSlot3.getWidth(), menuSlot3.getHeight());
				}
				else if(menuSlots.get(5).getHover()){
					g2d.setColor(menuSlotColor);
					g2d.fillRect(menuSlot3.getX(), menuSlot3.getY(), menuSlot3.getWidth(), menuSlot3.getHeight());
				}
				g2d.setColor(Color.WHITE);
				g2d.drawString(defensiveItem.toString(),350, 416);
				g2d.drawString("Move", 254, 436);
				g2d.drawString("Blk", 361, 436);
				g2d.drawString("Ftg", 413, 436);
				g2d.drawString("Dmg", 463, 436);
				int x = 254;
				int y = 441;
				for(int i = 0; i < 3; ++i){
					for(int j = 0; j < 4; ++j){
						if(j == 0){
							//menuSlots.get(i).setItemHeld(defensiveItem);
							//menuSlots.get(i).setType(2);
							x = 254;
							y += 20;
							g2d.drawString(defensiveItem.getMoveListIndice(i, j), x, y);
						}
						else{
							if(j == 1){
								menuSlots.get(i + 3).setInfo3(Integer.parseInt(defensiveItem.getMoveListIndice(i, j)));
								x += 115;
							}
							else if(j == 2){
								menuSlots.get(i + 3).setInfo2(Integer.parseInt(defensiveItem.getMoveListIndice(i, j)));
								x += 50;
							}
							else if(j == 3){
								menuSlots.get(i + 3).setInfo1(Integer.parseInt(defensiveItem.getMoveListIndice(i, j)));
								x += 50;
							}

							g2d.drawString(defensiveItem.getMoveListIndice(i, j), x, y);
						}
					}
				}
			}
			else{
				g2d.setColor(Color.WHITE);
				g2d.drawString("No defensive weapon equipped", 300, 416);
			}
		}
		else if(attackMenuNum == 3){
			g2d.setColor(Color.WHITE);
//			g2d.drawString("Name", 254, 436);
//			g2d.drawString("Usage", 361, 436);
//			g2d.drawString("+", 413, 436);
//			g2d.drawString("-", 463, 436);
			if(plr.getPotionEquipedIndicy(0) != -1 || plr.getPotionEquipedIndicy(1) != -1 || plr.getPotionEquipedIndicy(2) != -1){
				if(menuSlots.get(6).getPressed()){
					g2d.setColor(menuSlotColor);
					g2d.fillRect(menuSlot1.getX(), menuSlot1.getY(), menuSlot1.getWidth(), menuSlot1.getHeight());
				}
				else if(menuSlots.get(6).getHover()){
					g2d.setColor(menuSlotColor);
					g2d.fillRect(menuSlot1.getX(), menuSlot1.getY(), menuSlot1.getWidth(), menuSlot1.getHeight());
				}

				if(menuSlots.get(7).getPressed()){
					g2d.setColor(menuSlotColor);
					g2d.fillRect(menuSlot2.getX(), menuSlot2.getY(), menuSlot2.getWidth(), menuSlot2.getHeight());
				}
				else if(menuSlots.get(7).getHover()){
					g2d.setColor(menuSlotColor);
					g2d.fillRect(menuSlot2.getX(), menuSlot2.getY(), menuSlot2.getWidth(), menuSlot2.getHeight());
				}

				if(menuSlots.get(8).getPressed()){
					g2d.setColor(menuSlotColor);
					g2d.fillRect(menuSlot3.getX(), menuSlot3.getY(), menuSlot3.getWidth(), menuSlot3.getHeight());
				}
				else if(menuSlots.get(8).getHover()){
					g2d.setColor(menuSlotColor);
					g2d.fillRect(menuSlot3.getX(), menuSlot3.getY(), menuSlot3.getWidth(), menuSlot3.getHeight());
				}
				g2d.setColor(Color.WHITE);
				g2d.drawString("List of equipped potions",320, 416);
				g2d.drawString("Name", 254, 436);
				g2d.drawString("Affects", 361, 436);
				g2d.drawString("+", 423, 436);
				g2d.drawString("-", 470, 436);
				int x = 254;
				int y = 441;
				for(int i = 0; i < 3; ++i){
					//System.out.println("PotionEquipedIndicy(" + i + ") = " + plr.getPotionEquipedIndicy(i));
					if(plr.getPotionEquipedIndicy(i) != -1){
						//menuSlots.get(i).setItemHeld(potionItem);
						//menuSlots.get(i).setType(3);
						potionItem = plr.getFromInventory(plr.getPotionEquipedIndicy(i),3);
						x = 254;
						y += 20;
						g2d.drawString(potionItem.getMoveListIndice(0, 0), x, y);
						menuSlots.get(i + 6).setInfo4(potionItem.getMoveListIndice(1, 0));
						x += 110;
						g2d.drawString(potionItem.getMoveListIndice(0, 1), x, y);
						x += 55;
						menuSlots.get(i + 6).setInfo1(Integer.parseInt(potionItem.getMoveListIndice(0, 2)));
						g2d.drawString(potionItem.getMoveListIndice(0, 2), x, y);
						x += 50;
						menuSlots.get(i + 6).setInfo2(Integer.parseInt(potionItem.getMoveListIndice(0, 3)));
						g2d.drawString(potionItem.getMoveListIndice(0, 3), x, y);
					}
					else{
						y += 20;
					}
				}
			}
			else{
				g2d.setColor(Color.WHITE);
				g2d.drawString("No potions equipped", 335, 416);
			}
		}
	}

	public void fillInventoryDropDown(Graphics g2d){
		int test = 417;
		
		g2d.setColor(Color.WHITE);
		
		g2d.drawString("Item", 254, 416);
		g2d.drawString("---------------------------------", 254, 426);

		
		
		if(offensiveButton.getPressed()){ 
			int test2 = 0;
			g2d.drawString("Dmg", 350, 416);
			g2d.drawString("Ftg", 420, 416);
			g2d.drawString("Equip", 480, 416);
			g2d.setColor(menuColor);
			g2d.fillRect(offensiveButton.getX(), offensiveButton.getY(), offensiveButton.getWidth(), offensiveButton.getHeight());
			g2d.setColor(Color.WHITE);
			if(offensiveMenuNum == 1){
				if(plr.offensiveInventorySize() <= 2){
					for(int i = 0; i < plr.offensiveInventorySize(); ++i){
						drawInventoryItems(0,i,test,test2,1,g2d);
						test += 25;
						test2 += 25;
					}
				}
				else{
					for(int i = 0; i <= 2; ++i){
					drawInventoryItems(0,i,test,test2,1,g2d);
					test += 25;
					test2 += 25;
					}
				}	
			}
			else if(offensiveMenuNum == 2){
				if(plr.offensiveInventorySize() <= 5){
					for(int i = 3; i < plr.offensiveInventorySize(); ++i){
						drawInventoryItems(3,i,test,test2,1,g2d);     //int forLoopNum, int i, int test, int test2, int which, Graphics g2d
						test2 += 25;
						test += 25;
					}
				}
				else{
					for(int i = 3; i <= 5; ++i){
						drawInventoryItems(3,i,test,test2,1,g2d);
						test2 += 25;
						test += 25;
					}
				}
			}
			else if(offensiveMenuNum == 3){
				if(plr.offensiveInventorySize() <= 8){
					for(int i = 6; i < plr.offensiveInventorySize(); ++i){
						drawInventoryItems(6,i,test,test2,1,g2d);
						test2 += 25;
						test += 25;
					}
				}
				else{
					for(int i = 6; i <= 8; ++i){
						drawInventoryItems(6,i,test,test2,1,g2d);
						test2 += 25;
						test += 25;
					}
				}
			}
		}
		else{
			g2d.setColor(Color.LIGHT_GRAY);
			g2d.fillRect(offensiveButton.getX(), offensiveButton.getY(), offensiveButton.getWidth(), offensiveButton.getHeight());
		}
		if(defensiveButton.getPressed()){
			g2d.setColor(Color.WHITE);
			g2d.drawString("Blk", 345, 416);
			g2d.drawString("Dmg", 390, 416);
			g2d.drawString("Ftg", 435, 416);
			g2d.drawString("Equip", 480, 416);
			g2d.setColor(menuColor);
			g2d.fillRect(defensiveButton.getX(), defensiveButton.getY(), defensiveButton.getWidth(), defensiveButton.getHeight());
			g2d.setColor(Color.WHITE);
			int test2 = 0;
			if(defensiveMenuNum == 1){
				if(plr.defensiveInventorySize() <= 2){
					for(int i = 0; i < plr.defensiveInventorySize(); ++i){
						drawInventoryItems(0,i,test,test2,2,g2d);
						test += 25;
						test2 += 25;
						    //int forLoopNum, int test, int test2, int which, Graphics g2d
					}
				}
				else{
					for(int i = 0; i <= 2; ++i){
						drawInventoryItems(0,i,test,test2,2,g2d);
						test += 25;
						test2 += 25;
					}
				}
			}
			else if(defensiveMenuNum == 2){
				if(plr.defensiveInventorySize() <= 5){
					for(int i = 3; i < plr.defensiveInventorySize(); ++i){
						drawInventoryItems(3,i,test,test2,2,g2d);     //int forLoopNum, int i, int test, int test2, int which, Graphics g2d
						test2 += 25;
						test += 25;
					}
				}
				else{
					for(int i = 3; i <= 5; ++i){
						drawInventoryItems(3,i,test,test2,2,g2d);     //int forLoopNum, int i, int test, int test2, int which, Graphics g2d
						test2 += 25;
						test += 25;
					}
				}
			}
			else if(defensiveMenuNum == 3){
				if(plr.defensiveInventorySize() <= 8){
					for(int i = 6; i < plr.defensiveInventorySize(); ++i){
						drawInventoryItems(6,i,test,test2,2,g2d);
						test2 += 25;
						test += 25;
					}
				}
				else{
					for(int i = 6; i <= 8; ++i){
						drawInventoryItems(6,i,test,test2,2,g2d);
						test2 += 25;
						test += 25;
					}
				}
			}
		}
		else{
			g2d.setColor(Color.LIGHT_GRAY);
			g2d.fillRect(defensiveButton.getX(), defensiveButton.getY(), defensiveButton.getWidth(), defensiveButton.getHeight());
		}
		if(potionButton.getPressed()){
			g2d.setColor(Color.WHITE);
			g2d.drawString("Equip", 480, 416);
			g2d.setColor(menuColor);
			g2d.fillRect(potionButton.getX(), potionButton.getY(), potionButton.getWidth(), potionButton.getHeight());
			g2d.setColor(Color.WHITE);
			int test2 = 0;
			if(potionMenuNum == 1){
				if(plr.potionInventorySize() <= 2){
					for(int i = 0; i < plr.potionInventorySize(); ++i){
						drawInventoryItems(0,i,test,test2,3,g2d);
						test += 25;
						test2 += 25;
					}
				}
				else{
					for(int i = 0; i <= 2; ++i){
						drawInventoryItems(0,i,test,test2,3,g2d);
						test += 25;
						test2 += 25;
					}
				}
			}
			else if(potionMenuNum == 2){
				if(plr.potionInventorySize() <= 5){
					for(int i = 3; i < plr.potionInventorySize(); ++i){
							drawInventoryItems(3,i,test,test2,3,g2d);     //int forLoopNum, int i, int test, int test2, int which, Graphics g2d
							test2 += 25;
							test += 25;
					}
				}
				else{
					for(int i = 3; i <= 5; ++i){
							drawInventoryItems(3,i,test,test2,3,g2d);     //int forLoopNum, int i, int test, int test2, int which, Graphics g2d
							test2 += 25;
							test += 25;
					}
				}
			}
			else if(potionMenuNum == 3){
				if(plr.potionInventorySize() <= 8){
					for(int i = 6; i < plr.potionInventorySize(); ++i){
						drawInventoryItems(6,i,test,test2,3,g2d);
						test2 += 25;
						test += 25;
					}
				}
				else{
					for(int i = 6; i <= 8; ++i){
						drawInventoryItems(6,i,test,test2,3,g2d);
						test2 += 25;
						test += 25;
					}
				}
			}
		}
		else{
			g2d.setColor(Color.LIGHT_GRAY);
			g2d.fillRect(potionButton.getX(), potionButton.getY(), potionButton.getWidth(), potionButton.getHeight());
		}
		g2d.setColor(Color.BLACK);
		g2d.drawString("Offensive", 270, 395);    
		g2d.drawString("Defensive", 370, 395);
		g2d.drawString("Potion", 470, 395);
		g2d.drawRect(offensiveButton.getX(), offensiveButton.getY(), offensiveButton.getWidth(), offensiveButton.getHeight());
		g2d.drawRect(defensiveButton.getX(), defensiveButton.getY(), defensiveButton.getWidth(), defensiveButton.getHeight());
		g2d.drawRect(potionButton.getX(), potionButton.getY(), potionButton.getWidth(), potionButton.getHeight());
		g2d.setColor(Color.WHITE);

	}
	
	public void drawInventoryItems(int forLoopNum, int i, int test, int test2, int which, Graphics g2d){
		g2d.drawString(plr.getFromInventory(i,which).toString(), 254, (test + 25));
		if(which == 1){
			g2d.drawString(plr.getFromInventory(i,which).getStat1(), 350, (test + 25));
			g2d.drawString(plr.getFromInventory(i,which).getStat2(), 420, (test + 25));
		}
		else if(which == 2){
			g2d.drawString(plr.getFromInventory(i,which).getStat1(), 345, (test + 25));
			g2d.drawString(plr.getFromInventory(i,which).getStat2(), 390, (test + 25));
			g2d.drawString(plr.getFromInventory(i,which).getStat3(), 435, (test + 25));
		}
			equippedButtons.get(i - forLoopNum).setInfo1(-plr.getFromInventory(i,which).getIndex());   //equipped button info1 is items index place
			
			//System.out.println("in drawInventoryItems setting " + plr.getFromInventory(i,which).getIndex() + " to equippedButtons.get(" + (i - forLoopNum) + ")");
			
			equippedButtons.get(i - forLoopNum).setInfo2(-which); // addition
			equippedButtons.get(i - forLoopNum).setType(which);                                      //equipped button info2 is number for item type (1 is offensive)
//		    equippedButtons.get(i - forLoopNum).setItemHeld(plr.getFromInventory(i,which));
			g2d.setColor(Color.BLACK);
			g2d.drawRect(equippedButtons.get(i - forLoopNum).getX(), equippedButtons.get(i - forLoopNum).getY(), equippedButtons.get(i - forLoopNum).getWidth(), equippedButtons.get(i - forLoopNum).getHeight());
			if(plr.getFromInventory(equippedButtons.get(i - forLoopNum).getInfo1(), equippedButtons.get(i - forLoopNum).getInfo2()).getEquipped()){
//			if(equippedButtons.get(i - forLoopNum).getItemHeld().getEquipped()){
				//System.out.println("inventory item in position " + equippedButtons.get(i - forLoopNum).getInfo1() + " of type " + equippedButtons.get(i - forLoopNum).getInfo2() + " equipped status is + " + plr.getFromInventory(equippedButtons.get(i - forLoopNum).getInfo1(), equippedButtons.get(i - forLoopNum).getInfo2()).getEquipped());
				g2d.drawLine(490, 430 + test2, 500, 450 + test2);   //485,428
			}
			g2d.setColor(Color.WHITE);
	}

	/* 
	 * the game loop. Loops back up every 15 milliseconds
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(world == 1){     
			plr.setCollision(false);
			updatePlayer();    // calls on updatePlayer. Function moves player according to keystroke.
			checkCollisions();    // calls checkCollisions. Function checks if player collides with tree
		}

		else if(world == 2){
			if(!plr.getTurn()){
				++delay;
				if(delay == 50){
					if(enemyToBattle.getHealth() > 0){
						enemyToBattle.setBeginAttack(true);
						delay = 0;
					}
				}
			}
			if(finishedBattle){
				backToOverWorld();
			}
		}

		repaint();   //calls Paintcomponent. Function paints objects according to objects current x and y coordinates.
	}

	/*
	 * Function that updates the players x and y coordinates.
	 * 
	 */
	public void updatePlayer(){
		//System.out.println("in update player. Restrict down is currently " + plr.getRestrictDown());
		plr.move();

	}

	/*
	 * Function that checks if player collides with trees and logically stops player from moving in
	 * direction of tree
	 */
	public void checkCollisions(){
		Rectangle p1 = plr.getBounds();      // the rectangle that surrounds the player
		for(int i = 0; i < trees.size(); ++i){
			Rectangle t1 = trees.get(i).getBounds();     // the rectangle that surrounds the tree
			if(p1.intersects(t1)){               // if player collides with tree
				plr.setCollision(true);
				if(p1.getMaxX() < (t1.getMinX() + 3)){      //if player is to the left of the tree
					plr.restrictRight();                   //restrict player from moving to the right(so player can't morph through tree)
				}
				if(p1.getMinX() >(t1.getMaxX() - 3)){      // if player is to the right of the tree
					plr.restrictLeft();
				}
				if(p1.getMaxY() < (t1.getMinY() + 3)){    //if player is above the tree
					plr.restrictDown();
				}
				if(p1.getMinY() > (t1.getMaxY() - 3)){   //if player is below the tree
					plr.restrictUp();
				}
			}
			else{
				if(!plr.getCollision()){
					plr.noCollision();                     //needed to flip boolean values of restriction on players movement
				}
			}
			if(i < enemies.size()){
				Rectangle e1 = enemies.get(i).getBounds();
				if(p1.intersects(e1)){
					plr.setCollision(true);
					try {
						//enemyInBattle.add(enemies.get(i));
						num = i;
						i = enemies.size();
						create_Battle(enemies.get(num), 2);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else{
					if(!plr.getCollision()){
						plr.noCollision();                     //needed to flip boolean values of restriction on players movement
					}
				}
			}
		}
	}

	private class TAdapter extends KeyAdapter {

		/*
		 * Override the key adapter to follow Player classes keystrokes
		 * 
		 */
		@Override
		public void keyReleased(KeyEvent e) {
			plr.keyReleased(e);
		}

		@Override
		public void keyPressed(KeyEvent e) {
			plr.keyPressed(e);
		}
	}

}
