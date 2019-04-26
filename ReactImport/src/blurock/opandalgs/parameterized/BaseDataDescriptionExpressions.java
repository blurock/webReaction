/*
 * BaseDataDescriptionExpressions.java
 *
 * Created on March 3, 2001, 9:07 PM
 */

package blurock.opandalgs.parameterized;
import blurock.core.*;
import blurock.coreobjects.*;
import javax.swing.JPanel;
import blurock.opandalgs.ops.*;
import blurock.opandalgs.parameterized.*;
import blurock.utilities.TopObjectPanel;
import java.io.IOException;

/**
 *
 * @author  reaction
 * @version 
 */
public class BaseDataDescriptionExpressions extends blurock.opandalgs.parameterized.BaseDataParameterizedFunction {

    public BaseDataParameterSet Expressions;
    
    public boolean PostOperation;
    
    /** Creates new BaseDataDescriptionExpressions */
    public BaseDataDescriptionExpressions() {
    }

    public void Read(RWManager io) throws IOException {
        try {
        DataDescriptionExpressionsClass cls = (DataDescriptionExpressionsClass) 
                           io.dataClasses.findClass(Type);
        io.checkToken("DescriptionExpressions:");
        super.Read(io);
        io.checkToken("Post:");
        String post = io.readElement();
        String truepost = "PostOperation";
        String falsepost = "NoPostOperation";
        if(post.equals(truepost))
            PostOperation = true;
        else if(post.equals(falsepost))
            PostOperation = false;
        else {
            throw new IOException("Expected '" + truepost + "' or '" + falsepost + "'");
        }
        io.checkToken("BeginDescription:");
            Expressions = (BaseDataParameterSet) cls.ExpressionsClass.BaseDataObjectExample();
            Expressions.Name = "Description Expressions";
            Expressions.Read(io);
        io.checkToken("EndDescription:");
        } catch (NullPointerException nexp) {
            throw new IOException("Description Expressions Class not fully defined: '" + Type + "'");
        } catch (ObjectNotFoundException f) {
            throw new IOException("Description Expressions Class not registered: " + f);
        }
        
    }
    
    public void Write(RWManager io) throws IOException {
        io.printLine("DescriptionExpressions:");
        super.Write(io);
        io.printLine("Post:");
        if(PostOperation)
            io.printLine("PostOperation");
        else
            io.printLine("NoPostOperation");
        io.printLine("BeginDescription:");
        try {
            Expressions.Write(io);
        } catch (NullPointerException nexp) {
            throw new IOException("Description Expressions Class not fully defined: '" + Type + "'");
        }
        
        
    }
    
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man,DataObjectClass cls) {
        DBaseDataDescriptionExpressions dobj = 
            new DBaseDataDescriptionExpressions(man,this,cls);
        return dobj;
    }
    
}
