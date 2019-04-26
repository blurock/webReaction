/*
 * $Id: ReactMolecule.java,v 1.1 2008/01/25 13:23:45 blurock Exp $
 *
 * Copyright (c) 2000, Edward S. Blurock.
 * All Rights Reserved.
 */
package react.molecules;
import utilities.*;
import link.*;
import javax.swing.*;
import java.util.*;
import java.io.IOException;

public class ReactMolecule
{
    public String molcommand;
    public String MoleculeName;
    public int ID;
    protected ReactLink tLink;
    
    public Integer CHOHashCode = null;
    public Integer HashCode = null;
    
    public int NumberOfBonds;
    public int NumberOfAtoms;
    
    Vector Atoms = new Vector();
    Vector Bonds = new Vector();

    public ReactMolecule() {
    }
    public ReactMolecule(String name, ReactLink link, String command) {
	MoleculeName = name;
	tLink = link;
	molcommand = command;
    }
    public void createHashCode() {
        int iCHOHashCode = 0;
        int iHashCode = 0;
        int extra = 1000000;
        for(int i=0;i<NumberOfAtoms;i++) {
            ReactAtom atom = (ReactAtom) Atoms.elementAt(i);
            int atnum = atom.AtomicNumber;
            if(atnum == 6)
                iCHOHashCode += 1;
            else if(atnum == 1) 
                iCHOHashCode += 100;
            else if(atnum == 8)
                iCHOHashCode += 10000;
            else {
                iHashCode += atnum*extra;
                extra *= 100;
            }
        }
        CHOHashCode = new Integer(iCHOHashCode);
        HashCode = new Integer(iCHOHashCode + iHashCode);
    }
    public boolean isIsomer(ReactMolecule mol) {
        if(HashCode == null)
            createHashCode();
        if(mol.HashCode == null)
            mol.createHashCode();
        
        return HashCode.equals(mol.HashCode);
    }
    public ReactAtom getAtom(int index) throws NullPointerException {
        if(index < 0 || index >= NumberOfAtoms) {
            throw new NullPointerException("Atom not Found: " + index);
        } else {
            return (ReactAtom) Atoms.elementAt(index);
        }
    }
    public void addAtom(ReactAtom atom) {
	Atoms.addElement(atom);
    }

    public void addBond(ReactBond bond) {
	Bonds.addElement(bond);
    }

    
    public void getMoleculeInfo() {
	String command = new String(molcommand + " " + MoleculeName);
	System.out.println("Command: '" + command + "'");
        String moleculestring = tLink.singleCommand(command);
	//tLink.start(command);
	//String moleculestring = tLink.execute("\n");
	
	parseMolecule(moleculestring);
        tLink.stop();
    }
    public void parseMolecule(String molinp)
    {
	StringTokenizer molblocks = new StringTokenizer(molinp,"~");
	
	//String preamble      = molblocks.nextToken();
	String preamble1      = molblocks.nextToken();
	String basic         = molblocks.nextToken();
	String atoms         = molblocks.nextToken();
	String bonds         = molblocks.nextToken();
	String electronic1    = molblocks.nextToken();
	String electronic2    = molblocks.nextToken();
	String electronic3    = molblocks.nextToken();

        try {
            parseBasic(basic);
	    parseAtoms(atoms);
	    parseBonds(bonds);
        } catch(IOException io) {
            System.out.println("Error in reading files");
        }
    }

    public void print()
    {
	for(int i=0; i < NumberOfAtoms ; i++)
	    {
		ReactAtom a = (ReactAtom) Atoms.elementAt(i);
		a.print();
	    }
	for(int j=0; j < NumberOfBonds ; j++)
	    {
		ReactBond b = (ReactBond) Bonds.elementAt(j);
		b.print();
	    }
    }
    public void parseAtoms(String atoms) throws IOException 
    {
	StringTokenizer info = new StringTokenizer(atoms,"\n");
	while(info.hasMoreTokens())
	    {
		ReactAtom atom = new ReactAtom();
                parseAtom(info.nextToken(),atom);
	    }
    }
    protected void parseAtom(String atm,ReactAtom atom) throws IOException {
		atom.parse(atm,this);
		addAtom(atom);
    }
    public void parseBonds(String bonds)
    {
	StringTokenizer info = new StringTokenizer(bonds,"\n");
	while(info.hasMoreTokens())
	    {
		ReactBond bond = new ReactBond();
                parseBond(info.nextToken(),bond);
	    }
    }
    protected void parseBond(String bnd, ReactBond bond) {
		bond.parse(bnd,this);
		addBond(bond);
    }
    public void parseBasic(String basic)
    {
	StringTokenizer info = new StringTokenizer(basic,":");
	
	String preamble = info.nextToken();
	String idS = info.nextToken();
	StringNumber id = new StringNumber(idS.substring(7));
	String atoms = info.nextToken();
	String bonds = info.nextToken();
	MoleculeName = info.nextToken();
	ID = id.intValue();
	
	int count = 0;
	while(atoms.startsWith(" ",count))
		count++;
	int count1 = count;
	while(!atoms.startsWith(" ",count1))
		count1++;
	Integer numatoms = new Integer(atoms.substring(count,count1));
	NumberOfAtoms = numatoms.intValue();

	count = 0;
	while(bonds.startsWith(" ",count))
		count++;
	count1 = count;
	while(!bonds.startsWith(" ",count1))
		count1++;
	
	Integer numbonds = new Integer(bonds.substring(count,count1));
	NumberOfBonds = numbonds.intValue();
    }
    public int getAtomicNumber(String atm) {
        int ans = 0;
        if(atm.startsWith("C")) ans = 6;
        else if(atm.startsWith("C"))  ans = 6;
        else if(atm.startsWith("H"))  ans = 1;
        else if(atm.startsWith("O"))  ans = 8;
        else if(atm.startsWith("R"))  ans = 320;
        else if(atm.startsWith("Q"))  ans = 300;
        return ans;
    }
}
