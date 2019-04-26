/*
 * DataObjectClass.java
 *
 * Created on February 22, 2001, 9:27 AM
 */

package blurock.coreobjects;
import blurock.core.*;
import java.io.IOException;

/**
 *
 * @author  reaction
 * @version 
 */
public class DataObjectClass extends BaseDataObject {

    public String SubClass = "Identify";
    public String Description = "Object Class";
    public boolean derivedClass = false;
    
    /** Creates new DataObjectClass */
    public DataObjectClass() {
    }
    public DataObjectClass(int id, String type, String descr) {
        Description = descr;
        Name = type;
        Type = type;
        Identification = id;
    }
    public void CopyClone(DataObjectClass cls) {
        super.CopyClone(cls);
        derivedClass = cls.derivedClass;
        SubClass = cls.SubClass;
    }

    public DataObjectClass Clone() {
        DataObjectClass cls = new DataObjectClass(Identification,Name,Description);
        cls.CopyClone(this);
       return cls; 
        
    }
    public BaseDataObject BaseDataObjectExample() {
        return new BaseDataObject();
    }
    public void Read(RWManagerBase io) throws IOException {
    }
    
    public void Write(RWManagerBase io) throws IOException {
        io.printString(" " + Name + " ");
    }
}
