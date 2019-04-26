/*
 * CobwebRegionFinder.java
 *
 * Created on November 17, 2003, 2:35 PM
 */

package blurock.CobwebCluster;
import blurock.coreobjects.*;
import blurock.core.*;
import java.io.IOException;
import utilities.ErrorFrame;
import blurock.Consectutive.*;
import javax.swing.JPanel;

/**
 *
 * @author  reaction
 */
public class CobwebRegionFinder {
    static String inputfile;
    static String outputfile;
    
    /** Creates a new instance of CobwebRegionFinder */
    public CobwebRegionFinder() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CobwebRegionFinder finder = new CobwebRegionFinder();
        inputfile = args[0];
        outputfile = args[1];
        
       RegisteredClasses registered = new RegisteredClasses();
       DataConsecutiveSeriesClass cl1 = new DataConsecutiveSeriesClass(4031,"ConsecutiveSeries",
            "A single node consisting of a consecutive series");
       DataConsecutiveSeriesSetClass setclass = new DataConsecutiveSeriesSetClass(4032,"ConsecutiveSeriesSet",
            "Consecutive Series  Set");
        setclass.nodeClass = cl1;
        registered.AddClass(cl1);
        ObjectDisplayManager man = new ObjectDisplayManager(registered);

        BaseDataConsecutiveSeriesSet seriesSet = new BaseDataConsecutiveSeriesSet();
        seriesSet.Name = "ConsecutiveSeriesSet";

        RWManager io = new RWManager(registered);
        try {
            io.openInputFile(inputfile);
            io.openOutputFile(outputfile);
            seriesSet.Read(io);
            seriesSet.Write(io);

            seriesSet.findLevelRegions();
            String out = seriesSet.LevelsToString();
        
            io.printLine(out);

            io.closeInputFile();
            io.closeOutputFile();
        } catch(IOException exp) {
            ErrorFrame fr = new ErrorFrame("Problem with Reading File: \n" + exp.toString());
            fr.show();
        }
        
        
    }
}
