/*
 * DataSetOfLogicDescriptionsClass.java
 *
 * Created on March 3, 2001, 6:16 PM
 */

package blurock.logic.description;
import blurock.core.RWManager;
import blurock.coreobjects.*;
import blurock.opandalgs.ops.*;
import java.io.IOException;

/**
 *
 * @author  reaction
 * @version 
 */
public class DataSetOfLogicDescriptionsClass extends blurock.coreobjects.DataSetOfObjectsClass {

    /** Creates new DataSetOfLogicDescriptionsClass */
    public DataSetOfLogicDescriptionsClass() {
    }
    public DataSetOfLogicDescriptionsClass(int id, String type, String descr) {
        super(id,type,descr);
        SubClass = "GeneticObject";
    }

    public BaseDataObject BaseDataObjectExample() {
        BaseDataObject obj = (BaseDataObject) new BaseDataSetOfLogicDescriptions();
        obj.Type = Name;
        return obj;
    }
    
    public DataObjectClass Clone() {
        DataSetOfLogicDescriptionsClass cls = 
                new DataSetOfLogicDescriptionsClass(Identification,Name,Description);
        cls.CopyClone(this);
       return cls;
    }
    
    public void CopyClone(DataObjectClass ocls) {
        super.CopyClone(ocls);
    }
    
    public void Read(RWManager io) throws IOException {
        super.Read(io);
    }
    
    public void Write(RWManager io) throws IOException {
        super.Write(io);
    }
    
}
