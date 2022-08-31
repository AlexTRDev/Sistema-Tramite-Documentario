
package ModelosTablas;

import GestionTramiteDocumentos.Entidades.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class mdlRuta extends AbstractTableModel {
    private String columnas[] ={"K","Fecha de recepcion","Recepcionado en"};
    private List<Ruta> rutas;
    private Class[] tipoDato = {String.class, String.class, Object.class, Object.class};
    private Boolean[] editable = {false, false, false};
    public mdlRuta(List<Ruta> rutas){
        this.rutas = rutas;
    }
    @Override
    public int getRowCount(){
        return this.rutas.size();
    }
    @Override
    public int getColumnCount(){
        return this.columnas.length;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return tipoDato[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return editable[columnIndex];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
        Object dato = null;
        
        if(columnIndex ==0){
            dato ="OK";
        }else if(columnIndex ==1){
            dato = rutas.get(rowIndex).getFechaHora_recepcion();
        }else if(columnIndex ==2){
            dato = rutas.get(rowIndex).getUnidadTramite().getUnidadOrganizativa().getAbreviatura()+"-"+rutas.get(rowIndex).getUnidadTramite().getNombre();
        }
        return dato;
    }
    @Override
    public String getColumnName(int column){
        return columnas[column];
    } 
    
}