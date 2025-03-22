import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class InvestimentoTest {
  @Test
  void testCapitale() {
    Investimento investimento = new Investimento(1000, 6, 0.8);
    assertEquals(1000, investimento.getCapitale(), "Il capitale dovrebbe essere 1000");
  }

  @Test
  void testDurata() {
    Investimento investimento = new Investimento(1000, 6, 0.8);
    assertEquals(6, investimento.getDurata(), "La durata iniziale dovrebbe essere 6 mesi");

    investimento.sottraiMese(); // ridurre la durata di 1 mese
    assertEquals(
        5, investimento.getDurata(), "La durata dovrebbe essere 5 mesi dopo aver sottratto 1 mese");
  }

  @Test
  void testRendimentoZeroRischio() {
    Investimento investimento = new Investimento(1000, 6, 0.0); // Rischio nullo
    double rendimento = investimento.getRendimento();

    // Se il rischio è 0, il rendimento non dovrebbe mai essere positivo
    assertEquals(0.0, rendimento, "Il rendimento con rischio 0 dovrebbe essere 0");
  }
}
