/*
 * GNU GPL v3
 * Copyright 2011-2012 Nick Stanish
 */

package ez_squeeze;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {
	public static void main(String[] args) {
		try {
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new EzSqueeze();
			}
		});
	}
}
/*
for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
	if ("Nimbus".equals(info.getName())) {
		UIManager.setLookAndFeel(info.getClassName());
		 break;
	 }
 }
 * 
 */
