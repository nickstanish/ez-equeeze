package ez_squeeze.menu;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * 
 * @author Nick Stanish
 *
 */
public class MenuPanel extends JPanel implements MouseMotionListener, MouseListener {

  private Menu menu;

  private Timer timer;
  private static final Integer TIMER_DELAY = (int) (1000.0 / 30.0);

  public MenuPanel(Menu menu) {
    this.menu = menu;
    timer = new Timer(TIMER_DELAY, event -> timerAction(event));
    timer.start();
    addMouseListener(this);
    addMouseMotionListener(this);
  }

  public void timerAction(ActionEvent event) {
    repaint();
  }

  public void paintComponent(Graphics g1) {
    super.paintComponent(g1);
    Graphics2D g = (Graphics2D) g1;
    Point coor = getMenuBase();
    menu.draw(g, (int) coor.getX(), (int) coor.getY());

  }

  private static void callback() {
    System.out.println("action");

  }

  public static void main(String[] args) {
    JFrame window = new JFrame("MenuPanel");
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Menu menu = new Menu();
    MenuItem newGameItem = new MenuItem("New Game", () -> callback());
    MenuItem continueItem = new MenuItem("Continue", () -> callback());
    continueItem.setEnabled(false);
    MenuItem loadItem = new MenuItem("Load", () -> callback());
    MenuItem optionsItem = new MenuItem("Options", () -> callback());
    menu.registerItem(newGameItem, continueItem, loadItem, optionsItem);
    MenuPanel menuPanel = new MenuPanel(menu);

    window.getContentPane().add(menuPanel);
    window.pack();
    window.setVisible(true);
    window.setSize(400, 400);
  }

  public Point getMenuBase() {
    return new Point(getWidth() / 2 - Menu.MENU_WIDTH / 2, 100);
  }

  @Override
  public void mouseClicked(MouseEvent e) {

    menu.checkClick(translateToMenu(e.getPoint()));
  }

  public Point translateToMenu(Point point) {
    Point coor = getMenuBase();
    int x = (int) (point.getX() - coor.getX());
    int y = (int) (point.getY() - coor.getY());
    return new Point(x, y);
  }

  @Override
  public void mousePressed(MouseEvent e) {}

  @Override
  public void mouseReleased(MouseEvent e) {}

  @Override
  public void mouseEntered(MouseEvent e) {}

  @Override
  public void mouseExited(MouseEvent e) {}

  @Override
  public void mouseDragged(MouseEvent e) {}

  @Override
  public void mouseMoved(MouseEvent e) {
    menu.checkHover(translateToMenu(e.getPoint()));
  }
}
