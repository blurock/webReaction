/*
 * BaseDataPopulationObject.java
 *
 * Created on March 1, 2001, 9:45 PM
 */

package blurock.pop.base;
import blurock.core.RWManager;
import blurock.coreobjects.*;
import blurock.opandalgs.ops.*;
import java.io.IOException;
import blurock.core.ObjectDisplayManager;

/**
 *
 * @author  reaction
 * @version 
 */
public class BaseDataPopulationObject extends blurock.coreobjects.BaseDataObject {
    public BaseDataObject ValueObject;

    /** Creates new BaseDataPopulationObject */
    public BaseDataPopulationObject() {
    }

    public void Read(RWManager io) throws IOException {
        super.Read(io);
    }
    
    public void Write(RWManager io) throws IOException {
        super.Write(io);
    }
    
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man,DataObjectClass cls) {
        DBaseDataObject dobj = super.getDisplayObject(man,cls);
        return dobj;
    }
    
}
