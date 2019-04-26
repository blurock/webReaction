/*
 * DataExpressionTreeClass.java
 *
 * Created on February 28, 2001, 4:10 PM
 */

package blurock.opandalgs.expressions;
import blurock.coreobjects.*;
import blurock.core.*;
import javax.swing.*;
import java.io.IOException;

/**
 *
 * @author  reaction
 * @version 
 */
public class DataExpressionTreeClass extends blurock.opandalgs.ops.DataOperationClass {

    /** Creates new DataExpressionTreeClass */
    public DataExpressionTreeClass() {
    }
    public DataExpressionTreeClass(int id, String type, String descr) {
        super(id,type,descr);
        SubClass = "Operation";
    }

    public BaseDataObject BaseDataObjectExample() {
        BaseDataObject obj = (BaseDataObject) new BaseDataExpressionTree();
        obj.Type = Name;
        return obj;
     }
    
    public DataObjectClass Clone() {
        DataExpressionTreeClass cls = new DataExpressionTreeClass(Identification,Name,Description);
        cls.derivedClass = derivedClass;
        cls.SubClass = SubClass;
       return cls;
    }
    
    public void Read(RWManager io) throws IOException {
        super.Read(io);
    }
    
    public void Write(RWManager io) throws IOException {
        super.Write(io);
    }
    
}
