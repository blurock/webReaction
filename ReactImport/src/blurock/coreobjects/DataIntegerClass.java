/*
 * DataIntegerClass.java
 *
 * Created on February 27, 2001, 4:12 PM
 */

package blurock.coreobjects;
import blurock.core.*;
import java.io.IOException;

/**
 *
 * @author  reaction
 * @version 
 */
public class DataIntegerClass extends blurock.coreobjects.DataNumericClass {

    /** Creates new DataIntegerClass */
    public DataIntegerClass() {
    }
    public DataIntegerClass(int id, String type, String descr) {
        super(id,type,descr);
        SubClass = "Numeric";
    }
    public DataObjectClass Clone() {
        DataIntegerClass cls = new DataIntegerClass(Identification,Name,Description);
        cls.derivedClass = derivedClass;
        cls.SubClass = SubClass;
       return cls;
    }

    public BaseDataObject BaseDataObjectExample() {
        BaseDataObject obj = (BaseDataObject) new BaseDataInteger();
        obj.Type = Name;
        return obj;
    }
    
    public void Read(RWManager io) throws IOException {
    }
    
    public void Write(RWManager io) throws IOException {
    }
    
}
