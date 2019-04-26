/*
 * DBaseDataTreeNode.java
 *
 * Created on September 16, 2004, 4:23 PM
 */

package blurock.DirectedTreeObjects;
import blurock.core.ObjectAsTreeNode;
import blurock.core.ObjectDisplayManager;
import blurock.coreobjects.BaseDataObject;
import blurock.coreobjects.DataObjectClass;
import blurock.coreobjects.DBaseDataObject;

/**
 *
 * @author  reaction
 */
public class DBaseDataTreeNode extends blurock.coreobjects.DBaseDataObject {
    
    /** Creates a new instance of DBaseDataTreeNode */
    public DBaseDataTreeNode(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
        super(man,obj,cls);
    }
    
    public ObjectAsTreeNode objectAsSubTree(ObjectAsTreeNode topnode) {
        return super.objectAsSubTree(topnode);
    }
    
}
