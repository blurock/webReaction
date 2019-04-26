/*
 * DataGeneticDistributionClass.java
 *
 * Created on March 1, 2001, 11:21 PM
 */

package blurock.pop.genetic;
import blurock.core.*;
import blurock.coreobjects.*;
import blurock.opandalgs.ops.BaseDataOperation;
import java.io.IOException;
import javax.swing.*;
import blurock.core.RWManager;
import blurock.utilities.TopObjectPanel;

/**
 *
 * @author  reaction
 * @version 
 */
public class DataGeneticDistributionClass extends blurock.pop.base.DataGeneticObjectClass {

    /** Creates new DataGeneticDistributionClass */
    public DataGeneticDistributionClass() {
    }
    public DataGeneticDistributionClass(int id, String type, String descr) {
        super(id,type,descr);
        SubClass = "GeneticObject";
    }

    public BaseDataObject BaseDataObjectExample() {
        BaseDataObject obj = (BaseDataObject) new BaseDataGeneticDistribution();
        obj.Type = Name;
        return obj;
    }
    public void CopyClone(DataObjectClass ocls) {
        super.CopyClone(ocls);
    }        
    public DataObjectClass Clone() {
        DataGeneticDistributionClass cls = 
                new DataGeneticDistributionClass(Identification,Name,Description);
        cls.CopyClone(this);
       return cls;
    }
    
    public void Read(RWManager io) throws IOException {
        super.Read(io);
    }
    
    public void Write(RWManager io) throws IOException {
        super.Write(io);
    }
    
}
