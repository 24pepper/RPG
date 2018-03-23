package src.pinball2;

import java.io.IOException;

public class Tree extends Sprite{

	public Tree(int x, int y) throws IOException{

		super(x,y);
		initBackground();
	}

	private void initBackground() throws IOException{
		
			loadOverWorldImage("tree.png");
			getImageDimensions();
	}
}
