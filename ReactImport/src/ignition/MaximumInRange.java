/*
 * MaximumInRange.java
 *
 * Created on July 14, 2004, 3:10 PM
 */

package ignition;
import java.util.Comparator;
import ignition.XMatrix;
/**
 *
 * @author  reaction
 */
public class MaximumInRange implements Comparable {
    
    //private ignition.XMatrix Matrix;
    
    public String rangeParameterName;
    public String valueName;
    private int rangeParameterIndex = 0;
    private int ValueIndex = 0;
    
    private double RangeBegin;
    
    private double RangeEnd;
    
    
    public double Maximum;
    public int    MaximumIndex;
    public int    NumberInRange;
    
    /** Creates a new instance of MaximumInRange */
    public MaximumInRange(String rname, String vname, double b, double e) {
        //Matrix = mat;
        rangeParameterName = rname;
        valueName = vname;
        
        
        RangeBegin = b;
        RangeEnd = e;
    }
    
    int findMaximum(XMatrix Matrix) {
        rangeParameterIndex = Matrix.getIndex(rangeParameterName);
        ValueIndex = Matrix.getIndex(valueName);
        double[] parameters = Matrix.BuildReference(rangeParameterIndex);
        Maximum = Matrix.MatrixValues[0][ValueIndex];
        NumberInRange = 0;
        for(int i=1;i<parameters.length;i++) {
            if(parameters[i] >= RangeBegin && parameters[i] <= RangeEnd) {
                NumberInRange++;
                if(Matrix.MatrixValues[i][ValueIndex] > Maximum) {
                    Maximum = Matrix.MatrixValues[i][ValueIndex];
                    MaximumIndex = i;
                }
            }
        }
        return MaximumIndex;
    }
    
   
    public int compareTo(Object obj) {
        MaximumInRange r = (MaximumInRange) obj;
        
        int ans = 0;
        if(r.Maximum < Maximum) 
            ans = 1;
        else if(r.Maximum > Maximum)
            ans = -1;
        return ans;
   }
    
}
