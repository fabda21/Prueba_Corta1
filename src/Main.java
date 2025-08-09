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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(String fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void marcarComoCompletada() {
        this.completada = true;
    }

    public void editarTarea(String descripcion, String fechaLimite, String prioridad, String categoria) {
        setDescripcion(descripcion);
        setFechaLimite(fechaLimite);
        setPrioridad(prioridad);
        setCategoria(categoria);
    }

    public void mostrarInfo(int indice) {
        System.out.println("Tarea #" + (indice + 1));
        System.out.println("Descripción: " + descripcion);
        System.out.println("Fecha límite: " + fechaLimite);
        System.out.println("Prioridad: " + prioridad);
        System.out.println("Categoría: " + categoria);
        System.out.println("Estado: " + (completada ? "Completada" : "Pendiente"));
        System.out.println("----------------------------------");
    }

    class GestorTareas {
        private ArrayList<Tarea> listaTareas;

        public GestorTareas() {
            listaTareas = new ArrayList<>();
        }

        public void agregarTarea(Tarea t) {
            listaTareas.add(t);
            System.out.println("Tarea agregada exitosamente.\n");
        }

        public void listarTareas() {
            if (listaTareas.isEmpty()) {
                System.out.println("No hay tareas registradas.\n");
                return;
            }
            System.out.println("Listado de todas las tareas:");
            for (int i = 0; i < listaTareas.size(); i++) {
                listaTareas.get(i).mostrarInfo(i);
            }
        }
        public void listarTareasPorEstado(boolean completadas) {
            boolean alguna = false;
            String estadoStr = completadas ? "Completadas" : "Pendientes";
            System.out.println("Listado de tareas " + estadoStr + ":");
            for (int i = 0; i < listaTareas.size(); i++) {
                if (listaTareas.get(i).isCompletada() == completadas) {
                    listaTareas.get(i).mostrarInfo(i);
                    alguna = true;
                }
            }
            if (!alguna) {
                System.out.println("No hay tareas " + estadoStr.toLowerCase() + ".\n");
            }
        }
    }
}