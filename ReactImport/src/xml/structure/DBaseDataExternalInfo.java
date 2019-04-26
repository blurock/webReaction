/*
 * DBaseDataExternalInfo.java
 *
 * Created on June 26, 2001, 10:31 AM
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
public class DBaseDataExternalInfo extends blurock.coreobjects.DBaseDataObject {

    /** Creates new DBaseDataExternalInfo */
    public DBaseDataExternalInfo(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
        super(man,obj,cls);
    }

    public JPanel objectAsPanel() {
        BaseDataExternalInfo info = (BaseDataExternalInfo) Object;
        ExternalInfoPanel pan = new ExternalInfoPanel(info);
        return pan;
    }
    
    public ObjectAsTreeNode objectAsSubTree(ObjectAsTreeNode topnode) {
        return new ObjectAsTreeNode(Object.Name);
    }
    
}
