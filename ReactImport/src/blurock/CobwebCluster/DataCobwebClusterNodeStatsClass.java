/*
 * DataCobwebClusterNodeStatsClass.java
 *
 * Created on October 13, 2003, 6:42 PM
 */

package blurock.CobwebCluster;
import java.util.*;
import blurock.core.*;
import blurock.coreobjects.*;
import java.io.IOException;

/**
 *
 * @author  reaction
 */
public class DataCobwebClusterNodeStatsClass extends DataObjectClass {
    
    public DataCobwebClusterDescriptorPointClass descriptorPoinClass;
    /** Creates a new instance of DataCobwebClusterNodeStatsClass */
    public DataCobwebClusterNodeStatsClass(int id, String type, String descr) {
       super(id,type,descr);
        SubClass = "Object";
        descriptorPoinClass = new DataCobwebClusterDescriptorPointClass(4033,"","");
    }
    public DataObjectClass Clone() {
        DataCobwebClusterNodeStatsClass cls = new DataCobwebClusterNodeStatsClass(Identification,Name,Description);
        cls.derivedClass = derivedClass;
        cls.SubClass = SubClass;
       return cls;
    }

    public BaseDataObject BaseDataObjectExample() {
        BaseDataObject obj = (BaseDataObject) new BaseDataCobwebClusterNodeStats();
        obj.Type = Name;
        return obj;
    }
    public void Read(RWManager io) throws IOException {
    }
    
    public void Write(RWManager io) throws IOException {
    }

}
