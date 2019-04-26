/*
 * DataPopulationCostClass.java
 *
 * Created on March 1, 2001, 11:12 PM
 */

package blurock.pop.base;
import blurock.core.RWManager;
import blurock.coreobjects.*;
import blurock.opandalgs.ops.*;
import java.io.IOException;
import blurock.opandalgs.parameterized.*;

/**
 *
 * @author  reaction
 * @version 
 */
public class DataPopulationCostClass extends blurock.opt.base.DataOptimizeCostFunctionClass {
    public DataParameterizedFunctionClass ExpressionClass = null;

    /** Creates new DataPopulationCostClass */
    public DataPopulationCostClass() {
    }
    public DataPopulationCostClass(int id, String type, String descr) {
        super(id,type,descr);
        SubClass = "OptimizeCostFunction";
    }

    public BaseDataObject BaseDataObjectExample() {
        BaseDataObject obj = (BaseDataObject) new BaseDataPopulationCost();
        obj.Type = Name;
        return obj;
     }
    
    public void CopyClone(DataObjectClass ocls) {
        super.CopyClone(ocls);
        DataPopulationCostClass cls = (DataPopulationCostClass) ocls;
        try {
            cls.ExpressionClass = (DataParameterizedFunctionClass) ExpressionClass.Clone();
        } catch(NullPointerException exp) {
            cls.ExpressionClass = null;
        }
    }
    public DataObjectClass Clone() {
        DataPopulationCostClass cls = new DataPopulationCostClass(Identification,Name,Description);
        cls.CopyClone(this);
       return cls;
    }
    
    public void Read(RWManager io) throws IOException {
        super.Read(io);
        ExpressionClass = (DataParameterizedFunctionClass) io.getClassFromNextElement();
    }
    
    public void Write(RWManager io) throws IOException {
        super.Write(io);
        try {
            io.printLine(ExpressionClass.Name);
        } catch (NullPointerException nexp) {
            throw new IOException("Optimization cost class not fully defined");
        }
    }
    
}
