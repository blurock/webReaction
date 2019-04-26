/*
 * SetOfMaximaInRange.java
 *
 * Created on July 14, 2004, 3:44 PM
 */

package ignition;
import java.io.PrintStream;
import java.util.Hashtable;
/**
 *
 * @author  reaction
 */
public class SetOfMaximaInRange extends ignition.PointRange {
    
    public Hashtable Maxima = null;
    public String rangeParameterName;
    public int icount = 0;
    /** Creates a new instance of SetOfMaximaInRange */
    public SetOfMaximaInRange(XMatrix mat, String rname, PointRange pointrange) {
        super(pointrange);
        rangeParameterName = rname;
        Maxima = new Hashtable();
        setUpMaxima(mat);
    }
    public SetOfMaximaInRange(SetOfMaximaInRange maxcpy) {
        super(maxcpy);
        rangeParameterName = maxcpy.rangeParameterName;
        Object[] maxima = maxcpy.Maxima.values().toArray();
        maxcpy.Maxima.values();
        Maxima = new Hashtable();
        for(int i=0;i<maxima.length;i++) {
            MaximumInRange max = (MaximumInRange) maxima[i];
            Maxima.put(max.valueName,max);
        }
         icount = maxcpy.icount;
    }
    public void merge(SetOfMaximaInRange max1) throws Exception {
        if(max1.Maxima.size() != Maxima.size()) 
            throw new Exception("Parameter mismatch: excpected  " + Maxima.size() + "  got " + max1.Maxima.size());
        Object[] maxima = Maxima.values().toArray();
        for(int i=0;i<maxima.length;i++) {
            MaximumInRange max = (MaximumInRange) maxima[i];
            MaximumInRange mermax = (MaximumInRange) max1.Maxima.get(max.valueName);
                double x2 = max.Maximum;
                double x1 = mermax.Maximum;
                if(x1 > x2) {
                    Maxima.put(max.valueName,mermax);
                }
       }
       LowerBoundary = 0.0;
       UpperBoundary = 0.0;
       Condition = true;
    }
    private void setUpMaxima(XMatrix Matrix) {
        icount = 0;
        for(int i=0;i<Matrix.NumberOfParameters;i++) {            
            if( rangeParameterName.compareTo(Matrix.ParameterNames[i]) != 0 ) {
                 MaximumInRange max = new MaximumInRange(rangeParameterName,Matrix.ParameterNames[i],
                    LowerBoundary, UpperBoundary);
                 max.findMaximum(Matrix);
                 NumberOfPoints = max.NumberInRange;
                 Maxima.put(Matrix.ParameterNames[i],max);
                 icount++;
            }
        }
        
    }
    public int numberInRange() {
        return icount;
    }
    public int numberAboveLimit(double limit) {
        int count = 0;
        Object[] maxima = Maxima.values().toArray();
        for(int i=0;i<maxima.length;i++) {
            MaximumInRange max = (MaximumInRange) maxima[i];
            System.out.println("i=" + i + ":" + max.valueName + "\t  max.Maximum=" + max.Maximum + "   limit= " + limit);
            if(max.Maximum >= limit) {
                count++;
            } else
                System.out.println("true " + count);
        }
        System.out.println( "Total Count " + count);
        return count;
    }
    public void maximaOut(PrintStream io) {
        Object[] maxima = Maxima.values().toArray();
        for(int i=0;i<maxima.length;i++) {
            MaximumInRange max = (MaximumInRange) maxima[i];
            io.println(max.valueName + "  " + max.Maximum);
        }
    }
}
