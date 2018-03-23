package src.pinball2;


import java.io.IOException;

public class Button extends Sprite{
	
	boolean hover;
	boolean menu;
	int which;
	boolean pressed;

	public Button(int x, int y, int which) throws IOException{

		super(x,y);
		initBackground(which);
	}

	private void initBackground(int which) throws IOException{
		this.which = which;
		menu = false;
		if(which == 1){
			loadOverWorldImage("AttackButton.png");
			loadBattleModeImage("AttackButton2.png");
			getImageDimensions();
		}
		else if(which == 2){
			loadOverWorldImage("InventoryButton.png");
			loadBattleModeImage("InventoryButton2.png");
			getImageDimensions();
		}
		
	}
	
	public boolean getHover(){
		return hover;
	}
	
	public void setHover(boolean b){
		hover = b;
	}
	
	public void setMenu(boolean b){
		menu = b;
	}
	
	public boolean getMenu(){
		return menu;
	}
	
	public int getWhich(){
		return which;
	}
	
	public boolean getPressed(){
		return pressed;
	}
	
	public void setPressed(boolean b){
		pressed = b;
	}
}

