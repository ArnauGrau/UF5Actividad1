package empleado.dao;

import java.sql.Connection;
import conexion.conexionBD;
import static conexion.conexionBD.conectar;
import empleado.dominio.Empleado;
import java.sql.SQLException;
import java.util.List;
import empleado.dao.EmpleadoDAO;
import java.sql.Statement;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;

public class EmpleadoDAOImp implements EmpleadoDAO {

    private List<Empleado> empleados;

    public EmpleadoDAOImp() {
        List<Empleado> empleadoList = new ArrayList<>();
        try {
            Connection conection = null;
            conexionBD.conectar(conection);

            Statement sentencia = conection.createStatement();

            ResultSet resultado = sentencia.executeQuery("Select * from empleados");

            try {

                while (resultado.next()) {

                    while (resultado.next()) {
                        int codigo = resultado.getInt("e_codigo");
                        String nombre = resultado.getString("e_nombre");
                        String apellidos = resultado.getString("e_apellidos");
                        String password = resultado.getString("e_password");

                        empleadoList.add(new Empleado(codigo, nombre, apellidos, password));
                    }
                }

            } catch (Exception ex) {
                System.out.println("Error de lectura en: ");

            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDAOImp.class.getName()).log(Level.SEVERE, null, ex);

        }

        setEmpleados(empleadoList);

    }

    public List<Empleado> getEmpleados() {
        return this.empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    @Override
    public List<Empleado> leerEmpleados() {
        List<Empleado> empleadoList = new ArrayList<>();
        try {
            Connection conection = null;
            conexionBD.conectar(conection);

            Statement sentencia = conection.createStatement();

            ResultSet resultado = sentencia.executeQuery("Select * from empleados");

            try {

                while (resultado.next()) {

                    while (resultado.next()) {
                        int codigo = resultado.getInt("e_codigo");
                        String nombre = resultado.getString("e_nombre");
                        String apellidos = resultado.getString("e_apellidos");
                        String password = resultado.getString("e_password");

                        empleadoList.add(new Empleado(codigo, nombre, apellidos, password));
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(EmpleadoDAOImp.class.getName()).log(Level.SEVERE, null, ex);

            }
        } catch (Exception e) {

        }
        return empleadoList;
    }

    @Override

    public Empleado getEmpleadoPorCodigo(int codigo) {

        Empleado empleado = null;
        try {
            Connection conection = null;
            conexionBD.conectar(conection);

            Statement sentencia = conection.createStatement();

            ResultSet resultado = sentencia.executeQuery("Select * from empleados where e_codigo = codigo");

            while (resultado.next()) {
                int newCodigo = resultado.getInt("e_codigo");
                String nombre = resultado.getString("e_nombre");
                String apellidos = resultado.getString("e_apellidos");
                String password = resultado.getString("e_password");
                
                empleado = new Empleado(newCodigo, nombre, apellidos, password);
            }
        } catch (SQLException ex) {
            System.out.println("Error de lectura en: ");

        }

        return empleado;
    }



    public void actualizarPassword(Empleado employee, String employeePassword) {
         try {
            Connection conection = null;
            conexionBD.conectar(conection);

            Statement sentencia = conection.createStatement();

            ResultSet resultado = sentencia.executeQuery("Update empleados set e_password="+employeePassword+" where e_codigo="+employee.getCodigo());
        } catch (SQLException ex) {
            System.out.println("Error a lo hora de actualizar");

        }
    }

    @Override
    public String toString() {

        String productListString = "";
        for (Empleado i : this.empleados) {
            productListString
                    += "[empleado]"
                    + "\n [codigo]\n "
                    + i.getCodigo()
                    + "\n [nombre]\n "
                    + i.getNombre()
                    + "\n [apellidos]\n "
                    + i.getApellidos()
                    + "\n [contraseña]\n "
                    + i.getPassword()
                    + "\n";
        }
        return productListString;

    }

    @Override
    public boolean actualizarEmpleado(Empleado empleado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
