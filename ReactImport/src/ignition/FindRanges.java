/*
 * FindRanges.java
 *
 * Created on June 30, 2004, 10:19 PM
 */

package ignition;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author  reaction
 */
public class FindRanges {
    
    
    
    /** Creates a new instance of FindRanges */
    public FindRanges() {
    }

    public static void execute(String filename, String parameter, double boundary) throws IOException {
           XMatrix mat = new XMatrix(new File(filename));
           
           int indexH2O2 = mat.getIndex(parameter);
           Condition cond1 = new Condition(indexH2O2,boundary,true,false);
           SetOfConditions conditions = new SetOfConditions();
           conditions.addCondition(cond1);
           
           PointsSatisfyingCondition points = new PointsSatisfyingCondition();
           boolean[] isSatisfied = points.Test(mat,conditions);
           
           double reference[] = mat.BuildReference(0);
           SetOfRanges ranges = new SetOfRanges();
           ranges.PointRange(isSatisfied,reference);
           
           ranges.printBoundaries(System.out);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            if(args.length > 0) {
               String parameter = args[1];
               String boundaryS = args[2];
               double boundary = Double.parseDouble(boundaryS);
                execute(args[0],parameter,boundary);
            } else {
                System.err.println(" Expecting one argument:  file name with matrix");
            }
        } catch(IOException ex) {
            System.err.println(ex);
        }
    }
    
}
