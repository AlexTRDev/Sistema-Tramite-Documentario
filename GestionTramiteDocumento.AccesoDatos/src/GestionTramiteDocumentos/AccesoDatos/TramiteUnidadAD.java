package GestionTramiteDocumentos.AccesoDatos;

import Conector.EntidadAD;
import GestionTramiteDocumentos.Entidades.Tramite;
import GestionTramiteDocumentos.Entidades.TramiteUnidad;
import GestionTramiteDocumentos.Entidades.UnidadTramite;
import java.sql.Connection;

public class TramiteUnidadAD extends EntidadAD {
    UnidadTramiteAD unidadTramiteAD;
    TramiteAD tramiteAD;
    
    public TramiteUnidadAD(Connection connection) {
        super(connection);
        tramiteAD = new TramiteAD(connection);
        unidadTramiteAD = new UnidadTramiteAD(connection);
    }

    public void Insertar(TramiteUnidad objeto) throws Exception {
        try {
            String dml = "insert into tramite_unidad("+
                    "id_tramite, id_unidad_tramite) values("+
                    " "+objeto.getTramite().getId_tramite()+", "+
                    " "+objeto.getUnidadTramite().getId_unidad_tramite()+");";
            System.out.println(dml);
            EjecutarSentenciaDML(dml);
        }
        catch(Exception e) {
            throw e;
        }
    }

    public void ModificarUnidad(TramiteUnidad objeto) throws Exception {
       try {
            String dml = "update tramite_unidad set "+
                    "id_unidad_tramite= '"+objeto.getUnidadTramite()+"', "+
                    "where id_tramite = "+objeto.getTramite().getId_tramite()+";";
            System.out.println(dml);
            EjecutarSentenciaDML(dml);
       }
       catch(Exception e) {
            throw e;
       }
    }
    
    public void Eliminar(TramiteUnidad objeto) throws Exception {
       try { 
            String dml = "delete from tramite "+
                    "where id_tramite ="+objeto.getTramite().getId_tramite()+
                    "and id_unidad_tramite = "+objeto.getUnidadTramite().getId_unidad_tramite()+";";
            System.out.println(dml);
            EjecutarSentenciaDML(dml);
       }
       catch(Exception e) {
            throw e;
       }
    } 

    public TramiteUnidad Consultar1(Tramite tramite) throws Exception {
        try {
            String sql = "select * tramite_unidad where id_tramite = "+tramite.getId_tramite()+";";
            
            TramiteUnidad objeto = null;
            
            EjecutarSentenciaSQL(sql);

            rs.beforeFirst();

            if(rs.next()) {
                objeto = new TramiteUnidad();
                
                objeto.setTramite(tramiteAD.Consultar1(new Tramite(rs.getInt("id_tramite"))));
                objeto.setUnidadTramite(unidadTramiteAD.Consultar1(new UnidadTramite(rs.getInt("id_unidad_tramite"))));
            }
            return objeto;
        
        }catch(Exception e) {
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
