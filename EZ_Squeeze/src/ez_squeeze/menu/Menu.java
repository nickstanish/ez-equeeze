package ez_squeeze.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ez_squeeze.media.FontLoader;

/**
 * 
 * @author Nick Stanish
 *
 */
public class Menu {
  private List<MenuItem> items;
  private Font font;
  public static final Integer MENU_WIDTH = 250;
  private static final Integer ITEM_HEIGHT = 40;
  private static final Integer FONT_SIZE = 32;
  private static Map<RenderingHints.Key, Object> renderingHints;


  private static final Color BACKGROUND = Color.WHITE;
  private static final Color MENU_BORDER = Color
      .decode(net.vizbits.materialdesigncolors.MaterialColor.BLUE_900);
  private static final Color FOCUSED_BACKGROUND = Color
      .decode(net.vizbits.materialdesigncolors.MaterialColor.LIGHT_BLUE_100);
  private static final Color TEXT = Color
      .decode(net.vizbits.materialdesigncolors.MaterialColor.BLACK);
  private static final Color DISABLED_TEXT = Color
      .decode(net.vizbits.materialdesigncolors.MaterialColor.GREY_500);

  public Menu() {
    this.items = new ArrayList<MenuItem>();
    this.font =
        FontLoader.loadFontElse(FontLoader.SANSATION_REGULAR, Font.BOLD, FONT_SIZE, Font.SERIF);
  }

  public void registerItem(MenuItem... items) {
    for (MenuItem item : items) {
      registerItem(item);
    }
  }

  public void registerItem(MenuItem item) {
    items.add(item);
  }

  private Map<RenderingHints.Key, Object> getHints() {
    if (renderingHints == null) {
      renderingHints = new HashMap<RenderingHints.Key, Object>();
      renderingHints.put(RenderingHints.KEY_COLOR_RENDERING,
          RenderingHints.VALUE_COLOR_RENDER_QUALITY);
      renderingHints.put(RenderingHints.KEY_TEXT_ANTIALIASING,
          RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    }
    return renderingHints;
  }

  public void draw(Graphics2D g, int x, int y) {
    g.setColor(BACKGROUND);
    g.setRenderingHints(getHints());
    g.fillRect(x, y, MENU_WIDTH, items.size() * ITEM_HEIGHT);
    g.setFont(font);
    FontMetrics metrics = g.getFontMetrics();
    int descent = metrics.getDescent();
    int leading = metrics.getLeading();
    int baselineOffset = ITEM_HEIGHT / 2 - FONT_SIZE / 2 - leading + descent;
    for (int i = 0; i < items.size(); i++) {
      MenuItem item = items.get(i);
      String text = item.getText();
      if (item.getFocused() && item.getEnabled()) {
        g.setColor(FOCUSED_BACKGROUND);
        g.fillRect(x, y + i * ITEM_HEIGHT, MENU_WIDTH, ITEM_HEIGHT);
      }
      g.setColor(MENU_BORDER);
      g.drawRect(x, y + i * ITEM_HEIGHT, MENU_WIDTH, ITEM_HEIGHT);
      if (item.getEnabled()) {
        g.setColor(TEXT);
      } else {
        g.setColor(DISABLED_TEXT);
      }


      int left = (int) (MENU_WIDTH / 2 - metrics.getStringBounds(text, g).getWidth() / 2);
      g.drawString(text, x + left, y + (i + 1) * ITEM_HEIGHT - baselineOffset);
    }
  }

  private int indexOfPoint(Point p) {
    if (p.getX() <= 0 || p.getX() >= MENU_WIDTH || p.getY() <= 0
        || p.getY() >= items.size() * ITEM_HEIGHT) {
      return -1;
    } else {
      return (int) p.getY() / ITEM_HEIGHT;
    }
  }

  public void checkHover(Point p) {
    for (MenuItem item : items) {
      item.setFocused(false);
    }
    if (indexOfPoint(p) < 0)
      return;
    items.get(indexOfPoint(p)).setFocused(true);
  }

  public void checkClick(Point p) {
    int index = indexOfPoint(p);
    if (index < 0)
      return;
    if (items.get(index).getEnabled()) {
      items.get(index).performAction();
    }

  }
}
