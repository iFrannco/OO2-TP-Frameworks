package cabeza.framework;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class Screen {
    private static final String CLASS_NAME_PROPERTY = "acciones";
    private static final String CONFIG_FILE_NAME_DEFAULT = "/config.properties";
    private List<Accion> acciones = new ArrayList<>();

    public Screen() {
        this(CONFIG_FILE_NAME_DEFAULT);
    }

    public Screen(String path) {
        Properties prop = new Properties();
        try (InputStream configFile = getClass().getResourceAsStream(path)) {
            prop.load(configFile);
            String clasesStr = prop.getProperty(CLASS_NAME_PROPERTY);
            if (clasesStr == null || clasesStr.isBlank()) {
                throw new RuntimeException("El archivo esta vacio. Formato requerido: acciones=...");
            }

            String[] nombresDeClases = clasesStr.split(",");

            for (String nombreClase : nombresDeClases) {
                Class<?> clazz = Class.forName(nombreClase.trim());
                Accion instancia = (Accion) clazz.getDeclaredConstructor().newInstance();
                acciones.add(instancia);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al instanciar las clases", e);
        }
    }

    public void print() {
        Scanner scanner = new Scanner(System.in);

        boolean salir = false;

        while (!salir) {
            System.out.println("\nBienvenido, estas son sus opciones:\n");
            for (int i = 0; i < acciones.size(); i++) {
                System.out.println((i + 1) + ". " + acciones.get(i).nombreItemMenu() + " (" + acciones.get(i).descripcionItemMenu() + ")");
            }
            System.out.println((acciones.size() + 1) + ". Salir");
            System.out.print("Ingrese su opción: ");

            String entrada = scanner.nextLine();
            try {
                int opcion = Integer.parseInt(entrada);

                if (opcion == acciones.size() + 1) {
                    salir = true;
                    System.out.println("Saliendo del programa.");
                } else if (opcion >= 1 && opcion <= acciones.size()) {
                    acciones.get(opcion - 1).ejecutar();
                } else {
                    System.out.println("Opción fuera de rango.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingrese un número.");
            }
        }

        scanner.close();
    }

}
