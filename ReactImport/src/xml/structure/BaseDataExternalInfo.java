/*
 * BaseDataExternalInfoObject.java
 *
 * Created on June 26, 2001, 10:28 AM
 */

package xml.structure;
import java.io.IOException;
import blurock.coreobjects.*;
import blurock.core.*;
import org.w3c.dom.*;

/**
 *
 * @author  reaction
 * @version 
 */
public class BaseDataExternalInfo extends blurock.coreobjects.BaseDataObject {
    public String[] environmentNames;
    
    public String[] moduleNames;
    
    /** Creates new BaseDataExternalInfoObject */
    public BaseDataExternalInfo() {
        Name = "Dependencies";
        environmentNames = new String[0];
        moduleNames = new String[0];
    }
    public void setEnvironmentList(String[] names) {
        environmentNames = names;
    }
    public void setModuleList(String[] names) {
        moduleNames = names;
    }
    public void Read(RWManager io) throws IOException {
    }
    public void setModuleNames(Element env) {
        NodeList nlist = env.getElementsByTagName("module-name");
        int length = nlist.getLength();
        System.out.println("NameList size: " + length);
        moduleNames = new String[length];
        for(int i=0;i<length;i++) {
            Element node = (Element) nlist.item(i);
            String name = node.getAttribute("value");
            System.out.println("Value: " + name);
            moduleNames[i] = name;
            System.out.println("Module: " + moduleNames[i]);
        }
    }
    public void setEnvironmentNames(Element env) {
        NodeList nlist = env.getElementsByTagName("environment-name");
        int length = nlist.getLength();
        environmentNames = new String[length];
        for(int i=0;i<length;i++) {
            Element node = (Element) nlist.item(i);
            environmentNames[i] = node.getAttribute("value");
        }
    }
    public void Write(RWManager io) throws IOException {
        io.printLine("<environment-names>");
        for(int i=0;i<environmentNames.length;i++) {
            io.printLine("<environment-name value=\"" + environmentNames[i]
                    + "\"/>");
        }
        io.printLine("</environment-names>");
        io.printLine("<module-names>");
        for(int i=0;i<moduleNames.length;i++) {
            io.printLine("<module-name value=\"" + moduleNames[i]
                    + "\"/>");
        }
        io.printLine("</module-names>");
    }
    
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man,DataObjectClass cls) {
        return new DBaseDataExternalInfo(man,this,cls);
    }
    
}
