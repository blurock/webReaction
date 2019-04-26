/*
 * GetAttributeParameter.java
 *
 * Created on February 15, 2001, 8:09 PM
 */

package blurock.instattr;
import react.common.*;
import blurock.core.RunCommand;
import java.util.StringTokenizer;
import java.util.NoSuchElementException;
/**
 *
 * @author  reaction
 * @version 
 */
public class GetAttributeParameter extends Object {

    String startTextS  = "COREOBJECT";
    String endTextS = "coreEndOfText";
    String attributeS = "Attribute";
    String coreTextS   = "coreText";

    public String outputString = null;
    public String attributeType = null;
    protected TopReactionMenu Top;
    boolean outputOk = false;
    RunCommand runit = null;
    StringTokenizer tokenize;
       /** Creates new GetAttributeParameter */
    public GetAttributeParameter(TopReactionMenu top) {
        Top = top;
    }
    
    public boolean getParameterAsString(String name) {
        String command = Top.SystemCommands.AttributeList.getValue() + " " + name;
        runit = new RunCommand(Top,command,false);
        if(runit.commandOutput != null) {
            tokenize = new StringTokenizer(runit.commandOutput,"\n");
	    outputOk = false;
            return parseOutput();
        } else {
           outputString = new String("Attribute not found");
           attributeType = new String("notfound");
           return false;
        }
    }
        
    boolean parseOutput()
    {
        try {
	outputString = new String("");
	outputOk = skipToStart();
	if(outputOk)
	    {
                //tokenize.nextToken();
		String attr = tokenize.nextToken();
		outputOk = attr.startsWith(attributeS);
	    }
	if(outputOk) {
	    attributeType = tokenize.nextToken();
	    System.out.println("Attribute Type: " + attributeType);
	}
	if(outputOk)
	    {
		String foundname = tokenize.nextToken();
		//outputOk = foundname.startsWith(name);
		//System.out.println("Compare: '" + foundname + "' and '" + name + "'");
	    }
	if(outputOk)
	    {
		String coreText = tokenize.nextToken();
		outputOk = coreText.startsWith(coreTextS);
	    }
	if(outputOk)
	    outputOk = isolateObject();
        } catch( NoSuchElementException nosuch) {
            System.out.print("Error in parsing values");
            outputString = new String(outputString + "not found\n");
            if(runit != null) {
                outputString = new String(outputString+runit.commandOutput+"\n");
            }
            outputOk = false;
        }
	return outputOk;
     }
    boolean isolateObject()
    {
        StringBuffer buf = new StringBuffer();
	boolean notdone = true;
	while(notdone && tokenize.hasMoreTokens())
	    {
		String name = tokenize.nextToken();
		if(name.startsWith(endTextS))
		    notdone = false;
		else {
                    buf.append(name);
                    buf.append("\n");
		    //outputString = new String(outputString + name+ "\n");
                }
	    }
	//System.out.println("DONE: isolateObject()");
	//System.out.println("------------------------------------------");
	//System.out.println(outputString);
	//System.out.println("------------------------------------------");
	//System.out.println("DONE: isolateObject()");
        outputString = buf.toString();
	return !notdone;
    }
    boolean skipToStart()
    {
	boolean notdone = true;
	while(notdone && tokenize.hasMoreTokens())
	    {
		String name = tokenize.nextToken();
                System.out.println("start: '" + name + "' --> '" + startTextS + "'");
		if(name.startsWith(startTextS))
		    notdone = false;
	    }
	return !notdone;
    }
   boolean failedAttempt()
    {
	return outputOk;
    }

}
