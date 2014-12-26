package ez_squeeze.game;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import ez_squeeze.GameScreen;
import ez_squeeze.game.exceptions.NotEnoughMoneyException;
/**
 * 
 * @author Nick Stanish
 *
 */
public class PurchaseManager extends JPanel{
	//TODO: add more accuracy to buying items (maybe * 100 and then +/- before converting back to decimal to avoid rounding 0.06999999999999651 )
	// TODO: or make everything even
	// TODO: stats: amounts purchased, $ spent, clicks to buybutton
	/**
	 * 
	 */
	private static final long serialVersionUID = -5042582420031796854L;
	public State state;
	public JToggleButton lemonsButton, iceButton, sugarButton, cupsButton;
	public JButton smallButton, mediumButton, largeButton;
	public JLabel smallSizeLabel, smallPriceLabel, mediumSizeLabel, mediumPriceLabel, largeSizeLabel, largePriceLabel;
	public ButtonGroup purchaseToggles;
	public PurchaseItems lastSelected = PurchaseItems.Lemons;
	public GameScreen game;
	/**
	 * 
	 * Possible items to buy; simplifies checks with switches
	 *
	 */
	public static enum PurchaseItems{
		Lemons, Sugar, Ice, Cups
	}
	/**
	 * 
	 * possible sizes to buy; simplifies checks with switches, and purchase method
	 *
	 */
	public static enum PurchaseSize{
		Small, Medium, Large
	}
	/**
	 * Creates a jpanel manager for purchasing items and adding them to the game state
	 * @param state: game state that is updated to reflect purchases in panel
	 */
	public PurchaseManager(GameScreen game, State state){
		super();
		this.game = game;
		this.state = state;
		initComponents();
	
	}
	/**
	 * initializes all the components of the class
	 * split into several methods for clarity
	 */
	private void initComponents(){
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		initToggles(this);
		initItemList(this);
	}
	private void initItemList(JPanel root) {
		JPanel paddingPanel = new JPanel();
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JPanel pane[] = new JPanel[4];
		for(int i = 0; i < 4; i++){
			pane[i] = new JPanel();
			GridLayout grid = new GridLayout(0,3);
			grid.setHgap(20);
			pane[i].setLayout(grid);
			panel.add(pane[i]);
			panel.add(Box.createRigidArea(new Dimension(1,2)));
		}
		smallButton = new JButton("Buy");
		smallButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				purchase(PurchaseSize.Small);
				if(game != null) game.updateStateLabels(state);
			}
		});
		mediumButton = new JButton("Buy");
		mediumButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				purchase(PurchaseSize.Medium);
				if(game != null) game.updateStateLabels(state);
			}
		});
		largeButton = new JButton("Buy");
		largeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				purchase(PurchaseSize.Large);
				if(game != null) game.updateStateLabels(state);
			}
		});
		smallSizeLabel = new JLabel("" + Integer.MAX_VALUE);
		smallPriceLabel = new JLabel("$" + Integer.MAX_VALUE);
		mediumSizeLabel = new JLabel("" + Integer.MAX_VALUE);
		mediumPriceLabel = new JLabel("$" + Integer.MAX_VALUE);
		largeSizeLabel = new JLabel("" + Integer.MAX_VALUE);
		largePriceLabel = new JLabel("$" + Integer.MAX_VALUE);
		pane[0].add(new JLabel("Amount"));
		pane[0].add(new JLabel("Price"));
		pane[1].add(smallSizeLabel);
		pane[1].add(smallPriceLabel);
		pane[1].add(smallButton);
		pane[2].add(mediumSizeLabel);
		pane[2].add(mediumPriceLabel);
		pane[2].add(mediumButton);
		pane[3].add(largeSizeLabel);
		pane[3].add(largePriceLabel);
		pane[3].add(largeButton);
		paddingPanel.add(panel);
		root.add(paddingPanel);
		updateView(PurchaseItems.Lemons);
		
	}
	private void initToggles(JPanel root){
		ActionListener toggleListener = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				updateView();
			}
		};
		purchaseToggles = new ButtonGroup();
		lemonsButton = new JToggleButton("Lemons", true);
		lemonsButton.addActionListener(toggleListener);
		iceButton = new JToggleButton("Ice");
		iceButton.addActionListener(toggleListener);
		sugarButton = new JToggleButton("Sugar");
		sugarButton.addActionListener(toggleListener);
		cupsButton = new JToggleButton("Cups");
		cupsButton.addActionListener(toggleListener);
		purchaseToggles.add(lemonsButton);
		purchaseToggles.add(sugarButton);
		purchaseToggles.add(iceButton);
		purchaseToggles.add(cupsButton);
		FlowLayout flowLayout = new FlowLayout(FlowLayout.LEADING);
		flowLayout.setHgap(0);
		JPanel togglePanel = new JPanel(flowLayout);
		togglePanel.add(Box.createRigidArea(new Dimension(2,1)));
		togglePanel.add(new JLabel("Purchase "));
		togglePanel.add(Box.createRigidArea(new Dimension(5,1)));
		togglePanel.add(lemonsButton);
		togglePanel.add(sugarButton);
		togglePanel.add(iceButton);
		togglePanel.add(cupsButton);
		root.add(togglePanel);
	}
	/**
	 * responds to changes made to the toggleButtons and reacts to them 
	 */
	public void updateView(){
		Enumeration<AbstractButton> elements = purchaseToggles.getElements();
		while(elements.hasMoreElements()){
			AbstractButton toggle = elements.nextElement();
			Constants.LOG(toggle.isSelected() + " \t" + toggle.getText());
			if(toggle.isSelected()){
				try{
					PurchaseItems item = PurchaseItems.valueOf(toggle.getText());
					updateView(item);
				}
				catch(Exception e){
					Constants.LOG(e.getMessage());
					updateView(PurchaseItems.Lemons);
				}
			}
		}
	}
	/**
	 * updates the contents of the labels for the purchase screen
	 * @param item
	 */
	private void updateView(PurchaseItems item) {
		if(item == null){
			item = PurchaseItems.Lemons;
		}
		switch(item){
			case Cups:
				smallSizeLabel.setText(""  + Constants.smallSizeCups);
				smallPriceLabel.setText(Constants.priceFormat.format(Constants.smallPriceCups));
				mediumSizeLabel.setText("" + Constants.mediumSizeCups);
				mediumPriceLabel.setText(Constants.priceFormat.format(Constants.mediumPriceCups));
				largeSizeLabel.setText("" + Constants.largeSizeCups);
				largePriceLabel.setText(Constants.priceFormat.format(Constants.largePriceCups));
				purchaseToggles.setSelected(cupsButton.getModel(), true);
				break;
			case Ice:
				smallSizeLabel.setText(""  + Constants.smallSizeIce);
				smallPriceLabel.setText(Constants.priceFormat.format(Constants.smallPriceIce));
				mediumSizeLabel.setText("" + Constants.mediumSizeIce);
				mediumPriceLabel.setText(Constants.priceFormat.format(Constants.mediumPriceIce));
				largeSizeLabel.setText("" + Constants.largeSizeIce);
				largePriceLabel.setText(Constants.priceFormat.format(Constants.largePriceIce));
				purchaseToggles.setSelected(iceButton.getModel(), true);
				break;
			case Sugar:
				smallSizeLabel.setText(""  + Constants.smallSizeSugar);
				smallPriceLabel.setText(Constants.priceFormat.format(Constants.smallPriceSugar));
				mediumSizeLabel.setText("" + Constants.mediumSizeSugar);
				mediumPriceLabel.setText(Constants.priceFormat.format(Constants.mediumPriceSugar));
				largeSizeLabel.setText("" + Constants.largeSizeSugar);
				largePriceLabel.setText(Constants.priceFormat.format(Constants.largePriceSugar));
				purchaseToggles.setSelected(sugarButton.getModel(), true);
				break;
			case Lemons:
				smallSizeLabel.setText(""  + Constants.smallSizeLemons);
				smallPriceLabel.setText(Constants.priceFormat.format(Constants.smallPriceLemons));
				mediumSizeLabel.setText("" + Constants.mediumSizeLemons);
				mediumPriceLabel.setText(Constants.priceFormat.format(Constants.mediumPriceLemons));
				largeSizeLabel.setText("" + Constants.largeSizeLemons);
				largePriceLabel.setText(Constants.priceFormat.format(Constants.largePriceLemons));
				purchaseToggles.setSelected(lemonsButton.getModel(), true);
				break;	
		}
		lastSelected = item;
	}
	/**
	 * event handler to buy items
	 * checks to make sure user has enough money and then adds the selected amount to their state
	 * this can most likely be cleaned up by branching classes and data as such
	 * [item]-> [size]-> { [amount], [price] }
	 * @param size: size of item to buy
	 */
	public void purchase(PurchaseSize size){
		if(size == null){
			Constants.LOG("Null purchase size.");
			return;
		}
		int amount = 0;
		double cost = 0;
		switch(size){
			case Small:
				switch(lastSelected){
					case Cups:
						amount = Constants.smallSizeCups;
						cost = Constants.smallPriceCups;
						break;
					case Ice:
						amount = Constants.smallSizeIce;
						cost = Constants.smallPriceIce;
						break;
					case Lemons:
						amount = Constants.smallSizeLemons;
						cost = Constants.smallPriceLemons;
						break;
					case Sugar:
						amount = Constants.smallSizeSugar;
						cost = Constants.smallPriceSugar;
						break;
				}
				break;
			case Medium:
				switch(lastSelected){
				case Cups:
					amount = Constants.mediumSizeCups;
					cost = Constants.mediumPriceCups;
					break;
				case Ice:
					amount = Constants.mediumSizeIce;
					cost = Constants.mediumPriceIce;
					break;
				case Lemons:
					amount = Constants.mediumSizeLemons;
					cost = Constants.mediumPriceLemons;
					break;
				case Sugar:
					amount = Constants.mediumSizeSugar;
					cost = Constants.mediumPriceSugar;
					break;
				}
				break;
			case Large:
				switch(lastSelected){
				case Cups:
					amount = Constants.largeSizeCups;
					cost = Constants.largePriceCups;
					break;
				case Ice:
					amount = Constants.largeSizeIce;
					cost = Constants.largePriceIce;
					break;
				case Lemons:
					amount = Constants.largeSizeLemons;
					cost = Constants.largePriceLemons;
					break;
				case Sugar:
					amount = Constants.largeSizeSugar;
					cost = Constants.largePriceSugar;
					break;
				}
				break;
		}
		if(state != null){
			if(state.money >= cost ){
				state.money -= cost;
				 switch(lastSelected){
					 case Cups:
						 if(itemAtMax(state.cups)){
							 //refund purchase
							 state.money += cost;
							 //TODO: tell user they cannot buy more
						 }
						 else state.cups = checkAddItem(state.cups, amount, lastSelected);
						 break;
					 case Ice:
						 if(itemAtMax(state.ice)){
							 //refund purchase
							 state.money += cost;
							 //TODO: tell user they cannot buy more
						 }
						 else state.ice = checkAddItem(state.ice, amount, lastSelected);
						 break;
					 case Lemons:
						 if(itemAtMax(state.lemons)){
							 //refund purchase
							 state.money += cost;
							 //TODO: tell user they cannot buy more
						 }
						 else state.lemons = checkAddItem(state.lemons, amount, lastSelected);
						 break;
					 case Sugar:
						 if(itemAtMax(state.sugar)){
							 //refund purchase
							 state.money += cost;
							 //TODO: tell user they cannot buy more
						 }
						 else state.sugar = checkAddItem(state.sugar, amount, lastSelected);
						 break;
				 }
				 
			}
			else{
				if(Constants.debugging) System.err.println("Not enough money");
				NotEnoughMoneyException neme = new NotEnoughMoneyException(cost, state.money);
				
				// TODO: display to user that they cannot afford it or even disable buttons! with updateView
				if(Constants.debugging) System.err.println("TODO: tell user that they can't afford it " + neme);
			}
			if(Constants.debugging) state.print();
		}
		
	}
	/**
	 * makes sure that added amount when purchasing doesnt go over the max int value and cycle negative
	 * @param amount
	 * @param add
	 * @param item
	 * @return
	 */
	private int checkAddItem(int amount, int add, PurchaseItems item){
		if(amount > Integer.MAX_VALUE - add){
			 amount = Integer.MAX_VALUE;
			 Constants.LOG("Bought max number of " + item.name() + "\nTODO show error");
			 // TODO: show error - cannot buy more
			 // TODO: make test
		 }
		 else{
			 amount += add;
		 }
		return amount;
	}
	private boolean itemAtMax(int amount){
		return amount == Integer.MAX_VALUE;
	}
	public static void main(String[] args){
		JFrame frame = new JFrame("Purchase Manager");
		frame.getContentPane().add(new PurchaseManager(null, new State()));
		frame.pack();
		frame.setVisible(true);
		frame.setSize(400,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
