package ez_squeeze;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import ez_squeeze.topmenu.TopMenuScreen;

public class EzSqueeze extends JFrame{
	/**
	 * @author Nick Stanish
	 */
	private static final long serialVersionUID = 2902265810787080470L;
	public static final String version = "3.0.0";
	public JPanel topPanel, bottomPanel, cardPanel, contentPane;
	public JPanel menuCard, gameCard, optionsCard, helpCard, exitCard; // cards/views
	public JLabel titleLabel, lemonLabel, iceLabel, sugarLabel, cupLabel, walletLabel;
	public enum Cards{
		MENU, GAME, OPTIONS, ABOUT, HELP, EXIT
	}
	public EzSqueeze(){
		super("EZ Squeeze");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initComponents( this.getContentPane());
		this.pack();
		this.setVisible(true);
		this.setResizable(false);
		this.setSize(600, 600);
	}
	/**
	 * initialize the components of the program
	 * @param pane: content pane of the main frame
	 */
	private void initComponents(Container pane) {
		contentPane = new JPanel();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		topPanel = new JPanel();
		bottomPanel = new JPanel();
		cardPanel = new JPanel();
		cardPanel.setLayout(new CardLayout(1, 1));
		pane.add(contentPane);
		contentPane.add(topPanel);
		contentPane.add(cardPanel);
		contentPane.add(bottomPanel);
		initTop();
		initCards();
		initBottom();
	}
	private void initBottom() {
		bottomPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		bottomPanel.setBackground(Color.YELLOW);
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
            
        }
        catch(IOException ie){
        	if(Constants.debugging){
        		System.err.println("Media not found\n" + ie);
        	}
        	else{
        		JOptionPane.showMessageDialog(new JFrame(), "Media not found\n" + ie);
        		System.exit(0);
        	}
        	sugarLabel = new JLabel("Sugar: ");
            walletLabel = new JLabel("Money: ");
            lemonLabel = new JLabel("Lemons: ");
            iceLabel = new JLabel("Ice: ");
            cupLabel = new JLabel("Cups: ");
        }
		sugarLabel.setText(sugarLabel.getText() + Integer.MAX_VALUE);
		cupLabel.setText(cupLabel.getText() + Integer.MAX_VALUE);
		iceLabel.setText(iceLabel.getText() + Integer.MAX_VALUE);
		lemonLabel.setText(lemonLabel.getText() + Integer.MAX_VALUE);
		walletLabel.setText(walletLabel.getText() + Integer.MAX_VALUE);
		bottomPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
		JPanel[] pane = new JPanel[5];
		for(int x = 0; x<5; x++){
			pane[x] = new JPanel();
			pane[x].setPreferredSize(new Dimension(110,50));
		}
		pane[0].add(sugarLabel);
		pane[1].add(cupLabel);
		pane[2].add(iceLabel);
		pane[3].add(lemonLabel);
		pane[4].add(walletLabel);
		for(int x = 0; x<5; x++){
			bottomPanel.add(pane[x]);
			pane[x].setBackground(Color.YELLOW);;
		}
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
		menuCard = new TopMenuScreen();
		cardPanel.add(menuCard, Cards.MENU.name());
	}
}