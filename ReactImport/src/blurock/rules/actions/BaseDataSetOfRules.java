/*
 * BaseDataSetOfRules.java
 *
 * Created on February 28, 2001, 11:41 AM
 */

package blurock.rules.actions;
import blurock.opandalgs.parameterized.*;
import blurock.coreobjects.*;
import blurock.core.*;
import javax.swing.*;
import java.io.IOException;

/**
 *
 * @author  reaction
 * @version 
 */
public class BaseDataSetOfRules extends blurock.opandalgs.parameterized.BaseDataParameterSet {

    /** Creates new BaseDataSetOfRules */
    public BaseDataSetOfRules() {
        super(false);
    }

    public void Read(RWManager io) throws IOException {
        io.checkToken("SetOfRules:");
        super.Read(io);
    }
    
    public void Write(RWManager io) throws IOException {
        super.Write(io);
    }
    
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man,DataObjectClass cls) {
        return new DBaseDataSetOfRules(man,this,cls);
    }
    
}
