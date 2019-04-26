/*
 * StandardAttrFileFilter.java
 *
 * Created on February 15, 2001, 3:28 PM
 */

package blurock.utilities;
import java.io.File;
import javax.swing.filechooser.FileFilter;
/**
 *
 * @author  reaction
 * @version 
 */
public class StandardAttrFileFilter extends FileFilter {

    /** Creates new StandardAttrFileFilter */
    public StandardAttrFileFilter() {
    }
    
    public boolean accept(File f)
    {
	boolean result = false;
	String filename = f.getName();
	if(filename.endsWith(".inp"))
	    result = true;
	return result;
    }
    public String getDescription()
    {
	return new String("Filters out Attribute Files");
    }

}
