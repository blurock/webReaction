/*
 * ReactMechanismRxnClass.java
 *
 * Created on February 3, 2001, 6:44 PM
 */

package info.esblurock.reaction.data.gwt.client.data.mechanisms;

import info.esblurock.reaction.data.gwt.client.data.IParsableElement;
import info.esblurock.reaction.data.gwt.client.data.ReactionLog;
import info.esblurock.reaction.data.gwt.client.data.reactions.ReactReactionConstants;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.i18n.client.NumberFormat;
//import debug.utilities.ErrorFrame;
/**
 *
 * @author  reaction
 * @version 
 */
public abstract class ReactMechanismRxnClass implements IParsableElement, Serializable {
	private static final long serialVersionUID = 1L;
	public String className = "";
    public ArrayList<ReactMechanismRxn> reactions = new ArrayList<ReactMechanismRxn>();
    public ReactReactionConstants forwardConstants = new ReactReactionConstants();
    public ReactReactionConstants reverseConstants = new ReactReactionConstants(); 
    
    /** Creates new ReactMechanismRxnClass */
    public ReactMechanismRxnClass() {
    }
    
    public ArrayList<ReactMechanismRxn> reactionsWithMoleculeAsReactant(String molname) {
    	ArrayList<ReactMechanismRxn> rxns = new ArrayList<ReactMechanismRxn>();
        for(ReactMechanismRxn rxn : reactions) {
            if(rxn.moleculeAsReactant(molname)) { 
                rxns.add(rxn); 
            }
        }
        return rxns;
    }
    
    public ArrayList<ReactMechanismRxn> reactionsWithMoleculeAsProduct(String molname) {
    	ArrayList<ReactMechanismRxn> rxns = new ArrayList<ReactMechanismRxn>();
        for(ReactMechanismRxn rxn: reactions) {
            if(rxn.moleculeAsProduct(molname)) {
                rxns.add(rxn);
            }
        }
        return rxns;
    }
    
    public void print() 
    {
        System.out.println(" ______ ReactMechanismRxnClass ______");
        System.out.println( toString());
        System.out.println( writeCoeffs());
        System.out.println(" ____________________________________");
        ReactionLog.logInfo(" ______ ReactMechanismRxnClass ______");
        ReactionLog.logInfo( toString());
        ReactionLog.logInfo( writeCoeffs());
        ReactionLog.logInfo(" ____________________________________");
    }
    
    public void setData(IParsableElement element) 
    {
        if (! (element instanceof ReactMechanismRxnClass) )
        {   
            ReactionLog.logError(" > Tried to parse an element of wrong type: " + element.getClass().getName() + " where " + this.getClass().getName()+ " was expected.");
            return;
        }
        ReactMechanismRxnClass e = (ReactMechanismRxnClass)element;
        
        className = e.className;
        reactions = new ArrayList<ReactMechanismRxn>(e.reactions);
        forwardConstants.setData(e.forwardConstants);
        reverseConstants.setData(e.reverseConstants);
    }
    
    public String toString() 
    {
        StringBuffer str = new StringBuffer();
        str.append("REACTIONCLASS = " + className + System.getProperty("line.separator"));
        for(ReactMechanismRxn rxn : reactions) {
            str.append(rxn.toString());
        }
        str.append("END" + System.getProperty("line.separator"));
        return str.toString();
    }  
    
    public String writeCoeffs() 
    {
        StringBuffer str = new StringBuffer();
        str.append(className + " = ");
        NumberFormat format = NumberFormat.getFormat("00.0000####E00 ");
        str.append(format.format(forwardConstants.aFactor) + " ");
        str.append(format.format(forwardConstants.nFactor) + " ");
        str.append(format.format(forwardConstants.eFactor) + " ");
        str.append(format.format(reverseConstants.aFactor) + " ");
        str.append(format.format(reverseConstants.nFactor) + " ");
        str.append(format.format(reverseConstants.eFactor) + System.getProperty("line.separator"));
        return str.toString();
    }
        
    public String write() {
        return this.toString();
    }
}
