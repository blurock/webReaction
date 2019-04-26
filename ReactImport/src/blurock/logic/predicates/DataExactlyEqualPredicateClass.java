/*
 * DataExactlyEqualPredicateClass.java
 *
 * Created on July 12, 2005, 3:43 PM
 */

package blurock.logic.predicates;
import blurock.coreobjects.DataObjectClass;
import blurock.coreobjects.BaseDataObject;
import blurock.core.RWManagerBase;
import blurock.opandalgs.ops.DataOperationClass;
import blurock.opandalgs.ops.BaseDataOperation;
import java.io.IOException;
import blurock.logic.base.DataLogicalClass;

/**
 *
 * @author  reaction
 */
public class DataExactlyEqualPredicateClass extends DataOperationClass {
    public DataObjectClass objectClass = null;
    public DataLogicalClass logicClass = null;
    
    /** Creates a new instance of DataExactlyEqualPredicateClass */
    public DataExactlyEqualPredicateClass() {
    }
    public DataExactlyEqualPredicateClass(int id, String type, String descr) {
        super(id,type,descr);
    }
    public BaseDataObject BaseDataObjectExample() {
        BaseDataExactlyEqualPredicate obj = new BaseDataExactlyEqualPredicate();
        obj.Type = this.Name;
        obj.objectToCompare = (BaseDataObject) objectClass.BaseDataObjectExample();
        obj.logicClass = (DataLogicalClass) logicClass;
        return (BaseDataObject) obj;
    }
    
    public DataObjectClass Clone() {
        DataExactlyEqualPredicateClass cls = new DataExactlyEqualPredicateClass(Identification,Name,Description);
        cls.CopyClone(this);
       return cls;
    }
    
    public void CopyClone(DataObjectClass cls) {
        super.CopyClone(cls);
        DataExactlyEqualPredicateClass predcls = (DataExactlyEqualPredicateClass) cls;
        if(predcls.objectClass != null) 
            objectClass = (DataObjectClass) predcls.objectClass.Clone();
        if(predcls.logicClass != null)
            logicClass = (DataLogicalClass) predcls.logicClass.Clone();
    }
    
    public void Read(RWManagerBase io) throws IOException {
        logicClass = (DataLogicalClass) io.getClassFromNextElement();
        objectClass = (DataObjectClass) io.getClassFromNextElement();
    }
    
    public void Write(RWManagerBase io) throws IOException {
        io.printLine(logicClass.Name);
        io.printLine(objectClass.Name);
    }
    
}
