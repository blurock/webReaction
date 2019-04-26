/*
 * DataPredicateClass.java
 *
 * Created on February 1, 2002, 10:47 PM
 */

package blurock.logic.predicates;
import blurock.coreobjects.DataObjectClass;
import blurock.coreobjects.BaseDataObject;
import blurock.core.RWManager;
import blurock.opandalgs.ops.DataOperationClass;
import blurock.opandalgs.ops.BaseDataOperation;
import java.io.IOException;
/**
 *
 * @author  reaction
 * @version 
 */
public class DataPredicateClass extends blurock.opandalgs.ops.DataOperationClass {
    public DataOperationClass OpClass = null;
    
    /** Creates new DataPredicateClass */
    public DataPredicateClass() {
    }
    public DataPredicateClass(int id, String type, String descr) {
        super(id,type,descr);
        SubClass = "Operation";
    }

    public BaseDataObject BaseDataObjectExample() {
        BaseDataPredicate obj = new BaseDataPredicate();
        obj.Type = Name;
        obj.Operation = (BaseDataOperation) OpClass.BaseDataObjectExample();
        return (BaseDataObject) obj;
    }
    
    public DataObjectClass Clone() {
        DataPredicateClass cls = new DataPredicateClass(Identification,Name,Description);
        cls.CopyClone(this);
       return cls;
    }
    
    public void CopyClone(DataObjectClass cls) {
        DataPredicateClass predcls = (DataPredicateClass) cls;
        if(predcls.OpClass != null) 
            OpClass = (DataOperationClass) predcls.OpClass.Clone();
        super.CopyClone(cls);
    }
    
    public void Read(RWManager io) throws IOException {
        super.Read(io);
        OpClass = (DataOperationClass) io.getClassFromNextElement();
    }
    
    public void Write(RWManager io) throws IOException {
        super.Write(io);
    }
    
}
