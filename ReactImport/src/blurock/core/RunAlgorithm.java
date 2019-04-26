/*
 * RunAlgorithm.java
 *
 * Created on February 10, 2001, 7:19 PM
 */

package blurock.core;

import blurock.core.historyLevel;
import link.*;
import react.common.*;
import java.awt.Dimension;
import java.util.StringTokenizer;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import utilities.OutputFrame;

/** This Runs Algorithm Commands
 *
 * @author Edward S. Blurock
 * @version 1
 */
public class RunAlgorithm extends Object {
    protected TopReactionMenu Top;

/**
 */    
    protected String AlgorithmName;
    protected boolean Operate;
    protected String levelModS;
    public String commandOutput;

    /** Creates new RunAlgorithm */
    public RunAlgorithm(TopReactionMenu top, String algname, boolean operate)
    {
	Top = top;
	AlgorithmName = algname;
    }
    public void run()
    {
	String command = 
	    "RunAlgorithm " +
	    AlgorithmName + " " + 
	    Top.history.getDebugLevel();
        
        System.out.println("Run Command: '" + command + "'");
	commandOutput = Top.reactLink.execute(command);
    }
    public void showResults() {
        OutputFrame fr = new OutputFrame(commandOutput);
        fr.show();
    }
}

