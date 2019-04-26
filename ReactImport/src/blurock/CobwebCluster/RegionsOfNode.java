/*
 * RegionsOfNode.java
 *
 * Created on September 6, 2004, 10:56 PM
 */

package blurock.CobwebCluster;
import java.util.ArrayList;
import blurock.Consectutive.DefinedRegion;

/** This collects all the regions having the same node
 * name (and hence also level).
 *
 * The purpose of this class is to determine the behavior
 * of the cluster region with respect to condition.
 * @author reaction
 */
public class RegionsOfNode {
    /** The level of the node
     */    
    public int level;
    /** The cluster node name of the node
     */    
    public String Name;
    /** The set of regions (<B>Definedegions</B>) having this level and node name
     */    
    public ArrayList regions = new ArrayList();
    /** Creates a new instance of RegionsOfNode by isolating
     * all the nodes from the set with the given name.
     * @param l The cluster level
     * @param n The node name
     * @param Set The complete set of regions
     */
    public RegionsOfNode(int l, String n,ArrayList Set) {
        level = l;
        Name  = n;
        regionsWithName(Set);
    }
    /** This isolates the nodes with the given name
     * from the set of regions
     * @param Set The entire set of node regions to search
     */    
    private void regionsWithName(ArrayList Set) {
        for(int i=0;i<Set.size();i++) {
            ClusterNodeDataSet set = (ClusterNodeDataSet) Set.get(i);
            DefinedRegion region = set.regionWithName(Name);
            if(region != null) {
                if(region.Level == level) 
                    regions.add(region);
            } 
        }
    }
    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("----- " + Name + "----- " + level + "\n");
        for(int i=0;i<regions.size();i++) {
            DefinedRegion region = (DefinedRegion) regions.get(i);
            String regionS = region.toString();
            buf.append(regionS);
            buf.append("\n");
        }
        return buf.toString();
    }
    
}
