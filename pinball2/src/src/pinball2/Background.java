package src.pinball2;

import java.io.IOException;

public class Background extends Sprite{
	
	int which;

	public Background(int x, int y, int which) throws IOException{

		super(x,y);
		initBackground(which);
	}

	private void initBackground(int which) throws IOException{
		
		if(which == 1){
			loadOverWorldImage("good_background.png");
			getImageDimensions();
		}
		else if(which == 2){
			loadOverWorldImage("battleMode_Background.png");
			getImageDimensions();
		}
	}
}
