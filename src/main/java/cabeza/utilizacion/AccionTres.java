package cabeza.utilizacion;

import cabeza.framework_v3.Accion;

public class AccionTres implements Accion {
    @Override
    public void ejecutar() {
        System.out.println("Ejecutando accion 3");
    }

    @Override
    public String nombreItemMenu() {
        return "Accion 3";
    }

    @Override
    public String descripcionItemMenu() {
        return "Esto es una pruba de una nueva accion añadida";
    }
}
