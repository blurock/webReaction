/*
 * PSRTableModel.java
 *
 * Created on October 22, 2006, 6:43 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package react.mechanisms.chemkin.psr;

import javax.swing.table.DefaultTableModel;
/**
 *
 * @author reaction
 */
public class PSRTableModel extends DefaultTableModel {
    /** Creates a new instance of PSRTableModel */
    public PSRTableModel(Object[][] matvalues, String[] headernames) {
        super(matvalues,headernames);
    }
    public Class getColumnClass(int columnIndex) {
            if(columnIndex == 0) 
                return java.lang.String.class;
            else
                return java.lang.String.class;
            }    
}
