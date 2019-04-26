/*
 * DataTreeNodeClass.java
 *
 * Created on September 16, 2004, 4:22 PM
 */

package blurock.DirectedTreeObjects;
import blurock.DirectedTreeObjects.BaseDataTreeNode;
import blurock.coreobjects.BaseDataObject;
import blurock.coreobjects.DataObjectClass;
import blurock.coreobjects.DBaseDataObject;
import blurock.core.RWManagerBase;
import java.io.IOException;

/**
 *
 * @author  reaction
 */
public class DataTreeNodeClass extends DataObjectClass {
    
    /** Creates a new instance of DataTreeNodeClass */
    public DataTreeNodeClass() {
    }
    public DataTreeNodeClass(int id, String type, String descr) {
        super(id,type,descr);
    }
    public BaseDataObject BaseDataObjectExample() {
        BaseDataObject obj = (BaseDataObject) new BaseDataTreeNode();
        obj.Type = this.Type;
        return obj;
    }
    
    public DataObjectClass Clone() {
        DataObjectClass cls = new DataTreeNodeClass(Identification,Name,Description);
        cls.CopyClone(this);
       return cls;         
    }
    
    public void CopyClone(DataObjectClass cls) {
        super.CopyClone(cls);
    }
    
    public void Read(RWManagerBase io) throws IOException {
    }
    
    public void Write(RWManagerBase io) throws IOException {
    }
    
}
