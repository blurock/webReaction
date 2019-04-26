/*
 * DetermineRegionsFromTest.java
 *
 * Created on January 19, 2004, 11:14 AM
 */

package blurock.CobwebCluster;
import java.io.*;
import blurock.core.RWManager;
import blurock.coreobjects.DataSetOfObjectsClass;
import java.util.StringTokenizer;
import java.lang.Integer;
import java.util.ArrayList;
/**
 *
 * @author  reaction
 */
public class DetermineRegionsFromTest {
    SetOfClusterSets clusterSets;
    /** Creates a new instance of DetermineRegionsFromTest */
    public DetermineRegionsFromTest() {
    }
    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) {
        boolean found = false;
        DetermineRegionsFromTest test = new DetermineRegionsFromTest();
        
       if(args.length != 1) {
            System.out.println("Isolate regions from Test run");
            System.out.println("Input: ");
            System.out.println("       name: Name of file");
            System.out.println("");
            System.out.println("");
            System.out.println("");
       } else {
           
          try {
           System.out.println("");
           found = test.parse(args[0]);
        } catch(IOException io) {
                if(found)
                    System.out.println("Done");
                else
                    System.out.println("Never found Block"+ io);
        }
       }
    }
   boolean parse(String name) throws IOException {
            boolean found = true;
            DataSetOfObjectsClass cls = new DataSetOfObjectsClass();
            clusterSets = new SetOfClusterSets(name,cls);
            clusterSets.findRegions();
            String divs = clusterSets.toString();
            System.out.println(divs);
            return found;
    }
     
}
