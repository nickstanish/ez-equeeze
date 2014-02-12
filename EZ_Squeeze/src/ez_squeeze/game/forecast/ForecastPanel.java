package ez_squeeze.game.forecast;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import ez_squeeze.game.State;
/**
 * 
 * @author Nick Stanish
 *
 */
public class ForecastPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1116327336347843667L;
	public State state;
	public JLabel dayLabel, weatherLabel, tempLabel;
	public ForecastPanel(State state){
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.state = state;
		initComponents();
		update();
		
	}
	private void initComponents(){
		
		dayLabel = new JLabel();
		weatherLabel = new JLabel();
		tempLabel = new JLabel();
		
		JPanel panel = new JPanel();
		panel.add(dayLabel);
		this.add(panel);
		
		panel = new JPanel();
		panel.add(weatherLabel);
		this.add(panel);
		
		panel = new JPanel();
		panel.add(tempLabel);
		this.add(panel);
	}
	public void update(){
		if(state == null) return;
		dayLabel.setText(state.day.day.name());
		weatherLabel.setText(state.forecast.weather.name());
		tempLabel.setText(state.forecast.temperature + " °F" );
	}
	public static void main (String[] args){
		JFrame test = new JFrame();
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final State state = new State();
		final ForecastPanel fp = new ForecastPanel(state);
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.cyan, 3));
		fp.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
		panel.add(fp);
		test.getContentPane().add(panel);
		test.pack();
		test.setVisible(true);
		test.setSize(150,200);
		Timer timer = new Timer(3000, new ActionListener(){
			public void actionPerformed(ActionEvent e){
				state.nextDay();
				fp.update();
			}
		});
		timer.start();
	}
}
