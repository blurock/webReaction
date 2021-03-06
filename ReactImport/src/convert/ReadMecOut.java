/*
 * ReadMecOut.java
 *
 * Created on December 16, 2002, 3:18 PM
 */

package convert;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.*;
import javax.swing.JFileChooser;
import utilities.ErrorFrame;
/**
 *
 * @author  reaction
 */
public class ReadMecOut extends javax.swing.JPanel {
    StringTokenizer speciesFile = null;
    StringTokenizer mecoutFile = null;
    FileWriter mechOutput = null;
    FileWriter equationsOut = null;
    String current = ".";
    MecOutReactionSet set = null;
    SpeciesList lst = null;
    SpeciesList equlist = null;
    /** Creates new form ReadMecOut */
    public ReadMecOut() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        MecOutButton = new javax.swing.JButton();
        MecOutFileName = new javax.swing.JTextField();
        SpeciesButton = new javax.swing.JButton();
        SpeciesFileName = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        mechanismButton = new javax.swing.JButton();
        MechanismField = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        steadyStateRadial = new javax.swing.JRadioButton();
        SteadyStateButton = new javax.swing.JButton();
        SteadyStateListName = new javax.swing.JTextField();
        equationsOutNameButton = new javax.swing.JButton();
        equationsOutNameField = new javax.swing.JTextField();
        GenerateEquations = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridLayout(2, 2));

        jPanel1.setBorder(new javax.swing.border.TitledBorder("Input Files"));
        jPanel1.setToolTipText("Input Files for Conversion");
        MecOutButton.setText("MECOUT");
        MecOutButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                MecOutButtonMousePressed(evt);
            }
        });

        jPanel1.add(MecOutButton);

        MecOutFileName.setText("MECOUT.txt");
        jPanel1.add(MecOutFileName);

        SpeciesButton.setText("SPECIES");
        SpeciesButton.setEnabled(false);
        SpeciesButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                SpeciesButtonMousePressed(evt);
            }
        });

        jPanel1.add(SpeciesButton);

        SpeciesFileName.setText("SPECIES_LIST.txt");
        jPanel1.add(SpeciesFileName);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(jPanel1, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridLayout(1, 2));

        jPanel3.setBorder(new javax.swing.border.TitledBorder("Output File"));
        mechanismButton.setText("Mechanism");
        mechanismButton.setEnabled(false);
        mechanismButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                mechanismButtonMousePressed(evt);
            }
        });

        jPanel3.add(mechanismButton);

        MechanismField.setText("Mechanism");
        MechanismField.setToolTipText("The name of the mechanism");
        jPanel3.add(MechanismField);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(jPanel3, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel2.setToolTipText("Command");
        jButton1.setText("Convert");
        jButton1.setToolTipText("Convert");
        jButton1.setEnabled(false);
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(jButton1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(jPanel2, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridLayout(2, 1));

        jPanel4.setBorder(new javax.swing.border.TitledBorder("Species Equations"));
        jPanel5.setLayout(new java.awt.GridLayout(3, 2));

        steadyStateRadial.setSelected(true);
        steadyStateRadial.setText("Steday State Species");
        jPanel5.add(steadyStateRadial);

        SteadyStateButton.setText("Species List");
        SteadyStateButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SteadyStateButtonMouseClicked(evt);
            }
        });

        jPanel5.add(SteadyStateButton);

        SteadyStateListName.setText("SteadyState.txt");
        jPanel5.add(SteadyStateListName);

        equationsOutNameButton.setText("Equations File");
        equationsOutNameButton.setToolTipText("Set equations file name");
        equationsOutNameButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                equationsOutNameButtonMousePressed(evt);
            }
        });

        jPanel5.add(equationsOutNameButton);

        equationsOutNameField.setText("equations.txt");
        equationsOutNameField.setToolTipText("Name of the equations files");
        jPanel5.add(equationsOutNameField);

        GenerateEquations.setText("Write Equations");
        GenerateEquations.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GenerateEquationsMouseClicked(evt);
            }
        });

        jPanel5.add(GenerateEquations);

        jPanel4.add(jPanel5);

        add(jPanel4, new java.awt.GridBagConstraints());

    }//GEN-END:initComponents

    private void equationsOutNameButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_equationsOutNameButtonMousePressed
        try {
            equationsOutNameField.setText(getFileName(".txt"));
        } catch(IOException io) {}
    }//GEN-LAST:event_equationsOutNameButtonMousePressed

    private void GenerateEquationsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GenerateEquationsMouseClicked
         if(steadyStateRadial.isSelected()) {
            try {
                equlist = null;
                readSteadyStateSpecies();
            } catch(IOException io) {
                printError("Error reading steady state species");
            }
        }
        if(equlist == null)
            equlist = lst;
        if(lst != null && set != null) {
            String SpeciesArrayName = "c";
            String SpeciesRateName = "k";
            SpeciesEquations speciesequations = 
                new SpeciesEquations(steadyStateRadial.isSelected(), SpeciesArrayName, SpeciesRateName,set);
            speciesequations.addEquations(equlist);
            String equations = speciesequations.toString();
            try {
                equationsOut = new FileWriter(equationsOutNameField.getText());
                
                equationsOut.write(equations);
                equationsOut.close();
                System.out.println(equations);
            } catch(IOException io) {
                printError("Error in writing equations");
            }
        } else {
            printError("Species and Reactions not set up");
        }
    }//GEN-LAST:event_GenerateEquationsMouseClicked
    private void readSteadyStateSpecies() throws IOException {
       equlist = new SpeciesList();
       StringTokenizer tok = TokenizeInputFile(SteadyStateListName.getText()," \t\n");
       equlist.read(tok);
     }
    private void SteadyStateButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SteadyStateButtonMouseClicked
        try {
            SteadyStateListName.setText(getFileName(".txt"));
        } catch(IOException io) {}
    }//GEN-LAST:event_SteadyStateButtonMouseClicked

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        try {
            lst = new SpeciesList();
            lst.read(speciesFile);
            set = new MecOutReactionSet(lst);
            Object[] mols = lst.toArray();
            set.read(mecoutFile,mols);
            String out = set.toString();
            System.out.println(out);
            mechOutput.write(out);
            mechOutput.close();
        } catch(IOException io) {
            System.out.println(io);
        }
    }//GEN-LAST:event_jButton1MousePressed

    private void mechanismButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mechanismButtonMousePressed
        try {
            String name = getFileName("mecout");
            mechOutput = new FileWriter(name);
            MechanismField.setText(name);
            jButton1.setEnabled(true);
       } catch(IOException io) {
           printError(io);
       }

    }//GEN-LAST:event_mechanismButtonMousePressed
    private String getFileName(String suffix) throws IOException {
        JFileChooser chooser = new JFileChooser(current);
        int returnVal = chooser.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile().getAbsolutePath();
        } else {
            throw new IOException("No File Chosen");
        }
    }
    private void SpeciesButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SpeciesButtonMousePressed
       try {
            String name = getFileName("mecout");
            speciesFile = TokenizeInputFile(name,"\n");
            SpeciesFileName.setText(name);
            mechanismButton.setEnabled(true);
       } catch(IOException io) {
           printError(io);
       }

    }//GEN-LAST:event_SpeciesButtonMousePressed

    private void MecOutButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MecOutButtonMousePressed
       try {
            String name = getFileName("species");
            MecOutFileName.setText(name);
            mecoutFile = TokenizeInputFile(name,"\n");
            SpeciesButton.setEnabled(true);
       } catch(IOException io) {
           printError(io);
       }
    }//GEN-LAST:event_MecOutButtonMousePressed
    private StringTokenizer TokenizeInputFile(String name, String tokchars) throws IOException {
        StringTokenizer tok = null;
        try {
        File inputFile = new File(name);
        FileReader in = new FileReader(inputFile);
        int c;
	StringBuffer buf = new StringBuffer();
        while ((c = in.read()) != -1)
	    buf.append((char) c);
	String content = buf.toString();
        tok = new StringTokenizer(content,tokchars);
        in.close();
        }
        catch(FileNotFoundException io) {
            throw new IOException(io.toString());
        }
        
        return tok;
    }
    private void printError(Exception ex) {
        ErrorFrame fr = new ErrorFrame(ex.toString());
        System.err.println(ex);
        fr.show();
    }
    private void printError(String ex) {
        ErrorFrame fr = new ErrorFrame(ex);
        System.err.println(ex);
        fr.show();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton mechanismButton;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField MechanismField;
    private javax.swing.JButton MecOutButton;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton steadyStateRadial;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JButton equationsOutNameButton;
    private javax.swing.JTextField MecOutFileName;
    private javax.swing.JButton SteadyStateButton;
    private javax.swing.JTextField SpeciesFileName;
    private javax.swing.JTextField equationsOutNameField;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton GenerateEquations;
    private javax.swing.JTextField SteadyStateListName;
    private javax.swing.JButton SpeciesButton;
    // End of variables declaration//GEN-END:variables
    
}
