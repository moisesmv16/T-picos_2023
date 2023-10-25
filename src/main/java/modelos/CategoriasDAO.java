package modelos;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class CategoriasDAO {
   private int idCategoria;//java trabaja con poo y en la bd tenemos que pasar a metodos
    private String nomCategoria;
    private int idProducto;
    private String nomProducto;
    private int idPlatillo;
    private String nomPlatillo;
    private int precio;
    private String imagen;



    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNomCategoria() {
        return nomCategoria;
    }

    public void setNomCategoria(String nomCategoria) {
        this.nomCategoria = nomCategoria;
    }
    public int getIdPlatillo() {return idPlatillo;}
    public void setIdPlatillo(int idPlatillo) {this.idPlatillo = idPlatillo;}
    public String getNomPlatillo() {return nomPlatillo;}
    public void setNomPlatillo(String nomPlatillo) {this.nomPlatillo = nomPlatillo;}
    public int getprecio() {return precio;}
    public void setprecio(int precio) {this.precio = precio;}
    public String getimagen() {return imagen;}
    public void setimagen(String imagen) {this.imagen = imagen;}



    public void INSERTAR(){
        try{
            String query = "INSERT INTO tblCategorias"+
                    "(nomCategoria) VALUES('"+this.nomCategoria+"')";
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
           e.printStackTrace();
        }
    }

    public void ACTUALIZAR(){
        try{
            String query = "UPDATE tblCategorias SET nomCategoria = '"+this.nomCategoria+"' "+
                    "WHERE idCategoria = "+this.idCategoria;
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void ELIMINAR(){
        try{
            String query = "DELETE FROM tblCategorias WHERE idCategoria = "+this.idCategoria;
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ObservableList<CategoriasDAO> LISTARCATEGORIAS(){
        ObservableList<CategoriasDAO> ListCat = FXCollections.observableArrayList();
        CategoriasDAO objC;
        try{
            String query = "SELECT * FROM tblCategorias";
            Statement stmt = Conexion.conexion.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objC = new CategoriasDAO();
                objC.setIdCategoria(res.getInt("idCategoria"));
                objC.setNomCategoria(res.getString("nomCategoria"));
                ListCat.add(objC);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ListCat;
    }

    public void INSERTARCATEGORIAS(){
        try{
            String query = "INSERT INTO tblCategorias"+
                    "(nomCategoria) VALUES('"+this.nomCategoria+"')";
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void INSERTARPLATILLO(){
        try {
            String query = "INSERT INTO Platillo (nomPlatillo, precio, imagen) VALUES ('" + this.nomPlatillo + "', '" + this.precio + "', '" + this.imagen + "')";
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void ACTUALIZARPLATILLO(){
        try{
            String query = "UPDATE Platillo SET nomPlatillo = '"+this.nomPlatillo+"' "+
                    "WHERE idPlatillo = "+this.idPlatillo;
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void ELIMINARPLATILLO(){
        try{
            String query = "DELETE FROM Platillo WHERE idPlatillo = "+this.idPlatillo;
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ObservableList<CategoriasDAO> LISTARPLATILLOS(){
        ObservableList<CategoriasDAO> ListPro = FXCollections.observableArrayList();
        CategoriasDAO objC;
        try{
            String query = "SELECT * FROM Platillo";
            Statement stmt = Conexion.conexion.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objC = new CategoriasDAO();
                objC.setIdPlatillo(res.getInt("idPlatillo"));
                objC.setNomPlatillo(res.getString("nomPlatillo"));
                ListPro.add(objC);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ListPro;
    }


}
