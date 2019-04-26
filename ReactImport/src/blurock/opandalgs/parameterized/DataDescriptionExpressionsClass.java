/*
 * DataDescriptionExpressionsClass.java
 *
 * Created on March 3, 2001, 9:09 PM
 */

package blurock.opandalgs.parameterized;
import blurock.core.RWManager;
import blurock.coreobjects.*;
import blurock.opandalgs.ops.*;
import java.io.IOException;

/**
 *
 * @author  reaction
 * @version 
 */
public class DataDescriptionExpressionsClass extends blurock.opandalgs.parameterized.DataParameterizedFunctionClass {

    public DataParameterSetClass ExpressionsClass;
    
    private DataSetOfObjectsClass ResultsClass;
    
    private DataObjectClass BaseClass;
    
    /** Creates new DataDescriptionExpressionsClass */
    public DataDescriptionExpressionsClass() {
    }
    public DataDescriptionExpressionsClass(int id, String type, String descr) {
        super(id,type,descr);
        SubClass = "GeneticObject";
    }

    public BaseDataObject BaseDataObjectExample() {
        BaseDataObject obj = (BaseDataObject) new BaseDataDescriptionExpressions();
        obj.Type = Name;
        return obj;
    }
    
    public DataObjectClass Clone() {
        DataDescriptionExpressionsClass cls = 
                new DataDescriptionExpressionsClass(Identification,Name,Description);
        cls.CopyClone(this);
       return cls;
    }
    
    public void CopyClone(DataObjectClass ocls) {
        super.CopyClone(ocls);
        DataDescriptionExpressionsClass cls = (DataDescriptionExpressionsClass) ocls;
        try {
            cls.ExpressionsClass = (DataParameterSetClass) ExpressionsClass.Clone();
            cls.ResultsClass = (DataSetOfObjectsClass) ExpressionsClass.Clone();
            cls.BaseClass = (DataObjectClass) ExpressionsClass.Clone();
        } catch(NullPointerException exp) {
            cls.ExpressionsClass = null;
            cls.ResultsClass = null;
            cls.BaseClass = null;
        }
    }
    
    public void Read(RWManager io) throws IOException {
        super.Read(io);
        ExpressionsClass = (DataParameterSetClass) io.getClassFromNextElement();
        ResultsClass = (DataSetOfObjectsClass) io.getClassFromNextElement();
        BaseClass = (DataObjectClass) io.getClassFromNextElement();
    }
    
    public void Write(RWManager io) throws IOException {
        super.Write(io);
    }
    
}
