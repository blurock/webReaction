/*
 * DataConsectutiveSeriesClass.java
 *
 * Created on November 5, 2003, 5:33 PM
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
public class DataConsecutiveSeriesClass extends blurock.coreobjects.DataSetOfObjectsClass {
    
    /** Creates a new instance of DataConsectutiveSeriesClass */
    public DataConsecutiveSeriesClass() {
    }
    public DataConsecutiveSeriesClass(int id, String type, String descr) {
        super(id,type,descr);
        SubClass = "SetOfObjects";
    }
    public DataObjectClass Clone() {
        DataConsecutiveSeriesClass cls = new DataConsecutiveSeriesClass(Identification,Name,Description);
        cls.derivedClass = derivedClass;
        cls.SubClass = SubClass;
       return cls;
    }
   
    public BaseDataObject BaseDataObjectExample() {
        BaseDataConsecutiveSeries obj = new BaseDataConsecutiveSeries();
        obj.Name = new String(Name);
        return (BaseDataObject) obj;
     }
    
    public void Read(RWManager io) throws IOException {
    }
    
    public void Write(RWManager io) throws IOException {
    }
    
}
