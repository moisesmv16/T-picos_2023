package Components;

import Vistas.CategoriaForm;
import javafx.scene.control.*;
import modelos.CategoriasDAO;

import java.util.Optional;

public class ButtonCell extends TableCell<CategoriasDAO,String> {
    private Button btnCelda;
    private int opc;
    private TableView<CategoriasDAO> tbvCategorias;
    private TableView<CategoriasDAO> tbvProducto;
    private CategoriasDAO objCat;

    public ButtonCell(int opc){
        this.opc = opc;
        String txtBtn = this.opc == 1 ? "Editar" : "Eliminar";
        btnCelda=new Button(txtBtn);
        btnCelda.setOnAction(event -> accionBoton());
    }

    private void accionBoton(){
        tbvCategorias = ButtonCell.this.getTableView();
        objCat = tbvCategorias.getItems().get(ButtonCell.this.getIndex());

        if(this.opc ==1){
            new CategoriaForm(tbvCategorias, objCat);
        }else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Topicos Avanzados de Programacion");
            alert.setHeaderText("Confirmacion del sistema");
            alert.setContentText("¿Deseas eliminar?");

            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK){
                objCat.ELIMINAR();
                tbvCategorias.setItems(objCat.LISTARCATEGORIAS());
                tbvCategorias.refresh();
            }
        }
    }
    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty){
            this.setGraphic(btnCelda);
        }
    }
}
