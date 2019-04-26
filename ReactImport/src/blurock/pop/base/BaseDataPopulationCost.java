/*
 * BaseDataPopulationCost.java
 *
 * Created on March 1, 2001, 11:08 PM
 */

package blurock.pop.base;
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
public class BaseDataPopulationCost extends blurock.opt.base.BaseDataOptimizeCostFunction {
    public BaseDataParameterizedFunction Expression = null;

    /** Creates new BaseDataPopulationCost */
    public BaseDataPopulationCost() {
    }

    public void Read(RWManager io) throws IOException {
        System.out.println("Read in (super) OptimizeCostFunction");
        super.Read(io);
        System.out.println("Done: Read in (super) OptimizeCostFunction");
        try {
            DataPopulationCostClass cls = (DataPopulationCostClass) 
                           io.dataClasses.findClass(Type);
            Expression = (BaseDataParameterizedFunction) cls.ExpressionClass.BaseDataObjectExample();
            Expression.Name = "Cost Function Expression";
            System.out.println("Read in Cost Expression of Type: " + cls.ExpressionClass.Name);
            Expression.Read(io);
            System.out.println("Done: Read in Cost Expression");
        } catch (NullPointerException nexp) {
            throw new IOException("Population Class not fully defined: '" + Type + "'");
        } catch (ObjectNotFoundException f) {
            throw new IOException("Population Class not registered: " + f);
        }
    }
    
    public void Write(RWManager io) throws IOException {
        super.Write(io);
        try {
            Expression.Write(io);
        } catch (NullPointerException nexp) {
            throw new IOException("Population Class not fully defined: '" + Type + "'");
        }
    }
    
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man,DataObjectClass cls) {
        DBaseDataPopulationCost dobj = 
            new DBaseDataPopulationCost(man,this,cls);
        return dobj;
    }
    
}
