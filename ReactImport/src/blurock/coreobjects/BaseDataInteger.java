/*
 * BaseDataInteger.java
 *
 * Created on February 27, 2001, 4:11 PM
 */

package blurock.coreobjects;
import blurock.core.*;
import java.io.IOException;

/**
 *
 * @author  reaction
 * @version 
 */
public class BaseDataInteger extends blurock.coreobjects.BaseDataNumeric {

    public int integerValue = 0;
    /** Creates new BaseDataInteger */
    public BaseDataInteger() {
    }

    public void Read(RWManagerBase io) throws IOException {
        String name = "";
        try {
            name = io.readElement();
            Integer d = new Integer(name);
            integerValue = d.intValue();
        } catch(NumberFormatException exp) {
            throw new IOException("Expected an Integer: '" + name + "'");
        }
     }
    
    public void Write(RWManagerBase io) throws IOException {
        System.out.println("Write: BaseDataInteger");
            io.printLine(" " + Integer.toString(integerValue) + " ");
    }
    
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man,DataObjectClass cls) {
        return new DBaseDataInteger(man,this,cls);
    }
    
}
