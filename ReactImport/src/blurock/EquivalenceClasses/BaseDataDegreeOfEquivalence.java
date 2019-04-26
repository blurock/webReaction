/*
 * BaseDataDegreeOfEquivalence.java
 *
 * Created on June 6, 2005, 6:01 PM
 */

package blurock.EquivalenceClasses;
import java.io.IOException;
import blurock.coreobjects.BaseDataObject;
import blurock.coreobjects.BaseDataKeyWords;
import blurock.coreobjects.DBaseDataObject;
import blurock.coreobjects.DataObjectClass;
import blurock.core.ObjectDisplayManager;
import blurock.core.RWManagerBase;

/**
 *
 * @author  reaction
 */
public class BaseDataDegreeOfEquivalence extends BaseDataObject {
    
    /** Creates a new instance of BaseDataDegreeOfEquivalence */
    public BaseDataDegreeOfEquivalence() {
    }
    public BaseDataDegreeOfEquivalence(String name, int id) {
        super(name,id);
    }
    public BaseDataObject Clone() {
        BaseDataDegreeOfEquivalence obj = new BaseDataDegreeOfEquivalence(Name,Identification);
        obj.CopyClone(this);
       return obj;         
    }
    
    public void CopyClone(BaseDataObject obj) {
        Name = obj.Name;
        Identification = obj.Identification;
        Type = obj.Type;
    }

    public DBaseDataObject getDisplayObject(ObjectDisplayManager man,DataObjectClass cls) {
        return new DBaseDataObject(man,this,cls);
    }
    public void Read(RWManagerBase io) throws IOException {
    }
    
    public void Write(RWManagerBase io) throws IOException {
    }
   

}
