
package ModelosTablas;

import GestionTramiteDocumentos.Entidades.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class mdlTipoTramite extends AbstractTableModel {
    private List<TipoTramite> unidadTramite;
    private String[] titulos = {"Id","Nombre","Tiempo estimado"};
    private Boolean[] editable ={false,false,false};
    
    public mdlTipoTramite(List<TipoTramite> tipoTramites) {
        this.unidadTramite=tipoTramites;
    }

    @Override
    public int getRowCount(){
        return this.unidadTramite.size();
    }
    @Override
    public int getColumnCount(){
        return this.titulos.length;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return editable[columnIndex];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object dato = null;
        if(columnIndex==0){
            dato = rowIndex+1;
        }else if(columnIndex==1){
            dato = unidadTramite.get(rowIndex).getNombre();
        }else if(columnIndex==2){
            dato = unidadTramite.get(rowIndex).getTiempo_estimado();
        }
        return dato;
    }

    @Override
    public String getColumnName(int column) {
        return titulos[column];
    }
    
    
    
}
