/*
 * BaseDataGeneticSetOfParameters.java
 *
 * Created on March 1, 2001, 11:24 PM
 */

package blurock.pop.genetic;
import blurock.pop.base.*;
import blurock.core.*;
import blurock.coreobjects.*;
import blurock.opandalgs.parameterized.*;
import java.io.IOException;
import blurock.core.RWManager;

/**
 *
 * @author  reaction
 * @version 
 */
public class BaseDataGeneticSetOfParameters extends blurock.pop.base.BaseDataGeneticObject {
    BaseDataSetOfObjects GeneticObjects = null;
    /** Creates new BaseDataGeneticSetOfParameters */
    public BaseDataGeneticSetOfParameters() {
    }

    public void Read(RWManager io) throws IOException {
        super.Read(io);
        try {
            io.checkToken("GeneticParameters:");
            DataGeneticSetOfParametersClass cls = (DataGeneticSetOfParametersClass) 
                           io.dataClasses.findClass(Type);
            GeneticObjects = (BaseDataSetOfObjects) cls.GeneticObjectsClass.BaseDataObjectExample();
            GeneticObjects.Name = "Genetic Parameter Set";
            ClassNamePairs ps = new ClassNamePairs(false);
            ps.Read(io);
            GeneticObjects.Read(io,ps);
        } catch (NullPointerException nexp) {
            throw new IOException("Population Class not fully defined: '" + Type + "'");
        } catch (ObjectNotFoundException f) {
            throw new IOException("Population Class not registered: " + f);
        }
    }
    
    public void Write(RWManager io) throws IOException {
        super.Write(io);
        try {
            io.printLine("GeneticParameters:");
            GeneticObjects.Write(io);
        } catch (NullPointerException nexp) {
            throw new IOException("Genetic Parameter Set Class not fully defined: '" + Type + "'");
        }
    }
    
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man,DataObjectClass cls) {
        DBaseDataGeneticSetOfParameters dobj = new DBaseDataGeneticSetOfParameters(man,this,cls);
        return dobj;
    }
    
}
