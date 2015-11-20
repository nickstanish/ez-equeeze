package ez_squeeze.files;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public abstract class FileHelper {

  protected static final String HEADER = "EZ Squeeze Game Save";
  protected static final String FILE_EXTENSION = ".ezs";
  protected static final String VERSION_INDICATOR = "VERSION=";
  protected static final String SAVE_VERSION = "1";
  protected static final String DATA_INDICATOR = "DATA=";
  protected static final String HASH_INDICATOR = "HASH=";



  protected String getHashCode(String input) throws Exception {
    MessageDigest digest = MessageDigest.getInstance("SHA-256");
    digest.update(input.getBytes(StandardCharsets.UTF_8));
    byte[] digestBytes = digest.digest();
    return javax.xml.bind.DatatypeConverter.printHexBinary(digestBytes);
  }
}
