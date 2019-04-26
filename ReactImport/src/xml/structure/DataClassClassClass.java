/*
 * DataClassClassClass.java
 *
 * Created on May 11, 2001, 2:41 PM
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
public class DataClassClassClass extends blurock.coreobjects.DataObjectClass {

    /** Creates new DataClassClassClass */
    public DataClassClassClass() {
        super(4,"ClassClass","Class Object Description");
    }

    public BaseDataObject BaseDataObjectExample() {
        return new BaseDataClassClass(Identification,Name,Type,null,true);
    }
    
    public DataObjectClass Clone() {
        DataClassClassClass cls = new DataClassClassClass();
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
