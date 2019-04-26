/*
 * ClusterNodeData.java
 *
 * Created on August 26, 2004, 8:59 PM
 */

package blurock.CobwebCluster;
import java.util.Comparator;
/** This class contains the information of a singe point at a given set of
 * conditions;
 * Temperature, Pressure and Stochiametry determine the running conditions
 * position is the position of the point
 * Name and level are the node and level of the node
 * @author reaction
 */
public class ClusterNodeData implements Comparator {
    public int regionIndex = 0;
    public int levelIndex  = 1;
    /** The cluster node name
     */    
    public String Name;
    
    public double[] values = null;
    
    /** Creates a new instance of ClusterNodeData */
    ClusterNodeData() {
    }
    /** This returns true if the conditions match.
     * The set of condition matched here is not
     * the entire set of parameters defining the class.
     * @param data The comparison
     *
     * @return true if the conditions match
     *
     */    
    public boolean conditionsMatch(ClusterNodeData data) {
        boolean ans = true;
        if(data.values != null) {
            if(values != null) {
                if(values.length != data.values.length) 
                    ans = false;
                else {
                    for(int i=0;i<values.length;i++) {
                        if(i != regionIndex) {
                            if(values[i] != data.values[i])
                                ans = false;
                        }
                    }
            }
          }
        }
        return ans;
    }
    /** Just puts the conditions in a string
     * @return The string with the conditions
     */    
    public String toStringConditions() {
        StringBuffer buf = new StringBuffer();
        buf.append(Name);
        buf.append(": ");
        if(values == null) 
            buf.append("NULL");
        else {
            for(int i=0;i<values.length;i++) {
                if(i == regionIndex)
                    buf.append("Region[");
                else if(i == levelIndex) 
                    buf.append("Level[");
                else
                    buf.append("D[");
                buf.append(i);
                buf.append("]=");
                buf.append(values[i]);
                buf.append("  ");
            }
        }
        return buf.toString();
    }
    public String toString() {
        String data = new String(Name + ":" + toStringConditions());
        return data;
    }
    public int compare(Object o1, Object o2) {
        ClusterNodeData d1 = (ClusterNodeData) o1;
        ClusterNodeData d2 = (ClusterNodeData) o2;
        int ans = 0;
        if(d1.values[d1.levelIndex] > d2.values[d2.levelIndex])
            ans = 1;
        else if(d1.values[d1.levelIndex] < d2.values[d2.levelIndex])
            ans = -1;
        if(ans == 0) {
            ans = d1.Name.compareTo(d2.Name);
            if(ans == 0) {
            if(d1.values[d1.regionIndex] > d2.values[d2.regionIndex])
                ans = 11;
            else if(d1.values[d1.regionIndex] < d2.values[d2.regionIndex])
                ans = -1;
            }
        }
        return ans;
    }
}
