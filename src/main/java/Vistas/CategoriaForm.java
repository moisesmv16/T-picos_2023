package Vistas;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import modelos.CategoriasDAO;

public class CategoriaForm extends Stage {
    private Scene escena;
    private HBox hBox;
    private Button btnGuardar;
    private TextField txtNameCat;
    private CategoriasDAO objCatDAO;
    TableView<CategoriasDAO> tvbCategorias;
    public CategoriaForm(TableView<CategoriasDAO> tbvCat, CategoriasDAO objCatDAO){
        this.tvbCategorias = tbvCat;
        this.objCatDAO= objCatDAO == null ? new CategoriasDAO() : objCatDAO;
        CrearUI();
        escena = new Scene(hBox);
        this.setTitle("Gestion de Categorias");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {

        txtNameCat = new TextField();
        txtNameCat.setText(objCatDAO.getNomCategoria());
        txtNameCat.setPromptText("Nombre de la categoria");
        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(event -> guardarCategoria());
        hBox = new HBox(txtNameCat,btnGuardar);
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(10));
    }

    private void guardarCategoria(){
        objCatDAO.setNomCategoria(txtNameCat.getText());
        if(objCatDAO.getIdCategoria() > 0){
            objCatDAO.ACTUALIZAR();
        }else{
            objCatDAO.INSERTAR();
        }

        tvbCategorias.setItems(objCatDAO.LISTARCATEGORIAS());
        tvbCategorias.refresh();
        this.close();
    }
}
