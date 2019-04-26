/*
 * DataAlgorithmSetupClass.java
 *
 * Created on July 3, 2001, 4:00 PM
 */

package xml.structure;
import java.io.IOException;
import blurock.coreobjects.*;
import blurock.core.*;

/**
 *
 * @author  reaction
 * @version 
 */
public class DataAlgorithmSetupClass extends blurock.coreobjects.DataObjectClass {

    /** Creates new DataAlgorithmSetupClass */
    public DataAlgorithmSetupClass() {
        super(5,"AlgorithmSetup","Algorithm Information");
    }
    public DataAlgorithmSetupClass(int id, String name, String descr) {
        super(id,name,descr);
    }

    public BaseDataObject BaseDataObjectExample() {
        return new BaseDataAlgorithmSetup(null);
    }
    
    public DataObjectClass Clone() {
        DataAlgorithmSetupClass cls = new DataAlgorithmSetupClass(Identification,Name,Description);
        cls.CopyClone(this);
       return cls; 
    }
    
    public void CopyClone(DataObjectClass cls) {
        super.CopyClone(cls);
    }
    
    public void Read(RWManager io) throws IOException {
    }
    
    public void Write(RWManager io) throws IOException {
    }
    
}
