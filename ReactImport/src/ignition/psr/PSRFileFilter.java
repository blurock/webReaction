/*
 * PSRFileFilter.java
 *
 * Created on March 14, 2006, 10:35 AM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package ignition.psr;

/**
 *
 * @author reaction
 */
public class PSRFileFilter extends javax.swing.filechooser.FileFilter {
    public String psrPrefix = "RP";
    /** Creates a new instance of PSRFileFilter */
    public PSRFileFilter() {
    }
   public boolean accept(java.io.File file) {
 	boolean result = false;
	String filename = file.getName(); 
	if(filename.startsWith(psrPrefix)) 
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
