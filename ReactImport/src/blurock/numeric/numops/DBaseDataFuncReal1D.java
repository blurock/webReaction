/*
 * DBaseDataFuncReal1D.java
 *
 * Created on February 2, 2002, 10:11 AM
 */

package blurock.numeric.numops;
//import blurock.coreobjects.*;
import blurock.core.*;
import java.io.IOException;
import javax.swing.JPanel;
import blurock.core.ObjectDisplayManager;
import blurock.coreobjects.DataObjectClass;
import blurock.coreobjects.BaseDataObject;
import blurock.coreobjects.DBaseDataObject;

/**
 *
 * @author  reaction
 * @version 
 */
public class DBaseDataFuncReal1D extends blurock.coreobjects.DBaseDataObject {

    /** Creates new DBaseDataFuncReal1D */
    public DBaseDataFuncReal1D(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
        super(man,obj,cls);
    }

    public JPanel objectAsPanel() {
        return super.objectAsPanel();
    }
    
    public ObjectAsTreeNode objectAsSubTree(ObjectAsTreeNode topnode) {
        return super.objectAsSubTree(topnode);
    }
    
}
