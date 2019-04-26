/*
 * SpecieEquation.java
 *
 * Created on January 17, 2003, 11:07 AM
 */

package convert;
import java.util.ArrayList;
/**
 *
 * @author  reaction
 */
public class SpecieEquation extends java.lang.Object {
    ArrayList asReactant = new ArrayList();
    int asReactantCount = 0;
    ArrayList asProduct  = new ArrayList();
    int asProductCount = 0;
    String SpeciesName = null;
    String RateName = null;
    public String Species = null;
    public int ID = 0;
    /** Creates a new instance of SpecieEquation */
    public SpecieEquation(String name, int id, String speciesname, String ratename) {
        Species = name;
        ID = id;
        SpeciesName = speciesname;
        RateName = ratename;
    }
    public void addReaction(boolean asReactantBool, MecOutReaction rxn) {
        System.out.println("addReaction");
        if(asReactantBool) {
            asReactant.add(asReactantCount,rxn);
            asReactantCount++;
        } else {
            asProduct.add(asProductCount,rxn);
            asProductCount++;
        }
    }
    public String toString() {
        StringBuffer buf = new StringBuffer();
        Object[] asreactant = asReactant.toArray();
        Object[] asproduct  = asProduct.toArray();
        System.out.println("Reactants: " + asreactant.length + "  Products: " + asproduct.length);
        for(int cnt = 0; cnt < asreactant.length;cnt++) {
            MecOutReaction rxn = (MecOutReaction) asreactant[cnt];
            String eqstr = rxn.toEquation(SpeciesName, RateName, false);
            buf.append(eqstr);
        }
        for(int cnt = 0; cnt < asproduct.length;cnt++) {
            MecOutReaction rxn = (MecOutReaction) asproduct[cnt];
            String eqstr = rxn.toEquation(SpeciesName, RateName, true);
            buf.append(eqstr);
        }
        return buf.toString();
    }
}
