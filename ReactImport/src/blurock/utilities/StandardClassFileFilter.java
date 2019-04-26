/*
 * StandardClassFileFilter.java
 *
 * Created on February 15, 2001, 3:26 PM
 */

package blurock.utilities;
import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author  reaction
 * @version 
 */
public class StandardClassFileFilter extends FileFilter {

    /** Creates new StandardClassFileFilter */
    public StandardClassFileFilter() {
    }
    
    public boolean accept(File f)
    {
	boolean result = false;
	String filename = f.getName();
	if(filename.endsWith("Class.inp"))
	    result = true;
	return result;
    }
    
    public String getDescription()
    {
	return new String("Filters out Class Files");
    }

}
