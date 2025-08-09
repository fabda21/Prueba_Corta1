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

    static class GestorTareas {
        private final ArrayList<Tarea> listaTareas;

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

        public void marcarTareaComoCompletada(int indice) {
            if (indice < 0 || indice >= listaTareas.size()) {
                System.out.println("Índice inválido.\n");
                return;
            }
            listaTareas.get(indice).marcarComoCompletada();
            System.out.println("Tarea marcada como completada.\n");
        }

        public void editarTarea(int indice, String descripcion, String fechaLimite, String prioridad, String categoria) {
            if (indice < 0 || indice >= listaTareas.size()) {
                System.out.println("Índice inválido.\n");
                return;
            }
            listaTareas.get(indice).editarTarea(descripcion, fechaLimite, prioridad, categoria);
            System.out.println("Tarea editada correctamente.\n");
        }

        public void eliminarTarea(int indice) {
            if (indice < 0 || indice >= listaTareas.size()) {
                System.out.println("Índice inválido.\n");
                return;
            }
            listaTareas.remove(indice);
            System.out.println("Tarea eliminada.\n");
        }

        public int cantidadTareas() {
            return listaTareas.size();
        }
    }

    public static class ProgramaTareas {
        private static final Scanner sc = new Scanner(System.in);
        private static final GestorTareas gestor = new GestorTareas();
        public static void main(String[] args) {
            int opcion;
            do {
                mostrarMenu();
                opcion = leerEntero("Seleccione una opción: ");

                switch (opcion) {
                    case 1 -> agregarTarea();
                    case 2 -> gestor.listarTareas();
                    case 3 -> marcarComoCompletada();
                    case 4 -> editarTarea();
                    case 5 -> listarPorEstado();
                    case 6 -> eliminarTarea();
                    case 7 -> System.out.println("Saliendo del programa. ¡Hasta luego!");
                    default -> System.out.println("Opción inválida, intente de nuevo.");
                }
            } while (opcion != 7);
        }

        private static void mostrarMenu() {
            System.out.println("\n--- Menú Gestor de Tareas ---");
            System.out.println("1. Agregar tarea");
            System.out.println("2. Listar todas las tareas");
            System.out.println("3. Marcar tarea como completada");
            System.out.println("4. Editar tarea");
            System.out.println("5. Listar tareas por estado (pendientes/completadas)");
            System.out.println("6. Eliminar tarea");
            System.out.println("7. Salir");
        }

        private static int leerEntero(String mensaje) {
            int num;
            while (true) {
                System.out.print(mensaje);
                try {
                    num = Integer.parseInt(sc.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Por favor ingrese un número válido.");
                }
            }
            return num;
        }

        private static String leerTexto(String mensaje) {
            System.out.print(mensaje);
            return sc.nextLine();
        }

        private static String leerPrioridad() {
            while (true) {
                System.out.print("Ingrese prioridad (Alta, Media, Baja): ");
                String p = sc.nextLine().trim();
                if (p.equalsIgnoreCase("Alta") || p.equalsIgnoreCase("Media") || p.equalsIgnoreCase("Baja")) {
                    return capitalize(p);
                }
                System.out.println("Prioridad inválida. Intente de nuevo.");
            }
        }

        private static String leerCategoria() {
            while (true) {
                System.out.print("Ingrese categoría (Trabajo, Estudio, Personal, Otro): ");
                String c = sc.nextLine().trim();
                if (c.equalsIgnoreCase("Trabajo") || c.equalsIgnoreCase("Estudio") ||
                        c.equalsIgnoreCase("Personal") || c.equalsIgnoreCase("Otro")) {
                    return capitalize(c);
                }
                System.out.println("Categoría inválida. Intente de nuevo.");
            }
        }

        private static String capitalize(String texto) {
            if (texto == null || texto.isEmpty()) return texto;
            return texto.substring(0, 1).toUpperCase() + texto.substring(1).toLowerCase();
        }

        private static void agregarTarea() {
            System.out.println("\n--- Agregar Nueva Tarea ---");
            String desc = leerTexto("Descripción: ");
            String fecha = leerTexto("Fecha límite: ");
            String prioridad = leerPrioridad();
            String categoria = leerCategoria();

            Tarea tarea = new Tarea(desc, fecha, prioridad, categoria);
            gestor.agregarTarea(tarea);
        }

        private static void marcarComoCompletada() {
            System.out.println("\n--- Marcar Tarea como Completada ---");
            if (gestor.cantidadTareas() == 0) {
                System.out.println("No hay tareas para marcar.\n");
                return;
            }
            gestor.listarTareas();
            int num = leerEntero("Ingrese el número de la tarea a marcar como completada: ") - 1;
            gestor.marcarTareaComoCompletada(num);
        }

        private static void editarTarea() {
            System.out.println("\n--- Editar Tarea ---");
            if (gestor.cantidadTareas() == 0) {
                System.out.println("No hay tareas para editar.\n");
                return;
            }
            gestor.listarTareas();
            int num = leerEntero("Ingrese el número de la tarea a editar: ") - 1;

            System.out.println("Ingrese los nuevos datos:");
            String desc = leerTexto("Descripción nueva: ");
            String fecha = leerTexto("Fecha límite nueva: ");
            String prioridad = leerPrioridad();
            String categoria = leerCategoria();

            gestor.editarTarea(num, desc, fecha, prioridad, categoria);
        }

        private static void listarPorEstado() {
            System.out.println("\n--- Listar Tareas por Estado ---");
            System.out.println("1. Pendientes");
            System.out.println("2. Completadas");
            int opcion = leerEntero("Seleccione opción: ");
            if (opcion == 1) {
                gestor.listarTareasPorEstado(false);
            } else if (opcion == 2) {
                gestor.listarTareasPorEstado(true);
            } else {
                System.out.println("Opción inválida.");
            }
        }

        private static void eliminarTarea() {
            System.out.println("\n--- Eliminar Tarea ---");
            if (gestor.cantidadTareas() == 0) {
                System.out.println("No hay tareas para eliminar.\n");
                return;
            }
            gestor.listarTareas();
            int num = leerEntero("Ingrese el número de la tarea a eliminar: ") - 1;
            gestor.eliminarTarea(num);
        }
    }
    }

