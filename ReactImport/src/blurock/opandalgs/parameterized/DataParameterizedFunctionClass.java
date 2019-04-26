/*
 * DataParameterizedFunctionClass.java
 *
 * Created on February 27, 2001, 9:35 PM
 */

package blurock.opandalgs.parameterized;
import blurock.coreobjects.*;
import blurock.opandalgs.ops.*;
import blurock.core.*;
import javax.swing.*;
import java.io.IOException;

/**
 *
 * @author  reaction
 * @version 
 */
public class DataParameterizedFunctionClass extends blurock.opandalgs.ops.DataOperationClass {

    public DataParameterSetClass ParameterSetClass = null;
    
    public DataOperationClass OperationClass = null;
    
    public DataObjectClass OutputClass = null;
    
    /** Creates new DataParameterizedFunctionClass */
    public DataParameterizedFunctionClass() {
    }
    public DataObjectClass Clone() {
        DataParameterizedFunctionClass cls = 
                new DataParameterizedFunctionClass(Identification,Name,Description);
        cls.derivedClass = derivedClass;
        cls.SubClass = SubClass;
        try {
            cls.ParameterSetClass = (DataParameterSetClass) ParameterSetClass.Clone();
            cls.OperationClass = (DataOperationClass) OperationClass.Clone();
            cls.OutputClass = (DataObjectClass) OutputClass.Clone();
        } catch(NullPointerException exp) {
            cls.ParameterSetClass = null;
            cls.OperationClass = null;
            cls.OutputClass = null;
        }
       return cls;
    }
    
    public DataParameterizedFunctionClass(int id, String type, String descr) {
        super(id,type,descr);
        SubClass = "Operation";
    }

    public BaseDataObject BaseDataObjectExample() {
        BaseDataObject obj = (BaseDataObject) new BaseDataParameterizedFunction();
        obj.Type = Name;
        return obj;
    }
    
    public void Read(RWManager io) throws IOException {
        ParameterSetClass = (DataParameterSetClass) io.getClassFromNextElement();
        OperationClass    = (DataOperationClass) io.getClassFromNextElement();
        OutputClass       = (DataObjectClass) io.getClassFromNextElement();
    }
    
    public void Write(RWManager io) throws IOException {
        try {
            io.printLine(ParameterSetClass.Name + " " +
                        OperationClass.Name + " " + 
                        OutputClass.Name);
        } catch (NullPointerException nexp) {
            throw new IOException("ParameterizedFunction Class not fully defined");
        }
    }
    
}
