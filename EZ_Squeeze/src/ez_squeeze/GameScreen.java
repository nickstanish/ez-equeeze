package ez_squeeze;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ez_squeeze.game.PurchaseManager;
/**
 * 
 * @author Nick Stanish
 *
 */
public class GameScreen extends JPanel{
	public State state;
	public GameScreen(State state){
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.state = state;
		this.add(new PurchaseManager(state));
	}
	public static void main(String[] args){
		JFrame window = new JFrame("GameScreen");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GameScreen game = new GameScreen(new State());
		window.getContentPane().add(game);
		window.pack();
		window.setVisible(true);
		window.setSize(400, 600);
	}
}
