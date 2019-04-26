/*
 * SubstructureListButton.java
 *
 * Created on January 31, 2001, 6:50 PM
 */

package react.molecules;
import link.*;

/**
 *
 * @author  reaction
 * @version 
 */
public class SubstructureListButton extends MoleculeListButton {

    /** Creates new SubstructureListButton */
    public SubstructureListButton() {
    }
    public boolean doOperation(String mol) {
	System.out.println("doOperation in GetSubStructureList: "  + mol);
	CurrentMolecule = new ReactSubStructure(mol,Top.tLink,Top.Scripts.printSubstructure.getValue());
	drawMolecule(mol);
	return true;
    }
 
}
