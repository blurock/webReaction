/*
 * DBaseDataDecisionTreeNodeStats.java
 *
 * Created on September 17, 2004, 11:04 PM
 */

package blurock.DecisionTree;
import blurock.core.ObjectAsTreeNode;
import javax.swing.JPanel;
import blurock.core.ObjectDisplayManager;
import blurock.coreobjects.BaseDataObject;
import blurock.coreobjects.DataObjectClass;
import blurock.coreobjects.DBaseDataObject;
import blurock.DecisionTree.DecisionTreeNodeStatsPanel;
import blurock.DecisionTree.BaseDataDecisionTreeNodeSpec;

/**
 *
 * @author  reaction
 */
public class DBaseDataDecisionTreeNodeStats extends blurock.DecisionTree.DBaseDataDecisionTreeNodeSpec {
    
    /** Creates a new instance of DBaseDataDecisionTreeNodeStats */
    public DBaseDataDecisionTreeNodeStats(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
         super(man,obj,cls);
    }
    
    public JPanel objectAsPanel() {
        BaseDataDecisionTreeNodeStats nodespec = (BaseDataDecisionTreeNodeStats) Object;
        JPanel suppanel = super.objectAsPanel();
        DecisionTreeNodeStatsPanel panel = new DecisionTreeNodeStatsPanel(nodespec);
        panel.SuperPanel.add(suppanel,java.awt.BorderLayout.CENTER);
        return panel;
    }
    
    public ObjectAsTreeNode objectAsSubTree(ObjectAsTreeNode topnode) {
         return super.objectAsSubTree(topnode);
    }
}
