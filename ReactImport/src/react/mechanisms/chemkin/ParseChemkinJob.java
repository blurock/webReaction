/*
 * ParseChemkinJob.java
 *
 * Created on February 17, 2004, 9:24 PM
 */

package react.mechanisms.chemkin;
import utilities.*;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author  reaction
 */
public class ParseChemkinJob {
    
    /** Creates a new instance of ParseChemkinJob */
    public ParseChemkinJob() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        File f = new File("Butane-LLNL/nbutane_mech.txt");
        MechanismReadOptions options = new MechanismReadOptions();
        //File f = new File("mech.dat");
        ReadFileToString rf = new ReadFileToString();
        rf.read(f);
        //System.out.println(rf.outputString);
        ReadChemkinMechanism mech = new ReadChemkinMechanism(rf.outputString, options);
        try {
            mech.parse();
        } catch(IOException io) {
            System.out.println(io.toString());
        }
        
    }
}
