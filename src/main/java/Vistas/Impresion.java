package Vistas;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelos.Tabla;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Impresion extends Stage {
    private ProgressBar pgbHojas;
    private Scene escena;
    private VBox vBox;
    private  TableView<Tabla> tbvTabla;
    private Tabla tabla;
    private Button btnAgregar, btnA,btnE,btnActu;
    private int contador = 1;
    private boolean impresionEnProceso = false;
    private boolean impresionFinalizada = false;
    public Impresion(){
        CrearUI();
        escena=new Scene(vBox);
        this.setTitle("Simulador de Impresion");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        tabla=new Tabla();
        tbvTabla = new TableView<Tabla>();
        CrearTable();
        btnAgregar = new Button("Agregar Tarea");
        btnAgregar.setOnAction(event -> {
            insertarDatos();
        });

        btnA = new Button("Apagado");
        btnA.setOnAction(event -> {
            Apagado();
        });

        btnE = new Button("Encendido");
        btnE.setOnAction(event -> {
            //imprimirLista();
            btnActu.setDisable(false);
            btnAgregar.setDisable(false);
            IniciarImpresion();
        });

        btnActu = new Button("Actualizar");
        btnActu.setOnAction(event -> {limpiarTabla();});

        pgbHojas = new ProgressBar(0);

        vBox = new VBox(tbvTabla,btnAgregar,btnA,btnE,btnActu,pgbHojas);

    }

    private void CrearTable(){
        TableColumn<Tabla,Integer> tbcNO = new TableColumn<>("Numero de Archivo");
        tbcNO.setCellValueFactory(new PropertyValueFactory<>("numeroArchivo"));
        tbcNO.setPrefWidth(200);

        TableColumn<Tabla,String> tbcNombre = new TableColumn<>("Nombre Archivo");
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombreArchivo"));
        tbcNombre.setPrefWidth(200);

        TableColumn<Tabla,Integer> tbcHojas = new TableColumn<>("Numero de Hojas");
        tbcHojas.setCellValueFactory(new PropertyValueFactory<>("numeroHojas"));
        tbcHojas.setPrefWidth(200);

        TableColumn<Tabla, String> tbcHora = new TableColumn<>("Hora de Acceso");
        tbcHora.setCellValueFactory(new PropertyValueFactory<>("horaAcceso"));
        tbcHora.setPrefWidth(200);

        tbvTabla.getColumns().addAll(tbcNO,tbcNombre,tbcHojas,tbcHora);
    }
    public void insertarDatos() {
        int numeroArchivo = NumeroArchivo();
        int numeroHojas = NumeroHojas();
        String nombreArchivo =NombreArchivo(numeroArchivo);
        String horaAcceso = HoraAcceso();

        Tabla nuevaFila = new Tabla();
        nuevaFila.setNumeroArchivo(numeroArchivo);
        nuevaFila.setNombreArchivo(nombreArchivo);
        nuevaFila.setNumeroHojas(numeroHojas);
        nuevaFila.setHoraAcceso(horaAcceso);
        tbvTabla.getItems().add(nuevaFila);
    }

    private int NumeroArchivo(){
        int resultado = contador;
        contador++;
        return resultado;
    }

    private int NumeroHojas(){
        Random random = new Random();
        return random.nextInt(16)+5;
    }

    private String HoraAcceso(){
        SimpleDateFormat hora = new SimpleDateFormat("HH:mm");
        Date fecha = new Date();
        return hora.format(fecha);
    }
    private String NombreArchivo(int numeroArchivo){
        return numeroArchivo  + ".pdf";
    }

    private void imprimirLista(){
        System.out.println("PRUEBAAAAAAAAAAAAAAAAAA");

        for(Tabla fila : tbvTabla.getItems()){
            int numerohojas = fila.getNumeroHojas();
            System.out.println(numerohojas);
        }
    }

    private void IniciarImpresion(){
        btnAgregar.setDisable(true);
        btnActu.setDisable(true);
        if (impresionEnProceso) {
            // Muestra una alerta si la impresión ya está en progreso
            mostrarAlerta("La impresión ya está en progreso");
            return;
        }

        if (tbvTabla.getItems().isEmpty()) {

            mostrarAlerta("La lista está vacía. No hay archivos para imprimir, agrega unos por favor");
            return;
        }
        btnE.setDisable(true);


        ImpresionTarea[] tareas = tbvTabla.getItems().stream()
                .map(Tabla::getNumeroHojas)
                .map(ImpresionTarea::new)
                .toArray(ImpresionTarea[]::new);

        impresionEnProceso = true;

        Thread hiloImpresion = new Thread(() -> {
            for (ImpresionTarea tarea : tareas) {

                Platform.runLater(() -> mostrarAlerta1(tarea.getHojas()));

                tarea.run();

                Platform.runLater(() -> pgbHojas.setProgress(tarea.getProgreso()));
            }

            Platform.runLater(() -> {
                btnE.setDisable(false);
                impresionEnProceso = false;
                impresionFinalizada = true;

                if (tbvTabla.getItems().isEmpty()) {
                    mostrarAlerta("Impresión completada.Si desea imprimir mas pulse el boton actualizar.");
                    Platform.runLater(() -> tbvTabla.getItems().clear());
                }
            });
        });

        hiloImpresion.start();
        new Thread(() -> {
            try {
                hiloImpresion.join();
                if (impresionFinalizada) {
                    Platform.runLater(() -> mostrarAlerta("Impresión completada.Si desea imprimir mas pulse el boton actualizar."));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void mostrarAlerta1(int numeroHojas) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText("Imprimiendo archivo con un numero de hojas de:  " + numeroHojas + " hojas");
        alert.showAndWait();
    }
    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    private void limpiarTabla() {
        tbvTabla.getItems().clear();
        pgbHojas.setProgress(0);
    }

    private void Apagado(){
        btnAgregar.setDisable(true);
        btnActu.setDisable(true);
    }


    private class ImpresionTarea implements Runnable {
        private int hojas;
        private double progreso;

        public ImpresionTarea(int hojas) {
            this.hojas = hojas;
        }

        @Override
        public void run() {
            for (int i = 0; i < hojas; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                progreso = (i + 1.0) / hojas;
            }
        }
        public double getProgreso() {
            return progreso;
        }
        public int getHojas() {
            return hojas;
        }
    }

}
