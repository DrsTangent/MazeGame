package Game;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Runner {

	public static void main(String[] args) 
	{
		Game game = new Game();
		
		game.setPreferredSize(new Dimension((Game.WIDTH + Game.menuWIDTH), Game.HEIGHT));                    
        game.setMaximumSize(new Dimension((Game.WIDTH + Game.menuWIDTH), Game.HEIGHT ));
        game.setMinimumSize(new Dimension((Game.WIDTH + Game.menuWIDTH) , Game.HEIGHT));
		
		JFrame frame = new JFrame(Game.TITLE);
		frame.add(game);
		frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        game.start();
	}

}
