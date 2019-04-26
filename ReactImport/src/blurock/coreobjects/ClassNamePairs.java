/*
 * ClassNamePairs.java
 *
 * Created on February 27, 2001, 12:22 PM
 */

package blurock.coreobjects;
import java.util.Vector;
import blurock.core.RWManagerBase;
import java.io.IOException;

/** Helper Class for BaseDataSetOfObjects to
 * keep a summary of the attributes within
 * the set of objects
 *
 *
 * @author Edward S. Blurock
 * @version 2001
 */
public class ClassNamePairs extends java.lang.Object {

    public  Vector Names;
    
    public  Vector ClassNames;
    public  boolean useKeyWord;
    
    /** Creates new ClassNamePairs
 */
    public ClassNamePairs(boolean key) {
        useKeyWord = key;
        Names = new Vector();
        ClassNames = new Vector();
    }
    public ClassNamePairs() {
        useKeyWord = true;
        Names = new Vector();
        ClassNames = new Vector();
    }
    public ClassNamePairs(String[] types, String[] names) {
        useKeyWord = true;
        Names = new Vector();
        ClassNames = new Vector();
        for(int i=0;i<types.length;i++){
            addPair(names[i], types[i]);
        }
    }
    public ClassNamePairs(String type, String[] names) {
        useKeyWord = true;
        Names = new Vector();
        ClassNames = new Vector();
        for(int i=0;i<names.length;i++){
            addPair(names[i], type);
        }
    }

/** ASCII read data:
 *
 * The form expected is:
 * <B>ClassNamePairs:</B>
 * <CODE>Name1 ClassName1
 * Name2 ClassName2</CODE>
 * <B>END</B>
 *
 * @param io Manages where the ASCII data comes from
 * @throws IOException If something goes wrong with reading the
 * data
 */    
    public void Read(RWManagerBase io) throws IOException {
        if(useKeyWord)
            io.checkToken("ClassNamePairs:"); 
        boolean notdone = true;
        while(notdone) {
            String name = io.readElement();
            if(name.startsWith("END")) {
                notdone = false;
            } else {
            String classname = io.readElement();
           addPair(name,classname);
            }
        }
}
    
/** ASCII write data (see Read for form written out)
 * @param io Manages where the ASCII form is written to
 * @throws IOException If something goes wrong with writing
 */
public void Write(RWManagerBase io) throws IOException {
        if(Names.size() == 0) {
            if(useKeyWord)
                io.printLine(" ClassNamePairs: END");
        } else {
            io.printLine("%%% -------------------------------------------------");
            io.printLine("%%% ClassNamePairs");
            if(useKeyWord)
                io.printLine("ClassNamePairs:");
            for(int i = 0; i != Names.size() ; i++) {
                io.printLine(Names.elementAt(i) + " " + ClassNames.elementAt(i));
            }
            io.printLine("END");
            io.printLine("%%% -------------------------------------------------");
        }
}

/** Setting up a name with its associated class name
 * @param name The name of the attribute
 * @param classname The name of the class of the attribute
 *
 */
public void addPair(java.lang.String name,java.lang.String classname) {
        int count = Names.size();
        Names.add(count,name);
        ClassNames.add(count,classname);
}

}
