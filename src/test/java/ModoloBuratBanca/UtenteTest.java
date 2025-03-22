import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UtenteTest {

  private Utente utente;

  @BeforeEach
  void setUp() {
    utente = new Utente("testUser", "password123");
  }

  @Test
  void testInvesti() {
    utente.setContoBanca(1000);
    utente.investi(500, 6, 1.2);
    assertEquals(500, utente.getContoBanca(), 0.01);
    assertEquals(1, utente.investimenti.size());
  }

  @Test
  void testDeposita() {
    utente.setContoPortafoglio(500);
    utente.deposita(200);
    assertEquals(200, utente.getContoBanca(), 0.01);
    assertEquals(300, utente.getContoPortafoglio(), 0.01);
  }

  @Test
  void testPreleva() {
    utente.setContoBanca(500);
    utente.preleva(200);
    assertEquals(300, utente.getContoBanca(), 0.01);
    assertEquals(200, utente.getContoPortafoglio(), 0.01);
  }

  @Test
  void testAggiungiPortafoglio() {
    utente.aggiungiPortafoglio(500);
    assertEquals(500, utente.getContoPortafoglio(), 0.01);
  }

  @Test
  void testAggiungiBanca() {
    utente.aggiungiBanca(500);
    assertEquals(500, utente.getContoBanca(), 0.01);
  }

  @Test
  void testStoricoTransazioni() {
    utente.deposita(100);
    utente.preleva(50);
    assertTrue(utente.getStoricoTransizioni().size() >= 2);
  }
}
