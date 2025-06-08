package cabeza.main;

import cabeza.framework.Screen;

public class Main {
    public static void main(String[] args) {
        var screen = new Screen("/config.properties");
        screen.print();
    }
}
