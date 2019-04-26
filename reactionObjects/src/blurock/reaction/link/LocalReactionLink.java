/*
 * LocalReactionLink.java
 *
 * Created on December 25, 2004, 7:45 PM
 */

package blurock.reaction.link;

import blurock.reactions.common.Log;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

/**
 *
 * @author  reaction
 */
public class LocalReactionLink extends ReactLink {
    /**
     * 
     */
    protected static Log aLog;
    /** Creates a new instance of LocalReactionLink */
    public LocalReactionLink() {
   }
    PrintStream tPrintStream;
    BufferedInputStream buffer;
    InputStream mProcessError;
    BufferedInputStream ebuffer;
    public StringBuffer tStringBuffer;
    public String tEndOfOutput = "Reaction::";
    String executeDirectory;
    String homeDirectory;
    class JobDiedException extends IOException {
        JobDiedException(String message) {
            super(message);
        }
    }
    StringBuffer output = new StringBuffer(); 
    /** Creates new ReactionLink
     * @param home
     * @param execD 
     */
    public LocalReactionLink(String home, String execD) {
        super(false);
        executeDirectory = execD;
        homeDirectory = home;
        environment.put("DATAHOME", home);
   }
    /**
     * 
     * @param fParameters
     * @return
     */
    @Override
    public synchronized boolean start( String fParameters ) {
        Log.println("Command: " + fParameters);
        Log.println("Directory: " + executeDirectory);
        boolean ans = start(fParameters, new File(executeDirectory));
        buffer = new BufferedInputStream(mProcessOutput);
        tPrintStream = new PrintStream( mProcessInput );
        mProcessError  = mProcess.getErrorStream();
        ebuffer = new BufferedInputStream(mProcessError);
	    try  {
                    tPrintStream.flush();
                    tStringBuffer = new StringBuffer();
                    findUpToCommandPrompt(buffer,tStringBuffer);
/*
        OutputWindowThread errorOut = new OutputWindowThread(mProcess);
        errorOut.start();
 */
            } catch( IOException tException) {
		tException.printStackTrace();
                stop();
	    }
        return ans; 
    }
    
    public synchronized boolean start( String fParameters , File execDir)
    {
	Log.println( "ReactLink.start: with '" + fParameters + "'");
	if ( mProcess == null  ) {
	    try {
                // Start command
                String[] noparms = new String[0];
                System.out.println("Running Directory" + execDir);
		mProcess = Runtime.getRuntime().exec(fParameters,noparms,execDir);
                Log.println("(start) Process: " + mProcess);
		/*
		 * We need the process output-stream to write the request to
		 * the process, and the process input-stream to read the 
		 * response.
	  	 */

  		mProcessInput  = mProcess.getOutputStream();
	  	mProcessOutput = mProcess.getInputStream();
                mProcessError  = mProcess.getErrorStream();
                
	    }
	    catch( IOException tException ) {
		tException.printStackTrace();
		return false;
	    }
	}
	return true;
    }

    @Override
    public String execute( String fCommand ) {
        output.append(fCommand + "\n");
        if(mProcess == null) {
            output.append("Process Ended Abnormally\nPlease Restart");
            output.append("\nERROR\n");
             return output.toString();
        }
        try {
            output.append("------------Command: '" + fCommand + "'");
            if(mProcess.exitValue() == 0)
                output.append("Process Terminated Normally");
            else {
                    output.append("Process Terminated Abnormally");
                    output.append("\nERROR\n");
            }
            stop();
        } catch(java.lang.IllegalThreadStateException io) {}
        if(tPrintStream.checkError()) {
            output.append("***************Execute Error***************");
            output.append("\nERROR\n");
        } else {
            if ( mProcess != null  ) {
	    try  {
	  	mProcessOutput = mProcess.getInputStream();
                buffer = new BufferedInputStream(mProcessOutput);
		tPrintStream.flush();      
		tPrintStream.println( fCommand );
		tPrintStream.flush();      
	        Log.println("ReactionLink.execute: '" + fCommand + "'");
                tStringBuffer = new StringBuffer();
                findUpToCommandPrompt(buffer,tStringBuffer);
                while(mProcessError.available() > 0) {
                    char bit = (char) mProcessError.read();
                    tStringBuffer.append(bit);
                }
                mProcessOutput = null;
                buffer = null;
                Log.println("---------- Error Output Start ---------\n");
                Log.println(tStringBuffer.toString());
                Log.println("-----------Error Output End -----------\n");
                result = tStringBuffer.toString();
  		return tStringBuffer.toString();
  
	    }
	    catch( IOException tException ) {
 	       tException.printStackTrace();
               output.append(tException.toString());
               Log.println(tException.toString());
               output.append("\nERROR\n");
	    }
        } else {
            output.append("Process Null");
            output.append("\nERROR\n");
        }
        }
	return output.toString();
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
                    tStringBuffer.append("\nERROR\n");
                }
                if(count > 2) {
                    notdone = false;
                    tStringBuffer.append("\nERROR\n");
                }
            } else {
                count = 0;
                tChar = (char) iChar;
                tLine.append( tChar );
                if(tLine.toString().startsWith(tEndOfOutput)) {
                     System.out.println("End of Output Detected");
                     notdone = false;
                 } else {
                     if ( tChar == '\n' ) {
                         tStringBuffer.append( tLine.toString() );
                         tLine = new StringBuffer();
                     }
                 }
            }
        }
        return tStringBuffer;
    }

}
