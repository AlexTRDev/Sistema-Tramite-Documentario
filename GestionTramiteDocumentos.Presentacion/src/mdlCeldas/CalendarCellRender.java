/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdlCeldas;

import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Alex
 */
public class CalendarCellRender extends JLabel implements TableCellRenderer{
    JComponent component = new JDateChooser();

    public CalendarCellRender() {
        ((JDateChooser)component).getDateEditor().addPropertyChangeListener(new PropertyChangeListener(){ 
            public void propertyChange(PropertyChangeEvent e) {} 
        }); //Aquí agregaremos la funcionalidad que queremos
        
    }

    
    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
        return null;
    }
    
}
