/*
 * BaseDataExpressionTree.java
 *
 * Created on February 28, 2001, 4:09 PM
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
public class BaseDataExpressionTree extends blurock.opandalgs.ops.BaseDataOperation {
    public BaseDataSetOfObjects Parameters;
    public String expressionAsString = "";
    private String parenChar = "(";
    private String endParenChar = ")";
    
    /** Creates new BaseDataExpressionTree */
    public BaseDataExpressionTree() {
    }

    public void Read(RWManager io) throws IOException {
        //Parameters = new BaseDataSetOfObjects();
        //ClassNamePairs ps = new ClassNamePairs(false);
        //ps.Read(io);
        //Parameters.Read(io,ps);
        
        StringBuffer accum = new StringBuffer();
        String paren = io.readElement();
        if(paren.equals(parenChar)) {
            accum.append(parenChar + " ");
            readFullExpression(io,accum);
        } else {
            throw new IOException("Expected begining of an expression (a left paren)\n but got '" 
                                + paren + "'");
        }
        expressionAsString = accum.toString();
    }
    private void readFullExpression(RWManager io, StringBuffer accum) throws IOException {
        String op = io.readElement();
        accum.append(op + " ");
        String ele = io.readElement();
        int count = 0;
        while(!ele.equals(endParenChar)) {
            if(ele.equals(parenChar)) {
                accum.append(parenChar + " ");
                readFullExpression(io,accum);
            } else {
                accum.append(ele + " ");
            }
            count++;
            if(count > 100) 
                throw new IOException("Infinite Expression:  something wrong");
            ele = io.readElement();
        }
        accum.append(endParenChar + " ");
    }
               
    public void Write(RWManager io) throws IOException {
        Parameters.Write(io);
        io.printLine(expressionAsString);
    }
    
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man,DataObjectClass cls) {
        return (DBaseDataObject) new DBaseDataExpressionTree(man,this,cls);
    }
    
}
