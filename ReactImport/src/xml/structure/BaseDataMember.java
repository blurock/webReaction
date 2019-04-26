/*
 * BaseDataMember.java
 *
 * Created on May 10, 2001, 11:27 AM
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
public class BaseDataMember extends blurock.coreobjects.BaseDataObject {

    public String MemberType = "Object";
    public String MemberName = "Name";
    public String descriptionText = "Description";
    
    public boolean PublicMember = true;
    public boolean isVisible = true;
    
    public String Qualifier = "normal";
    
    public boolean SimpleObject = false;
    
    /** Creates new BaseDataMember */
    public BaseDataMember() {
        super();
    }

    public void Read(RWManager io) throws IOException {
    }
    public void XMLRead(Node node) {
      for(Node child = node.getFirstChild(); child != null;
          child = child.getNextSibling()) {
          if(child.getNodeType() == Node.ELEMENT_NODE) {
              Element elenode = (Element) child;
             System.out.println("MemberElement(" + 
                                  child.getNodeName()  + ") = '" +
                                  child.getNodeValue() + "'");
             String name = child.getNodeName();
             String value = child.getNodeValue();
             if(name == "class-member-name") {
                 MemberName = elenode.getAttribute("value");
             } else if(name == "class-member-type") {
                 MemberType = elenode.getAttribute("value");
             } else if(name == "parameter-qualifier") {
                 Qualifier = elenode.getAttribute("value");
             } else if(name == "simple-data-type") {
                 String n = elenode.getAttribute("value");
                 System.out.println("Simple Data Type: : " + n);
                 if(n.equals("true") || n.equals("simple"))
                     SimpleObject = true;
                 else
                     SimpleObject = false;
                 if(SimpleObject)
                     System.out.println("Recognized as: true");
                 else  
                     System.out.println("Recognized as: false");
             } else if(name.equals("member-flags")) {
                 String n = elenode.getAttribute("member-flags-visible");
                 if(n.equals("visible"))
                     isVisible = true;
                 else
                     isVisible = false;
             } else if(name == "generate-flag") {
             }
          }
      }
  }
    
    public void Write(RWManager rw) throws IOException {
        if(PublicMember) 
            rw.printLine("<class-member-pub>");
        else
            rw.printLine("<class-member-priv>");
        rw.printLine("<class-member-name value=\"" + MemberName + "\" />");
        rw.printLine("<class-member-type value=\"" + MemberType + "\" />");
        rw.printLine("<description>");
        rw.printLine(descriptionText);
        rw.printLine("</description>");
        if(isVisible)
            rw.printLine("<member-flags member-flags-visible=\"visible\" />");
        else
            rw.printLine("<member-flags member-flags-visible=\"not-visible\" />");
        rw.printLine("<parameter-qualifier value=\"" + Qualifier + "\" />");
        if(SimpleObject)
            rw.printLine("<simple-data-type value=\"true\" />");
        else
            rw.printLine("<simple-data-type value=\"false\" />");
        
        if(PublicMember) 
            rw.printLine("</class-member-pub>");
        else
            rw.printLine("</class-member-priv>");
        
    }
    
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man,DataObjectClass cls) {
        return new DBaseDataMember(man,this,cls);
    }
    
}
