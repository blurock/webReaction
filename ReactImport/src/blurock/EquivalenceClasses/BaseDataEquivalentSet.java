/*
 * BaseDataEquivalentSet.java
 *
 * Created on June 6, 2005, 5:09 PM
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
public class BaseDataEquivalentSet extends BaseDataObject {
    public BaseDataKeyWords ObjectNames = null;
    public BaseDataDegreeOfEquivalence degreeOfEquivalence = null;
    public BaseDataObject cutOffCriteria = null;
    /** Creates a new instance of BaseDataEquivalentSet */
    public BaseDataEquivalentSet() {
    }
    public BaseDataEquivalentSet(String name, int id) {
        super(name,id);
    }
    public BaseDataObject Clone() {
        BaseDataEquivalentSet obj = new BaseDataEquivalentSet(Name,Identification);
        obj.CopyClone(this);
       return obj;         
    }
    
    public void CopyClone(BaseDataObject o) {
        BaseDataEquivalentSet obj = (BaseDataEquivalentSet) o;
        Name = obj.Name;
        Identification = obj.Identification;
        Type = obj.Type;
        if(obj.ObjectNames != null) {
            ObjectNames = obj.ObjectNames.Clone();
        }
        if(obj.degreeOfEquivalence != null) {
            degreeOfEquivalence = (BaseDataDegreeOfEquivalence) obj.degreeOfEquivalence.Clone();
        }
        if(obj.cutOffCriteria != null) {
            cutOffCriteria = obj.cutOffCriteria.Clone();
        }
    }

    public DBaseDataObject getDisplayObject(ObjectDisplayManager man,DataObjectClass cls) {
        return new DBaseDataEquivalenceSet(man,this,cls);
    }
    public void Read(RWManagerBase io) throws IOException {
        io.checkToken("EquivalentSet:");
        ObjectNames.Read(io);
        String degreeToken = io.readElement();
        if(degreeToken.startsWith("NoEquivalence")) {
            degreeOfEquivalence = null;
        } else if(degreeToken.startsWith("Equivalence:")) {
            degreeOfEquivalence.Read(io);
        } else {
            throw new IOException("Unknown token while reading EquivalentSet: " + degreeToken);
        }
        String cutoffToken = io.readElement();
        if(cutoffToken.startsWith("NoCutoff")) {
              cutOffCriteria = null;
        } else if(cutoffToken.startsWith("CutOffCriteria")) {
            System.out.println("Read in Criteria: " + cutOffCriteria);
            cutOffCriteria.Read(io);
            
        } else {
            throw new IOException("Unknown token while reading EquivalentSet: " + cutoffToken);
        }
    }
    
    public void Write(RWManagerBase io) throws IOException {
        io.printLine("EquivalentSet:");
        ObjectNames.Write(io);
        if(degreeOfEquivalence != null) {
            io.printLine("Equivalence:");
            degreeOfEquivalence.Write(io);
        } else {
            io.printLine(" NoEquivalence ");
        }
        if(cutOffCriteria != null) {
            io.printLine("CutOffCriteria");
            cutOffCriteria.Write(io);
        } else {
            io.printLine(" NoCutoff ");
        }
    }
   
}
