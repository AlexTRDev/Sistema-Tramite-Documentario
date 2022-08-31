package GestionTramiteDocumentos.AccesoDatos;

import Conector.EntidadAD;
import GestionTramiteDocumentos.Entidades.TipoDocumento;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class TipoDocumentoAD extends EntidadAD {
    public TipoDocumentoAD(Connection connection) {
        super(connection);
    }

    public void Insertar(TipoDocumento  objeto) throws Exception {
        try {
            String dml = "insert into tipo_documento("+
                    "nombre) values("+
                    "'"+objeto.getNombre()+"');";
            System.out.println(dml);
            EjecutarSentenciaDML(dml);
        }
        catch(Exception e) {
            throw e;
        }
    }

    public void Modificar(TipoDocumento objeto) throws Exception {
       try {
            String dml = "update tipo_documento set "+
                    "nombre = '"+objeto.getNombre()+"' "+
                    "where id_tipo_documento = "+objeto.getId_tipoDocumento()+";";
            System.out.println(dml);
            EjecutarSentenciaDML(dml);
       }
       catch(Exception e) {
            throw e;
       }
    }

    public void Eliminar(TipoDocumento objeto) throws Exception {
       try { 
            String dml = "delete from tipo_documento "
                    + "where id_tipo_documento = "+objeto.getId_tipoDocumento()+";";
            System.out.println(dml);
            EjecutarSentenciaDML(dml);
       }
       catch(Exception e) {
            throw e;
       }
    } 
    
    public List<TipoDocumento> ConsultarAll() throws Exception {
        try {
            String sql = "select * from tipo_documento";

            List<TipoDocumento> tipoDocumentos = new ArrayList<TipoDocumento>();
            TipoDocumento tipoDocumento = null;
            
            EjecutarSentenciaSQL(sql);

            rs.beforeFirst();

            while(rs.next()) {
                tipoDocumento = new TipoDocumento();
                tipoDocumento.setId_tipoDocumento(rs.getInt("id_tipo_documento"));
                tipoDocumento.setNombre(rs.getString("nombre"));
                tipoDocumento.setEstado(rs.getBoolean("estado"));
                
                tipoDocumentos.add(tipoDocumento);
            }

            return tipoDocumentos;
        }
        catch(Exception e) {
            throw e;
        }
        finally {
            if(rs != null) {
                rs.close();
            }

            if(ps != null) {
                ps.close();
            }
        }
    }
    
    public TipoDocumento Consultar1(TipoDocumento objeto) throws Exception {
        try {
            String sql = "select * from tipo_documento where id_tipo_documento = "+objeto.getId_tipoDocumento()+";";
            System.out.println(sql);
            TipoDocumento tipoDocumento = null;
            
            EjecutarSentenciaSQL(sql);

            rs.beforeFirst();

            if(rs.next()) {
                tipoDocumento = new TipoDocumento();
                tipoDocumento.setId_tipoDocumento(rs.getInt("id_tipo_documento"));
                tipoDocumento.setNombre(rs.getString("nombre"));
                tipoDocumento.setEstado(rs.getBoolean("estado"));
            }

            return tipoDocumento;
        }
        catch(Exception e) {
            throw e;
        }
        finally {
            if(rs != null) {
                rs.close();
            }

            if(ps != null) {
                ps.close();
            }
        }
    }
    
}
