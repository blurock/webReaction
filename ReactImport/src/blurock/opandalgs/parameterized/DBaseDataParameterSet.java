/*
 * DBaseDataParameterSet.java
 *
 * Created on February 27, 2001, 6:30 PM
 */

package blurock.opandalgs.parameterized;
import blurock.coreobjects.*;
import blurock.core.*;
import javax.swing.*;

/**
 *
 * @author  reaction
 * @version 
 */
public class DBaseDataParameterSet extends blurock.coreobjects.DBaseDataSetOfObjects {

    /** Creates new DBaseDataParameterSet */
    public DBaseDataParameterSet(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
        super(man,obj,cls);
    }

    public JPanel objectAsPanel() {
        return super.objectAsPanel();
    }
    
    public ObjectAsTreeNode objectAsSubTree(ObjectAsTreeNode topnode) {
        return super.objectAsSubTree(topnode);
    }
    
}
