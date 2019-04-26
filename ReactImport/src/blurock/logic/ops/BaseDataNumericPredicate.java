/*
 * BaseDataNumericPredicate.java
 *
 * Created on September 21, 2004, 9:54 AM
 */

package blurock.logic.ops;
import blurock.core.RWManagerBase;
import java.io.IOException;
import blurock.core.ObjectDisplayManager;
import blurock.coreobjects.BaseDataObject;
import blurock.coreobjects.DataObjectClass;
import blurock.coreobjects.DBaseDataObject;
import blurock.numeric.numops.BaseDataFuncReal1D;
import blurock.numeric.numops.DataFuncReal1DClass;
import blurock.logic.base.DataLogicalClass;
import blurock.core.ObjectNotFoundException;
/**
 *
 * @author  reaction
 */
public class BaseDataNumericPredicate extends blurock.opandalgs.ops.BaseDataOperation {
    BaseDataFuncReal1D PredicateFunction = null;
    DataLogicalClass LogicClass = null;
    
    /** Creates a new instance of BaseDataNumericPredicate */
    public BaseDataNumericPredicate() {
    }
    
    public void Read(RWManagerBase io) throws IOException {
        super.Read(io);
        try {
            DataNumericPredicateClass cls = (DataNumericPredicateClass) io.dataClasses.findClass(Type);
            LogicClass = (DataLogicalClass) cls.LogicClass.Clone();
            DataFuncReal1DClass predfuncclass = cls.PredicateFunction;
            PredicateFunction = (BaseDataFuncReal1D) predfuncclass.BaseDataObjectExample();
            PredicateFunction.Read(io);
        } catch(ObjectNotFoundException ex) {
            throw new IOException("Predicate Type: " + Type + " not defined in Predicate Function of Numeric Predicate");
        }
    }
    
    public void Write(RWManagerBase io) throws IOException {
        super.Write(io);
        PredicateFunction.Write(io);
    }
    
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man, DataObjectClass cls) {
        return new DBaseDataNumericPredicate(man,this,cls);
    }
    
    public BaseDataObject Clone() {
        BaseDataObject cls = (BaseDataObject)  new BaseDataNumericPredicate();
        cls.CopyClone(this);
       return cls;         
    }
    
    public void CopyClone(BaseDataObject obj) {
        super.CopyClone(obj);
        PredicateFunction.CopyClone(obj);
        LogicClass.CopyClone(obj);
    }
    
}
