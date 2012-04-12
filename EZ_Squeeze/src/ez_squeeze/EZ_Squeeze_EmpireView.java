/*
 * GNU GPL v3
 * Copyright 2011-2012 Nick Stanish
 */

package ez_squeeze;

import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;


/**
 * The application's main frame.
 */
public class EZ_Squeeze_EmpireView extends FrameView implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6432186054637421827L;
	private static double version = 2.0; 
    private Pitcher pitcher = new Pitcher();
    private Day date = new Day();
    private int day = date.getNumberDay();
    private double netGain = 0;
    private double dailyGain = 0;
    private static int lemons = 0;
    private static int sugar = 0;
    private static int ice = 0;
    private static int cups = 0;
    private static int recipeLemons = 0;
    private static int recipeSugar = 0;
    private static int recipeIce = 0;
    private int salesTotal = 0;
    private int salesToday = 0;
    private int totalDays = 0;
    private int smAmountLemons = 15;
    private int mdAmountLemons = 40;
    private int lgAmountLemons = 100;
    private int smAmountIce = 75;
    private int mdAmountIce = 200;
    private int lgAmountIce = 500;
    private int smAmountCups = 100;
    private int mdAmountCups = 250;
    private int lgAmountCups = 600;
    private int smAmountSugar = 12;
    private int mdAmountSugar = 30;
    private int lgAmountSugar = 75;
    private double money = 25.00; //starting money
    private double price = 0.25; //default price
    private double lemonsSmCost = 2.50;
    private double lemonsMdCost = 6.00;
    private double lemonsLgCost = 13.00;
    private double sugarSmCost = 1.50;
    private double sugarMdCost = 3.50;
    private double sugarLgCost = 8.00;
    private double iceSmCost = 1.00;
    private double iceMdCost = 2.39;
    private double iceLgCost = 4.83;
    private double cupsSmCost = .70;
    private double cupsMdCost = 1.50;
    private double cupsLgCost = 3.25;
    private double totalSatisfaction = 0;
    private String item = "";
    private Person[] person;
    private int itemId;
    @SuppressWarnings("unused")
	private void parseUpgrade(int a){
         try{
        upgradeParser up = new upgradeParser(new File("upgrades.xml"));
        up.printNode(a);
        }
        catch(Exception e){System.out.println(e);}
    }
    private void print(String string){
    	String s = customerSalesTextArea.getText() + "\n";
    	customerSalesTextArea.setText(s + string);
    }
    private void println(String string){
    	customerSalesTextArea.setText(string);
    }
    private void save(){
    	new File("saves").mkdir();
        FileSystemView fsv = new RestrictedFileChooser(new File("saves\\"));
        JFileChooser fc = new JFileChooser(fsv.getHomeDirectory(),fsv);
        int returnVal = fc.showSaveDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION){
            File file = fc.getSelectedFile();
            if (file.getName().indexOf(".")==-1){
                file = new File(file.getAbsolutePath() + ".sav"); //add extension if necessary
            }
        
    	try{
    		FileOutputStream fos = new FileOutputStream(file);
    		ObjectOutputStream out = new ObjectOutputStream(fos);
    		out.writeObject(pitcher);
    		out.writeObject(date);
    		out.writeObject(recipeLemons);
    		out.writeObject(recipeIce);
    		out.writeObject(recipeSugar);
    		out.writeObject(netGain);
    		out.writeObject(dailyGain);
    		out.writeObject(lemons);
    		out.writeObject(sugar);
    		out.writeObject(ice);
    		out.writeObject(cups);
    		out.writeObject(salesTotal);
    		out.writeObject(salesToday);
    		out.writeObject(totalDays);
    		out.writeObject(money);
    		out.writeObject(person);
    		out.writeObject(totalSatisfaction);
    		out.writeObject(price);
    		out.close();
    		fos.close();
    		JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "Saved! \n");
    	}
    	catch(IOException ioe){
    		ioe.printStackTrace();
    		JFrame frame = new JFrame();
    		JOptionPane.showMessageDialog(frame, "Unable to save \n");
    	}
        }
        else{
            JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame, "Save Cancelled \n");
        }
    }
    private void load(){
    	new File("saves").mkdir();
        FileSystemView fsv = new RestrictedFileChooser(new File("saves\\"));
        JFileChooser fc = new JFileChooser(fsv.getHomeDirectory(),fsv);
        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
	    	try{
	    		FileInputStream fis = new FileInputStream(file);
	    		ObjectInputStream in = new ObjectInputStream(fis);
	    		try {
					pitcher = (Pitcher)in.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					date = (Day)in.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				day = date.getNumberDay();
				try {
					recipeLemons = (Integer) in.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					recipeIce = (Integer) in.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					recipeSugar = (Integer) in.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					netGain = (Double) in.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					dailyGain = (Double) in.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					lemons = (Integer) in.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					sugar = (Integer) in.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					ice = (Integer) in.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					cups = (Integer) in.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					salesTotal = (Integer) in.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					salesToday = (Integer) in.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					totalDays = (Integer) in.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					money = (Double) in.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					person = (Person[]) in.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					totalSatisfaction = (Double) in.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					price = (Double) in.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				in.close();
				fis.close();
				 JFrame frame = new JFrame();
		            JOptionPane.showMessageDialog(frame, "Loaded! \n");
	    	}
	    	catch(IOException ioe){
	    		
	    	}
        }
        else{
            JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame, "Load Cancelled \n");
        }
    	iceRecipeBox.setText(recipeIce+ "");
    	lemonsRecipeBox.setText(recipeLemons + "");
    	sugarRecipeBox.setText(recipeSugar + "");
    	updateBoxes();
    }
    @SuppressWarnings("unused")
    private double weatherProb(){ //very basic form
        Random rdm = new Random();
        int a = rdm.nextInt(71) + 30; //makes random int [30,100]
        double weather = a * 1;
        // System.out.println("weather: " + weather);
        return weather;
    }
    private double averageSatisfaction(){
    	int x = 0;
    	while(x < person.length){
    		totalSatisfaction += person[x].getSatisfaction();
    		x++;
    	}
    	totalSatisfaction /= person.length;
    	totalSatisfaction = formatDecimal(totalSatisfaction,2);
    	return totalSatisfaction;
    }
    private void enabledStatus(boolean x){ //quickly change status of buttons/other objects
        //use by saying enabledStatus(false);
        lemonsRecipeBox.setEnabled(x);
        iceRecipeBox.setEnabled(x);
        sugarRecipeBox.setEnabled(x);
        priceBox.setEnabled(x);
        startDayButton.setEnabled(x);
        purchaseButton.setEnabled(x);
        purchaseItemChoice.setEnabled(x);
    }
    private void updateBoxes(){
    	iceBox.setText(ice + "");
    	lemonsBox.setText(lemons + "");
    	moneyBox.setText(formatDecimal(money,2) + "");
    	sugarBox.setText(sugar + "");
    	dayBox.setText(totalDays + "");
    	cupsBox.setText(cups + "");
    	salesTodayBox.setText(salesToday + "");
    	salesTotalBox.setText(salesTotal + "");
    	priceBox.setText(formatDecimal(price,2) + "");

    }
    private void getRecipeInfo(){
        recipeLemons = Integer.parseInt(lemonsRecipeBox.getText());
        recipeIce = Integer.parseInt(iceRecipeBox.getText());
        recipeSugar = Integer.parseInt(sugarRecipeBox.getText());
        price = Double.parseDouble(priceBox.getText());
    }
    @SuppressWarnings("unused")
	private double inverse(double num){
			num = 0 - num;
			return num;
		}
    @SuppressWarnings("unused")
    private double power(double num, int x){ //base number, to the power of x
			double result;
			result = num;
			for(int a = 1; a < x; a++){
				result *= num;
			}
			if (x == 0){
				result = 1;
			}
			return result;
		}

    private void startDay(){
    	println("Today is: " + date.getDay());
    	enabledStatus(false); //disable buttons that could possibly change output of they day
    	getRecipeInfo();
    	dailyGain = 0;
    	salesToday = 0;
    	int visits = 0;
    	totalDays = date.getTotalDays();
    	updateBoxes();
    	Recipe recipe = new Recipe(recipeLemons, recipeSugar);
    	String today = date.getDay();
    	for (int x = 0; x <100; x++){
    	String[] array = {price+""};
        double weather = 86;
        	
        	if(person[x].visits(today)){
        		visits++;
        		print("******");
        		if(person[x].willPurchase(array)){
        			if (cups > 0){
        				pitcher.serve();
	        			salesToday++;
	        			print(person[x].getName() + " purchased lemonade.");
	        			person[x].Drink(recipe.getCriticism(), recipe.getFlavor(), weather, 3);
	        			print("Satisfaction: "+ person[x].getSatisfaction());
	        			print("Reaction: "+ person[x].getReaction());
	        			money += price;
	        			dailyGain += price;
	        			updateBoxes();
        			}
        			else{
        				print("Not enough cups");
        			}
        		}
        		else{
        			print(person[x].getName() + " didn't buy lemonade.\nReason: " + person[x].getReason());
        		}
        	}
        	
    	}
    	print( "sales: " + salesToday + " / " + visits + " visits");
    	print("Company Satisfaction: " + averageSatisfaction());
        
        getRecipeInfo();
        salesTotal += salesToday;
        date.nextDay();
		enabledStatus(true); //reenable at the end of the day
		ice = 0; //melts
		updateBoxes();
		pitcher.dump();
		netGain += dailyGain;
        }
    private double formatDecimal(double formatThis,int x){ //num you wanted formatted, x many after decimal point
	   		if (x > 0){
				int count = 0;
				String decimals = "#.";
				while(count < x){
						decimals += "#";
						count++;
				}
				DecimalFormat form = new DecimalFormat(decimals);
				formatThis = Double.valueOf(form.format(formatThis));
	   		}
	   		return formatThis;

	   	}
    private int formatInt(double formatThis){ //put in a double and get a integer
	   		int formatted = 0;
	   		DecimalFormat form = new DecimalFormat("#");
	   		formatted = Integer.valueOf(form.format(formatThis));
	   		return formatted;
	   }
    private void purchase(){
        
        // TODO add your handling code here:
        //buy btton
        //popup a dialog box confirming purchase
        //clear spinners
        //subtract from money and add to supplies
        double totalCost = 0;
        int a = Integer.parseInt(smallSpinner.getValue().toString());
        int b = Integer.parseInt(mediumSpinner.getValue().toString());
        int c = Integer.parseInt(largeSpinner.getValue().toString());
        double priceA = Double.parseDouble(smallItemPriceBox.getText());
        double priceB = Double.parseDouble(mediumItemPriceBox.getText());
        double priceC = Double.parseDouble(largeItemPriceBox.getText());
        int amountA = Integer.parseInt(smallAmountBox.getText());
        int amountB = Integer.parseInt(mediumAmountBox.getText());
        int amountC = Integer.parseInt(largeAmountBox.getText());
        int toBuyAmount = formatInt(((a * amountA) + (b * amountB) + (c * amountC)));
        //get total amount to buy and convert to integer
        totalCost = formatDecimal(((a * priceA) + (b * priceB) + (c * priceC)), 2); //put cost in form $x.xx
        int res = JOptionPane.showConfirmDialog(null, "Are you sure you wanna spend \n$" + totalCost + " on " + toBuyAmount + " " + item + "?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.YES_OPTION) { //if confirm purchase
        if (money - totalCost < 0) { //make sure has enough money
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame, "Sorry \nNot Enough Money!\nGet a job you lazy ass!"); //error message:]
        } else {
			customerSalesTextArea.setText("purchase Confirmed");
			money -= totalCost; //money - totalcost = money
			moneyBox.setText("" + formatDecimal(money, 2));
			//fun stuff below
			switch (itemId) { //basically a series of if (itemId==x) statements
			case 0: //if item is lemons
			lemons += toBuyAmount; //add to amount
			lemonsBox.setText("" + lemons);
			break;
			case 1://if it is ice being bought
			ice += toBuyAmount;
			iceBox.setText("" + ice);
			break;
			case 2:
			sugar += toBuyAmount;
			sugarBox.setText("" + sugar);
			break;
			case 3:
			cups += toBuyAmount;
			cupsBox.setText("" + cups);
			break;
			default: //as if to say somehow no item was selected
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "An unexpected error occurred: \n no itemId");
			} //end switch
			}//if have enough money
			}//end if confirmed purchase
    }
    private void purchaseItemChoice(){
        //purchase area
    	if (getPurchaseItemChoice().getSelectedItem().equals("Lemons")) {
			//allow purchase
			item = "Lemons";
			itemId = 0;
			purchaseButton.setEnabled(true);
			//setting the purchase screen
			//set the amounts/prices
			largeAmountBox.setText("" + lgAmountLemons);
			largeItemPriceBox.setText("" + lemonsLgCost);
			mediumAmountBox.setText("" + mdAmountLemons);
			mediumItemPriceBox.setText("" + lemonsMdCost);
			smallAmountBox.setText("" + smAmountLemons);
			smallItemPriceBox.setText("" + lemonsSmCost);
		}
		if (getPurchaseItemChoice().getSelectedItem().equals("Ice")) {
			//allow purchase
			item = "Ice";
			itemId = 1;
			purchaseButton.setEnabled(true);
			//set the amounts/prices
			largeAmountBox.setText("" + lgAmountIce);
			largeItemPriceBox.setText("" + iceLgCost);
			mediumAmountBox.setText("" + mdAmountIce);
			mediumItemPriceBox.setText("" + iceMdCost);
			smallAmountBox.setText("" + smAmountIce);
			smallItemPriceBox.setText("" + iceSmCost);
		}
		if (getPurchaseItemChoice().getSelectedItem().equals("Sugar")) {
			//allow purchase
			purchaseButton.setEnabled(true);
			itemId = 2; //for use with purchasiong
			item = "Sugar";
			//set the amounts/prices
			largeAmountBox.setText("" + lgAmountSugar);
			largeItemPriceBox.setText("" + sugarLgCost);
			mediumAmountBox.setText("" + mdAmountSugar);
			mediumItemPriceBox.setText("" + sugarMdCost);
			smallAmountBox.setText("" + smAmountSugar);
			smallItemPriceBox.setText("" + sugarSmCost);
		}
		if (getPurchaseItemChoice().getSelectedItem().equals("Cups")) {
			//allow purchase
			purchaseButton.setEnabled(true);
			itemId = 3;
			//set the amounts/prices
			item = "Cups";
			largeAmountBox.setText("" + lgAmountCups);
			largeItemPriceBox.setText("" + cupsLgCost);
			mediumAmountBox.setText("" + mdAmountCups);
			mediumItemPriceBox.setText("" + cupsMdCost);
			smallAmountBox.setText("" + smAmountCups);
			smallItemPriceBox.setText("" + cupsSmCost);
		}
		if (getPurchaseItemChoice().getSelectedItem().equals("---")) {
			//disable purchse button when no choice is selected
			purchaseButton.setEnabled(false);
			//set the amounts/prices
			largeAmountBox.setText("");
			largeItemPriceBox.setText("");
			mediumAmountBox.setText("");
			mediumItemPriceBox.setText("");
			smallAmountBox.setText("");
			smallItemPriceBox.setText("");
		}
    }
    public EZ_Squeeze_EmpireView(SingleFrameApplication app) {
        super(app);
        initComponents();
        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getStatusMessageLabel().setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setBusyIconIndex((getBusyIconIndex() + 1) % getBusyIcons().length);
                getStatusAnimationLabel().setIcon(getBusyIcons()[getBusyIconIndex()]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        getStatusAnimationLabel().setIcon(getBusyIcons()[0]);
                        setBusyIconIndex(0);
                        getBusyIconTimer().start();
                    }
                    getProgressBar().setVisible(true);
                    getProgressBar().setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    getBusyIconTimer().stop();
                    getStatusAnimationLabel().setIcon(getIdleIcon());
                    getProgressBar().setVisible(false);
                    getProgressBar().setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    getStatusMessageLabel().setText((text == null) ? "" : text);
                    getMessageTimer().restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    getProgressBar().setVisible(true);
                    getProgressBar().setIndeterminate(false);
                    getProgressBar().setValue(value);
                }
            }
        });
        person = new Person[100];
        for(int x = 0; x < 100; x++){
        	person[x] = new Person();
        }
    }

    @Action
    public void showAboutBox() {
        if (getAboutBox() == null) {
            JFrame mainFrame = EZ_Squeeze_EmpireApp.getApplication().getMainFrame();
            setAboutBox(new EZ_Squeeze_EmpireAboutBox(mainFrame));
            getAboutBox().setLocationRelativeTo(mainFrame);
        }
        EZ_Squeeze_EmpireApp.getApplication().show(getAboutBox());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        purchaseItemChoice = new javax.swing.JComboBox();
        purchaseButton = new javax.swing.JButton();
        startDayButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        lemonsRecipeBox = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        sugarRecipeBox = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        iceRecipeBox = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        customerSalesTextArea = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        salesTodayBox = new javax.swing.JTextField();
        salesTotalBox = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        smallItemPriceBox = new javax.swing.JTextField();
        moneyBox = new javax.swing.JTextField();
        dayBox = new javax.swing.JTextField();
        lemonsBox = new javax.swing.JTextField();
        sugarBox = new javax.swing.JTextField();
        iceBox = new javax.swing.JTextField();
        cupsBox = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        mediumItemPriceBox = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        largeItemPriceBox = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        smallSpinner = new javax.swing.JSpinner();
        mediumSpinner = new javax.swing.JSpinner();
        largeSpinner = new javax.swing.JSpinner();
        smallAmountBox = new javax.swing.JTextField();
        mediumAmountBox = new javax.swing.JTextField();
        largeAmountBox = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        priceBox = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        jMenuBar1 = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        menuLoad = new javax.swing.JMenuItem();
        menuSave = new javax.swing.JMenuItem();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(ez_squeeze.EZ_Squeeze_EmpireApp.class).getContext().getResourceMap(EZ_Squeeze_EmpireView.class);
        mainPanel.setBackground(resourceMap.getColor("mainPanel.background")); // NOI18N
        mainPanel.setForeground(resourceMap.getColor("mainPanel.foreground")); // NOI18N
        mainPanel.setMaximumSize(new java.awt.Dimension(425, 354));
        mainPanel.setName("mainPanel"); // NOI18N
        mainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(mainPanel.getBackground());
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setForeground(resourceMap.getColor("jLabel1.foreground")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 420, -1));

        purchaseItemChoice.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---", "Lemons", "Sugar", "Ice", "Cups", " " }));
        purchaseItemChoice.setName("purchaseItemChoice"); // NOI18N
        purchaseItemChoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                purchaseItemChoiceActionPerformed(evt);
            }
        });
        jPanel1.add(purchaseItemChoice, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, -1, -1));

        purchaseButton.setText(resourceMap.getString("purchaseButton.text")); // NOI18N
        purchaseButton.setEnabled(false);
        purchaseButton.setName("purchaseButton"); // NOI18N
        purchaseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                purchaseButtonActionPerformed(evt);
            }
        });
        jPanel1.add(purchaseButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 210, 60, -1));

        startDayButton.setFont(resourceMap.getFont("startDayButton.font")); // NOI18N
        startDayButton.setText(resourceMap.getString("startDayButton.text")); // NOI18N
        startDayButton.setName("startDayButton"); // NOI18N
        startDayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startDayButtonActionPerformed(evt);
            }
        });
        jPanel1.add(startDayButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 430, -1, -1));

        jLabel3.setFont(resourceMap.getFont("jLabel10.font")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, -1, -1));

        lemonsRecipeBox.setFont(resourceMap.getFont("iceBox.font")); // NOI18N
        lemonsRecipeBox.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        lemonsRecipeBox.setText(resourceMap.getString("lemonsRecipeBox.text")); // NOI18N
        lemonsRecipeBox.setName("lemonsRecipeBox"); // NOI18N
        jPanel1.add(lemonsRecipeBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 270, 60, -1));

        jLabel4.setFont(resourceMap.getFont("jLabel9.font")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 270, -1, -1));

        sugarRecipeBox.setFont(resourceMap.getFont("iceBox.font")); // NOI18N
        sugarRecipeBox.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        sugarRecipeBox.setText(resourceMap.getString("sugarRecipeBox.text")); // NOI18N
        sugarRecipeBox.setName("sugarRecipeBox"); // NOI18N
        jPanel1.add(sugarRecipeBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 270, 60, -1));

        jLabel5.setFont(resourceMap.getFont("jLabel10.font")); // NOI18N
        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 490, -1, -1));

        iceRecipeBox.setFont(resourceMap.getFont("iceBox.font")); // NOI18N
        iceRecipeBox.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        iceRecipeBox.setText(resourceMap.getString("iceRecipeBox.text")); // NOI18N
        iceRecipeBox.setName("iceRecipeBox"); // NOI18N
        jPanel1.add(iceRecipeBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 270, 60, -1));

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        customerSalesTextArea.setColumns(20);
        customerSalesTextArea.setEditable(false);
        customerSalesTextArea.setLineWrap(true);
        customerSalesTextArea.setRows(5);
        customerSalesTextArea.setName("customerSalesTextArea"); // NOI18N
        jScrollPane1.setViewportView(customerSalesTextArea);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 260, -1));

        jLabel8.setFont(resourceMap.getFont("jLabel10.font")); // NOI18N
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 440, -1, -1));

        jLabel9.setFont(resourceMap.getFont("jLabel9.font")); // NOI18N
        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, -1));

        jLabel6.setFont(resourceMap.getFont("jLabel9.font")); // NOI18N
        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, -1, -1));

        jLabel10.setFont(resourceMap.getFont("jLabel10.font")); // NOI18N
        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 490, -1, -1));

        jLabel11.setFont(resourceMap.getFont("jLabel9.font")); // NOI18N
        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 270, -1, -1));

        jLabel12.setFont(resourceMap.getFont("jLabel10.font")); // NOI18N
        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 490, -1, -1));

        jLabel13.setFont(resourceMap.getFont("jLabel14.font")); // NOI18N
        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 350, -1, -1));

        jLabel14.setFont(resourceMap.getFont("jLabel14.font")); // NOI18N
        jLabel14.setText(resourceMap.getString("jLabel14.text")); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 380, -1, -1));

        salesTodayBox.setEditable(false);
        salesTodayBox.setFont(resourceMap.getFont("iceBox.font")); // NOI18N
        salesTodayBox.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        salesTodayBox.setText(resourceMap.getString("salesTodayBox.text")); // NOI18N
        salesTodayBox.setName("salesTodayBox"); // NOI18N
        jPanel1.add(salesTodayBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 350, 60, -1));

        salesTotalBox.setEditable(false);
        salesTotalBox.setFont(resourceMap.getFont("iceBox.font")); // NOI18N
        salesTotalBox.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        salesTotalBox.setText(resourceMap.getString("salesTotalBox.text")); // NOI18N
        salesTotalBox.setName("salesTotalBox"); // NOI18N
        jPanel1.add(salesTotalBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 380, 60, -1));

        jLabel15.setFont(resourceMap.getFont("jLabel15.font")); // NOI18N
        jLabel15.setText(resourceMap.getString("jLabel15.text")); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 180, -1, -1));

        smallItemPriceBox.setEditable(false);
        smallItemPriceBox.setFont(resourceMap.getFont("iceBox.font")); // NOI18N
        smallItemPriceBox.setText(resourceMap.getString("smallItemPriceBox.text")); // NOI18N
        smallItemPriceBox.setName("smallItemPriceBox"); // NOI18N
        jPanel1.add(smallItemPriceBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 120, 60, -1));

        moneyBox.setEditable(false);
        moneyBox.setFont(resourceMap.getFont("moneyBox.font")); // NOI18N
        moneyBox.setText(resourceMap.getString("moneyBox.text")); // NOI18N
        moneyBox.setName("moneyBox"); // NOI18N
        jPanel1.add(moneyBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, 80, -1));

        dayBox.setEditable(false);
        dayBox.setFont(resourceMap.getFont("iceBox.font")); // NOI18N
        dayBox.setText(resourceMap.getString("dayBox.text")); // NOI18N
        dayBox.setName("dayBox"); // NOI18N
        jPanel1.add(dayBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 440, 60, -1));

        lemonsBox.setEditable(false);
        lemonsBox.setFont(resourceMap.getFont("iceBox.font")); // NOI18N
        lemonsBox.setText(resourceMap.getString("lemonsBox.text")); // NOI18N
        lemonsBox.setName("lemonsBox"); // NOI18N
        jPanel1.add(lemonsBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 490, 50, -1));

        sugarBox.setEditable(false);
        sugarBox.setFont(resourceMap.getFont("iceBox.font")); // NOI18N
        sugarBox.setText(resourceMap.getString("sugarBox.text")); // NOI18N
        sugarBox.setName("sugarBox"); // NOI18N
        jPanel1.add(sugarBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 490, 50, -1));

        iceBox.setEditable(false);
        iceBox.setFont(resourceMap.getFont("iceBox.font")); // NOI18N
        iceBox.setText(resourceMap.getString("iceBox.text")); // NOI18N
        iceBox.setName("iceBox"); // NOI18N
        jPanel1.add(iceBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 490, 50, -1));

        cupsBox.setEditable(false);
        cupsBox.setFont(resourceMap.getFont("iceBox.font")); // NOI18N
        cupsBox.setText(resourceMap.getString("cupsBox.text")); // NOI18N
        cupsBox.setName("cupsBox"); // NOI18N
        jPanel1.add(cupsBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 490, 50, -1));

        jLabel16.setFont(resourceMap.getFont("jLabel9.font")); // NOI18N
        jLabel16.setText(resourceMap.getString("jLabel16.text")); // NOI18N
        jLabel16.setName("jLabel16"); // NOI18N
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        mediumItemPriceBox.setEditable(false);
        mediumItemPriceBox.setFont(resourceMap.getFont("iceBox.font")); // NOI18N
        mediumItemPriceBox.setText(resourceMap.getString("mediumItemPriceBox.text")); // NOI18N
        mediumItemPriceBox.setName("mediumItemPriceBox"); // NOI18N
        jPanel1.add(mediumItemPriceBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 150, 60, -1));

        jLabel17.setFont(resourceMap.getFont("jLabel15.font")); // NOI18N
        jLabel17.setText(resourceMap.getString("jLabel17.text")); // NOI18N
        jLabel17.setName("jLabel17"); // NOI18N
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 150, -1, -1));

        largeItemPriceBox.setEditable(false);
        largeItemPriceBox.setFont(resourceMap.getFont("iceBox.font")); // NOI18N
        largeItemPriceBox.setText(resourceMap.getString("largeItemPriceBox.text")); // NOI18N
        largeItemPriceBox.setName("largeItemPriceBox"); // NOI18N
        jPanel1.add(largeItemPriceBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 180, 60, -1));

        jLabel18.setFont(resourceMap.getFont("jLabel18.font")); // NOI18N
        jLabel18.setText(resourceMap.getString("jLabel18.text")); // NOI18N
        jLabel18.setName("jLabel18"); // NOI18N
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 120, -1, -1));

        jLabel2.setFont(resourceMap.getFont("jLabel20.font")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, -1, -1));

        jLabel19.setFont(resourceMap.getFont("jLabel20.font")); // NOI18N
        jLabel19.setText(resourceMap.getString("jLabel19.text")); // NOI18N
        jLabel19.setName("jLabel19"); // NOI18N
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 150, -1, -1));

        jLabel20.setFont(resourceMap.getFont("jLabel20.font")); // NOI18N
        jLabel20.setText(resourceMap.getString("jLabel20.text")); // NOI18N
        jLabel20.setName("jLabel20"); // NOI18N
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, -1, -1));

        smallSpinner.setName("smallSpinner"); // NOI18N
        jPanel1.add(smallSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, 60, -1));

        mediumSpinner.setName("mediumSpinner"); // NOI18N
        jPanel1.add(mediumSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, 60, -1));

        largeSpinner.setName("largeSpinner"); // NOI18N
        jPanel1.add(largeSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, 60, -1));

        smallAmountBox.setEditable(false);
        smallAmountBox.setFont(resourceMap.getFont("iceBox.font")); // NOI18N
        smallAmountBox.setText(resourceMap.getString("smallAmountBox.text")); // NOI18N
        smallAmountBox.setToolTipText(resourceMap.getString("smallAmountBox.toolTipText")); // NOI18N
        smallAmountBox.setName("smallAmountBox"); // NOI18N
        jPanel1.add(smallAmountBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 120, 40, 20));

        mediumAmountBox.setEditable(false);
        mediumAmountBox.setFont(resourceMap.getFont("iceBox.font")); // NOI18N
        mediumAmountBox.setText(resourceMap.getString("mediumAmountBox.text")); // NOI18N
        mediumAmountBox.setName("mediumAmountBox"); // NOI18N
        jPanel1.add(mediumAmountBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, 40, -1));

        largeAmountBox.setEditable(false);
        largeAmountBox.setFont(resourceMap.getFont("iceBox.font")); // NOI18N
        largeAmountBox.setText(resourceMap.getString("largeAmountBox.text")); // NOI18N
        largeAmountBox.setName("largeAmountBox"); // NOI18N
        jPanel1.add(largeAmountBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 180, 40, -1));

        jLabel21.setFont(resourceMap.getFont("jLabel9.font")); // NOI18N
        jLabel21.setText(resourceMap.getString("jLabel21.text")); // NOI18N
        jLabel21.setName("jLabel21"); // NOI18N
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, -1, -1));

        priceBox.setFont(resourceMap.getFont("iceBox.font")); // NOI18N
        priceBox.setText(resourceMap.getString("priceBox.text")); // NOI18N
        priceBox.setName("priceBox"); // NOI18N
        jPanel1.add(priceBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 300, 60, -1));

        jLabel22.setFont(resourceMap.getFont("jLabel22.font")); // NOI18N
        jLabel22.setText(resourceMap.getString("jLabel22.text")); // NOI18N
        jLabel22.setName("jLabel22"); // NOI18N
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 10, -1));

        jSeparator1.setForeground(resourceMap.getColor("jSeparator1.foreground")); // NOI18N
        jSeparator1.setName("jSeparator1"); // NOI18N
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, 450, 10));

        jSeparator2.setForeground(resourceMap.getColor("jSeparator2.foreground")); // NOI18N
        jSeparator2.setName("jSeparator2"); // NOI18N
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 450, 10));

        jSeparator3.setForeground(resourceMap.getColor("jSeparator3.foreground")); // NOI18N
        jSeparator3.setName("jSeparator3"); // NOI18N
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 450, 10));

        jSeparator4.setForeground(resourceMap.getColor("jSeparator4.foreground")); // NOI18N
        jSeparator4.setName("jSeparator4"); // NOI18N
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 450, 10));

        mainPanel.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 550));

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 314, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        jMenuBar1.setName("jMenuBar1"); // NOI18N
        jMenuBar1.setVisible(true);

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText(resourceMap.getString("jMenuItem1.text")); // NOI18N
        jMenuItem1.setName("jMenuItem1"); // NOI18N
        fileMenu.add(jMenuItem1);

        menuLoad.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        menuLoad.setText(resourceMap.getString("menuLoad.text")); // NOI18N
        menuLoad.setName("menuLoad"); // NOI18N
        menuLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuLoadActionPerformed(evt);
            }
        });
        fileMenu.add(menuLoad);

        menuSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        menuSave.setText(resourceMap.getString("menuSave.text")); // NOI18N
        menuSave.setName("menuSave"); // NOI18N
        menuSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSaveActionPerformed(evt);
            }
        });
        fileMenu.add(menuSave);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(ez_squeeze.EZ_Squeeze_EmpireApp.class).getContext().getActionMap(EZ_Squeeze_EmpireView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        jMenuBar1.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        jMenuBar1.add(helpMenu);

        setComponent(mainPanel);
        setMenuBar(jMenuBar1);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void menuSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSaveActionPerformed
        // TODO add your handling code here:
        save();
    }//GEN-LAST:event_menuSaveActionPerformed

    private void menuLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuLoadActionPerformed
        // TODO add your handling code here:
        load();
    }//GEN-LAST:event_menuLoadActionPerformed

    
        private void purchaseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_purchaseButtonActionPerformed
            purchase();
			}//GEN-LAST:event_purchaseButtonActionPerformed

    private void startDayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startDayButtonActionPerformed
        // TODO add your handling code here:
        startDay();
    }//GEN-LAST:event_startDayButtonActionPerformed

    private void purchaseItemChoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_purchaseItemChoiceActionPerformed
        // TODO add your handling code here:
        purchaseItemChoice();
    }//GEN-LAST:event_purchaseItemChoiceActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cupsBox;
    private javax.swing.JTextArea customerSalesTextArea;
    private javax.swing.JTextField dayBox;
    private javax.swing.JTextField iceBox;
    private javax.swing.JTextField iceRecipeBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTextField largeAmountBox;
    private javax.swing.JTextField largeItemPriceBox;
    private javax.swing.JSpinner largeSpinner;
    private javax.swing.JTextField lemonsBox;
    private javax.swing.JTextField lemonsRecipeBox;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTextField mediumAmountBox;
    private javax.swing.JTextField mediumItemPriceBox;
    private javax.swing.JSpinner mediumSpinner;
    private javax.swing.JMenuItem menuLoad;
    private javax.swing.JMenuItem menuSave;
    private javax.swing.JTextField moneyBox;
    private javax.swing.JTextField priceBox;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JButton purchaseButton;
	private javax.swing.JComboBox purchaseItemChoice;
    private javax.swing.JTextField salesTodayBox;
    private javax.swing.JTextField salesTotalBox;
    private javax.swing.JTextField smallAmountBox;
    private javax.swing.JTextField smallItemPriceBox;
    private javax.swing.JSpinner smallSpinner;
    private javax.swing.JButton startDayButton;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JTextField sugarBox;
    private javax.swing.JTextField sugarRecipeBox;
    // End of variables declaration//GEN-END:variables

    private Timer messageTimer;
    private Timer busyIconTimer;
    private Icon idleIcon;
    private Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;

    /**
     * @return the lemons
     */
    public static int getLemons() {
        return lemons;
    }

    /**
     * @param lemons the lemons to set
     */
    public static void setLemons(int lemon) {
        lemons = lemon;
    }

    /**
     * @return the sugar
     */
    public static int getSugar() {
        return sugar;
    }

    /**
     * @param sugar the sugar to set
     */
    public static void setSugar(int sug) {
        sugar = sug;
    }

    /**
     * @return the ice
     */
    public static int getIce() {
        return ice;
    }

    /**
     * @param ice the ice to set
     */
    public static void setIce(int ice1) {
        ice = ice1;
    }

    /**
     * @return the cups
     */
    public static int getCups() {
        return cups;
    }

    /**
     * @param cups the cups to set
     */
    public static void setCups(int cup) {
        cups = cup;
    }

    /**
     * @return the recipeLemons
     */
    public static int getRecipeLemons() {
        return recipeLemons;
    }

    /**
     * @param recipeLemons the recipeLemons to set
     */
    public static void setRecipeLemons(int Lemons) {
        recipeLemons = Lemons;
    }

    /**
     * @return the recipeSugar
     */
    public static int getRecipeSugar() {
        return recipeSugar;
    }

    /**
     * @param recipeSugar the recipeSugar to set
     */
    public static void setRecipeSugar(int Sugar) {
        recipeSugar = Sugar;
    }

    /**
     * @return the recipeIce
     */
    public static int getRecipeIce() {
        return recipeIce;
    }

    /**
     * @param recipeIce the recipeIce to set
     */
    public static void setRecipeIce(int Ice) {
        recipeIce = Ice;
    }

    /**
     * @return the salesTotal
     */
    public int getSalesTotal() {
        return salesTotal;
    }

    /**
     * @param salesTotal the salesTotal to set
     */
    public void setSalesTotal(int salesTotal) {
        this.salesTotal = salesTotal;
    }

    /**
     * @return the salesToday
     */
    public int getSalesToday() {
        return salesToday;
    }

    /**
     * @param salesToday the salesToday to set
     */
    public void setSalesToday(int salesToday) {
        this.salesToday = salesToday;
    }

    /**
     * @return the day
     */
    public int getDay() {
        return day;
    }

    /**
     * @param day the day to set
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * @return the smAmountLemons
     */
    public int getSmAmountLemons() {
        return smAmountLemons;
    }

    /**
     * @param smAmountLemons the smAmountLemons to set
     */
    public void setSmAmountLemons(int smAmountLemons) {
        this.smAmountLemons = smAmountLemons;
    }

    /**
     * @return the mdAmountLemons
     */
    public int getMdAmountLemons() {
        return mdAmountLemons;
    }

    /**
     * @param mdAmountLemons the mdAmountLemons to set
     */
    public void setMdAmountLemons(int mdAmountLemons) {
        this.mdAmountLemons = mdAmountLemons;
    }

    /**
     * @return the lgAmountLemons
     */
    public int getLgAmountLemons() {
        return lgAmountLemons;
    }

    /**
     * @param lgAmountLemons the lgAmountLemons to set
     */
    public void setLgAmountLemons(int lgAmountLemons) {
        this.lgAmountLemons = lgAmountLemons;
    }

    /**
     * @return the smAmountIce
     */
    public int getSmAmountIce() {
        return smAmountIce;
    }

    /**
     * @param smAmountIce the smAmountIce to set
     */
    public void setSmAmountIce(int smAmountIce) {
        this.smAmountIce = smAmountIce;
    }

    /**
     * @return the mdAmountIce
     */
    public int getMdAmountIce() {
        return mdAmountIce;
    }

    /**
     * @param mdAmountIce the mdAmountIce to set
     */
    public void setMdAmountIce(int mdAmountIce) {
        this.mdAmountIce = mdAmountIce;
    }

    /**
     * @return the lgAmountIce
     */
    public int getLgAmountIce() {
        return lgAmountIce;
    }

    /**
     * @param lgAmountIce the lgAmountIce to set
     */
    public void setLgAmountIce(int lgAmountIce) {
        this.lgAmountIce = lgAmountIce;
    }

    /**
     * @return the money
     */
    public double getMoney() {
        return money;
    }

    /**
     * @param money the money to set
     */
    public void setMoney(double money) {
        this.money = money;
    }

    /**
     * @return the lemonsSmCost
     */
    public double getLemonsSmCost() {
        return lemonsSmCost;
    }

    /**
     * @param lemonsSmCost the lemonsSmCost to set
     */
    public void setLemonsSmCost(double lemonsSmCost) {
        this.lemonsSmCost = lemonsSmCost;
    }

    /**
     * @return the lemonsMdCost
     */
    public double getLemonsMdCost() {
        return lemonsMdCost;
    }

    /**
     * @param lemonsMdCost the lemonsMdCost to set
     */
    public void setLemonsMdCost(double lemonsMdCost) {
        this.lemonsMdCost = lemonsMdCost;
    }

    /**
     * @return the lemonsLgCost
     */
    public double getLemonsLgCost() {
        return lemonsLgCost;
    }

    /**
     * @param lemonsLgCost the lemonsLgCost to set
     */
    public void setLemonsLgCost(double lemonsLgCost) {
        this.lemonsLgCost = lemonsLgCost;
    }

    /**
     * @return the sugarSmCost
     */
    public double getSugarSmCost() {
        return sugarSmCost;
    }

    /**
     * @param sugarSmCost the sugarSmCost to set
     */
    public void setSugarSmCost(double sugarSmCost) {
        this.sugarSmCost = sugarSmCost;
    }

    /**
     * @return the sugarMdCost
     */
    public double getSugarMdCost() {
        return sugarMdCost;
    }

    /**
     * @param sugarMdCost the sugarMdCost to set
     */
    public void setSugarMdCost(double sugarMdCost) {
        this.sugarMdCost = sugarMdCost;
    }

    /**
     * @return the sugarLgCost
     */
    public double getSugarLgCost() {
        return sugarLgCost;
    }

    /**
     * @param sugarLgCost the sugarLgCost to set
     */
    public void setSugarLgCost(double sugarLgCost) {
        this.sugarLgCost = sugarLgCost;
    }

    /**
     * @return the iceSmCost
     */
    public double getIceSmCost() {
        return iceSmCost;
    }

    /**
     * @param iceSmCost the iceSmCost to set
     */
    public void setIceSmCost(double iceSmCost) {
        this.iceSmCost = iceSmCost;
    }

    /**
     * @return the iceMdCost
     */
    public double getIceMdCost() {
        return iceMdCost;
    }

    /**
     * @param iceMdCost the iceMdCost to set
     */
    public void setIceMdCost(double iceMdCost) {
        this.iceMdCost = iceMdCost;
    }

    /**
     * @return the iceLgCost
     */
    public double getIceLgCost() {
        return iceLgCost;
    }

    /**
     * @param iceLgCost the iceLgCost to set
     */
    public void setIceLgCost(double iceLgCost) {
        this.iceLgCost = iceLgCost;
    }

    /**
     * @return the cupsSmCost
     */
    public double getCupsSmCost() {
        return cupsSmCost;
    }


        /**
     * @return the version
     */
    public static double getVersion() {
        return version;
    }

    /**
     * @param versionNumber
     */
        public void setVersion(double versionNumber) {
        EZ_Squeeze_EmpireView.version = versionNumber;
    }

  /**
     * @param cupsSmCost the cupsSmCost to set
     */
    public void setCupsSmCost(double cupsSmCost) {
        this.cupsSmCost = cupsSmCost;
    }

    /**
     * @return the cupsMdCost
     */
    public double getCupsMdCost() {
        return cupsMdCost;
    }

    /**
     * @param cupsMdCost the cupsMdCost to set
     */
    public void setCupsMdCost(double cupsMdCost) {
        this.cupsMdCost = cupsMdCost;
    }

    /**
     * @return the cupsLgcost
     */
    public double getCupsLgCost() {
        return cupsLgCost;
    }

    /**
     * @param cupsLgCost
     */
    public void setCupsLgCost(double cupsLgCost) {
        this.cupsLgCost = cupsLgCost;
    }

    /**
     * @return the cupsBox
     */
    public javax.swing.JTextField getCupsBox() {
        return cupsBox;
    }

    /**
     * @param cupsBox the cupsBox to set
     */
    public void setCupsBox(javax.swing.JTextField cupsBox) {
        this.cupsBox = cupsBox;
    }

    /**
     * @return the customerSalesTextArea
     */
    public javax.swing.JTextArea getCustomerSalesTextArea() {
        return customerSalesTextArea;
    }

    /**
     * @param customerSalesTextArea the customerSalesTextArea to set
     */
    public void setCustomerSalesTextArea(javax.swing.JTextArea customerSalesTextArea) {
        this.customerSalesTextArea = customerSalesTextArea;
    }

    /**
     * @return the dayBox
     */
    public javax.swing.JTextField getDayBox() {
        return dayBox;
    }

    /**
     * @param dayBox the dayBox to set
     */
    public void setDayBox(javax.swing.JTextField dayBox) {
        this.dayBox = dayBox;
    }

    /**
     * @return the iceBox
     */
    public javax.swing.JTextField getIceBox() {
        return iceBox;
    }

    /**
     * @param iceBox the iceBox to set
     */
    public void setIceBox(javax.swing.JTextField iceBox) {
        this.iceBox = iceBox;
    }

    /**
     * @return the iceRecipeBox
     */
    public javax.swing.JTextField getIceRecipeBox() {
        return iceRecipeBox;
    }

    /**
     * @param iceRecipeBox the iceRecipeBox to set
     */
    public void setIceRecipeBox(javax.swing.JTextField iceRecipeBox) {
        this.iceRecipeBox = iceRecipeBox;
    }

    /**
     * @return the jLabel1
     */
    public javax.swing.JLabel getjLabel1() {
        return jLabel1;
    }

    /**
     * @param jLabel1 the jLabel1 to set
     */
    public void setjLabel1(javax.swing.JLabel jLabel1) {
        this.jLabel1 = jLabel1;
    }

    /**
     * @return the jLabel10
     */
    public javax.swing.JLabel getjLabel10() {
        return jLabel10;
    }

    /**
     * @param jLabel10 the jLabel10 to set
     */
    public void setjLabel10(javax.swing.JLabel jLabel10) {
        this.jLabel10 = jLabel10;
    }

    /**
     * @return the jLabel11
     */
    public javax.swing.JLabel getjLabel11() {
        return jLabel11;
    }

    /**
     * @param jLabel11 the jLabel11 to set
     */
    public void setjLabel11(javax.swing.JLabel jLabel11) {
        this.jLabel11 = jLabel11;
    }

    /**
     * @return the jLabel12
     */
    public javax.swing.JLabel getjLabel12() {
        return jLabel12;
    }

    /**
     * @param jLabel12 the jLabel12 to set
     */
    public void setjLabel12(javax.swing.JLabel jLabel12) {
        this.jLabel12 = jLabel12;
    }

    /**
     * @return the jLabel13
     */
    public javax.swing.JLabel getjLabel13() {
        return jLabel13;
    }

    /**
     * @param jLabel13 the jLabel13 to set
     */
    public void setjLabel13(javax.swing.JLabel jLabel13) {
        this.jLabel13 = jLabel13;
    }

    /**
     * @return the jLabel14
     */
    public javax.swing.JLabel getjLabel14() {
        return jLabel14;
    }

    /**
     * @param jLabel14 the jLabel14 to set
     */
    public void setjLabel14(javax.swing.JLabel jLabel14) {
        this.jLabel14 = jLabel14;
    }

    /**
     * @return the jLabel15
     */
    public javax.swing.JLabel getjLabel15() {
        return jLabel15;
    }

    /**
     * @param jLabel15 the jLabel15 to set
     */
    public void setjLabel15(javax.swing.JLabel jLabel15) {
        this.jLabel15 = jLabel15;
    }

    /**
     * @return the jLabel16
     */
    public javax.swing.JLabel getjLabel16() {
        return jLabel16;
    }

    /**
     * @param jLabel16 the jLabel16 to set
     */
    public void setjLabel16(javax.swing.JLabel jLabel16) {
        this.jLabel16 = jLabel16;
    }

    /**
     * @return the jLabel17
     */
    public javax.swing.JLabel getjLabel17() {
        return jLabel17;
    }

    /**
     * @param jLabel17 the jLabel17 to set
     */
    public void setjLabel17(javax.swing.JLabel jLabel17) {
        this.jLabel17 = jLabel17;
    }

    /**
     * @return the jLabel18
     */
    public javax.swing.JLabel getjLabel18() {
        return jLabel18;
    }

    /**
     * @param jLabel18 the jLabel18 to set
     */
    public void setjLabel18(javax.swing.JLabel jLabel18) {
        this.jLabel18 = jLabel18;
    }

    /**
     * @return the jLabel19
     */
    public javax.swing.JLabel getjLabel19() {
        return jLabel19;
    }

    /**
     * @param jLabel19 the jLabel19 to set
     */
    public void setjLabel19(javax.swing.JLabel jLabel19) {
        this.jLabel19 = jLabel19;
    }

    /**
     * @return the jLabel2
     */
    public javax.swing.JLabel getjLabel2() {
        return jLabel2;
    }

    /**
     * @param jLabel2 the jLabel2 to set
     */
    public void setjLabel2(javax.swing.JLabel jLabel2) {
        this.jLabel2 = jLabel2;
    }

    /**
     * @return the jLabel20
     */
    public javax.swing.JLabel getjLabel20() {
        return jLabel20;
    }

    /**
     * @param jLabel20 the jLabel20 to set
     */
    public void setjLabel20(javax.swing.JLabel jLabel20) {
        this.jLabel20 = jLabel20;
    }

    /**
     * @return the jLabel3
     */
    public javax.swing.JLabel getjLabel3() {
        return jLabel3;
    }

    /**
     * @param jLabel3 the jLabel3 to set
     */
    public void setjLabel3(javax.swing.JLabel jLabel3) {
        this.jLabel3 = jLabel3;
    }

    /**
     * @return the jLabel4
     */
    public javax.swing.JLabel getjLabel4() {
        return jLabel4;
    }

    /**
     * @param jLabel4 the jLabel4 to set
     */
    public void setjLabel4(javax.swing.JLabel jLabel4) {
        this.jLabel4 = jLabel4;
    }

    /**
     * @return the jLabel5
     */
    public javax.swing.JLabel getjLabel5() {
        return jLabel5;
    }

    /**
     * @param jLabel5 the jLabel5 to set
     */
    public void setjLabel5(javax.swing.JLabel jLabel5) {
        this.jLabel5 = jLabel5;
    }

    /**
     * @return the jLabel6
     */
    public javax.swing.JLabel getjLabel6() {
        return jLabel6;
    }

    /**
     * @param jLabel6 the jLabel6 to set
     */
    public void setjLabel6(javax.swing.JLabel jLabel6) {
        this.jLabel6 = jLabel6;
    }

    /**
     * @return the jLabel8
     */
    public javax.swing.JLabel getjLabel8() {
        return jLabel8;
    }

    /**
     * @param jLabel8 the jLabel8 to set
     */
    public void setjLabel8(javax.swing.JLabel jLabel8) {
        this.jLabel8 = jLabel8;
    }

    /**
     * @return the jLabel9
     */
    public javax.swing.JLabel getjLabel9() {
        return jLabel9;
    }

    /**
     * @param jLabel9 the jLabel9 to set
     */
    public void setjLabel9(javax.swing.JLabel jLabel9) {
        this.jLabel9 = jLabel9;
    }



    /**
     * @return the jScrollPane1
     */
    public javax.swing.JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    /**
     * @param jScrollPane1 the jScrollPane1 to set
     */
    public void setjScrollPane1(javax.swing.JScrollPane jScrollPane1) {
        this.jScrollPane1 = jScrollPane1;
    }

    /**
     * @return the jSeparator1
     */
    public javax.swing.JSeparator getjSeparator1() {
        return jSeparator1;
    }

    /**
     * @param jSeparator1 the jSeparator1 to set
     */
    public void setjSeparator1(javax.swing.JSeparator jSeparator1) {
        this.jSeparator1 = jSeparator1;
    }

    /**
     * @return the jSeparator2
     */
    public javax.swing.JSeparator getjSeparator2() {
        return jSeparator2;
    }

    /**
     * @param jSeparator2 the jSeparator2 to set
     */
    public void setjSeparator2(javax.swing.JSeparator jSeparator2) {
        this.jSeparator2 = jSeparator2;
    }

    /**
     * @return the jSeparator3
     */
    public javax.swing.JSeparator getjSeparator3() {
        return jSeparator3;
    }

    /**
     * @param jSeparator3 the jSeparator3 to set
     */
    public void setjSeparator3(javax.swing.JSeparator jSeparator3) {
        this.jSeparator3 = jSeparator3;
    }

    /**
     * @return the jSeparator4
     */
    public javax.swing.JSeparator getjSeparator4() {
        return jSeparator4;
    }

    /**
     * @param jSeparator4 the jSeparator4 to set
     */
    public void setjSeparator4(javax.swing.JSeparator jSeparator4) {
        this.jSeparator4 = jSeparator4;
    }

    /**
     * @return the largeAmountBox
     */
    public javax.swing.JTextField getLargeAmountBox() {
        return largeAmountBox;
    }

    /**
     * @param largeAmountBox the largeAmountBox to set
     */
    public void setLargeAmountBox(javax.swing.JTextField largeAmountBox) {
        this.largeAmountBox = largeAmountBox;
    }

    /**
     * @return the largeItemPriceBox
     */
    public javax.swing.JTextField getLargeItemPriceBox() {
        return largeItemPriceBox;
    }

    /**
     * @param largeItemPriceBox the largeItemPriceBox to set
     */
    public void setLargeItemPriceBox(javax.swing.JTextField largeItemPriceBox) {
        this.largeItemPriceBox = largeItemPriceBox;
    }

    /**
     * @return the largeSpinner
     */
    public javax.swing.JSpinner getLargeSpinner() {
        return largeSpinner;
    }

    /**
     * @param largeSpinner the largeSpinner to set
     */
    public void setLargeSpinner(javax.swing.JSpinner largeSpinner) {
        this.largeSpinner = largeSpinner;
    }

    /**
     * @return the lemonsBox
     */
    public javax.swing.JTextField getLemonsBox() {
        return lemonsBox;
    }

    /**
     * @param lemonsBox the lemonsBox to set
     */
    public void setLemonsBox(javax.swing.JTextField lemonsBox) {
        this.lemonsBox = lemonsBox;
    }

    /**
     * @return the lemonsRecipeBox
     */
    public javax.swing.JTextField getLemonsRecipeBox() {
        return lemonsRecipeBox;
    }

    /**
     * @param lemonsRecipeBox the lemonsRecipeBox to set
     */
    public void setLemonsRecipeBox(javax.swing.JTextField lemonsRecipeBox) {
        this.lemonsRecipeBox = lemonsRecipeBox;
    }

    /**
     * @return the mainPanel
     */
    public javax.swing.JPanel getMainPanel() {
        return mainPanel;
    }

    /**
     * @param mainPanel the mainPanel to set
     */
    public void setMainPanel(javax.swing.JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    /**
     * @return the mediumAmountBox
     */
    public javax.swing.JTextField getMediumAmountBox() {
        return mediumAmountBox;
    }

    /**
     * @param mediumAmountBox the mediumAmountBox to set
     */
    public void setMediumAmountBox(javax.swing.JTextField mediumAmountBox) {
        this.mediumAmountBox = mediumAmountBox;
    }

    /**
     * @return the mediumItemPriceBox
     */
    public javax.swing.JTextField getMediumItemPriceBox() {
        return mediumItemPriceBox;
    }

    /**
     * @param mediumItemPriceBox the mediumItemPriceBox to set
     */
    public void setMediumItemPriceBox(javax.swing.JTextField mediumItemPriceBox) {
        this.mediumItemPriceBox = mediumItemPriceBox;
    }

    /**
     * @return the mediumSpinner
     */
    public javax.swing.JSpinner getMediumSpinner() {
        return mediumSpinner;
    }

    /**
     * @param mediumSpinner the mediumSpinner to set
     */
    public void setMediumSpinner(javax.swing.JSpinner mediumSpinner) {
        this.mediumSpinner = mediumSpinner;
    }


    /**
     * @return the moneyBox
     */
    public javax.swing.JTextField getMoneyBox() {
        return moneyBox;
    }

    /**
     * @param moneyBox the moneyBox to set
     */
    public void setMoneyBox(javax.swing.JTextField moneyBox) {
        this.moneyBox = moneyBox;
    }

    /**
     * @return the progressBar
     */
    public javax.swing.JProgressBar getProgressBar() {
        return progressBar;
    }

    /**
     * @param progressBar the progressBar to set
     */
    public void setProgressBar(javax.swing.JProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    /**
     * @return the purchaseButton
     */
    public javax.swing.JButton getPurchaseButton() {
        return purchaseButton;
    }

    /**
     * @param purchaseButton the purchaseButton to set
     */
    public void setPurchaseButton(javax.swing.JButton purchaseButton) {
        this.purchaseButton = purchaseButton;
    }

    /**
     * @return the purchaseItemChoice
     */
	public javax.swing.JComboBox getPurchaseItemChoice() {
        return purchaseItemChoice;
    }

    /**
     * @param purchaseItemChoice the purchaseItemChoice to set
     */
    public void setPurchaseItemChoice(javax.swing.JComboBox purchaseItemChoice) {
        this.purchaseItemChoice = purchaseItemChoice;
    }

    /**
     * @return the salesTodayBox
     */
    public javax.swing.JTextField getSalesTodayBox() {
        return salesTodayBox;
    }

    /**
     * @param salesTodayBox the salesTodayBox to set
     */
    public void setSalesTodayBox(javax.swing.JTextField salesTodayBox) {
        this.salesTodayBox = salesTodayBox;
    }

    /**
     * @return the salesTotalBox
     */
    public javax.swing.JTextField getSalesTotalBox() {
        return salesTotalBox;
    }

    /**
     * @param salesTotalBox the salesTotalBox to set
     */
    public void setSalesTotalBox(javax.swing.JTextField salesTotalBox) {
        this.salesTotalBox = salesTotalBox;
    }

    /**
     * @return the smallAmountBox
     */
    public javax.swing.JTextField getSmallAmountBox() {
        return smallAmountBox;
    }

    /**
     * @param smallAmountBox the smallAmountBox to set
     */
    public void setSmallAmountBox(javax.swing.JTextField smallAmountBox) {
        this.smallAmountBox = smallAmountBox;
    }

    /**
     * @return the smallItemPriceBox
     */
    public javax.swing.JTextField getSmallItemPriceBox() {
        return smallItemPriceBox;
    }

    /**
     * @param smallItemPriceBox the smallItemPriceBox to set
     */
    public void setSmallItemPriceBox(javax.swing.JTextField smallItemPriceBox) {
        this.smallItemPriceBox = smallItemPriceBox;
    }

    /**
     * @return the smallSpinner
     */
    public javax.swing.JSpinner getSmallSpinner() {
        return smallSpinner;
    }

    /**
     * @param smallSpinner the smallSpinner to set
     */
    public void setSmallSpinner(javax.swing.JSpinner smallSpinner) {
        this.smallSpinner = smallSpinner;
    }

    /**
     * @return the startDayButton
     */
    public javax.swing.JButton getStartDayButton() {
        return startDayButton;
    }

    /**
     * @param startDayButton the startDayButton to set
     */
    public void setStartDayButton(javax.swing.JButton startDayButton) {
        this.startDayButton = startDayButton;
    }

    /**
     * @return the statusAnimationLabel
     */
    public javax.swing.JLabel getStatusAnimationLabel() {
        return statusAnimationLabel;
    }

    /**
     * @param statusAnimationLabel the statusAnimationLabel to set
     */
    public void setStatusAnimationLabel(javax.swing.JLabel statusAnimationLabel) {
        this.statusAnimationLabel = statusAnimationLabel;
    }

    /**
     * @return the statusMessageLabel
     */
    public javax.swing.JLabel getStatusMessageLabel() {
        return statusMessageLabel;
    }

    /**
     * @param statusMessageLabel the statusMessageLabel to set
     */
    public void setStatusMessageLabel(javax.swing.JLabel statusMessageLabel) {
        this.statusMessageLabel = statusMessageLabel;
    }

    /**
     * @return the statusPanel
     */
    public javax.swing.JPanel getStatusPanel() {
        return statusPanel;
    }

    /**
     * @param statusPanel the statusPanel to set
     */
    public void setStatusPanel(javax.swing.JPanel statusPanel) {
        this.statusPanel = statusPanel;
    }

    /**
     * @return the sugarBox
     */
    public javax.swing.JTextField getSugarBox() {
        return sugarBox;
    }

    /**
     * @param sugarBox the sugarBox to set
     */
    public void setSugarBox(javax.swing.JTextField sugarBox) {
        this.sugarBox = sugarBox;
    }

    /**
     * @return the sugarRecipeBox
     */
    public javax.swing.JTextField getSugarRecipeBox() {
        return sugarRecipeBox;
    }

    /**
     * @param sugarRecipeBox the sugarRecipeBox to set
     */
    public void setSugarRecipeBox(javax.swing.JTextField sugarRecipeBox) {
        this.sugarRecipeBox = sugarRecipeBox;
    }

    /**
     * @return the messageTimer
     */
    public Timer getMessageTimer() {
        return messageTimer;
    }

    /**
     * @param messageTimer the messageTimer to set
     */
    public void setMessageTimer(Timer messageTimer) {
        this.messageTimer = messageTimer;
    }

    /**
     * @return the busyIconTimer
     */
    public Timer getBusyIconTimer() {
        return busyIconTimer;
    }

    /**
     * @param busyIconTimer the busyIconTimer to set
     */
    public void setBusyIconTimer(Timer busyIconTimer) {
        this.busyIconTimer = busyIconTimer;
    }

    /**
     * @return the idleIcon
     */
    public Icon getIdleIcon() {
        return idleIcon;
    }

    /**
     * @param idleIcon the idleIcon to set
     */
    public void setIdleIcon(Icon idleIcon) {
        this.idleIcon = idleIcon;
    }

    /**
     * @return the busyIcons
     */
    public Icon[] getBusyIcons() {
        return busyIcons;
    }

    /**
     * @param busyIcons the busyIcons to set
     */
    public void setBusyIcons(Icon[] busyIcons) {
        this.busyIcons = busyIcons;
    }

    /**
     * @return the busyIconIndex
     */
    public int getBusyIconIndex() {
        return busyIconIndex;
    }

    /**
     * @param busyIconIndex the busyIconIndex to set
     */
    public void setBusyIconIndex(int busyIconIndex) {
        this.busyIconIndex = busyIconIndex;
    }

    /**
     * @return the aboutBox
     */
    public JDialog getAboutBox() {
        return aboutBox;
    }

    /**
     * @param aboutBox the aboutBox to set
     */
    public void setAboutBox(JDialog aboutBox) {
        this.aboutBox = aboutBox;
    }
}
