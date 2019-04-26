/*
 * DBaseDataDecisionTreeNodeSpec.java
 *
 * Created on September 17, 2004, 8:38 PM
 */

package blurock.DecisionTree;
import blurock.logic.description.BaseDataConjunction;
import blurock.logic.description.DataConjunctionClass;
import blurock.logic.description.DBaseDataConjunction;
import blurock.coreobjects.BaseDataKeyWords;
//import blurock.coreobjects.DBaseDataKeyWords;
//import blurock.coreobjects.DataKeyWordsClass;
import blurock.core.ObjectAsTreeNode;
import javax.swing.JPanel;
import blurock.core.ObjectDisplayManager;
import blurock.coreobjects.BaseDataObject;
import blurock.coreobjects.DataObjectClass;
import blurock.coreobjects.DBaseDataObject;
import blurock.core.RWManagerBase;
import java.io.IOException;


/**
 *
 * @author  reaction
 */
public class DBaseDataDecisionTreeNodeSpec extends blurock.DirectedTreeObjects.DBaseDataTreeNode {
    
    /** Creates a new instance of DBaseDataDecisionTreeNodeSpec */
    public DBaseDataDecisionTreeNodeSpec(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
        super(man,obj,cls);
    }
    
    public ObjectAsTreeNode objectAsSubTree(ObjectAsTreeNode topnode) {
        BaseDataDecisionTreeNodeSpec nodespec = (BaseDataDecisionTreeNodeSpec) Object;
        DataDecisionTreeNodeSpecClass nodeclass = (DataDecisionTreeNodeSpecClass) OClass;
        ObjectAsTreeNode supnode = super.objectAsSubTree(topnode);
        
        DataConjunctionClass conjC = nodeclass.ConjunctionClass;
        BaseDataConjunction conj = nodespec.Conjunction;
        //DataKeyWordsClas partitionC = nodeclass.PartitionClass;
        
        DBaseDataObject conjD = conj.getDisplayObject(displayManager, conjC);
        //DBaseDataObject partitionD = nodespec.getDisplayObject(displayManager,partitionC);
        
        //ObjectAsTreeNode conjnode = conjD.objectAsSubTree(supnode);
        //partitionD.objectAsSubTree(topnode);
        return supnode;
    }
    
    public JPanel objectAsPanel() {
        BaseDataDecisionTreeNodeSpec nodespec = (BaseDataDecisionTreeNodeSpec) Object;
        DataDecisionTreeNodeSpecClass nodeclass = (DataDecisionTreeNodeSpecClass) OClass;
        DataConjunctionClass conjC = nodeclass.ConjunctionClass;
        JPanel panel = new JPanel();
        panel.setLayout(new java.awt.BorderLayout());
        
        DBaseDataObject conjD = nodespec.Conjunction.getDisplayObject(displayManager, conjC);
        JPanel conjP = conjD.objectAsPanel();
        JPanel superP = super.objectAsPanel();
        panel.add(conjP,java.awt.BorderLayout.CENTER);
        panel.add(superP,java.awt.BorderLayout.NORTH);
        return panel;
    }
    
}
