/*
 * SetOfRanges.java
 *
 * Created on June 30, 2004, 3:29 PM
 */

package ignition;
import java.io.PrintStream;
import java.util.Vector;

/**
 *
 * @author  reaction
 */
public class SetOfRanges {
    
    private Vector RangeSet;
    
    /** Creates a new instance of SetOfRanges */
    public SetOfRanges() {
        RangeSet = new Vector();
    }
    public void PointRange(boolean[] conditions, double[] reference) {
        boolean compvalue = conditions[0];
        PointRange range = new PointRange();
        range.LowerBoundary = reference[0];
        range.NumberOfPoints = 1;
        for(int i = 1;i< conditions.length;i++) {
             if( (compvalue && !conditions[i]) ||
                 (!compvalue && conditions[i])) {
                     range.UpperBoundary = reference[i-1];
                     range.Condition = compvalue;
                     RangeSet.add(range);
                     
                     range = new PointRange();
                     range.LowerBoundary = reference[i];
                     range.NumberOfPoints = 0;
                     
                     compvalue = conditions[i];
            }
            range.NumberOfPoints++;
        }
        range.UpperBoundary = reference[conditions.length - 1];
        range.Condition = compvalue;
        RangeSet.add(range);
    }
    public int size() {
        return RangeSet.size();
    }
    public PointRange elementAt(int i) {
        return (PointRange) RangeSet.elementAt(i);
    }
      public void print(PrintStream io) {
       for(int i=0;i<RangeSet.size();i++) {
           PointRange range = (PointRange) RangeSet.elementAt(i);
           io.println("Range: " + i);
           range.print(io);
        }
   }
    public void printBoundaries(PrintStream io) {
        PointRange range = null;
       for(int i=0;i<RangeSet.size();i++) {
           range = (PointRange) RangeSet.elementAt(i);
           io.print(range.LowerBoundary + "   ");
       }
       io.println(range.UpperBoundary + "   ");
   }
}
