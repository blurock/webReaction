/*
 * DBaseDataPredicate.java
 *
 * Created on February 1, 2002, 10:49 PM
 */

package blurock.logic.predicates;
import blurock.core.ObjectAsTreeNode;
import javax.swing.JPanel;
import blurock.core.ObjectDisplayManager;
import blurock.coreobjects.DataObjectClass;
import blurock.coreobjects.BaseDataObject;
import blurock.opandalgs.ops.BaseDataOperation;
import blurock.opandalgs.ops.DataOperationClass;
import blurock.opandalgs.ops.DBaseDataOperation;
/**
 *
 * @author  reaction
 * @version 
 */
public class DBaseDataPredicate extends blurock.opandalgs.ops.DBaseDataOperation {

    /** Creates new DBaseDataPredicate */
    public DBaseDataPredicate(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
        super(man,obj,cls);
    }

    public JPanel objectAsPanel() {
        BaseDataPredicate pred =  (BaseDataPredicate) Object;
        DataPredicateClass predclass = (DataPredicateClass) OClass;
        BaseDataOperation op = pred.Operation;
        DataOperationClass opclass = predclass.OpClass;
        DBaseDataOperation dop = (DBaseDataOperation) op.getDisplayObject(displayManager,opclass);
        JPanel opPanel = dop.objectAsPanel();
        JPanel basePanel = super.objectAsPanel();
        
        PredicatePanel pPanel = new PredicatePanel(pred,basePanel,opPanel);
        
        return pPanel;
    }
    
    public ObjectAsTreeNode objectAsSubTree(ObjectAsTreeNode topnode) {
        return super.objectAsSubTree(topnode);
    }
    
}
