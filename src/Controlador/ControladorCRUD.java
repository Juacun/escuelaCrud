package Controlador;
import Modelo.*;
import Vista.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class ControladorCRUD implements ActionListener {

    
    JFCRUD vistaCrud = new JFCRUD();
    AlumnosDAO modeloCrud = new AlumnosDAO();
    
    public ControladorCRUD(JFCRUD VistaCRUD, AlumnosDAO ModeloCRUD) {
        
        this.modeloCrud = ModeloCRUD;
        this.vistaCrud = VistaCRUD;
        
        VistaCRUD.btnSave.setVisible(false);
        
        this.vistaCrud.btnCreate.addActionListener(this);
        this.vistaCrud.btnRead.addActionListener(this);
        this.vistaCrud.btnUpdate.addActionListener(this);
        this.vistaCrud.btnSave.addActionListener(this);
        this.vistaCrud.btnDelete.addActionListener(this);
        
    }
    
    public void inicalizarCRUD() {
        
    }
    
    public void llenarTabla(JTable Table) {
    
        DefaultTableModel modeloTable = new DefaultTableModel();
        Table.setModel(modeloTable);
        
        modeloTable.addColumn("MATRICULA");
        modeloTable.addColumn("NOMBRE");
        modeloTable.addColumn("APELLIDO");
        modeloTable.addColumn("DNI");
        modeloTable.addColumn("FECHA DE NACIMIENTO");
        modeloTable.addColumn("SEXO");
        modeloTable.addColumn("DIRECCION");
        modeloTable.addColumn("TELEFONO");
        modeloTable.addColumn("CURSO");
        modeloTable.addColumn("JORNADA");
        
        Object[] objectRow = new Object[10];
        
        int numRegistros = modeloCrud.readAlumno().size();
        
        for (int i = 0; i < numRegistros; i++) {
            
            objectRow[0] = modeloCrud.readAlumno().get(i).getMatricula();
            objectRow[1] = modeloCrud.readAlumno().get(i).getNombre();
            objectRow[2] = modeloCrud.readAlumno().get(i).getApellido();
            objectRow[3] = modeloCrud.readAlumno().get(i).getDni();
            objectRow[4] = modeloCrud.readAlumno().get(i).getFechaDeNacimiento();
            objectRow[5] = modeloCrud.readAlumno().get(i).getSexo();
            objectRow[6] = modeloCrud.readAlumno().get(i).getDireccion();
            objectRow[7] = modeloCrud.readAlumno().get(i).getTelefono();
            objectRow[8] = modeloCrud.readAlumno().get(i).getCurso();
            objectRow[9] = modeloCrud.readAlumno().get(i).getJornada();
            
            modeloTable.addRow(objectRow);
        }
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {


        if (ae.getSource() == vistaCrud.btnCreate) {
            
            String Matricula = vistaCrud.txtMatricula.getText();
            String Nombre = vistaCrud.txtNombre.getText();
            String Apellido = vistaCrud.txtApellido.getText();
            int    DNI = Integer.parseInt(vistaCrud.txtDNI.getText());
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MMM-yyyy");
            String FechaDeNacimiento = formatoFecha.format(vistaCrud.jdFechaDeNacimiento.getDate());
            String Sexo = vistaCrud.txtSexo.getText();
            String Direccion = vistaCrud.txtDireccion.getText();
            int    Telefono = Integer.parseInt(vistaCrud.txtTelefono.getText());
            String Curso = vistaCrud.txtCurso.getText();
            String Jornada = vistaCrud.txtJornada.getText();
            
            String rptaCreate = modeloCrud.createAlumno(Matricula, Nombre, Apellido, DNI, FechaDeNacimiento, Sexo, Direccion, Telefono, Curso, Jornada);
            
            if (rptaCreate!=null) {
                
                JOptionPane.showMessageDialog(null,rptaCreate);
            } else {
                
                JOptionPane.showMessageDialog(null, "NO SE PUDO CARGAR EL ALUMNO");
            }
        }
        
        
        if (ae.getSource() == vistaCrud.btnRead) {
            
            llenarTabla(vistaCrud.jtDatos);
        }
        
        if (ae.getSource() == vistaCrud.btnUpdate) {
            
            int countOfSelectedRows = vistaCrud.jtDatos.getSelectedRowCount();
            int selectedRow = vistaCrud.jtDatos.getSelectedRow();
            
            if (countOfSelectedRows > 1 || countOfSelectedRows <= 0) {
                
                JOptionPane.showMessageDialog(null, "Seleccione cada Item individualmente de la tabla para su Edicion");
            }
            
            if (countOfSelectedRows == 1) {
                
                
                vistaCrud.btnCreate.setEnabled(false);
                vistaCrud.btnRead.setEnabled(false);
                vistaCrud.btnDelete.setEnabled(false);
                    
                try {    
                    vistaCrud.txtMatricula.setText((String) vistaCrud.jtDatos.getValueAt(selectedRow , 0));
                    vistaCrud.txtNombre.setText((String) vistaCrud.jtDatos.getValueAt(selectedRow , 1));
                    vistaCrud.txtApellido.setText((String) vistaCrud.jtDatos.getValueAt(selectedRow , 2));
                    vistaCrud.txtDNI.setText(String.valueOf(vistaCrud.jtDatos.getValueAt(selectedRow , 3)));
                    
                    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MMM-yyyy");
                    vistaCrud.jdFechaDeNacimiento.setDate(formatoFecha.parse((String)(vistaCrud.jtDatos.getValueAt(selectedRow , 4))));
                    
                    vistaCrud.txtSexo.setText((String) vistaCrud.jtDatos.getValueAt(selectedRow , 5));
                    vistaCrud.txtDireccion.setText((String) vistaCrud.jtDatos.getValueAt(selectedRow , 6));
                    vistaCrud.txtTelefono.setText(String.valueOf(vistaCrud.jtDatos.getValueAt(selectedRow , 7)));
                    vistaCrud.txtCurso.setText((String) vistaCrud.jtDatos.getValueAt(selectedRow , 8));
                    vistaCrud.txtJornada.setText((String) vistaCrud.jtDatos.getValueAt(selectedRow , 9));
                    
                } catch (ParseException ex) {
                    Logger.getLogger(ControladorCRUD.class.getName()).log(Level.SEVERE, null, ex);
                    
                    vistaCrud.btnCreate.setEnabled(true);
                    vistaCrud.btnRead.setEnabled(true);
                    vistaCrud.btnDelete.setEnabled(true);
                    
                    vistaCrud.txtMatricula.setText("");
                    vistaCrud.txtNombre.setText("");
                    vistaCrud.txtApellido.setText("");
                    vistaCrud.txtDNI.setText("");
                    vistaCrud.jdFechaDeNacimiento.setDateFormatString("");
                    vistaCrud.txtSexo.setText("");
                    vistaCrud.txtDireccion.setText("");
                    vistaCrud.txtTelefono.setText("");
                    vistaCrud.txtCurso.setText("");
                    vistaCrud.txtJornada.setText("");

                }
                    
                vistaCrud.btnUpdate.setVisible(false);
                vistaCrud.btnSave.setVisible(true);

            }
        }
        
        if (ae.getSource() == vistaCrud.btnSave) {
            
            String Matricula = vistaCrud.txtMatricula.getText();
            String Nombre = vistaCrud.txtNombre.getText();
            String Apellido = vistaCrud.txtApellido.getText();
            int    DNI = Integer.parseInt(vistaCrud.txtDNI.getText());
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MMM-yyyy");
            String FechaDeNacimiento = formatoFecha.format(vistaCrud.jdFechaDeNacimiento.getDate());
            String Sexo = vistaCrud.txtSexo.getText();
            String Direccion = vistaCrud.txtDireccion.getText();
            int    Telefono = Integer.parseInt(vistaCrud.txtTelefono.getText());
            String Curso = vistaCrud.txtCurso.getText();
            String Jornada = vistaCrud.txtJornada.getText();

            String rptaCreate = modeloCrud.updateAlumno( Nombre, Apellido, DNI, FechaDeNacimiento, Sexo, Direccion, Telefono, Curso, Jornada, Matricula);
            
            if (rptaCreate!=null) {
                
                JOptionPane.showMessageDialog(null,rptaCreate);
            } else {
                
                JOptionPane.showMessageDialog(null, "NO SE PUDO MODIFICAR EL ALUMNO");
            }
            
            llenarTabla(vistaCrud.jtDatos);
            
            vistaCrud.btnSave.setVisible(false);
            vistaCrud.btnUpdate.setVisible(true);
            vistaCrud.btnCreate.setEnabled(true);
            vistaCrud.btnRead.setEnabled(true);
            vistaCrud.btnDelete.setEnabled(true);
        }
        
        if (ae.getSource() == vistaCrud.btnDelete) {
            
            int countOfRows = vistaCrud.jtDatos.getRowCount();
            int countOfSelectedRows = vistaCrud.jtDatos.getSelectedRowCount();
            int countOfDeletedRows = 0;
            
            if (countOfSelectedRows <= 0) {
                
                JOptionPane.showMessageDialog(null, "Para Eliminar, seleccione uno o mas items de la lista." );
            }
            
            if (countOfSelectedRows > 0) {
                                
                for (int i = 0; i < countOfRows; i++) {
                
                    if (vistaCrud.jtDatos.isRowSelected(i)) {
                        
                        String getMatricula = (String) vistaCrud.jtDatos.getValueAt(i , 0);
                        modeloCrud.deleteAlumno(getMatricula);
                        countOfDeletedRows++;
                    }
                }
                
                llenarTabla(vistaCrud.jtDatos);
                
                if (countOfSelectedRows == countOfDeletedRows) {
                
                    JOptionPane.showMessageDialog(null, "[" + countOfDeletedRows + "/" + countOfSelectedRows + "] ITEMS ELIMINADOS" );
                }
                
            }
        }
    }
}
