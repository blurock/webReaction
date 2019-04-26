/*
 * RWManagerString.java
 *
 * Created on August 10, 2004, 4:20 PM
 */

package blurock.core;
import java.io.StringWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.NoSuchElementException;
import blurock.coreobjects.DataSetOfObjectsClass;
import java.io.PrintWriter;
/**
 *
 * @author  reaction
 */
public class RWManagerString extends blurock.core.RWManagerBase {
    
    private String inputString = null;
    StringTokenizer inputTokenizer;
    StringWriter strWriter;
    PrintWriter outStream;
    /** Creates a new instance of RWManagerString */
    public RWManagerString(DataSetOfObjectsClass cls,String input) {
        super(cls);
        inputString = input;
        inputTokenizer = new StringTokenizer(inputString,"\n");
    }
    public void openOutputFile() {
        reading = false;
        strWriter = new StringWriter();
        outStream = new PrintWriter(strWriter);
    }
    public String readNextLine() throws IOException {
        String line = "";
        try {
            line = inputTokenizer.nextToken();
            if(line.startsWith("%"))
                line = readNextLine();
        } catch(NoSuchElementException ex) {
            throw new IOException(ex.toString());
        }
        
        return line;
    }
    public String toString() {
        return strWriter.getBuffer().toString();
    }
    public void printLine(String line) {
        outStream.println(line);
    }
    public void printString(String line) {
        outStream.print(line);
    }    
}
