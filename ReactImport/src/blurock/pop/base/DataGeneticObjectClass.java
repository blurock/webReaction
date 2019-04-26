/*
 * DataGeneticObject.java
 *
 * Created on March 1, 2001, 6:19 PM
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
public class DataGeneticObjectClass extends blurock.opandalgs.ops.DataOperationClass {

    private DataObjectClass ObjectClass;
    
    /** Creates new DataGeneticObject */
    public DataGeneticObjectClass() {
    }

    public DataGeneticObjectClass(int id, String type, String descr) {
        super(id,type,descr);
        SubClass = "Operation";
    }

    public BaseDataObject BaseDataObjectExample() {
        BaseDataObject obj = (BaseDataObject) new BaseDataGeneticObject();
        obj.Type = Name;
        return obj;
    }
    
    public void CopyClone(DataObjectClass ocls) {
        super.CopyClone(ocls);
        DataGeneticObjectClass cls = (DataGeneticObjectClass) ocls;
        try {
            cls.ObjectClass = (DataObjectClass) ObjectClass.Clone();
        } catch(NullPointerException exp) {
            cls.ObjectClass = null;
        }
    }
    public DataObjectClass Clone() {
        DataGeneticObjectClass cls = 
                new DataGeneticObjectClass(Identification,Name,Description);
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
