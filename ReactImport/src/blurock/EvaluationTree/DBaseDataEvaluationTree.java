/*
 * DBaseDataEvaluationTree.java
 *
 * Created on September 16, 2004, 5:41 PM
 */

package blurock.EvaluationTree;
import blurock.core.ObjectAsTreeNode;
import javax.swing.JPanel;
import blurock.core.ObjectDisplayManager;
import blurock.coreobjects.BaseDataObject;
import blurock.coreobjects.DataObjectClass;
import blurock.coreobjects.DBaseDataObject;
import blurock.DirectedTreeObjects.BaseDataDirectedTree;
import blurock.DirectedTreeObjects.DataDirectedTreeClass;
/**
 *
 * @author  reaction
 */
public class DBaseDataEvaluationTree extends blurock.coreobjects.DBaseDataObject {
    
    /** Creates a new instance of DBaseDataEvaluationTree */
    public DBaseDataEvaluationTree(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
         super(man,obj,cls);
    }
    
    public JPanel objectAsPanel() {
        BaseDataEvaluationTree tree = (BaseDataEvaluationTree) Object;
        BaseDataDirectedTree dirtree = tree.Tree;
        DataEvaluationTreeClass treeclass = (DataEvaluationTreeClass) OClass;
        DBaseDataObject treeobj = dirtree.getDisplayObject(displayManager,treeclass.TreeClass);
        return treeobj.objectAsPanel();
    }
    
    public ObjectAsTreeNode objectAsSubTree(ObjectAsTreeNode topnode) {
        BaseDataEvaluationTree tree = (BaseDataEvaluationTree) Object;
        DBaseDataObject  display = tree.getDisplayObject(displayManager, OClass);
        return display.objectAsSubTree(topnode);
    }
    
}
