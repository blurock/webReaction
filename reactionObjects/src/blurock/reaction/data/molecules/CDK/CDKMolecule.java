/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package blurock.reaction.data.molecules.CDK;

import blurock.reaction.data.molecules.CML.CMLMolecule;
import info.esblurock.reaction.data.gwt.client.data.molecules.ReactMolecule;

import java.text.ParseException;

/**
 *
 * @author blurock
 */
@SuppressWarnings("serial")
public class CDKMolecule extends ReactMolecule {

    public void parse(byte[] data) throws ParseException {
        CMLMolecule cmlmolecule = new CMLMolecule();
        cmlmolecule.parse(data);
    }

}
