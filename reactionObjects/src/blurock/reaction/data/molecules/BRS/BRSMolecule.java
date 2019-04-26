
package blurock.reaction.data.molecules.BRS;
import info.esblurock.reaction.data.gwt.client.data.ReactionLog;
import info.esblurock.reaction.data.gwt.client.data.molecules.ReactAtom;
import info.esblurock.reaction.data.gwt.client.data.molecules.ReactBond;
import info.esblurock.reaction.data.gwt.client.data.molecules.ReactMolecule;
import info.esblurock.reaction.data.gwt.client.data.molecules.ReactPeriodicTable;

import java.util.*;

import blurock.reaction.link.ReactLink;

import java.io.IOException;

@SuppressWarnings("serial")
public class BRSMolecule extends ReactMolecule {
	ReactLink tLink;
	String molcommand;
	
	public BRSMolecule(ReactLink tLink, String molcommand) {
		super();
		this.tLink = tLink;
		this.molcommand = molcommand;
	}

    public void parse(byte[] data) throws java.text.ParseException {
                String molecule = new String(data);
                StringTokenizer molblocks = new StringTokenizer(molecule, "~");
	
		molblocks.nextToken();
		//String preamble1      = molblocks.nextToken();
		String basic          = molblocks.nextToken();
		String atoms          = molblocks.nextToken();
		String bonds          = molblocks.nextToken();
		//String electronic1    = 
				molblocks.nextToken();
		//String electronic2  =  
		molblocks.nextToken();
		//String electronic3    = 
				molblocks.nextToken();

                try 
                {
                    parseBasic(basic);
                    parseAtoms(atoms);
                    parseBonds(bonds);
                } 
                catch(IOException io)
                {
                    throw new java.text.ParseException("Parse error: " + io.toString(), -1);
                }
    }
    
    public void getMoleculeInfo() 
    {
		//String command = new String(molcommand + " " + MoleculeName);
		//Log.println("Command: '" + command + "'");
                tLink.setCommand(molcommand);
                String[] params = {MoleculeName};
                tLink.setParameters(params);
                tLink.start();
                String moleculestring = tLink.getResult();
		//tLink.start(command);
		//String moleculestring = tLink.execute("\n");
		try {
			parseMolecule(moleculestring);
		} catch (Exception ignore) {}
		
        ReactionLog.logInfo("----");
        tLink.stop();
        ReactionLog.logInfo(moleculestring);
    }
    
    public void parseMolecule(String molecule) throws java.text.ParseException
    {
                StringTokenizer molblocks = new StringTokenizer(molecule, "~");
	
		molblocks.nextToken();
		//String preamble1      = molblocks.nextToken();
		String basic          = molblocks.nextToken();
		String atoms          = molblocks.nextToken();
		String bonds          = molblocks.nextToken();
		//String electronic1    = 
				molblocks.nextToken();
		//String electronic2    = 
				molblocks.nextToken();
		//String electronic3    = 
				molblocks.nextToken();

                try 
                {
                    parseBasic(basic);
                    parseAtoms(atoms);
                    parseBonds(bonds);
                } 
                catch(IOException io)
                {
                    throw new java.text.ParseException("Parse error: " + io.toString(), -1);
                }
    }


    public void parseAtoms(String atoms) throws IOException 
    {
		StringTokenizer info = new StringTokenizer(atoms,"\n");
		while(info.hasMoreTokens())
                {
			ReactAtom atom = new BRSAtom();
                        parseAtom(info.nextToken(), atom);
                }
    }
    
    protected void parseAtom(String atm,ReactAtom atom) throws IOException {
                try
                {
                        atom.parse(atm.getBytes());
                        addAtom(atom);
                }
                catch(java.text.ParseException e) 
                {}
    }
    
    public void parseBonds(String bonds)
    {
            StringTokenizer info = new StringTokenizer(bonds,"\n");
            while(info.hasMoreTokens())
	    {
		ReactBond bond = new BRSBond();
                parseBond(info.nextToken(), bond);
	    }
    }
    
    protected void parseBond(String bnd, ReactBond bond) {
        try
        {
		bond.parse(bnd.getBytes());
		addBond(bond);
        }
        catch(java.text.ParseException e)
        {}
    }
    
    public void parseBasic(String basic)
    {
		StringTokenizer info = new StringTokenizer(" "+basic, ":");
	
		info.nextToken();
		String idS      = info.nextToken();
		String atoms    = info.nextToken();
		String bonds    = info.nextToken();
		MoleculeName    = info.nextToken().trim();

		ID              = Integer.parseInt(idS.replaceAll("\\D", ""));
		NumberOfAtoms   = Integer.parseInt(atoms.replaceAll("\\D", ""));
		NumberOfBonds   = Integer.parseInt(bonds.replaceAll("\\D", ""));
    }
    
    public int getAtomicNumber(String atm) {
        /*int ans = 0;
        if(atm.startsWith("C")) ans = 6;
        else if(atm.startsWith("C"))  ans = 6;
        else if(atm.startsWith("H"))  ans = 1;
        else if(atm.startsWith("O"))  ans = 8;
        else if(atm.startsWith("R"))  ans = 320;
        else if(atm.startsWith("Q"))  ans = 300;
        */
        return ReactPeriodicTable.AtomNumber(atm);
    }    
}
