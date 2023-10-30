package Components;

import javafx.scene.control.*;
import modelos.TaqueriasDAO;

import java.util.Optional;

public class ButtonCell3 extends TableCell<TaqueriasDAO,String> {
    private Button btnCelda;
    private int opc;
    private TableView<TaqueriasDAO> tbvTaquerias;
    private TableView<TaqueriasDAO> tbvTaque;
    private TaqueriasDAO objTaq;

    public ButtonCell3(int opc){
        this.opc = opc;
        String txtBtn = this.opc == 1 ? "Editar" : "Eliminar";
        btnCelda=new Button(txtBtn);
        btnCelda.setOnAction(event -> accionBoton());
    }

    private void accionBoton(){
        tbvTaquerias = ButtonCell3.this.getTableView();
        objTaq = tbvTaquerias.getItems().get(ButtonCell3.this.getIndex());

        if(this.opc ==1){
            //new CategoriaForm(tbvTaquerias, objCat);
        }else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Topicos Avanzados de Programacion");
            alert.setHeaderText("Confirmacion del sistema");
            alert.setContentText("¿Deseas eliminar?");

            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK){
                //objTaq.ELIMINARPLATILLO();
                //objTaq.ELIMINARPLATILLOTODO();
                objTaq.ELIMINARCATEGORIANUEVA();
                tbvTaquerias.setItems(objTaq.LISTARCATEGORIAS());
                tbvTaquerias.refresh();
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
