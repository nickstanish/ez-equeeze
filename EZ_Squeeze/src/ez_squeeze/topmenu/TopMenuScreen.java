package ez_squeeze.topmenu;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
/**
 * This class encapsulates the top menu screen. It is drawn graphically
 * and gives opens for a new game, loading a saved game, instructions, 
 * an about screen, and persistent options
 * 
 * @author Nick Stanish
 *
 */
public class TopMenuScreen extends JPanel implements MouseListener, MouseMotionListener{
	public Color background = new Color(255, 255, 153);
	public Point base = new Point(50,50);
	public int fontSize = 36;
	public Font font = new Font(Font.SERIF, Font.BOLD, fontSize);
	public static void main(String[] args) {
		JFrame frame = new JFrame("Top Menu Screen");
		frame.getContentPane().add(new TopMenuScreen());
		frame.pack();
		frame.setVisible(true);
		frame.setSize(400,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public TopMenuScreen(){
		super();
		for(TopMenuItem.Item item : TopMenuItem.Item.values()){
			TopMenuItem menuItem = new TopMenuItem( item, menuItems.size(), base);
			menuItems.add(menuItem);
		}
		addMouseListener(this);
		addMouseMotionListener(this);
		timer.start();
	}
	public int refreshRate = 1000 / 30;
	public Point mouseLocation;
	public ArrayList<TopMenuItem> menuItems = new ArrayList<TopMenuItem>();
	public Timer timer = new Timer(refreshRate, new ActionListener(){
		public void actionPerformed(ActionEvent e){
			for(TopMenuItem item: menuItems){
				if(mouseLocation != null) item.hovered(mouseLocation);
			}
			repaint();
		}
	});
	@Override
	public void paintComponent(Graphics g1){
		super.paintComponent(g1);
		Graphics2D g = (Graphics2D) g1;
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_DEFAULT);
		g.setFont(font);
		int width = getWidth();
		int height = getHeight();
		g.setColor(background);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.black);
		for(TopMenuItem item : menuItems){
			item.draw(g);
		}
		if(mouseLocation != null) g.fill(new Ellipse2D.Double(mouseLocation.x - 5, mouseLocation.y - 5, 10, 10));
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		for(TopMenuItem item: menuItems){
			if(mouseLocation != null && item.hovered(mouseLocation)){
				System.out.println(item.item.name() + " clicked");
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseLocation = e.getPoint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseLocation = e.getPoint();
	}
}