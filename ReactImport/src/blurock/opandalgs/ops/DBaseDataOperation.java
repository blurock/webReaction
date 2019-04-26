/*
 * DBaseDataOperation.java
 *
 * Created on February 27, 2001, 6:12 PM
 */

package blurock.opandalgs.ops;
import blurock.coreobjects.*;
import blurock.core.*;
import javax.swing.*;

/**
 *
 * @author  reaction
 * @version 
 */
public class DBaseDataOperation extends blurock.coreobjects.DBaseDataObject {

    /** Creates new DBaseDataOperation */
    public DBaseDataOperation(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
        super(man,obj,cls);
    }

    public ObjectAsTreeNode objectAsSubTree(ObjectAsTreeNode topnode) {
        return super.objectAsSubTree(topnode);
    }
    
    public JPanel objectAsPanel() {
        return super.objectAsPanel();
    }
    
}
