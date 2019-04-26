/*
 * RegionsOfLevel.java
 *
 * Created on September 6, 2004, 11:07 PM
 */

package blurock.CobwebCluster;
import java.util.ArrayList;

/**
 *
 * @author  reaction
 */
public class RegionsOfLevel {
    ArrayList Regions = new ArrayList();
    /** Creates a new instance of regionsOfLevels */
    public RegionsOfLevel(ArrayList lst, int level) {
        Regions = lst;
    }
    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("==============================================================\n");
        for(int i=0; i< Regions.size();i++) {
            RegionsOfNode regions = (RegionsOfNode) Regions.get(i);
            buf.append(regions.toString());
        }
        return buf.toString();
    }
}
