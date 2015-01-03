package ez_squeeze.menu;

/**
 * 
 * @author Nick Stanish
 *
 */

public class MenuItem {
  private String text;
  private Callback callback;
  private boolean enabled;
  private boolean focused;

  public MenuItem(String text, Callback callback) {
    this.text = text;
    this.callback = callback;
    this.enabled = true;
    this.focused = false;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }

  public void performAction() {
    callback.accept();
  }

  public boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public boolean isFocused() {
    return focused;
  }

  public void setFocused(boolean focused) {
    this.focused = focused;
  }

  public boolean getFocused() {
    return focused;
  }



}
