/*
 * $Id: ReactLink.java,v 1.1 2008/01/25 13:23:58 blurock Exp $
 *
 * Copyright (c) 2000, Eindhoven University of Technology (TU/e).
 * All Rights Reserved.
 */

package link;

import java.io.*;

/**
 * Gives the programmer a link to r. <p>
 *
 * @author Olga Caprotti
 * @version 1.0
 */
 
public class ReactLink
{
    public String tEndOfOutput  = "EndOfOutput";
    /**
     * Stores the outputstream. <p>
     *
     * @since 1.0
     */
   
    protected OutputStream mProcessInput = null;
    public boolean shouldWait = true;
  
    /**
     * Stores the inputstream. <p>
     *
     * @since 1.0
     */
   
    protected InputStream mProcessOutput = null;
   

    /**
     * Stores a reference to the process. <p>
     *
     * @since 1.0
     */
   
    protected Process mProcess = null;
  
    /**
     * Constructor. <p>
     *
     * @since 1.0
     */
   
    public ReactLink() {
    }
    public ReactLink(boolean waitfor)
    {
	shouldWait = waitfor;
    }
    /**
     * Starts the link. <p>
     *
     * @since 1.0
     */
   public String singleCommand(String fParameters ) {
       start(fParameters);
       return getStreamOutput();
   }
    public synchronized boolean start( String fParameters )
    {
	System.out.println( "ReactLink.start: with '" + fParameters + "'");
	if ( mProcess == null  ) {
	    try {
                // Start command
		mProcess = Runtime.getRuntime().exec(fParameters);
                System.out.println("(start) Process: " + mProcess);
		/*
		 * We need the process output-stream to write the request to
		 * the process, and the process input-stream to read the 
		 * response.
	  	 */

  		mProcessInput  = mProcess.getOutputStream();
	  	mProcessOutput = mProcess.getInputStream();
	    }
	    catch( IOException tException ) {
		tException.printStackTrace();
		return false;
	    }
	}
	return true;
    }

  
    /**
     * Stops the link. <p>
     *
     * @since 1.0
     */
   
    public synchronized boolean stop()
    {
	System.out.println( "ReactLink.stop" );
	if ( mProcess != null ){
	    mProcess.destroy();
	    mProcess = null;
	}
	return true;
    }


    /**
     * Executes the particular command and returns the result. <p>
     *
     * @since 1.0
     */
   
    public synchronized String execute( String fCommand ) {
	System.out.println( "ReactLink.execute" );
        String out = null;
	if ( mProcess != null  ){
                // Write the command
                // if command is null, don't execute
                if(fCommand != null) {
                    // set the command stream
                    System.out.println("Execute the command '"+fCommand+"'");
                    PrintStream tPrintStream = new PrintStream( mProcessInput );
                    tPrintStream.println( fCommand );
                    tPrintStream.flush();
                    System.out.println( fCommand );
                }
		if(shouldWait) {
                    try {mProcess.waitFor();
			} catch( InterruptedException ioe) {
			    System.out.println("Process Interrupted");}			
		    }
                out = getStreamOutput();
        } else {
            System.out.println("Process Died");
        }
        return out;
    }
		/*
		 * Reads the response.
		 */
    private String getStreamOutput() {
        try {
               System.out.println("Process Done: " + mProcess.exitValue());
            } catch(IllegalThreadStateException ioe) { 
                    System.out.println("oops");}
	try{
            BufferedInputStream buffer = 
		    new BufferedInputStream(mProcessOutput);

            StringBuffer tStringBuffer = new StringBuffer();
            StringBuffer tLine         = new StringBuffer();
	    char tChar = (char) buffer.read();
	    boolean notdone = true;
	    if(tChar == -1)
		    notdone = false;
            while(notdone) {
		    tLine.append( tChar );
		    if ( tChar == '\n' ) {
			if(tLine.toString().startsWith(tEndOfOutput)) {
			    notdone = false;
			} else {
			    tStringBuffer.append( tLine.toString() );
			}
			tLine = new StringBuffer();
		    }
			tChar = (char) buffer.read();
            }
            InputStream mProcessError  = mProcess.getErrorStream();
            buffer = new BufferedInputStream(mProcessError);
            tLine.append("\nBegin: ============Error Output ===========\n");
            while( buffer.available() > 0 && notdone) {
		tLine.append( tChar );
		if ( tChar == '\n' ) {
                    if(tLine.toString().startsWith(tEndOfOutput)) {
			    notdone = false;
			} else {
			    tStringBuffer.append( tLine.toString() );
			}
			tLine = new StringBuffer();
		    }
                    tChar = (char) buffer.read();
            }
            tLine.append("\nEnd: ============Error Output ===========\n");
            //System.out.println("Done getting output");
            tStringBuffer.append( tLine.toString() );
            return tStringBuffer.toString();
         } catch( IOException tException ){
		tException.printStackTrace();
	 }
        return null;
    }
}







