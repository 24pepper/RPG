package src.pinball2;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;

public class MyGameEx extends JFrame {
	static boolean start;

	public MyGameEx(boolean start) throws IOException {
		
		initUI(start);
	}
	
	private void initUI(boolean start) throws IOException {
		
		add(new Game());
		
	
		setSize(800, 600);
		setResizable(false);
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable(){
		
		@Override
		public void run() {
			
			
			
			MyGameEx ex;
			try {
				ex = new MyGameEx(start);
				ex.setVisible(true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		});

	}

}