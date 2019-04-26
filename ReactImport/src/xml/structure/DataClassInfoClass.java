/*
 * DataClassInfoClass.java
 *
 * Created on May 13, 2001, 9:26 AM
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
public class DataClassInfoClass extends blurock.coreobjects.DataObjectClass {

    /** Creates new DataClassInfoClass */
    public DataClassInfoClass() {
        super(5,"ClassInfo","Class Flag Information");
    }

    public BaseDataObject BaseDataObjectExample() {
        return new BaseDataClassInfo();
    }
    
    public DataObjectClass Clone() {
        DataClassInfoClass cls = new DataClassInfoClass();
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
