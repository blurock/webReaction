/*
 * RunCommand.java
 *
 * Created on February 14, 2001, 3:55 PM
 */

package blurock.utilities;
import react.common.TopReactionMenu;
import link.ReactLink;
import link.ReactionLink;

/**
 *
 * @author  reaction
 * @version 
 */
public class RunCommand extends Object {
    TopReactionMenu Top;
    public String outputString = new String();
    boolean interactive = false;
    
    /** Creates new RunCommand */
    public RunCommand(TopReactionMenu top) {
        Top = top;
    }
    public void run(String commandString) {
        Top.initializeSystemIfNeeded();
        System.out.println("RunCommand -> " + commandString);
        outputString = Top.reactLink.execute(commandString + "\n");
    } 
}
