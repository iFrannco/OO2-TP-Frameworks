package cabeza.main;

import cabeza.framework_v3.EstrategiaProperties;
import cabeza.framework_v3.Start;

public class Main {
    public static void main(String[] args) {
        // Version 1
//        var start = new Start("/config.properties");
//        start.init();


        // Version 3
        var estrategia = new EstrategiaProperties("/config.properties");
//        var estrategia = new EstrategiaJSON("/config.json");
        var start = new Start(estrategia);
        start.init();
    }
}
