package producto.dao;

import conexion.conexionBD;
import empleado.dao.EmpleadoDAOImp;
import empleado.dominio.Empleado;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import producto.dominio.Producto;

public class ProductoDAOImp implements ProductoDAO {

    List<Producto> products;

    Scanner scan = new Scanner(System.in);

    //@Override
    public ProductoDAOImp() {

        List<Producto> productList = new ArrayList<Producto>();

        try {
            Connection conection = null;
            conexionBD.conectar(conection);

            Statement sentencia = conection.createStatement();

            ResultSet resultado = sentencia.executeQuery("Select * from productos");

            try {

                while (resultado.next()) {

                    while (resultado.next()) {
                        int codigo = resultado.getInt("p_codigo");
                        String nombre = resultado.getString("p_nombre");
                        String descripcion = resultado.getString("p_descripcion");
                        Double precio = resultado.getDouble("p_precio");

                        productList.add(new Producto(codigo, nombre, descripcion, precio));
                    }
                }

            } catch (Exception ex) {
                System.out.println("Error de lectura en: ");

            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDAOImp.class.getName()).log(Level.SEVERE, null, ex);

        }

        setProducts(productList);

    }

    // @Override
    public List<Producto> leerProducts() {

        Producto Productos = new Producto();
        List<Producto> productList = new ArrayList<>();

        try {
            Connection conection = null;
            conexionBD.conectar(conection);

            Statement sentencia = conection.createStatement();

            ResultSet resultado = sentencia.executeQuery("Select * from empleados");

            while (resultado.next()) {

                while (resultado.next()) {
                    int codigo = resultado.getInt("p_codigo");
                    String nombre = resultado.getString("p_nombre");
                    String descripcion = resultado.getString("p_descripcion");
                    Double precio = resultado.getDouble("p_precio");

                    productList.add(new Producto(codigo, nombre, descripcion, precio));
                }
            }

        } catch (SQLException ex) {
            System.out.println("Error de lectura en: ");

        }
        return productList;
    }

    public List<Producto> getProductos() {
        return products;
    }

    public void setProducts(List<Producto> products) {
        this.products = products;
    }

    public void updateCode(int productCode, int productNewCode) {
        
        try {
            Connection conection = null;
            conexionBD.conectar(conection);

            Statement sentencia = conection.createStatement();

            ResultSet resultado = sentencia.executeQuery("Update productos set p_codigo=" +productNewCode + " where p_codigo=" + productCode);
        } catch (SQLException ex) {
            System.out.println("Error a lo hora de actualizar");

        }
    }

    public void updateName(int productCode, String nuevoNombre) {

        try {
            Connection conection = null;
            conexionBD.conectar(conection);

            Statement sentencia = conection.createStatement();

            ResultSet resultado = sentencia.executeQuery("Update productos set p_nombre=" +nuevoNombre + " where p_codigo=" + productCode);
        } catch (SQLException ex) {
            System.out.println("Error a lo hora de actualizar");

        }
    }

    //@Override

    public void updatePrice(int productCode, double productPrice) {
      try {
            Connection conection = null;
            conexionBD.conectar(conection);

            Statement sentencia = conection.createStatement();

            ResultSet resultado = sentencia.executeQuery("Update productos set p_precio=" +productPrice+ " where p_codigo=" + productCode);
        } catch (SQLException ex) {
            System.out.println("Error a lo hora de actualizar");

        }
    }

    @Override
    public String toString() {

        String productListString = "";
        for (Producto i : this.products) {
            productListString
                    += "[producto]"
                    + "\n [codigo]\n "
                    + i.getCodigo()
                    + "\n [nombre]\n "
                    + i.getNombre()
                    + "\n [descripcion]\n "
                    + i.getDescripcion()
                    + "\n [precio]\n "
                    + i.getPrecio()
                    + "\n";
        }
        return productListString;
    }

    @Override
    public boolean actualizarProducto(Producto producto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
