/*
 * DataClassObjectClass.java
 *
 * Created on May 11, 2001, 2:36 PM
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
public class DataClassObjectClass extends blurock.coreobjects.DataObjectClass {

    /** Creates new DataClassObjectClass */
    public DataClassObjectClass() {
        super(3,"ClassObject","The Object Class Specification");
    }

    public BaseDataObject BaseDataObjectExample() {
        return new BaseDataClassObject(Identification,Name,Type,null,true);
    }
    
    public DataObjectClass Clone() {
        DataClassObjectClass cls = new DataClassObjectClass();
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
