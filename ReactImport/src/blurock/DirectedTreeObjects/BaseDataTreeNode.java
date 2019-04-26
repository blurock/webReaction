/*
 * Class.java
 *
 * Created on September 16, 2004, 4:08 PM
 */

package blurock.DirectedTreeObjects;
import blurock.DirectedTreeObjects.DBaseDataTreeNode;
import blurock.core.RWManagerBase;
import java.io.IOException;
import blurock.core.ObjectDisplayManager;
import blurock.coreobjects.BaseDataObject;
import blurock.coreobjects.DataObjectClass;
import blurock.coreobjects.DBaseDataObject;

/**
 *
 * @author  reaction
 */
public class BaseDataTreeNode extends blurock.coreobjects.BaseDataObject {
    
    /** Creates a new instance of Class */
    public BaseDataTreeNode() {
        Name = "TreeNode";
        Identification = 0;
    }
    
    public void Read(RWManagerBase io) throws IOException {
    }
    
    public void Write(RWManagerBase io) throws IOException {
    }
    
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man, DataObjectClass cls) {
        return new DBaseDataTreeNode(man,this,cls);
    }
    
}
