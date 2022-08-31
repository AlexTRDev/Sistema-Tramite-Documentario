
package ModelosTablas;

import GestionTramiteDocumentos.Entidades.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class mdlRequisitoDocNew extends AbstractTableModel {
    private final Class tipoColumnas[] = {Boolean.class,Object.class};
    private final String Columnas[] = {" ", "NOMBRE"};
    private final Boolean editables[] = {true,false};
    private List<Requisito> requisitos = new ArrayList<Requisito>();

    public mdlRequisitoDocNew(List<Requisito> requisitos) {
        this.requisitos = requisitos;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return editables[columnIndex];
    }
    
    @Override
    public int getRowCount() {
        return requisitos.size();
    }

    @Override
    public int getColumnCount() {
        return Columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0: return requisitos.get(rowIndex).getEstado();
            case 1: return requisitos.get(rowIndex).getDescipcion();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        return Columnas[columnIndex];
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        super.setValueAt(value, rowIndex, rowIndex); 
        
        switch (columnIndex) {
            case 0: requisitos.get(rowIndex).setEstado((boolean)value);
                break;
            case 1: requisitos.get(rowIndex).setDescipcion(String.valueOf(value));
                break;
            default:
                break;
        }
    }

    @Override
    public Class<?> getColumnClass(int column) {
        return tipoColumnas[column];
    }
    
}
