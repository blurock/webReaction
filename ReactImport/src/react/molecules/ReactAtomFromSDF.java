/*
 * ReactAtomFromSDF.java
 *
 * Created on January 15, 2002, 12:27 PM
 */

package react.molecules;
import java.io.IOException;

/**
 *
 * @author  reaction
 * @version 
 */
public class ReactAtomFromSDF extends react.molecules.ReactAtom {

    /** Creates new ReactAtomFromSDF */
    public ReactAtomFromSDF() {
    }

    public void parse(String atom, ReactMolecule mol) throws IOException {
        try {
            String xS = atom.substring(0,9);
            String yS = atom.substring(10,19);
            String zS = atom.substring(20,29);
            X = Float.parseFloat(xS);
            Y = Float.parseFloat(yS);
            Z = Float.parseFloat(zS);
            
            String atomicNumberS = atom.substring(31,32);
            AtomicNumber = mol.getAtomicNumber(atomicNumberS);
            String infoS = atom.substring(38,39);
            Info = Integer.parseInt(infoS);
        } catch(java.lang.NumberFormatException io) {
            throw new IOException("Invalid Atom of SDF file: '" + atom +  "'");
    }
    }
}