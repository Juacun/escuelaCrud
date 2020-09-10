package Modelo;
import com.mysql.jdbc.Connection;
import java.sql.*;


public class Conexion {
    
    private final String Schema = "escuela";
    private final String User = "root";
    private final String Password = "";
    private final String Url = "jdbc:mysql://localhost:3306/" + Schema;
    
    private Connection Conector = null;
    
    public Conexion(){
        
    }
    
    public Connection getConexion(){
                
        try {
            // Instanciar JDBC
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            // Realizar la conexion
            Conector = (Connection) DriverManager.getConnection(this.Url, this.User, this.Password);
        
        } catch (Exception e) {
        
            //debug
             System.out.println(e);
        }
        
        return Conector;
    }
}
