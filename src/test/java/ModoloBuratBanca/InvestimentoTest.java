import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class InvestimentoTest {
  private Investimento inv1;
  private Investimento inv2;
  private Investimento inv3;

  @BeforeEach
  public void setUp() {
    inv1 = new Investimento(1000, 3);
    inv2 = new Investimento(2000, 6);
    inv3 = new Investimento(5000, 12);
  }

  @RepeatedTest(10) // Ripete il test per controllare la variabilità del rendimento
  public void testCalcoloRendimento() {
    assertTrue(
        inv1.getRendimento() == -1000 * 1.05 || inv1.getRendimento() == 1000 * 1.05,
        "Il rendimento deve essere positivo o negativo in base alla probabilità");
    assertTrue(
        inv2.getRendimento() == -2000 * 1.08 || inv2.getRendimento() == 2000 * 1.08,
        "Il rendimento deve essere positivo o negativo in base alla probabilità");
    assertTrue(
        inv3.getRendimento() == -5000 * 1.15 || inv3.getRendimento() == 5000 * 1.15,
        "Il rendimento deve essere positivo o negativo in base alla probabilità");
  }

  @Test
  public void testSottraiMese() {
    inv1.sottraiMese();
    assertEquals(2, inv1.getDurata(), "La durata dovrebbe essere 2 mesi dopo una sottrazione");
    inv1.sottraiMese();
    assertEquals(1, inv1.getDurata(), "La durata dovrebbe essere 1 mese dopo due sottrazioni");
    inv1.sottraiMese();
    assertEquals(0, inv1.getDurata(), "La durata dovrebbe essere 0 mesi dopo tre sottrazioni");
  }

  @Test
  public void testValoriIniziali() {
    assertEquals(1000, inv1.getCapitale(), "Il capitale iniziale dovrebbe essere 1000");
    assertEquals(3, inv1.getDurata(), "La durata iniziale dovrebbe essere 3 mesi");
    assertEquals(2000, inv2.getCapitale(), "Il capitale iniziale dovrebbe essere 2000");
    assertEquals(6, inv2.getDurata(), "La durata iniziale dovrebbe essere 6 mesi");
  }
}
