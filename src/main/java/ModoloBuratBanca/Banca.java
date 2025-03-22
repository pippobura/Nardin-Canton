import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Vector;

public class Banca {

  static LocalDate dataAttuale = LocalDate.now();

  public static void caricaData(String filePath) {
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      String[] dataString = reader.readLine().split("-");
      dataAttuale =
          LocalDate.of(
              Integer.parseInt(dataString[0]),
              Integer.parseInt(dataString[1]),
              Integer.parseInt(dataString[2]));
    } catch (IOException e) {
      System.err.println("Errore nel caricamento della data: " + e.getMessage());
    }
  }

  public static void salvaData(String filePath) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
      writer.write(dataAttuale.toString());
    } catch (IOException e) {
      System.err.println("Errore nel salvataggio della data: " + e.getMessage());
    }
  }

  public static void avanzaTempo() {
    dataAttuale = dataAttuale.plusMonths(1);
    sottraiMese();
    stipendia();
  }

  private static void sottraiMese() {
    for (int i = 0; i < GestoreUtenti.utenti.size(); i++) {
      Vector<Investimento> investimentiDaRimuovere = new Vector<>();
      for (int j = 0; j < GestoreUtenti.utenti.elementAt(i).investimenti.size(); j++) {
        GestoreUtenti.utenti.elementAt(i).investimenti.elementAt(j).sottraiMese();
        if (GestoreUtenti.utenti.elementAt(i).investimenti.elementAt(j).getDurata() == 0) {
          double rendimento =
              GestoreUtenti.utenti.elementAt(i).investimenti.elementAt(j).getRendimento();
          GestoreUtenti.utenti.elementAt(i).aggiungiBanca(rendimento);
          investimentiDaRimuovere.add(GestoreUtenti.utenti.elementAt(i).investimenti.elementAt(j));
        }
      }
      GestoreUtenti.utenti.elementAt(i).investimenti.removeAll(investimentiDaRimuovere);
    }
  }

  private static void stipendia() {
    for (int i = 0; i < GestoreUtenti.utenti.size(); i++) {
      GestoreUtenti.utenti.elementAt(i).aggiungiPortafoglio(1000);
    }
  }
}
