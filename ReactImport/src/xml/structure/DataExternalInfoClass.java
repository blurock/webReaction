/*
 * DataExternalInfoClass.java
 *
 * Created on June 26, 2001, 10:29 AM
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
public class DataExternalInfoClass extends blurock.coreobjects.DataObjectClass {

    /** Creates new DataExternalInfoClass */
    public DataExternalInfoClass() {
        super(3,"ExternalInfo","External Information for Module (Dependencies)");
    }
    public DataExternalInfoClass(int id, String name, String descr) {
        super(id,name,descr);
    }
    public BaseDataObject BaseDataObjectExample() {
        return new BaseDataExternalInfo();
    }
    
    public DataObjectClass Clone() {
        DataExternalInfoClass cls = new DataExternalInfoClass(Identification,Name,Description);
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
