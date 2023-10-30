package Vistas;

import Components.ButtonCell;
import Components.ButtonCell2;
import Components.ButtonCell3;
import javafx.application.Platform;
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
import modelos.TaqueriasDAO;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

public class Baileys extends Stage {
    private Scene escena;
    private HBox hBoxMostrar, hBoxUsuarios;
    private VBox vBox, vBoxTablaPedido,vBoxSuge,vBoxTablaSugerencia;
    private VBox vBox2, vBox3, vBox4, vBoxPostre1, vBoxPostre2;
    private VBox vBoxEspe1, vBoxEspe2, vBoxBebi1, vBoxBebi2;
    private Button btnSugerir, btnCarrito, btnTacos, btnPostres, btnEspecialidad, btnBebidas, btnTacoPastor, btnTacoCabeza, btnTacoCostilla, btnTacoChorizo;
    private Button btnAlambre, btnCarneAsada, btnPapa, btnVolcanes,btnAcabarSugerencia;
    private Button btnPay, btnHelado, btnGelatina, btnPastel, btnSesion,btnOrdenar,btnPedir;
    private Button btnCoca, btnAguaNatural, btnAguaLimon, btnPepsi, btnCerveza, btnBurrito,btnSuge;

    private TaqueriasDAO taqueriasDAO;

    private Button btnCR7, btnMOY;
    private TableView<TaqueriasDAO> tbvTaquerias;

    private TableView<TaqueriasDAO> tbvTaque;



    public Baileys() {

        CrearUI();
        Panel panel = new Panel("Menu Principal");//pone el titulo al panel
        panel.getStyleClass().add("panel-primary");//agrega el panel prymary                            //(2)
        BorderPane content = new BorderPane();//agrega border pane
        content.setPadding(new Insets(20));//espacio del padre hacia dentro
        content.setTop(hBoxMostrar);
        content.setLeft(vBox);//
        content.setRight(vBox2);
        panel.setBody(content);//carga el contenido
        escena = new Scene(panel, 200, 200);
        escena.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());//hoja de estilos de bootstrap  //(3)
        escena.getStylesheets().add(getClass().getResource("/Estilos/Baileys.css").toString());
        this.setTitle("BIENVENIDO AL RESTAURANTE BAILEYS...");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {

        taqueriasDAO = new TaqueriasDAO();
        tbvTaquerias = new TableView<TaqueriasDAO>();
        CrearTabla();
        btnPedir = new Button("Ordenar Ya");
        btnPedir.getStyleClass().setAll("btn", "btn-success");
        btnPedir.setDisable(true); // Deshabilita el botón inicialmente

        btnPedir.setOnAction(event -> {
            LimpiarTabla();
        });

        btnOrdenar = new Button("Actualizar Orden");
        btnOrdenar.getStyleClass().setAll("btn", "btn-success");
        btnOrdenar.setOnAction(event -> {
            actualizarTabla();
            btnPedir.setDisable(false); // Habilita el botón al presionar btnOrdenar
            btnOrdenar.setDisable(true);
        });

        vBoxTablaPedido = new VBox(tbvTaquerias, btnOrdenar, btnPedir);


        taqueriasDAO = new TaqueriasDAO();
        tbvTaque = new TableView<TaqueriasDAO>();
        Crear();
        Image imgCerveza = new Image("C:\\Users\\Hp\\IdeaProjects\\Topicos\\src\\main\\resources\\ImagenesTaqueria\\24.jpg");
        ImageView imvcer = new ImageView(imgCerveza);
        imvcer.setFitWidth(50);
        imvcer.setFitHeight(50);

        btnCerveza = new Button("Cerveza");
        btnCerveza.setGraphic(imvcer);
        btnCerveza.getStyleClass().setAll("btn","btn-default");
        btnCerveza.setOnAction(event -> {
            AbrirCategoriaCerveza();
        });

        Image imgBurrito = new Image("C:\\Users\\Hp\\IdeaProjects\\Topicos\\src\\main\\resources\\ImagenesTaqueria\\25.jpg");
        ImageView imvBurrito = new ImageView(imgBurrito);
        imvBurrito.setFitWidth(50);
        imvBurrito.setFitHeight(50);

        btnBurrito = new Button("Burrito");
        btnBurrito.setGraphic(imvBurrito);
        btnBurrito.getStyleClass().setAll("btn","btn-default");
        btnBurrito.setOnAction(event -> {
            AbrirCategoriaBurrito();
        });

        vBoxSuge = new VBox();
        vBoxSuge.setSpacing(35);
        vBoxSuge.getChildren().setAll(btnCerveza,btnBurrito);

        btnSuge=new Button("Agregar");
        btnSuge.getStyleClass().setAll("btn", "btn-success");
        btnSuge.setOnAction(event -> {
            ActualizarTablaCategoria();
        });

        btnAcabarSugerencia=new Button("Terminar");
        btnAcabarSugerencia.getStyleClass().setAll("btn", "btn-success");
        btnAcabarSugerencia.setOnAction(event -> {
            LimpiarSuge();
        });

        vBoxTablaSugerencia = new VBox(tbvTaque,btnSuge,btnAcabarSugerencia,vBoxSuge);


        Image imgUsu1 = new Image("C:\\Users\\Hp\\IdeaProjects\\Topicos\\src\\main\\resources\\ImagenesTaqueria\\26.jpg");
        ImageView imvUsu = new ImageView(imgUsu1);
        imvUsu.setFitWidth(200);
        imvUsu.setFitHeight(200);

        btnCR7 = new Button(" ");
        btnCR7.setGraphic(imvUsu);
        btnCR7.getStyleClass().setAll("btn", "btn-default");
        btnCR7.setOnAction(event -> {
            AbrirCR7();
            BOTONCR7();
        });

        Image imgUsu2 = new Image("C:\\Users\\Hp\\IdeaProjects\\Topicos\\src\\main\\resources\\ImagenesTaqueria\\27.jpg");
        ImageView imvUsu2 = new ImageView(imgUsu2);
        imvUsu2.setFitWidth(200);
        imvUsu2.setFitHeight(200);

        btnMOY = new Button(" ");
        btnMOY.setGraphic(imvUsu2);
        btnMOY.getStyleClass().setAll("btn", "btn-default");
        btnMOY.setOnAction(event -> {
            AbrirHasbu();
            BOTONHASBU();
        });

        hBoxUsuarios = new HBox();
        hBoxUsuarios.setSpacing(35);
        hBoxUsuarios.getChildren().setAll(btnCR7, btnMOY);


        Image imgTaco = new Image("C:\\Users\\Hp\\IdeaProjects\\Topicos\\src\\main\\resources\\ImagenesTaqueria\\1.jpg");
        ImageView imv = new ImageView(imgTaco);
        imv.setFitWidth(200);
        imv.setFitHeight(200);

        btnTacos = new Button("Tacos");
        btnTacos.setGraphic(imv);
        btnTacos.getStyleClass().setAll("btn", "btn-default");
        btnTacos.setOnAction(event -> {
            AbrirTacos();
            AbrirCategoria();
        });

        Image imgPostres = new Image("C:\\Users\\Hp\\IdeaProjects\\Topicos\\src\\main\\resources\\ImagenesTaqueria\\2.jpg");
        ImageView imv1 = new ImageView(imgPostres);
        imv1.setFitWidth(200);
        imv1.setFitHeight(200);

        btnPostres = new Button("Postres");
        btnPostres.setGraphic(imv1);
        btnPostres.getStyleClass().setAll("btn", "btn-default");
        btnPostres.setOnAction(event -> {
            AbrirPostres();
            AbrirCategoriaPostres();
        });

        vBox = new VBox();
        vBox.setSpacing(35);
        vBox.getChildren().setAll(btnTacos, btnPostres);

        Image imgEspecialidad = new Image("C:\\Users\\Hp\\IdeaProjects\\Topicos\\src\\main\\resources\\ImagenesTaqueria\\3.jpg");
        ImageView imv2 = new ImageView(imgEspecialidad);
        imv2.setFitWidth(200);
        imv2.setFitHeight(200);

        btnEspecialidad = new Button("Especialidades");
        btnEspecialidad.setGraphic(imv2);
        btnEspecialidad.getStyleClass().setAll("btn", "btn-default");
        btnEspecialidad.setOnAction(event -> {
            AbrirEspecialidad();
            AbrirCategoriaEspecialidad();
        });

        Image imgBebidas = new Image("C:\\Users\\Hp\\IdeaProjects\\Topicos\\src\\main\\resources\\ImagenesTaqueria\\4.jpg");
        ImageView imv3 = new ImageView(imgBebidas);
        imv3.setFitWidth(200);
        imv3.setFitHeight(200);

        btnBebidas = new Button("Bebidas");
        btnBebidas.setGraphic(imv3);
        btnBebidas.getStyleClass().setAll("btn", "btn-default");
        btnBebidas.setOnAction(event -> {
            AbrirBebidas();
            AbrirCategoriaBebidas();
        });

        vBox2 = new VBox();
        vBox2.setSpacing(35);
        vBox2.getChildren().setAll(btnEspecialidad, btnBebidas);

        Image imgCarrito = new Image("C:\\Users\\Hp\\IdeaProjects\\Topicos\\src\\main\\resources\\ImagenesTaqueria\\21.jpg");
        ImageView imvcarrito = new ImageView(imgCarrito);
        imvcarrito.setFitWidth(50);
        imvcarrito.setFitHeight(50);

        btnCarrito = new Button("");
        btnCarrito.setGraphic(imvcarrito);
        btnCarrito.getStyleClass().setAll("btn", "btn-default");
        btnCarrito.setOnAction(event -> {
            Carrito();
            AsignarTicket();
        });

        Image imgSuge = new Image("C:\\Users\\Hp\\IdeaProjects\\Topicos\\src\\main\\resources\\ImagenesTaqueria\\23.jpg");
        ImageView imvSuge = new ImageView(imgSuge);
        imvSuge.setFitWidth(50);
        imvSuge.setFitHeight(50);

        btnSugerir = new Button("");
        btnSugerir.setGraphic(imvSuge);
        btnSugerir.getStyleClass().setAll("btn", "btn-default");
        btnSugerir.setOnAction(event -> Sugerencia());

        Image imgSesion = new Image("C:\\Users\\Hp\\IdeaProjects\\Topicos\\src\\main\\resources\\ImagenesTaqueria\\22.jpg");
        ImageView imvSesion = new ImageView(imgSesion);
        imvSesion.setFitWidth(50);
        imvSesion.setFitHeight(50);

        btnSesion = new Button("");
        btnSesion.setGraphic(imvSesion);
        btnSesion.getStyleClass().setAll("btn", "btn-default");
        btnSesion.setOnAction(event -> {
            Sesion();
        });


        hBoxMostrar = new HBox();
        hBoxMostrar.setSpacing(50);
        hBoxMostrar.getChildren().setAll(btnCarrito, btnSugerir, btnSesion);


        //tacos

        Image imgTacosPastor = new Image("C:\\Users\\Hp\\IdeaProjects\\Topicos\\src\\main\\resources\\ImagenesTaqueria\\5.jpg");
        ImageView imv4 = new ImageView(imgTacosPastor);
        imv4.setFitWidth(200);
        imv4.setFitHeight(200);

        btnTacoPastor = new Button("Tacos de pastor");
        btnTacoPastor.setGraphic(imv4);
        btnTacoPastor.getStyleClass().setAll("btn", "btn-default");
        btnTacoPastor.setOnAction(event -> {
            AsignarTaco();
        });

        Image imgTacosCabeza = new Image("C:\\Users\\Hp\\IdeaProjects\\Topicos\\src\\main\\resources\\ImagenesTaqueria\\6.jpg");
        ImageView imv5 = new ImageView(imgTacosCabeza);
        imv5.setFitWidth(200);
        imv5.setFitHeight(200);

        btnTacoCabeza = new Button("Tacos de cabeza");
        btnTacoCabeza.setGraphic(imv5);
        btnTacoCabeza.getStyleClass().setAll("btn", "btn-default");
        btnTacoCabeza.setOnAction(event -> {
            AsignarTacoCabeza();
        });

        vBox3 = new VBox();
        vBox3.setSpacing(35);
        vBox3.getChildren().setAll(btnTacoPastor, btnTacoCabeza);

        Image imgTacosCostilla = new Image("C:\\Users\\Hp\\IdeaProjects\\Topicos\\src\\main\\resources\\ImagenesTaqueria\\7.jpg");
        ImageView imv6 = new ImageView(imgTacosCostilla);
        imv6.setFitWidth(200);
        imv6.setFitHeight(200);

        btnTacoCostilla = new Button("Tacos de costilla");
        btnTacoCostilla.setGraphic(imv6);
        btnTacoCostilla.getStyleClass().setAll("btn", "btn-default");
        btnTacoCostilla.setOnAction(event -> {
            AsignarTacoCostilla();
        });

        Image imgTacosChorizo = new Image("C:\\Users\\Hp\\IdeaProjects\\Topicos\\src\\main\\resources\\ImagenesTaqueria\\8.jpg");
        ImageView imv7 = new ImageView(imgTacosChorizo);
        imv7.setFitWidth(200);
        imv7.setFitHeight(200);

        btnTacoChorizo = new Button("Tacos de chorizo");
        btnTacoChorizo.setGraphic(imv7);
        btnTacoChorizo.getStyleClass().setAll("btn", "btn-default");
        btnTacoChorizo.setOnAction(event -> {
            AsignarTacoChorizo();
        });

        vBox4 = new VBox();
        vBox4.setSpacing(35);
        vBox4.getChildren().setAll(btnTacoCostilla, btnTacoChorizo);

        //Postres


        Image imgPay = new Image("C:\\Users\\Hp\\IdeaProjects\\Topicos\\src\\main\\resources\\ImagenesTaqueria\\9.jpg");
        ImageView imv8 = new ImageView(imgPay);
        imv8.setFitWidth(200);
        imv8.setFitHeight(200);

        btnPay = new Button("Pay");
        btnPay.setGraphic(imv8);
        btnPay.getStyleClass().setAll("btn", "btn-default");
        btnPay.setOnAction(event -> {
            AsignarPay();
        });

        Image imgHelado = new Image("C:\\Users\\Hp\\IdeaProjects\\Topicos\\src\\main\\resources\\ImagenesTaqueria\\10.jpg");
        ImageView imv9 = new ImageView(imgHelado);
        imv9.setFitWidth(200);
        imv9.setFitHeight(200);

        btnHelado = new Button("Helado");
        btnHelado.setGraphic(imv9);
        btnHelado.getStyleClass().setAll("btn", "btn-default");
        btnHelado.setOnAction(event -> {
            AsignarHelado();
        });

        vBoxPostre1 = new VBox();
        vBoxPostre1.setSpacing(35);
        vBoxPostre1.getChildren().setAll(btnPay, btnHelado);

        Image imgGelatina = new Image("C:\\Users\\Hp\\IdeaProjects\\Topicos\\src\\main\\resources\\ImagenesTaqueria\\11.jpg");
        ImageView imv10 = new ImageView(imgGelatina);
        imv10.setFitWidth(200);
        imv10.setFitHeight(200);

        btnGelatina = new Button("Gelatina");
        btnGelatina.setGraphic(imv10);
        btnGelatina.getStyleClass().setAll("btn", "btn-default");
        btnGelatina.setOnAction(event -> {
            AsignarGelatina();
        });

        Image imgPastel = new Image("C:\\Users\\Hp\\IdeaProjects\\Topicos\\src\\main\\resources\\ImagenesTaqueria\\12.jpg");
        ImageView imv11 = new ImageView(imgPastel);
        imv11.setFitWidth(200);
        imv11.setFitHeight(200);

        btnPastel = new Button("Pastel");
        btnPastel.setGraphic(imv11);
        btnPastel.getStyleClass().setAll("btn", "btn-default");
        btnPastel.setOnAction(event -> {
            AsignarPastel();
        });

        vBoxPostre2 = new VBox();
        vBoxPostre2.setSpacing(35);
        vBoxPostre2.getChildren().setAll(btnGelatina, btnPastel);

        //Especialidades

        Image imgAlambre = new Image("C:\\Users\\Hp\\IdeaProjects\\Topicos\\src\\main\\resources\\ImagenesTaqueria\\13.jpg");
        ImageView imv12 = new ImageView(imgAlambre);
        imv12.setFitWidth(200);
        imv12.setFitHeight(200);

        btnAlambre = new Button("Alambre");
        btnAlambre.setGraphic(imv12);
        btnAlambre.getStyleClass().setAll("btn", "btn-default");
        btnAlambre.setOnAction(event -> {
            AsignarAlambre();
        });

        Image imgAsada = new Image("C:\\Users\\Hp\\IdeaProjects\\Topicos\\src\\main\\resources\\ImagenesTaqueria\\14.jpg");
        ImageView imv13 = new ImageView(imgAsada);
        imv13.setFitWidth(200);
        imv13.setFitHeight(200);

        btnCarneAsada = new Button("Carne asada");
        btnCarneAsada.setGraphic(imv13);
        btnCarneAsada.getStyleClass().setAll("btn", "btn-default");
        btnCarneAsada.setOnAction(event -> {
            AsignarAsada();
        });

        vBoxEspe1 = new VBox();
        vBoxEspe1.setSpacing(35);
        vBoxEspe1.getChildren().setAll(btnAlambre, btnCarneAsada);

        Image imgPapa = new Image("C:\\Users\\Hp\\IdeaProjects\\Topicos\\src\\main\\resources\\ImagenesTaqueria\\15.jpg");
        ImageView imv14 = new ImageView(imgPapa);
        imv14.setFitWidth(200);
        imv14.setFitHeight(200);

        btnPapa = new Button("Papa rellena");
        btnPapa.setGraphic(imv14);
        btnPapa.getStyleClass().setAll("btn", "btn-default");
        btnPapa.setOnAction(event -> {
            AsignarPapa();
        });

        Image imgVolcan = new Image("C:\\Users\\Hp\\IdeaProjects\\Topicos\\src\\main\\resources\\ImagenesTaqueria\\16.jpg");
        ImageView imv15 = new ImageView(imgVolcan);
        imv15.setFitWidth(200);
        imv15.setFitHeight(200);

        btnVolcanes = new Button("Volcanes");
        btnVolcanes.setGraphic(imv15);
        btnVolcanes.getStyleClass().setAll("btn", "btn-default");
        btnVolcanes.setOnAction(event -> {
            AsignarVolcanes();
        });

        vBoxEspe2 = new VBox();
        vBoxEspe2.setSpacing(35);
        vBoxEspe2.getChildren().setAll(btnPapa, btnVolcanes);

        //bebidas

        Image imgCoca = new Image("C:\\Users\\Hp\\IdeaProjects\\Topicos\\src\\main\\resources\\ImagenesTaqueria\\17.jpg");
        ImageView imv16 = new ImageView(imgCoca);
        imv16.setFitWidth(200);
        imv16.setFitHeight(200);

        btnCoca = new Button("Coca");
        btnCoca.setGraphic(imv16);
        btnCoca.getStyleClass().setAll("btn", "btn-default");
        btnCoca.setOnAction(event -> {
            AsignarCoca();
        });

        Image imgAguaNatural = new Image("C:\\Users\\Hp\\IdeaProjects\\Topicos\\src\\main\\resources\\ImagenesTaqueria\\18.jpg");
        ImageView imv17 = new ImageView(imgAguaNatural);
        imv17.setFitWidth(200);
        imv17.setFitHeight(200);

        btnAguaNatural = new Button("Agua");
        btnAguaNatural.setGraphic(imv17);
        btnAguaNatural.getStyleClass().setAll("btn", "btn-default");
        btnAguaNatural.setOnAction(event -> {
            AsignarAgua();
        });

        vBoxBebi1 = new VBox();
        vBoxBebi1.setSpacing(35);
        vBoxBebi1.getChildren().setAll(btnCoca, btnAguaNatural);

        Image imgAguaLimon = new Image("C:\\Users\\Hp\\IdeaProjects\\Topicos\\src\\main\\resources\\ImagenesTaqueria\\19.jpg");
        ImageView imv18 = new ImageView(imgAguaLimon);
        imv18.setFitWidth(200);
        imv18.setFitHeight(200);

        btnAguaLimon = new Button("Agua Limon");
        btnAguaLimon.setGraphic(imv18);
        btnAguaLimon.getStyleClass().setAll("btn", "btn-default");
        btnAguaLimon.setOnAction(event -> {
            AsignarLimon();
        });

        Image imgPepsi = new Image("C:\\Users\\Hp\\IdeaProjects\\Topicos\\src\\main\\resources\\ImagenesTaqueria\\20.jpg");
        ImageView imv19 = new ImageView(imgPepsi);
        imv19.setFitWidth(200);
        imv19.setFitHeight(200);

        btnPepsi = new Button("Pepsi");
        btnPepsi.setGraphic(imv19);
        btnPepsi.getStyleClass().setAll("btn", "btn-default");
        btnPepsi.setOnAction(event -> {
            AsignarPepsi();
        });

        vBoxBebi2 = new VBox();
        vBoxBebi2.setSpacing(35);
        vBoxBebi2.getChildren().setAll(btnAguaLimon, btnPepsi);


    }

    private void AbrirTacos() {
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

    private void AbrirPostres() {
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

    private void AbrirEspecialidad() {
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

    private void AbrirBebidas() {
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
        content.setCenter(vBoxTablaPedido);
        panel.setBody(content);

        Scene nuevaEscena = new Scene(panel, 400, 400);
        nuevaEscena.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        nuevaVentana.setTitle("Carrito de Compras");
        nuevaVentana.setScene(nuevaEscena);
        nuevaVentana.setOnCloseRequest(event -> nuevaVentana.close());
        nuevaVentana.show();
    }

    private void Sugerencia() {
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

    private void Sesion() {
        Stage nuevaVentana = new Stage();
        Panel panel = new Panel("Inicio de Sesion");
        panel.getStyleClass().setAll("panel-primary");
        BorderPane content = new BorderPane();
        content.setPadding(new Insets(20));
        content.setCenter(hBoxUsuarios);
        panel.setBody(content);

        Scene nuevaEscena = new Scene(panel, 400, 400);
        nuevaEscena.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        nuevaVentana.setTitle("Escoga su usuario");
        nuevaVentana.setScene(nuevaEscena);
        nuevaVentana.setOnCloseRequest(event -> nuevaVentana.close());
        nuevaVentana.show();
    }

    private int idCategoria = -1;
    private int idPedido = -1;
    private int idPlatillo = -1;
    private int Total = 0;

    private void AbrirCategoria() {
        idCategoria = taqueriasDAO.insertarCategoria("Tacos");
    }

    private void AbrirCategoriaPostres() {
        idCategoria = taqueriasDAO.insertarCategoria("Postres");
    }

    private void AbrirCategoriaEspecialidad() {
        idCategoria = taqueriasDAO.insertarCategoria("Volcanes");
    }

    private void AbrirCategoriaBebidas() {
        idCategoria = taqueriasDAO.insertarCategoria("Bebidas");
    }

    private void AsignarTaco() {
        if (idCategoria != -1) {
            // Si se obtiene un id de categoría válido, insertar un platillo asociado a esa categoría
            idPlatillo = taqueriasDAO.insertarPlatillo("Tacos de Pastor", 15, "ImagenesTaqueria/5.jpg", idCategoria);
        }
    }

    private void AsignarTacoCabeza() {
        if (idCategoria != -1) {
            idPlatillo = taqueriasDAO.insertarPlatillo("Tacos de Cabeza", 15, "ImagenesTaqueria/6.jpg", idCategoria);
        }
    }

    private void AsignarTacoCostilla() {
        if (idCategoria != -1) {
            idPlatillo = taqueriasDAO.insertarPlatillo("Tacos de Costilla", 15, "ImagenesTaqueria/7.jpg", idCategoria);
        }
    }

    private void AsignarTacoChorizo() {
        if (idCategoria != -1) {
            idPlatillo = taqueriasDAO.insertarPlatillo("Tacos de Chorizo", 15, "ImagenesTaqueria/8.jpg", idCategoria);
        }
    }

    private void AsignarPay() {
        if (idCategoria != -1) {
            idPlatillo = taqueriasDAO.insertarPlatillo("Rebanada de Pay", 40, "ImagenesTaqueria/9.jpg", idCategoria);
        }
    }

    private void AsignarHelado() {
        if (idCategoria != -1) {
            idPlatillo = taqueriasDAO.insertarPlatillo("Helado Individual", 50, "ImagenesTaqueria/10.jpg", idCategoria);
        }
    }

    private void AsignarGelatina() {
        if (idCategoria != -1) {
            idPlatillo = taqueriasDAO.insertarPlatillo("Gelatina Individual", 25, "ImagenesTaqueria/11.jpg", idCategoria);
        }
    }

    private void AsignarPastel() {
        if (idCategoria != -1) {
            idPlatillo = taqueriasDAO.insertarPlatillo("Rebanada de Pastel", 50, "ImagenesTaqueria/12.jpg", idCategoria);
        }
    }

    private void AsignarAlambre() {
        if (idCategoria != -1) {
            idPlatillo = taqueriasDAO.insertarPlatillo("Orden de Alambre", 90, "ImagenesTaqueria/13.jpg", idCategoria);
        }
    }

    private void AsignarAsada() {
        if (idCategoria != -1) {
            idPlatillo = taqueriasDAO.insertarPlatillo("Orden de Carne Asada", 120, "ImagenesTaqueria/14.jpg", idCategoria);
        }
    }

    private void AsignarPapa() {
        if (idCategoria != -1) {
            idPlatillo = taqueriasDAO.insertarPlatillo("Papa rellena de pastor", 80, "ImagenesTaqueria/15.jpg", idCategoria);
        }
    }

    private void AsignarVolcanes() {
        if (idCategoria != -1) {
            idPlatillo = taqueriasDAO.insertarPlatillo("Papa rellena de pastor", 80, "ImagenesTaqueria/16.jpg", idCategoria);
        }
    }

    private void AsignarCoca() {
        if (idCategoria != -1) {
            idPlatillo = taqueriasDAO.insertarPlatillo("Coca-Cola 600ml", 30, "ImagenesTaqueria/17.jpg", idCategoria);
        }
    }

    private void AsignarAgua() {
        if (idCategoria != -1) {
            idPlatillo = taqueriasDAO.insertarPlatillo("Agua Natural 600ml", 20, "ImagenesTaqueria/18.jpg", idCategoria);
        }
    }

    private void AsignarLimon() {
        if (idCategoria != -1) {
            idPlatillo = taqueriasDAO.insertarPlatillo("Agua de Limon 600ml", 25, "ImagenesTaqueria/19.jpg", idCategoria);
        }
    }

    private void AsignarPepsi() {
        if (idCategoria != -1) {
            idPlatillo = taqueriasDAO.insertarPlatillo("Pepsi 600ml", 30, "ImagenesTaqueria/20.jpg", idCategoria);
        }
    }

    private void AbrirCR7() {
        idPedido = taqueriasDAO.insertarUsuario("Cristiano Ronaldo");
    }

    private void BOTONCR7() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText("Bienvenido Cristiano Ronaldo");

        alert.showAndWait();

        btnCR7.setDisable(true);
        btnMOY.setDisable(true);
    }

    private void AbrirHasbu() {
        idPedido = taqueriasDAO.insertarUsuario("Hasbullah Magomedov");
    }

    private void BOTONHASBU() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText("Bienvenido Hasbullah Magomedov");

        alert.showAndWait();

        btnCR7.setDisable(true);
        btnMOY.setDisable(true);
    }

    public void AsignarTicket() {
        if (idPlatillo != -1 && idPedido != -1) {
            int valorContador = taqueriasDAO.obtenerContadorInserciones(); // Obtén el valor del contador
            int sumaTodo = taqueriasDAO.obtenerSuma();
            taqueriasDAO.insertarCompra(idPlatillo, idPedido, 0, sumaTodo, valorContador); // Pasa el valor del contador
        }
    }

    private void CrearTabla(){
        TableColumn<TaqueriasDAO,Integer> tbcldCat = new TableColumn<>("ID DE PLATILLO");
        tbcldCat.setCellValueFactory(new PropertyValueFactory<>("idPlatillo"));

        TableColumn<TaqueriasDAO,String> tbcldCat2 = new TableColumn<>("Nombre");
        tbcldCat2.setCellValueFactory(new PropertyValueFactory<>("nombrePlatillo"));
        tbcldCat2.setEditable(true);
        tbcldCat2.setPrefWidth(200);

        TableColumn<TaqueriasDAO,String> tbcldCat3 = new TableColumn<>("Imagen");
        tbcldCat3.setCellValueFactory(new PropertyValueFactory<>("imagenPlatillo"));
        tbcldCat3.setPrefWidth(300);

        TableColumn<TaqueriasDAO,String> tbcldCat4 = new TableColumn<>("Precio Platillo");
        tbcldCat4.setCellValueFactory(new PropertyValueFactory<>("precio"));
        tbcldCat4.setPrefWidth(100);

        TableColumn<TaqueriasDAO,String> borrar = new TableColumn<>("Borrar");
        borrar.setCellFactory(
                new Callback<TableColumn<TaqueriasDAO, String>, TableCell<TaqueriasDAO, String>>() {
                    @Override
                    public TableCell<TaqueriasDAO, String> call(TableColumn<TaqueriasDAO, String> taqueriasDAOStringTableColumn) {
                        return new ButtonCell2(2);
                    }
                }
        );


        tbvTaquerias.getColumns().addAll(tbcldCat,tbcldCat2,tbcldCat3,tbcldCat4,borrar);
        tbvTaquerias.setItems(taqueriasDAO.LISTARPRODUCTOS());
    }

    private void Crear(){
        TableColumn<TaqueriasDAO,Integer> Categoria = new TableColumn<>("ID Categoria");
        Categoria.setCellValueFactory(new PropertyValueFactory<>("idCategoria"));
        TableColumn<TaqueriasDAO, String> nom = new TableColumn<>("Categoria");
        nom.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        TableColumn<TaqueriasDAO,String> borrar = new TableColumn<>("Borrar");
        borrar.setCellFactory(
                new Callback<TableColumn<TaqueriasDAO, String>, TableCell<TaqueriasDAO, String>>() {
                    @Override
                    public TableCell<TaqueriasDAO, String> call(TableColumn<TaqueriasDAO, String> taqueriasDAOStringTableColumn) {
                        return new ButtonCell3(2);
                    }
                }
        );

        tbvTaque.getColumns().addAll(Categoria,nom,borrar);
        tbvTaque.setItems(taqueriasDAO.LISTARCATEGORIAS());
    }

    private void actualizarTabla() {
        tbvTaquerias.getItems().setAll(taqueriasDAO.LISTARPRODUCTOS());
    }


    private void AbrirCategoriaCerveza() {
        idCategoria = taqueriasDAO.insertarCategoria("Bebidas");
    }
    private void AbrirCategoriaBurrito() {
        idCategoria = taqueriasDAO.insertarCategoria("Especialidades");
    }

    private void ActualizarTablaCategoria(){
        tbvTaque.getItems().setAll(taqueriasDAO.LISTARCATEGORIAS());
    }

    private void LimpiarTabla(){
        ObservableList<TaqueriasDAO> Lista = tbvTaquerias.getItems();

        int sumaTodo = 0;
        int valorContador = 0;
        for (TaqueriasDAO item : Lista) {
            sumaTodo += item.getPrecio();
            valorContador++;
        }

        tbvTaquerias.getItems().clear();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Compra Exitosa");
        alert.setHeaderText("Detalle De Su Compra: ");
        alert.setContentText("Gracias por su compra. Total a Pagar es: " + sumaTodo + ", Cantidad de Productos Comprados: " + valorContador);
        alert.getButtonTypes().setAll(ButtonType.OK);
        alert.showAndWait();

        taqueriasDAO.eliminarTodo();
        Platform.exit();
    }

    private void LimpiarSuge(){
        tbvTaque.getItems().clear();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Recomendacion Exitosa");
        alert.setHeaderText(null);
        alert.setContentText("Gracias Por Su Recomendacion");
        alert.getButtonTypes().setAll(ButtonType.OK);
        alert.showAndWait();
    }

}
