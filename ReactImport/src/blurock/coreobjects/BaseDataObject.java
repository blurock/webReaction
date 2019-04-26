/*
 * BaseDataObject.java
 *
 * Created on February 22, 2001, 9:00 AM
 */

package blurock.coreobjects;
import blurock.core.*;
import java.io.IOException;
import java.util.Comparator;
/**
 *
 * @author  reaction
 * @version 
 */
public class BaseDataObject extends java.lang.Object implements Comparator {

    public String Name;
    
    public int Identification;
    
    public String Type = "Object";
    
    
    /** Creates new BaseDataObject */
    public BaseDataObject() {
        Name = "Object";
        Identification = 0;
    }
    public BaseDataObject(String name, int id) {
        Name = name;
        Identification = id;
    }
    public BaseDataObject Clone() {
        BaseDataObject obj = new BaseDataObject(Name,Identification);
        obj.Type = Type;
       return obj;         
    }
    
    public void CopyClone(BaseDataObject obj) {
        Name = obj.Name;
        Identification = obj.Identification;
        Type = obj.Type;
    }

    public DBaseDataObject getDisplayObject(ObjectDisplayManager man,DataObjectClass cls) {
        return new DBaseDataObject(man,this,cls);
    }
    public void Read(RWManagerBase io) throws IOException {
    }
    
    public void Write(RWManagerBase io) throws IOException {
        System.out.println("Write: BaseDataObject");
    }
    public int compare(Object o1, Object o2) {
        BaseDataObject obj1 = (BaseDataObject) o1;
        BaseDataObject obj2 = (BaseDataObject) o2;
        return obj1.Name.compareTo(obj2.Name);
    }
    public boolean equals(Object obj) {
        BaseDataObject object = (BaseDataObject) obj;
        return equals(object.Name);
    }
}
