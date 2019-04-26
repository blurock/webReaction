/*
 * SubstituteProgramParameters.java
 *
 * Created on August 17, 2006, 5:45 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package react.utilities;

import utilities.FileToString;
import utilities.ReadFileToString;
import java.io.File;
import utilities.ReadFileToString;

/**
 *
 * @author reaction
 */
public class SubstituteProgramParameters {
    public String parameter1 ="XXXXX";
    public String parameter2 = "YYYYY";
    public String parameter3 = "ZZZZZ";
    
    String program = null;
    /** Creates a new instance of SubstituteProgramParameters */
    public SubstituteProgramParameters() {
    }
    
    public void setProgramFromString(String prog) {
        program = prog;
    }
    public void setProgramFromFile(File progF) {
        ReadFileToString f2s = new ReadFileToString();
        f2s.read(progF);
        program = f2s.outputString;
    }
    public String substitute(File progF, String[] subs) {
        System.out.println("substitute it: " + progF);
        setProgramFromFile(progF);
        System.out.println("substitute it: " + program);
        String out = program;
        if(subs.length == 1) {
            out = substitute(subs[0]);
        } else if(subs.length == 2) {
            out = substitute(subs[0],subs[1]);
        } else if(subs.length == 3) {
            out = substitute(subs[0],subs[1],subs[2]);
        }
        return out;
    }
    public String substitute(String newp) {
        String newprogram = new String(program);
        newprogram = newprogram.replaceAll(parameter1, newp);
        return newprogram;
    }
    
    public String substitute(String p1, String p2) {
        String newprogram = new String(program);
        newprogram = newprogram.replaceAll(parameter1, p1);
        newprogram = newprogram.replaceAll(parameter2, p2);
        return newprogram;
    }
    public String substitute(String p1, String p2, String p3) {
        String newprogram = new String(program);
        newprogram = newprogram.replaceAll(parameter1, p1);
        newprogram = newprogram.replaceAll(parameter2, p2);
        newprogram = newprogram.replaceAll(parameter3, p3);
        return newprogram;
    }
    
}
