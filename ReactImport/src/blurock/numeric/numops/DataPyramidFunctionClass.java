/*
 * DataPyramidFunctionClass.java
 *
 * Created on February 2, 2002, 10:30 AM
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
public class DataPyramidFunctionClass extends DataFuncReal1DClass {

    /** Creates new DataPyramidFunctionClass */
    public DataPyramidFunctionClass() {
    }
    public DataPyramidFunctionClass(int id, String type, String descr) {
        super(id,type,descr);
        SubClass = "Object";
    }

    public BaseDataObject BaseDataObjectExample() {
        BaseDataObject obj = (BaseDataObject) new BaseDataPyramidFunction();
        obj.Type = Name;
        return obj;
    }
    
    public DataObjectClass Clone() {
        DataPyramidFunctionClass cls = new DataPyramidFunctionClass(Identification,Name,Description);
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
