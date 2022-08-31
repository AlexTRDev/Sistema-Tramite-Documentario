package Reporte;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;
import Conector.Conexion;
public class Reporte {
    private List<Parametro> lParametro;
    private Conexion conexion;

    public Reporte(List<Parametro> lParametro) {
        this.lParametro = lParametro;
    }

    public JRViewer VistaPreliminar(String nombre) throws Exception {
        try {
            //JasperReport reporte = (JasperReport)(JRLoader.loadObject(getClass().getResourceAsStream("/SIB/GestionBiblioteca/Presentacion/Reportes/IReport/" + nombre + ".jasper")));
            JasperReport reporte = (JasperReport)(JRLoader.loadObject(getClass().getResourceAsStream("/GestionTramiteDocumentos/Presentacion/Reportes/IReport/" + nombre + ".jasper")));       
            
            
            
            Map parametros = new HashMap();

            if(lParametro != null) {
                for(int i = 0; i < lParametro.size(); i++) {
                    parametros.put(lParametro.get(i).getNombre(),lParametro.get(i).getValor());
                }
            }

            conexion = new Conexion();
            conexion.Abrir(true);
            return new JRViewer(JasperFillManager.fillReport(reporte,parametros,conexion.getConnection()));
        }
        catch (Exception e) {
            throw e;
        }
    }
}