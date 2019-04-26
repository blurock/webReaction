/*
 * DBaseDataClassInfo.java
 *
 * Created on May 13, 2001, 9:27 AM
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
public class DBaseDataClassInfo extends blurock.coreobjects.DBaseDataObject {

    /** Creates new DBaseDataClassInfo */
    public DBaseDataClassInfo(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
        super(man,obj,cls);
    }

    public JPanel objectAsPanel() {
        BaseDataClassInfo info = (BaseDataClassInfo) Object;
        ClassInfoPanel pan = new ClassInfoPanel(info);
        return pan;
    }
    
    public ObjectAsTreeNode objectAsSubTree(ObjectAsTreeNode topnode) {
        return new ObjectAsTreeNode(Object.Name);
    }
    
}
