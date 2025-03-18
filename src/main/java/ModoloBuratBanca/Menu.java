package ModoloBuratBanca;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Menu {

    private int scelta, max;
    private String percorsoFile;

    public Menu(int max, String percorsoFile) {
        this.max = max;
        this.percorsoFile = percorsoFile;
    }

    private void menu() throws FileNotFoundException {

        File f = new File(percorsoFile);
        Scanner inputFile = new Scanner(f);
        String riga;
        while(inputFile.hasNextLine()) {
            riga=inputFile.nextLine();
            System.out.println(riga);
        }
        inputFile.close();
    }

    public int setScelta() throws FileNotFoundException {
        Scanner tastiera = new Scanner(System.in);
        do {
            menu();
            scelta = tastiera.nextInt();
        } while (this.scelta < 0 || this.scelta > max);
        return this.scelta;
    }
}
