/*
 * DefinedRegionUnderCondition.java
 *
 * Created on September 6, 2004, 5:40 PM
 */

package blurock.Consectutive;
import blurock.Consectutive.DefinedRegion;
import blurock.CobwebCluster.ClusterNodeData;

/**
 *
 * @author  reaction
 */
public class DefinedRegionUnderCondition extends blurock.Consectutive.DefinedRegion {
    ClusterNodeData Condition;
    /** Creates a new instance of DefinedRegionUnderCondition */
    public DefinedRegionUnderCondition(DefinedRegion region, ClusterNodeData condition) {
        super(region);
        Condition = condition;
    }
    public int compare(Object obj, Object obj1) {
        DefinedRegionUnderCondition reg1 = (DefinedRegionUnderCondition) obj;
        DefinedRegionUnderCondition reg2 = (DefinedRegionUnderCondition) obj1;
        ClusterNodeData Condition1 = reg1.Condition;
        ClusterNodeData Condition2 = reg2.Condition;
        double[] values1 = Condition1.values;
        double[] values2 = Condition2.values;
        int result = 0;
        if(values1[Condition1.levelIndex] < values2[Condition2.levelIndex]) 
            result = 1;
        else if(values1[Condition1.levelIndex]  > values2[Condition2.levelIndex]) 
            result = -1;
        else {
            result = reg1.Name.compareTo(reg2.Name);
            if(result == 0) {
                if(values1[1] < values2[1])
                    result = 1;
                else if(values1[1] > values2[1])
                    result = -1;
                else
                    result = super.compare(obj,obj1);
            }
        }
        return result;
    }
    public String toString() {
        String conditions = Condition.toStringConditions();
        String region = super.toString();
        return region + "," + conditions;
    }
}
