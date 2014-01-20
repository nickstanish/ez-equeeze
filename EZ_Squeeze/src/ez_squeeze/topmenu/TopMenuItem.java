package ez_squeeze.topmenu;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
/**
 * 
 * @author Nick Stanish
 *
 */
public class TopMenuItem{
	public Item item;
	public boolean hovered = false;
	public int index;
	public Color highlightColor = Color.cyan;
	public Color baseColor = Color.black;
	public Point base;
	public int xpadding = 30;
	public Rectangle2D.Double area = new Rectangle2D.Double();
	public Rectangle2D.Double paddedArea = new Rectangle2D.Double();
	public enum Item{
		NEW, LOAD, OPTIONS, HELP, ABOUT, EXIT
	}
	/**
	 * 
	 * @param name: name of menu item
	 * @param index: index in list of menu items
	 * @param baseCoordinate: top left coordinate in which to begin listing items
	 */
	public TopMenuItem(Item item, int index, Point baseCoordinate){
		this.item = item;
		this.index = index;
		this.base = baseCoordinate;
	}
	public void printRect(Rectangle2D rect){
		System.out.println(rect.getX() + " " + rect.getY() + " " + rect.getWidth() + " " + rect.getHeight());
	}
	public boolean hovered(Point p){
		hovered = area.contains(p);
		return hovered;
	}
	public void draw(Graphics2D g) {
		FontMetrics fm = g.getFontMetrics();
		Rectangle2D rect = fm.getStringBounds(item.name(), g);
		double height = fm.getAscent() + fm.getDescent();
		paddedArea = new Rectangle2D.Double(base.x - xpadding/2, (base.y + (index) * (height + 1))+ fm.getDescent(), rect.getWidth() + xpadding, height + fm.getDescent());
		area = new Rectangle2D.Double(base.x, (base.y + (index) * (height + 1)),rect.getWidth(),rect.getHeight() );
		if(hovered){
			g.setColor(highlightColor);
			g.fill(paddedArea);
		}
		g.setColor(baseColor);
		g.drawString(item.name(), (int) base.x, (int)(base.y + (index + 1) * (height + 1)));
		
		
	}

}
