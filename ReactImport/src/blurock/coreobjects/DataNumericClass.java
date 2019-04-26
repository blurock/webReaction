/*
 * DataNumericClass.java
 *
 * Created on February 27, 2001, 4:10 PM
 */

package blurock.coreobjects;
import blurock.core.*;
import java.io.IOException;

/**
 *
 * @author  reaction
 * @version 
 */
public class DataNumericClass extends blurock.coreobjects.DataObjectClass {

    /** Creates new DataNumericClass */
    public DataNumericClass() {
    }
    public DataNumericClass(int id, String type, String descr) {
        super(id,type,descr);
        SubClass = "Object";
    }
    public DataObjectClass Clone() {
        DataNumericClass cls = new DataNumericClass(Identification,Name,Description);
        cls.derivedClass = derivedClass;
        cls.SubClass = SubClass;
       return cls;
    }

    public BaseDataObject BaseDataObjectExample() {
        BaseDataObject obj = (BaseDataObject) new BaseDataNumeric();
        obj.Type = Name;
        return obj;
    }
    
    public void Read(RWManager io) throws IOException {
    }
    
    public void Write(RWManager io) throws IOException {
    }
    
}
