/*
 * ReactSubstructureSet.java
 *
 * Created on February 4, 2004, 11:06 AM
 */

package info.esblurock.reaction.data.gwt.client.data.reactions;

import info.esblurock.reaction.data.gwt.client.data.molecules.ReactMolecule;

import java.util.ArrayList;

/*
 * $Id: ReactSubstructureSet.java,v 1.1.1.1 2008/11/13 13:33:14 blurock Exp $
 *
 * Copyright (c) 2018, Edward S. Blurock.
 * All Rights Reserved.
 */
public class ReactSubstructureSet extends ArrayList<ReactMolecule> {

	private static final long serialVersionUID = 1L;


	public ReactSubstructureSet() {
    	super(); 
    }
    
    public void addSubstructure(ReactMolecule corr) {
    	add(corr);
    }
    public ReactMolecule getSubstructure(String name)
    {
	for(ReactMolecule element : this) {
		if(element.getMoleculeName().equals(name))
		    return element;
	    }
        return null;
    }
    
  
    public ReactMolecule getSubstructure(int id) {
    	for(ReactMolecule element : this) {
		if(element.getID() == id)
		    return element;
	    }
        return null;
    }

    public String toString() {
    	StringBuilder build = new StringBuilder();
    	for(ReactMolecule molecule: this) {
    		build.append(molecule.toString());
    	}
    	return build.toString();
    }
}
