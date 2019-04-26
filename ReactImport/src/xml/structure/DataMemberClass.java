/*
 * DataMemberClass.java
 *
 * Created on May 10, 2001, 11:21 AM
 */

package xml.structure;
import java.io.IOException;
import blurock.coreobjects.*;
import blurock.core.*;

/**
 *
 * @author  reaction
 * @version 
 */
public class DataMemberClass extends blurock.coreobjects.DataObjectClass {

    /** Creates new DataMemberClass */
    public DataMemberClass() {
        super(2,"Member","Class Member Description Object");
    }
    public DataMemberClass(int id, String name, String descr) {
        super(id,name,descr);
    }

    public BaseDataObject BaseDataObjectExample() {
        return new BaseDataObject();
    }
    
    public DataObjectClass Clone() {
        DataMemberClass cls = new DataMemberClass(Identification,Name,Description);
        cls.CopyClone(this);
       return cls; 
    }
    
    public void CopyClone(DataObjectClass cls) {
        super.CopyClone(cls);
    }
    
    public void Read(RWManager io) throws IOException {
    }
    
    public void Write(RWManager io) throws IOException {
    }
    
}
