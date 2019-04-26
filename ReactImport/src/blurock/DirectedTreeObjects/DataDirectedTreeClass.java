/*
 * DataDirectedTreeClass.java
 *
 * Created on September 16, 2004, 3:31 PM
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
public class DataDirectedTreeClass extends blurock.coreobjects.DataSetOfObjectsClass {
    
    /** Creates a new instance of DataDirectedTreeClass */
    public DataDirectedTreeClass() {
    }
    public DataDirectedTreeClass(int id, String type, String descr) {
        super(id,type,descr);
    }
    
    public void Read(RWManagerBase io) throws IOException {
    }
    
    public void Write(RWManagerBase io) throws IOException {
    }
        
    public BaseDataObject BaseDataObjectExample() {
        BaseDataObject obj = (BaseDataObject) new BaseDataDirectedTree();
        obj.Type = this.Type;
        return obj;
     }
    
    public DataObjectClass Clone() {
        DataObjectClass cls = new DataDirectedTreeClass(Identification,Name,Description);
        cls.CopyClone(this);
       return cls;         
    }
    
    public void CopyClone(DataObjectClass cls) {
        super.CopyClone(cls);
    }
    
}
