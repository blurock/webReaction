/*
 * DataDegreeOfEquivalenceClass.java
 *
 * Created on June 6, 2005, 6:03 PM
 */

package blurock.EquivalenceClasses;
import java.io.IOException;
import blurock.coreobjects.BaseDataObject;
import blurock.coreobjects.DBaseDataObject;
import blurock.coreobjects.DataObjectClass;
import blurock.core.ObjectDisplayManager;
import blurock.core.RWManagerBase;

/**
 *
 * @author  reaction
 */
public class DataDegreeOfEquivalenceClass extends DataObjectClass {
    
    /** Creates a new instance of DataDegreeOfEquivalenceClass */
    public DataDegreeOfEquivalenceClass() {
    }
   public DataDegreeOfEquivalenceClass(int id, String type, String descr) {
       super(id,type,descr);
    }
    public void CopyClone(DataObjectClass cls) {
        super.CopyClone(cls);
        derivedClass = cls.derivedClass;
        SubClass = cls.SubClass;
    }

    public DataObjectClass Clone() {
        DataDegreeOfEquivalenceClass cls = new DataDegreeOfEquivalenceClass(Identification,Name,Description);
        cls.CopyClone(this);
       return (DataObjectClass) cls; 
        
    }
    public BaseDataObject BaseDataObjectExample() {
        BaseDataDegreeOfEquivalence obj = new BaseDataDegreeOfEquivalence(this.Name,this.Identification);
        obj.Type = this.Name;
        return obj;
    }
    public void Read(RWManagerBase io) throws IOException {
    }
    
    public void Write(RWManagerBase io) throws IOException {
    }

}
