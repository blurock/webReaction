/*
 * BaseDataOptimizeCostFunction.java
 *
 * Created on March 1, 2001, 11:04 PM
 */

package blurock.opt.base;
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
public class BaseDataOptimizeCostFunction extends blurock.opandalgs.ops.BaseDataOperation {
    BaseDataSetOfObjects Parameters = null;

    /** Creates new BaseDataOptimizeCostFunction */
    public BaseDataOptimizeCostFunction() {
    }

    public void Read(RWManager io) throws IOException {
        super.Read(io);
        io.checkToken("CostFunction:");
        try {
            DataOptimizeCostFunctionClass cls = (DataOptimizeCostFunctionClass) 
                           io.dataClasses.findClass(Type);
            Parameters = (BaseDataSetOfObjects) cls.ParameterClass.BaseDataObjectExample();
            Parameters.Name = "Parameters";
            System.out.println("Reading in Parameters");
            Parameters.Read(io);
            System.out.println("Done Reading in Parameters");
        } catch (NullPointerException nexp) {
            throw new IOException("Cost Function Class not fully defined: '" + Type + "'");
        } catch (ObjectNotFoundException f) {
            throw new IOException("Cost Function Class not registered: " + f);
        }
    }
    
    public void Write(RWManager io) throws IOException {
        super.Write(io);
        try {
            Parameters.Write(io);
        } catch (NullPointerException nexp) {
            throw new IOException("Cost Function Class not fully defined: '" + Type + "'");
        }
    }
    
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man,DataObjectClass cls) {
        DBaseDataOptimizeCostFunction dobj = 
            new DBaseDataOptimizeCostFunction(man,this,cls);
        return dobj;
    }
    
}
