package cabeza.framework_v3;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class EstrategiaProperties extends EstrategiaConfiguracion {
    private static final String CLASS_NAME_PROPERTY = "acciones";

    public EstrategiaProperties(String path) {
        super(path);
    }

    @Override
    public List<String> cargarNombresDeClases() {
        Properties prop = new Properties();
        try (InputStream configFile = getClass().getResourceAsStream(path)) {
            prop.load(configFile);
            String clasesStr = prop.getProperty(CLASS_NAME_PROPERTY);
            if (clasesStr == null || clasesStr.isBlank()) {
                throw new RuntimeException("Falta la propiedad 'acciones'");
            }
            return Arrays.stream(clasesStr.split(","))
                    .map(String::trim)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Error leyendo archivo properties", e);
        }
    }
}
