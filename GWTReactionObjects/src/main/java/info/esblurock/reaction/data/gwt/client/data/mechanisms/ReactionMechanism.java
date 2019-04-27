/*
 * ReactionMechanism.java
 *
 * Created on February 3, 2001, 6:40 PM
 */

package info.esblurock.reaction.data.gwt.client.data.mechanisms;
import info.esblurock.reaction.data.gwt.client.data.IParsableElement;
import info.esblurock.reaction.data.gwt.client.data.ReactionLog;
import info.esblurock.reaction.data.gwt.client.data.molecules.ReactMolecule;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author  moliate
 * @version 
 */
public abstract class ReactionMechanism implements IParsableElement, Serializable
{
	private static final long serialVersionUID = 1L;
	public ArrayList<ReactMechanismRxnClass> rxnClasses = new ArrayList<ReactMechanismRxnClass>();
    public HashMap<String,ReactMolecule> Molecules = new HashMap<String,ReactMolecule>();
    public String seedMolecule = "";
    /** Creates new ReactionMechanism */
    
    public ReactionMechanism() {
    }
    
    
    public String write() {
        return this.toString();
    }  
   
    public void print() {
        System.out.println(this.toString());
        ReactionLog.logInfo(this.toString());
    }
    
    public void setData(IParsableElement element) {
        if (! (element instanceof ReactionMechanism) )
        {   
            ReactionLog.logError(" > Tried to parse an element of wrong type: " + element.getClass().getName() + " where " + this.getClass().getName()+ " was expected.");
            return;
        }
        ReactionMechanism e = (ReactionMechanism)element;
        
        Molecules = e.Molecules;
        rxnClasses = e.rxnClasses;
        seedMolecule = e.seedMolecule;
    }
    public String toString() 
   {
      StringBuffer str = new StringBuffer();
       str.append("CLASSCOEFFICIENTS\n");
       for(ReactMechanismRxnClass rxnclass : rxnClasses) {
          str.append(rxnclass.writeCoeffs());
       }
       str.append("END\n");
       str.append("CLASSEQUIVALENT\n");
       str.append("END\n");
       for(ReactMechanismRxnClass rxnclass : rxnClasses) {
          str.append(rxnclass.write());
       }
       return str.toString();
   }        
 
}
