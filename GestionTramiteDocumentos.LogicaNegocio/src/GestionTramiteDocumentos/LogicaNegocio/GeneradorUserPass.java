package GestionTramiteDocumentos.LogicaNegocio;
import GestionTramiteDocumentos.Entidades.Usuario;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.digest.DigestUtils;
public class GeneradorUserPass {

    public String generarCuenta(String nombre, String paterno, String materno){
        String name = nombre + " ";
        String numero = Integer.toString(paterno.length() + materno.length() + nombre.length() + (int)(Math.random()*100));
        String cuenta = nombre.substring(0, name.indexOf(" ")) + paterno.substring(0, (int)(Math.random()*paterno.length()/2)) + materno.substring(0, (int)(Math.random()*materno.length()/2)) + numero;

        try {
            UsuarioLN usuarioLN = new UsuarioLN();
            if (usuarioLN.ConsultarUsuarioCuenta(new Usuario(null, null, null, cuenta, null, null, null, null)) != null) {
                generarCuenta(nombre,paterno,materno);
            }
        } catch (Exception ex) {
            Logger.getLogger(GeneradorUserPass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cuenta;
    }
    
    public String generarUser(String nombre, String app1, String app2){

        String nomAll = nombre+app1+app2;
        String code = Integer.toString(numeroLetra(nomAll.toUpperCase()));
        String cuenta = nombre.substring(0,1)+app1+app2.substring(0,1);
        String user = cuenta+code;

        return user.toUpperCase();

    }
    
    public String generarPass(String user){
        Random ramdom = new Random();
        String pass = "";
        for (int i = 0; i < 6; i++) {
            pass+=getLetra(ramdom.nextInt(26));
        }
        return pass.toUpperCase();
    }
    
    public String encriptarPass(String User){
        return DigestUtils.md5Hex(User);
    }
    
    private String getLetra(int n){
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";   
        return letras.substring(n,n+1);
    }
    
    private int numeroLetra(String texto){
        int num = 0;
        String letras = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";    
        for (int i = 0; i < texto.length(); i++) {
            for (int j = 0; j < letras.length(); j++) {
                if(texto.substring(i,i+1).equals(letras.substring(j,j+1))){
                    num=num+j;
                    break;
                }
            }
        }
        return num;
    }
    
    
}
