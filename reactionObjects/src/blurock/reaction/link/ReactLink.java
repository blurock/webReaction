/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package blurock.reaction.link;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import blurock.reaction.common.SProperties;

class SystemEnvironment  extends java.util.Hashtable {
    String[] toExecForm()
    {
         java.util.Vector vector = new java.util.Vector();
         for (java.util.Enumeration e = this.keys(); e.hasMoreElements();) 
         {
             Object key = e.nextElement();
             vector.add(key.toString() + "=" + get(key).toString());
         }
         String[] array = new String[vector.size()];
         vector.copyInto(array);
         return array;
    }
}


/**
 *
 * @author blurock
 */
public class ReactLink {
    
    public static final String tEndOfOutput  = "EndOfOutput";
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

    protected String command="";
    protected String parameters="";
    protected String result="";
    protected String error="";    
    protected SystemEnvironment environment = new SystemEnvironment();
    protected java.io.File executeDir = null;   
    
    public ReactLink() {
        
    }
    
    public ReactLink(boolean waitfor)
    {
        super();
	shouldWait = waitfor;
    }

  
    public void setSystemProperty(String property, String value)
    {
        environment.put(property, value);
    }
    
    public void setExecuteDir(java.io.File dir)
    {
        if (dir.isFile())
            dir = dir.getParentFile();
        dir.mkdirs();
        
        executeDir = dir;
    }
    
    public boolean start()
    {
        result = "";
        error  = "";   
        mProcessInput = null;
        mProcessOutput = null;
        mProcess = null;
        boolean ok = start(command + parameters);
        if (ok)
            ok = ok && getProcessResults();
        
        return ok;
    }
    
    public void setCommand(String command) {
        this.command = command;
    }
    
    public void setParameters(Object[] parameters) {
        this.parameters = "";
        for (int i=0; i < parameters.length; i++)
            this.parameters += " " + parameters[i];    
    }
    
    public String setProperty(String property, String v) {
        return "";
    }
    
    public String getResult() {
        return result;
    }
    
    public String getError() {
        return "LINK ERROR: " + error;
    }
    
    public synchronized boolean start( String fParameters ) {
        String home = SProperties.getProperty("reaction.home");
        //home = "/opt/ReactUsers/users/trial";
        //home = "/Users/edwardblurock/Reaction/REACT";
        environment.put("REACTROOT", home);
        System.out.println(fParameters);
        java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.INFO,  "ReactLink.start: \"" + command +"\" REACTROOT=\"" + home + "\"");
        if (null == executeDir)
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.WARNING, " (no execution directory specified)");
        else
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.INFO,"Execute in: " + executeDir.toString());
        
	if ( mProcess == null  ) {
	    try {
                // Start command
                if (null == executeDir)
                    mProcess = Runtime.getRuntime().exec(fParameters, environment.toExecForm());
                else
                    mProcess = Runtime.getRuntime().exec(fParameters, environment.toExecForm(), executeDir);                    
                //Log.println("(start) Process: " + mProcess);
		/*
		 * We need the process output-stream to write the request to
		 * the process, and the process input-stream to read the
		 * response.
	  	 */

  		mProcessInput  = mProcess.getOutputStream();
	  	mProcessOutput = mProcess.getInputStream();
	    }
	    catch( IOException tException ) {
                java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.WARNING,tException.toString());
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
	//Log.println( "ReactLink.stop" );
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
	//Log.println( "ReactLink.execute" );
        String out = null;
	if ( mProcess != null  ){
                // Write the command
                // if command is null, don't execute
                if(fCommand != null) {
                    // set the command stream
                    //Log.println("Execute the command '"+fCommand+"'");
                    PrintStream tPrintStream = new PrintStream( mProcessInput );
                    tPrintStream.println( fCommand );
                    tPrintStream.flush();
                    java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.INFO,fCommand);
                }
		if(shouldWait) {
                    try {mProcess.waitFor();
			} catch( InterruptedException ioe) {
                            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.WARNING,"Process Interrupted: " + ioe.toString());
                        }
		    }
                out = getStreamOutput();
        } else {
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE,"Process Died");
        }
        return out;
    }
    
    
   private boolean getProcessResults() {

	try{
            BufferedInputStream buffer = new BufferedInputStream(mProcessOutput);
            int last = -1;
            StringBuffer sb = new StringBuffer(4096);            
	        int data = -1;
            while (-1 != (data = buffer.read()))
                sb.append( (char)data );
            buffer.close();
                        
            result = sb.toString();
            if ( -1 != (last = result.indexOf(tEndOfOutput)) );
                try{
                        result = result.substring(0, last);
                   } 
                catch (IndexOutOfBoundsException e) {}
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.INFO,"(" + result.length() + " bytes)");
            
            InputStream mProcessError  = mProcess.getErrorStream();
            buffer = new BufferedInputStream(mProcessError);
            sb = new StringBuffer(1024);
            
	        data = -1;
            while (-1 != (data = buffer.read()))
                sb.append( (char)data );
            buffer.close();
            mProcessError.close();
                        
            error = sb.toString();
            if ( -1 != (last = error.indexOf(tEndOfOutput)) );
                try{
                        error = error.substring(0, last);
                   } 
                catch (IndexOutOfBoundsException e) {}
            try {
                 int ret = mProcess.waitFor();
            } catch(InterruptedException e) {
                 error = e.toString();
                 java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE," > Interrupted: " + e.toString());
                 return false;
                }
		           
            return true;
         } 
        catch( IOException tException )
        {
                error += " I/O Exception: " + error;
                java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE,tException.toString());
                return false;
	}   
    }
        
   // __________________________________________________________________________________________________________
   // ____________________________________ DEPRECATED BELOW THIS LINE __________________________________________       
    /**
     *  @deprecated
     */
    private String getStreamOutput() {
        try {
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.INFO,"Process Done: " + mProcess.exitValue());
            } catch(IllegalThreadStateException ioe) {
                java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE," " + ioe.toString());
            }

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
            
            while(buffer.available() > 0) buffer.read();
            
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
            while(buffer.available() > 0) buffer.read();
            tStringBuffer.append( tLine.toString() );         
            try{
                 int ret = mProcess.waitFor();
                 java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.INFO,"Done. Exitcode = " + ret);
            } catch(InterruptedException e)
                {
                java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE,e.toString());
                }
            return tStringBuffer.toString();
         } catch( IOException tException ){
             java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE,tException.toString());
	 }
        return null;
    }
    

    /**
     * @deprecated
     *
     * Use @see #start()
     */
   public String singleCommand(String fParameters ) {
       start(fParameters);
       return getStreamOutput();
   }
    

}
