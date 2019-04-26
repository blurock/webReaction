/*
 * ReactMechanismGenerationStep.java
 *
 * Created on January 22, 2001, 7:37 PM
 */

package react.mechanisms;
import react.common.*;
import java.util.StringTokenizer;


/**
 *
 * @author  reaction
 * @version 
 */
public class ReactMechanismGenerationStep extends Object {

    TopReactionMenu Top;
    
    public String[] Patterns;
    
    public String[] stepMolecules;
    public int index;
    
    /** Creates new ReactMechanismGenerationStep */
    public ReactMechanismGenerationStep(TopReactionMenu top) {
        Top = top;
    }
    
    public boolean parse(String lne) {
        System.out.println("Parse through Steps");
        StringTokenizer tok = new StringTokenizer(lne,":");
        
        String elements = tok.nextToken();         
        System.out.println("The elements:  " + elements);
        StringTokenizer elementTokens = new StringTokenizer(elements,",");

        System.out.println("Number of Steps: " + elementTokens.countTokens());
        Patterns = new String[elementTokens.countTokens()];
        int numElements = 0;
        System.out.println("Begin: Through elements");
        while(elementTokens.hasMoreTokens()) {
            String ele = elementTokens.nextToken();
            System.out.println("Token: '" + ele + "'");
            Patterns[numElements] = ele;
            numElements++;
        }
        if(tok.hasMoreTokens()) {
            System.out.println("Begin: Through molecules");
            String molecules = tok.nextToken();
            System.out.println("The Molecules: " + molecules);
            StringTokenizer moleculeTokens = new StringTokenizer(molecules,",");
            stepMolecules = new String[moleculeTokens.countTokens()];
            int numMolecules = 0;
            while(moleculeTokens.hasMoreTokens()) {
                String ele = moleculeTokens.nextToken();
                System.out.println("Token: '" + ele + "'");
                stepMolecules[numMolecules] = ele;
                numMolecules++;
            }
        }   
        return true;
   }
   public String write() {
       String str = new String();
       for(int i = 0; i < Patterns.length; i++) {
            String m = new String(Patterns[i]);
            str.concat(m);
            if(i != Patterns.length - 1) 
                        str.concat(",\\");
            str.concat("\n");
        }
       String colon = new String(":");
       str.concat(colon);
        for(int i = 0; i < stepMolecules.length; i++) {
                String m = new String(stepMolecules[i]);
                str.concat(m);
                if(i != stepMolecules.length - 1) 
                        str.concat(",");
            }
       str.concat("\n");
       return str;         
   }

}
