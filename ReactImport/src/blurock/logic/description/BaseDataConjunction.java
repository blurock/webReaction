/*
 * BaseDataConjunction.java
 *
 * Created on September 17, 2004, 7:12 PM
 */

package blurock.logic.description;
import blurock.core.RWManagerBase;
import java.io.IOException;
import blurock.core.ObjectDisplayManager;
import blurock.coreobjects.BaseDataObject;
import blurock.coreobjects.DataObjectClass;
import blurock.coreobjects.DBaseDataObject;

/**
 *
 * @author  reaction
 */
public class BaseDataConjunction extends blurock.opandalgs.ops.BaseDataOperation {
    BaseDataDescription LogicOperations;
    
    /** Creates a new instance of BaseDataConjunction */
    public BaseDataConjunction() {
        LogicOperations = new BaseDataDescription();
        LogicOperations.Name = "ConjunctionDescription";
        Type = "Conjunction";
        Name = "Conjunction";
    }
    
    public void Read(RWManagerBase io) throws IOException {
        io.checkToken("ConjunctionPredicates:"); 
        LogicOperations.Read(io);
    }
    
    public void Write(RWManagerBase io) throws IOException {
        io.printLine("Connections:");
        LogicOperations.Write(io);
    }
    
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man, DataObjectClass cls) {
        return new DBaseDataConjunction(man,this,cls);
    }
    
    public BaseDataObject Clone() {
        BaseDataObject cls = (BaseDataObject)  new BaseDataConjunction();
        cls.CopyClone(this);
       return cls;         
    }
    
    public void CopyClone(BaseDataObject obj) {
        super.CopyClone(obj);
        BaseDataDescription descr = (BaseDataDescription) obj;
        LogicOperations.CopyClone(descr);
    }
    public BaseDataDescription setOfLogicOperations() {
        return LogicOperations;
    }
}
