/*
 * DBaseDataOptimizeCostFunction.java
 *
 * Created on March 1, 2001, 11:06 PM
 */

package blurock.opt.base;
import blurock.coreobjects.DBaseDataObject;
import blurock.core.ObjectDisplayManager;
import blurock.coreobjects.BaseDataObject;
import blurock.coreobjects.DataObjectClass;
import blurock.core.ObjectAsTreeNode;
import javax.swing.JPanel;
import blurock.utilities.TopObjectPanel;

/**
 *
 * @author  reaction
 * @version 
 */
public class DBaseDataOptimizeCostFunction extends blurock.opandalgs.ops.DBaseDataOperation {

    /** Creates new DBaseDataOptimizeCostFunction */
    public DBaseDataOptimizeCostFunction(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
        super(man,obj,cls);
    }

    public JPanel objectAsPanel() {
        JPanel top = super.objectAsPanel();
        BaseDataOptimizeCostFunction pop = (BaseDataOptimizeCostFunction) Object;
        DataOptimizeCostFunctionClass cls = (DataOptimizeCostFunctionClass) OClass;
        DBaseDataObject dset = pop.Parameters.getDisplayObject(displayManager,cls.ParameterClass);
        JPanel pset = dset.objectAsPanel();
        TopObjectPanel toppanel = new TopObjectPanel(pop.Name,top,pset);
        return toppanel;
    }
    
    public ObjectAsTreeNode objectAsSubTree(ObjectAsTreeNode topnode) {
        ObjectAsTreeNode objtop = super.objectAsSubTree(topnode);
        BaseDataOptimizeCostFunction pop = (BaseDataOptimizeCostFunction) Object;
        DataOptimizeCostFunctionClass cls = (DataOptimizeCostFunctionClass) OClass;
        DBaseDataObject dset = pop.Parameters.getDisplayObject(displayManager,cls.ParameterClass);
        dset.objectAsSubTree(objtop);
        return objtop;
    }
    
}
