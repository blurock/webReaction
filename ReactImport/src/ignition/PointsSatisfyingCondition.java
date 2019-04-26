/*
 * PointsSatisfyingCondition.java
 *
 * Created on June 29, 2004, 12:59 AM
 */

package ignition;

/**
 *
 * @author  reaction
 */
public class PointsSatisfyingCondition {
    
    boolean ans[];
    /** Creates a new instance of PointsSatisfyingCondition */
    public PointsSatisfyingCondition() {
    }
    public boolean[] Test(XMatrix x,SetOfConditions conditions) {
        int numberOfPoints = x.NumberOfPoints;
        boolean[] ans = new boolean[numberOfPoints];
        for(int i=0;i<numberOfPoints;i++) {
            ans[i] = conditions.Test(x.MatrixValues[i]);
        }
        return ans;
    }
}
