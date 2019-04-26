/*
 * DataContinuousClass.java
 *
 * Created on March 2, 2001, 8:48 PM
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
public class DataContinuousClass extends blurock.logic.base.DataLogicalClass {

    /** Creates new DataContinuousClass */
    public DataContinuousClass() {
    }
    public DataContinuousClass(int id, String type, String descr) {
        super(id,type,descr);
        SubClass = "Logical";
    }

    public BaseDataObject BaseDataObjectExample() {
        BaseDataObject obj = (BaseDataObject) new BaseDataContinuous();
        obj.Type = Name;
        return obj;
    }
    
    public DataObjectClass Clone() {
        DataContinuousClass cls = new DataContinuousClass(Identification,Name,Description);
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
