import java.io.*;
import java.util.Vector;

public class Utente {

  private final String nome;
  private final String password;
  private double contoBanca = 0;
  private double contoPortafoglio = 0;
  public Vector<Investimento> investimenti;
  private final Vector<String> storicoTransizioni = new Vector<>();
  private static final String fileTransazioni = "src/main/data/transazioni";
  private static final String fileGrafico = "src/main/data/fileGrafico";

  public Utente(String nome, String password) {
    this.nome = nome;
    this.password = password;
    this.investimenti = new Vector<>();
  }

  public void investi(double soldi, int durata, double rischio) {
    contoBanca -= soldi;
    registraTransazione("Investimento di " + soldi + " per " + durata + " mesi avviato");
    Investimento nuovoInvestimento = new Investimento(soldi, durata, rischio);
    registraGrafico();
    investimenti.add(nuovoInvestimento);
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

  public Vector<String> getStoricoTransizioni() {
    return storicoTransizioni;
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
