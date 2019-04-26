/*
 * DataOperationClass.java
 *
 * Created on February 27, 2001, 6:11 PM
 */

package blurock.opandalgs.ops;
import blurock.coreobjects.*;
import blurock.core.*;
import java.io.IOException;

/**
 *
 * @author  reaction
 * @version 
 */
public class DataOperationClass extends blurock.coreobjects.DataObjectClass {

    /** Creates new DataOperationClass */
    public DataOperationClass() {
    }
    public DataOperationClass(int id, String type, String descr) {
        super(id,type,descr);
        SubClass = "Object";
    }
    public void CopyClone(DataObjectClass cls) {
        super.CopyClone(cls);
    }
    public DataObjectClass Clone() {
        DataOperationClass cls = new DataOperationClass(Identification,Name,Description);
        cls.CopyClone(this);
       return cls;
    }

    public BaseDataObject BaseDataObjectExample() {
        BaseDataObject obj = (BaseDataObject) new BaseDataOperation();
        obj.Type = Name;
        return obj;
    }
    
    public void Read(RWManager io) throws IOException {
        super.Read(io);
    }
    
    public void Write(RWManager io) throws IOException {
        super.Write(io);
    }
    
}
