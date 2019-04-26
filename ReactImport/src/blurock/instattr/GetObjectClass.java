/*
 * GetObjectClass.java
 *
 * Created on March 11, 2001, 5:32 PM
 */

package blurock.instattr;
import react.common.*;
import blurock.core.RunCommand;
import java.util.StringTokenizer;

/**
 *
 * @author  reaction
 * @version 
 */
public class GetObjectClass extends blurock.instattr.GetAttributeParameter {

    /** Creates new GetObjectClass */
    public GetObjectClass(TopReactionMenu top) {
        super(top);
    }
    
    public boolean getParameterAsString(String name) {
        //String command = "WindowObject Class" + " " + name;
        String command = "Print Class" + " " + name;
        RunCommand runit = new RunCommand(Top,command,false);
        //runit.run();
        outputString = runit.commandOutput;
        return true;
        //tokenize = new StringTokenizer(runit.commandOutput,"\n");
	//outputOk = false;
        //return parseOutput();
    }

}
