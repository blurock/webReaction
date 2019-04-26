/*
 * MecOutReaction.java
 *
 * Created on December 16, 2002, 5:03 PM
 */

package convert;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.NoSuchElementException;
/**
 *
 * @author  reaction
 */
public class MecOutReaction extends java.lang.Object {
    
    public int ID;
    
    public int maxReactionLength = 50;
    
    private int numReactants;
    
    private int numProducts;
    
    private String[] Reactants = null;
    
    private String[] Products = null;
    
    private String A = "0.0000";
    private String n = "0.000";
    private String E = "0.000";
    
    private SpeciesList speciesList = null;
    
    /** Creates a new instance of MecOutReaction */
    public MecOutReaction(int count, SpeciesList splist) {
        speciesList = splist;
        ID = count;
    }
    
    public void read(StringTokenizer tok, Object[] species) 
        throws DoneReadingException,IOException {
        String line;
        try {
            System.out.println("MecOutReaction: " + tok.countTokens());
            line = tok.nextToken();
         } catch(NoSuchElementException ex) {
            throw new DoneReadingException();
         }
        try {
            System.out.println("Num Rxns/Prods: " + line);
            StringTokenizer ltok = new StringTokenizer(line);
            numReactants = Integer.parseInt(ltok.nextToken());
            numProducts  = Integer.parseInt(ltok.nextToken());
            System.out.println("Reactants: " + numReactants + 
                "Products: " + numProducts);
            Reactants = new String[numReactants];
            Products = new String[numProducts];
            
            line = tok.nextToken();
            ltok = new StringTokenizer(line);
            for(int i=0;i<numReactants;i++) {
                String numS = ltok.nextToken();
                int num = Integer.parseInt(numS) - 1;
                Reactants[i] = (String) species[num];
            }
            line = tok.nextToken();
            ltok = new StringTokenizer(line);
            for(int i=0;i<numProducts;i++) {
                String numS = ltok.nextToken();
                int num = Integer.parseInt(numS) - 1;
                Products[i] = (String) species[num];
            }
            
            line = tok.nextToken();
            System.out.println(line);
            ltok = new StringTokenizer(line);
            A = ltok.nextToken();
            n = ltok.nextToken();
            E = ltok.nextToken();
        } catch(NoSuchElementException ex) {
            throw new IOException("MECOUT read error: '" + line + "'");
        } catch(NumberFormatException ex) {
            throw new IOException("MECOUT read error: '" + line + "'");
        }
    }
    public String toString() {
        StringBuffer buf = new StringBuffer();
        String rxn = toReaction();
        buf.append(rxn);
        int cnt = maxReactionLength - rxn.length();
        for(int i=0; i<cnt;i++)
            buf.append(' ');
        toRates(buf);
         buf.append("\n");
        return buf.toString();
    }
    public String toReaction() {
        StringBuffer buf = new StringBuffer();
        toMolecules(buf,Reactants);
        buf.append(" > ");
        toMolecules(buf,Products);
       return buf.toString();
    }
    public void toMolecules(StringBuffer buf, String[] mols) {
        for(int i=0;i<mols.length;i++) {
            if(i != 0) 
                buf.append("+");
            buf.append(mols[i]);
        }
    }
    private void toRates(StringBuffer buf) {
        numAdjust(A,buf,10);
        buf.append(' ');
        numAdjust(n,buf,10);
        buf.append(' ');
        numAdjust(E,buf,10);
     }
    private void numAdjust(String num, StringBuffer buf, int s) {
        buf.append(num);
        for(int i=0;i<s;i++)
            buf.append(' ');
    }
    public String toEquation(String speciesname, String ratename, 
                            boolean product) {
        StringBuffer buf = new StringBuffer();
        if(product) {
            buf.append(equation(speciesname,ratename,Reactants, true));
        } else {
            buf.append(equation(speciesname,ratename,Products,false));
        }
        return buf.toString();
    }
    private String equation(String speciesname, String ratename, 
                            String[] species, boolean pos) {
        System.out.println("equation");
        StringBuffer buf = new StringBuffer();
        if(pos) 
            buf.append(" + ");
        else 
            buf.append(" - ");
        buf.append(ratename + ID);
        for(int cnt = 0; cnt < species.length;cnt++) {
            buf.append("*");
            int speciesID = speciesList.getID(species[cnt]);
            buf.append(speciesname + speciesID);
        }
        System.out.println("Equation: " + buf.toString());
        return buf.toString();
    }
    public boolean isProduct(String name) {
        int cnt = 0;
        boolean notfound = true;
        while(notfound && cnt < Products.length) {
            if(name.equals(Products[cnt]))
                notfound = false;
            cnt++;
        }
        return !notfound;
    }
    public boolean isReactant(String name) {
       int cnt = 0;
        boolean notfound = true;
        while(notfound && cnt < Reactants.length) {
            if(name.equals(Reactants[cnt]))
                notfound = false;
            cnt++;
        }
        return !notfound;
    }
}
