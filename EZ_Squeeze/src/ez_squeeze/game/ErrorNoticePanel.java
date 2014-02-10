package ez_squeeze.game;

import javax.swing.JPanel;

/**
 * Panel to go above card view to display any errors/notices
 * @author Nick Stanish
 *
 */
public class ErrorNoticePanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1562596972645382785L;
	//TODO
	public ErrorNoticePanel(){
		super();
		//
	}
	public void displayError(String s){
		Constants.LOGERROR(s);
		//TODO
	}
}
