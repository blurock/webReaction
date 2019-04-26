/*
 * DBaseDataParameterizedFunction.java
 *
 * Created on February 27, 2001, 9:37 PM
 */

package blurock.opandalgs.parameterized;
import blurock.coreobjects.*;
import blurock.opandalgs.ops.*;
import blurock.core.*;
import javax.swing.*;

/**
 *
 * @author  reaction
 * @version 
 */
public class DBaseDataParameterizedFunction extends blurock.opandalgs.ops.DBaseDataOperation {

    /** Creates new DBaseDataParameterizedFunction */
    public DBaseDataParameterizedFunction(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
        super(man,obj,cls);
    }

    public JPanel objectAsPanel() {
        JPanel top = super.objectAsPanel();
        BaseDataParameterizedFunction func = (BaseDataParameterizedFunction) Object;
        DataParameterizedFunctionClass clsfunc = (DataParameterizedFunctionClass) OClass;
        DBaseDataObject dop = func.Operation.getDisplayObject(displayManager,
                                clsfunc.OperationClass);
        DBaseDataObject dset = func.ParameterSet.getDisplayObject(displayManager,
                                clsfunc.ParameterSetClass);
        JPanel op  = dop.objectAsPanel();
        JPanel set = dset.objectAsPanel();
        return new PanelParameterizedFunction(top,set,op);
    }
    
    public ObjectAsTreeNode objectAsSubTree(ObjectAsTreeNode topnode) {
        ObjectAsTreeNode objtop = super.objectAsSubTree(topnode);

        BaseDataParameterizedFunction func = (BaseDataParameterizedFunction) Object;
        DataParameterizedFunctionClass clsfunc = (DataParameterizedFunctionClass) OClass;
        DBaseDataObject dop = func.Operation.getDisplayObject(displayManager,
                                clsfunc.OperationClass);
        DBaseDataObject dset = func.ParameterSet.getDisplayObject(displayManager,
                                clsfunc.ParameterSetClass);

        ObjectAsTreeNode op = dop.objectAsSubTree(objtop);
        ObjectAsTreeNode set = dset.objectAsSubTree(objtop);
        return objtop;
    }
    
}
