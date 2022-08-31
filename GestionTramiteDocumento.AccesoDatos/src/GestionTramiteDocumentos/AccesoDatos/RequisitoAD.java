package GestionTramiteDocumentos.AccesoDatos;

import GestionTramiteDocumentos.Entidades.UnidadOrganizativa;
import Conector.EntidadAD;
import GestionTramiteDocumentos.Entidades.Requisito;
import GestionTramiteDocumentos.Entidades.TipoTramite;
import GestionTramiteDocumentos.Entidades.UnidadTramite;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class RequisitoAD extends EntidadAD {
    UnidadOrganizativaAD unidadOrganizativaAD;
    UnidadTramiteAD unidadTramiteAD;
    TipoTramiteAD tipoTramiteAD;
    public RequisitoAD(Connection connection) {
        super(connection);
        unidadOrganizativaAD = new UnidadOrganizativaAD(connection);
        unidadTramiteAD = new UnidadTramiteAD(connection);
        tipoTramiteAD = new TipoTramiteAD(connection);
    }

    public void Insertar(Requisito objeto) throws Exception {
        try {
            String dml = "insert into requisito("+
                    "descripcion,id_tipo_tramite) values("+
                    "'"+objeto.getDescipcion()+"',"+objeto.getTipoTramite().getId_tipoTramite()+");";

            System.out.println(dml);
            EjecutarSentenciaDML(dml);
        }
        catch(Exception e) {
            throw e;
        }
    }

    public void Modificar(Requisito objeto) throws Exception {
       try {
            String dml = "update requisito set "+
                    "descripcion = '"+objeto.getDescipcion()+"', "+
                    "id_tipo_tramite = "+objeto.getTipoTramite().getId_tipoTramite()+" "+
                    "where id_requisito= "+objeto.getId_requisito()+";";
            System.out.println(dml);
            EjecutarSentenciaDML(dml);
       }
       catch(Exception e) {
            throw e;
       }
    }

    public void Eliminar(Requisito objeto) throws Exception {
       try { 
            String dml = "delete from requisito "
                    + "where id_requisito = "+objeto.getId_requisito()+";";
            System.out.println(dml);
            EjecutarSentenciaDML(dml);
       }
       catch(Exception e) {
            throw e;
       }
    } 
    
    public List<Requisito> ConsultarAll(TipoTramite objeto) throws Exception {
        try {
            String sql = "select * from requisito"
                    + " where id_tipo_tramite= "+objeto.getId_tipoTramite();

            List<Requisito> requisitos = new ArrayList<Requisito>();
            Requisito requisito = null;
            
            EjecutarSentenciaSQL(sql);

            rs.beforeFirst();

            while(rs.next()) {
                requisito = new Requisito();
                requisito.setId_requisito(rs.getInt("id_requisito"));
                requisito.setDescipcion(rs.getString("descripcion"));
                requisito.setEstado(rs.getBoolean("estado"));
                requisito.setTipoTramite(tipoTramiteAD.Consultar1(new TipoTramite(rs.getInt("id_tipo_tramite"))));
                
                requisitos.add(requisito);
            }

            return requisitos;
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
       
    public Requisito Consultar1(Requisito objeto) throws Exception {//solo para uso de entidad RecDoc
        try {
            String sql = "select * from requisito"
                    + " where id_requisito= "+objeto.getId_requisito();

            List<Requisito> requisitos = new ArrayList<Requisito>();
            Requisito requisito = null;
            
            EjecutarSentenciaSQL(sql);

            rs.beforeFirst();

            if(rs.next()) {
                requisito = new Requisito();
                requisito.setId_requisito(rs.getInt("id_requisito"));
                requisito.setDescipcion(rs.getString("descripcion"));
                requisito.setEstado(objeto.getEstado());
                requisito.setTipoTramite(tipoTramiteAD.Consultar1(new TipoTramite(rs.getInt("id_tipo_tramite"))));
                
                requisitos.add(requisito);
            }

            return requisito;
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
    
    public Integer getId() throws Exception {
        try {
            String sql = "select nextval('tb_tema_tb_tema_id_seq') as tema_id;";

            Integer id = 0;

            EjecutarSentenciaSQL(sql);

            rs.beforeFirst();

            if(rs.next()) {
                id = rs.getInt("tema_id");

                if(rs.wasNull()) {
                    id = 0;
                }
            }

            return id;
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
