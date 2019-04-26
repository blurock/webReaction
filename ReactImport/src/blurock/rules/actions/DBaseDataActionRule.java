/*
 * DBaseDataActionRule.java
 *
 * Created on February 28, 2001, 9:15 AM
 */

package blurock.rules.actions;
import blurock.opandalgs.parameterized.*;
import blurock.coreobjects.*;
import blurock.opandalgs.ops.*;
import blurock.core.*;
import javax.swing.*;

/**
 *
 * @author  reaction
 * @version 
 */
public class DBaseDataActionRule extends blurock.opandalgs.parameterized.DBaseDataParameterizedFunction {

    /** Creates new DBaseDataActionRule */
    public DBaseDataActionRule(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
        super(man,obj,cls);
    }

    public JPanel objectAsPanel() {
        JPanel top = super.objectAsPanel();
        BaseDataActionRule rule = (BaseDataActionRule) Object;
        DataActionRuleClass rulecls = (DataActionRuleClass) OClass;
        DBaseDataObject dcond = 
            rule.Condition.getDisplayObject(displayManager,rulecls.ConditionClass);
        DBaseDataObject dact = 
            rule.Actions.getDisplayObject(displayManager,rulecls.ActionsClass);
        JPanel pcond = dcond.objectAsPanel();
        JPanel pact  = dact.objectAsPanel();
        return new PanelActionRule(pcond,pact,rule.cutOff);
    }
    
    public ObjectAsTreeNode objectAsSubTree(ObjectAsTreeNode topnode) {
        ObjectAsTreeNode objtop = super.objectAsSubTree(topnode);
        BaseDataActionRule rule = (BaseDataActionRule) Object;
        DataActionRuleClass rulecls = (DataActionRuleClass) OClass;
        DBaseDataObject dcond = 
            rule.Condition.getDisplayObject(displayManager,rulecls.ConditionClass);
        DBaseDataObject dact = 
            rule.Actions.getDisplayObject(displayManager,rulecls.ActionsClass);
        ObjectAsTreeNode cond = dcond.objectAsSubTree(objtop);
        ObjectAsTreeNode act = dact.objectAsSubTree(objtop);
        return objtop;
    }
    
}
