/*
 * CClusterNodeDataSet.java
 *
 * Created on September 2, 2004, 5:44 PM
 */

package blurock.CobwebCluster;
import java.util.ArrayList;
import blurock.Consectutive.DefinedRegionLevel;
import blurock.Consectutive.DefinedRegion;
import blurock.Consectutive.DefinedRegionUnderCondition;
import java.util.Collections;

/** This is a collection ClusterNodeData classes representing
 * the same set of running conditions.
 *
 * Set:  The basic collection of points
 * regions: The set of derived regions
 * @author reaction
 */
public class ClusterNodeDataSet extends blurock.CobwebCluster.ClusterNodeData {
    
    
    /** The set of points under the specified conditions
     */    
    public ArrayList Set = new ArrayList();
    /** The entire set of regions derived from the set.
     *
     * This is filled in by the <B>findRegion</B> procedure.
     */    
    public DefinedRegionLevel regions = new DefinedRegionLevel();
    
    /** Creates a new instance of Class */
    public ClusterNodeDataSet() {
    }
    /**
     * @param data The point to check if it belongs to this set
     * (i.e. whether it has the same conditions)
     * @return true:  if the point has the same set of running conditions
     */    
    public boolean belongsToSet(ClusterNodeData data) {
        return this.conditionsMatch(data);
    }
    /**
     * @param data The point to add to the set
     */    
    public void addToSet(ClusterNodeData data) {
        Set.add(data);
    }
    /** This transfers the set of conditions defining this
     * set
     * @param data The conditions of this point are transfered to this set
     */    
    public void transferConditions(ClusterNodeData data) {
        levelIndex = data.levelIndex;
        regionIndex = data.regionIndex;
        if(values == null)
            values = new double[data.values.length];
         for(int i=0;i<values.length;i++) {
            values[i] = data.values[i];
        }
    }
    /** This loops through all the points and collects
     * the regions together.  A region is defined as
     * being in the same node.
     *
     * This procedure fills in <B>regtions</B>.
     *
     *
     */    
    public void findRegions() {
        Collections.sort(Set,new ClusterNodeData());
        DefinedRegionUnderCondition region = null;
        for(int i=0;i<Set.size();i++) {
           ClusterNodeData data = (ClusterNodeData) Set.get(i);
           double pos = data.values[regionIndex];
           double level = data.values[levelIndex];
           int levelI        = (int) level;
           System.out.println("findRegions: " + data.Name + " (" + pos + ", " + level + ")");
            if(region == null) {
                DefinedRegion reg = new DefinedRegion(data.Name,0,pos,pos,levelI);
                region = new DefinedRegionUnderCondition(reg,data);
            }
           String name;
            if(region.Name.equals(data.Name)) {
                region.Top = data.values[regionIndex];
            } else {
               regions.AddObject(region);
               DefinedRegion reg = new DefinedRegion(data.Name,0,pos,pos,levelI);
               region            = new DefinedRegionUnderCondition(reg,data);
            }
        }
        regions.AddObject(region);
    }
    /** The list of all region node names.
     *
     * @return The list of region node names
     */    
    public String[] listOfNodes() {
        return regions.listOfNodes();
    }
    /**
     * @param name The name of the region to find
     * @return The specified <B>DefinedRegion</B>
     */    
    public DefinedRegion regionWithName(String name) {
        return regions.regionWithName(name);
    }
    /**
     * @return Returns the condition and region information
     * of this set as a string
     */    
    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append(toStringConditions());
        buf.append("\n");
        buf.append(regions.divisionsAsString());
        buf.append("\n\n" );
        
        return buf.toString();
    }
}
