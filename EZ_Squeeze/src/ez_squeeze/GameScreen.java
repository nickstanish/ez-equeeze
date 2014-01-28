package ez_squeeze;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import ez_squeeze.game.Constants;
import ez_squeeze.game.PurchaseManager;
import ez_squeeze.game.RecipeInputException;
import ez_squeeze.game.RecipePanel;
import ez_squeeze.game.State;
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
	public JButton menuButton, saveButton, startButton;
	public JPanel navPanel, startPanel;
	public JToolBar bottomToolBar;
	public JLabel lemonLabel, iceLabel, sugarLabel, cupLabel, walletLabel;
	public boolean mediaFound = false;
	public EzSqueeze game;
	public PurchaseManager purchaseManager;
	public RecipePanel recipePanel;
	public GameScreen(EzSqueeze game, State state){
		super();
		this.game = game;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.state = state;
		navPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		this.add(navPanel);
		initNav();
		this.purchaseManager = new PurchaseManager(this, state);
		this.add(purchaseManager);
		this.add(Box.createRigidArea(new Dimension(1,50)));
		recipePanel = new RecipePanel();
		this.add(recipePanel);
		startPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		initStart();
		this.add(startPanel);
		bottomToolBar = new JToolBar(JToolBar.HORIZONTAL);
		bottomToolBar.setFloatable(false);
		bottomToolBar.setMaximumSize(new Dimension(Integer.MAX_VALUE,30));
		this.add(bottomToolBar);
		initBottom();
	}
	private void initNav(){
		menuButton = 	new JButton("Menu");
		menuButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(game != null){
					game.displayMenuFromGame();
				}
			}
		});
		saveButton = 	new JButton("Save");
		saveButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				game.saveState(state);
			}
		});
		navPanel.add(menuButton);
		navPanel.add(saveButton);
	}
	private void initStart(){
		startButton =	new JButton("Start");
		startPanel.add(startButton);
		startButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				startDay(); 
			}
		});
	}
	protected void startDay() {
		// TODO start day logic
		// grab recipe values
		try{
			state.recipe = recipePanel.getRecipe();
			Constants.LOG(state.recipe.toString());
		}
		catch(RecipeInputException r){
			Constants.LOG(r.toString());
		}
		updateStateLabels(state);
		
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
		bottomToolBar.setLayout(new FlowLayout(FlowLayout.LEADING));
		Dimension separator = new Dimension(15,1);
		bottomToolBar.add(lemonLabel);
		bottomToolBar.addSeparator(separator);
		bottomToolBar.add(sugarLabel);
		bottomToolBar.addSeparator(separator);
		bottomToolBar.add(iceLabel);
		bottomToolBar.addSeparator(separator);
		bottomToolBar.add(cupLabel);
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
			walletLabel.setText(Constants.priceFormat.format(state.money));
			sugarLabel.setText(state.sugar + "");
			cupLabel.setText(state.cups + "");
		}
		else{
			sugarLabel.setText("Sugar: " + state.sugar);
            walletLabel.setText("Money: " + Constants.priceFormat.format(state.money));
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
		this.purchaseManager.state = state;
		updateStateLabels(state);
	}
	public static void main(String[] args){
		JFrame window = new JFrame("GameScreen");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GameScreen game = new GameScreen(null, new State());
		window.getContentPane().add(game);
		window.pack();
		window.setVisible(true);
		window.setSize(400, 600);
	}
}
