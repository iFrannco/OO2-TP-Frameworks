package cabeza.framework_v3;

import java.util.List;

public abstract class EstrategiaConfiguracion {
    protected String path;

    public EstrategiaConfiguracion(String path) {
        this.path = path;
    }

    public abstract List<String> cargarNombresDeClases();
}
