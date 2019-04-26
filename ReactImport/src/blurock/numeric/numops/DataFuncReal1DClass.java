/*
 * DataFuncReal1DClass.java
 *
 * Created on February 2, 2002, 10:12 AM
 */

package blurock.numeric.numops;
import blurock.coreobjects.*;
import blurock.core.*;
import java.io.IOException;
import blurock.numeric.numops.*;

/**
 *
 * @author  reaction
 * @version 
 */
public class DataFuncReal1DClass extends DataObjectClass {

    /** Creates new DataFuncReal1DClass */
    public DataFuncReal1DClass() {
    }
    public DataFuncReal1DClass(int id, String type, String descr) {
        super(id,type,descr);
        SubClass = "Object";
    }

    public BaseDataObject BaseDataObjectExample() {
        BaseDataObject obj = (BaseDataObject) new BaseDataFuncReal1D();
        obj.Type = Name;
        return obj;
    }
    
    public DataObjectClass Clone() {
        DataFuncReal1DClass cls = new DataFuncReal1DClass(Identification,Name,Description);
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
