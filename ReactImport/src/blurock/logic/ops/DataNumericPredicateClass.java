/*
 * DataNumericPredicateClass.java
 *
 * Created on September 21, 2004, 9:56 AM
 */

package blurock.logic.ops;
import blurock.DirectedTreeObjects.BaseDataTreeNode;
import blurock.coreobjects.BaseDataObject;
import blurock.coreobjects.DataObjectClass;
import blurock.coreobjects.DBaseDataObject;
import blurock.core.RWManagerBase;
import java.io.IOException;
import blurock.opandalgs.ops.BaseDataOperation;
import blurock.opandalgs.ops.DataOperationClass;
import blurock.opandalgs.ops.DBaseDataOperation;
import blurock.numeric.numops.DataFuncReal1DClass;
import blurock.logic.base.DataLogicalClass;
/**
 *
 * @author  reaction
 */
public class DataNumericPredicateClass extends blurock.opandalgs.ops.DataOperationClass {
    DataFuncReal1DClass PredicateFunction = null;
    DataLogicalClass LogicClass = null;
    
    /** Creates a new instance of DataNumericPredicateClass */
    public DataNumericPredicateClass() {
    }
    public DataNumericPredicateClass(int id, String type, String descr) {
        super(id,type,descr);
    }
    
    public BaseDataObject BaseDataObjectExample() {
        BaseDataObject obj = (BaseDataObject) new BaseDataNumericPredicate();
        obj.CopyClone((DataObjectClass) this);
        return obj;
    }
    
    public DataObjectClass Clone() {
        DataObjectClass cls = new DataNumericPredicateClass(Identification,Name,Description);
        cls.CopyClone(this);
       return cls;         
    }
    
    public void CopyClone(DataObjectClass cls) {
        super.CopyClone(cls);
        DataNumericPredicateClass predclass = (DataNumericPredicateClass) cls;
        PredicateFunction = (DataFuncReal1DClass) predclass.PredicateFunction.Clone();
        LogicClass = (DataLogicalClass) predclass.LogicClass.Clone();
    }
    
    public void Read(RWManagerBase io) throws IOException {
        super.Read(io);
        PredicateFunction = (DataFuncReal1DClass) io.getClassFromNextElement();
        LogicClass = (DataLogicalClass) io.getClassFromNextElement();
    }
    
    public void Write(RWManagerBase io) throws IOException {
        super.Write(io);
        io.printString(" " + PredicateFunction.Name + " ");
        io.printString(LogicClass.Name + " \n");
    }
    
}
