import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

class GestoreUtentiTest {

  @BeforeEach
  void setUp() {
    // Pulizia prima di ogni test
    GestoreUtenti.utenti.clear();
  }

  @Test
  void testRegistraUtente() {
    GestoreUtenti.registraUtente("Mario", "1234");
    assertEquals(1, GestoreUtenti.utenti.size());
    assertEquals("Mario", GestoreUtenti.utenti.get(0).getNome());
  }

  @Test
  void testLoginSuccesso() {
    GestoreUtenti.registraUtente("Luigi", "password");
    assertNotNull(GestoreUtenti.login("Luigi", "password"));
  }

  @Test
  void testLoginFallito() {
    GestoreUtenti.registraUtente("Peach", "mario");
    assertNull(GestoreUtenti.login("Peach", "passwordSbagliata"));
  }

  @Test
  void testSalvaECaricaUtenti() {
    GestoreUtenti.registraUtente("Toad", "fungo");
    GestoreUtenti.salvaUtenti();
    GestoreUtenti.utenti.clear();
    GestoreUtenti.caricaUtenti();

    assertEquals(1, GestoreUtenti.utenti.size());
    assertEquals("Toad", GestoreUtenti.utenti.get(0).getNome());
  }
}
