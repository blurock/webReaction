/*
 * DBaseDataDescription.java
 *
 * Created on September 17, 2004, 6:56 PM
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
public class DBaseDataDescription extends blurock.coreobjects.DBaseDataSetOfObjects {
    
    /** Creates a new instance of DBaseDataDescription */
    public DBaseDataDescription(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
        super(man,obj,cls);
    }
    
    public JPanel objectAsPanel() {
        return super.objectAsPanel();
    }
    
    public ObjectAsTreeNode objectAsSubTree(ObjectAsTreeNode topnode) {
        return super.objectAsSubTree(topnode);
    }
    
}
