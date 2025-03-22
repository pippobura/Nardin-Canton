import java.io.*;
import java.util.Scanner;
import java.util.Vector;

public class Utente {

  private final String nome;
  private final String password;
  private double contoBanca = 0;
  private double contoPortafoglio = 0;
  public Vector<Investimento> investimenti;
  public Vector<String> storicoTransizioni = new Vector<>();
  private static final String fileTransazioni = "data/transazioni";
  private static final String fileGrafico = "data/fileGrafico";

  public Utente(String nome, String password) {
    this.nome = nome;
    this.password = password;
    this.investimenti = new Vector<>();
  }

  public void investi(double soldi, int durata) {
    if (soldi > contoBanca) {
      System.out.println("Non hai i soldi necessari per investire!");
    }
    contoBanca -= soldi;
    registraTransazione("Investimento di " + soldi + " per " + durata + " mesi avviato");
    Investimento nuovoInvestimento = new Investimento(soldi, durata);
    registraGrafico();
    investimenti.add(nuovoInvestimento);
  }

  public void mostraInvestimenti() {
    if (investimenti.isEmpty()) {
      System.out.println("Nessun investimento attivo.");
    }
    System.out.println("Investimenti attuali:");
    for (Investimento inv : investimenti) {
      System.out.println(
          "Capitale: " + inv.getCapitale() + " Durata: " + inv.getDurata() + " mesi");
    }
  }

  public void deposita(double dep) {
    contoBanca += dep;
    contoPortafoglio -= dep;
    registraTransazione("Depositati " + dep + " euro");
    registraGrafico();
    GestoreUtenti.salvaUtenti();
  }

  public void preleva(double pre) {
    contoBanca -= pre;
    contoPortafoglio += pre;
    registraTransazione("Prelevati " + pre + " euro");
    registraGrafico();
    GestoreUtenti.salvaUtenti();
  }

  private void registraTransazione(String transazione) {
    storicoTransizioni.add(transazione);
    try (PrintWriter writer =
        new PrintWriter(new FileWriter(fileTransazioni + nome + ".txt", true))) {
      writer.println(transazione);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void registraGrafico() {
    try (PrintWriter writer = new PrintWriter(new FileWriter(fileGrafico + nome + ".csv", true))) {
      writer.println(Banca.dataAttuale + ";" + contoBanca + ";" + contoPortafoglio);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void mostraStoricoTransizioni() {
    System.out.println("Storico transazioni di " + nome + ": ");
    try (Scanner scanner = new Scanner(new File(fileTransazioni + nome + ".txt"))) {
      while (scanner.hasNextLine()) {
        System.out.println(scanner.nextLine());
      }
    } catch (FileNotFoundException e) {
      System.out.println("Nessuna transizione");
    }
  }

  public String toFileString() {
    return nome + "," + password + "," + contoBanca + "," + contoPortafoglio;
  }

  public double getContoPortafoglio() {
    return contoPortafoglio;
  }

  public void aggiungiPortafoglio(double n) {
    contoPortafoglio += n;
    GestoreUtenti.salvaUtenti();
    registraGrafico();
    registraTransazione("Aggiunti " + n + " euro al portafoglio");
  }

  public void aggiungiBanca(double n) {
    contoBanca += n;
    GestoreUtenti.salvaUtenti();
    registraGrafico();
    registraTransazione("Aggiunti " + n + " euro al conto in banca");
  }

  public double getContoBanca() {
    return contoBanca;
  }

  public void setContoBanca(double contoBanca) {
    this.contoBanca = contoBanca;
    GestoreUtenti.salvaUtenti();
    registraGrafico();
  }

  public void setContoPortafoglio(double contoPortafoglio) {
    this.contoPortafoglio = contoPortafoglio;
    GestoreUtenti.salvaUtenti();
    registraGrafico();
  }

  public String getNome() {
    return nome;
  }

  public String getPassword() {
    return password;
  }
}
