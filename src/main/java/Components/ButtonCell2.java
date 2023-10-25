package Components;

import Vistas.CategoriaForm;
import javafx.scene.control.*;
import modelos.CategoriasDAO;

import java.util.Optional;

public class ButtonCell2 extends TableCell<CategoriasDAO,String> {
    private Button btnCelda;
    private int opc;
    private TableView<CategoriasDAO> tbvProducto;
    private CategoriasDAO objPro;

    public ButtonCell2(int opc){
        this.opc = opc;
        String txtBtn = this.opc == 1 ? "Editar" : "Eliminar";
        btnCelda=new Button(txtBtn);
        btnCelda.setOnAction(event -> accionBotonBailey());
    }

    private void accionBotonBailey(){
        tbvProducto = ButtonCell2.this.getTableView();
        objPro = tbvProducto.getItems().get(ButtonCell2.this.getIndex());

        if(this.opc ==1){
            new CategoriaForm(tbvProducto, objPro);
        }else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Topicos Avanzados de Programacion");
            alert.setHeaderText("Confirmacion del sistema");
            alert.setContentText("¿Deseas eliminar?");

            Optional<ButtonType> result = alert.showAndWait();

            if(result.get() == ButtonType.OK){
                objPro.ELIMINARPLATILLO();
                tbvProducto.setItems(objPro.LISTARPLATILLOS());
                tbvProducto.refresh();
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
