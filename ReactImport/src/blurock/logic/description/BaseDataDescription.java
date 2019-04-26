/*
 * BaseDataDescription.java
 *
 * Created on September 17, 2004, 6:47 PM
 */

package blurock.logic.description;
import blurock.core.RWManagerBase;
import graph.DrawGraph;
import graph.DrawGraphNode;
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
public class BaseDataDescription extends blurock.coreobjects.BaseDataSetOfObjects {
    
    /** Creates a new instance of BaseDataDescription */
    public BaseDataDescription() {
        Type = "Description";
        Name = "Description";
    }
    
    public void Read(RWManagerBase io) throws IOException {
        io.checkToken("Description:");
        super.Read(io);
    }
    
    public void Write(RWManagerBase io) throws IOException {
        io.printLine("Description:");
        super.Write(io);
    }
    
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man, DataObjectClass cls) {
        return new DBaseDataDescription(man,this,cls);
    }
    
    public BaseDataObject Clone() {
        BaseDataObject cls = (BaseDataObject)  new BaseDataDescription();
        cls.CopyClone(this);
       return cls;         
    }
    
    public void CopyClone(BaseDataObject obj) {
        super.CopyClone(obj);
    }
    
}
