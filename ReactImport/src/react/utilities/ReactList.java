/*
 * $Id: ReactList.java,v 1.1 2008/01/25 13:23:54 blurock Exp $
 *
 * Copyright (c) 2000, Edward S. Blurock.
 * All Rights Reserved.
 */
package react.utilities;

import utilities.TokenizeOutput;
import react.common.*;
import link.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.awt.Dimension;

public class ReactList
{
    public TopReactionMenu Top;
    protected String commandString;
    
    protected String[] nameList = null;
    protected String[] idList = null;
   
    protected int maxNames = 5;
    protected int numNames = 0;
    
    public ReactList() {
    }
    
    public ReactList(String title, String command, TopReactionMenu top) {
	Top = top;
	commandString = command;
	//updateList();
    }
    
    public void updateList() {        
	numNames = 0;
        System.out.println("updateList(): "+ commandString);
	TokenizeOutput tokens = new TokenizeOutput(Top.tLink);
	tokens.startCommand(commandString);
        
        int maxNames = tokens.countTokens();
        System.out.println("updateList(): "+ maxNames);
	nameList = new String[maxNames];
	idList = new String[maxNames];
        numNames = 0;
	String next = tokens.getNextToken();
        //System.out.println("updateList(): next="+ next);
	while(next.length() >0 && numNames < maxNames) {
            if(!(next.startsWith("Begin: ======"))) {
	    isolateAndAddElement(next);
	    next = tokens.getNextToken();
         //System.out.println("updateList(): next="+ next);
           } else {
                System.out.println("End of ReactList");
                next = new String();
            }
	}
	tokens.endCommand();
        System.out.println("updateList(): num="+ numNames + " " + maxNames);
    }
    protected void isolateAndAddElement(String strLine) {
	StringTokenizer comToken = new StringTokenizer(strLine,":");
	String id = new String(comToken.nextToken());
	if(comToken.hasMoreTokens())
	    {
		String name = new String(comToken.nextToken());
		String isoname = name.substring(4,name.length() - 3);
		//System.out.println(isoname);
		//System.out.println(id);
		addElement(id,isoname);
	    }
    }
    protected void addElement(String id, String name) {
	nameList[numNames] = name.trim();
	idList[numNames] = id.trim();
	numNames++;
    }

    public boolean isInList(String name) {
        if(nameList == null)
            updateList();
        boolean notdone = true;
        int cnt = 0;
        while(notdone && cnt < numNames) {
            if(nameList[cnt].equals(name)) 
                notdone = false;
            cnt++;
        }
        return !notdone;
    }
    
    public String getCommandString() {return commandString;}
    
    /**
       * Set the value of commandString.
       * @param v  Value to assign to commandString.
       */
    public void setCommandString(String  v) {this.commandString = v;}
    
    /**
       * Get the value of nameList.
       * @return Value of nameList.
       */
    public String[] getNameList() {
        if(nameList == null)
            updateList();
        String names[] = new String[numNames];
        for(int i=0;i<numNames;i++) 
            names[i] = nameList[i];
        return names;
    }
    
    /**
       * Set the value of nameList.
       * @param v  Value to assign to nameList.
       */
    public void setNameList(String[]  v) {this.nameList = v;}
        /**
       * Get the value of idList.
       * @return Value of idList.
       */
    public String[] getIdList() {
        if(nameList == null)
            updateList();
        
        return idList;
    }
    
    /**
       * Set the value of idList.
       * @param v  Value to assign to idList.
       */
    public void setIdList(String[]  v) {
        if(nameList == null)
            updateList();        
        this.idList = v;
    }
    
    public String getIDFromName(String name) {
        if(nameList == null)
            updateList();
        String ans = new String("");
        int i = 0;
        while(i<numNames && !nameList[i].equals(name)) {
            i++;
            //System.out.println(i + ":  '" + nameList[i] + "'");
        }
        if(i<numNames) {
            ans = new String(idList[i]);
            System.out.println("getIDFromName '" + idList[i] + "'");
        } else {
            System.err.println("Molecule: '" + name + "' not found " + numNames);
        }
        System.out.println("getIDFromName '" + ans + "' from '"+ 
                name + "' "  + numNames + " " + name.length());
        return ans;
        }
}
