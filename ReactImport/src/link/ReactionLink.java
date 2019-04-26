/*
 * ReactionLink.java
 *
 * Created on August 15, 2001, 8:57 AM
 */

package link;
import java.io.*;
import utilities.OutputFrame;
/**
 *
 * @author  reaction
 * @version 
 */
public class ReactionLink extends ReactLink {

    PrintStream tPrintStream;
    BufferedInputStream buffer;
    InputStream mProcessError;
    BufferedInputStream ebuffer;
    StringBuffer tStringBuffer;
    OutputFrame ofr;
    OutputFrame protocoll;
    public boolean visibleFrame = false;
    
    /** Creates new ReactionLink */
    public ReactionLink() {
        super(false);
    }
    public ReactionLink(String endofoutput) {
        super(false);
        tEndOfOutput = endofoutput;
        ofr = new OutputFrame();
        protocoll = new OutputFrame();
        setOutputFrames(false);
    }
    public void setOutputFrames(boolean visible) {
        visibleFrame = visible;
        protocoll.setVisible(visibleFrame);
        ofr.setVisible(visibleFrame);
    }
    public synchronized boolean start( String fParameters ) {
        System.out.println("Command: " + fParameters);
        protocoll.errorText.append(fParameters + "\n");
        boolean ans = super.start(fParameters);
        buffer = new BufferedInputStream(mProcessOutput);
        tPrintStream = new PrintStream( mProcessInput );
        //mProcessError  = mProcess.getErrorStream();
        //ebuffer = new BufferedInputStream(mProcessError);
	    try  {
                    tPrintStream.flush();
                    tStringBuffer = new StringBuffer();
                    findUpToCommandPrompt(buffer,tStringBuffer);

        OutputWindowThread errorOut = new OutputWindowThread(mProcess);
        errorOut.start();
            } catch( IOException tException) {
		tException.printStackTrace();
                stop();
	    }
        return ans; 
    }
    public synchronized String execute( String fCommand ) {
        protocoll.errorText.append(fCommand + "\n");
        if(mProcess == null) {
            OutputFrame done = new OutputFrame("Process Ended Abnormally\nPlease Restart");
            done.show();
            return null;
        }
        try {
            ofr.errorText.append("------------Command: '" + fCommand + "'");
            if(mProcess.exitValue() == 0)
                System.out.println("Process Terminated Normally");
            else
                System.out.println("Process Terminated Abnormally");
            stop();
        } catch(java.lang.IllegalThreadStateException io) {}
        if(tPrintStream.checkError())
            System.out.println("***************Execute Error***************");
	if ( mProcess != null  ) {
	    try  {
	  	mProcessOutput = mProcess.getInputStream();
                buffer = new BufferedInputStream(mProcessOutput);
		tPrintStream.flush();      
		tPrintStream.println( fCommand );
		tPrintStream.flush();      
	        System.out.println("ReactionLink.execute: '" + fCommand + "'");

                tStringBuffer = new StringBuffer();
                findUpToCommandPrompt(buffer,tStringBuffer);
                //buffer.mark(10000);
                //System.out.println("Get Error Output");
                //tStringBuffer.append("\nBegin: ============Error Output ===========\n");
                //findUpToCommandPrompt(ebuffer,tStringBuffer);
                //tStringBuffer.append("\nEnd: ============Error Output ===========\n");
                //ebuffer.mark(10000);
                mProcessOutput = null;
                buffer = null;
  		return tStringBuffer.toString();
  
	    }
	    catch( IOException tException ) {
		tException.printStackTrace();
	    }
        } else {
            System.out.println("Process Null");
        }
      
	return null;
    }
    StringBuffer findUpToCommandPrompt(BufferedInputStream buffer, StringBuffer tStringBuffer) throws IOException {
        boolean notdone = true;
        StringBuffer tLine = new StringBuffer();
        char tChar;
        int iChar;
        int count = 0;
        while(notdone) {
            iChar = buffer.read();
           if(iChar == -1) {
                count++;
                try {
                    System.out.println("Wait: " + count);
                    Thread.sleep(500);
                } catch(java.lang.InterruptedException io) {
                    System.out.println("findUpToCommandPrompt: Interupted");
                    notdone = false;
                }
                if(count > 2)
                    notdone = false;
            } else {
                count = 0;
                tChar = (char) iChar;
                tLine.append( tChar );
                if(tLine.toString().startsWith(tEndOfOutput)) {
                     System.out.println("End of Output Detected");
                     notdone = false;
                 } else {
                     if ( tChar == '\n' ) {
                         //ofr.errorText.append(tLine.toString());
                         //ofr.errorText.updateUI();
                         tStringBuffer.append( tLine.toString() );
                         tLine = new StringBuffer();
                     }
                 }
            }
        }
        return tStringBuffer;
    }

}
