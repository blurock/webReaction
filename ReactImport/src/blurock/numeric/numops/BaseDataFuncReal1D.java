/*
 * BaseDataFuncReal1D.java
 *
 * Created on February 2, 2002, 10:10 AM
 */

package blurock.numeric.numops;
import blurock.coreobjects.*;
import blurock.core.*;
import java.io.IOException;

/**
 *
 * @author  reaction
 * @version 
 */
public class BaseDataFuncReal1D extends blurock.coreobjects.BaseDataObject {
  double LowerBound;
  double UpperBound;
  boolean IncludeLower;
  boolean IncludeUpper;

    /** Creates new BaseDataFuncReal1D */
    public BaseDataFuncReal1D() {
    }

    public void Read(RWManagerBase io) throws IOException {
        io.checkToken("ODReal");
        LowerBound = io.readDouble();
        IncludeLower = io.readBoolean();
        UpperBound = io.readDouble();
        IncludeLower = io.readBoolean();
    }
    
    public void Write(RWManagerBase io) throws IOException {
    }
    
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man,DataObjectClass cls) {
        return new DBaseDataObject(man,this,cls);
    }
    
}
