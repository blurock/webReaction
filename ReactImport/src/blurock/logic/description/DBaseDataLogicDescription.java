/*
 * DBaseDataLogicDescription.java
 *
 * Created on March 3, 2001, 6:12 PM
 */

package blurock.logic.description;
import blurock.core.ObjectDisplayManager;
import blurock.coreobjects.BaseDataObject;
import blurock.coreobjects.DataObjectClass;
import blurock.coreobjects.DBaseDataObject;
import blurock.core.ObjectAsTreeNode;
import javax.swing.JPanel;
import blurock.utilities.TopObjectPanel;

/**
 *
 * @author  reaction
 * @version 
 */
public class DBaseDataLogicDescription extends blurock.coreobjects.DBaseDataSetOfObjects {

    /** Creates new DBaseDataLogicDescription */
    public DBaseDataLogicDescription(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
        super(man,obj,cls);
    }

    public JPanel objectAsPanel() {
        JPanel top = super.objectAsPanel();
        return top;
    }
    
    public ObjectAsTreeNode objectAsSubTree(ObjectAsTreeNode topnode) {
        ObjectAsTreeNode objtop = super.objectAsSubTree(topnode);
        return objtop;
    }
    
}
