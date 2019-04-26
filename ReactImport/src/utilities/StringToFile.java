/*
 * StringToFile.java
 *
 * Created on August 17, 2006, 4:30 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package utilities;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
/**
 *
 * @author reaction
 */
public class StringToFile {
    
    /** Creates a new instance of StringToFile */
    public StringToFile() {
    }
    
    public void makeFile(String name, String content) throws IOException {
        try {
            FileOutputStream str = new FileOutputStream(name);
            str.write(content.getBytes());
            str.close();
        } catch(FileNotFoundException io) {
            throw new IOException("File cannot be created: " + name);
        }
    }
}
