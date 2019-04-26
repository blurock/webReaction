/*
 * FileToString.java
 *
 * Created on February 10, 2001, 12:24 PM
 */

package utilities;
import java.io.*;

/**
 * This class reads extends FileFrame to read in a file directly from the constructor
 * The result is put in 'outputString'.
 * @author Edward S. Blurock
 * @version August, 2006
 */
public class FileToString extends ReadFileToString {
    /**
     * This constructor asks for and reads in the file (as specified by the input parameters).
     * The String results are in 'outputString'. If outputString is null, then the read was not successful.
     * @param title The title of the frame asking to select file
     * @param dir The default directory
     * @param ftype A string pattern to match after the dot.
     */
    public FileToString(String title, String dir, String ftype) {
      FileFrame ff = new FileFrame();
      ff.setupButton(title,dir,ftype);
      ff.getFile();
      File chosenFile = ff.chosenFile;
      read(chosenFile);
    }
}
