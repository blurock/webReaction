/*
 * BuildClassXML.java
 *
 * Created on May 8, 2001, 5:13 PM
 */

package xml.structure;
import utilities.WaitForAnswer;
import utilities.BaseFrame;
import xml.ClassPropertiesPanel;
import xml.XMLOptions;
import blurock.coreobjects.*;
import blurock.core.*;
import blurock.utilities.*;
import java.io.*;
import blurock.core.RunScriptWithOutput;
import link.ReactLink;
import org.w3c.dom.Node;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Attr;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.*;
import javax.swing.JPanel;

/**
 *
 * @author  reaction
 * @version 
 */
public class BuildClassXML extends javax.swing.JPanel {
    ObjectAsTreeNode topNode;
    int memberCount = 0;
    int cMemberCount = 0;
    int jMemberCount = 0;
    int classCount = 0;
    int objectCount = 0;
    
      ObjectAsTreeNode classtop = null;
      ObjectAsTreeNode objecttop = null;
      BaseDataClassObject clsobj = null;
      BaseDataClassClass  clscls = null;
      ObjectAsTreeNode oinfo    = null;
      ObjectAsTreeNode cinfo    = null;
      DBaseDataExternalInfo dinfo = null;
      
      boolean isObject = true;

    BaseDataSetOfObjects classSet = new BaseDataSetOfObjects();
    String[] listOfClasses = new String[100];
    int totalClassCount = 0;
    ObjectAsTreeNode selected;
    
    BaseDataExternalInfo externalInfo = null;
    
    DataObjectClass nodeNameClass = 
        new DataObjectClass(1,"NodeName","Simple Node Class");
    DataObjectClass memberClass = 
        new DataMemberClass();
    DataClassObjectClass objectClass = new DataClassObjectClass();
    DataClassClassClass classClass = new DataClassClassClass();
    DataClassInfoClass classInfoClass = new DataClassInfoClass();
    DataExternalInfoClass extInfoClass = new DataExternalInfoClass();
    DataAlgorithmSetupClass algClass = new DataAlgorithmSetupClass();
    
    DataSetOfObjectsClass classes;
    ObjectDisplayManager dispManager;
    ObjectAsTreeNode members;
    ObjectAsTreeNode info;
    XMLOptions xmlOptions;
    
      public boolean objectType = true;
      public boolean operationType =  false;
      public boolean algorithmType = false;
      public String toAddClassName = "NewClass";
      public String toAddSuperClassName = "Object";
      
    /** Creates new form BuildClassXML */
    public BuildClassXML(XMLOptions xmlopt) {
        xmlOptions = xmlopt;
        initComponents ();
        classes = new DataSetOfObjectsClass(1,"SetOfObjects","Set of Objects Class");
        classes.AddClass(nodeNameClass);
        classes.AddClass(memberClass);
        classes.AddClass(extInfoClass);
        dispManager = new ObjectDisplayManager(classes);
        setup(nodeNameClass,dispManager);
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        modulePanel = new javax.swing.JPanel();
        envLabel = new javax.swing.JLabel();
        environmentName = new javax.swing.JTextField();
        moduleLabel = new javax.swing.JLabel();
        moduleName = new javax.swing.JTextField();
        buttonPanel = new javax.swing.JPanel();
        newButton = new javax.swing.JButton();
        add = new javax.swing.JButton();
        remove = new javax.swing.JButton();
        readButton = new javax.swing.JButton();
        writeButton = new javax.swing.JButton();
        localButton = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        hierarchy = new blurock.core.Hierarchy();
        jPanel1 = new javax.swing.JPanel();
        includeProto = new javax.swing.JButton();
        sourceProto = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        setBackground(new java.awt.Color(204, 204, 204));
        modulePanel.setLayout(new java.awt.GridLayout(1, 4));

        modulePanel.setBorder(new javax.swing.border.TitledBorder("Module"));
        envLabel.setText("Environment");
        modulePanel.add(envLabel);

        environmentName.setText("CoreObjects");
        modulePanel.add(environmentName);

        moduleLabel.setText("Module");
        modulePanel.add(moduleLabel);

        moduleName.setText("CoreDataObjects");
        modulePanel.add(moduleName);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(modulePanel, gridBagConstraints);

        buttonPanel.setLayout(new java.awt.GridLayout());

        buttonPanel.setBorder(new javax.swing.border.TitledBorder("Operations"));
        newButton.setText("New");
        newButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                newButtonMouseClicked(evt);
            }
        });

        buttonPanel.add(newButton);

        add.setText("Add/Modify");
        add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addMouseClicked(evt);
            }
        });

        buttonPanel.add(add);

        remove.setText("Remove");
        buttonPanel.add(remove);

        readButton.setText("Read");
        readButton.setToolTipText("Read in the Class Information");
        readButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                readButtonMouseClicked(evt);
            }
        });

        buttonPanel.add(readButton);

        writeButton.setText("Write");
        writeButton.setToolTipText("Write out the Class information");
        writeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                writeButtonMouseClicked(evt);
            }
        });

        buttonPanel.add(writeButton);

        localButton.setSelected(true);
        localButton.setLabel("local");
        buttonPanel.add(localButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(buttonPanel, gridBagConstraints);

        jPanel2.setBorder(new javax.swing.border.TitledBorder("Member Information"));
        jPanel2.setMinimumSize(new java.awt.Dimension(322, 334));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(500, 300));
        hierarchy.setPreferredSize(new java.awt.Dimension(500, 1000));
        jScrollPane2.setViewportView(hierarchy);

        jPanel2.add(jScrollPane2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(jPanel2, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        jPanel1.setBorder(new javax.swing.border.TitledBorder("Local Prototype Tests"));
        includeProto.setText("Include");
        includeProto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                includeProtoMouseClicked(evt);
            }
        });

        jPanel1.add(includeProto);

        sourceProto.setText("Source");
        sourceProto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sourceProtoMouseClicked(evt);
            }
        });

        jPanel1.add(sourceProto);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(jPanel1, gridBagConstraints);

    }//GEN-END:initComponents
  private void newButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newButtonMouseClicked
// Add your handling code here:
       classes = new DataSetOfObjectsClass(1,"SetOfObjects","Set of Objects Class");
       memberCount = 0;
       cMemberCount = 0;
       jMemberCount = 0;
       classCount = 0;
       objectCount = 0;
       listOfClasses = new String[100];
       totalClassCount = 0;
       
       classes.AddClass(nodeNameClass);
       classes.AddClass(memberClass);
       setup(nodeNameClass,dispManager);
  }//GEN-LAST:event_newButtonMouseClicked

  private void readButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_readButtonMouseClicked
// Add your handling code here:
      String filename = CreateXMLFileName();
      //String filename = moduleName.getText() + ".xml";
      System.out.println("Reading to: " + filename);
      RWManager rw = new RWManager(classes);
      
      try {
      rw.parseXMLFile(filename);
      Node topnode = rw.getModuleNode();
      String name = topnode.getNodeName();
      String val = topnode.getNodeValue();
      System.out.println("Node(" + name + ") = '" + val + "'");
      setTopModuleInfo(topnode);
      Element topele = (Element) topnode;
      NodeList envnamenodes  = topele.getElementsByTagName("environment-names");
      NodeList modnamenodes  = topele.getElementsByTagName("module-names");
      NodeList clsnamenodes  = topele.getElementsByTagName("analysis-class");
      
      System.out.println("Number of Dependencies: " + envnamenodes.getLength() + 
            "  " + modnamenodes.getLength() );
      Element envnode = (Element) envnamenodes.item(0);
      externalInfo.setEnvironmentNames(envnode);
      Element modnode = (Element) modnamenodes.item(0);
      externalInfo.setEnvironmentNames(modnode);
      
      for(int i=0;i<clsnamenodes.getLength();i++) {
        Element classnode = (Element) clsnamenodes.item(i);
        NodeList clsname  = classnode.getElementsByTagName("class-name");
        Element clsnode = (Element) clsname.item(0);
        String classname = clsnode.getAttribute("value");
        if(classname.startsWith("Data"))         
              fillInNodeClass(classnode); 
        else
              fillInNodeObject(classnode);          
      }
      } catch(IOException io) {
            System.out.println(io);
      }
  }//GEN-LAST:event_readButtonMouseClicked
  private void addToClassList(String name) {
      listOfClasses[totalClassCount] = new String(name);
      totalClassCount++;
  }
  private void fillInNodeClass(Node classnode) {
        BaseDataClassClass mems = new BaseDataClassClass(classCount,environmentName.getText(),false);
        mems.XMLRead(classnode);
        mems.Identification = classCount;
        addToClassList(mems.className);
        classSet.AddObject(mems);
        classCount = classCount + 1;
        DBaseDataClassClass dmem = new DBaseDataClassClass(dispManager,mems,classClass);
        ObjectAsTreeNode classtop = new ObjectAsTreeNode(dmem);
        hierarchy.addNodeToSet(classtop,topNode);
        
        cinfo    = createInfoNode(mems.Name);
        hierarchy.addNodeToSet(cinfo,classtop);        
        createSubInfoClass(cinfo,mems);
        Object[] objset = mems.memberObjects.setAsArray();
        AddMemberObjects(classtop,objset);
  }
  private void fillInNodeObject(Node classnode) {
        BaseDataClassObject mems = new BaseDataClassObject(objectCount,environmentName.getText(),false);
        mems.XMLRead(classnode);
        mems.Identification = objectCount;
        addToClassList(mems.className);
        classSet.AddObject(mems);
        objectCount = objectCount + 1;
        DBaseDataClassObject dmem = new DBaseDataClassObject(dispManager,mems,objectClass);
        ObjectAsTreeNode classtop = new ObjectAsTreeNode(dmem);
        hierarchy.addNodeToSet(classtop,topNode);

        if(mems.algorithmInfo != null) {
           DBaseDataAlgorithmSetup dsetup = new DBaseDataAlgorithmSetup(dispManager,
                              mems.algorithmInfo,algClass);
           ObjectAsTreeNode algnode = new ObjectAsTreeNode(dsetup);
           hierarchy.addNodeToSet(algnode,classtop);
        }
        cinfo    = createInfoNode(mems.Name);
        hierarchy.addNodeToSet(cinfo,classtop);        
        createSubInfoObject(cinfo,mems);
        Object[] objset = mems.memberObjects.setAsArray();
        AddMemberObjects(classtop,objset);
  }
  void AddMemberObjects(ObjectAsTreeNode classtop, Object[] objset) {
      for(int i=0;i<objset.length;i++) {
          BaseDataMember member = (BaseDataMember) objset[i];
         addMemberToHierarchy(classtop,member);
      }
  }
  String language;
  String flagName;
  void generateFlagsObject(Node node, BaseDataClassObject obj) {
      DetermineGenerateFlag(node);
      if(language.equals("C"))
          FillInGenerateFlag(obj.cInfo);
      else
          FillInGenerateFlag(obj.javaInfo);
  }
  void generateFlagsClass(Node node, BaseDataClassClass obj) {
      DetermineGenerateFlag(node);
      if(language.equals("C"))
          FillInGenerateFlag(obj.cInfo);
      else
          FillInGenerateFlag(obj.javaInfo);
  }
  void DetermineGenerateFlag(Node node) {
      NamedNodeMap nodemap = node.getAttributes();
      int length = nodemap.getLength();
      for(int i=0;i<nodemap.getLength();i++) {
          Attr n = (Attr) nodemap.item(i);
          if(n.getName().equals("language")) {
              language = n.getValue();
          } else if(n.getName() == "routine") {
              flagName = n.getValue();
          }
      }
  }
  void FillInGenerateFlag(BaseDataClassInfo inf) {
      int length = inf.flagNames.length;
      System.out.println("flagName: " + flagName + "  " + language);
      int i = 0;
      boolean notdone = true;
      while(i<length && notdone) {
          System.out.println("Compare: '" + inf.flagNames[i]
                     + "'  '" + flagName + "'");
          if(inf.flagNames[i].equals(flagName)) {
              System.out.println("Set it to true");
              notdone = false;
              inf.functionFlags[i] = true;
          }
          i++;
      }
  }
  private void setTopModuleInfo(Node topnode) {
      System.out.println("setTopModuleInfo    Node(" + topnode.getNodeName()
                       + ") = '" + topnode.getNodeValue() + "'");
    NamedNodeMap nodemap = topnode.getAttributes();
    if(nodemap != null) {
      int length = nodemap.getLength();
      for(int i=0;i<nodemap.getLength();i++) {
          Attr node = (Attr) nodemap.item(i);
          if(node.getName().equals("module-name")) {
              moduleName.setText(node.getValue());
          } else if(node.getName().equals("module-set")) {
              environmentName.setText(node.getValue());
          }
      }
    } else {
        System.out.println("No Module Information");
    }
    
  }
  private void sourceProtoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sourceProtoMouseClicked
// Add your handling code here:
      runProcess("analysistopsrc.xsl", "cc");
  }//GEN-LAST:event_sourceProtoMouseClicked

  private void includeProtoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_includeProtoMouseClicked
// Add your handling code here:
      runProcess("analysistopinclude.xsl", "hh");
  }//GEN-LAST:event_includeProtoMouseClicked

  private void writeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_writeButtonMouseClicked
// Add your handling code here:
      String fileName = CreateXMLFileName();
      //String fileName = moduleName.getText() + ".xml";
      System.out.println("Writing to: " + fileName);
      RWManager rw = new RWManager(classes);
      try {
        rw.openOutputFile(fileName);
        //Object[] set = classSet.setAsArray();
        rw.printLine("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        if(localButton.isSelected()) {
            rw.printLine("<!DOCTYPE analysis-module SYSTEM \"analysis.dtd\">");
            rw.printLine("<!-- Grammar referenced in document: \"analysis.dtd\" -->");
        } else {
            rw.printLine("<!DOCTYPE analysis-module SYSTEM \"../../dtd/analysis.dtd\">");
            rw.printLine("<!-- Grammar referenced in document: \"../../dtd/analysis.dtd\" -->");
        }
        rw.printLine("<analysis-module module-name=\"" +
                           moduleName.getText() +
                           "\" module-set=\"" +
                           environmentName.getText() +
                           "\">");

        externalInfo.Write(rw);
        for(int i=0;i<totalClassCount;i++) {
            try {
                rw.printLine("<analysis-class module-name=\"" +
                               moduleName.getText() +
                               "\">");
                BaseDataObject obj = classSet.findObject(listOfClasses[i]);
                System.out.println("Class to write: " + obj.Name);
                obj.Write(rw);
                rw.printLine("</analysis-class>");
            } catch(ObjectNotFoundException ex) {
                System.out.println("Major Error: Class not found : " + listOfClasses[i]);
            }
        }
        rw.printLine("</analysis-module>");
        rw.closeOutputFile();
      } catch(IOException io) {
            System.out.println("OOPs");
      }
  }//GEN-LAST:event_writeButtonMouseClicked

  private void addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addMouseClicked
// Add your handling code here:
      if(hierarchy.nodeSelected) {
          ObjectAsTreeNode selected = hierarchy.selectedNode;
          DBaseDataObject dobj = selected.nodeObject;

          String name = selected.toString();
          System.out.println("Add/Modify: '" + name + "'");
          if(name.equals("Module")) {
              ClassPropertiesPanel pnl = new ClassPropertiesPanel(this);
              BaseFrame fr = new BaseFrame(pnl,"Build Class","Build Class","Build Class");
              WaitForAnswer ans = new WaitForAnswer(pnl);
              ans.show();
              setupNewClass(topNode);
          } else {
              DataObjectClass cls = dobj.OClass;
              if(cls.Name.equals("ExternalInfo")) {
                  System.out.println("External Information");
                  memberFrame(selected,"ExternalInfo");
              } else if(cls.Name.equals("Member")) {
                  System.out.println("Modify Member");
                  BaseDataMember mem = (BaseDataMember) dobj.Object;
                  selected.setUserObject(mem.MemberName);
                  memberFrame(selected,"Member");
              } else if(cls.Name.equals("AlgorithmSetup")) {
                  System.out.println("Modify Algorithm Information");
                  //BaseDataAlgorithmSetup setup = (BaseDataAlgorithmSetup) dobj.Object;
                  //selected.setUserObject(mem.MemberName);
                  memberFrame(selected,"AlgorithmSetup");
              } else if(cls.Name.equals("ClassObject")) {
                  BaseDataClassObject obj = (BaseDataClassObject) dobj.Object;
                  String oname = "Object." + obj.Name + "." + obj.memberCount;
                  System.out.println("Add Member To Object Class: " + oname + "to " + selected.toString());
                  BaseDataMember member = addEmptyMemberToHierarchy(selected,oname);
                  obj.addNewMember(member);
              } else if(cls.Name.equals("ClassClass")) {
                  BaseDataClassClass obj = (BaseDataClassClass) dobj.Object;
                  String cname = "Class." + obj.Name + "." + obj.memberCount;
                  System.out.println("Add Member To Class Class: " + cname + "to " + selected.toString());
                  BaseDataMember member = addEmptyMemberToHierarchy(selected,cname);
                  obj.addNewMember(member);
              } else if(cls.Name.equals("ClassInfo")) {
                  BaseDataClassInfo obj = (BaseDataClassInfo) dobj.Object;
                  String cname = "ClassInfo." + obj.Name;
                  memberFrame(selected,cname);
              }
          } 
          updateUI();
      }
  }//GEN-LAST:event_addMouseClicked
      public BaseDataMember addEmptyMemberToHierarchy(ObjectAsTreeNode top,String name) {
          BaseDataMember member = new BaseDataMember();
          DBaseDataMember dmem = new DBaseDataMember(dispManager,member,memberClass);
          JPanel pnl = dmem.objectAsPanel();
          BaseFrame fr = new BaseFrame(pnl,"Build Member","Build Member","Build Member");
          WaitForAnswer ans = new WaitForAnswer(pnl);
          ans.show();
          member.Name = new String(member.MemberName);
          ObjectAsTreeNode node = new ObjectAsTreeNode(dmem);
          hierarchy.addNodeToSet(node,top);
          return member;
      }
      void addMemberToHierarchy(ObjectAsTreeNode top, BaseDataMember member) {
          DBaseDataMember dmem = new DBaseDataMember(dispManager,member,memberClass);
          ObjectAsTreeNode node = new ObjectAsTreeNode(dmem);
          hierarchy.addNodeToSet(node,top);
      }
      public void memberFrame(ObjectAsTreeNode node, String name) {
          BaseScrollFrame fr = new BaseScrollFrame(node.objectAsPanel(),
                                                     name,name,name);
          fr.pack();
          fr.show();
      }
      public void setup(DataObjectClass cls, ObjectDisplayManager man) {
        hierarchy.setup(cls,man);
        topNode = new ObjectAsTreeNode("Module");

        externalInfo = new BaseDataExternalInfo();
        dinfo = new DBaseDataExternalInfo(dispManager,externalInfo,extInfoClass);
        ObjectAsTreeNode ninfo = new ObjectAsTreeNode(dinfo);
        hierarchy.addNodeToSet(ninfo,topNode);
        
        hierarchy.setTopNode(topNode);
      }
     
      public void setupNewClass(ObjectAsTreeNode top) {
          String oname = "BaseData" + toAddClassName;
          String cname = "Data" + toAddClassName + "Class";

        objecttop = createObjectNode(oname);
        classtop = createClassNode(cname);
        hierarchy.addNodeToSet(objecttop,top);
        hierarchy.addNodeToSet(classtop,top);

        clsobj = (BaseDataClassObject) objecttop.nodeObject.Object;
        clscls = (BaseDataClassClass)  classtop.nodeObject.Object;
        
        oinfo    = createInfoNode(oname);
        cinfo    = createInfoNode(cname);
        createSubInfoObject(oinfo,clsobj);
        createSubInfoClass(cinfo,clscls);

        hierarchy.addNodeToSet(oinfo,objecttop);
        hierarchy.addNodeToSet(cinfo,classtop);
    }
 
    void AddAlgorithmSetupNode(ObjectAsTreeNode top,BaseDataClassObject obj) {
        BaseDataAlgorithmSetup setup = new BaseDataAlgorithmSetup(obj);
        obj.algorithmInfo = setup;
        DBaseDataAlgorithmSetup dsetup = new DBaseDataAlgorithmSetup(dispManager,setup,algClass);
        ObjectAsTreeNode node = new ObjectAsTreeNode(dsetup);
        hierarchy.addNodeToSet(node,top);
    }
     ObjectAsTreeNode createClassNode(String name) {
         String sname = "Data" + toAddSuperClassName + "Class";
        BaseDataClassClass mems = new BaseDataClassClass(classCount, 
                                                         name,
                                                         sname,
                                                         environmentName.getText(),
                                                         true);
        mems.Name = name;
        mems.Identification = classCount;
        addToClassList(mems.className);
        classSet.AddObject(mems);
        classCount = classCount + 1;
        DBaseDataClassClass dmem = new DBaseDataClassClass(dispManager,mems,classClass);
        ObjectAsTreeNode node = new ObjectAsTreeNode(dmem);
        return node;
     }
     ObjectAsTreeNode createObjectNode(String name) {
         String sname = "BaseData" + toAddSuperClassName;
        BaseDataClassObject mems = new BaseDataClassObject(objectCount,
                                                          name,
                                                          sname,
                                                          environmentName.getText(),
                                                          true);
        mems.Name = name;
        mems.Identification = objectCount;
        addToClassList(mems.className);
        classSet.AddObject(mems);
        objectCount = objectCount + 1;
        DBaseDataClassObject dmem = new DBaseDataClassObject(dispManager,mems,objectClass);
        ObjectAsTreeNode node = new ObjectAsTreeNode(dmem);
        if(algorithmType)
            AddAlgorithmSetupNode(node,mems);
        return node;
     }
     ObjectAsTreeNode createMembersNode(String name) {
        BaseDataObject mems = new BaseDataObject("Members." + name,memberCount);
        memberCount = memberCount + 1;
        DBaseDataObject dmem = new DBaseDataObject(dispManager,mems,nodeNameClass);
        ObjectAsTreeNode node = new ObjectAsTreeNode(dmem);
        return node;
     }
      ObjectAsTreeNode createInfoNode(String name) {
        BaseDataObject mems = new BaseDataObject("Class Specs",memberCount);
        memberCount = memberCount + 1;
        DBaseDataObject dmem = new DBaseDataObject(dispManager,mems,nodeNameClass);
        ObjectAsTreeNode node = new ObjectAsTreeNode(dmem);
        return node;
      }
      void createSubInfoObject(ObjectAsTreeNode node, BaseDataClassObject obj) {
        DBaseDataClassInfo dcinfo = new DBaseDataClassInfo(dispManager,obj.cInfo,classInfoClass);
        ObjectAsTreeNode cnode = new ObjectAsTreeNode(dcinfo);
        DBaseDataClassInfo djinfo = new DBaseDataClassInfo(dispManager,obj.javaInfo,classInfoClass);
        ObjectAsTreeNode jnode = new ObjectAsTreeNode(djinfo);
        hierarchy.addNodeToSet(cnode,node);
        hierarchy.addNodeToSet(jnode,node);
     }
      
      void createSubInfoClass(ObjectAsTreeNode node, BaseDataClassClass cls) {
        DBaseDataClassInfo dcinfo = new DBaseDataClassInfo(dispManager,cls.cInfo,classInfoClass);
        ObjectAsTreeNode cnode = new ObjectAsTreeNode(dcinfo);
        DBaseDataClassInfo djinfo = new DBaseDataClassInfo(dispManager,cls.javaInfo,classInfoClass);
        ObjectAsTreeNode jnode = new ObjectAsTreeNode(djinfo);
        hierarchy.addNodeToSet(cnode,node);
        hierarchy.addNodeToSet(jnode,node);
     }
   private void runProcess(String xslfile, String outext) {
      String command =  xmlOptions.defaultDirectories.xmlDirectory.getValue() +
                        "/scripts/ProcessXML.sh " +
                        moduleName.getText() + ".xml " +
                        xmlOptions.defaultDirectories.xmlDirectory.getValue() +
                        "/xsl/" + xslfile + " " +
                        moduleName.getText() + "." + outext;
      ReactLink tLink = new ReactLink();
      RunScriptWithOutput scr = new RunScriptWithOutput(tLink);
      scr.run(command,true);
  }
  String CreateXMLFileName() {
    String fileName = moduleName.getText() + ".xml";
    String xmlfilename = null;
    if(localButton.isSelected()) {
        xmlfilename = fileName;
    } else {
        xmlfilename = xmlOptions.defaultDirectories.currentDevDirectory.getValue()
                      + "/" + environmentName.getText()
                      + "/" + moduleName.getText() 
                      + "/etc/xml/" + environmentName.getText() 
                      + "/" + fileName;
    }
    return xmlfilename;
  }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox localButton;
    private javax.swing.JPanel modulePanel;
    private javax.swing.JButton readButton;
    private javax.swing.JButton newButton;
    private javax.swing.JButton sourceProto;
    private javax.swing.JButton writeButton;
    private javax.swing.JTextField moduleName;
    private javax.swing.JLabel envLabel;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField environmentName;
    private javax.swing.JButton add;
    private javax.swing.JButton remove;
    private javax.swing.JLabel moduleLabel;
    private javax.swing.JPanel jPanel1;
    private blurock.core.Hierarchy hierarchy;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton includeProto;
    // End of variables declaration//GEN-END:variables

}
