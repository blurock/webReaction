/*
 * DataCobwebClusterClass.java
 *
 * Created on October 13, 2003, 4:51 PM
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
public class DataCobwebClusterClass extends blurock.coreobjects.DataObjectClass {
    
    public DataCobwebClusterNodeStatsClass nodeClass;
    /** Creates a new instance of DataCobwebClusterClass */
    public DataCobwebClusterClass() {
    }
        public DataCobwebClusterClass(int id, String type, String descr) {
        super(id,type,descr);
        SubClass = "Object";
    }
        public DataObjectClass Clone() {
        DataCobwebClusterClass cls = new DataCobwebClusterClass(Identification,Name,Description);
        cls.derivedClass = derivedClass;
        cls.SubClass = SubClass;
       return cls;
    }

    public BaseDataObject BaseDataObjectExample() {
        BaseDataObject obj = (BaseDataObject) new BaseDataCobwebCluster();
        obj.Type = Name;
        return obj;
    }
    public void Read(RWManager io) throws IOException {
    }
    
    public void Write(RWManager io) throws IOException {
    }
    

}
