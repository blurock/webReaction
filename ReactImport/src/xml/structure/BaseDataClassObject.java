/*
 * BaseDataClassObject.java
 *
 * Created on May 11, 2001, 2:37 PM
 */

package xml.structure;
import java.io.IOException;
import blurock.coreobjects.*;
import blurock.core.*;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Attr;

/**
 *
 * @author  reaction
 * @version 
 */
public class BaseDataClassObject extends blurock.coreobjects.BaseDataObject {
    int memberCount = 0;
    BaseDataSetOfObjects memberObjects;
    BaseDataClassInfo cInfo;
    BaseDataClassInfo javaInfo;
    public String className;
    public String superClassName;
    public String environmentName;
    public BaseDataAlgorithmSetup algorithmInfo = null;
    
         String cnames[] = {"emptyconstructor", "copyconstructor", "destructor", "initialization",
                           "clone", "copyclone", "print", "encode", "read", "xml"};
         String cdescr[] = {"Generate Empty Class Constructor",
                           "Generate Copy Class Constructtor",
                           "Generate Class Destructor",
                           "Generate 'Initialization'",
                           "Generate 'Clone'",
                           "Generate 'CopyClone'",
                           "Generate 'print'",
                           "Generate 'EncodeThis' and 'DecodeThis'",
                           "Generate 'Read' and 'Write'",
                           "Generate 'XMLRead' and 'XMLWrite'"};

         String jnames[] = {"empty", "copy", "clone", "read", "xml"};
         String jdescr[] = {"Generate Empty Constructor",
                           "Generate Copy Constructtor",
                           "Generate 'Clone' and 'CopyClone'",
                           "Generate 'Read' and 'Write'",
                           "Generate 'XMLRead' and 'XMLWrite'"};
    
/** Creates new BaseDataClassObject */
    public BaseDataClassObject(int id,String name, String sname, String env,boolean init) {
        className = name;
        environmentName = env;
        superClassName = sname;
        
        memberObjects = new BaseDataSetOfObjects();
        infoInit(id,init);
    }
    public BaseDataClassObject(int id,String env,boolean init) {
        className = "NewObject";
        environmentName = env;
        superClassName = "Object";
        
        memberObjects = new BaseDataSetOfObjects();
        infoInit(id,init);
    }
    void infoInit(int id,boolean init) {
        cInfo = new BaseDataClassInfo(id,"C",
                             cnames,cdescr,init);
        javaInfo = new BaseDataClassInfo(id,"java",
                                       jnames,jdescr,init);
    }
    public void addNewMember(BaseDataMember mem) {
        System.out.println("Adding a member to Object: " + mem.Name);
        memberObjects.AddObject(mem);
        memberCount = memberCount + 1;
        Object[] set = memberObjects.setAsArray();
        for(int i=0;i<set.length;i++) {
            BaseDataMember obj = (BaseDataMember) set[i];
            System.out.println("Members: " + obj.Name);
        }
    }
    public void setup(BaseDataClassInfo cinfo, BaseDataClassInfo javainfo) {
        cInfo = cinfo;
        javaInfo = javainfo;
    }
    public void XMLRead(Node node) {
        Element classnode = (Element) node;
        
        NodeList clsname  = classnode.getElementsByTagName("class-name");
        NodeList sclsname = classnode.getElementsByTagName("superclass-name");
        NodeList pubmems  = classnode.getElementsByTagName("class-member-pub");
        NodeList prvmems  = classnode.getElementsByTagName("class-member-priv");
        NodeList genmems  = classnode.getElementsByTagName("generate-flag");
        NodeList algmems  = classnode.getElementsByTagName("algorithm-info");
        
        Element clsnode = (Element) clsname.item(0);
        //className = filterName(clsnode.getAttribute("value"));
        className = clsnode.getAttribute("value");
        Name = className;
        Element sclsnode = (Element) sclsname.item(0);
        superClassName = sclsnode.getAttribute("value");
        //superClassName = filterName(sclsnode.getAttribute("value"));
        
        if(algmems.getLength() > 0) {
            Element algnode = (Element) algmems.item(0);
            algorithmInfo = new BaseDataAlgorithmSetup(this);
            algorithmInfo.XMLRead(algnode);
        }
        
        for(int ipub=0;ipub<pubmems.getLength();ipub++) {
            Element child = (Element) pubmems.item(ipub);
            addMember(child,true);
        }
        for(int iprv=0;iprv<prvmems.getLength();iprv++) {
            Element child = (Element) prvmems.item(iprv);
            addMember(child,false);
        }
        for(int igen=0;igen<genmems.getLength();igen++) {
            Element child = (Element) genmems.item(igen);
            NamedNodeMap nodemap = child.getAttributes();
            int length = nodemap.getLength();
            String language = "C";
            String flagName = "unknown";
            for(int i=0;i<nodemap.getLength();i++) {
               Attr n = (Attr) nodemap.item(i);
               if(n.getName().equals("language")) {
                   language = n.getValue();
               } else if(n.getName() == "routine") {
                   flagName = n.getValue();
               }
            }
            if(language.equals("C"))
                cInfo.FillInGenerateFlag(flagName,language);
            else
                javaInfo.FillInGenerateFlag(flagName,language);
        }
    }
    String filterName(String name) {
            String sn = name;
            if(name.startsWith("BaseData"))
                  sn = name.substring(8);
            return sn;
    }
  void addMember(Node child, boolean pub) {
      BaseDataMember member = new BaseDataMember();
      member.XMLRead(child);
      member.Name = new String(member.MemberName);
      addNewMember(member);
      member.PublicMember = pub;
     }
    public void Read(RWManager io) throws IOException {
    }
    
    public void Write(RWManager io) throws IOException {
        Object[] set = memberObjects.setAsArray();
        System.out.println("Number of Members: " + set.length);
        io.printLine("<class-name value=\"" +
                            className +
                            "\"/>");
        io.printLine("<superclass-name value=\"" +
                            superClassName +
                            "\"/>");

        for(int i=0;i<set.length;i++) {
            BaseDataMember obj = (BaseDataMember) set[i];
            obj.Write(io);
        }
        cInfo.Write(io);
        javaInfo.Write(io);
        if(algorithmInfo != null) 
            algorithmInfo.Write(io);
    }
    
    public Object[] memberSet() {
        return memberObjects.setAsArray();
    }
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man,DataObjectClass cls) {
        return new DBaseDataClassObject(man,this,cls);
    }
    public String[] listOfNames() {
    Object[] objs = memberSet();
    String[] names = new String[objs.length];
    for(int i=0;i<objs.length;i++) {
        BaseDataMember mem = (BaseDataMember) objs[i];
        names[i] = mem.MemberName;
    }
    return names;
}

}
