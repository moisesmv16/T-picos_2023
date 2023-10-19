package Vistas;

import Components.ButtonCell;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import modelos.CategoriasDAO;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

public class Restaurante extends Stage {
    private VBox vBox;
    private TableView<CategoriasDAO> tbvCategorias;
    private Button btnAgregar;
    private CategoriasDAO categoriasDAO;

    public Restaurante(){
        CrearUI();
        Panel panel = new Panel("This is the title");//pone el titulo al panel
        panel.getStyleClass().add("panel-primary");//agrega el panel prymary                            //(2)
        BorderPane content = new BorderPane();//agrega border pane
        content.setPadding(new Insets(20));//espacio del padre hacia dentro
        //Button button = new Button("Hello BootstrapFX");//boton
        //button.getStyleClass().setAll("btn","btn-info");//crear 2 clases al boton          //(2)
        content.setCenter(vBox);//
        panel.setBody(content);//carga el contenido

        Scene scene = new Scene(panel);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());//hoja de estilos de bootstrap  //(3)

        this.setTitle("BootstrapFX");
        this.setScene(scene);
        this.sizeToScene();
        this.show();
    }

    private void CrearUI(){
        categoriasDAO = new CategoriasDAO();
        tbvCategorias = new TableView<CategoriasDAO>();
        CrearTable();
        btnAgregar = new Button("Agregar");
        btnAgregar.getStyleClass().setAll("btn","btn-success");
        btnAgregar.setOnAction(event -> new CategoriaForm(tbvCategorias, null));
        vBox = new VBox(tbvCategorias,btnAgregar);
    }

    private void CrearTable(){
        TableColumn<CategoriasDAO,Integer> tbcldCat = new TableColumn<>("ID");
        tbcldCat.setCellValueFactory(new PropertyValueFactory<>("idCategoria"));

        TableColumn<CategoriasDAO,String> tbcNomCat = new TableColumn<>("Nombre");
        tbcNomCat.setCellValueFactory(new PropertyValueFactory<>("nomCategoria"));

        TableColumn<CategoriasDAO,String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(
                new Callback<TableColumn<CategoriasDAO, String>, TableCell<CategoriasDAO, String>>() {
                    @Override
                    public TableCell<CategoriasDAO, String> call(TableColumn<CategoriasDAO, String> categoriasDAOStringTableColumn) {
                        return new ButtonCell(1);
                    }
                }
        );

        TableColumn<CategoriasDAO,String> tbcEliminar = new TableColumn<>("Eliminar");
        tbcEliminar.setCellFactory(
                new Callback<TableColumn<CategoriasDAO, String>, TableCell<CategoriasDAO, String>>() {
                    @Override
                    public TableCell<CategoriasDAO, String> call(TableColumn<CategoriasDAO, String> categoriasDAOStringTableColumn) {
                        return new ButtonCell(2);
                    }
                }
        );


        tbvCategorias.getColumns().addAll(tbcldCat,tbcNomCat,tbcEditar,tbcEliminar);
        tbvCategorias.setItems(categoriasDAO.LISTARCATEGORIAS());
    }
}