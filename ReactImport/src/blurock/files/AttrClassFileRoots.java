/*
 * AttrClassFileRoots.java
 *
 * Created on March 23, 2001, 12:26 AM
 */

package blurock.files;

/**
 *
 * @author  reaction
 * @version 
 */
public class AttrClassFileRoots extends java.lang.Object implements java.io.FilenameFilter {
    String[] classes;
    /** Creates new AttrClassFileRoots */
    public AttrClassFileRoots(String path) {
        ListOfFiles files = new ListOfFiles(path);
        files.findFileEndingWith("Class","inp");
        classes = files.listOfNames;
        
    }

    public boolean accept(java.io.File p1,java.lang.String p2) {
        boolean notdone = true;
        String post = ".inp";
        int i = 0;
        if(p2.endsWith(post)) {
            while(notdone && i < classes.length) {
                if(p2.length() == classes[i].length() + 4) {
                    if(p2.startsWith(classes[i]))
                        notdone = false;
                }
            }
        }
        return !notdone;
    }
    
}
