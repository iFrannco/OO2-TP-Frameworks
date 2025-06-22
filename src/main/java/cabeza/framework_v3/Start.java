package cabeza.framework_v3;

public class Start {

    private Screen s;

    public Start() {
        this.s = new Screen();
    }

    public Start(String pathConfig) {
        this.s = new Screen(pathConfig);
    }

    public Start(EstrategiaConfiguracion estrategiaConfiguracion) {
        this.s = new Screen(estrategiaConfiguracion);
    }

    public void init() {
        this.s.print();
    }
}
