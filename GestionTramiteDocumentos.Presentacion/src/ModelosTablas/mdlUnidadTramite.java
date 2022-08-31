
package ModelosTablas;

import GestionTramiteDocumentos.Entidades.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class mdlUnidadTramite extends AbstractTableModel {
    private List<UnidadTramite> unidadTramite;
    private String[] titulos = {"Id","Nombre","Abreviatura","Responsable"};
    private Boolean[] editable ={false,false,false,false};
    
    public mdlUnidadTramite(List<UnidadTramite> unidadTramites) {
        this.unidadTramite=unidadTramites;
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
            dato = unidadTramite.get(rowIndex).getAbreviatura();
        }else if(columnIndex==3){
            dato = unidadTramite.get(rowIndex).getResponsable();
        }
        return dato;
    }
    
}
