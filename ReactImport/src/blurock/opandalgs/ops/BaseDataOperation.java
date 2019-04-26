/*
 * BaseDataOperation.java
 *
 * Created on February 27, 2001, 6:10 PM
 */

package blurock.opandalgs.ops;
import blurock.coreobjects.*;
import blurock.core.*;
import java.io.IOException;

/**
 *
 * @author  reaction
 * @version 
 */
public class BaseDataOperation extends blurock.coreobjects.BaseDataObject {

    /** Creates new BaseDataOperation */
    public BaseDataOperation() {
    }

    public void Read(RWManager io) throws IOException {
    }
    
    public void Write(RWManager io) throws IOException {
    }
    
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man,DataObjectClass cls) {
        return new DBaseDataOperation(man,this,cls);
    }
    
}
