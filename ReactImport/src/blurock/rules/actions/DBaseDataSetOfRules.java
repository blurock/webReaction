/*
 * DBaseDataSetOfRules.java
 *
 * Created on February 28, 2001, 11:44 AM
 */

package blurock.rules.actions;
import blurock.opandalgs.parameterized.*;
import blurock.coreobjects.*;
import blurock.core.*;
import javax.swing.*;

/**
 *
 * @author  reaction
 * @version 
 */
public class DBaseDataSetOfRules extends blurock.opandalgs.parameterized.DBaseDataParameterSet {

    /** Creates new DBaseDataSetOfRules */
    public DBaseDataSetOfRules(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
        super(man,obj,cls);
    }

    public JPanel objectAsPanel() {
        return super.objectAsPanel();
    }
    
    public ObjectAsTreeNode objectAsSubTree(ObjectAsTreeNode topnode) {
        return super.objectAsSubTree(topnode);
    }
    
}
