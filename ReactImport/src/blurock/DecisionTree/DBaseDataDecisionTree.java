/*
 * DBaseDataDecisionTree.java
 *
 * Created on September 17, 2004, 6:06 PM
 */

package blurock.DecisionTree;
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
public class DBaseDataDecisionTree extends blurock.EvaluationTree.DBaseDataEvaluationTree {
    
    /** Creates a new instance of DBaseDataDecisionTree */
    public DBaseDataDecisionTree(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
         super(man,obj,cls);
    }
    
    public JPanel objectAsPanel() {
        return super.objectAsPanel();
    }
    
    public ObjectAsTreeNode objectAsSubTree(ObjectAsTreeNode topnode) {
        return super.objectAsSubTree(topnode);
    }
    
}
