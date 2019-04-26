/*
 * DBaseDataPopulationObject.java
 *
 * Created on March 1, 2001, 9:49 PM
 */

package blurock.pop.base;
import blurock.core.ObjectDisplayManager;
import blurock.coreobjects.BaseDataObject;
import blurock.coreobjects.DataObjectClass;
import blurock.core.ObjectAsTreeNode;
import javax.swing.JPanel;

/**
 *
 * @author  reaction
 * @version 
 */
public class DBaseDataPopulationObject extends blurock.coreobjects.DBaseDataObject {

    /** Creates new DBaseDataPopulationObject */
    public DBaseDataPopulationObject(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
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
