package GestionTramiteDocumentos.Presentacion;


import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Graficas {
    JFreeChart grafica;
    XYSeriesCollection datos = new XYSeriesCollection();
    String titulo;
    String tituloX;
    String tituloY;
    
    final static int LINEAL =1;
    final static int POLAR =2;
    final static int DISPERSION =3;
    final static int AREA =4;
    final static int LOGARITMICA =5;
    final static int SERIETIEMPO =6;
    final static int PASO =7;
    final static int PASOAREA =8;
    
    public Graficas(int tipo, String titulo,String tituloX, String tituloY) {
        this.titulo=titulo;
        this.tituloX = tituloX;
        this.tituloY = tituloY;
        this.tipoGrafica(tipo);
    }
    
    public void tipoGrafica(int tipo){
        switch(tipo){
            case LINEAL:
                grafica=ChartFactory.createXYLineChart(titulo, tituloX, tituloY, datos, PlotOrientation.VERTICAL, true, true, true);
                break;
            case POLAR:
                grafica=ChartFactory.createPolarChart(titulo, datos, true, true, true);
                break;
            case DISPERSION:
                grafica=ChartFactory.createScatterPlot(titulo, tituloX, tituloY, datos, PlotOrientation.VERTICAL, true, true, true);
                break;
            case AREA:
                grafica=ChartFactory.createXYAreaChart(titulo, tituloX, tituloY, datos, PlotOrientation.VERTICAL, true, true, true);
                break;
            case SERIETIEMPO:
                grafica=ChartFactory.createTimeSeriesChart(titulo, tituloX, tituloY, datos, true, true, true);
                break;
            case PASO:
                grafica=ChartFactory.createXYStepChart(titulo, tituloX, tituloY, datos, PlotOrientation.VERTICAL, true, true, true);
                break;
            case PASOAREA:
                grafica=ChartFactory.createXYStepAreaChart(titulo, tituloX, tituloY, datos, PlotOrientation.VERTICAL, true, true, true);
                break;
            default:
                break;
            
        }
    }
    
    public void setParametros(String id, Integer[] x, Integer[] y){
        XYSeries s = new XYSeries(id);
        int n=x.length;
        for (int i = 0; i < n; i++) {
            s.add(x[i],y[i]);
        }
        datos.addSeries(s);
    }
    
    public JPanel getPanel(){
        JPanel panel = new ChartPanel(grafica);
        return panel;
    }
}
