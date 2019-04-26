/*
 * RWManager.java
 *
 * Created on February 22, 2001, 9:11 AM
 */

package blurock.core;
import blurock.coreobjects.DataSetOfObjectsClass;
import blurock.coreobjects.DataObjectClass;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.awt.List;

import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import org.w3c.dom.*;

/**
 *
 * @author  reaction
 * @version 
 */
public class RWManager extends RWManagerBase {
    PrintWriter outStream = null;
    BufferedReader inStream = null;
    
    /** Creates new RWManager */
    public RWManager() {
        super();
    }
    public RWManager(DataSetOfObjectsClass cls) {
        super(cls);
    }
    public void openManager(String name, boolean read) throws IOException {
        super.openManager(name,read);
        try {
            if(read)
                openInputFile(name);
            else
                openOutputFile(name);
        } catch(FileNotFoundException io) {
            throw new IOException("File not found '" + name + "'");
        }
    }
    public void closeManager() throws IOException {
        super.closeManager();
        if(reading)
            closeInputFile();
        else
            closeOutputFile();
    }
    public void openOutputFile(String name) throws FileNotFoundException {
        outStream = new PrintWriter(new FileOutputStream(name));
    }
    public void openOutputFile(PrintWriter wrt) {
        outStream = wrt;
    }
    public void closeOutputFile() throws IOException {
        outStream.close();
    }
    public void printLine(String line) {
        outStream.println(line);
    }
    public void printString(String line) {
        outStream.print(line);
    }
    public void openInputFile(String name) throws FileNotFoundException {
        inStream = new BufferedReader(new FileReader(name));
    }
    public void closeInputFile() throws IOException {
        if(inStream != null) 
            inStream.close();
        inStream = null;
    }
    public String readNextLineAbsolute() throws IOException {
        String next = inStream.readLine();
        return next;
    }    
    public String readNextLine() throws IOException {
        String next = "";
        while(next.length() == 0) {
            next = inStream.readLine();
            if(next.startsWith("%"))
                next = "";
        }
        return next;
    }
    public Document parseXMLFile(String filename) throws IOException {
        
                // Step 1: create a DocumentBuilderFactory and configure it
        
        DocumentBuilderFactory dbf =
            DocumentBuilderFactory.newInstance();

        // Optional: set various configuration options
        dbf.setValidating(false);
        //dbf.setIgnoringComments(false);
        //dbf.setIgnoringElementContentWhitespace(false);
        //dbf.setCoalescing(false);
        // The opposite of creating entity ref nodes is expanding them inline
        //dbf.setExpandEntityReferences(true);

        // At this point the DocumentBuilderFactory instance can be saved
        // and reused to create any number of DocumentBuilder instances
        // with the same configuration options.

        // Step 2: create a DocumentBuilder that satisfies the constraints
        // specified by the DocumentBuilderFactory
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException pce) {
            System.err.println(pce);
            System.exit(1);
        }

        // Set an ErrorHandler before parsing
        OutputStreamWriter errorWriter =
            new OutputStreamWriter(System.err, "UTF-8");
        //db.setErrorHandler(
        //    new MyErrorHandler(new PrintWriter(errorWriter, true)));

        // Step 3: parse the input file
        xmlDocument = null;
        try {
            xmlDocument = db.parse(new File(filename));
        } catch (SAXException se) {
            System.err.println(se.getMessage());
            xmlDocument = null;
        } catch (IOException ioe) {
            System.err.println(ioe);
            xmlDocument = null;
        }
        return xmlDocument;
    }
}
