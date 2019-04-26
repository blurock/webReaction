/*
 * DBaseDataSetOfLogicDescriptions.java
 *
 * Created on March 3, 2001, 6:17 PM
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
public class DBaseDataSetOfLogicDescriptions extends blurock.coreobjects.DBaseDataSetOfObjects {

    /** Creates new DBaseDataSetOfLogicDescriptions */
    public DBaseDataSetOfLogicDescriptions(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
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
