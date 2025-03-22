public class Investimento {
  private final double capitale;
  private int durata;
  private final double rendimento;

  public Investimento(double capitale, int durata) {
    this.capitale = capitale;
    this.durata = durata;
    this.rendimento = calcolaRendimento();
  }

  private double calcolaRendimento() {
    double tasso =
        switch (durata) {
          case 3 -> 1.05; // 5% di interesse per 3 mesi
          case 6 -> 1.08; // 8% di interesse per 6 mesi
          case 12 -> 1.15; // 15% di interesse per 12 mesi
          default -> 1.00;
        };
    if ((Math.random() * 100) < 50) {
      return -(capitale * tasso);
    }
    return capitale * tasso;
  }

  public double getRendimento() {
    return rendimento;
  }

  public void sottraiMese() {
    durata--;
  }

  public double getCapitale() {
    return capitale;
  }

  public int getDurata() {
    return durata;
  }

  public String toString() {
    return "Investimento di: " + capitale + " euro, " + durata + " mesi rimanenti";
  }
}
