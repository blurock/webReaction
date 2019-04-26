/*
 * DataDescriptionClass.java
 *
 * Created on September 17, 2004, 6:55 PM
 */

package blurock.logic.description;
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
public class DataDescriptionClass extends blurock.coreobjects.DataSetOfObjectsClass {
    
    /** Creates a new instance of DataDescriptionClass */
    public DataDescriptionClass() {
    }
    public DataDescriptionClass(int id, String type, String descr) {
        super(id,type,descr);
    }
    
    public BaseDataObject BaseDataObjectExample() {
        BaseDataObject obj = (BaseDataObject) new BaseDataDescription();
        obj.Type = this.Type;
        obj.CopyClone((DataObjectClass) this);
        return obj;
    }
    
    public DataObjectClass Clone() {
        DataObjectClass cls = new DataDescriptionClass(Identification,Name,Description);
        cls.CopyClone(this);
        return cls;
    }
    
    public void CopyClone(DataObjectClass cls) {
        super.CopyClone(cls);
    }
    
    public void Read(RWManagerBase io) throws IOException {
        super.Read(io);
    }
    
    public void Write(RWManagerBase io) throws IOException {
        super.Read(io);
    }
    
}
