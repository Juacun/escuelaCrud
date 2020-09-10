package Modelo;
import java.sql.*;
import java.util.ArrayList;


public class AlumnosDAO {

    Conexion conexion;

    public AlumnosDAO() {
        
        conexion = new Conexion();
        
    }
    
    public String createAlumno(String Matricula, String Nombre, String Apellido, int DNI, String FechaDeNacimiento, String Sexo, String Direccion, int Telefono, String Curso, String Jornada) {
        
        
        String createAlumno = null;
        
        try {
            
            Connection accesoDB = conexion.getConexion();
            PreparedStatement ps = accesoDB.prepareStatement("INSERT INTO alumnos (Matricula, Nombre, Apellido, DNI, FechaDeNacimiento, Sexo, Direccion, Telefono, Curso, Jornada) VALUES(?,?,?,?,?,?,?,?,?,?) ");
            
            ps.setString(1, Matricula);
            ps.setString(2, Nombre);
            ps.setString(3, Apellido);
            ps.setInt   (4, DNI);
            ps.setString(5, FechaDeNacimiento);
            ps.setString(6, Sexo);
            ps.setString(7, Direccion);
            ps.setInt   (8, Telefono);
            ps.setString(9, Curso);
            ps.setString(10, Jornada);
            
            int numAfectedRows = ps.executeUpdate();
            
            if (numAfectedRows > 0) {
                
                createAlumno = "ALUMNO AGREGADO";
            }
        } catch(Exception e) {
            
            //debug
            System.out.println(e);
        }
        
        return createAlumno;
    }
    
    public ArrayList<Alumnos> readAlumno() {
        
        ArrayList<Alumnos> listaAlumnos = new ArrayList<Alumnos>();
        Alumnos Alumno;
        
        
        try {
        
            Connection accesoDB = conexion.getConexion();
            PreparedStatement ps = accesoDB.prepareStatement("SELECT * FROM `alumnos`");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
            
                Alumno = new Alumnos();
                
                Alumno.setId(rs.getInt(1));
                Alumno.setMatricula(rs.getString(2));
                Alumno.setNombre(rs.getString(3));
                Alumno.setApellido(rs.getString(4));
                Alumno.setDni(rs.getInt(5));
                Alumno.setFechaDeNacimiento(rs.getString(6));
                Alumno.setSexo(rs.getString(7));
                Alumno.setDireccion(rs.getString(8));
                Alumno.setTelefono(rs.getInt(9));
                Alumno.setCurso(rs.getString(10));
                Alumno.setJornada(rs.getString(11));
                
                listaAlumnos.add(Alumno);
                
            }
            
        } catch (Exception e) {
            
            //debug
            System.out.println(e);
        }
        
        return listaAlumnos;
    }
    
    public String updateAlumno(String Nombre, String Apellido, int DNI, String FechaDeNacimiento, String Sexo, String Direccion, int Telefono, String Curso, String Jornada, String Matricula) {
        
        
        String updateAlumno = null;
        
        try {
            
            Connection accesoDB = conexion.getConexion();
            PreparedStatement ps = accesoDB.prepareStatement("UPDATE alumnos  SET Nombre=?, Apellido=?, DNI=?, FechaDeNacimiento=?, Sexo=?, Direccion=?, Telefono=?, Curso=?, Jornada=? WHERE Matricula=? ");
            
            ps.setString(1, Nombre);
            ps.setString(2, Apellido);
            ps.setInt   (3, DNI);
            ps.setString(4, FechaDeNacimiento);
            ps.setString(5, Sexo);
            ps.setString(6, Direccion);
            ps.setInt   (7, Telefono);
            ps.setString(8, Curso);
            ps.setString(9, Jornada);
            ps.setString(10, Matricula);
            
            int numAfectedRows = ps.executeUpdate();
            
            if (numAfectedRows > 0) {
                
                updateAlumno = "ALUMNO MODIFICADO";
            }
        } catch (Exception e) {
            
            //debug
            System.out.println(e);
        }
        
        return updateAlumno;
    }
    
    public String deleteAlumno(String Matricula) {
        
        
        String deleteAlumno = null;
        
        try {
            
            Connection accesoDB = conexion.getConexion();
            PreparedStatement ps = accesoDB.prepareStatement("DELETE FROM alumnos WHERE Matricula=? ");
            
            ps.setString(1, Matricula);
            ps.execute();
            
            deleteAlumno = "ALUMNO ELIMINADO";   
            
            } catch (Exception e) {
            
            //debug
            System.out.println(e);
            }
        
        return deleteAlumno;
    }
    
    
}
