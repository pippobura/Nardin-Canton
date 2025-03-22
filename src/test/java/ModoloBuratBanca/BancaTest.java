import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BancaTest {
  private static final String TEST_FILE = "test_data.txt";

  @BeforeEach
  public void setUp() {
    Banca.dataAttuale = LocalDate.of(2025, 1, 1);
  }

  @Test
  public void testCaricaData() throws IOException {
    Files.writeString(Path.of(TEST_FILE), "2024-12-31");
    Banca.caricaData(TEST_FILE);
    assertEquals(
        LocalDate.of(2024, 12, 31),
        Banca.dataAttuale,
        "La data dovrebbe essere caricata correttamente");
  }

  @Test
  public void testSalvaData() throws IOException {
    Banca.salvaData(TEST_FILE);
    String dataSalvata = Files.readString(Path.of(TEST_FILE)).trim();
    assertEquals(
        "2025-01-01", dataSalvata, "La data salvata dovrebbe corrispondere a quella attuale");
  }

  @Test
  public void testAvanzaTempo() {
    LocalDate dataIniziale = Banca.dataAttuale;
    Banca.avanzaTempo();
    assertEquals(
        dataIniziale.plusMonths(1), Banca.dataAttuale, "La data dovrebbe avanzare di un mese");
  }

  @Test
  public void testSottraiMese() {
    GestoreUtenti.utenti.clear();
    Utente utente = new Utente("TestUser", "1234");
    Investimento inv = new Investimento(1000, 1, 1.2);
    utente.investimenti.add(inv);
    GestoreUtenti.utenti.add(utente);

    Banca.avanzaTempo();

    assertEquals(
        0, inv.getDurata(), "La durata dell'investimento dovrebbe essere zero dopo un mese");
    assertFalse(utente.investimenti.contains(inv), "L'investimento dovrebbe essere rimosso");
  }

  @Test
  public void testStipendia() {
    GestoreUtenti.utenti.clear();
    Utente utente = new Utente("TestUser", "1234");
    GestoreUtenti.utenti.add(utente);

    double saldoPrecedente = utente.getContoPortafoglio();
    Banca.avanzaTempo();

    assertEquals(
        saldoPrecedente + 1000,
        utente.getContoPortafoglio(),
        "Lo stipendio dovrebbe essere accreditato");
  }

  @Test
  public void testFileNonEsistente() {
    String nonExistingFile = "non_existing_file.txt";
    Banca.caricaData(nonExistingFile);
    assertEquals(
        LocalDate.of(2025, 1, 1),
        Banca.dataAttuale,
        "Se il file non esiste, la data dovrebbe rimanere invariata");
  }

  @AfterAll
  public static void deleteFiles() {
    File f = new File("data/utenti.txt");
    f.delete();
    File f1 = new File("transazioniTestUser.txt");
    f1.delete();
  }
}
