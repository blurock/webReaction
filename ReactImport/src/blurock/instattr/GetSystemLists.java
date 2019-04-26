/*
 * GetAttributeParameter.java
 *
 * Created on February 15, 2001, 8:09 PM
 */

package blurock.instattr;
import react.common.*;
import blurock.core.RunCommand;
import java.util.StringTokenizer;
import blurock.instattr.GetAttributeParameter;

/**
 *
 * @author  reaction
 * @version 
 */
public class GetSystemLists extends Object {

    protected String startTextS    = new String("TEXTCHOICE");
    protected String endTextS = new String("ENDTEXTCHOICE");

    public String outputString;
    public String attributeType;
    protected TopReactionMenu Top;
    protected boolean outputOk;
    
    protected StringTokenizer tokenize;
       /** Creates new GetAttributeParameter */
    public GetSystemLists(TopReactionMenu top) {
        Top = top;
    }
    
    public String[] getAttributeList() {
        String command = Top.SystemCommands.AttributeList.getValue();
        RunCommand runit = new RunCommand(Top,command,false);
        //runit.run();
        tokenize = new StringTokenizer(runit.commandOutput,"\n");
	outputOk = false;
        startTextS    = new String("TEXTCHOICE");
        endTextS = new String("ENDTEXTCHOICE");
        parseOutput();
        return parseOutList();
    }
    public String[] getInstanceAttributeList(String instname) {
        String command = Top.SystemCommands.AttributeList.getValue() 
                    + " " + instname;
        RunCommand runit = new RunCommand(Top,command,false);
        //runit.run();
        tokenize = new StringTokenizer(runit.commandOutput,"\n");
	outputOk = false;
        startTextS    = new String("TEXTCHOICE");
        endTextS = new String("ENDTEXTCHOICE");
        parseOutput();
        return parseOutList();
    }
    public String[] getInstanceList() {
        String command = Top.SystemCommands.InstanceList.getValue();
        RunCommand runit = new RunCommand(Top,command,false);
        //runit.run();
        //System.out.println("======================================================");
        //System.out.println(runit.commandOutput);
        //System.out.println("======================================================");
        //System.out.flush();
        tokenize = new StringTokenizer(runit.commandOutput,"\n");
	outputOk = false;
        startTextS    = new String("TEXTCHOICE");
        endTextS = new String("ENDTEXTCHOICE");
        parseOutput();
        return parseOutList();
    }
    public String[] getSystemList(String name) {
        GetAttributeParameter getparam = new GetAttributeParameter(Top);
        String[] names;
        startTextS    = new String("coreText");
        endTextS = new String("endCoreText");
        boolean success = getparam.getParameterAsString(name);
        if(success) {
            outputString = getparam.outputString;
            names = parseOutList();
            System.out.println("Found " + names.length + " names for list");
        } else {
            names = new String[0];
            System.out.println("Getting Parameter not successful");
        }
        return names;
    }
    public String[] parseOutList() {
        StringTokenizer tokens = new StringTokenizer(outputString);
        int num = tokens.countTokens() - 2; // There is a blank line at the end
        String[] names;
        if(num > 0) {
            tokens.nextToken();
            names = new String[num];
            for(int i=0; i<num;i++) {
                names[i] = tokens.nextToken();
            }
        } else {
            System.out.println("No Elements Isolated");
            names = new String[0];
        }
        return names;
    }
        
        
    protected boolean parseOutput()
    {
        
	outputString = new String("");
	outputOk = skipToStart();
	if(outputOk)
	    {
                //System.out.println("Two Dummies");
                tokenize.nextToken();
		tokenize.nextToken();
	    }
        else
            System.out.println("Error in finding start");
        
	if(outputOk)
	    outputOk = isolateObject();
        else
            System.out.println("Couldn't Isolate Object");
	
	return outputOk;
        
   }
    protected boolean isolateObject()
    {
        System.out.println("isolateObject()");
	boolean notdone = true;
	while(notdone && tokenize.hasMoreTokens())
	    {
		String name = tokenize.nextToken();
                System.out.println("isolate: " + name + " -> " + endTextS);
		if(name.startsWith(endTextS))
		    notdone = false;
		else
		    outputString = new String(outputString + name+ "\n");
	    }
	//System.out.println("DONE: isolateObject()");
	//System.out.println("------------------------------------------");
	//System.out.println(outputString);
	//System.out.println("------------------------------------------");
	//System.out.println("DONE: isolateObject()");
	return !notdone;
    }
    boolean skipToStart()
    {
	//System.out.println("skipToCOREOBJECTS()");
	boolean notdone = true;
	while(notdone && tokenize.hasMoreTokens())
	    {
		String name = tokenize.nextToken();
                //System.out.println("'" + name + "' --> '" + startTextS + "'");
		if(name.startsWith(startTextS))
		    notdone = false;
	    }
	//System.out.println("DONE:  skipToCOREOBJECTS()");
	return !notdone;
    }
   protected boolean failedAttempt()
    {
	return outputOk;
    }

}
