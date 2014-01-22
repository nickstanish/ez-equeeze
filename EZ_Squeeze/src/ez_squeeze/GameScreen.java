package ez_squeeze;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import ez_squeeze.game.PurchaseManager;
/**
 * 
 * @author Nick Stanish
 *
 */
public class GameScreen extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5809625874051644833L;
	public State state;
	public JToolBar bottomToolBar;
	public JLabel lemonLabel, iceLabel, sugarLabel, cupLabel, walletLabel;
	public boolean mediaFound = false;
	public GameScreen(State state){
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.state = state;
		this.add(new PurchaseManager(state));
		this.add(Box.createRigidArea(new Dimension(1,250)));
		bottomToolBar = new JToolBar(JToolBar.HORIZONTAL);
		bottomToolBar.setFloatable(false);
		bottomToolBar.setMaximumSize(new Dimension(Integer.MAX_VALUE,30));
		this.add(bottomToolBar);
		initBottom();
	}
	private void initBottom() {
		//bottomToolBar.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		//bottomToolBar.setBackground(Color.YELLOW);
		File file;
		BufferedImage sugarImage, cupImage, iceImage, walletImage, lemonImage;
		try{
			// TODO: revise images
            file = new File("media/sugar.png");
            sugarImage = ImageIO.read(file);
            file = new File("media/cup.png");
            cupImage = ImageIO.read(file);
            file = new File("media/ice.png");
            iceImage = ImageIO.read(file);
            file = new File("media/lemon.png");
            lemonImage = ImageIO.read(file);
            file = new File("media/money.png");
            walletImage = ImageIO.read(file);
            sugarLabel = new JLabel(new ImageIcon(sugarImage));
            walletLabel = new JLabel(new ImageIcon(walletImage));
            lemonLabel = new JLabel(new ImageIcon(lemonImage));
            iceLabel = new JLabel(new ImageIcon(iceImage));
            cupLabel = new JLabel(new ImageIcon(cupImage));
            mediaFound = true;
            
        }
        catch(IOException ie){
        	if(Constants.debugging){
        		System.err.println("Media not found\n" + ie);
        	}
        	else{
        		JOptionPane.showMessageDialog(new JFrame(), "Media not found\n" + ie);
        		System.exit(0);
        	}
        	mediaFound = false;
        	sugarLabel = new JLabel("Sugar: ");
            walletLabel = new JLabel("Money: ");
            lemonLabel = new JLabel("Lemons: ");
            iceLabel = new JLabel("Ice: ");
            cupLabel = new JLabel("Cups: ");
        }
		sugarLabel.setText(sugarLabel.getText() + Integer.MAX_VALUE);
		//sugarLabel.setFont(Constants.normalFont);
		cupLabel.setText(cupLabel.getText() + Integer.MAX_VALUE);
		//cupLabel.setFont(Constants.normalFont);
		iceLabel.setText(iceLabel.getText() + Integer.MAX_VALUE);
		//iceLabel.setFont(Constants.normalFont);
		lemonLabel.setText(lemonLabel.getText() + Integer.MAX_VALUE);
		//lemonLabel.setFont(Constants.normalFont);
		walletLabel.setText(walletLabel.getText() + Integer.MAX_VALUE);
		//walletLabel.setFont(Constants.normalFont);
		bottomToolBar.setLayout(new FlowLayout(FlowLayout.LEADING));
		Dimension separator = new Dimension(20,1);
		bottomToolBar.add(sugarLabel);
		bottomToolBar.addSeparator(separator);
		bottomToolBar.add(cupLabel);
		bottomToolBar.addSeparator(separator);
		bottomToolBar.add(iceLabel);
		bottomToolBar.addSeparator(separator);
		bottomToolBar.add(lemonLabel);
		bottomToolBar.addSeparator(separator);
		bottomToolBar.add(walletLabel);
		bottomToolBar.addSeparator(separator);
		updateStateLabels(state);
	}
	/**
	 * update all the variables in the bottom panel
	 * @param state
	 */
	public void updateStateLabels(State state){
		if(state == null) return;
		if(mediaFound){
			lemonLabel.setText(state.lemons + "");
			iceLabel.setText(state.ice + "");
			walletLabel.setText(state.money + ""); // TODO: format to money
			sugarLabel.setText(state.sugar + "");
			cupLabel.setText(state.cups + "");
		}
		else{
			sugarLabel.setText("Sugar: " + state.sugar);
            walletLabel.setText("Money: " + state.money);
            lemonLabel.setText("Lemons: " + state.lemons);
            iceLabel.setText("Ice: " + state.ice);
            cupLabel.setText("Cups: "+ state.cups);
		}
		
		
	}
	/**
	 * update the state of the game in order to play a new, or load a game
	 * @param state
	 */
	public void loadState(State state){
		this.state = state;
		updateStateLabels(state);
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
