package main;
import java.io.FileNotFoundException;

public class DurataInvestimento {

    public static int scegliDurata() throws FileNotFoundException {

        Menu durata = new Menu(3,"menuDurata.txt");

        return switch (durata.setScelta()) {
            case 1 -> 4;
            case 2 -> 7;
            case 3 -> 13;
            default -> 0;
        };
    }
}
