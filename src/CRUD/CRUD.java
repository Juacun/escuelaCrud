package CRUD;
import Modelo.*;
import Vista.*;
import Controlador.*;


public class CRUD {

    
    public static void main(String[] args) {

        JFCRUD Vista = new JFCRUD();
        AlumnosDAO Modelo = new AlumnosDAO();
        @SuppressWarnings("unused")
		ControladorCRUD Controlador = new ControladorCRUD(Vista, Modelo);
        
        Vista.setVisible(true);
        Vista.setLocationRelativeTo(null);
    }
    
}
