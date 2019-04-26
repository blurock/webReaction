/*
 * ReactMoleculeFromSDF.java
 *
 * Created on January 15, 2002, 11:51 AM
 */

package react.molecules;
import java.io.*;
import java.util.*;

/**
 *
 * @author  reaction
 * @version 
 */
public class ReactMoleculeFromSDF extends react.molecules.ReactMolecule {

    /** Creates new ReactMoleculeFromSDF */
    public ReactMoleculeFromSDF() {
    }

    public void parseMolecule(java.io.BufferedReader inp) throws IOException {
        System.out.println("parseMolecule");
        String idS = inp.readLine();
        if(idS == null) throw new IOException();
        String ignore = inp.readLine();
        MoleculeName = inp.readLine();
        System.out.println("Molecule: " + idS + "  " + MoleculeName);
        try {
            ID = Integer.parseInt(idS);
            String numS = inp.readLine();
            StringTokenizer nums = new StringTokenizer(numS);
            if(nums.countTokens() < 2)
                throw new IOException("Invalid Begining of SDF file: '" + idS +  "'");
            NumberOfAtoms = Integer.parseInt(nums.nextToken());
            NumberOfBonds = Integer.parseInt(nums.nextToken());
        } catch(java.lang.NumberFormatException io) {
            throw new IOException("Invalid Begining of SDF file: '" + idS + "'");
        }
        try {
        for(int i=0; i < NumberOfAtoms ; i++) {
            ReactAtomFromSDF atom = new ReactAtomFromSDF();
            parseAtom(inp.readLine(),atom);
        }
        } catch(IOException io) {
            System.out.println(io);
            throw new IOException("Error in Reading Atoms");
        }
	for(int i=0; i < NumberOfBonds ; i++) {
            ReactBondFromSDF bond = new ReactBondFromSDF();
            parseBond(inp.readLine(),bond);
        }
        String next = inp.readLine();
        while(!next.startsWith("$$$")) {
            next = inp.readLine();
        }
    }
}
