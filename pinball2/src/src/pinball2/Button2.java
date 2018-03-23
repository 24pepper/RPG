package src.pinball2;

import java.awt.Rectangle;
import java.io.IOException;

public class Button2{
	
	boolean hover;
	boolean menu;
	int which;
	boolean pressed;
	String buttonName;
	int x;
	int y;
	int width;
	int height;
	int info1;
	int info2;
	int info3;
	String info4;
	int type;
//	Item itemHeld;

	public Button2(int x, int y, int height, int width, int which){
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		initBackground(which);
	}

	private void initBackground(int which){
		this.which = which;
		menu = false;
		if(which == 1){
			buttonName = "Action";
		}
		else if(which == 2){
			buttonName = "Inventory";
		}
		else if(which == 3){
			buttonName = "Stats";
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
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
    
    public String toString(){
    	return buttonName;
    }
    
    public int getInfo1(){
    	return info1;
    }
    
    public int getInfo2(){
    	return info2;
    }
    
    public void setInfo1(int what){
    	info1 = -what;
    }
    
    public void setInfo2(int what){
    	info2 = -what;
    }
    
    public void setInfo3(int what){
    	info3 = -what;
    }
    
    public void setInfo4(String what){
    	info4 = what;
    }
    
    public int getInfo3(){
    	return info3;
    }
    public String getInfo4(){
    	return info4;
    }
   public void setType(int what){
	type = what;
}

   public int getType(){
	return type;
}
//    public Item getItemHeld(){
//    	return itemHeld;
//    }
//    
//    public void setItemHeld(Item i){
//    	itemHeld = i;
//    }

}
