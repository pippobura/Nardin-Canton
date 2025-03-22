import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

class InvestimentoTest {

  @Test
  void testCalcoloRendimento() {
    Investimento inv3Mesi = new Investimento(1000, 3);
    Investimento inv6Mesi = new Investimento(1000, 6);
    Investimento inv12Mesi = new Investimento(1000, 12);

    assertEquals(1050, inv3Mesi.getRendimento(), 0.01);
    assertEquals(1080, inv6Mesi.getRendimento(), 0.01);
    assertEquals(1150, inv12Mesi.getRendimento(), 0.01);
  }

  @Test
  void testSottraiMese() {
    Investimento inv = new Investimento(500, 6);
    inv.sottraiMese();
    assertEquals(5, inv.getDurata());
  }
}
