/*
 * InputMechanism.java
 *
 * Created on February 7, 2001, 6:03 PM
 */

package react.mechanisms;
import blurock.core.*;
import java.io.*;
import java.util.StringTokenizer;
import react.mechanisms.*;
import react.molecules.*;
import react.utilities.*;
import react.common.*;
import utilities.*;
import utilities.OutputFrame;

/**
 *
 * @author  reaction
 * @version 
 */
public class InputMechanism extends javax.swing.JPanel {

    TopReactionMenu Top;
    ManageListOfMolecules submols;
    /** Creates new form InputMechanism */
    public InputMechanism(TopReactionMenu top) {
        Top = top;
        initComponents ();
        rxnUnits.add("KJoule-cm3");
        rxnUnits.add("Joule-cm3");
        rxnUnits.add("KJoule-m3");
        rxnUnits.add("Joule-cm3");
        rxnUnits.add("KCalorie-cm3");
        rxnUnits.add("Calorie-cm3");
        rxnUnits.add("KCalorie-m3");
        rxnUnits.add("Calorie-m3");
        thermoUnits.add("GCKiloJoules-Mole");
        thermoUnits.add("GCKiloCalories-Mole");
        thermoUnits.add("GCCalories-Molecule");
        thermoUnits.add("GCJoules-Molecule");
        thermoUnits.add("GCJoules-Mole");
        thermoUnits.add("KCalorie-cm3");
        printAlgorithm.add("PrintMechStandardShortWithReverse");
        printAlgorithm.add("PrintMechStandardShort");
        printAlgorithm.add("PrintMechStandard");
        printAlgorithm.add("PrintMechLaTeX");






    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        inputRoot = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        outputRoot = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        authorText = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        sourceText = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        noteText = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        rxnUnits = new java.awt.Choice();
        jLabel6 = new javax.swing.JLabel();
        thermoUnits = new java.awt.Choice();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        printAlgorithm = new java.awt.Choice();
        jPanel5 = new javax.swing.JPanel();
        inputButton = new javax.swing.JButton();
        printButton = new javax.swing.JButton();
        readMols = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        formGraph = new javax.swing.JButton();
        listOfMolecules = new ManageListOfMolecules(Top,"Submechanism Molecules",30);
        
        setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        jPanel1.setLayout(new java.awt.GridLayout(1, 4));
        jPanel1.setToolTipText("Root Name of Mechanism Files");
        
        jLabel1.setText("Input Root");
          jPanel1.add(jLabel1);
          
          
        inputRoot.setText("Test-Combined");
          jPanel1.add(inputRoot);
          
          
        jLabel8.setText("Output Root");
          jPanel1.add(jLabel8);
          
          
        outputRoot.setText("Test");
          jPanel1.add(outputRoot);
          
          
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridwidth = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(jPanel1, gridBagConstraints1);
        
        
        jPanel2.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints2;
        jPanel2.setBorder(new javax.swing.border.TitledBorder("Mechanism Reference"));
        
        jPanel3.setLayout(new java.awt.GridLayout(3, 2));
          jPanel3.setMinimumSize(new java.awt.Dimension(400, 70));
          
          jLabel2.setText("Author");
            jPanel3.add(jLabel2);
            
            
          authorText.setPreferredSize(new java.awt.Dimension(200, 16));
            authorText.setText("Edward S. Blurock");
            authorText.setMinimumSize(new java.awt.Dimension(200, 16));
            jPanel3.add(authorText);
            
            
          jLabel3.setText("Source");
            jPanel3.add(jLabel3);
            
            
          sourceText.setPreferredSize(new java.awt.Dimension(200, 16));
            sourceText.setText("Generated");
            jPanel3.add(sourceText);
            
            
          jLabel4.setText("Note");
            jPanel3.add(jLabel4);
            
            
          noteText.setPreferredSize(new java.awt.Dimension(200, 16));
            noteText.setText("from react");
            jPanel3.add(noteText);
            
            gridBagConstraints2 = new java.awt.GridBagConstraints();
          gridBagConstraints2.gridwidth = 0;
          gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
          jPanel2.add(jPanel3, gridBagConstraints2);
          
          
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridwidth = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(jPanel2, gridBagConstraints1);
        
        
        jPanel4.setLayout(new java.awt.GridLayout(1, 4));
        jPanel4.setBorder(new javax.swing.border.TitledBorder("Mechanism Units"));
        
        jLabel5.setText("Reaction");
          jLabel5.setToolTipText("The units used for manipulating the reaction information");
          jPanel4.add(jLabel5);
          
          
        rxnUnits.setFont(new java.awt.Font ("Dialog", 0, 11));
          rxnUnits.setBackground(new java.awt.Color (204, 204, 204));
          rxnUnits.setForeground(java.awt.Color.black);
          jPanel4.add(rxnUnits);
          
          
        jLabel6.setText("Thermo");
          jLabel6.setToolTipText("The units used for manipulating the thermodynamic data");
          jPanel4.add(jLabel6);
          
          
        thermoUnits.setFont(new java.awt.Font ("Dialog", 0, 11));
          thermoUnits.setBackground(new java.awt.Color (204, 204, 204));
          thermoUnits.setForeground(java.awt.Color.black);
          jPanel4.add(thermoUnits);
          
          
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridwidth = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(jPanel4, gridBagConstraints1);
        
        
        jPanel6.setLayout(new java.awt.GridLayout(1, 2));
        
        jLabel7.setText("Print Algorithm Type");
          jPanel6.add(jLabel7);
          
          
        printAlgorithm.setFont(new java.awt.Font ("Dialog", 0, 11));
          printAlgorithm.setName("choice7");
          printAlgorithm.setBackground(new java.awt.Color (204, 204, 204));
          printAlgorithm.setForeground(java.awt.Color.black);
          jPanel6.add(printAlgorithm);
          
          
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridwidth = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(jPanel6, gridBagConstraints1);
        
        
        jPanel5.setLayout(new java.awt.GridLayout(1, 3));
        
        inputButton.setToolTipText("Initiate Input of Mechanism");
          inputButton.setText("Input");
          inputButton.addMouseListener(new java.awt.event.MouseAdapter() {
              public void mouseClicked(java.awt.event.MouseEvent evt) {
                  inputMechanism(evt);
              }
          }
          );
          jPanel5.add(inputButton);
          
          
        printButton.setToolTipText("Print out Mechanism in standard format");
          printButton.setText("Print");
          printButton.addMouseListener(new java.awt.event.MouseAdapter() {
              public void mouseClicked(java.awt.event.MouseEvent evt) {
                  printMechanism(evt);
              }
          }
          );
          jPanel5.add(printButton);
          
          
        readMols.setText("Read Molelcules");
          readMols.addMouseListener(new java.awt.event.MouseAdapter() {
              public void mouseClicked(java.awt.event.MouseEvent evt) {
                  readMols(evt);
              }
          }
          );
          jPanel5.add(readMols);
          
          
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridwidth = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(jPanel5, gridBagConstraints1);
        
        
        jPanel7.setBorder(new javax.swing.border.TitledBorder("Convert To Graph"));
        
        formGraph.setText("Form Graph");
          formGraph.addMouseListener(new java.awt.event.MouseAdapter() {
              public void mouseClicked(java.awt.event.MouseEvent evt) {
                  formGraphMouseClicked(evt);
              }
          }
          );
          jPanel7.add(formGraph);
          
          
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridwidth = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(jPanel7, gridBagConstraints1);
        
        
        java.awt.GridBagConstraints gridBagConstraints3;
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        add(listOfMolecules, gridBagConstraints1);
        
    }//GEN-END:initComponents

  private void formGraphMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formGraphMouseClicked
    RunAlgorithm runalg = new RunAlgorithm(Top,"MechanismAsGraph",true);
    runalg.run();
    runalg.showResults();
  }//GEN-LAST:event_formGraphMouseClicked

  private void printMechanism(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printMechanism
// Add your handling code here:
      String inputname = inputRoot.getText();
      String rootname = outputRoot.getText();
      String fileclassS = new String(rootname + "PrintClass.inp");
      String fileargsS = new String(rootname + "Print.inp");

      String[] molnames = listOfMolecules.moleculeList.getElements();
      String rxnunits = rxnUnits.getSelectedItem();
      String thermounits = thermoUnits.getSelectedItem();
      
      try {
        FileOutputStream fileclass = new FileOutputStream(fileclassS);
        PrintStream inpclass = new PrintStream(fileclass);
        FileOutputStream fileargs = new FileOutputStream(fileargsS);
        PrintStream inpargs = new PrintStream(fileargs);

        writePrintClassFile(new PrintStream(fileclass));
        writePrintArgFile(new PrintStream(fileargs),
                          inputname,rootname,molnames,
                          rxnunits,thermounits);
        ReadFile rf = new ReadFile(Top,fileclassS,fileargsS,true);
        RunAlgorithm run = new RunAlgorithm(Top,printAlgorithm.getSelectedItem(),false);
        run.run();
        OutputFrame fr = new OutputFrame(run.commandOutput);
        fr.show();
        } catch(IOException ios) {
          System.out.println("Could not open file");
      }
  }//GEN-LAST:event_printMechanism

  private void writePrintClassFile(PrintStream inpclass) {
       inpclass.println("ObjectClasses:");
       inpclass.println("END");
       inpclass.println("ClassNamePairs:");
       inpclass.println("PrintParameters  KeySet");
       inpclass.println("SubMolecules   KeyWords");
       inpclass.println("END");
       inpclass.println("%% -------------------------------------------------");
       inpclass.println("ObjectClasses:");
       inpclass.println("END");
       inpclass.println("%% -------------------------------------------------");
       inpclass.println("ClassNamePairs:");
       inpclass.println("END");
       inpclass.println("%% -------------------------------------------------");
  }
  private void writePrintArgFile(PrintStream inpargs, 
                        String inputname, String rootname,
                        String[] molnames,
                        String rxnunits,String thermounits) {
       inpargs.println("Print Mechanism Print the mechanism");
       inpargs.println("Attributes:");
       inpargs.println("%% ------------------------------------");
       inpargs.println("%% PrintMechanism   KeySet");
       inpargs.println("%% ------------------------------------");
       inpargs.println(inputname);
       inpargs.println("StandardMechanism");
       inpargs.println(rxnunits);
       inpargs.println(thermounits);
       inpargs.println(thermounits);
       inpargs.println(thermounits);
       inpargs.println(rootname);
       inpargs.println("END");
       inpargs.println("%% ------------------------------------");
       inpargs.println("%% SubMolecules   KeyWords");
       inpargs.println("%% ------------------------------------");
       for(int i=0;i<molnames.length;i++) {
            inpargs.println(molnames[i]);
       }
       inpargs.println("END");
       inpargs.println("%% ------------------------------------");
       inpargs.println("%% No Instances");
       inpargs.println("%% ------------------------------------");
       inpargs.println("END");
  }  
  private void readMols(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_readMols
// Add your handling code here:
    FileToString f = new FileToString("Submechanism Molecules",".","mol");
    StringTokenizer strtoken = new StringTokenizer(f.outputString);
    String[] molnames = new String[strtoken.countTokens()];
    int count = 0;
    while(strtoken.hasMoreTokens()) {
        String name = strtoken.nextToken();
        molnames[count] = name;
        count++;
    }
    String[] reducedlist = new String[count];
    for(int i=0;i<count;i++) {
        reducedlist[i] = molnames[i];
        System.out.println("Molecule: '" + reducedlist[i] + "'");
    }
    listOfMolecules.moleculeList.addNewItems(reducedlist);
  }//GEN-LAST:event_readMols

  private void inputMechanism(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inputMechanism
      
      String rootname = inputRoot.getText();
      String fileclassS = new String(rootname + "Class.inp");
      String fileargsS = new String(rootname + ".inp");
      
      String[] molnames = listOfMolecules.moleculeList.getElements();
      String author = authorText.getText();
      String source = sourceText.getText();
      String note = noteText.getText();
      
      try {
          // This is here because it has to start new each time....
          Top.quitSystemProcess();
          Top.startSystemProcess();
        FileOutputStream fileclass = new FileOutputStream(fileclassS);
        PrintStream inpclass = new PrintStream(fileclass);
        FileOutputStream fileargs = new FileOutputStream(fileargsS);
        PrintStream inpargs = new PrintStream(fileargs);

        writeClassFile(inpclass);
        inpclass.close();
        writeArgFile(inpargs,rootname,molnames,author,source,note);
        inpargs.close();
        ReadFile rf = new ReadFile(Top,fileclassS,fileargsS,true);
        RunAlgorithm run = new RunAlgorithm(Top,"InputGenerated",false);
        run.run();
        run.showResults();
      } catch(IOException ios) {
          System.out.println("Could not open file");
      }
  }//GEN-LAST:event_inputMechanism

  void writeClassFile(PrintStream inpclass) {
       inpclass.println("ObjectClasses:");
       inpclass.println("DataType: MoleculeSummarySet The set of standard molecule summaries");
       inpclass.println("StandardMoleculeSummary  MoleculeSummary");
       inpclass.println("END");
       inpclass.println("ClassNamePairs:");
       inpclass.println("Generated    KeyWords");
       inpclass.println("RefGenerated    LiteratureReference");
       inpclass.println("StandardMoleculeSummary StandardMoleculeSummary");
       inpclass.println("Mechanism   String");
       inpclass.println("END");
       inpclass.println("%% -------------------------------------------------");
       inpclass.println("ObjectClasses:");
       inpclass.println("END");
       inpclass.println("%% -------------------------------------------------");
       inpclass.println("ClassNamePairs:");
       inpclass.println("END");
       inpclass.println("%% -------------------------------------------------");
  }
  void writeArgFile(PrintStream inpargs,
            String rootname, String[] molnames, 
            String author, String source, String note) {
       inpargs.println("Input   1 Input Mechanism");
       inpargs.println("Attributes:");
       inpargs.println("% ---------------------------------------");
       inpargs.println("% Generated    KeyWords");
       inpargs.println("% ---------------------------------------");
       inpargs.println(rootname);
       inpargs.println(Top.Defaults.dbaseName.getValue());
       inpargs.println(Top.Defaults.molDatabaseName.getValue());
       inpargs.println(Top.Defaults.rxnDatabaseName.getValue());
       inpargs.println(Top.Defaults.mechDatabaseName.getValue());
       inpargs.println(Top.Defaults.chemkinClass.getValue());
       inpargs.println(Top.Defaults.chemkinCoeffsName.getValue());
       inpargs.println(Top.Defaults.chemkinMoleculeName.getValue());
       inpargs.println("RefGenerated");
       inpargs.println("StandardMoleculeSummary");
       inpargs.println("END");
       inpargs.println("% -----------------------------------------");
       inpargs.println("%  Ref     RxnDataLiteratureReference");
       inpargs.println("% -----------------------------------------");
       inpargs.println(source + " ; " + author + " ; " + note );
       inpargs.println("% -----------------------------------------");
       inpargs.println("% StandardMoleculeSummary StandardMoleculeSummary");
       inpargs.println("% -----------------------------------------");

       for(int i=0;i<molnames.length;i++) {
            inpargs.print(molnames[i] + " ");
            inpargs.print(Top.Defaults.chemkinCoeffsName.getValue() + " ");
            inpargs.println(Top.Defaults.chemkinMoleculeName.getValue());
       }
       inpargs.println("END");
       inpargs.println(inputRoot.getText());
       inpargs.println("% -----------------------------------------");
       inpargs.println("END");
  }
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JPanel jPanel1;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JTextField inputRoot;
  private javax.swing.JLabel jLabel8;
  private javax.swing.JTextField outputRoot;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JPanel jPanel3;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JTextField authorText;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JTextField sourceText;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JTextField noteText;
  private javax.swing.JPanel jPanel4;
  private javax.swing.JLabel jLabel5;
  private java.awt.Choice rxnUnits;
  private javax.swing.JLabel jLabel6;
  private java.awt.Choice thermoUnits;
  private javax.swing.JPanel jPanel6;
  private javax.swing.JLabel jLabel7;
  private java.awt.Choice printAlgorithm;
  private javax.swing.JPanel jPanel5;
  private javax.swing.JButton inputButton;
  private javax.swing.JButton printButton;
  private javax.swing.JButton readMols;
  private javax.swing.JPanel jPanel7;
  private javax.swing.JButton formGraph;
  private react.molecules.ManageListOfMolecules listOfMolecules;
  // End of variables declaration//GEN-END:variables

}
