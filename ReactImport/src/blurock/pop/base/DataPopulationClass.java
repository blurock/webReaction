/*
 * DataPopulationClass.java
 *
 * Created on March 1, 2001, 10:17 PM
 */

package blurock.pop.base;
import blurock.core.RWManager;
import blurock.coreobjects.*;
import blurock.opandalgs.ops.*;
import java.io.IOException;

/**
 *
 * @author  reaction
 * @version 
 */
public class DataPopulationClass extends blurock.coreobjects.DataSetOfObjectsClass {

    public DataPopulationObjectClass ObjectClass;
    
    public DataGeneticObjectClass GeneticClass;
    
    /** Creates new DataPopulationClass */
    public DataPopulationClass() {
    }
    public DataPopulationClass(int id, String type, String descr) {
        super(id,type,descr);
        SubClass = "SetOfObjects";
    }

    public BaseDataObject BaseDataObjectExample() {
        BaseDataObject obj = (BaseDataObject) new BaseDataPopulation();
        obj.Type = Name;
        return obj;
    }
    public void CopyClone(DataObjectClass ocls) {
        super.CopyClone(ocls);
        DataPopulationClass cls = (DataPopulationClass) ocls;
        try {
            cls.ObjectClass = (DataPopulationObjectClass) ObjectClass.Clone();
            cls.GeneticClass = (DataGeneticObjectClass) GeneticClass.Clone();
        } catch(NullPointerException exp) {
            cls.ObjectClass = null;
            cls.GeneticClass = null;
        }
    }
    
    public DataObjectClass Clone() {
        DataPopulationClass cls = new DataPopulationClass(Identification,Name,Description);
        cls.CopyClone(this);
       return cls;
    }
    
    public void Read(RWManager io) throws IOException {
        //super.Read(io);
        ObjectClass = (DataPopulationObjectClass) io.getClassFromNextElement();
        GeneticClass = (DataGeneticObjectClass) io.getClassFromNextElement();
    }
    
    public void Write(RWManager io) throws IOException {
        //super.Write(io);
        try {
            io.printLine(ObjectClass.Name + " " + GeneticClass.Name);
        } catch (NullPointerException nexp) {
            throw new IOException("Genetic Object Class not fully defined");
        }
    }
    
}
