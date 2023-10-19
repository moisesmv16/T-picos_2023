package Vistas;

import Components.ButtonCell;
import Components.ButtonCell2;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import modelos.CategoriasDAO;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

public class Baileys extends Stage {
    private Scene escena;
    private HBox hBoxMostrar;
    private VBox vBox,vBoxTabla,vBoxTablaSugerencia,vBoxSuge;
    private VBox vBox2,vBox3,vBox4,vBoxPostre1,vBoxPostre2;
    private VBox vBoxEspe1,vBoxEspe2,vBoxBebi1,vBoxBebi2;
    private Button btnSugerir,btnCarrito,btnTacos,btnPostres,btnEspecialidad,btnBebidas,btnTacoPastor,btnTacoCabeza,btnTacoCostilla,btnTacoChorizo;
    private Button btnAlambre,btnCarneAsada,btnPapa,btnVolcanes;
    private Button btnPay,btnHelado,btnGelatina,btnPastel,btnBurrito;
    private Button btnCoca,btnAguaNatural,btnAguaLimon,btnPepsi,btnCerveza;
    //private ObservableList<String> itemsCarrito = FXCollections.observableArrayList();
    private TableView<CategoriasDAO> tbvProducto;
    private TableView<CategoriasDAO> tbvCategorias;
    private Button btnOrdenar,btnSugerencia;
    private CategoriasDAO categoriasDAO;
    //private ObservableList<CategoriasDAO> tacosDePastorList = FXCollections.observableArrayList();


    public Baileys(){
        CrearUI();
        Panel panel = new Panel("Menu Principal");//pone el titulo al panel
        panel.getStyleClass().add("panel-primary");//agrega el panel prymary                            //(2)
        BorderPane content = new BorderPane();//agrega border pane
        content.setPadding(new Insets(20));//espacio del padre hacia dentro
        content.setTop(hBoxMostrar);
        content.setLeft(vBox);//
        content.setRight(vBox2);
        panel.setBody(content);//carga el contenido
        escena = new Scene(panel,200,200);
        escena.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());//hoja de estilos de bootstrap  //(3)
        escena.getStylesheets().add(getClass().getResource("/Estilos/Baileys.css").toString());
        this.setTitle("BIENVENIDO AL RESTAURANTE BAILEYS...");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI(){

        categoriasDAO = new CategoriasDAO();
        tbvProducto = new TableView<CategoriasDAO>();
        CrearTable();
        btnOrdenar=new Button("Ordenar");
        btnOrdenar.getStyleClass().setAll("btn","btn-success");
        btnOrdenar.setOnAction(event -> {LimpiarTabla();});
        vBoxTabla = new VBox(tbvProducto,btnOrdenar);

        categoriasDAO=new CategoriasDAO();
        tbvCategorias = new TableView<CategoriasDAO>();
        CrearSuge();
        btnSugerencia=new Button("sugerir");
        btnSugerencia.getStyleClass().setAll("btn","btn-success");
        btnSugerencia.setOnAction(event -> LimpiarTablaSugerencia());

        Image imgCerveza = new Image("C:\\Users\\Hp\\IdeaProjects\\Topicos\\src\\main\\resources\\ImagenesTaqueria\\24.jpg");
        ImageView imvcer = new ImageView(imgCerveza);
        imvcer.setFitWidth(50);
        imvcer.setFitHeight(50);

        btnCerveza = new Button("Cerveza");
        btnCerveza.setGraphic(imvcer);
        btnCerveza.getStyleClass().setAll("btn","btn-default");
        btnCerveza.setOnAction(event -> SugerirCerveza());

        Image imgBurrito = new Image("C:\\Users\\Hp\\IdeaProjects\\Topicos\\src\\main\\resources\\ImagenesTaqueria\\25.jpg");
        ImageView imvBurrito = new ImageView(imgBurrito);
        imvBurrito.setFitWidth(50);
        imvBurrito.setFitHeight(50);

        btnBurrito = new Button("Burrito");
        btnBurrito.setGraphic(imvBurrito);
        btnBurrito.getStyleClass().setAll("btn","btn-default");
        btnBurrito.setOnAction(event -> SugerirBurrito());

        vBoxSuge = new VBox();
        vBoxSuge.setSpacing(35);
        vBoxSuge.getChildren().setAll(btnCerveza,btnBurrito);

        vBoxTablaSugerencia = new VBox(tbvCategorias,btnSugerencia,vBoxSuge);



        Image imgTaco = new Image("C:\\Users\\Hp\\IdeaProjects\\Topicos\\src\\main\\resources\\ImagenesTaqueria\\1.jpg");
        ImageView imv = new ImageView(imgTaco);
        imv.setFitWidth(200);
        imv.setFitHeight(200);

        btnTacos = new Button("Tacos");
        btnTacos.setGraphic(imv);
        btnTacos.getStyleClass().setAll("btn","btn-default");
        btnTacos.setOnAction(event -> AbrirTacos());

        Image imgPostres = new Image("C:\\Users\\Hp\\IdeaProjects\\Topicos\\src\\main\\resources\\ImagenesTaqueria\\2.jpg");
        ImageView imv1 = new ImageView(imgPostres);
        imv1.setFitWidth(200);
        imv1.setFitHeight(200);

        btnPostres = new Button("Postres");
        btnPostres.setGraphic(imv1);
        btnPostres.getStyleClass().setAll("btn","btn-default");
        btnPostres.setOnAction(event -> AbrirPostres());

        vBox = new VBox();
        vBox.setSpacing(35);
        vBox.getChildren().setAll(btnTacos,btnPostres);

        Image imgEspecialidad = new Image("C:\\Users\\Hp\\IdeaProjects\\Topicos\\src\\main\\resources\\ImagenesTaqueria\\3.jpg");
        ImageView imv2 = new ImageView(imgEspecialidad);
        imv2.setFitWidth(200);
        imv2.setFitHeight(200);

        btnEspecialidad = new Button("Especialidades");
        btnEspecialidad.setGraphic(imv2);
        btnEspecialidad.getStyleClass().setAll("btn","btn-default");
        btnEspecialidad.setOnAction(event -> AbrirEspecialidad());

        Image imgBebidas = new Image("C:\\Users\\Hp\\IdeaProjects\\Topicos\\src\\main\\resources\\ImagenesTaqueria\\4.jpg");
        ImageView imv3 = new ImageView(imgBebidas);
        imv3.setFitWidth(200);
        imv3.setFitHeight(200);

        btnBebidas = new Button("Bebidas");
        btnBebidas.setGraphic(imv3);
        btnBebidas.getStyleClass().setAll("btn","btn-default");
        btnBebidas.setOnAction(event -> AbrirBebidas());

        vBox2 = new VBox();
        vBox2.setSpacing(35);
        vBox2.getChildren().setAll(btnEspecialidad,btnBebidas);

        Image imgCarrito = new Image("C:\\Users\\Hp\\IdeaProjects\\Topicos\\src\\main\\resources\\ImagenesTaqueria\\21.jpg");
        ImageView imvcarrito = new ImageView(imgCarrito);
        imvcarrito.setFitWidth(50);
        imvcarrito.setFitHeight(50);

        btnCarrito = new Button("");
        btnCarrito.setGraphic(imvcarrito);
        btnCarrito.getStyleClass().setAll("btn","btn-default");
        btnCarrito.setOnAction(event -> Carrito());

        Image imgSuge = new Image("C:\\Users\\Hp\\IdeaProjects\\Topicos\\src\\main\\resources\\ImagenesTaqueria\\23.jpg");
        ImageView imvSuge = new ImageView(imgSuge);
        imvSuge.setFitWidth(50);
        imvSuge.setFitHeight(50);

        btnSugerir = new Button("");
        btnSugerir.setGraphic(imvSuge);
        btnSugerir.getStyleClass().setAll("btn","btn-default");
        btnSugerir.setOnAction(event -> Sugerencia());


        hBoxMostrar = new HBox();
        hBoxMostrar.setSpacing(50);
        hBoxMostrar.getChildren().setAll(btnCarrito,btnSugerir);


        //tacos

        Image imgTacosPastor = new Image("C:\\Users\\Hp\\IdeaProjects\\Topicos\\src\\main\\resources\\ImagenesTaqueria\\5.jpg");
        ImageView imv4 = new ImageView(imgTacosPastor);
        imv4.setFitWidth(200);
        imv4.setFitHeight(200);

        btnTacoPastor=new Button("Tacos de pastor");
        btnTacoPastor.setGraphic(imv4);
        btnTacoPastor.getStyleClass().setAll("btn","btn-default");
        btnTacoPastor.setOnAction(event ->{
            AgregarTacoPastor();
        });

        Image imgTacosCabeza = new Image("C:\\Users\\Hp\\IdeaProjects\\Topicos\\src\\main\\resources\\ImagenesTaqueria\\6.jpg");
        ImageView imv5 = new ImageView(imgTacosCabeza);
        imv5.setFitWidth(200);
        imv5.setFitHeight(200);

        btnTacoCabeza=new Button("Tacos de cabeza");
        btnTacoCabeza.setGraphic(imv5);
        btnTacoCabeza.getStyleClass().setAll("btn","btn-default");
        btnTacoCabeza.setOnAction(event -> {
            AgregarTacoCabeza();
        });

        vBox3=new VBox();
        vBox3.setSpacing(35);
        vBox3.getChildren().setAll(btnTacoPastor,btnTacoCabeza);

        Image imgTacosCostilla = new Image("C:\\Users\\Hp\\IdeaProjects\\Topicos\\src\\main\\resources\\ImagenesTaqueria\\7.jpg");
        ImageView imv6 = new ImageView(imgTacosCostilla);
        imv6.setFitWidth(200);
        imv6.setFitHeight(200);

        btnTacoCostilla=new Button("Tacos de costilla");
        btnTacoCostilla.setGraphic(imv6);
        btnTacoCostilla.getStyleClass().setAll("btn","btn-default");
        btnTacoCostilla.setOnAction(event -> {
            AgregarTacoCostilla();
        });

        Image imgTacosChorizo = new Image("C:\\Users\\Hp\\IdeaProjects\\Topicos\\src\\main\\resources\\ImagenesTaqueria\\8.jpg");
        ImageView imv7 = new ImageView(imgTacosChorizo);
        imv7.setFitWidth(200);
        imv7.setFitHeight(200);

        btnTacoChorizo=new Button("Tacos de chorizo");
        btnTacoChorizo.setGraphic(imv7);
        btnTacoChorizo.getStyleClass().setAll("btn","btn-default");
        btnTacoChorizo.setOnAction(event -> {
            AgregarTacoChorizo();
        });

        vBox4=new VBox();
        vBox4.setSpacing(35);
        vBox4.getChildren().setAll(btnTacoCostilla,btnTacoChorizo);

        //Postres


        Image imgPay = new Image("C:\\Users\\Hp\\IdeaProjects\\Topicos\\src\\main\\resources\\ImagenesTaqueria\\9.jpg");
        ImageView imv8 = new ImageView(imgPay);
        imv8.setFitWidth(200);
        imv8.setFitHeight(200);

        btnPay = new Button("Pay");
        btnPay.setGraphic(imv8);
        btnPay.getStyleClass().setAll("btn","btn-default");
        btnPay.setOnAction(event -> {
            AgregarPay();
        });

        Image imgHelado = new Image("C:\\Users\\Hp\\IdeaProjects\\Topicos\\src\\main\\resources\\ImagenesTaqueria\\10.jpg");
        ImageView imv9 = new ImageView(imgHelado);
        imv9.setFitWidth(200);
        imv9.setFitHeight(200);

        btnHelado=new Button("Helado");
        btnHelado.setGraphic(imv9);
        btnHelado.getStyleClass().setAll("btn","btn-default");
        btnHelado.setOnAction(event -> {
            AgregarHelado();
        });

        vBoxPostre1=new VBox();
        vBoxPostre1.setSpacing(35);
        vBoxPostre1.getChildren().setAll(btnPay,btnHelado);

        Image imgGelatina = new Image("C:\\Users\\Hp\\IdeaProjects\\Topicos\\src\\main\\resources\\ImagenesTaqueria\\11.jpg");
        ImageView imv10 = new ImageView(imgGelatina);
        imv10.setFitWidth(200);
        imv10.setFitHeight(200);

        btnGelatina=new Button("Gelatina");
        btnGelatina.setGraphic(imv10);
        btnGelatina.getStyleClass().setAll("btn","btn-default");
        btnGelatina.setOnAction(event -> {
            AgregarGelatina();
        });

        Image imgPastel = new Image("C:\\Users\\Hp\\IdeaProjects\\Topicos\\src\\main\\resources\\ImagenesTaqueria\\12.jpg");
        ImageView imv11 = new ImageView(imgPastel);
        imv11.setFitWidth(200);
        imv11.setFitHeight(200);

        btnPastel=new Button("Pastel");
        btnPastel.setGraphic(imv11);
        btnPastel.getStyleClass().setAll("btn","btn-default");
        btnPastel.setOnAction(event -> {
            AgregarPastel();
        });

        vBoxPostre2=new VBox();
        vBoxPostre2.setSpacing(35);
        vBoxPostre2.getChildren().setAll(btnGelatina,btnPastel);

        //Especialidades

        Image imgAlambre = new Image("C:\\Users\\Hp\\IdeaProjects\\Topicos\\src\\main\\resources\\ImagenesTaqueria\\13.jpg");
        ImageView imv12 = new ImageView(imgAlambre);
        imv12.setFitWidth(200);
        imv12.setFitHeight(200);

        btnAlambre=new Button("Alambre");
        btnAlambre.setGraphic(imv12);
        btnAlambre.getStyleClass().setAll("btn","btn-default");
        btnAlambre.setOnAction(event -> {
            AgregarAlambre();
        });

        Image imgAsada = new Image("C:\\Users\\Hp\\IdeaProjects\\Topicos\\src\\main\\resources\\ImagenesTaqueria\\14.jpg");
        ImageView imv13 = new ImageView(imgAsada);
        imv13.setFitWidth(200);
        imv13.setFitHeight(200);

        btnCarneAsada=new Button("Carne asada");
        btnCarneAsada.setGraphic(imv13);
        btnCarneAsada.getStyleClass().setAll("btn","btn-default");
        btnCarneAsada.setOnAction(event -> {
            AgregarAsada();
        });

        vBoxEspe1=new VBox();
        vBoxEspe1.setSpacing(35);
        vBoxEspe1.getChildren().setAll(btnAlambre,btnCarneAsada);

        Image imgPapa = new Image("C:\\Users\\Hp\\IdeaProjects\\Topicos\\src\\main\\resources\\ImagenesTaqueria\\15.jpg");
        ImageView imv14 = new ImageView(imgPapa);
        imv14.setFitWidth(200);
        imv14.setFitHeight(200);

        btnPapa=new Button("Papa rellena");
        btnPapa.setGraphic(imv14);
        btnPapa.getStyleClass().setAll("btn","btn-default");
        btnPapa.setOnAction(event -> {
            AgregarPapa();
        });

        Image imgVolcan = new Image("C:\\Users\\Hp\\IdeaProjects\\Topicos\\src\\main\\resources\\ImagenesTaqueria\\16.jpg");
        ImageView imv15 = new ImageView(imgVolcan);
        imv15.setFitWidth(200);
        imv15.setFitHeight(200);

        btnVolcanes=new Button("Volcanes");
        btnVolcanes.setGraphic(imv15);
        btnVolcanes.getStyleClass().setAll("btn","btn-default");
        btnVolcanes.setOnAction(event -> {
            AgregarVolcanes();
        });

        vBoxEspe2=new VBox();
        vBoxEspe2.setSpacing(35);
        vBoxEspe2.getChildren().setAll(btnPapa,btnVolcanes);

        //bebidas

        Image imgCoca = new Image("C:\\Users\\Hp\\IdeaProjects\\Topicos\\src\\main\\resources\\ImagenesTaqueria\\17.jpg");
        ImageView imv16 = new ImageView(imgCoca);
        imv16.setFitWidth(200);
        imv16.setFitHeight(200);

        btnCoca=new Button("Coca");
        btnCoca.setGraphic(imv16);
        btnCoca.getStyleClass().setAll("btn","btn-default");
        btnCoca.setOnAction(event -> {
            AgregarCoca();
        });

        Image imgAguaNatural = new Image("C:\\Users\\Hp\\IdeaProjects\\Topicos\\src\\main\\resources\\ImagenesTaqueria\\18.jpg");
        ImageView imv17 = new ImageView(imgAguaNatural);
        imv17.setFitWidth(200);
        imv17.setFitHeight(200);

        btnAguaNatural=new Button("Agua");
        btnAguaNatural.setGraphic(imv17);
        btnAguaNatural.getStyleClass().setAll("btn","btn-default");
        btnAguaNatural.setOnAction(event -> {
            AgregarAgua();
        });

        vBoxBebi1=new VBox();
        vBoxBebi1.setSpacing(35);
        vBoxBebi1.getChildren().setAll(btnCoca,btnAguaNatural);

        Image imgAguaLimon = new Image("C:\\Users\\Hp\\IdeaProjects\\Topicos\\src\\main\\resources\\ImagenesTaqueria\\19.jpg");
        ImageView imv18 = new ImageView(imgAguaLimon);
        imv18.setFitWidth(200);
        imv18.setFitHeight(200);

        btnAguaLimon=new Button("Agua Limon");
        btnAguaLimon.setGraphic(imv18);
        btnAguaLimon.getStyleClass().setAll("btn","btn-default");
        btnAguaLimon.setOnAction(event -> {
            AgregarAguaLimon();
        });

        Image imgPepsi = new Image("C:\\Users\\Hp\\IdeaProjects\\Topicos\\src\\main\\resources\\ImagenesTaqueria\\20.jpg");
        ImageView imv19 = new ImageView(imgPepsi);
        imv19.setFitWidth(200);
        imv19.setFitHeight(200);

        btnPepsi=new Button("Pepsi");
        btnPepsi.setGraphic(imv19);
        btnPepsi.getStyleClass().setAll("btn","btn-default");
        btnPepsi.setOnAction(event -> {
            AgregarPepsi();
        });

        vBoxBebi2=new VBox();
        vBoxBebi2.setSpacing(35);
        vBoxBebi2.getChildren().setAll(btnAguaLimon,btnPepsi);


    }

    public  void CrearTable(){
        TableColumn<CategoriasDAO,Integer> tbcCarrito = new TableColumn<>("ID");
        tbcCarrito.setCellValueFactory(new PropertyValueFactory<>("idProducto"));

        TableColumn<CategoriasDAO,String> tbcNomPro = new TableColumn<>("Producto");
        tbcNomPro.setCellValueFactory(new PropertyValueFactory<>("nomProducto"));

        TableColumn<CategoriasDAO,String> tbcEliminar = new TableColumn<>("Eliminar");
        tbcEliminar.setCellFactory(
                new Callback<TableColumn<CategoriasDAO, String>, TableCell<CategoriasDAO, String>>() {
                    @Override
                    public TableCell<CategoriasDAO, String> call(TableColumn<CategoriasDAO, String> categoriasDAOStringTableColumn) {
                        return new ButtonCell2(2);
                    }
                }
        );
        tbvProducto.getColumns().addAll(tbcCarrito,tbcNomPro,tbcEliminar);
        tbvProducto.setItems(categoriasDAO.LISTARPRODUCTOS());
    }

    public void CrearSuge(){
        TableColumn<CategoriasDAO,Integer> tbcldCat = new TableColumn<>("ID");
        tbcldCat.setCellValueFactory(new PropertyValueFactory<>("idCategoria"));

        TableColumn<CategoriasDAO,String> tbcNomCat = new TableColumn<>("Categoria");
        tbcNomCat.setCellValueFactory(new PropertyValueFactory<>("nomCategoria"));

        TableColumn<CategoriasDAO,String> tbcEliminar = new TableColumn<>("Eliminar");
        tbcEliminar.setCellFactory(
                new Callback<TableColumn<CategoriasDAO, String>, TableCell<CategoriasDAO, String>>() {
                    @Override
                    public TableCell<CategoriasDAO, String> call(TableColumn<CategoriasDAO, String> categoriasDAOStringTableColumn) {
                        return new ButtonCell(2);
                    }
                }
        );
        tbvCategorias.getColumns().addAll(tbcldCat,tbcNomCat,tbcEliminar);
        tbvCategorias.setItems(categoriasDAO.LISTARCATEGORIAS());
    }
    private void LimpiarTabla(){
        tbvProducto.getItems().clear();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Compra Exitosa");
        alert.setHeaderText(null);
        alert.setContentText("Gracias por su compra");
        alert.getButtonTypes().setAll(ButtonType.OK);
        alert.showAndWait();
    }

    private void LimpiarTablaSugerencia(){
        tbvCategorias.getItems().clear();
        btnCerveza.setDisable(false);
        btnBurrito.setDisable(false);
    }

    private void AbrirTacos(){
        Stage nuevaVentana = new Stage();
        Panel panel = new Panel("Menu de Tacos...");
        panel.getStyleClass().setAll("panel-primary");
        BorderPane content = new BorderPane();
        content.setPadding(new Insets(20));
        content.setLeft(vBox3); //
        content.setCenter(vBox4);
        panel.setBody(content); // Carga el contenido

        Scene nuevaEscena = new Scene(panel, 400, 400); //
        nuevaEscena.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        nuevaEscena.getStylesheets().add(getClass().getResource("/Estilos/Baileys.css").toString());
        nuevaVentana.setTitle("Elija sus tacos favoritos..");
        nuevaVentana.setScene(nuevaEscena);
        nuevaVentana.setOnCloseRequest(event -> nuevaVentana.close());
        nuevaVentana.show();
    }

    private void AbrirPostres(){
        Stage nuevaVentana = new Stage();
        Panel panel = new Panel("Menu de Postres...");
        panel.getStyleClass().setAll("panel-primary");
        BorderPane content = new BorderPane();
        content.setPadding(new Insets(20));
        content.setLeft(vBoxPostre1); //
        content.setCenter(vBoxPostre2);
        panel.setBody(content); // Carga el contenido

        Scene nuevaEscena = new Scene(panel, 400, 400); //
        nuevaEscena.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        nuevaEscena.getStylesheets().add(getClass().getResource("/Estilos/Baileys.css").toString());
        nuevaVentana.setTitle("Escoge lo que mas se te antoje..");
        nuevaVentana.setScene(nuevaEscena);
        nuevaVentana.setOnCloseRequest(event -> nuevaVentana.close());
        nuevaVentana.show();
    }

    private void AbrirEspecialidad(){
        Stage nuevaVentana = new Stage();
        Panel panel = new Panel("Menu de Especialidades de la casa...");
        panel.getStyleClass().setAll("panel-primary");
        BorderPane content = new BorderPane();
        content.setPadding(new Insets(20));
        content.setLeft(vBoxEspe1); //
        content.setCenter(vBoxEspe2);
        panel.setBody(content); // Carga el contenido

        Scene nuevaEscena = new Scene(panel, 400, 400); //
        nuevaEscena.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        nuevaEscena.getStylesheets().add(getClass().getResource("/Estilos/Baileys.css").toString());
        nuevaVentana.setTitle("Elige una de nuestras especalidades..");
        nuevaVentana.setScene(nuevaEscena);
        nuevaVentana.setOnCloseRequest(event -> nuevaVentana.close());
        nuevaVentana.show();
    }

    private void AbrirBebidas(){
        Stage nuevaVentana = new Stage();
        Panel panel = new Panel("Menu de Bebidas...");
        panel.getStyleClass().setAll("panel-primary");
        BorderPane content = new BorderPane();
        content.setPadding(new Insets(20));
        content.setLeft(vBoxBebi1); //
        content.setCenter(vBoxBebi2);
        panel.setBody(content); // Carga el contenido

        Scene nuevaEscena = new Scene(panel, 400, 400); //
        nuevaEscena.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        nuevaEscena.getStylesheets().add(getClass().getResource("/Estilos/Baileys.css").toString());
        nuevaVentana.setTitle("Elige una de nuestras Bebidas refrescantes..");
        nuevaVentana.setScene(nuevaEscena);
        nuevaVentana.setOnCloseRequest(event -> nuevaVentana.close());
        nuevaVentana.show();
    }

    private void Carrito() {
        Stage nuevaVentana = new Stage();
        Panel panel = new Panel("Carrito de Compras");
        panel.getStyleClass().setAll("panel-primary");
        BorderPane content = new BorderPane();
        content.setPadding(new Insets(20));
        content.setCenter(vBoxTabla);
        panel.setBody(content);

        Scene nuevaEscena = new Scene(panel, 400, 400);
        nuevaEscena.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        nuevaVentana.setTitle("Carrito de Compras");
        nuevaVentana.setScene(nuevaEscena);
        nuevaVentana.setOnCloseRequest(event -> nuevaVentana.close());
        nuevaVentana.show();
    }

    private void Sugerencia(){
        Stage nuevaVentana = new Stage();
        Panel panel = new Panel("Sugerencia de Platillos nuevos");
        panel.getStyleClass().setAll("panel-primary");
        BorderPane content = new BorderPane();
        content.setPadding(new Insets(20));
        content.setCenter(vBoxTablaSugerencia);
        panel.setBody(content);

        Scene nuevaEscena = new Scene(panel, 400, 400);
        nuevaEscena.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        nuevaVentana.setTitle("Platillos a sugerir");
        nuevaVentana.setScene(nuevaEscena);
        nuevaVentana.setOnCloseRequest(event -> nuevaVentana.close());
        nuevaVentana.show();
    }

    public void AgregarTacoPastor(){
        CategoriasDAO pedido = new CategoriasDAO();
        pedido.setNomProducto("Taco de Pastor");
        pedido.INSERTARPRODUCTO();
        int ProductID = pedido.getIdProducto();

        if(ProductID > 0){
            pedido.setIdProducto(ProductID);
        }
        tbvProducto.setItems(pedido.LISTARPRODUCTOS());
        tbvProducto.refresh();
    }

    public void AgregarTacoCabeza(){
        CategoriasDAO pedido = new CategoriasDAO();
        pedido.setNomProducto("Taco de Cabeza");
        pedido.INSERTARPRODUCTO();
        int ProductID = pedido.getIdProducto();

        if(ProductID > 0){
            pedido.setIdProducto(ProductID);
        }
        tbvProducto.setItems(pedido.LISTARPRODUCTOS());
        tbvProducto.refresh();
    }

    public void AgregarTacoCostilla(){
        CategoriasDAO pedido = new CategoriasDAO();
        pedido.setNomProducto("Taco de Costilla");
        pedido.INSERTARPRODUCTO();
        int ProductID = pedido.getIdProducto();

        if(ProductID > 0){
            pedido.setIdProducto(ProductID);
        }
        tbvProducto.setItems(pedido.LISTARPRODUCTOS());
        tbvProducto.refresh();
    }

    public void AgregarTacoChorizo(){
        CategoriasDAO pedido = new CategoriasDAO();
        pedido.setNomProducto("Taco de Chorizo");
        pedido.INSERTARPRODUCTO();
        int ProductID = pedido.getIdProducto();

        if(ProductID > 0){
            pedido.setIdProducto(ProductID);
        }
        tbvProducto.setItems(pedido.LISTARPRODUCTOS());
        tbvProducto.refresh();
    }

    public void AgregarPay(){
        CategoriasDAO pedido = new CategoriasDAO();
        pedido.setNomProducto("Rebanada de pay");
        pedido.INSERTARPRODUCTO();
        int ProductID = pedido.getIdProducto();

        if(ProductID > 0){
            pedido.setIdProducto(ProductID);
        }
        tbvProducto.setItems(pedido.LISTARPRODUCTOS());
        tbvProducto.refresh();
    }

    public void AgregarHelado(){
        CategoriasDAO pedido = new CategoriasDAO();
        pedido.setNomProducto("Helado individual");
        pedido.INSERTARPRODUCTO();
        int ProductID = pedido.getIdProducto();

        if(ProductID > 0){
            pedido.setIdProducto(ProductID);
        }
        tbvProducto.setItems(pedido.LISTARPRODUCTOS());
        tbvProducto.refresh();
    }

    public void AgregarGelatina(){
        CategoriasDAO pedido = new CategoriasDAO();
        pedido.setNomProducto("Gelatina individual");
        pedido.INSERTARPRODUCTO();
        int ProductID = pedido.getIdProducto();

        if(ProductID > 0){
            pedido.setIdProducto(ProductID);
        }
        tbvProducto.setItems(pedido.LISTARPRODUCTOS());
        tbvProducto.refresh();
    }

    public void AgregarPastel(){
        CategoriasDAO pedido = new CategoriasDAO();
        pedido.setNomProducto("Rebanada de Pastel");
        pedido.INSERTARPRODUCTO();
        int ProductID = pedido.getIdProducto();

        if(ProductID > 0){
            pedido.setIdProducto(ProductID);
        }
        tbvProducto.setItems(pedido.LISTARPRODUCTOS());
        tbvProducto.refresh();
    }

    public void AgregarAlambre(){
        CategoriasDAO pedido = new CategoriasDAO();
        pedido.setNomProducto("Un Alambre Especial");
        pedido.INSERTARPRODUCTO();
        int ProductID = pedido.getIdProducto();

        if(ProductID > 0){
            pedido.setIdProducto(ProductID);
        }
        tbvProducto.setItems(pedido.LISTARPRODUCTOS());
        tbvProducto.refresh();
    }

    public void AgregarAsada(){
        CategoriasDAO pedido = new CategoriasDAO();
        pedido.setNomProducto("Plato de Carne Asada");
        pedido.INSERTARPRODUCTO();
        int ProductID = pedido.getIdProducto();

        if(ProductID > 0){
            pedido.setIdProducto(ProductID);
        }
        tbvProducto.setItems(pedido.LISTARPRODUCTOS());
        tbvProducto.refresh();
    }

    public void AgregarPapa(){
        CategoriasDAO pedido = new CategoriasDAO();
        pedido.setNomProducto("Papa de pastor");
        pedido.INSERTARPRODUCTO();
        int ProductID = pedido.getIdProducto();

        if(ProductID > 0){
            pedido.setIdProducto(ProductID);
        }
        tbvProducto.setItems(pedido.LISTARPRODUCTOS());
        tbvProducto.refresh();

    }

    public void AgregarVolcanes(){
        CategoriasDAO pedido = new CategoriasDAO();
        pedido.setNomProducto("Orden de 4 Alambres");
        pedido.INSERTARPRODUCTO();
        int ProductID = pedido.getIdProducto();

        if(ProductID > 0){
            pedido.setIdProducto(ProductID);
        }
        tbvProducto.setItems(pedido.LISTARPRODUCTOS());
        tbvProducto.refresh();
    }

    public void AgregarCoca(){
        CategoriasDAO pedido = new CategoriasDAO();
        pedido.setNomProducto("una Coca Cola");
        pedido.INSERTARPRODUCTO();
        int ProductID = pedido.getIdProducto();

        if(ProductID > 0){
            pedido.setIdProducto(ProductID);
        }
        tbvProducto.setItems(pedido.LISTARPRODUCTOS());
        tbvProducto.refresh();
    }

    public void AgregarAgua(){
        CategoriasDAO pedido = new CategoriasDAO();
        pedido.setNomProducto("una Agua Natural");
        pedido.INSERTARPRODUCTO();
        int ProductID = pedido.getIdProducto();

        if(ProductID > 0){
            pedido.setIdProducto(ProductID);
        }
        tbvProducto.setItems(pedido.LISTARPRODUCTOS());
        tbvProducto.refresh();
    }
    public void AgregarAguaLimon(){
        CategoriasDAO pedido = new CategoriasDAO();
        pedido.setNomProducto("una Agua de limon");
        pedido.INSERTARPRODUCTO();
        int ProductID = pedido.getIdProducto();

        if(ProductID > 0){
            pedido.setIdProducto(ProductID);
        }
        tbvProducto.setItems(pedido.LISTARPRODUCTOS());
        tbvProducto.refresh();

    }
    public void AgregarPepsi(){
        CategoriasDAO pedido = new CategoriasDAO();
        pedido.setNomProducto("una Pepsi");
        pedido.INSERTARPRODUCTO();
        int ProductID = pedido.getIdProducto();

        if(ProductID > 0){
            pedido.setIdProducto(ProductID);
        }
        tbvProducto.setItems(pedido.LISTARPRODUCTOS());
        tbvProducto.refresh();
    }

    public void SugerirCerveza(){
        CategoriasDAO sugerir = new CategoriasDAO();
        sugerir.setNomCategoria("Bebidas");
        sugerir.INSERTAR();
        int ProductID = sugerir.getIdCategoria();

        if(ProductID > 0){
            sugerir.setIdCategoria(ProductID);
        }
        tbvCategorias.setItems(sugerir.LISTARCATEGORIAS());
        tbvCategorias.refresh();
        btnCerveza.setDisable(true);
    }

    public void SugerirBurrito(){
        CategoriasDAO sugerir = new CategoriasDAO();
        sugerir.setNomCategoria("Especialidades");
        sugerir.INSERTAR();
        int ProductID = sugerir.getIdCategoria();

        if(ProductID > 0){
            sugerir.setIdCategoria(ProductID);
        }
        tbvCategorias.setItems(sugerir.LISTARCATEGORIAS());
        tbvCategorias.refresh();
        btnBurrito.setDisable(true);
    }
}
