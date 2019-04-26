/*
 * DataPredicateClass.java
 *
 * Created on February 1, 2002, 10:47 PM
 */

package blurock.numeric.numops;
import blurock.coreobjects.DataObjectClass;
import blurock.coreobjects.BaseDataObject;
import blurock.core.RWManager;
import blurock.opandalgs.ops.DataOperationClass;
import java.io.IOException;
import blurock.logic.base.*;
import blurock.logic.predicates.*;
import blurock.numeric.numops.DataFuncReal1DClass;
import blurock.logic.base.DataLogicalClass;

/**
 *
 * @author  reaction
 * @version 
 */
public class DataNumericPredicateClass extends blurock.opandalgs.ops.DataOperationClass {
     public DataFuncReal1DClass PredicateFunction = null;
     public DataLogicalClass LogicClass = null;
    
    /** Creates new DataPredicateClass */
    public DataNumericPredicateClass() {
    }
    public DataNumericPredicateClass(int id,String type,String descr) {
        super(id,type,descr);
        SubClass = "Operation";
    }

    public BaseDataObject BaseDataObjectExample() {
        BaseDataObject obj = (BaseDataObject) new BaseDataNumericPredicate();
        obj.Type = this.Type;
        obj.CopyClone((DataObjectClass) this);
        return obj;
    }
    
    public DataObjectClass Clone() {
        DataNumericPredicateClass cls = new DataNumericPredicateClass(Identification,Name,Description);
        cls.CopyClone(this);
       return cls;
    }
    
    public void CopyClone(DataObjectClass cls) {
        super.CopyClone(cls);
        DataNumericPredicateClass predclass = (DataNumericPredicateClass) cls;
        if(predclass.PredicateFunction != null)
            PredicateFunction = (DataFuncReal1DClass) predclass.PredicateFunction.Clone();
        if(predclass.LogicClass != null)
             LogicClass = (DataLogicalClass) predclass.LogicClass.Clone();
    }
    
    public void Read(RWManager io) throws IOException {
        super.Read(io);
        PredicateFunction = (DataFuncReal1DClass) io.getClassFromNextElement();
        LogicClass = (DataLogicalClass) io.getClassFromNextElement();
    }
    
    public void Write(RWManager io) throws IOException {
        super.Write(io);
        io.printString(" " + PredicateFunction.Name + " ");
        io.printString(LogicClass.Name + " \n");
    }
    
}
