import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BancaTest {

    private Banca banca;
    double contoStipendiato;
    double contoConInvestimento;
    double contoBancaPrima;
    double contoPortafoglioPrima;

    @BeforeEach
    void setUp() {
        Utente u1 = new Utente("Giorgio", "1234");
        Utente u2 = new Utente("Giovanni", "1234");
        GestoreUtenti.utenti.add(u1);
        GestoreUtenti.utenti.add(u2);
        contoBancaPrima = GestoreUtenti.utenti.elementAt(0).getContoBanca();
        contoPortafoglioPrima = GestoreUtenti.utenti.elementAt(0).getContoPortafoglio();
        Investimento i1 = new Investimento(100, 1);
        GestoreUtenti.utenti.elementAt(0).investimenti.add(i1);
        contoStipendiato = contoPortafoglioPrima + 1000;
        contoConInvestimento = contoBancaPrima + GestoreUtenti.utenti.elementAt(0).investimenti.elementAt(0).getRendimento();
    }

    @Test
    void testAvanzaMese(){
        Banca.mese = 1;
        Banca.anno = 2025;
        Banca.avanzaTempo();

        Assertions.assertEquals(2, Banca.mese, "Il mese dovrebbe essere aumentato di 1 (2)");
        Assertions.assertEquals(2025, Banca.anno, "L'anno dovrebbe essere lo stesso (2025)");
    }

    @Test
    void testAvanzaAnno() {
        Banca.mese = 12;
        Banca.anno = 2024;
        Banca.avanzaTempo();

        Assertions.assertEquals(1, Banca.mese, "Il mese dovrebbe essere 1");
        Assertions.assertEquals(2025, Banca.anno, "L'anno dovrebbe essere aumentato di 1 (2025)");
    }

    @Test
    void testStipendia(){
        Banca.avanzaTempo();
        Assertions.assertEquals(contoStipendiato, GestoreUtenti.utenti.elementAt(0).getContoPortafoglio(), "Dovrebbe essere aumentato di 1000");
    }

    @Test
    void testSottraiMese(){

        Banca.avanzaTempo();
        Assertions.assertEquals(contoConInvestimento, GestoreUtenti.utenti.elementAt(0).getContoBanca(), "Dovrebbe essere aggiunto il rendimento dell'investimento");
    }

}