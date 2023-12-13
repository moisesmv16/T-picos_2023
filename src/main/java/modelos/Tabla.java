package modelos;

public class Tabla {
    private int NumeroArchivo;
    private String NombreArchivo;
    private int NumeroHojas;
    private String HoraAcceso;

    public int getNumeroArchivo() {
        return NumeroArchivo;
    }

    public void setNumeroArchivo(int numeroArchivo) {
        NumeroArchivo = numeroArchivo;
    }

    public String getNombreArchivo() {
        return NombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        NombreArchivo = nombreArchivo;
    }

    public int getNumeroHojas() {
        return NumeroHojas;
    }

    public void setNumeroHojas(int numeroHojas) {
        NumeroHojas = numeroHojas;
    }

    public String getHoraAcceso() {
        return HoraAcceso;
    }

    public void setHoraAcceso(String horaAcceso) {
        HoraAcceso = horaAcceso;
    }

}
