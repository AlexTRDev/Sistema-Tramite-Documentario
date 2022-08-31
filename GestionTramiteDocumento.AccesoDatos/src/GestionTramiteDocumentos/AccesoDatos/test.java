/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionTramiteDocumentos.AccesoDatos;

import Conector.Conexion;

/**
 *
 * @author ENVY
 */
public class test {
    
    public static void main(String[] args) {
        Conexion c = null;
        
        try{
            c=new Conexion();
            c.Abrir(true);
            
//            DocumentoEmitidoAD oDocumentoEmitidoAD = new DocumentoEmitidoAD(c.getConnection());
//            String sql = "SELECT * FROM documento_emitido WHERE id_unidad_tramite = 1 AND fecha_recepcion >= 'Fri May 15 00:00:00 COT 2015' AND fecha_emision <= 'Sat Dec 26 09:10:54 COT 2015'  ORDER BY id_tipo_documento,numero;";
//            System.out.println(oDocumentoEmitidoAD.ConsultarXFiltros(sql));
            
            System.out.println(c.getConnection());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
}
