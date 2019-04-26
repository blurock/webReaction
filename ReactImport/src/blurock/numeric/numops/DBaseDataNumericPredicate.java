/*
 * DBaseDataPredicate.java
 *
 * Created on February 1, 2002, 10:49 PM
 */

package blurock.numeric.numops;
import blurock.core.ObjectAsTreeNode;
import javax.swing.JPanel;
import blurock.core.ObjectDisplayManager;
import blurock.coreobjects.DataObjectClass;
import blurock.coreobjects.BaseDataObject;
import blurock.numeric.numops.BaseDataFuncReal1D;
import blurock.numeric.numops.DataFuncReal1DClass;
import blurock.numeric.numops.DBaseDataFuncReal1D;

/**
 *
 * @author  reaction
 * @version 
 */
public class DBaseDataNumericPredicate extends blurock.opandalgs.ops.DBaseDataOperation {

    /** Creates new DBaseDataPredicate */
    public DBaseDataNumericPredicate(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
        super(man,obj,cls);
    }

    public JPanel objectAsPanel() {
        BaseDataNumericPredicate pred = (BaseDataNumericPredicate) Object;
        DataNumericPredicateClass predclass = (DataNumericPredicateClass) OClass;
        BaseDataFuncReal1D func = pred.PredicateFunction;
        DataFuncReal1DClass funclass = predclass.PredicateFunction;
        DBaseDataFuncReal1D dfunc = (DBaseDataFuncReal1D) func.getDisplayObject(displayManager,funclass);
        return dfunc.objectAsPanel();
        //return.objectAsPanel();
    }
    
    public ObjectAsTreeNode objectAsSubTree(ObjectAsTreeNode topnode) {
        return super.objectAsSubTree(topnode);
    }
    
}
