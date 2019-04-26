/*
 * DBaseDataNumericPredicate.java
 *
 * Created on September 21, 2004, 9:57 AM
 */

package blurock.logic.ops;
import blurock.core.ObjectAsTreeNode;
import blurock.core.ObjectDisplayManager;
import blurock.coreobjects.BaseDataObject;
import blurock.coreobjects.DataObjectClass;
import blurock.coreobjects.DBaseDataObject;
import javax.swing.JPanel;
/**
 *
 * @author  reaction
 */
public class DBaseDataNumericPredicate extends blurock.opandalgs.ops.DBaseDataOperation {
    
    /** Creates a new instance of DBaseDataNumericPredicate */
    public DBaseDataNumericPredicate(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
        super(man,obj,cls);
    }
    
    public JPanel objectAsPanel() {
        return super.objectAsPanel();
    }
    
    public ObjectAsTreeNode objectAsSubTree(ObjectAsTreeNode topnode) {
        return super.objectAsSubTree(topnode);
    }
}
