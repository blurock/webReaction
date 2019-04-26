/*
 * ErrorOutputThread.java
 *
 * Created on February 20, 2002, 2:35 PM
 */

package link;
import java.io.*;
import utilities.OutputFrame;
/**
 *
 * @author  reaction
 * @version 
 */
public class OutputWindowThread extends java.lang.Thread {
    Process mProcess = null;
    InputStream mProcessError = null;
    BufferedInputStream buffer = null;
    boolean notdone = true;
    OutputFrame oFrame;
    /** Creates new ErrorOutputThread */
    public OutputWindowThread(Process pr) {
       mProcess = pr;
       mProcessError   = mProcess.getErrorStream();
       System.out.println("Get Error String");
       buffer = new BufferedInputStream(mProcessError);
       oFrame = new OutputFrame();
         oFrame.errorText.append("=====================================================\n");
         oFrame.errorText.append("Error Stream\n");
         oFrame.errorText.append("=====================================================\n");
         oFrame.show();
    }
    public void run() {
     try {
        notdone = true;
	char tChar = (char) buffer.read();
        if(tChar == -1) notdone = false;
	StringBuffer tLine = new StringBuffer();
	while(notdone) {
            tLine.append( tChar );
            if ( tChar == '\n' ) {
	        oFrame.errorText.append( tLine.toString() );
	        tLine = new StringBuffer();
	    }
	    tChar = (char) buffer.read();
	}
     } catch(IOException io) {
         System.out.println("Error Proecessing done");
         oFrame.errorText.append("=====================================================\n");
         oFrame.errorText.append("Ending Process Error Stream\n");
         oFrame.errorText.append("=====================================================\n");
         
     }
    }    
}
