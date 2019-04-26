/*
 * DBaseDataPopulationCost.java
 *
 * Created on March 1, 2001, 11:13 PM
 */

package blurock.pop.base;
import blurock.core.ObjectDisplayManager;
import blurock.coreobjects.BaseDataObject;
import blurock.coreobjects.DataObjectClass;
import blurock.coreobjects.DBaseDataObject;
import blurock.core.ObjectAsTreeNode;
import javax.swing.JPanel;
import blurock.opandalgs.ops.*;
import blurock.opandalgs.parameterized.*;
import blurock.utilities.TopObjectPanel;

/**
 *
 * @author  reaction
 * @version 
 */
public class DBaseDataPopulationCost extends blurock.opt.base.DBaseDataOptimizeCostFunction {

    /** Creates new DBaseDataPopulationCost */
    public DBaseDataPopulationCost(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
        super(man,obj,cls);
    }

    public JPanel objectAsPanel() {
        JPanel top = super.objectAsPanel();
        BaseDataPopulationCost pop = (BaseDataPopulationCost) Object;
        DataPopulationCostClass cls = (DataPopulationCostClass) OClass;
        DBaseDataObject dexp = pop.Expression.getDisplayObject(displayManager,cls.ExpressionClass);
        JPanel pexp = dexp.objectAsPanel();
        TopObjectPanel toppanel = new TopObjectPanel(pop.Name,top,pexp);
        return toppanel;
    }
    
    public ObjectAsTreeNode objectAsSubTree(ObjectAsTreeNode topnode) {
        ObjectAsTreeNode objtop = super.objectAsSubTree(topnode);
        BaseDataPopulationCost pop = (BaseDataPopulationCost) Object;
        DataPopulationCostClass cls = (DataPopulationCostClass) OClass;
        DBaseDataObject dexp = pop.Expression.getDisplayObject(displayManager,cls.ExpressionClass);
        dexp.objectAsSubTree(objtop);
        return objtop;
    }
    
}
