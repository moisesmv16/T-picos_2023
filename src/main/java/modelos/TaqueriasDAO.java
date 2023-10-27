package modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class TaqueriasDAO {
    private int idCategoria;
    private String nombre;
    private int idPlatillo;

    private int precio;
    private String nombrePlatillo;
    private String imagenPlatillo;


    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getNombrePlatillo() {
        return nombrePlatillo;
    }

    public void setNombrePlatillo(String nombrePlatillo) {
        this.nombrePlatillo = nombrePlatillo;
    }

    public String getImagenPlatillo() {
        return imagenPlatillo;
    }

    public void setImagenPlatillo(String imagenPlatillo) {
        this.imagenPlatillo = imagenPlatillo;
    }

    public int getIdPlatillo() {
        return idPlatillo;
    }

    public void setIdPlatillo(int idPlatillo) {
        this.idPlatillo = idPlatillo;
    }


    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public int insertarCategoria(String nombreCategoria) {
        int idCategoria = -1;
        try {
            // Insertar una nueva categoría
            String insertCategoriaQuery = "INSERT INTO Categorias (nombre) VALUES (?)";
            PreparedStatement pstmtCategoria = Conexion.conexion.prepareStatement(insertCategoriaQuery, Statement.RETURN_GENERATED_KEYS);
            pstmtCategoria.setString(1, nombreCategoria);
            pstmtCategoria.executeUpdate();

            // Obtener el idCategoria de la categoría recién creada
            ResultSet id = pstmtCategoria.getGeneratedKeys();
            if (id.next()) {
                idCategoria = id.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idCategoria;
    }
    private int Total1= 0;
    private int SUMA=0;
    public int insertarPlatillo(String nombrePlatillo, int precio, String imagenPlatillo, int idCategoria) {
        int idPlatillo = -1;
        try {
            // Insertar un nuevo platillo y asignarle la categoría
            String insertPlatilloQuery = "INSERT INTO Platillo (nombre, precio, imagenPlatillo, idCategoria) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmtPlatillo = Conexion.conexion.prepareStatement(insertPlatilloQuery, Statement.RETURN_GENERATED_KEYS);
            pstmtPlatillo.setString(1, nombrePlatillo);
            pstmtPlatillo.setInt(2, precio);
            pstmtPlatillo.setString(3, imagenPlatillo);
            pstmtPlatillo.setInt(4, idCategoria);
            pstmtPlatillo.executeUpdate();

            ResultSet id = pstmtPlatillo.getGeneratedKeys();
            if(id.next()){
                idPlatillo = id.getInt(1);
                Total1++; // Incrementa el contador de inserciones
                SUMA += precio;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idPlatillo;
    }

    public int obtenerContadorInserciones() {
        return Total1;
    }
    public int obtenerSuma() {
        return SUMA;
    }


    public int insertarUsuario(String nombreUsuario) {
        int idPedido = -1;
        try {
            // Insertar una nueva categoría
            String insertCategoriaQuery = "INSERT INTO Pedido (cliente) VALUES (?)";
            PreparedStatement pstmtCategoria = Conexion.conexion.prepareStatement(insertCategoriaQuery, Statement.RETURN_GENERATED_KEYS);
            pstmtCategoria.setString(1, nombreUsuario);
            pstmtCategoria.executeUpdate();
            // Obtener el idCategoria de la categoría recién creada
            ResultSet id = pstmtCategoria.getGeneratedKeys();
            if (id.next()) {
                idPedido = id.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idPedido;
    }


    public void insertarCompra(int idPlatillo, int idPedido, int Precio, int Total, int Cantidad) {
        try {
            // Insertar una nueva compra asignándole la categoría y el pedido
            String insertCompraQuery = "INSERT INTO DetallePedido (idPlatillo, idPedido, Precio, Total, Cantidad) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmtCompra = Conexion.conexion.prepareStatement(insertCompraQuery);
            pstmtCompra.setInt(1, idPlatillo);
            pstmtCompra.setInt(2, idPedido);
            pstmtCompra.setInt(3, Precio);
            pstmtCompra.setInt(4, Total);
            pstmtCompra.setInt(5, Cantidad);
            pstmtCompra.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarTodo() {
        try {

            String queryDetalle = "DELETE FROM DetallePedido";
            Statement stmtDetalle = Conexion.conexion.createStatement();
            stmtDetalle.executeUpdate(queryDetalle);

            // Luego, elimina los registros de la tabla "Pedido"
            String queryPedido = "DELETE FROM Pedido";
            Statement stmtPedido = Conexion.conexion.createStatement();
            stmtPedido.executeUpdate(queryPedido);

            String queryPlatillo = "DELETE FROM Platillo";
            Statement stmtPlatillo = Conexion.conexion.createStatement();
            stmtPlatillo.executeUpdate(queryPlatillo);

            String queryCategorias = "DELETE FROM Categorias";
            Statement stmtCategorias = Conexion.conexion.createStatement();
            stmtCategorias.executeUpdate(queryCategorias);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<TaqueriasDAO> LISTARPRODUCTOS(){
        ObservableList<TaqueriasDAO> ListTaq = FXCollections.observableArrayList();
        TaqueriasDAO objC;
        try{
            String query = "SELECT * FROM Platillo";
            Statement stmt = Conexion.conexion.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objC = new TaqueriasDAO();
                objC.setIdPlatillo(res.getInt("idPlatillo"));
                objC.setNombrePlatillo(res.getString("nombre"));
                ListTaq.add(objC);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ListTaq;
    }

    public ObservableList<TaqueriasDAO> LISTARCATEGORIAS(){
        ObservableList<TaqueriasDAO> ListTaq = FXCollections.observableArrayList();
        TaqueriasDAO objC;
        try{
            String query = "SELECT * FROM Categorias";
            Statement stmt = Conexion.conexion.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objC = new TaqueriasDAO();
                objC.setIdCategoria(res.getInt("idCategoria"));
                objC.setNombre(res.getString("nombre"));
                ListTaq.add(objC);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ListTaq;
    }


    public void ELIMINARPLATILLO(){
        try{
            String query = "DELETE FROM DetallePedido WHERE idPlatillo = "+this.idPlatillo;
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void ELIMINARPLATILLOTODO(){
        try{
            String query = "DELETE FROM Platillo WHERE idPlatillo = "+this.idPlatillo;
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}

