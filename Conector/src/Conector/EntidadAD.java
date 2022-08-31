package Conector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class EntidadAD {
    private Connection connection;
    protected PreparedStatement ps;
    protected ResultSet rs;

    public EntidadAD(Connection connection) {
        this.connection = connection;
    }

    protected void EjecutarSentenciaSQL(String sql) throws Exception {
        try {
            ps = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs = ps.executeQuery();
        }
        catch(Exception e) {
            System.out.println(e);
            throw e;
        }
    }

    protected void EjecutarSentenciaDML(String dml) throws Exception {
        try {
            ps = connection.prepareStatement(dml);
            ps.executeUpdate();
        }
        catch(Exception e) {
            throw e;
        }
        finally {
            if(ps != null) {
                ps.close();
            }
        }
    }
}