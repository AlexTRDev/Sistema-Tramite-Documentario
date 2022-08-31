/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdlCeldas;

import Util.FormatoFecha;
import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.AbstractCellEditor;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;


/**
 *
 * @author Alex
 */
public class CalendarCellEditor extends AbstractCellEditor implements TableCellEditor{
    JComponent component = new JDateChooser();

    public CalendarCellEditor() {
        ((JDateChooser)component).getDateEditor().addPropertyChangeListener(new PropertyChangeListener(){ 
            public void propertyChange(PropertyChangeEvent e) {} 
        }); //Aquí agregaremos la funcionalidad que queremos
        
    }

    @Override
    public Object getCellEditorValue() {
        return FormatoFecha.getFecha((JDateChooser)component,"dd/MM/yyyy");
    }

    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object value, boolean bln, int i, int i1) {
        return ((JDateChooser)component);
    }
    
}
