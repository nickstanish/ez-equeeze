package ez_squeeze.files;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import ez_squeeze.game.Constants;
import ez_squeeze.game.Recipe;
import ez_squeeze.game.State;

public class SaveHelper extends FileHelper {

  public SaveHelper() {}

  private boolean verifyWrite(File file) {
    if (file.exists()) {
      String title = "Verify Overwrite";
      String message = "File already exists. Are you sure you want to overwrite?";
      int result = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
      return result == JOptionPane.YES_OPTION;
    }
    return true;
  }

  public File addFileExtension(File file, String extension) throws IOException {
    String name = file.getCanonicalPath();
    if (!name.endsWith(extension)) {
      int index = name.lastIndexOf('.');
      if (index > 0 && index > name.lastIndexOf(File.separator)) {
        // existing extension
        name = name.substring(0, index);
      }
      name = name.concat(extension);
    }
    return new File(name);
  }


  public void writeSave(File file, State state) throws Exception {
    file = addFileExtension(file, FILE_EXTENSION);
    if (!verifyWrite(file)) {
      Constants.LOG("Cancelled write");
      return;
    }

    Gson gson = new Gson();
    String data = gson.toJson(state);
    String hash = getHashCode(data);

    try (PrintWriter writer = new PrintWriter(new FileOutputStream(file))) {
      writer.println(HEADER);
      writer.print(VERSION_INDICATOR);
      writer.println(SAVE_VERSION);
      writer.print(HASH_INDICATOR);
      writer.println(hash);
      writer.print(DATA_INDICATOR);
      writer.println(data);
      writer.flush();
      writer.close();
    }

  }

  public static void main(String[] args) throws Exception {
    State state = new State(true);
    state.cups = 0;
    state.recipe = new Recipe(1, 1, 1, 0.25);
    File file = new File("test.ezs");
    SaveHelper helper = new SaveHelper();
    helper.writeSave(file, state);
    LoadHelper loadHelper = new LoadHelper();
    loadHelper.readSave(file);
  }
}
