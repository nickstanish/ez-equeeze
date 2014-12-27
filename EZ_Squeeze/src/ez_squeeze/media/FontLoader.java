package ez_squeeze.media;

import java.awt.Font;
import java.io.File;

public class FontLoader {
  public static final String SANSATION_LIGHT = "media/fonts/Sansation/Sansation_Light_Italic.ttf";
  public static final String SANSATION_REGULAR = "media/fonts/Sansation/Sansation_Regular.ttf";

  public static Font loadFont(String location, int style, int size) throws Exception {
    File fontFile = new File(location);
    return Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(style, size);
  }

  public static Font loadFontElse(String location, int style, int size, String fallback) {
    Font font;
    try {
      font = loadFont(location, style, size);
    } catch (Exception e) {
      font = new Font(fallback, style, size);
    }
    return font;
  }


}
