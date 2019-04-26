/*
 * BaseDataProductionSystem.java
 *
 * Created on February 28, 2001, 12:02 PM
 */

package blurock.rules.prodsys;
import blurock.rules.actions.*;
import blurock.opandalgs.parameterized.*;
import blurock.coreobjects.*;
import blurock.core.*;
import javax.swing.*;
import java.io.IOException;

/**
 *
 * @author  reaction
 * @version 
 */
public class BaseDataProductionSystem extends blurock.opandalgs.parameterized.BaseDataParameterizedFunction {

    public BaseDataSetOfRules Rules;
    public String GeneratedNodeS;
    public int numberOfGoals;
    public String[] goalResults;
    
    /** Creates new BaseDataProductionSystem */
    public BaseDataProductionSystem() {
    }

    public void Read(RWManager io) throws IOException {
        try {
            io.checkToken("ProductionSystem:");
            io.checkToken("GeneratedNode:");
            GeneratedNodeS = io.readElement();
            io.checkToken("NumberOfGoals:");
            numberOfGoals = io.readInteger();
            io.checkToken("GoalResults:");
            goalResults = io.getSetOfNames();
            io.checkToken("GoalCondition:");
            super.Read(io);
            DataProductionSystemClass cls = (DataProductionSystemClass) 
                           io.dataClasses.findClass(Type);
            Rules = (BaseDataSetOfRules) cls.RulesClass.BaseDataObjectExample();
            Rules.Name = "RuleSystem";
            Rules.Read(io);
        } catch (NullPointerException nexp) {
            throw new IOException("Action Rule Class not fully defined");
        } catch (ObjectNotFoundException f) {
            throw new IOException("Action Rule not registered: " + f);
        }   
     }
    
    public void Write(RWManager io) throws IOException {
        try {
            super.Write(io);
            Rules.Write(io);
        } catch(NullPointerException nexp) {
            throw new IOException("Action Rule not fully defined");
        }
    }
    
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man,DataObjectClass cls) {
        return (DBaseDataObject) new DBaseDataProductionSystem(man,this,cls);
    }
    
}
