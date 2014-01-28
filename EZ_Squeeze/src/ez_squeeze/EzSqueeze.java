package ez_squeeze;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import ez_squeeze.game.Constants;
import ez_squeeze.game.State;
import ez_squeeze.topmenu.TopMenuItem;
import ez_squeeze.topmenu.TopMenuScreen;

public class EzSqueeze extends JFrame{
	/**
	 * @author Nick Stanish
	 */
	private static final long serialVersionUID = 2902265810787080470L;
	public static final String version = "3.0.0";
	public JPanel topPanel, cardPanel, contentPane;
	public JPanel optionsCard, helpCard, exitCard; // cards/views
	public GameScreen gameCard;
	public TopMenuScreen menuCard;
	public JLabel titleLabel;
	public JFileChooser fileChooser;
	public enum Cards{
		MENU, GAME, OPTIONS, ABOUT, HELP, EXIT
	}
	public EzSqueeze(){
		super("EZ Squeeze");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initComponents( this.getContentPane());
		this.pack();
		this.setVisible(true);
		this.setPreferredSize(new Dimension(500,600));
		this.setMinimumSize(new Dimension(500,600));
		this.setSize(500, 600);
	}
	/**
	 * initialize the components of the program
	 * @param pane: content pane of the main frame
	 */
	private void initComponents(Container pane) {
		contentPane = new JPanel();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		topPanel = new JPanel();
		cardPanel = new JPanel();
		fileChooser = new JFileChooser(".");
		cardPanel.setLayout(new CardLayout(1, 1));
		pane.add(contentPane);
		contentPane.add(topPanel);
		contentPane.add(cardPanel);
		initTop();
		initCards();
		
	}
	
	private void initTop() {
		topPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		titleLabel = new JLabel("EZ Squeeze");
		titleLabel.setFont(new Font(Font.SERIF, Font.BOLD, 40));
		titleLabel.setForeground(Color.BLACK);
		topPanel.add(titleLabel);
		topPanel.setBackground(Color.YELLOW);
		
	}
	private void initCards(){
		cardPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		cardPanel.setBackground(Color.YELLOW);
		menuCard = new TopMenuScreen(this);
		cardPanel.add(menuCard, Cards.MENU.name());
		gameCard = new GameScreen(this,null);
		cardPanel.add(gameCard, Cards.GAME.name());
	}

	public void displayAbout() {
		// TODO Auto-generated method stub
		
	}
	public void displayExit() {
		if(Constants.debugging) System.err.println("Exitting ---TODO: show are you sure screen?");
		System.exit(0);
		
	}
	public void displayHelp() {
		// TODO Auto-generated method stub
		
	}
	public void saveState(State state){
		int val = fileChooser.showSaveDialog(this);
		switch(val){
		case JFileChooser.APPROVE_OPTION:
			File file = fileChooser.getSelectedFile();
			Constants.LOG("Save " + file.getName());
			//TODO: save state to file
			break;
		case JFileChooser.CANCEL_OPTION:
		default:
		}
	}
	public void displayLoad() {
		int val = fileChooser.showOpenDialog(this);
		switch(val){
		case JFileChooser.APPROVE_OPTION:
			File file = fileChooser.getSelectedFile();
			Constants.LOG("Load " + file.getName());
			
			//TODO: load state from file
			//create game from state
			break;
		case JFileChooser.CANCEL_OPTION:
		default:
		}

	}
	public void displayNew() {
		gameCard.loadState(new State());
		CardLayout cl = (CardLayout)(cardPanel.getLayout());
        cl.show(cardPanel, Cards.GAME.name());
		
	}
	public void displayOptions() {
		// TODO Auto-generated method stub
		
	}
	public void displayMenuFromGame() {
		menuCard.continueItem.enabled = true;
		CardLayout cl = (CardLayout)(cardPanel.getLayout());
        cl.show(cardPanel, Cards.MENU.name());
	}
	public void displayContinue(){
		CardLayout cl = (CardLayout)(cardPanel.getLayout());
        cl.show(cardPanel, Cards.GAME.name());
	}
}