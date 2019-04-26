/*
 * ClusterRegionAlpgorithmParameters.java
 *
 * Created on August 5, 2004, 4:22 PM
 */

package ignition;
import java.io.File;
import javax.swing.JFileChooser;
import utilities.FileFrame;
import utilities.*;
import blurock.utilities.*;
import react.common.TopReactionMenu;
import java.util.Hashtable;
/**
 *
 * @author  reaction
 */
public class ClusterRegionAlpgorithmParameters {
    
    public XandGNChooser filter = new XandGNChooser();
    public File defaultDir = new File(".");
    public File outputDir = new File(".");
    public FileFrame chooser = null;
    
    public Object[] XfileList = null;
    public Object[] GNfileList = null;

    
    RunCommand run;
    String runName;
    
    public Hashtable partitions = new Hashtable();
    public TopReactionMenu Top;

    public String selectedAttributes[] = null;

    /** Creates a new instance of ClusterRegionAlpgorithmParameters */
    public ClusterRegionAlpgorithmParameters(TopReactionMenu top) {
        Top = top;
        run = new RunCommand(Top);
    }
    public void initializeClusteringRun(String historyName) {
        Top.history.setToFlame();
        Top.history.setHistoryName(historyName);
        runName = Top.SystemInfo.flameRunExecutable.getValue();
        Top.InitializeSystem.basicInitialization();
        Top.InitializeSystem.algorithmsInitialization();
        Top.InitializeSystem.logicInitialization();
        //Top.InitializeSystem.expressionInitialization();
  }

}
