/*
 * RunCommand.java
 *
 * Created on February 16, 2001, 5:43 PM
 */

package blurock.core;
import link.*;
import react.common.*;
import utilities.OutputFrame;
/**
 *
 * @author  reaction
 * @version 
 */
public class RunCommand extends Object {
    
    TopReactionMenu Top;
    String CommandName;
    String levelModS;
    boolean Operate;
    public String commandOutput;
    boolean run = true;
    
    /** Creates new RunCommand */
    public RunCommand(ReactLink rlink, String comname)
    {
	CommandName = comname;
        commandOutput = rlink.execute(comname);;
        run = false;
    }
    public RunCommand(TopReactionMenu top, String comname, boolean operate)
    {
	Top = top;
	CommandName = comname;
	Operate = operate;
        levelModS = top.history.historyCommandKey(Operate);
        run();
        run = false;
    }
    public RunCommand(TopReactionMenu top, String comname, boolean operate, String level)
    {
	Top = top;
	CommandName = comname;
	Operate = operate;
        levelModS = level;
    }
    private void run()
    {
        if(run) {
            Top.initializeSystemIfNeeded();
            commandOutput = Top.reactLink.execute(CommandName);
        }
    }
    public void showResults() {
        OutputFrame fr = new OutputFrame(commandOutput);
        fr.show();
    }
}
