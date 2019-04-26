/*
 * $Id: TokenizeOutput.java,v 1.1 2008/01/25 13:24:33 blurock Exp $
 *
 * Copyright (c) 2000, Edward S. Blurock
 * All Rights Reserved.
 */
package utilities;

import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import java.awt.Dimension;
import java.util.StringTokenizer;
import link.ReactLink;

public class TokenizeOutput
{
    ReactLink tLink;
    int retry;
    int MaxRetries = 20;

    StringTokenizer strTokenizer;
    String CommandString;
    public boolean singleCommand = true;
    
    public TokenizeOutput(ReactLink tlink)
    {
	tLink = tlink;
    }
    public TokenizeOutput(ReactLink tlink, boolean s)
    {
	tLink = tlink;
        singleCommand = s;
        if(singleCommand)
            System.out.println("TokenizeOutput: singleCommand = true");
        else
            System.out.println("TokenizeOutput: singleCommand = false");
    }
    public void startCommand(String command)
    {
	retry = 0;
	System.out.println("Command: " + command);
        if(singleCommand) {
	    //tLink.start(command);
            //System.out.println("Back from command");
	    //CommandString = tLink.execute("\n");
            CommandString = tLink.singleCommand(command);
            System.out.println("Got Output");
        } else {
	    CommandString = tLink.execute(new String(command));
        }
	    System.out.println("--------------------------------------------------------");
	    System.out.println(CommandString);
	    System.out.println("--------------------------------------------------------");
	strTokenizer = new StringTokenizer(CommandString,"\n");
    }
    public int countTokens() {
        return strTokenizer.countTokens();
    }
    public String getNextToken()
    {
	String outstring;
	if(strTokenizer.hasMoreTokens())
	    {
		outstring =  strTokenizer.nextToken();
	    }
	else
	    {
		retry += 1;
		if(retry < MaxRetries) {
		    CommandString = tLink.execute(new String("\n"));	
		    System.out.println("------------------  Retry       ------------------------");
		    System.out.println(CommandString);
		    System.out.println("--------------------------------------------------------");
		    strTokenizer = new StringTokenizer(CommandString,"\n");
		    outstring =  getNextToken();
		} else {
		    outstring = new String();
		}
	    }
	return outstring;
    }
    public void endCommand()
    {
	tLink.stop();
    }
    boolean failedAttempt()
    {
	return retry >= MaxRetries;
    }
}
