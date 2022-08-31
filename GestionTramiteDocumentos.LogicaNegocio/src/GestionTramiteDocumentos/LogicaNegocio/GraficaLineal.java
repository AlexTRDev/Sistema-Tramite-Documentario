package GestionTramiteDocumentos.LogicaNegocio;


import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class GraficaLineal {
    private JFreeChart grafica;
    private XYSeriesCollection datos = new XYSeriesCollection();
    
    private String titulo;
    private String tituloX;
    private String tituloY;

    public GraficaLineal(String titulo, String tituloX, String tituloY) {
        this.titulo = titulo;
        this.tituloX = tituloX;
        this.tituloY = tituloY;
        graficar();
    }

    public final void graficar(){
        grafica=ChartFactory.createXYLineChart(titulo, tituloX, tituloY, datos, PlotOrientation.VERTICAL, true, true, true);
    }
    
    public  void setDatos(String id ,Integer[] y, Integer[] x){
        XYSeries s = new XYSeries(id);
        int n=x.length;
        for (int i = 0; i < n; i++) {
            s.add(x[i],y[i]);
        }
        datos.addSeries(s);
    }
    
    public JPanel getPanel(){
        JPanel panel = new ChartPanel(grafica);
        panel.setSize(100,100);
        return panel;
    }
}
