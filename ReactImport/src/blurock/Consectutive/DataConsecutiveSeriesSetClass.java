/*
 * DataConsecutiveSeriesSetClass.java
 *
 * Created on November 5, 2003, 6:22 PM
 */

package blurock.Consectutive;
import java.util.*;
import blurock.core.*;
import blurock.coreobjects.*;
import java.io.IOException;

/**
 *
 * @author  reaction
 */
public class DataConsecutiveSeriesSetClass extends blurock.coreobjects.DataSetOfObjectsClass {
    
    public DataConsecutiveSeriesClass nodeClass;
    /** Creates a new instance of DataConsecutiveSeriesSetClass */
    public DataConsecutiveSeriesSetClass() {
    }
    public DataConsecutiveSeriesSetClass(int id, String type, String descr) {
        super(id,type,descr);
        SubClass = "SetOfObjects";
    }
    
    public BaseDataObject BaseDataObjectExample() {
        BaseDataConsecutiveSeriesSet obj = new BaseDataConsecutiveSeriesSet();
        obj.Name = new String(Name);
        return (BaseDataObject) obj;
    }
    
    public DataObjectClass Clone() {
        DataConsecutiveSeriesSetClass cls = new DataConsecutiveSeriesSetClass(Identification,Name,Description);
        cls.derivedClass = derivedClass;
        cls.SubClass = SubClass;
       return cls;
    }
    
    public void Read(RWManager io) throws IOException {
    }
    
    public void Write(RWManager io) throws IOException {
    }
    
}
