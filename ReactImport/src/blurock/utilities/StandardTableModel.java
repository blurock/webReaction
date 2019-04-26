/*
 * StandardTable.java
 *
 * Created on August 23, 2002, 8:31 AM
 */

package blurock.utilities;
import java.util.Vector;
import java.awt.Dimension;
import java.awt.Component;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableCellRenderer;
import javax.swing.event.TableModelListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JFormattedTextField;
//import kwb.utilities.table.TableSorter;
/**
 *
 * @author  Raffaella Bellanca
 * @version 
 */
public abstract class StandardTableModel extends javax.swing.table.AbstractTableModel implements TableModelListener {
  protected Object[][] data;
  protected String[] columnNames;
  protected Object[] colsLength;
  //protected TableSorter sorter;
  private DecimalFormat dFormat = (DecimalFormat)NumberFormat.getNumberInstance();

  /** Creates new StandardTable */
  public StandardTableModel(Object[][] d, String[] c) {
    data = d;
    columnNames = c;
  }
/***********************************************************************/
  public StandardTableModel(Vector vRow, Vector vCol) {
    initCols(vCol);
    initRows(vRow);
  }
/***********************************************************************/
  public StandardTableModel() {}
/***********************************************************************/
  public Object[] getColsLength() {return colsLength;}
/***********************************************************************/
  public int getColumnCount() {return columnNames.length;}
/***********************************************************************/
  public Vector getColumn(int col) {
    Vector v = new Vector();
    for(int i=0; i<data.length; i++) {
      v.add(data[i][col]);
    }
    return v;
  }
/***********************************************************************/
  public int getRowCount() {return data.length;}
/***********************************************************************/
  public String getColumnName(int col) {return columnNames[col];}
/***********************************************************************/
  public Object getValueAt(int row, int col) {return data[row][col];}
/***********************************************************************/
  public Class getColumnClass(int c) {return getValueAt(0, c).getClass();}
/***********************************************************************/
  public Dimension getViewSize() {return new Dimension(getXViewSize(), 200);}
/***********************************************************************/
  protected int getXViewSize() {return 600;}
/***********************************************************************/
//  public void setSorter(TableSorter s) {sorter = s;}
/***********************************************************************/  
  public void setValueAt(Object value, int row, int col) {  
    data[row][col] = value;
    fireTableCellUpdated(row, col);
  }
/***********************************************************************/
  public void setColumnLook(TableColumn col) {col.sizeWidthToFit();}
/***********************************************************************/
  public TableCellRenderer getCellRenderer(int row, int column) {return null;}
/***********************************************************************/
  public abstract boolean isCellEditable(int row, int col);
/***********************************************************************/
  protected abstract void setColumnsLength(int maxLen);
/***********************************************************************/
  protected abstract void fillRows(Vector vRow);
/***********************************************************************/
  protected void initRows(Vector vRow) {
    data = new Object[vRow.size()][columnNames.length];
    fillRows(vRow);
  }
/***********************************************************************/
  protected void initCols(Vector vCol) {
    columnNames = new String[vCol.size()];
    fillCols(vCol);
  }
/***********************************************************************/
  protected void setUpToolTipText(TableColumn col, String str) {
    DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
    renderer.setToolTipText(str);
    col.setCellRenderer(renderer);
  }
/***********************************************************************/
  private void fillCols(Vector vCol) {
    for(int i=0; i<columnNames.length; i++) columnNames[i] = (String)vCol.get(i);
  }
/***********************************************************************/
  public void removeRow(int row) {
    Object[][] newData = new Object[data.length-1][columnNames.length];
    for(int i=0; i<row; i++) System.arraycopy(data[i], 0, newData[i], 0, columnNames.length);      
    for(int i=row+1; i<data.length; i++) System.arraycopy(data[i], 0, newData[i-1], 0, columnNames.length); 
    data = newData;
    fireTableRowsDeleted(row, row);
  }
/***********************************************************************/
  public void addRow(Object[] rowData) {
    int num = ((Integer)rowData[0]).intValue();
    for(int i=0; i<data.length; i++) if(num == ((Integer)data[i][0]).intValue()) return; // If the row is already there exit  
    Object[][] newData = new Object[data.length+1][columnNames.length];
    for(int i=0; i<data.length; i++) System.arraycopy(data[i], 0, newData[i], 0, columnNames.length); 
    newData[data.length] = rowData;
    data = newData;
    fireTableRowsInserted(data.length, data.length);
  }
/***********************************************************************/
  public Object[] getRow(int i) {return data[i];}
/***********************************************************************/
  public Object[][] getRows() {return data;}
/***********************************************************************/
  public boolean hasData() {return data.length > 0;}
/***********************************************************************/
// Implementation of the TableModelListener interface, 
    // By default forward all events to all the listeners. 
    public void tableChanged(TableModelEvent e) {
        fireTableChanged(e);
    }
/***********************************************************************/
  //Set up the renderer and editor for the checkBox cells
  class MyDoubleExpRenderer extends javax.swing.JFormattedTextField implements javax.swing.table.TableCellRenderer {   
    public MyDoubleExpRenderer() {
      super(dFormat);
      dFormat.applyPattern("0.00E00");
    }
    public Component getTableCellRendererComponent(JTable t, Object v, boolean isS, boolean hasF, int r, int c) {
      setBackground(new java.awt.Color(226, 216, 183));
      setBorder(javax.swing.BorderFactory.createEmptyBorder());
      setValue(v);
      return this;
    }
  }
 
}
