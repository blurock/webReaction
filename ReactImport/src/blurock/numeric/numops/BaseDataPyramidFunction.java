/*
 * BaseDataFuncReal1D.java
 *
 * Created on February 2, 2002, 10:10 AM
 */

package blurock.numeric.numops;
import blurock.coreobjects.*;
import blurock.core.*;
import java.io.IOException;
import blurock.numeric.numops.*;
/**
 *
 * @author  reaction
 * @version 
 */
public class BaseDataPyramidFunction extends blurock.numeric.numops.BaseDataFuncReal1D {
  public double InitialHeight;
  public double PointHeight;
  public double FinalHeight;
  public double Xbegin;
  public double Xpoint;
  public double Xend;

    /** Creates new BaseDataFuncReal1D */
    public BaseDataPyramidFunction() {
    }

    public void Read(RWManagerBase io) throws IOException {
        super.Read(io);
        
        io.checkToken("PyramidODReal");
        
        InitialHeight = io.readDouble();
        PointHeight   = io.readDouble();
        FinalHeight   = io.readDouble();
        Xbegin        = io.readDouble();
        Xpoint        = io.readDouble();
        Xend          = io.readDouble();
    }
    
    public void Write(RWManagerBase io) throws IOException {
    }
    
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man,DataObjectClass cls) {
        return new DBaseDataPyramidFunction(man,this,cls);
    }
    
}
