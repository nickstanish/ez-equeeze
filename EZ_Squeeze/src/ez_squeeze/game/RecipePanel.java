package ez_squeeze.game;

import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RecipePanel extends JPanel{
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
		this.setLayout(new FlowLayout(FlowLayout.LEADING));
		lemonField = new JTextField("0", 4);
		lemonField.addKeyListener(integerAdapter);
		iceField = new JTextField("0", 4);
		iceField.addKeyListener(integerAdapter);
		sugarField = new JTextField("0", 4);
		sugarField.addKeyListener(integerAdapter);
		priceField = new JTextField("0", 4);
		priceField.addKeyListener(decimalAdapter);
		this.add(lemonField);
		this.add(iceField);
		this.add(sugarField);
		this.add(priceField);
	}
	public Recipe getRecipe(){
		return null;
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
