/*
 * DataNValuedClass.java
 *
 * Created on March 2, 2001, 8:51 PM
 */

package blurock.logic.base;
import blurock.coreobjects.*;
import blurock.core.*;
import java.io.IOException;

/**
 *
 * @author  reaction
 * @version 
 */
public class DataNValuedClass extends blurock.logic.base.DataLogicalClass {
    String[] nValues;
    /** Creates new DataNValuedClass */
    public DataNValuedClass() {
    }
    public DataNValuedClass(int id, String type, String descr) {
        super(id,type,descr);
        SubClass = "Logical";
    }

    public BaseDataObject BaseDataObjectExample() {
        BaseDataObject obj = (BaseDataObject) new BaseDataNValued();
        obj.Type = Name;
        return obj;
    }
    
    public DataObjectClass Clone() {
        DataNValuedClass cls = new DataNValuedClass(Identification,Name,Description);
        cls.CopyClone(this);
       return cls;
    }
    
    public void CopyClone(DataObjectClass cls) {
        super.CopyClone(cls);
    }
    
    public void Read(RWManager io) throws IOException {
        super.Read(io);
        String endString = new String("END");
        String[] names = new String[200];
        String name = io.readElement();
        int count = 0;
        while(!name.equals(endString)) {
            System.out.println("Add NValued: '" + name + "'");
            names[count] = name;
            count++;
            String value = io.readElement();
            name = io.readElement();
        }
        nValues = new String[count];
        for(int i=0;i<count;i++) {
            nValues[i] = names[i];
        }
    }
    public String asString() {
        StringBuffer buf = new StringBuffer();
        for(int i=0;i<nValues.length;i++) {
            buf.append(nValues[i]+ " ");
        }
        buf.append("END");
        return buf.toString();
    }
        
    public void Write(RWManager io) throws IOException {
        super.Write(io);
        io.printLine(asString());
    }
    
}
