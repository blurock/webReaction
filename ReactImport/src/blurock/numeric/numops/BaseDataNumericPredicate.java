/*
 * BaseDataPredicate.java
 *
 * Created on February 1, 2002, 10:45 PM
 */

package blurock.numeric.numops;
import blurock.coreobjects.DBaseDataObject;
import blurock.coreobjects.DataObjectClass;
import blurock.core.ObjectDisplayManager;
import blurock.core.RWManagerBase;
import blurock.opandalgs.ops.BaseDataOperation;
import blurock.opandalgs.ops.DBaseDataOperation;
import java.io.IOException;
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
 * @version 
 */
public class BaseDataNumericPredicate extends blurock.opandalgs.ops.BaseDataOperation {
  public BaseDataFuncReal1D PredicateFunction;
  DataLogicalClass LogicClass;

    /** Creates new BaseDataPredicate */
    public BaseDataNumericPredicate() {
    }

    public void Read(RWManagerBase io) throws IOException {
        super.Read(io);
        try {
            System.out.println(Name);
            DataNumericPredicateClass cls = (DataNumericPredicateClass) io.dataClasses.findClass(Name);
            LogicClass = (DataLogicalClass) cls.LogicClass.Clone();
            DataFuncReal1DClass predfuncclass = cls.PredicateFunction;
            System.out.println(predfuncclass.Name);
            PredicateFunction = (BaseDataFuncReal1D) predfuncclass.BaseDataObjectExample();
            System.out.println(PredicateFunction.Name);
            PredicateFunction.Read(io);
        } catch(ObjectNotFoundException ex) {
            throw new IOException("Predicate Type: " + Type + " not defined in Predicate Function of Numeric Predicate");
        }
    }
    
    public void Write(RWManagerBase io) throws IOException {
        super.Write(io);
        LogicClass.Write(io);
        PredicateFunction.Write(io);
    }
    
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man,DataObjectClass cls) {
        return new DBaseDataNumericPredicate(man,this,cls);
    }
    public BaseDataObject Clone() {
        BaseDataObject cls = (BaseDataObject)  new BaseDataNumericPredicate();
        cls.CopyClone(this);
       return cls;         
    }
    
    public void CopyClone(BaseDataObject obj) {
        super.CopyClone(obj);
        if(PredicateFunction != null) 
            PredicateFunction.CopyClone(obj);
        if(LogicClass != null) 
            LogicClass.CopyClone(obj);
    }

}
