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
import blurock.numeric.numops.*;

/**
 *
 * @author  reaction
 * @version 
 */
public class DBaseDataPyramidFunction extends blurock.numeric.numops.DBaseDataFuncReal1D {

    /** Creates new DBaseDataFuncReal1D */
    public DBaseDataPyramidFunction(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
        super(man,obj,cls);
    }

    public JPanel objectAsPanel() {
        BaseDataPyramidFunction pyr = (BaseDataPyramidFunction) Object;
        PyramidFunctionPanel panel = new PyramidFunctionPanel(pyr, super.objectAsPanel());
        return panel;
    }
    
    public ObjectAsTreeNode objectAsSubTree(ObjectAsTreeNode topnode) {
        return super.objectAsSubTree(topnode);
    }
    
}
