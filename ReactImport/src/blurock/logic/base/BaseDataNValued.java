/*
 * BaseDataNValued.java
 *
 * Created on March 2, 2001, 8:49 PM
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
public class BaseDataNValued extends blurock.logic.base.BaseDataLogical {
    public String stringValue;
    /** Creates new BaseDataNValued */
    public BaseDataNValued() {
    }

    public void Read(RWManager io) throws IOException {
        super.Read(io);
        stringValue = io.readElement();
    }
    
    public void Write(RWManager io) throws IOException {
        super.Write(io);
        io.printLine(stringValue);
    }
    
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man,DataObjectClass cls) {
        return new DBaseDataNValued(man,this,cls);
    }
    
}
