/*
 * DBaseDataClassObject.java
 *
 * Created on May 11, 2001, 2:41 PM
 */

package xml.structure;
import blurock.core.*;
    import javax.swing.*;
    import javax.swing.tree.*;
import blurock.coreobjects.*;

/**
 *
 * @author  reaction
 * @version 
 */
public class DBaseDataClassObject extends blurock.coreobjects.DBaseDataObject {

    /** Creates new DBaseDataClassObject */
    public DBaseDataClassObject(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
        super(man,obj,cls);
    }

    public JPanel objectAsPanel() {
        return new JPanel();
    }
    
    public ObjectAsTreeNode objectAsSubTree(ObjectAsTreeNode topnode) {
        return new ObjectAsTreeNode(Object.Name);
    }
    
}
