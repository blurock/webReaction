/*
 * RWManagerFromReactLink.java
 *
 * Created on February 2, 2002, 12:07 PM
 */

package blurock.core;
import java.io.IOException;
import react.common.*;
import blurock.core.RunCommand;
import blurock.coreobjects.DataSetOfObjectsClass;
import blurock.core.RunCommand;
import java.util.StringTokenizer;

/**
 *
 * @author  reaction
 * @version 
 */
public class RWManagerFromReactLink extends blurock.core.RWManager {
    TopReactionMenu Top;

    public String commandOutput;

/** Creates new RWManagerFromReactLink */
    public RWManagerFromReactLink(TopReactionMenu top, DataSetOfObjectsClass cls) {
        super(cls);
        Top = top;
    }
    public void openManager(String command, boolean read)  throws IOException {
        if(read) {
            RunCommand run = new RunCommand(Top,command,true);
            tokens = new StringTokenizer(run.commandOutput,"\n");
        }
    }
    public void closeManager()  throws IOException {
    }
    
    public String readNextLine() throws IOException {
        String next = "";
        if(tokens != null) {
            next = tokens.nextToken();
        }
        return next;
    }
}
