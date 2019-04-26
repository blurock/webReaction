/*
 * DBaseDataObject.java
 *
 * Created on March 1, 2001, 6:21 PM
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
public class DBaseDataGeneticObject extends blurock.opandalgs.ops.DBaseDataOperation {

    /** Creates new DBaseDataObject */
    public DBaseDataGeneticObject(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
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
