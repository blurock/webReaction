/*
 * ChemkinString.java
 *
 * Created on February 17, 2004, 8:54 PM
 */

package react.mechanisms.chemkin;
import java.util.StringTokenizer;
/**
 *
 * @author  reaction
 */
public class ChemkinString {
    
    StringTokenizer tok;
    String commentChar = "!";
    /** Creates a new instance of ChemkinString */
    public ChemkinString(String input, String commentString) {
        tok = new StringTokenizer(input,"\n");
        commentChar = commentString;
    }
    public String nextToken() {
        String next = tok.nextToken();
        next = next.trim();
        if(next.startsWith(commentChar) || next.length() == 0) {
             next = nextToken();
        }
        return next.toUpperCase();
    } 
    public boolean tokenMatch(String element, String token) {
        return element.toUpperCase().startsWith(token.toUpperCase());
    }
}
