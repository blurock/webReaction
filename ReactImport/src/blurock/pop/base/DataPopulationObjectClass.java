/*
 * DataPopulationObjectClass.java
 *
 * Created on March 1, 2001, 9:47 PM
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
public class DataPopulationObjectClass extends blurock.coreobjects.DataObjectClass {

    private DataObjectClass ObjectClass;
    
    /** Creates new DataPopulationObjectClass */
    public DataPopulationObjectClass() {
    }
    public DataPopulationObjectClass(int id, String type, String descr) {
        super(id,type,descr);
        SubClass = "Object";
    }

    public BaseDataObject BaseDataObjectExample() {
        BaseDataObject obj = (BaseDataObject) new BaseDataPopulationObject();
        obj.Type = Name;
        return obj;
    }
    public void CopyClone(DataObjectClass ocls) {
        super.CopyClone(ocls);
        DataPopulationObjectClass cls = (DataPopulationObjectClass) ocls;
        try {
            cls.ObjectClass = (DataObjectClass) ObjectClass.Clone();
        } catch(NullPointerException exp) {
            cls.ObjectClass = null;
        }
    }
    public DataObjectClass Clone() {
        DataPopulationObjectClass cls = new DataPopulationObjectClass(Identification,Name,Description);
        cls.CopyClone(this);
        return cls;
    }
    
    public void Read(RWManager io) throws IOException {
        super.Read(io);
        ObjectClass = (DataObjectClass) io.getClassFromNextElement();
    }
    
    public void Write(RWManager io) throws IOException {
        super.Write(io);
        try {
            io.printLine(ObjectClass.Name);
        } catch (NullPointerException nexp) {
            throw new IOException("Genetic Object Class not fully defined");
        }
    }
    
}
