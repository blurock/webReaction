/*
 * BaseDataPredicate.java
 *
 * Created on February 1, 2002, 10:45 PM
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
/**
 *
 * @author  reaction
 * @version 
 */
public class BaseDataPredicate extends blurock.opandalgs.ops.BaseDataOperation {
    public String Logic = null;
    public String Parameter = null;
    public BaseDataOperation Operation = null;

    /** Creates new BaseDataPredicate */
    public BaseDataPredicate() {
    }
    public BaseDataPredicate(BaseDataPredicate pred) {
        pred.CopyClone(this);
    }
    public void Read(RWManagerBase io) throws IOException {
        super.Read(io);
        io.checkToken("Predicate:");
        Parameter = io.nextToken();
        Logic = io.nextToken();
        Operation.Read(io);
    }
    public void Write(RWManagerBase io) throws IOException {
        super.Write(io);
    }
    
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man,DataObjectClass cls) {
        return new DBaseDataPredicate(man,this,cls);
    }
    public BaseDataObject Clone() {
        BaseDataObject obj = (BaseDataObject) new BaseDataPredicate();
        obj.CopyClone(this);
        return obj;
    }
    public void CopyClone(BaseDataObject obj) {
        BaseDataPredicate pred = (BaseDataPredicate) obj;
        if(pred.Logic != null) Logic = pred.Logic;
        if(pred.Parameter != null) Parameter = pred.Parameter;
        if(pred.Operation != null) Operation = pred.Operation;
    }
    
}
