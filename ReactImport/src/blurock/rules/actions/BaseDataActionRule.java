/*
 * BaseDataActionRule.java
 *
 * Created on February 28, 2001, 9:02 AM
 */

package blurock.rules.actions;
import blurock.opandalgs.parameterized.*;
import blurock.coreobjects.*;
import blurock.opandalgs.ops.*;
import blurock.core.*;
import javax.swing.*;
import java.io.IOException;

/**
 *
 * @author  reaction
 * @version 
 */
public class BaseDataActionRule extends blurock.opandalgs.parameterized.BaseDataParameterizedFunction {

    public BaseDataParameterizedFunction Condition;
    
    public BaseDataParameterSet Actions;
    
    public double cutOff;
    
    /** Creates new BaseDataActionRule */
    public BaseDataActionRule() {
    }

    public void Read(RWManager io) throws IOException {
        System.out.println("Read in ActionRule");
        try {
            DataActionRuleClass cls = (DataActionRuleClass) 
                           io.dataClasses.findClass(Type);
            Condition = (BaseDataParameterizedFunction) cls.ConditionClass.BaseDataObjectExample();
            Actions = (BaseDataParameterSet) cls.ActionsClass.BaseDataObjectExample();
            Actions.useKeyWord = false;
            Condition.Name = "RuleCondition";
            Actions.Name = "Actions";
            io.checkToken("ActionRule:");
            super.Read(io);
            io.checkToken("ActionRuleCondition:");
            Condition.Read(io);
            io.checkToken("CutOff:");
            cutOff = io.readDouble();
            io.checkToken("ActionRuleSetOfActions:");
            Actions.Read(io);
        } catch (NullPointerException nexp) {
            throw new IOException("Action Rule Class not fully defined");
        } catch (ObjectNotFoundException f) {
            throw new IOException("Action Rule not registered: " + f);
        }   
    }
    
    public void Write(RWManager io) throws IOException {
        try {
            super.Write(io);
            Condition.Write(io);
            Actions.Write(io);
        } catch(NullPointerException nexp) {
            throw new IOException("Action Rule not fully defined");
        }
    }
    
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man,DataObjectClass cls) {
        return (DBaseDataObject) new DBaseDataActionRule(man,this,cls);
    }
    
}
