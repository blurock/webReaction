/*
 * RunScriptWithOutput.java
 *
 * Created on February 20, 2001, 10:57 AM
 */

package blurock.core;
import link.ReactLink;
import utilities.ErrorFrame;

/**
 *
 * @author  reaction
 * @version 
 */
public class RunScriptWithOutput extends java.lang.Object {

    private ReactLink tLink;
    private String commandOutput;
    
    /** Creates new RunScriptWithOutput */
    public RunScriptWithOutput(ReactLink tlink) {
        tLink = tlink;
    }
    public void run(String scriptcommand, boolean showoutput)
    {
        System.out.println("Run Command: '" + scriptcommand + "'");
	tLink.start(scriptcommand);
	//commandOutput = tLink.execute(new String("\n"));
        //tLink.execute("\n");
        tLink.execute(null);
	tLink.stop();
        if(showoutput) {
            ErrorFrame fr = new ErrorFrame(commandOutput);
            fr.show();
        }
    }

}
