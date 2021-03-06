/*
 * IgnitionTimesOptions.java
 *
 * Created on June 2, 2005, 10:26 AM
 */

package ignition.times;

/**
 *
 * @author  reaction
 */
public class IgnitionTimesOptions extends javax.swing.JPanel {
    
    /** Creates new form IgnitionTimesOptions */
    public IgnitionTimesOptions() {
        initComponents();
        temperature.setup("Temperature", "Temperature", 16,8);
        ohvalue.setup("OH Value", "t([OH] Max)",19,24);
        co2value.setup("CO2 Value", "t([CO2*] Max)",19,24);
        pressure.setup("Maximum Pressure Gradient","t(p_PrimeMax)",19,24);
        tprime.setup("Temperaure Gradient Value", "t(t_PrimeMax)",19,24);
        kamenetzki.setup("Kamenetzki Value", "t(F Kamenetzki)", 19,24);
    }
    public boolean includeTitle() {
        return titleCheck.isSelected();
    }
    public boolean includeInverseT() {
        return inverseTCheck.isSelected();
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jPanel1 = new javax.swing.JPanel();
        temperature = new ignition.times.IsolateValue();
        ohvalue = new ignition.times.IsolateValue();
        co2value = new ignition.times.IsolateValue();
        pressure = new ignition.times.IsolateValue();
        tprime = new ignition.times.IsolateValue();
        kamenetzki = new ignition.times.IsolateValue();
        dataOutputPanel = new javax.swing.JPanel();
        dataPanel = new javax.swing.JPanel();
        titleCheck = new javax.swing.JCheckBox();
        inverseTCheck = new javax.swing.JCheckBox();
        gnuPanel = new javax.swing.JPanel();
        calcualatedPanel = new javax.swing.JPanel();
        CalcmillisecCheck = new javax.swing.JCheckBox();
        experimentPanel = new javax.swing.JPanel();
        dataMillSecCheck = new javax.swing.JCheckBox();
        expInverseTCheck = new javax.swing.JCheckBox();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.GridLayout(6, 1));

        jPanel1.add(temperature);

        jPanel1.add(ohvalue);

        jPanel1.add(co2value);

        jPanel1.add(pressure);

        jPanel1.add(tprime);

        jPanel1.add(kamenetzki);

        add(jPanel1, java.awt.BorderLayout.CENTER);

        dataOutputPanel.setLayout(new java.awt.BorderLayout());

        dataPanel.setLayout(new java.awt.GridLayout(1, 2));

        dataPanel.setBorder(new javax.swing.border.TitledBorder(null, "Data Output File", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11)));
        titleCheck.setFont(new java.awt.Font("Dialog", 0, 12));
        titleCheck.setSelected(true);
        titleCheck.setText("Include Titles");
        dataPanel.add(titleCheck);

        inverseTCheck.setFont(new java.awt.Font("Dialog", 0, 12));
        inverseTCheck.setSelected(true);
        inverseTCheck.setText("1000/T");
        inverseTCheck.setToolTipText("Include column with 1000/T calculated");
        dataPanel.add(inverseTCheck);

        dataOutputPanel.add(dataPanel, java.awt.BorderLayout.NORTH);

        gnuPanel.setLayout(new java.awt.GridLayout(1, 2));

        gnuPanel.setBorder(new javax.swing.border.TitledBorder(null, "Gnu Plot Output", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11)));
        calcualatedPanel.setLayout(new java.awt.GridLayout());

        calcualatedPanel.setBorder(new javax.swing.border.TitledBorder("Calculated Data"));
        CalcmillisecCheck.setFont(new java.awt.Font("Dialog", 0, 12));
        CalcmillisecCheck.setSelected(true);
        CalcmillisecCheck.setText("Output in milliseconds");
        calcualatedPanel.add(CalcmillisecCheck);

        gnuPanel.add(calcualatedPanel);

        experimentPanel.setLayout(new java.awt.GridLayout());

        experimentPanel.setBorder(new javax.swing.border.TitledBorder("Experimental Data"));
        dataMillSecCheck.setFont(new java.awt.Font("Dialog", 0, 12));
        dataMillSecCheck.setSelected(true);
        dataMillSecCheck.setText("Data in milliseconds");
        experimentPanel.add(dataMillSecCheck);

        expInverseTCheck.setFont(new java.awt.Font("Dialog", 0, 12));
        expInverseTCheck.setSelected(true);
        expInverseTCheck.setText("Data is 1000/T");
        expInverseTCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expInverseTCheckActionPerformed(evt);
            }
        });

        experimentPanel.add(expInverseTCheck);

        gnuPanel.add(experimentPanel);

        dataOutputPanel.add(gnuPanel, java.awt.BorderLayout.SOUTH);

        add(dataOutputPanel, java.awt.BorderLayout.NORTH);

    }//GEN-END:initComponents

    private void expInverseTCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expInverseTCheckActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_expInverseTCheckActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JCheckBox CalcmillisecCheck;
    private javax.swing.JPanel calcualatedPanel;
    public ignition.times.IsolateValue co2value;
    public javax.swing.JCheckBox dataMillSecCheck;
    private javax.swing.JPanel dataOutputPanel;
    private javax.swing.JPanel dataPanel;
    public javax.swing.JCheckBox expInverseTCheck;
    private javax.swing.JPanel experimentPanel;
    private javax.swing.JPanel gnuPanel;
    private javax.swing.JCheckBox inverseTCheck;
    private javax.swing.JPanel jPanel1;
    public ignition.times.IsolateValue kamenetzki;
    public ignition.times.IsolateValue ohvalue;
    public ignition.times.IsolateValue pressure;
    public ignition.times.IsolateValue temperature;
    private javax.swing.JCheckBox titleCheck;
    public ignition.times.IsolateValue tprime;
    // End of variables declaration//GEN-END:variables
    
}
