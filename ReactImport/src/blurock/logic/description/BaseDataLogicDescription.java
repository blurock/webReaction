/*
 * BaseDataLogicDescription.java
 *
 * Created on March 3, 2001, 6:09 PM
 */

package blurock.logic.description;
import blurock.core.*;
import blurock.coreobjects.*;
import blurock.opandalgs.parameterized.BaseDataParameterizedFunction;
import java.io.IOException;
import blurock.core.RWManager;

/**
 *
 * @author  reaction
 * @version 
 */
public class BaseDataLogicDescription extends blurock.coreobjects.BaseDataSetOfObjects {

    /** Creates new BaseDataLogicDescription */
    public BaseDataLogicDescription() {
    }

    public void Read(RWManager io) throws IOException {
        super.Read(io);
    }
    
    public void Write(RWManager io) throws IOException {
        super.Write(io);
    }
    
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man,DataObjectClass cls) {
        DBaseDataLogicDescription dobj = 
            new DBaseDataLogicDescription(man,this,cls);
        return dobj;
    }
    
}
