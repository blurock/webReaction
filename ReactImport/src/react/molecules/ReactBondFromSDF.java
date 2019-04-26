/*
 * ReactBondFromSDF.java
 *
 * Created on January 15, 2002, 6:09 PM
 */

package react.molecules;

/**
 *
 * @author  reaction
 * @version 
 */
public class ReactBondFromSDF extends react.molecules.ReactBond {

    /** Creates new ReactBondFromSDF */
    public ReactBondFromSDF() {
    }

    public void parse(String str,ReactMolecule mol) {
            String iS = str.substring(1,3);
            String jS = str.substring(4,6);
            String bS = str.substring(7,9);
            I = Integer.parseInt(iS.trim()) - 1;
            J = Integer.parseInt(jS.trim()) - 1;
            int b = Integer.parseInt(bS.trim());
           if(b == 1) BondType = "Single";
            else if(b == 2) BondType = "Double";
            else if(b == 3) BondType = "Triple";
    }
    
}
