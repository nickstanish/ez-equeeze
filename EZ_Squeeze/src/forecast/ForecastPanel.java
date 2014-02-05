package forecast;

import javax.swing.JPanel;

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
	public ForecastPanel(State state){
		this.state = state;
	}
	public void update(){
		
	}
}
