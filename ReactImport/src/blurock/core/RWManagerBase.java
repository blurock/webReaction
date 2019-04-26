/*
 * RWManagerBase.java
 *
 * Created on February 2, 2002, 11:28 AM
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
import java.util.NoSuchElementException;

import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import org.w3c.dom.*;

/**
 *
 * @author  reaction
 * @version 
 */
public class RWManagerBase extends java.lang.Object {

    public DataSetOfObjectsClass dataClasses;
    protected Document xmlDocument;
    StringTokenizer tokens = null;
    
    public boolean xml = false;
    public boolean xmlTop = true;

    public boolean reading;
    /** Creates new RWManagerBase */
    public RWManagerBase() {
        dataClasses = new DataSetOfObjectsClass();
    }
    public RWManagerBase(DataSetOfObjectsClass cls) {
        dataClasses = cls;
    }
    public void openManager(String name, boolean read)  throws IOException {
        reading = read;
    }
    public void closeManager() throws IOException {
        xmlDocument = null;
        tokens = null;    
        xml = false;
        xmlTop = true;
    }
    public String readNextLine() throws IOException {
        String next = "";
        return next;
    }
    public String nextToken()  throws IOException {
        String next;
        try {
            next = tokens.nextToken();
            if(next.startsWith("%")) {
                String lne = readNextLine();
                tokens = new StringTokenizer(lne);
                next = tokens.nextToken();
            }
        } catch(NoSuchElementException ex) {
            throw new IOException("Error in Next Token");
        }
        return next;
    }
    public String restOfLine() throws IOException {
        StringBuffer buf = new StringBuffer();
        while(tokens.hasMoreTokens()) {
            buf.append(tokens.nextToken());
            buf.append(" ");
        }
        String lne = readNextLine();
        tokens = new StringTokenizer(lne);
        return buf.toString();
    }
    public void checkToken(String name) throws IOException {
        String next = readElement();
        if(!next.equals(name)) {
            IOException ex = new IOException("Wrong Token: expected '" 
            + name + "' got '" + next + "'");
            throw ex;
        }
    }
    public String readElement() throws IOException {
        String next;
        if(tokens == null) {
            String lne = readNextLine();
            tokens = new StringTokenizer(lne);
        }
        if(tokens.hasMoreTokens())
            next = nextToken();
        else {
            String lne = readNextLine();
            tokens = new StringTokenizer(lne);
            if(tokens == null)
                System.out.println("Tokens: null");
            next = nextToken();
        }
        return next;
    }
    public double readDouble() throws IOException {
        double num = 0.0;
        String name = readElement();
        try {
            if(name.equals("-Infinity"))
                num = -1e30;
            else if(name.equals("Infinity"))
                num =  1e30;
            else
                num = Double.parseDouble(name);
        } catch(NumberFormatException exp) {
            throw new IOException("Expected a double value got '" + name + "'");
        }
        return num;
    }
    public int readInteger() throws IOException {
        int num = 0;
        String name = readElement();
        try {
             num = Integer.parseInt(name);
        } catch(NumberFormatException exp) {
            throw new IOException("Expected an integer value got '" + name + "'");
        }
        return num;
    }
    public boolean readBoolean() throws IOException {
        boolean value = false;
        String name = readElement();
        if(name.equals("T"))
            value = true;
        return value;
    }
    public List getSetOfNamesAsList() throws IOException {
        String endString = "END";
        List names = new List();
        String name = readElement();
        while(!name.equals(endString)) {
            names.add(name);
            name = readElement();
        }
        return names;
    }
    boolean whiteSpace(char c) {
        return true;
    }
    public String[] getSetOfNames() throws IOException {
        List names = getSetOfNamesAsList();
        return names.getItems();
    }
    public DataObjectClass getClassFromNextElement() throws IOException {
        String name = readElement();
        try {
            DataObjectClass cls = dataClasses.findClass(name);
            return (DataObjectClass) cls.Clone();
        } catch(ObjectNotFoundException exp) {
            throw new IOException("Class name not found: '" + name + "'");
        }
    }
    public Node getModuleNode() throws IOException {
        Node modulenode = null;
        modulenode = (Node) xmlDocument;
        System.out.println("getModuleNode() 1:  " + modulenode.getNodeName());
        modulenode = modulenode.getFirstChild();
        modulenode = modulenode.getNextSibling();
        modulenode = modulenode.getNextSibling();
      
        //System.out.println("getModuleNode() 2:  " + modulenode.getNodeName());
      for(Node child = modulenode.getFirstChild(); child != null;
          child = child.getNextSibling()) {
              //System.out.println("Child: " + child.getNodeName() 
                        //+ " " + child.getNodeValue());
      }
        
        //System.out.println("getModuleNode() 3:  " + modulenode.getNodeName());
        return modulenode;
    }
    public void printLine(String line) {
        System.out.println(line);
    }
    public void printString(String line) {
        System.out.print(line);
    }
    public void openInputFile(String name) throws FileNotFoundException {
    }
    public void closeInputFile() throws IOException {
    }
    public void openOutputFile(String name) throws FileNotFoundException {
    }
    public void closeOutputFile() throws IOException {
    }

}
