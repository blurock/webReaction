/*
 * BaseDataExactlyEqualPredicate.java
 *
 * Created on July 12, 2005, 3:32 PM
 */

package blurock.logic.predicates;
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
import blurock.logic.base.DataLogicalClass;
import blurock.core.ObjectNotFoundException;
/*
 *
 * @author  reaction
 */
public class BaseDataExactlyEqualPredicate extends blurock.opandalgs.ops.BaseDataOperation {
    public String parameterName = null;
    public BaseDataObject objectToCompare = null;
    public DataLogicalClass logicClass = null;
    /** Creates a new instance of BaseDataExactlyEqualPredicate */
    public BaseDataExactlyEqualPredicate() {
    }
    public void Read(RWManagerBase io) throws IOException {
        super.Read(io);
        try {
            DataExactlyEqualPredicateClass cls = (DataExactlyEqualPredicateClass) io.dataClasses.findClass(this.Type);
            io.checkToken("ExactlyEqualPredicate:");
            parameterName = io.nextToken();
            logicClass = cls.logicClass;
            DataObjectClass objectClass = cls.objectClass;
            if(objectClass != null) {
                objectToCompare = (BaseDataObject) cls.objectClass.BaseDataObjectExample();
                objectToCompare.Read(io);
            }
        } catch(ObjectNotFoundException ex) {
            System.out.println("Classes not initialized properly");
        }  
        
    }
    public void Write(RWManagerBase io) throws IOException {
        io.printLine("ExactlyEqualPredicate: " + parameterName);
        objectToCompare.Write(io);
    }
    
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man,DataObjectClass cls) {
        return new DBaseDataExactlyEqualPredicate(man,this,cls);
    }
    public BaseDataObject Clone() {
        BaseDataObject obj = (BaseDataObject) new BaseDataExactlyEqualPredicate();
        obj.CopyClone(this);
        return obj;
    }
    public void CopyClone(BaseDataObject obj) {
        BaseDataExactlyEqualPredicate pred = (BaseDataExactlyEqualPredicate) obj;
        if(pred.logicClass != null) logicClass = pred.logicClass;
        if(pred.parameterName != null) parameterName = pred.parameterName;
        if(pred.objectToCompare != null) objectToCompare = pred.objectToCompare;
    }
    
}
