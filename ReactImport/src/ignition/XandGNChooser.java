/*
 * XandGNChooser.java
 *
 * Created on July 14, 2004, 5:55 PM
 */

package ignition;
import java.io.File;
/**
 *
 * @author  reaction
 */
public class XandGNChooser extends javax.swing.filechooser.FileFilter {
    String Xprefix = "X";
    String GNprefix = "GN";
    
    /** Creates a new instance of XandGNChooser */
    public XandGNChooser() {
    }
    public XandGNChooser(String xprefix, String gnprefix) {
        Xprefix = xprefix;
        GNprefix = gnprefix;
    }
    public boolean accept(java.io.File file) {
           boolean acc = false;
         String name = file.getName();
        if(name.startsWith(Xprefix) && name.endsWith(".txt")) {
            System.out.println(name);
            int l = name.length();
            String specs = name.substring(5,l-4);
            System.out.println(specs);
            File parent = file.getAbsoluteFile().getParentFile();
            String[] namelist = parent.list();
            for(int i=0; i< namelist.length; i++) {
                if(namelist[i].startsWith(GNprefix) && 
                  namelist[i].endsWith(".txt")  &&
                  namelist[i].indexOf(specs) > 0) 
                    acc = true;
            }
        } else if(file.isDirectory()) {
            acc = true;
        }
        return acc;
    }
    public String getDescription() {
        return new String("The concentration matrix with Importance Matrix");
    }
    
}
