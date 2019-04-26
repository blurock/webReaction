/*
 * ReactionDefaults.java
 *
 * Created on January 20, 2001, 11:16 AM
 */

package react.common;

/**
 *
 * @author  reaction
 * @version 
 */
public class ReactionDefaults extends javax.swing.JFrame {

    /** Creates new form ReactionDefaults */
    public ReactionDefaults() {
        initComponents ();
        dialogSizeX.setValueName("dialogSizeX");
        dialogSizeX.setDefaultValue(600);
        dialogSizeY.setValueName("dialogSizeY");
        dialogSizeY.setDefaultValue(600);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        dialogSizeX = new react.common.StandardValue();
        dialogSizeY = new react.common.StandardValue();
        databaseDefaults = new java.awt.Panel();
        dbaseName = new StandardName("Database Name",
        "MolecularStructureDataBase");
        molDatabaseName = new StandardName("Molecule Database",
        "Molecule");
        rxnDatabaseName = new StandardName("Reaction Database",
        "Reaction");
        mechDatabaseName = new StandardName("Mechanism Database",
        "Mechanisms");
        
        Thermo = new java.awt.Panel();
        chemkinClass = new StandardName("Chemkin Class",
        "StandardChemkin");
        chemkinCoeffsName = new StandardName("Chemkin Coeffs Name",
        "MoleculeChemkin");
        
        chemkinMoleculeName = new StandardName("Chemkin Molecule Name",
        "ChemkinName");
        
        mechPanel = new java.awt.Panel();
        moleculeSummaryName = new StandardName("Molecule Summary Name",
        "MoleculeSummary");
        referenceName = new StandardName("Standard Reference Name",
        "MechRef");
        
        setName("ReactionDefaults");
        setTitle("ReactionDefaults");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        }
        );
        
        jTabbedPane1.setName("Scripts");
        
        jPanel1.setLayout(new java.awt.GridLayout(5, 1));
          jPanel1.setPreferredSize(new java.awt.Dimension(400, 100));
          jPanel1.setName("Scripts");
          jPanel1.setMinimumSize(new java.awt.Dimension(100, 350));
          jPanel1.setToolTipText("Scripts");
          jPanel1.setMaximumSize(new java.awt.Dimension(1000, 400));
          
          dialogSizeX.setLayout(new java.awt.GridLayout(2, 3));
            jPanel1.add(dialogSizeX);
            
            
          dialogSizeY.setLayout(new java.awt.GridLayout(2, 3));
            jPanel1.add(dialogSizeY);
            
            jTabbedPane1.addTab("Win", null, jPanel1, "The default sizes of windows and frames");
          
          
        databaseDefaults.setLayout(new java.awt.GridLayout(5, 1));
          databaseDefaults.setFont(new java.awt.Font ("Dialog", 0, 11));
          databaseDefaults.setName("DataBasePanel");
          databaseDefaults.setBackground(new java.awt.Color (153, 153, 153));
          databaseDefaults.setForeground(java.awt.Color.black);
          
          dbaseName.setLayout(new java.awt.FlowLayout());
            dbaseName.setName("DatabaseName");
            databaseDefaults.add(dbaseName);
            
            
          molDatabaseName.setLayout(new java.awt.FlowLayout());
            molDatabaseName.setName("Database Name");
            databaseDefaults.add(molDatabaseName);
            
            
          rxnDatabaseName.setLayout(new java.awt.FlowLayout());
            databaseDefaults.add(rxnDatabaseName);
            
            
          mechDatabaseName.setLayout(new java.awt.FlowLayout());
            databaseDefaults.add(mechDatabaseName);
            
            jTabbedPane1.addTab("DB", null, databaseDefaults, "Standard Reaction Database Parameters");
          
          
        Thermo.setLayout(new java.awt.GridLayout(5, 1));
          Thermo.setFont(new java.awt.Font ("Dialog", 0, 11));
          Thermo.setBackground(new java.awt.Color (153, 153, 153));
          Thermo.setForeground(java.awt.Color.black);
          
          chemkinClass.setLayout(new java.awt.FlowLayout());
            Thermo.add(chemkinClass);
            
            
          chemkinCoeffsName.setLayout(new java.awt.FlowLayout());
            Thermo.add(chemkinCoeffsName);
            
            
          chemkinMoleculeName.setLayout(new java.awt.FlowLayout());
            Thermo.add(chemkinMoleculeName);
            
            jTabbedPane1.addTab("Thermo", null, Thermo, "Names of Thermodynamic Classes and Variables");
          
          
        mechPanel.setLayout(new java.awt.GridLayout(5, 1));
          mechPanel.setFont(new java.awt.Font ("Dialog", 0, 11));
          mechPanel.setBackground(new java.awt.Color (153, 153, 153));
          mechPanel.setForeground(java.awt.Color.black);
          
          moleculeSummaryName.setLayout(new java.awt.GridLayout(2, 3));
            mechPanel.add(moleculeSummaryName);
            
            
          referenceName.setLayout(new java.awt.GridLayout(2, 3));
            mechPanel.add(referenceName);
            
            jTabbedPane1.addTab("Mech", null, mechPanel, "Mechanism Parameter, Classes and Names");
          
          
        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);
        
    }//GEN-END:initComponents

    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit (0);
    }//GEN-LAST:event_exitForm

    /**
    * @param args the command line arguments
    */
    public static void main (String args[]) {
        new ReactionDefaults ().show ();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel jPanel1;
    public react.common.StandardValue dialogSizeX;
    public react.common.StandardValue dialogSizeY;
    private java.awt.Panel databaseDefaults;
    public react.common.StandardName dbaseName;
    public react.common.StandardName molDatabaseName;
    public react.common.StandardName rxnDatabaseName;
    public react.common.StandardName mechDatabaseName;
    private java.awt.Panel Thermo;
    public react.common.StandardName chemkinClass;
    public react.common.StandardName chemkinCoeffsName;
    public react.common.StandardName chemkinMoleculeName;
    private java.awt.Panel mechPanel;
    public react.common.StandardName moleculeSummaryName;
    public react.common.StandardName referenceName;
    // End of variables declaration//GEN-END:variables

    public int maxMoleculesInReactions = 3; 

    public StandardValue panelSizeX = new StandardValue("panelSizeX",400);
    
    public StandardValue panelSizeY = new StandardValue("panelSizeY",400);
    
}
