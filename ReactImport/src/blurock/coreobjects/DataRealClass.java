/*
 * DataRealClass.java
 *
 * Created on February 27, 2001, 4:13 PM
 */

package blurock.coreobjects;
import blurock.core.*;
import java.io.IOException;

/**
 *
 * @author  reaction
 * @version 
 */
public class DataRealClass extends blurock.coreobjects.DataNumericClass {

    /** Creates new DataRealClass */
    public DataRealClass() {
    }
    public DataRealClass(int id, String type, String descr) {
        super(id,type,descr);
        SubClass = "Numeric";
    }
    public DataObjectClass Clone() {
        DataRealClass cls = new DataRealClass(Identification,Name,Description);
        cls.derivedClass = derivedClass;
        cls.SubClass = SubClass;
        cls.Type = Type;
       return cls;
    }

    public BaseDataObject BaseDataObjectExample() {
        BaseDataReal obj = new BaseDataReal();
        obj.Type = Type;
        return obj;
    }
    
    public void Read(RWManager io) throws IOException {
        super.Read(io);
    }
    
    public void Write(RWManager io) throws IOException {
        super.Write(io);
    }
    
}
