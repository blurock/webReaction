/*
 * $Id: ReactReactionConstants.java,v 1.1.1.1 2008/11/13 13:33:14 blurock Exp $
 *
 * Copyright (c) 2000, Edward S. Blurock.
 * All Rights Reserved.
 */
package info.esblurock.reaction.data.gwt.client.data.reactions;

import java.io.Serializable;
import java.text.ParseException;

import info.esblurock.reaction.data.gwt.client.data.IParsableElement;
import info.esblurock.reaction.data.gwt.client.data.ReactionLog;

public class ReactReactionConstants implements IParsableElement, Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public double aFactor;
    public double nFactor;
    public double eFactor;
    public int multiplicity;
    public boolean direction;
    public String reference;
    
    
    public ReactReactionConstants() {
		super();
	}

	public ReactReactionConstants(double aFactor, double nFactor, double eFactor, int multiplicity, boolean direction,
			String reference) {
		super();
		this.aFactor = aFactor;
		this.nFactor = nFactor;
		this.eFactor = eFactor;
		this.multiplicity = multiplicity;
		this.direction = direction;
		this.reference = reference;
	}

	public void print() 
    {
        ReactionLog.logInfo(this.toString());
        ReactionLog.logInfo(this.toString());
    }
    
    public void setData(IParsableElement element) 
    {        
        if (! (element instanceof ReactReactionConstants) )
        {   
            ReactionLog.logError(" > Tried to parse an element of wrong type: " + element.getClass().getName() + " where " + this.getClass().getName()+ " was expected.");
            return;
        }
        ReactReactionConstants e = (ReactReactionConstants)element;
        aFactor = e.aFactor;
        nFactor = e.nFactor;
        eFactor = e.eFactor;
        multiplicity = e.multiplicity;
        direction = e.direction;
        reference = e.reference;
    }
    public String toString() {
        return "Constants: a=" + aFactor + ", n=" + nFactor + ", e=" + eFactor + ", multiplicity=" + multiplicity; 
    }

	@Override
	public void parse(byte[] data) throws ParseException {
		
		
	}
    
    
}
