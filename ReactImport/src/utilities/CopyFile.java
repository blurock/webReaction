/*
 * CopyFile.java
 *
 * Created on October 4, 2004, 2:27 PM
 */

package utilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 *
 * @author  reaction
 */
public class CopyFile {
    
    /** Creates a new instance of CopyFile */
    public CopyFile() {
    }
    public void copyFile(File in, File out) throws IOException {
       FileInputStream fis  = new FileInputStream(in);
       FileOutputStream fos = new FileOutputStream(out);
       byte[] buf = new byte[1024];
       int i = 0;
       while((i=fis.read(buf))!=-1) {
         fos.write(buf, 0, i);
         }
       fis.close();
       fos.close();
      }
}
