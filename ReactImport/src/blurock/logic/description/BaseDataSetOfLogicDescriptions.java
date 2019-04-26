/*
 * BaseDataSetOfLogicalDescriptions.java
 *
 * Created on March 3, 2001, 6:14 PM
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
public class BaseDataSetOfLogicDescriptions extends blurock.coreobjects.BaseDataSetOfObjects {

    /** Creates new BaseDataSetOfLogicalDescriptions */
    public BaseDataSetOfLogicDescriptions() {
    }

    public void Read(RWManager io) throws IOException {
        super.Read(io);
    }
    
    public void Write(RWManager io) throws IOException {
        super.Write(io);
    }
    
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man,DataObjectClass cls) {
        DBaseDataSetOfLogicDescriptions dobj = 
            new DBaseDataSetOfLogicDescriptions(man,this,cls);
        return dobj;
    }
    
}
