package forecast;

import javax.swing.JPanel;
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
	public Forecast forecast;
	public Day day;
	public ForecastPanel(){
		super();
		forecast = new Forecast();
		day = new Day();
	}
	public void nextDay(){
		forecast = new Forecast();
		day.nextDay();
	}
}
