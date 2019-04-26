/*
 * DBaseDataConjunction.java
 *
 * Created on September 17, 2004, 7:15 PM
 */

package blurock.logic.description;
import blurock.core.ObjectAsTreeNode;
import javax.swing.JPanel;
import blurock.core.ObjectDisplayManager;
import blurock.coreobjects.BaseDataObject;
import blurock.coreobjects.DataObjectClass;
import blurock.coreobjects.DBaseDataObject;

/**
 *
 * @author  reaction
 */
public class DBaseDataConjunction extends blurock.opandalgs.ops.DBaseDataOperation {
    
    /** Creates a new instance of DBaseDataConjunction */
    public DBaseDataConjunction(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
         super(man,obj,cls);
    }
    
    public JPanel objectAsPanel() {
        BaseDataConjunction conj = (BaseDataConjunction) Object;
        DataConjunctionClass conjclass = (DataConjunctionClass) OClass;
        DBaseDataObject dconj = conj.LogicOperations.getDisplayObject(displayManager,conjclass.DescriptionClass);
        return dconj.objectAsPanel();
    }
    
    public ObjectAsTreeNode objectAsSubTree(ObjectAsTreeNode topnode) {
        BaseDataConjunction conj = (BaseDataConjunction) Object;
        DataConjunctionClass conjclass = (DataConjunctionClass) OClass;
        DBaseDataObject dconj = conj.LogicOperations.getDisplayObject(displayManager,conjclass.DescriptionClass);
        
        return dconj.objectAsSubTree(topnode);
    }
    
}
