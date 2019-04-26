/*
 * DataLogicDescriptionClass.java
 *
 * Created on March 3, 2001, 6:11 PM
 */

package blurock.logic.description;
import blurock.logic.base.*;
import blurock.core.RWManager;
import blurock.coreobjects.*;
import blurock.opandalgs.ops.*;
import java.io.IOException;

/**
 *
 * @author  reaction
 * @version 
 */
public class DataLogicDescriptionClass extends blurock.coreobjects.DataSetOfObjectsClass {

    private DataLogicalClass LogicClass;
    
    /** Creates new DataLogicDescriptionClass */
    public DataLogicDescriptionClass() {
    }
    public DataLogicDescriptionClass(int id, String type, String descr) {
        super(id,type,descr);
        SubClass = "GeneticObject";
    }

    public BaseDataObject BaseDataObjectExample() {
        BaseDataObject obj = (BaseDataObject) new BaseDataLogicDescription();
        obj.Type = Name;
        return obj;
    }
    
    public DataObjectClass Clone() {
        DataLogicDescriptionClass cls = 
                new DataLogicDescriptionClass(Identification,Name,Description);
        cls.CopyClone(this);
       return cls;
    }
    
    public void CopyClone(DataObjectClass ocls) {
        super.CopyClone(ocls);
    }
    
    public void Read(RWManager io) throws IOException {
        //super.Read(io);
        LogicClass = (DataLogicalClass) io.getClassFromNextElement();
    }
    
    public void Write(RWManager io) throws IOException {
        super.Write(io);
        try {
            io.printLine(LogicClass.Name);
        } catch (NullPointerException nexp) {
            throw new IOException("Logic Description class not fully defined");
        }
     }
    
}
