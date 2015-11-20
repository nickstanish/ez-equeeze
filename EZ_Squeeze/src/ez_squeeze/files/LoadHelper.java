package ez_squeeze.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.google.gson.Gson;

import ez_squeeze.game.State;

public class LoadHelper extends FileHelper {

  public State readSave(File file) throws Exception {
    String hash = null;
    String data = null;
    String version = null;
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String line = null;
      while ((line = reader.readLine()) != null) {

        if (line.startsWith(HASH_INDICATOR)) {
          hash = line.substring(HASH_INDICATOR.length());
        } else if (line.startsWith(VERSION_INDICATOR)) {
          version = line.substring(VERSION_INDICATOR.length());
        } else if (line.startsWith(DATA_INDICATOR)) {
          data = line.substring(DATA_INDICATOR.length());
        }
      }
      reader.close();
    }
    if (hash == null || data == null || version == null) {
      System.err.println("Your save is invalid - missing data");
      // TODO throw exception
      return null;
    }


    Gson gson = new Gson();
    State state = gson.fromJson(data, State.class);


    String hashCheck = getHashCode(data);
    if (!hashCheck.equals(hash)) {
      System.err.println("Your save is invalid - hash check");
      // TODO throw exception
      return null;
    }



    return state;
  }
}
