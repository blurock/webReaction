/*
 * DataSetOfEquivalentSetsClass.java
 *
 * Created on June 6, 2005, 6:13 PM
 */

package blurock.EquivalenceClasses;
import java.io.IOException;
import blurock.coreobjects.BaseDataObject;
import blurock.coreobjects.DBaseDataObject;
import blurock.coreobjects.DataObjectClass;
import blurock.core.ObjectDisplayManager;
import blurock.core.RWManagerBase;
import blurock.coreobjects.DataSetOfObjectsClass;
import blurock.core.ObjectNotFoundException;

/**
 *
 * @author  reaction
 */
public class DataSetOfEquivalentSetsClass extends DataSetOfObjectsClass {
    public DataEquivalentSetClass EquivalentSetClass = null;
    /** Creates a new instance of DataSetOfEquivalentSetsClass */
    public DataSetOfEquivalentSetsClass() {
    }
   public DataSetOfEquivalentSetsClass(int id, String type, String descr) {
       super(id,type,descr);
    }
    public void CopyClone(DataObjectClass cls) {
        super.CopyClone(cls);
        DataSetOfEquivalentSetsClass ecls = (DataSetOfEquivalentSetsClass) cls;
        if(ecls.EquivalentSetClass != null) {
            EquivalentSetClass = (DataEquivalentSetClass) ecls.EquivalentSetClass.Clone();
        }
    }

    public DataObjectClass Clone() {
        DataSetOfEquivalentSetsClass cls = new DataSetOfEquivalentSetsClass(Identification,Name,Description);
        cls.CopyClone(this);
       return cls; 
        
    }
    public BaseDataObject BaseDataObjectExample() {
        BaseDataSetOfEquivalentSets sets = new BaseDataSetOfEquivalentSets(this.Name,this.Identification);
        sets.Type = this.Name;
        return sets;
    }
    public void Read(RWManagerBase io) throws IOException {
        try {
            String name = io.readElement();
            if(name != null) {
                EquivalentSetClass = (DataEquivalentSetClass) io.dataClasses.findClass(name);
            }
        } catch(ObjectNotFoundException ex) {
            throw new IOException("Error in Reading EquivalentSet Class: class not found");
        }
    }
    
    public void Write(RWManagerBase io) throws IOException {
    }

}
