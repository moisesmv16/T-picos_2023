package com.example.topicos;

import Components.Hilo;
import Vistas.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import modelos.Conexion;

import java.io.IOException;
import java.util.Optional;

public class HelloApplication extends Application {
    private Scene escena;
    private BorderPane borderPane;
    private MenuBar menuBar;
    private Menu menuParcial1,menuParcial2, menuSalir;
    private MenuItem mitCalculadora;
    private MenuItem miLoteria,mitSalir,mitRestaurante,mitBaileys,mitPista,mitImpresion;

    private void CrearUI(){
        mitCalculadora = new MenuItem("Calculadora");//crea mit calculadora con la etiqueta calculadora
        mitCalculadora.setOnAction((event)-> new Calculadora());//da la accion de boton y crea la ventana calculadora

        miLoteria=new MenuItem("Loteria");//crea mit loteria con la etiqueta calculadora
        miLoteria.setOnAction((event) -> new Loteria());//crear la ventana de la loteria


        menuParcial1 = new Menu("Parcial 1 ");//crea un menu parcial 1
        menuParcial1.getItems().addAll(mitCalculadora,miLoteria);//agrega a la al submenu estos


        mitRestaurante = new MenuItem("Restaurante");
        mitRestaurante.setOnAction((event) -> new Restaurante());

        mitBaileys = new MenuItem("Baileys");
        mitBaileys.setOnAction((event) -> new Baileys());

        mitPista=new MenuItem("Pista");
        mitPista.setOnAction(event -> new PistaAtletismo());

        mitImpresion=new MenuItem("Simulador de Impresion");
        mitImpresion.setOnAction(event -> new Impresion());

        menuParcial2 = new Menu("Parcial 2");//crea un menu parcial 2
        menuParcial2.getItems().addAll(mitRestaurante,mitBaileys,mitPista,mitImpresion);


        menuSalir=new Menu("Mas opciones");//crea un menu mas opciones
        mitSalir= new MenuItem("Salir");//crea un submenu para mas opciones
        mitSalir.setOnAction((event)->Salir());//genera un evento para el salir
        menuSalir.getItems().add(mitSalir);//agrega el mit salir al menu salir

        menuBar= new MenuBar(menuParcial1,menuParcial2,menuSalir);//lo carga todo en el menu prinipal
    }

    private void Salir() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);//clase alerta la cual va ser de confirmacion
        alert.setTitle("Mensaje del sistema");//titulo de la vemtana de confirmacion
        alert.setHeaderText("¿Estas seguro de salir?");//mensaje que lleva dentro la ventana
        Optional<ButtonType> option= alert.showAndWait();//ayuda almacenar la opcon dad por l usuario de la lerta
        if (option.get()==ButtonType.OK){//si le da ok
            System.exit(0);//se acaba cierra ventana
        }
    }


    @Override
    public void start(Stage stage) throws IOException {
       /* new Hilo("Moises").start();//OBJETOS ANONIMOS
        new Hilo("Cristiano").start();
        new Hilo("Messi").start();
        new Hilo("Jude").start();
        new Hilo("Pele").start();
        new Hilo("Bailey").start();*/


        connectToDB();
        CrearUI();
        borderPane = new BorderPane();//instancia el borderpane
        borderPane.setTop(menuBar);//pone el menu bar hasta arriba
        escena = new Scene(borderPane, 200, 300);//crea la escena con el poder pane un tamaño de 200 x 300
        escena.getStylesheets().add(getClass().getResource("/Estilos/Estilos.css").toString()); //crea la css buscando en la carpeta resources
        stage.setScene(escena);//carga la escena
        stage.setMaximized(true);
        stage.show();//la muestra
    }

    public void connectToDB(){
        Conexion.createConnection();
        System.out.println("Conexion establecida");
    }


    public static void main(String[] args) {
        launch();
    }
}