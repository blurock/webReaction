/*
 * DataGeneticSetOfParametersClass.java
 *
 * Created on March 1, 2001, 11:25 PM
 */

package blurock.pop.genetic;
import blurock.pop.base.*;
import blurock.core.RWManager;
import blurock.coreobjects.*;
import blurock.opandalgs.ops.*;
import java.io.IOException;

/**
 *
 * @author  reaction
 * @version 
 */
public class DataGeneticSetOfParametersClass extends blurock.pop.base.DataGeneticObjectClass {
    DataSetOfObjectsClass GeneticObjectsClass = null;

    /** Creates new DataGeneticSetOfParametersClass */
    public DataGeneticSetOfParametersClass() {
    }
    public DataGeneticSetOfParametersClass(int id, String type, String descr) {
        super(id,type,descr);
        SubClass = "GeneticObject";
    }

    public BaseDataObject BaseDataObjectExample() {
        BaseDataObject obj = (BaseDataObject) new BaseDataGeneticSetOfParameters();
        obj.Type = Name;
        return obj;
    }
    public void CopyClone(DataObjectClass ocls) {
        super.CopyClone(ocls);
        DataGeneticSetOfParametersClass cls = (DataGeneticSetOfParametersClass) ocls;
        try {
            cls.GeneticObjectsClass = (DataSetOfObjectsClass) GeneticObjectsClass.Clone();
        } catch(NullPointerException exp) {
            cls.GeneticObjectsClass = null;
        }
    }
    
    public DataObjectClass Clone() {
        DataGeneticSetOfParametersClass cls = 
                new DataGeneticSetOfParametersClass(Identification,Name,Description);
        cls.CopyClone(this);
       return cls;
    }
    
    public void Read(RWManager io) throws IOException {
        //super.Read(io);
        GeneticObjectsClass = (DataSetOfObjectsClass) io.getClassFromNextElement();
    }
    
    public void Write(RWManager io) throws IOException {
        //super.Write(io);
        try {
            io.printLine(GeneticObjectsClass.Name);
        } catch (NullPointerException nexp) {
            throw new IOException("Genetic Parameter Set Class not fully defined");
        }
    }
    
}
