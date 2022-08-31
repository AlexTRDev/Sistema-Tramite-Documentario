
package ModelosTablas;

import GestionTramiteDocumentos.Entidades.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class mdlTipoDocumentos extends AbstractTableModel {
    private List<TipoDocumento> tipoDocumentos;
    private String[] titulos = {"Id","Nombre"};
    private Boolean[] editable ={false,false};
    
    public mdlTipoDocumentos(List<TipoDocumento> tipoDocumentos) {
        this.tipoDocumentos=tipoDocumentos;
    }

    @Override
    public int getRowCount(){
        return this.tipoDocumentos.size();
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
            dato = tipoDocumentos.get(rowIndex).getNombre();
        }
        return dato;
    }

    @Override
    public String getColumnName(int column) {
        return titulos[column];
    }
    
    
    
}
