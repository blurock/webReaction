/*
 * BaseDataContinuous.java
 *
 * Created on March 2, 2001, 8:47 PM
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
public class BaseDataContinuous extends blurock.logic.base.BaseDataLogical {

    /** Creates new BaseDataContinuous */
    public BaseDataContinuous() {
    }

    public void Read(RWManager io) throws IOException {
        super.Read(io);
        Value = io.readDouble();
    }
    
    public void Write(RWManager io) throws IOException {
        super.Write(io);
        io.printLine(String.valueOf(Value));
    }
    
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man,DataObjectClass cls) {
        return new DBaseDataContinuous(man,this,cls);
    }
    
}
