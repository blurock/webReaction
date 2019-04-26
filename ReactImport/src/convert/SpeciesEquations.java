/*
 * SpeciesEquations.java
 *
 * Created on January 17, 2003, 11:07 AM
 */

package convert;
import java.util.ArrayList;

/**
 *
 * @author  reaction
 */
public class SpeciesEquations extends java.util.ArrayList {
    MecOutReactionSet reactions = null;
    String SpeciesName = null;
    String RateName = null;
    boolean toZero = true;
    /** Creates a new instance of SpeciesEquations */
    public SpeciesEquations(boolean tozero, String speciesname, String ratename, MecOutReactionSet set) {
        reactions = set;
        SpeciesName = speciesname;
        RateName = ratename;
        toZero = tozero;
    }
    public void addEquations(SpeciesList species) {
        System.out.println("addEquations");
        for(int cnt = 0; cnt < species.size();cnt++) {
            String name = (String)  species.get(cnt);
            System.out.println("Species: " + name);
             int id = reactions.speciesList.getID(name);
            SpecieEquation eq = new SpecieEquation(name,id,SpeciesName,RateName);
            for(int rcnt = 0;rcnt<reactions.size();rcnt++) {
                MecOutReaction rxn = (MecOutReaction) reactions.get(rcnt);
                if(rxn.isProduct(name)) {
                    eq.addReaction(false,rxn);
                }
                if(rxn.isReactant(name)) {
                    eq.addReaction(true,rxn);
                }
            }
            add(cnt,eq);
            System.out.println("Add Equation: " + size());
        }
    }
    public String toString() {
        StringBuffer buf = new StringBuffer();

        buf.append("Variables:\n");
        for(int cnt = 0; cnt<size();cnt++) {
             SpecieEquation eq = (SpecieEquation) get(cnt);
             buf.append(SpeciesName + reactions.speciesList.getID(eq.Species) + " ");
        }
        buf.append("\n");
        buf.append("Equations:\n");
        for(int cnt = 0;cnt<size();cnt++) {
            SpecieEquation eq = (SpecieEquation) get(cnt);
            if(toZero)
                buf.append("0 = ");
            else 
                buf.append(SpeciesName + eq.ID + " = ");
            buf.append(eq.toString());
            buf.append("\n");
            
        }
        return buf.toString();
    }
}
