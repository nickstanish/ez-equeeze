package ez_squeeze.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * 
 * @author Nick Stanish
 *
 */
public class RecipePanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -596793567566446238L;
	public JLabel recipeLabel, lemonLabel, iceLabel, sugarLabel, priceLabel;
	public JPanel lemonPanel, icePanel, sugarPanel, pricePanel;
	public JTextField lemonField, iceField, sugarField, priceField;

	public KeyAdapter integerAdapter = new KeyAdapter() {
		public void keyTyped(KeyEvent e) {
			char c = e.getKeyChar();
			if (!((c >= '0') && (c <= '9') ||
					(c == KeyEvent.VK_BACK_SPACE) ||
					(c == KeyEvent.VK_DELETE))) {
				getToolkit().beep();
				e.consume();
			}
		}
	};
	public KeyAdapter decimalAdapter = new KeyAdapter() {
		public void keyTyped(KeyEvent e) {
			char c = e.getKeyChar();
			if (!((c >= '0') && (c <= '9') || (c == '.') ||
					(c == KeyEvent.VK_BACK_SPACE) ||
					(c == KeyEvent.VK_DELETE))) {
				getToolkit().beep();
				e.consume();
			}
		}
	};

	public RecipePanel(){
		super();
		this.setLayout(new BorderLayout());
		JPanel paddedPanel = new JPanel();
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEADING));
		paddedPanel.setLayout(new BoxLayout(paddedPanel, BoxLayout.Y_AXIS));
		recipeLabel = new JLabel("Recipe:");
		lemonLabel = new JLabel("Lemons");
		iceLabel = new JLabel("Ice");
		sugarLabel = new JLabel("Sugar");
		priceLabel = new JLabel("Price");
		lemonField = new JTextField("0", 4);
		lemonField.addKeyListener(integerAdapter);
		iceField = new JTextField("0", 4);
		iceField.addKeyListener(integerAdapter);
		sugarField = new JTextField("0", 4);
		sugarField.addKeyListener(integerAdapter);
		priceField = new JTextField("0", 4);
		priceField.addKeyListener(decimalAdapter);
		lemonPanel = new JPanel();
		lemonPanel.setLayout(new BoxLayout(lemonPanel, BoxLayout.Y_AXIS));
		lemonPanel.add(lemonLabel);
		lemonPanel.add(lemonField);
		icePanel = new JPanel();
		icePanel.setLayout(new BoxLayout(icePanel, BoxLayout.Y_AXIS));
		icePanel.add(iceLabel);
		icePanel.add(iceField);
		sugarPanel = new JPanel();
		sugarPanel.setLayout(new BoxLayout(sugarPanel, BoxLayout.Y_AXIS));
		sugarPanel.add(sugarLabel);
		sugarPanel.add(sugarField);
		pricePanel = new JPanel();
		pricePanel.setLayout(new BoxLayout(pricePanel, BoxLayout.Y_AXIS));
		pricePanel.add(priceLabel);
		pricePanel.add(priceField);
		recipeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		paddedPanel.add(recipeLabel);
		panel.add(lemonPanel);
		panel.add(sugarPanel);
		panel.add(icePanel);
		panel.add(pricePanel);
		paddedPanel.add(panel);
		this.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1, true));
		this.add(paddedPanel, BorderLayout.CENTER);
	}
	public Recipe getRecipe() throws RecipeInputException{
		int lemons = (int)getTextWrapper(lemonField);
		int sugar  = (int)getTextWrapper(sugarField);
		int ice    = (int)getTextWrapper(iceField);
		double price =    getTextWrapper(priceField);
		if(lemons < 0 || sugar < 0 || ice < 0 || price < 0) throw new RecipeInputException();
		return new Recipe(lemons, sugar, ice, price);
	}
	
	public double getTextWrapper(JTextField text){
		double n;
		try{
			n = Double.parseDouble(text.getText());
			text.setBackground(Color.lightGray);
			
			return n;
		}
		catch(Exception e){
			if(Constants.debugging) System.err.println(e);
			text.setBackground(Color.red);
		}
		return -1;
	}
	public static void main(String[] args) {
		JFrame frame = new JFrame("Recipe Panel");
		frame.getContentPane().add(new RecipePanel());
		frame.pack();
		frame.setVisible(true);
		frame.setSize(400,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
