/*
 * ReadFile.java
 *
 * Created on February 10, 2001, 9:03 PM
 */

package blurock.core;
import react.common.*;

/**
 *
 * @author  reaction
 * @version 
 */
public class ReadFile extends Object {
    public String commandOutput;

    /** Creates new ReadFile */
    public ReadFile(TopReactionMenu top, String clsfile, String argfile, boolean operate) {
        top.initializeSystemIfNeeded();
        //System.out.println("Testing in ReadFile:   Print Attribute");
        //RunCommand runit = new RunCommand(top,"Print Attribute",false);
        //runit.run();
        //top.rLink.execute("Print Attribute\n");
        String command = "Read " + clsfile + " " + argfile + " " + top.history.getDebugLevel();
        commandOutput = top.reactLink.execute(command);
       /*
        String levelModS = top.history.historyCommandKey(operate);
	String command = 
	    top.SystemInfo.reactionExecutable.getValue() + " xxx " + 
	    levelModS + " " +
	    top.history.getHistoryName() + " " +
	    top.history.getHistoryLevel() + " " +
	    " Read " +
	    clsfile + " " + argfile + " " +
	    top.history.getDebugLevel();
	System.out.println("Command: '" + command  + "'");
	top.tLink.start(command);
	commandOutput = top.tLink.execute(new String("\n"));
	top.tLink.stop();
        if(operate)
	    top.history.incrementHistoryLevel();
         **/
    }
}
