/*
 * BaseDataAlgorithmSetup.java
 *
 * Created on July 3, 2001, 3:58 PM
 */

package xml.structure;
import java.io.IOException;
import blurock.coreobjects.*;
import blurock.core.*;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

/*
/**
 *
 * @author  reaction
 * @version 
 */
public class BaseDataAlgorithmSetup extends blurock.coreobjects.BaseDataObject {
    BaseDataClassObject classObject;
    
    public String[] inputObjects  = new String[0];
    public String[] outputObjects = new String[0];
   
    /** Creates new BaseDataAlgorithmSetup */
    public BaseDataAlgorithmSetup(BaseDataClassObject obj) {
        classObject = obj;
        Name = "Algorithm";
    }
    public void Read(RWManager io) throws IOException {
    }
    public void XMLRead(Element classnode) {
        NodeList inpnames = classnode.getElementsByTagName("input-parameters");
        NodeList outnames = classnode.getElementsByTagName("output-parameters");
        if(inpnames.getLength() > 0) {
              Element inpnode = (Element) inpnames.item(0);
              NodeList inpnodes = inpnode.getElementsByTagName("parameter");
              inputObjects = new String[inpnodes.getLength()];
              fillInParameters(inputObjects,inpnodes);
        }
        if(outnames.getLength() > 0) {
              Element outnode = (Element) outnames.item(0);
              NodeList outnodes = outnode.getElementsByTagName("parameter");
              outputObjects = new String[outnodes.getLength()];
              fillInParameters(outputObjects,outnodes);
        }
    }
    void fillInParameters(String[] names, NodeList nodes) {
        for(int i=0;i<nodes.getLength();i++) {
            Element node = (Element) nodes.item(i);
            names[i] = node.getAttribute("value");
        }
        
    }
    public void Write(RWManager io) throws IOException {
        io.printLine("<algorithm-info>");
        io.printLine("<input-parameters>");
        for(int inp=0;inp<inputObjects.length;inp++) {
         io.printLine("<parameter value=\"" +
                             inputObjects[inp] +
                             "\" />");
        }
        io.printLine("</input-parameters>");
        io.printLine("<output-parameters>");
        for(int inp=0;inp<outputObjects.length;inp++) {
         io.printLine("<parameter value=\"" +
                             outputObjects[inp] +
                             "\" />");
        }
        io.printLine("</output-parameters>");
        io.printLine("</algorithm-info>");
    }
    
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man,DataObjectClass cls) {
        return new DBaseDataAlgorithmSetup(man,this,cls);
    }
      public String[] listOfNames() {
          return classObject.listOfNames();
      }  
}
