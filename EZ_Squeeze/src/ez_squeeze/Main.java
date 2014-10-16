/*
 * GNU GPL v3 Copyright 2011-2012 Nick Stanish
 */

package ez_squeeze;

import com.alee.laf.WebLookAndFeel;

public class Main {
  public static void main(String[] args) {
    /*
     * WebLaf https://github.com/mgarin/weblaf
     */

    WebLookAndFeel.install();

    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new EzSqueeze();
      }
    });
  }
}
