
package ModelosTablas;

import GestionTramiteDocumentos.Entidades.UnidadOrganizativa;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class mdlUnidadOrganizativa extends AbstractTableModel {
    private List<UnidadOrganizativa> unidadOrganizativas;
    private String[] titulos = {"Id","Nombre","Abreviatura"};
    private Boolean[] editable ={false,false,false};
    
    public mdlUnidadOrganizativa(List<UnidadOrganizativa> unidadOrganizativas) {
        this.unidadOrganizativas=unidadOrganizativas;
    }

    @Override
    public int getRowCount(){
        return this.unidadOrganizativas.size();
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
            dato = unidadOrganizativas.get(rowIndex).getNombre();
        }else if(columnIndex==2){
            dato = unidadOrganizativas.get(rowIndex).getAbreviatura();
        }
        return dato;
    }
    
}
