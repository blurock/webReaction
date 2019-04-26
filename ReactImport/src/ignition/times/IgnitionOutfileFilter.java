/*
 * IgnitionOutfileFilter.java
 *
 * Created on May 7, 2003, 10:22 AM
 */

package ignition.times;
import java.io.*;
/**
 *
 * @author  reaction
 */
public class IgnitionOutfileFilter extends javax.swing.filechooser.FileFilter {
    
    private String speciesName = null;
    private String pressureString = null;
    private String phiString = null;
    
    /** Creates a new instance of IgnitionOutfileFilter */
    public IgnitionOutfileFilter(String name, String pressure, String phi) {
        pressureString = pressure;
        phiString = phi;
        speciesName = name;
    }
    
    public boolean accept(java.io.File file) {
 	boolean result = false;
	String filename = file.getName(); 
	if(filename.startsWith(speciesName) && 
            filename.indexOf(pressureString) > 0 &&
            filename.indexOf(phiString) > 0)
                result = true;
        if(file.isDirectory()) {
            result = true;
        }
	return result;
    }
    
    public String getDescription() {
        return new String("Ignition Output Files");
    }
}
