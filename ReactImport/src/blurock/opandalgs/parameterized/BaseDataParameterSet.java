/*
 * BaseDataParameterSet.java
 *
 * Created on February 27, 2001, 6:27 PM
 */

package blurock.opandalgs.parameterized;
import blurock.coreobjects.*;
import blurock.core.*;
import javax.swing.*;
import java.io.IOException;

/**
 *
 * @author  reaction
 * @version 
 */
public class BaseDataParameterSet extends blurock.coreobjects.BaseDataSetOfObjects {
    public boolean useKeyWord;
    /** Creates new BaseDataParameterSet */
    public BaseDataParameterSet() {
        useKeyWord = true;
    }

    public BaseDataParameterSet(boolean useit) {
        useKeyWord = useit;
    }

    public void Read(RWManager io) throws IOException {
        if(useKeyWord)
            io.checkToken("ParameterSet:");
        ClassNamePairs ps = new ClassNamePairs();
        ps.Read(io);
        super.Read(io,ps);
    }
    
    public void Write(RWManager io) throws IOException {
        if(useKeyWord)
            io.printLine("ParameterSet:");
        super.Write(io);
    }
    
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man,DataObjectClass cls) {
        return new DBaseDataParameterSet(man,this,cls);
    }
    
}
