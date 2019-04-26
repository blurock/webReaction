/*
 * CompareDescriptorDifferences.java
 *
 * Created on October 14, 2003, 1:45 PM
 */

package blurock.CobwebCluster;

/**
 *
 * @author  reaction
 */
public class CompareDescriptorDifferences implements java.util.Comparator {
    boolean diffcomp = true;
    
    /** Creates a new instance of CompareDescriptorDifferences */
    public CompareDescriptorDifferences() {
    }
    public void setDifferenceCompare() {
        diffcomp = true;
    }
    public void setDescriptionCompare() {
        diffcomp = false;
    }
    public int compare(Object obj, Object obj1) {
        BaseDataCobwebClusterDescriptorPoint o1 = (BaseDataCobwebClusterDescriptorPoint) obj;
        BaseDataCobwebClusterDescriptorPoint o2 = (BaseDataCobwebClusterDescriptorPoint) obj1;
        int result = 0;
        if(diffcomp) {
            if(o1.differenceValue < o2.differenceValue)
                result = 1;
            else if(o1.differenceValue > o2.differenceValue)
                result = -1;
            else {
            if(o1.descriptorValue < o2.descriptorValue)
                result = 1;
            else if(o1.descriptorValue > o2.descriptorValue)
                result = -1;
            }
        } else {
            if(o1.descriptorValue < o2.descriptorValue)
                result = 1;
            else if(o1.descriptorValue > o2.descriptorValue)
                result = -1;
        }
        return result;
    }    
}
