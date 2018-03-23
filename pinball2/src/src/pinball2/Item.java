package src.pinball2;

public class Item {
	
	int which;
	int index;
	int poIndex;
	String stat1;
	String stat2;
	String stat3;
	String [][] moveList = new String[3][4];
	String type;
	boolean equipped;
	//String [][]moveList;
	
	public Item(int which) {
		this.which = which;
		whichItem(which);
	}
	
	public void whichItem(int which){
		if(which == 1){                 // basic sword
			stat1 = "3-9";
			stat2 = "1-3";
			type = "offensive";
			moveList[0][0] = "Stab";
			moveList[0][1] = "3";
			moveList[0][2] = "1";
			moveList[0][3] = "";
			moveList[1][0] = "Slash";
			moveList[1][1] = "6";
			moveList[1][2] = "2";
			moveList[1][3] = "";
			moveList[2][0] = "Strong Hit";
			moveList[2][1] = "9";
			moveList[2][2] = "3";
			moveList[2][3] = "";
		}
			
		else if(which == 2){            // shield
			stat1 = "2-6";
			stat2 = "0-3";
			stat3 = "1-3";
			type = "defensive";
			moveList[0][0] = "Defensive Block";
			moveList[0][1] = "6";
			moveList[0][2] = "3";
			moveList[0][3] = "0";
			moveList[1][0] = "Neutral Block";
			moveList[1][1] = "3";
			moveList[1][2] = "1";
			moveList[1][3] = "1";
			moveList[2][0] = "Offensive Block";
			moveList[2][1] = "2";
			moveList[2][2] = "3";
			moveList[2][3] = "3";
		}
		else if(which == 3){           // basic health potion
			stat1 = "10";
			type = "potion";
			moveList[0][0] = "Def health";
			moveList[0][1] = "Self";
			moveList[0][2] = "10";
			moveList[0][3] = "0";
			moveList[1][0] = "self health 10";
			moveList[1][1] = "0";
			moveList[1][2] = "0";
			moveList[1][3] = "0";
			moveList[2][0] = "0";
			moveList[2][1] = "0";
			moveList[2][2] = "0";
			moveList[2][3] = "0";
		}
		
		else if(which == 4){           // basic mace
			stat1 = "6-12";
			stat2 = "2-4";
			type = "offensive";
			moveList[0][0] = "Slash";
			moveList[0][1] = "6";
			moveList[0][2] = "2";
			moveList[0][3] = "";
			moveList[1][0] = "Lunge";
			moveList[1][1] = "9";
			moveList[1][2] = "3";
			moveList[1][3] = "";
			moveList[2][0] = "Crush";
			moveList[2][1] = "12";
			moveList[2][2] = "4";
			moveList[2][3] = "";
		}
		
		else if(which == 5){            //basic dagger
			stat1 = "1-4";
			stat2 = "0-2";
			type = "offensive";
			moveList[0][0] = "Cut";
			moveList[0][1] = "1";
			moveList[0][2] = "0";
			moveList[0][3] = "";
			moveList[1][0] = "Stab";
			moveList[1][1] = "2";
			moveList[1][2] = "1";
			moveList[1][3] = "";
			moveList[2][0] = "Strike";
			moveList[2][1] = "4";
			moveList[2][2] = "2";
			moveList[2][3] = "";
		}
		else if(which == 6){             //Iron Sword
			stat1 = "12-18";
			stat2 = "4-6";
			type = "offensive";
			moveList[0][0] = "Stab";
			moveList[0][1] = "12";
			moveList[0][2] = "4";
			moveList[0][3] = "";
			moveList[1][0] = "Slash";
			moveList[1][1] = "15";
			moveList[1][2] = "5";
			moveList[1][3] = "";
			moveList[2][0] = "Strong Hit";
			moveList[2][1] = "18";
			moveList[2][2] = "6";
			moveList[2][3] = "";
		}
		else if(which == 7){           // basic stamina potion
			stat1 = "5";
			type = "potion";
			moveList[0][0] = "Def fatigue";
			moveList[0][1] = "Self";
			moveList[0][2] = "5";
			moveList[0][3] = "0";
			moveList[1][0] = "self fatigu 5";
			moveList[1][1] = "0";
			moveList[1][2] = "0";
			moveList[1][3] = "0";
			moveList[2][0] = "0";
			moveList[2][1] = "0";
			moveList[2][2] = "0";
			moveList[2][3] = "0";
		}
		else if(which == 8){           // basic fatigue potion
			stat1 = "3";
			type = "potion";
			moveList[0][0] = "Off fatigue";
			moveList[0][1] = "Enemy";
			moveList[0][2] = "0";
			moveList[0][3] = "5";
			moveList[1][0] = "enem fatigu 5";
			moveList[1][1] = "0";
			moveList[1][2] = "0";
			moveList[1][3] = "0";
			moveList[2][0] = "0";
			moveList[2][1] = "0";
			moveList[2][2] = "0";
			moveList[2][3] = "0";
		}
		else if(which == 9){           // basic fatigue potion
			stat1 = "2";
			type = "potion";
			moveList[0][0] = "Off health";
			moveList[0][1] = "Enemy";
			moveList[0][2] = "0";
			moveList[0][3] = "5";
			moveList[1][0] = "enem health 5";
			moveList[1][1] = "0";
			moveList[1][2] = "0";
			moveList[1][3] = "0";
			moveList[2][0] = "0";
			moveList[2][1] = "0";
			moveList[2][2] = "0";
			moveList[2][3] = "0";
		}
	}
	
	@Override
	public String toString(){
		if(which == 1){
			return "basic sword";
		}
		else if(which == 2){
			return "basic shield";
		}
		else if(which == 3){
			return "basic d hlth ptn";
		}
		else if(which == 4){
			return "basic mace";
		}
		else if(which == 5){
			return "basic dagger";
		}
		else if(which == 6){
			return "Iron sword";
		}
		else if(which == 7){
			return "basic d ftg ptn";
		}
		else if(which == 8){
			return "basic o ftg ptn";
		}
		else if(which == 9){
			return "basic o hlth ptn";
		}
		else{
			return "poop";
		}
	}
	
	public String getStat1(){
		return stat1;
	}
	
	public String getStat2(){
		return stat2;
	}
	public String getStat3(){
		return stat3;
	}
	
	public String getMoveListIndice(int a, int b){
		return moveList[a][b];
	}
	
	public String getType(){
		return type;
	}
	
	public void setIndex(int ind){
		index = ind;
	}
	
	public int getIndex(){
		return index;
	}
	
	public void setEquipped(boolean b){
		equipped = b;
	}
	
	public boolean getEquipped(){
		return equipped;
	}
	
	public void setPotionIndex(int ind){
		poIndex = ind;
	}
	
	public int getPotionIndex(){
		return poIndex;
	}

}


