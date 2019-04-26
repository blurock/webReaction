/*
 * BaseDataLogical.java
 *
 * Created on March 2, 2001, 8:43 PM
 */

package blurock.logic.base;
import blurock.coreobjects.*;
import blurock.core.*;
import java.io.IOException;

/**
 *
 * @author  reaction
 * @version 
 */
public class BaseDataLogical extends blurock.coreobjects.BaseDataObject {
    public double Value;
    /** Creates new BaseDataLogical */
    public BaseDataLogical() {
    }

    public void Read(RWManager io) throws IOException {
        super.Read(io);
    }
    
    public void Write(RWManager io) throws IOException {
        super.Write(io);
    }
    
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man,DataObjectClass cls) {
        return new DBaseDataLogical(man,this,cls);
    }
    
}
