/*
 * BaseDataReal.java
 *
 * Created on February 27, 2001, 4:12 PM
 */

package blurock.coreobjects;
import blurock.core.*;
import java.io.IOException;

/**
 *
 * @author  reaction
 * @version 
 */
public class BaseDataReal extends blurock.coreobjects.BaseDataNumeric {

    public double realValue = 0.0;
    /** Creates new BaseDataReal */
    public BaseDataReal() {
        Type = "Real";
    }

    public void Read(RWManagerBase io) throws IOException {
        String name = "";
        try {
            name = io.readElement();
            Double d = new Double(name);
            realValue = d.doubleValue();
        } catch(NumberFormatException exp) {
            throw new IOException("Expected a Real: '" + name + "'");
        }
    }
    public BaseDataObject Clone() {
        BaseDataReal r = new BaseDataReal();
        r.CopyClone(this);
        return r;
    }
    public void CopyClone(BaseDataObject o) {
        BaseDataReal r = (BaseDataReal) o;
        realValue = r.realValue;
        Type = r.Type;
    }
    public void Write(RWManagerBase io) throws IOException {
         String valueS = Double.toString(realValue);
         io.printString(" " + valueS + " ");
    }
    
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man,DataObjectClass cls) {
        return new DBaseDataReal(man,this,cls);
    }
    
}
