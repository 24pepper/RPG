package src.pinball2;

public class Sword {

	int strength;
	int which;
	
	public Sword(int which,int strength1) {
		strength = strength1;
		which = this.which;
	}
	
	@Override
	public String toString(){
		if(which == 1){
			return "basic wooden spoon";
		}
		else{
			return "poop";
		}
	}
	
	public int get(){
		return strength;
	}

}
