/*
 * SetOfRegionsOfLevel.java
 *
 * Created on September 7, 2004, 8:21 PM
 */

package blurock.CobwebCluster;
import java.util.ArrayList;
/**
 *
 * @author  reaction
 */
public class SetOfRegionsOfLevel {
    ArrayList Set;
    /** Creates a new instance of SetOfRegionsOfLevel */
    public SetOfRegionsOfLevel() {
        Set = new ArrayList();
    }
    void addLevelSet(RegionsOfLevel lset) {
        Set.add(lset);
    }
    public String toString() {
        StringBuffer buf = new StringBuffer();
        for(int i=0;i<Set.size();i++) {
            RegionsOfLevel lset = (RegionsOfLevel) Set.get(i);
            buf.append(lset.toString());
        }
        return buf.toString();
    }
    
}
