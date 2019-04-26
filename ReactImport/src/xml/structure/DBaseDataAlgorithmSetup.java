/*
 * DBaseDataAlgorithmSetup.java
 *
 * Created on July 3, 2001, 4:01 PM
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
public class DBaseDataAlgorithmSetup extends blurock.coreobjects.DBaseDataObject {

    /** Creates new DBaseDataAlgorithmSetup */
    public DBaseDataAlgorithmSetup(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
        super(man,obj,cls);
    }

    public JPanel objectAsPanel() {
        BaseDataAlgorithmSetup setup = (BaseDataAlgorithmSetup) Object;
        AlgorithmSetupPanel setuppanel = new AlgorithmSetupPanel(setup);
        return setuppanel;
    }
    
    public ObjectAsTreeNode objectAsSubTree(ObjectAsTreeNode topnode) {
        return new ObjectAsTreeNode(Object.Name);
    }
    
}
