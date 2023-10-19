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

    public int getIdProducto() {return idProducto;}

    public void setIdProducto(int idProducto) {this.idProducto = idProducto;}

    public String getNomProducto() {return nomProducto;}

    public void setNomProducto(String nomProducto) {this.nomProducto = nomProducto;}

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

    public void INSERTARPRODUCTO(){
        try {
            String query = "INSERT INTO Carrito"+
                    "(nomProducto) VALUES('"+this.nomProducto+"')";
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void ACTUALIZARPRODUCTO(){
        try{
            String query = "UPDATE Carrito SET nomProducto = '"+this.nomProducto+"' "+
                    "WHERE idProducto = "+this.idProducto;
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void ELIMINARPRODUCTO(){
        try{
            String query = "DELETE FROM Carrito WHERE idProducto = "+this.idProducto;
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ObservableList<CategoriasDAO> LISTARPRODUCTOS(){
        ObservableList<CategoriasDAO> ListPro = FXCollections.observableArrayList();
        CategoriasDAO objC;
        try{
            String query = "SELECT * FROM Carrito";
            Statement stmt = Conexion.conexion.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objC = new CategoriasDAO();
                objC.setIdProducto(res.getInt("idProducto"));
                objC.setNomProducto(res.getString("nomProducto"));
                ListPro.add(objC);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ListPro;
    }



}
