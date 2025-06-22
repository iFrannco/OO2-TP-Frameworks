package cabeza.framework_v3;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class EstrategiaJSON extends EstrategiaConfiguracion {

    public EstrategiaJSON(String path) {
        super(path);
    }

    @Override
    public List<String> cargarNombresDeClases() {
        try (InputStream jsonFile = getClass().getResourceAsStream(path)) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(jsonFile);
            JsonNode accionesNode = root.get("acciones");

            if (accionesNode == null || !accionesNode.isArray()) {
                throw new RuntimeException("El JSON debe contener un arreglo 'acciones'");
            }

            List<String> nombres = new ArrayList<>();
            for (JsonNode nodo : accionesNode) {
                nombres.add(nodo.asText().trim());
            }
            return nombres;
        } catch (IOException e) {
            throw new RuntimeException("Error leyendo archivo JSON", e);
        }
    }
}
