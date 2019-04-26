/*
 * BaseDataGeneticObject.java
 *
 * Created on March 1, 2001, 5:56 PM
 */

package blurock.pop.base;
import blurock.core.*;
import blurock.coreobjects.*;
import blurock.opandalgs.ops.BaseDataOperation;
import java.io.IOException;
import blurock.core.RWManager;

/**
 *
 * @author  reaction
 * @version 
 */
public class BaseDataGeneticObject extends blurock.opandalgs.ops.BaseDataOperation {

    
    /** Creates new BaseDataGeneticObject */
    public BaseDataGeneticObject() {
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
