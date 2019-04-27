/*
 * $Id: ReactRxnPattern.java,v 1.1.1.1 2008/11/13 13:33:14 blurock Exp $
 *
 * Copyright (c) 2000, Edward S. Blurock.
 * All Rights Reserved.
 */
package info.esblurock.reaction.data.gwt.client.data.reactions;

import info.esblurock.reaction.data.gwt.client.data.IParsableElement;
import info.esblurock.reaction.data.gwt.client.data.ReactionLog;
import info.esblurock.reaction.data.gwt.client.data.molecules.ReactMolecule;

public abstract class ReactRxnPattern implements IParsableElement
{
    public String Name;
    public int Id;
    public ReactReactionConstants Forward = new ReactReactionConstants();
    public ReactReactionConstants Reverse = new ReactReactionConstants();
    public ReactSubstructureSet Reactants = new ReactSubstructureSet();
    public ReactSubstructureSet Products  = new ReactSubstructureSet();  
    public ReactCorrespondenceSet CorrSet = new ReactCorrespondenceSet();
    
    //TopReactionMenu Top;
    
    public ReactRxnPattern() {
        Reactants = new ReactSubstructureSet();
        Products  = new ReactSubstructureSet();  
    }    
    public ReactMolecule findReactant(int molID) {
    	return Reactants.getSubstructure(molID); 
    }
    
    public ReactMolecule findProduct(int molID) {
    	return Products.getSubstructure(molID); 
    }
    
    
    public void setData(IParsableElement element) {
        if (! (element instanceof ReactRxnPattern) )
        {   
            ReactionLog.logError(" > Tried to parse an element of wrong type: " + element.getClass().getName() + " where " + this.getClass().getName()+ " was expected.");
            return;
        }
        ReactRxnPattern e = (ReactRxnPattern)element;
        
        Name = e.Name;
        Id   = e.Id;
        Reactants.addAll(e.Reactants);
        Products.addAll(e.Products);
        Forward.setData(e.Forward);
        Reverse.setData(e.Reverse);
        CorrSet.addAll(e.CorrSet);        
    }
    public String toString() {
    	StringBuilder build = new StringBuilder();
        for(ReactAtomCorrespondence corr : CorrSet) {
        	build.append(corr.toString());
        }
        for(ReactMolecule m : Reactants) {
                build.append(m.toString());
        }
        for(ReactMolecule m : Products) {
                build.append(m.toString());
            }
        build.append(Forward.toString());
        build.append(Reverse.toString());  
        return build.toString();
    }
    public void print() 
    {
        ReactionLog.logInfo(Name);
        ReactionLog.logInfo(this.toString());
        System.out.println(this.toString());
    }
    
  }
