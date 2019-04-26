/*
 * BaseDataNumeric.java
 *
 * Created on February 27, 2001, 4:09 PM
 */

package blurock.coreobjects;
import blurock.core.*;
import java.io.IOException;

/**
 *
 * @author  reaction
 * @version 
 */
public class BaseDataNumeric extends blurock.coreobjects.BaseDataObject {

    /** Creates new BaseDataNumeric */
    public BaseDataNumeric() {
    }

    public void Read(RWManager io) throws IOException {
    }
    
    public void Write(RWManager io) throws IOException {
    }
    
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man,DataObjectClass cls) {
        return super.getDisplayObject(man,cls);
    }
    
}
