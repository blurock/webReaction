/*
 * DataParameterSetClass.java
 *
 * Created on February 27, 2001, 6:29 PM
 */

package blurock.opandalgs.parameterized;
import blurock.coreobjects.*;
import blurock.core.*;
import javax.swing.*;
import java.io.IOException;

/**
 *
 * @author  reaction
 * @version 
 */
public class DataParameterSetClass extends blurock.coreobjects.DataSetOfObjectsClass {

    /** Creates new DataParameterSetClass */
    public DataParameterSetClass() {
    }
    public DataParameterSetClass(int id, String type, String descr) {
        super(id,type,descr);
        SubClass = "SetOfObjects";
    }
    public DataObjectClass Clone() {
        DataParameterSetClass cls = new DataParameterSetClass(Identification,Name,Description);
        cls.derivedClass = derivedClass;
        cls.SubClass = SubClass;
       return cls;
    }

    public BaseDataObject BaseDataObjectExample() {
        BaseDataObject obj = (BaseDataObject) new BaseDataParameterSet();
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
