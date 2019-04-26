/*
 * ChemkinReaction.java
 *
 * Created on February 18, 2004, 10:56 AM
 */

package react.mechanisms.chemkin;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.IOException;
/**
 *
 * @author  reaction
 */
public class ChemkinReaction {
    
    public ArrayList Reactants = new ArrayList();
    public ArrayList Products = new ArrayList();
    public String A,n,Ea;
    
    public boolean ThirdBodyFlag = false;
    public boolean ThirdBodyCoeffsFlag= false;
    public boolean reversible = false;
    public String comment = null;
    public boolean markedAsDuplicate = false;
    public String commentChar = "!";
    
    ChemkinString lines;
    ArrayList molecules;
    
    /** Creates a new instance of ChemkinReaction */
    public ChemkinReaction(ChemkinString ls, ArrayList mols, String commentstring) {
        lines = ls;
        molecules = mols;
        commentChar = commentstring;
    }
    public String parse(String rxn,String forward, String equ)   throws IOException {
     int pos = rxn.indexOf("!");
     comment = rxn.substring(pos+1);
     String next;
     if(pos > 0)
        next = rxn.substring(0,pos);
     else
         next = rxn;
     try {
           pos = next.indexOf(forward);
           if(pos > 0) {
              parseReactants(next.substring(0,pos));
              StringTokenizer tok = parseProducts(next.substring(pos+2));
              parseCoeffs(tok);            
           } else {
        pos = next.indexOf("equ");
        System.out.println(next);
        if(pos > 0) {
            parseReactants(next.substring(0,pos));
            StringTokenizer tok = parseProducts(next.substring(pos+3));
            parseCoeffs(tok);
            reversible = true;
        }
        }
           if(ThirdBodyCoeffsFlag)
            next = parseThirdBodyCoeffs();
     } catch(StringIndexOutOfBoundsException io) {
         throw new IOException("StringIndexOutOfBoundsException: " + next);
    }
        return next;
    }
    private void parseReactants(String react)  throws IOException {
        System.out.println("Reactants: " + react);
        parseMolList(react,true);
    }
   private StringTokenizer parseProducts(String react)  throws IOException {
        System.out.println("Products: " + react);
        StringTokenizer tok;
        tok = parseMolList(react,false);
        return tok;
    }
   private StringTokenizer parseMolList(String mollist, boolean reactants)  throws IOException {
       mollist = mollist.replaceAll("\\(\\+[mM]\\)","+XXX");
       System.out.println("--->" + mollist);
       StringTokenizer tok = new StringTokenizer(mollist," ");
       int num = tok.countTokens();
       if(!reactants) num = num - 3;
       while(num > 0) {
           String mols = tok.nextToken();
           StringTokenizer tok1 = new StringTokenizer(mols,"+");
           while(tok1.hasMoreTokens()) {
            String mol = tok1.nextToken();
            System.out.println("Molecule" + mol);
            if(mol.equals("XXX")) {
               System.out.println("ThirdBody: XXXX");
               ThirdBodyCoeffsFlag = true;
               ThirdBodyFlag = true;
           } else if(mol.equals("M") || mol.equals("m") ) {
               System.out.println("ThirdBody: M");
               ThirdBodyFlag = true;
           } else {
               int dupcnt = 1;
               if(!molecules.contains(mol)) {
                   System.out.println("============== not there =============");
                   char ch[] = new char[mol.length()];
                   int cnt = 0;
                   int length = mol.length();
                   while(!molecules.contains(mol) && cnt < length) {
                       ch[cnt] = mol.charAt(0);
                       cnt++;
                       mol = mol.substring(1);
                   }
                   if(cnt < length) {
                       String cntS = new String(ch,0,cnt);
                       try {
                        dupcnt = Integer.parseInt(cntS);
                       } catch(NumberFormatException ex) {
                           throw new IOException("Multiplicity Error");
                       }
                   } else {
                       throw new IOException("Species Name Error");
                   }
               }
               while(dupcnt > 0) {
               System.out.println("Add" + mol);

                if(reactants) 
                    Reactants.add(mol);
                else
                    Products.add(mol);
                dupcnt--;
               }
           }
           }
           num--;
       }
       System.out.println("Parse Mol done");
       return tok;
   }
    private void parseCoeffs(StringTokenizer tok) throws IOException {
        if(tok.countTokens() == 3) {
            A = tok.nextToken();
            n = tok.nextToken();
            Ea = tok.nextToken();
        } else {
            throw new IOException("Coefficient Parse Error:");
        }
        System.out.println(" A: " + A + " n: " + n + " Ea: " + Ea );
    }
    private String parseThirdBodyCoeffs() {
        boolean done = false;
        String rxn = lines.nextToken();
        while(!done) {
            int pos = rxn.indexOf("!");
            String next;
            if(pos > 0) 
                next = rxn.substring(0,pos);
            else
                next = rxn;
            
            if(next.toUpperCase().startsWith("LOW")) {
                System.out.println("LOW: " + next);
                rxn = lines.nextToken();
            } else if(next.toUpperCase().startsWith("TROE")) {
                System.out.println("TROE: " + next);
                rxn = lines.nextToken();
            } else if(next.toUpperCase().startsWith("REV")) {
                System.out.println("REV: " + next);
                rxn = lines.nextToken();
            } else if(next.indexOf("/") > 0) {
                System.out.println("ThirdBodies: " + next);
                rxn = lines.nextToken();
            } else {
                done = true;
            }
        }
        return rxn;
    }

} 