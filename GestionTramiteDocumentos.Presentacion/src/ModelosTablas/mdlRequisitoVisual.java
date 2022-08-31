
package ModelosTablas;

import GestionTramiteDocumentos.Entidades.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class mdlRequisitoVisual extends AbstractTableModel {
    private List<DocRec> docRec;
    private String[] titulos = {"k","Nombre"};
    private Boolean[] editable ={false,false};
    
    public mdlRequisitoVisual(List<DocRec> docRec) {
        this.docRec=docRec;
    }

    @Override
    public int getRowCount(){
        return this.docRec.size();
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
            if(docRec.get(rowIndex).getRequisito().getEstado()){
                dato = "SI";
            }else{
                dato = "NO";
            }
        }else if(columnIndex==1){
            dato = docRec.get(rowIndex).getRequisito().getDescipcion();
        }
        
        return dato;
    }

    @Override
    public String getColumnName(int column) {
        return titulos[column];
    }
    
    
    
}
