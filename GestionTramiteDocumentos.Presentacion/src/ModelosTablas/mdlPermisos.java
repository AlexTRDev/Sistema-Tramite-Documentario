
package ModelosTablas;

import GestionTramiteDocumentos.Entidades.UnidadTramite;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class mdlPermisos extends AbstractTableModel {
        
    private final Class tipoColumnas[] = {Boolean.class,Object.class,Object.class,Object.class};
    private final String Columnas[] = {"||", "UNIDAD DE TRÁMITE", "FECHA DE INICIO", "FECHA DE FINAL"};
    private final Boolean editables[] = {true,false,true,true};
    
    private List<UnidadTramite> oUTramite = new ArrayList<UnidadTramite>();
    
    public mdlPermisos(List<UnidadTramite> oUTramite) {
        this.oUTramite = oUTramite;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return editables[columnIndex];
    }
    
    @Override
    public int getRowCount() {
        return oUTramite.size();
    }

    @Override
    public int getColumnCount() {
        return Columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        switch(columnIndex){
            case 0: return oUTramite.get(rowIndex).getEstado();
            case 1: return oUTramite.get(rowIndex).getNombre();
            case 2: return oUTramite.get(rowIndex).getFechaInicio();
            case 3: return oUTramite.get(rowIndex).getFechaFinal();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return Columnas[column];
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        super.setValueAt(value, rowIndex, columnIndex);
        
        switch (columnIndex) {
            case 0: oUTramite.get(rowIndex).setEstado((boolean)value);
                break;
            case 1: oUTramite.get(rowIndex).setNombre(String.valueOf(value));
                break;
            case 2:  oUTramite.get(rowIndex).setFechaInicio(String.valueOf(value));
                break;
            case 3:  oUTramite.get(rowIndex).setFechaFinal(String.valueOf(value));
                break;
        }
    }

    @Override
    public Class<?> getColumnClass(int rowIndex) {
        return tipoColumnas[rowIndex];
    }
}
