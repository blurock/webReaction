/*
 * BaseDataGeneticDistribution.java
 *
 * Created on March 1, 2001, 11:19 PM
 */

package blurock.pop.genetic;
import blurock.core.*;
import blurock.coreobjects.*;
import blurock.opandalgs.ops.BaseDataOperation;
import java.io.IOException;
import blurock.core.RWManager;

/**
 *
 * @author  reaction
 * @version 
 */
public class BaseDataGeneticDistribution extends blurock.pop.base.BaseDataGeneticObject {
    public String DistributionName = "NotSpecified";
    public String ParameterName = "NotSpecified";
    /** Creates new BaseDataGeneticDistribution */
    public BaseDataGeneticDistribution() {
    }

    public void Read(RWManager io) throws IOException {
        super.Read(io);
        ParameterName = io.readElement();
        DistributionName = io.readElement();
    }
    
    public void Write(RWManager io) throws IOException {
        super.Write(io);
        io.printLine(DistributionName + " " + ParameterName);
    }
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man,DataObjectClass cls) {
        DBaseDataGeneticDistribution dobj = 
            new DBaseDataGeneticDistribution(man,this,cls);
        return dobj;
    }
    
}
