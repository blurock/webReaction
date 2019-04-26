/*
 * BaseDataDegreeOfEquivalenceNumeric.java
 *
 * Created on June 6, 2005, 6:04 PM
 */

package blurock.EquivalenceClasses;
import java.io.IOException;
import blurock.coreobjects.BaseDataObject;
import blurock.coreobjects.BaseDataKeyWords;
import blurock.coreobjects.DBaseDataObject;
import blurock.coreobjects.DataObjectClass;
import blurock.core.ObjectDisplayManager;
import blurock.core.RWManagerBase;
import java.io.IOException;
/**
 *
 * @author  reaction
 */
public class BaseDataDegreeOfEquivalenceNumeric extends BaseDataDegreeOfEquivalence {
    double[] Values = null;
    /** Creates a new instance of BaseDataDegreeOfEquivalenceNumeric */
    public BaseDataDegreeOfEquivalenceNumeric() {
    }
    public BaseDataDegreeOfEquivalenceNumeric(String name, int id) {
        super(name,id);
    }
    public BaseDataObject Clone() {
        BaseDataDegreeOfEquivalenceNumeric obj = new BaseDataDegreeOfEquivalenceNumeric(Name,Identification);
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
        io.checkToken("Values:");
        try {
            String dimensionS = io.readElement();
            int dimension = Integer.valueOf(dimensionS).intValue();
            Values = new double[dimension];
            for(int i=0;i<dimension;i++) {
                String valueS = io.readElement();
                Values[i] = Double.valueOf(valueS).doubleValue();
            }
        } catch(NumberFormatException ex) {
            throw new IOException("Error in reading DegreeOfEquivalenceNumeric");
        }
    }
    
    public void Write(RWManagerBase io) throws IOException {
        io.printLine("Values:");
        int num = Values.length;
        io.printLine(Integer.toString(num));
        for(int i=0;i<num;i++) {
            io.printString(" " + Double.toString(Values[i]) + " ");
        }
        io.printLine("");
    }
   

}
