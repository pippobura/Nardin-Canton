import org.junit.jupiter.api.*;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

class UtenteTest {
  private Utente utente;

  @BeforeEach
  void setUp() {
    utente = new Utente("Mario", "password123");
    utente.setContoBanca(1000);
    utente.setContoPortafoglio(500);
  }

  @Test
  void testDeposita() {
    utente.deposita(200);
    assertEquals(1200, utente.getContoBanca());
    assertEquals(300, utente.getContoPortafoglio());
  }

  @Test
  void testPreleva() {
    utente.preleva(100);
    assertEquals(900, utente.getContoBanca());
    assertEquals(600, utente.getContoPortafoglio());
  }

  @Test
  void testInvestiSuccesso() {
    utente.investi(500, 6);
    assertEquals(500, utente.getContoBanca());
    assertEquals(1, utente.investimenti.size());
  }

  @Test
  void testInvestiFallito() {
    utente.investi(2000, 6);
    assertEquals(1000, utente.getContoBanca()); //Fondi insufficenti
    assertEquals(0, utente.investimenti.size());
  }

  @Test
  void testAggiungiPortafoglio() {
    utente.aggiungiPortafoglio(150);
    assertEquals(650, utente.getContoPortafoglio());
  }

  @Test
  void testAggiungiBanca() {
    utente.aggiungiBanca(250);
    assertEquals(1250, utente.getContoBanca());
  }

  @Test
  void testToFileString() {
    String expected = "Mario,password123,1000.0,500.0";
    assertEquals(expected, utente.toFileString());
  }
}
