/*
 * DataEquivalentSetClass.java
 *
 * Created on June 6, 2005, 5:35 PMDataEquivalentSetClass
 */

package blurock.EquivalenceClasses;
import java.io.IOException;
import blurock.coreobjects.BaseDataObject;
import blurock.coreobjects.DBaseDataObject;
import blurock.coreobjects.DataObjectClass;
import blurock.core.ObjectDisplayManager;
import blurock.core.RWManagerBase;
import blurock.core.ObjectNotFoundException;
import blurock.coreobjects.BaseDataKeyWords;
/**
 *
 * @author  reaction
 */
public class DataEquivalentSetClass extends DataObjectClass {
    public DataDegreeOfEquivalenceClass EquivalenceClass = null;
    public DataObjectClass CutOffCriteriaClass = null;
    /** Creates a new instance of DataEquivalentSetClass */
    public DataEquivalentSetClass() {
    }
   public DataEquivalentSetClass(int id, String type, String descr) {
       super(id,type,descr);
    }
    public void CopyClone(DataObjectClass cls) {
        super.CopyClone(cls);
        DataEquivalentSetClass ecls = (DataEquivalentSetClass) cls;
        derivedClass = cls.derivedClass;
        SubClass = cls.SubClass;
        if(ecls.EquivalenceClass != null)
            EquivalenceClass = (DataDegreeOfEquivalenceClass) ecls.EquivalenceClass.Clone();
        if(ecls.CutOffCriteriaClass != null)
            CutOffCriteriaClass = (DataObjectClass) ecls.CutOffCriteriaClass.Clone();
    }

    public DataObjectClass Clone() {
        DataEquivalentSetClass cls = new DataEquivalentSetClass(Identification,Name,Description);
        cls.CopyClone(this);
       return (DataObjectClass) cls; 
        
    }
    public BaseDataObject BaseDataObjectExample() {
        BaseDataEquivalentSet set = new BaseDataEquivalentSet(this.Type,this.Identification);
        set.Type = Name;
        set.ObjectNames = new BaseDataKeyWords();
        if(this.EquivalenceClass != null) {
            set.degreeOfEquivalence = (BaseDataDegreeOfEquivalence) EquivalenceClass.BaseDataObjectExample();
        } else {
            set.degreeOfEquivalence = null;
        }
        if(this.CutOffCriteriaClass != null) {
            set.cutOffCriteria = (BaseDataObject) CutOffCriteriaClass.BaseDataObjectExample();
        } else {
            set.cutOffCriteria = null;
        }
        return set;
    }
    public void Read(RWManagerBase io) throws IOException {
        String equivalenceS = io.readElement();
        String cutoffS = io.readElement();
        System.out.println("Read EquivalentSet Class: " + equivalenceS + "  " + cutoffS);
        try {
            if(!equivalenceS.equals("null"))
                EquivalenceClass = (DataDegreeOfEquivalenceClass) io.dataClasses.findClass(equivalenceS);
            if(!cutoffS.equals("null"))
                CutOffCriteriaClass = (DataObjectClass) io.dataClasses.findClass(cutoffS);
        } catch(ObjectNotFoundException ex) {
            throw new IOException("Error in Reading EquivalentSet Class: class not found");
        }
    }
    
    public void Write(RWManagerBase io) throws IOException {
        if(EquivalenceClass != null) {
            io.printLine(EquivalenceClass.Type);
        } else {
            io.printLine("null");
        }
        if(CutOffCriteriaClass != null) {
            io.printLine(CutOffCriteriaClass.Type);
        } else {
            io.printLine("null");
        }
    } 
    
}
