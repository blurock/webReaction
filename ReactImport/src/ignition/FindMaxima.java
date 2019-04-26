/*
 * FindMaxima.java
 *
 * Created on July 14, 2004, 4:03 PM
 */

package ignition;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author  reaction
 */
public class FindMaxima {
    
    /** Creates a new instance of FindMaxima */
    public FindMaxima(String filename, String rangeName, double lower, double upper)  throws IOException {
           File fileF = new File(filename);
           
           XMatrix mat = new XMatrix(fileF);
           PointRange rng = new PointRange();
           rng.LowerBoundary = lower;
           rng.UpperBoundary = upper;
           rng.NumberOfPoints = 0;
           
           SetOfMaximaInRange MaximaSet = new SetOfMaximaInRange(mat,rangeName,rng);
           
           MaximaSet.maximaOut(System.out);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            if(args.length > 0) {
               String filename = args[0];
               String rangeName = args[1];
               String lowerS = args[2];
               String upperS = args[3];
               
              double lower = Double.parseDouble(lowerS);
              double upper = Double.parseDouble(upperS);
             
              FindMaxima Maxima = new FindMaxima(filename,rangeName,lower,upper);
            } else {
                System.err.println(" Expecting one argument:  file name with matrix");
            }
        } catch(IOException ex) {
            System.err.println(ex);
            
        }
        
    }
    
}
