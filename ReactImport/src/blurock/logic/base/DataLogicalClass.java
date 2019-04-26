/*
 * DataLogicalClass.java
 *
 * Created on March 2, 2001, 8:45 PM
 */

package blurock.logic.base;
import blurock.coreobjects.*;
import blurock.core.*;
import java.io.IOException;

/**
 *
 * @author  reaction
 * @version 
 */
public class DataLogicalClass extends blurock.coreobjects.DataObjectClass {

    /** Creates new DataLogicalClass */
    public DataLogicalClass() {
    }
    public DataLogicalClass(int id, String type, String descr) {
        super(id,type,descr);
        SubClass = "Object";
    }

    public BaseDataObject BaseDataObjectExample() {
        BaseDataObject obj = (BaseDataObject) new BaseDataLogical();
        obj.Type = Name;
        return obj;
    }
    public DataObjectClass Clone() {
        DataLogicalClass cls = new DataLogicalClass(Identification,Name,Description);
        cls.CopyClone(this);
       return cls;
    }
    
    public void CopyClone(DataObjectClass cls) {
        super.CopyClone(cls);
    }
    
    public void Read(RWManager io) throws IOException {
        super.Read(io);
    }
    
    public void Write(RWManager io) throws IOException {
        super.Write(io);
    }
    
}
