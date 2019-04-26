/*
 * PointRange.java
 *
 * Created on June 30, 2004, 3:27 PM
 */

package ignition;
import java.io.PrintStream;
/**
 *
 * @author  reaction
 */
public class PointRange {
    
    public double LowerBoundary;
    
    public double UpperBoundary;
    
    public int NumberOfPoints;
    
    public boolean Condition;
    
    /** Creates a new instance of PointRange */
    public PointRange() {
    }
    public PointRange(PointRange p) {
        LowerBoundary = p.LowerBoundary;
        UpperBoundary = p.UpperBoundary;
        NumberOfPoints = p.NumberOfPoints;
    }
    public void print(PrintStream io) {
        io.println("[" + LowerBoundary + "," + UpperBoundary  );
    } 
}
