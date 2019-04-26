/*
 * DBaseDataClassClass.java
 *
 * Created on May 11, 2001, 2:43 PM
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
public class DBaseDataClassClass extends blurock.coreobjects.DBaseDataObject {

    /** Creates new DBaseDataClassClass */
    public DBaseDataClassClass(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
        super(man,obj,cls);
    }

    public JPanel objectAsPanel() {
       return new JPanel();
    }
    
    public ObjectAsTreeNode objectAsSubTree(ObjectAsTreeNode topnode) {
        return new ObjectAsTreeNode(Object.Name);
    }
    
}
