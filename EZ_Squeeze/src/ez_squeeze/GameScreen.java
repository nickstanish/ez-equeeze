package ez_squeeze;

import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * 
 * @author Nick Stanish
 *
 */
public class GameScreen extends JPanel{
	public GameScreen(){
		
	}
	public static void main(String[] args){
		JFrame window = new JFrame("GameScreen");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GameScreen game = new GameScreen();
		window.getContentPane().add(game);
		window.pack();
		window.setVisible(true);
		window.setSize(400, 600);
	}
}
