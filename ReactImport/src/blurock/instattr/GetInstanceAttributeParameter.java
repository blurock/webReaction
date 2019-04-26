/*
 * GetInstanceAttributeParameter.java
 *
 * Created on March 12, 2001, 5:44 PM
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
public class GetInstanceAttributeParameter extends blurock.instattr.GetAttributeParameter {

    /** Creates new GetInstanceAttributeParameter */
    public GetInstanceAttributeParameter(TopReactionMenu top) {
        super(top);
    }

    public boolean getParameterAsString(java.lang.String instname,java.lang.String attrname) {
        String command = Top.SystemCommands.InstanceList.getValue() + 
                " " + instname + " " + attrname;
        RunCommand runit = new RunCommand(Top,command,false);
        //runit.run();
        if(runit.commandOutput != null) {
            tokenize = new StringTokenizer(runit.commandOutput,"\n");
	    outputOk = false;
            return parseOutput();
        } else {
           outputString = new String("Instance Attribute not found");
           attributeType = new String("notfound");
           return false;
        }
}
    
}
