import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void Pause() {
        System.out.println("Clicca per continuare");
        new Scanner(System.in).nextLine();
    }

    public static Utente login(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserisci username: ");
        String nome = scanner.nextLine();
        System.out.print("Inserisci password: ");
        String password = scanner.nextLine();
        return GestoreUtenti.login(nome, password);
    }

    public static void signUp(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserisci username: ");
        String nome = scanner.nextLine();
        System.out.print("Inserisci password: ");
        String password = scanner.nextLine();
        GestoreUtenti.registraUtente(nome, password);
    }

    public static void main(String[] args) throws FileNotFoundException{

        Scanner tastiera = new Scanner(System.in);
        Menu principale = new Menu(8,"menu.txt");
        Menu mUtente = new Menu(2, "menuRegistrazione.txt");
        double soldi = 0;
        int registrazione;
        int scelta;
        GestoreUtenti.caricaUtenti();

        do{

            registrazione = mUtente.setScelta();

            switch (registrazione){
                case 1: {
                    signUp();
                    break;
                }
                case 2: {
                    registrazione = 0;
                    break;
                }
            }
        } while(registrazione != 0);

        Utente utente = login();

        if(utente != null){
            do {

                scelta = principale.setScelta();

                switch (scelta) {

                    case 1: {
                        System.out.println("Il tuo conto in banca e' di : " + utente.getContoBanca() + "€");
                        Pause();
                        break;
                    }
                    case 2: {
                        System.out.println("Il tuo conto nel portafoglio e' di : " + utente.getContoPortafoglio() + "€");
                        Pause();
                        break;
                    }

                    case 3: {

                        if (utente.getContoBanca() <= 0) {
                            System.out.println("Non hai soldi in banca!");
                        } else {
                            boolean vero;
                            do {
                                vero = true;
                                System.out.print("Quanti soldi vuoi investire: ");
                                String double1 = tastiera.nextLine().trim();

                                try {

                                    soldi = Double.parseDouble(double1);

                                } catch (NumberFormatException e) {
                                    System.out.print("ERRORE!! FORMATO NON DOUBLE!!\n");
                                    vero = false;
                                }
                            } while (!vero);

                            while (soldi > utente.getContoBanca()) {
                                System.out.println("Importo troppo alto\n");
                                System.out.print("Quanti soldi vuoi investire: ");
                                soldi = tastiera.nextDouble();
                            } // while

                            int durata = DurataInvestimento.scegliDurata();
                            utente.investi(soldi, durata);
                        }

                        Pause();
                        break;
                    } // case 3

                    case 4: {
                        double dep = 0;
                        if (utente.getContoPortafoglio() <= 0) {
                            System.out.println("Non hai soldi nel portafoglio!");
                        } else {
                            boolean vero;
                            do {
                                vero = true;
                                System.out.print("Quanti soldi vuoi depositare: ");
                                String double1 = tastiera.nextLine().trim();

                                try {

                                    dep = Double.parseDouble(double1);

                                } catch (NumberFormatException e) {
                                    System.out.print("ERRORE!! FORMATO NON DOUBLE!!\n");
                                    vero = false;
                                }
                            } while (!vero);

                            while (dep > utente.getContoPortafoglio()) {
                                System.out.println("Importo troppo alto!");
                                System.out.println("Il tuo portafoglio contiene : " + utente.getContoPortafoglio());
                                System.out.print("Quanti soldi vuoi depositare: ");
                                dep = tastiera.nextDouble();
                            }
                        }// while
                        utente.deposita(dep);
                        Pause();
                        break;
                    } // case

                    case 5: {
                        double pre = 0;
                        boolean vero;
                        do {
                            vero = true;
                            System.out.print("Quanti soldi vuoi prelevare : ");

                            String double1 = tastiera.nextLine();
                            try {

                                pre = Double.parseDouble(double1);

                            } catch (NumberFormatException e) {
                                System.out.print("ERRORE!! FORMATO NON DOUBLE!!\n");
                                vero = false;
                            }
                        } while (!vero);

                        while (pre > utente.getContoBanca()) {
                            System.out.println("Importo troppo alto!");
                            System.out.println("Il tuo conto in banca contiene : " + utente.getContoBanca());
                            System.out.print("Quanti soldi vuoi prelevare : ");
                            pre = tastiera.nextDouble();
                        } // while
                        utente.preleva(pre);
                        Pause();
                        break;
                    }

                    case 6: {
                        Banca.avanzaTempo();
                        Pause();
                        break;
                    }

                    case 7: {
                        utente.mostraInvestimenti();
                        break;
                    }

                    case 8: {
                        utente.mostraStoricoTransizioni();
                        break;
                    }

                    case 0: {
                        System.out.println("Arrivederci");
                        break;
                    }

                    default:
                        System.out.println("Input non valido");
                        break;
                }

            } while (scelta != 0);
            tastiera.close();

        } else {
            System.out.println("Login fallito. Username o password errati.");
        }
        GestoreUtenti.salvaUtenti();
    }
}