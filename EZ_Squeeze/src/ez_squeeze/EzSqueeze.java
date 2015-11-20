package ez_squeeze;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import net.vizbits.materialdesigncolors.MaterialColor;
import ez_squeeze.files.LoadHelper;
import ez_squeeze.files.SaveHelper;
import ez_squeeze.game.Constants;
import ez_squeeze.game.State;
import ez_squeeze.media.FontLoader;
import ez_squeeze.menu.Menu;
import ez_squeeze.menu.MenuItem;
import ez_squeeze.menu.MenuPanel;

public class EzSqueeze extends JFrame {
  /**
   * @author Nick Stanish
   */
  private static final long serialVersionUID = 2902265810787080470L;
  public static final String version = "3.1.0";
  public JPanel topPanel, cardPanel, contentPane;
  public JPanel optionsCard, helpCard, exitCard; // cards/views
  public GameScreen gameCard;
  private MenuPanel menuPanel;
  private MenuItem continueItem;
  public JLabel titleLabel;
  public JFileChooser fileChooser;

  public enum Cards {
    MENU, GAME, OPTIONS, ABOUT, HELP, EXIT
  }

  public EzSqueeze() {
    super("EZ Squeeze");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    initComponents(this.getContentPane());
    this.pack();
    this.setVisible(true);
    this.setPreferredSize(new Dimension(500, 600));
    this.setMinimumSize(new Dimension(500, 600));
    this.setSize(500, 600);
  }

  /**
   * initialize the components of the program
   * 
   * @param pane: content pane of the main frame
   */
  private void initComponents(Container pane) {
    contentPane = new JPanel();
    contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
    topPanel = new JPanel();
    cardPanel = new JPanel();
    fileChooser = new JFileChooser(".");
    cardPanel.setLayout(new CardLayout(1, 1));
    pane.add(contentPane);
    contentPane.add(topPanel);
    contentPane.add(cardPanel);
    initTop();
    initCards();

  }

  private void initTop() {
    // topPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
    titleLabel = new JLabel("EZ Squeeze");
    titleLabel.setFont(FontLoader.loadFontElse(FontLoader.SANSATION_REGULAR, Font.BOLD, 40,
        Font.SERIF));
    titleLabel.setForeground(Color.decode(MaterialColor.YELLOW));
    topPanel.add(titleLabel);
    topPanel.setDoubleBuffered(true);
    topPanel.setBackground(Color.decode(MaterialColor.WHITE));

  }

  private Menu createMenu() {
    Menu menu = new Menu();
    continueItem = new MenuItem("Continue", () -> displayContinue());
    MenuItem newGameItem = new MenuItem("New Game", () -> displayNew());
    MenuItem loadItem = new MenuItem("Load", () -> displayLoad());
    MenuItem optionsItem = new MenuItem("Options", () -> displayOptions());
    MenuItem helpItem = new MenuItem("Help", () -> displayHelp());
    MenuItem aboutItem = new MenuItem("About", () -> displayAbout());
    MenuItem exitItem = new MenuItem("Exit", () -> displayExit());
    continueItem.setEnabled(false);
    optionsItem.setEnabled(false);
    helpItem.setEnabled(false);
    aboutItem.setEnabled(false);
    menu.registerItem(continueItem, newGameItem, loadItem, optionsItem, helpItem, aboutItem,
        exitItem);
    return menu;
  }

  private void initCards() {
    // cardPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
    cardPanel.setBackground(Color.YELLOW);
    // menuCard = new TopMenuScreen(this);

    menuPanel = new MenuPanel(createMenu());
    cardPanel.add(menuPanel, Cards.MENU.name());
    gameCard = new GameScreen(this, null);
    cardPanel.add(gameCard, Cards.GAME.name());
  }

  private void displayAbout() {
    // TODO Auto-generated method stub

  }

  private void displayExit() {
    if (Constants.debugging)
      System.err.println("Exitting ---TODO: show are you sure screen?");
    System.exit(0);

  }

  private void displayHelp() {
    // TODO Auto-generated method stub

  }

  public void saveState(State state) {
    if (state == null) {
      JOptionPane.showMessageDialog(this, "Invalid game state");
      return;
    }
    int result = fileChooser.showSaveDialog(this);
    if (result == JFileChooser.APPROVE_OPTION) {
      final File selectedFile = fileChooser.getSelectedFile();

      new Thread() {
        public void run() {
          try {
            SaveHelper saveHelper = new SaveHelper();
            saveHelper.writeSave(selectedFile, state);

            JOptionPane.showMessageDialog(null, "Saved!");
          } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occured while saving");

          }
        }
      }.start();
    }

  }


  private void displayLoad() {
    int result = fileChooser.showOpenDialog(this);
    if (result == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      Constants.LOG("Load " + file.getName());
      State state = null;
      try {
        LoadHelper loadHelper = new LoadHelper();
        state = loadHelper.readSave(file);
      } catch (Exception e) {
        e.printStackTrace();
        Constants.LOGERROR(e.getMessage());
      }
      if (state != null) {
        gameCard.loadState(state);
        CardLayout cl = (CardLayout) (cardPanel.getLayout());
        cl.show(cardPanel, Cards.GAME.name());
      } else {
        JOptionPane.showMessageDialog(this, "An error occurred loading your game file");
      }

    }

  }

  private void displayNew() {
    gameCard.loadState(new State(true));
    CardLayout cl = (CardLayout) (cardPanel.getLayout());
    cl.show(cardPanel, Cards.GAME.name());

  }

  private void displayOptions() {
    // TODO Auto-generated method stub

  }

  public void displayMenuFromGame() {
    continueItem.setEnabled(true);
    CardLayout cl = (CardLayout) (cardPanel.getLayout());
    cl.show(cardPanel, Cards.MENU.name());
  }

  private void displayContinue() {
    CardLayout cl = (CardLayout) (cardPanel.getLayout());
    cl.show(cardPanel, Cards.GAME.name());
  }
}
