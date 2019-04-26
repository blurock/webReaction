/*
 * DBaseDataDirectedTree.java
 *
 * Created on September 16, 2004, 3:33 PM
 */

package blurock.DirectedTreeObjects;
import blurock.core.ObjectAsTreeNode;
import javax.swing.JPanel;
import blurock.core.ObjectDisplayManager;
import blurock.coreobjects.BaseDataObject;
import blurock.coreobjects.DataObjectClass;
import blurock.coreobjects.DBaseDataObject;
import graph.DrawGraph;
import graph.DrawGraphNode;
import java.util.Vector;
import blurock.core.ObjectNotFoundException;
/**
 *
 * @author  reaction
 */
public class DBaseDataDirectedTree extends blurock.coreobjects.DBaseDataSetOfObjects {
    
    /** Creates a new instance of DBaseDataDirectedTree */
    public DBaseDataDirectedTree(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
        super(man,obj,cls);
    }
    
    public JPanel objectAsPanel() {
        return super.objectAsPanel();
    }
    
    public ObjectAsTreeNode objectAsSubTree(ObjectAsTreeNode topnode) {
        BaseDataDirectedTree tree = (BaseDataDirectedTree) Object;
        //ObjectAsTreeNode objtop = super.objectAsSubTree(topnode);
        addSons(topnode,tree.rootNode);
        return topnode;
    }
        
    public ObjectAsTreeNode addSons(ObjectAsTreeNode topnode, String parent) {
        System.out.println("addSons: " + parent);
        BaseDataDirectedTree tree = (BaseDataDirectedTree) Object;
        DrawGraph g = tree.Graph;
        try {
            int index = g.getNode(parent);
            DrawGraphNode node = (DrawGraphNode) g.Nodes.elementAt(index);
            BaseDataTreeNode stats = (BaseDataTreeNode) tree.findObject(node.getNameTag());
            
            System.out.println("Node: " + stats.Name + "   index=" + index);
            DataTreeNodeClass nodeclass = (DataTreeNodeClass) displayManager.dataClasses.findClass(stats.Type);
            DBaseDataObject dobj = stats.getDisplayObject(displayManager,nodeclass);
            
            ObjectAsTreeNode topsubnode = dobj.objectAsSubTree(topnode);
            Vector sons = g.getSons(parent);
            for(int i=0; i< sons.size();i++) {
                 addSons(topsubnode, (String) sons.elementAt(i));
              }
        } catch(ObjectNotFoundException ex) {
            System.out.println(ex);
        }        
        //return super.objectAsSubTree(topnode);
        return topnode;
    }
    
    
}
