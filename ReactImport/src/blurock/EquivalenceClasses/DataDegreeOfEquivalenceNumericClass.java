/*
 * DataDegreeOfEquivalenceNumericClass.java
 *
 * Created on June 6, 2005, 6:04 PM
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
public class DataDegreeOfEquivalenceNumericClass extends DataDegreeOfEquivalenceClass {
    
    /** Creates a new instance of DataDegreeOfEquivalenceNumericClass */
    public DataDegreeOfEquivalenceNumericClass() {
    }
   public DataDegreeOfEquivalenceNumericClass(int id, String type, String descr) {
       super(id,type,descr);
        System.out.println("Construct DataDegreeOfEquivalenceNumericClass" + id);
    }
    public void CopyClone(DataObjectClass cls) {
        super.CopyClone(cls);
        Type = cls.Type;
    }

    public DataObjectClass Clone() {
        System.out.println("Clone DataDegreeOfEquivalenceNumericClass");
        DataDegreeOfEquivalenceNumericClass cls = new DataDegreeOfEquivalenceNumericClass(Identification,Name,Description);
        cls.CopyClone(this);
       return (DataObjectClass) cls; 
        
    }
    public BaseDataObject BaseDataObjectExample() {
        BaseDataDegreeOfEquivalenceNumeric num = new BaseDataDegreeOfEquivalenceNumeric(Name,Identification);
        num.Type = Type;
        return num;
    }
    public void Read(RWManagerBase io) throws IOException {
        System.out.println("Read DataDegreeOfEquivalenceNumericClass");
        String t = io.readElement();
        System.out.println(t);
    }
    
    public void Write(RWManagerBase io) throws IOException {
    }

}
