import java.util.ArrayList;
import java.util.Scanner;

class Tarea {
    private String descripcion;
    private String fechaLimite;
    private String prioridad;  // Alta, Media, Baja
    private String categoria;  // Trabajo, Estudio, Personal, Otro
    private boolean completada;

    public Tarea(String descripcion, String fechaLimite, String prioridad, String categoria) {
        this.descripcion = descripcion;
        this.fechaLimite = fechaLimite;
        this.prioridad = prioridad;
        this.categoria = categoria;
        this.completada = false;
    }
}