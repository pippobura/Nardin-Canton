import java.io.*;
import java.util.Scanner;
import java.util.Vector;

class GestoreUtenti {
    public static Vector<Utente> utenti = new Vector<>();
    private static String fileUtenti = "utenti.txt";

    public static void registraUtente(String nome, String password) {
        utenti.add(new Utente(nome, password));
        salvaUtenti();
    }

    public static Utente login(String nome, String password) {
        for(int i=0;i<utenti.size();i++){
            if(nome.equals(utenti.elementAt(i).getNome())){
                if(password.equals(utenti.elementAt(i).getPassword())){
                    return utenti.elementAt(i);
                }
            }
        }
        return null;
    }

    public static void salvaUtenti(){
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileUtenti))){
            for(int i=0;i<utenti.size(); i++){
                writer.println(utenti.elementAt(i).toFileString());
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void caricaUtenti(){
        utenti.clear();
        try(Scanner scanner = new Scanner(new File(fileUtenti))){
            while(scanner.hasNextLine()){
                String linea = scanner.nextLine();
                String[] dati = linea.split(",");
                Utente utente = new Utente(dati[0], dati[1]);
                utente.setContoBanca(Double.parseDouble(dati[2]));
                utente.setContoPortafoglio(Double.parseDouble(dati[3]));
                utenti.add(utente);
            }
        } catch (FileNotFoundException e){
            System.out.println("Nessun file utenti");
        }
    }
}

