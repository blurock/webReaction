/*
 * IsobaricAdiabaticCombustion.java
 *
 * Created on June 8, 2006, 2:51 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package blurock.runignition;

/**
 *
 * @author reactionNewClass
 */
public class IsobaricAdiabaticCombustion {
    double[] temperatures;
    double[] pressures;
    double[][] species;
    String[] speciesNames;
    double timeStepSize;
    
    
    /** Creates a new instance of IsobaricAdiabaticCombustion */
    public IsobaricAdiabaticCombustion(double[] t, double[] p, double ts, double[][] s, String[] spnames) {
        temperatures = t;
        pressures = p;
        timeStepSize = ts;
        species = s;
        speciesNames = spnames;
        setupInputFiles();
    }
    public void setupInputFiles() {
        
    }
}
