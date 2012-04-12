/*
 * GNU GPL v3
 * Copyright 2011-2012 Nick Stanish
 */

package ez_squeeze;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class EZ_Squeeze_EmpireApp extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        show(new EZ_Squeeze_EmpireView(this));
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of EZ_Squeeze_EmpireApp
     */
    public static EZ_Squeeze_EmpireApp getApplication() {
        return Application.getInstance(EZ_Squeeze_EmpireApp.class);
    }

    /**
     * Main method launching the application.
     * @param args 
     */
    public static void main(String[] args) {
        launch(EZ_Squeeze_EmpireApp.class, args);
    }
}
