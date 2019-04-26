/*
 * SpeciesList.java
 *
 * Created on December 16, 2002, 6:16 PM
 */

package convert;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.io.*;

/**
 *
 * @author  reaction
 */
public class SpeciesList extends ArrayList {
     int count = 0;
     Hashtable speciesIDs = new Hashtable();
    /** Creates a new instance of SpeciesList */
    public SpeciesList() {
    }
    
    public void read(StringTokenizer tok) throws IOException {
        while(tok.hasMoreTokens()) {
            String sp = tok.nextToken();
            String name = sp.trim();
            add(count,name);
            speciesIDs.put(name, new Integer(count));
            count++;
        }
    }
    public int getID(String name) {
        Integer id = (Integer) speciesIDs.get(name);
        return id.intValue();
    }
}
