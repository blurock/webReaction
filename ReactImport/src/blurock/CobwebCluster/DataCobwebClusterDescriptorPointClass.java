/*
 * DataCobwebClusterDescriptorPointClass.java
 *
 * Created on October 14, 2003, 12:05 PM
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
public class DataCobwebClusterDescriptorPointClass extends DataObjectClass {
    
    /** Creates a new instance of DataCobwebClusterDescriptorPointClass */
    public DataCobwebClusterDescriptorPointClass(int id, String type, String descr) {
       super(id,type,descr);
        SubClass = "Object";
    }
    public DataObjectClass Clone() {
        DataCobwebClusterDescriptorPointClass cls = new DataCobwebClusterDescriptorPointClass(Identification,Name,Description);
        cls.derivedClass = derivedClass;
        cls.SubClass = SubClass;
       return cls;
    }

    public BaseDataObject BaseDataObjectExample() {
        BaseDataObject obj = (BaseDataObject) new BaseDataCobwebClusterDescriptorPoint();
        obj.Type = Name;
        return obj;
    }
    public void Read(RWManager io) throws IOException {
    }
    
    public void Write(RWManager io) throws IOException {
    }
 
}
