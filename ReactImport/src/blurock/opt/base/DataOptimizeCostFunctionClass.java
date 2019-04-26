/*
 * DataOptimizeCostFunctionClass.java
 *
 * Created on March 1, 2001, 11:05 PM
 */

package blurock.opt.base;
import blurock.core.RWManager;
import blurock.coreobjects.*;
import blurock.opandalgs.ops.*;
import java.io.IOException;

/**
 *
 * @author  reaction
 * @version 
 */
public class DataOptimizeCostFunctionClass extends blurock.opandalgs.ops.DataOperationClass {
    DataSetOfObjectsClass ParameterClass = null;
    
    /** Creates new DataOptimizeCostFunctionClass */
    public DataOptimizeCostFunctionClass() {
    }
    public DataOptimizeCostFunctionClass(int id, String type, String descr) {
        super(id,type,descr);
        SubClass = "Operation";
    }

    public BaseDataObject BaseDataObjectExample() {
        BaseDataObject obj = (BaseDataObject) new BaseDataOptimizeCostFunction();
        obj.Type = Name;
        return obj;
    }
    
    public void CopyClone(DataObjectClass ocls) {
        super.CopyClone(ocls);
        DataOptimizeCostFunctionClass cls = (DataOptimizeCostFunctionClass) ocls;
        try {
            cls.ParameterClass = (DataSetOfObjectsClass) ParameterClass.Clone();
        } catch(NullPointerException exp) {
            cls.ParameterClass = null;
        }
    }
    
    public DataObjectClass Clone() {
        DataOptimizeCostFunctionClass cls = new DataOptimizeCostFunctionClass(Identification,Name,Description);
        cls.CopyClone(this);
       return cls;
    }
    
    public void Read(RWManager io) throws IOException {
        super.Read(io);
        ParameterClass = (DataSetOfObjectsClass) io.getClassFromNextElement();
    }
    
    public void Write(RWManager io) throws IOException {
        super.Write(io);
        try {
            io.printLine(ParameterClass.Name);
        } catch (NullPointerException nexp) {
            throw new IOException("Optimization cost class not fully defined");
        }
    }
    
}
