/*
 * BaseDataPopulation.java
 *
 * Created on March 1, 2001, 10:12 PM
 */

package blurock.pop.base;
import blurock.core.*;
import blurock.coreobjects.*;
import blurock.opandalgs.ops.BaseDataOperation;
import java.io.IOException;
import blurock.core.RWManager;
import java.util.*;

/**
 *
 * @author  reaction
 * @version 
 */
public class BaseDataPopulation  extends blurock.coreobjects.BaseDataSetOfObjects {

    public BaseDataGeneticObject GeneticObject = null;
    
    public int PopulationSize = 0;
    
    /** Creates new BaseDataPopulation */
    public BaseDataPopulation() {
    }

    public void Read(RWManager io) throws IOException {
        io.checkToken("Genetic:");
        try {
            DataPopulationClass cls = (DataPopulationClass) 
                           io.dataClasses.findClass(Type);
            GeneticObject = (BaseDataGeneticObject) cls.GeneticClass.BaseDataObjectExample();
            GeneticObject.Name = "GeneticObject";
            GeneticObject.Read(io);
            io.checkToken("Population:");
            PopulationSize = io.readInteger();
        } catch (NullPointerException nexp) {
            throw new IOException("Population Class not fully defined: '" + Type + "'");
        } catch (ObjectNotFoundException f) {
            throw new IOException("Population Class not registered: " + f);
        }
    }
    
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man,DataObjectClass cls) {
        DBaseDataPopulation dobj = 
            new DBaseDataPopulation(man,this,cls);
        return dobj;
    }
    
    public void Write(RWManager io) throws IOException {
        try {
            io.printLine("Genetic:");
            GeneticObject.Write(io);
            io.printLine("Population: " + String.valueOf(PopulationSize));
        } catch (NullPointerException nexp) {
            throw new IOException("Population Class not fully defined: '" + Type + "'");
        }
        
    }    
}
