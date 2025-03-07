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
        banca = new Banca();
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
        banca.mese = 1;
        banca.anno = 2025;
        banca.avanzaTempo();

        Assertions.assertEquals(2, banca.mese, "Il mese dovrebbe essere aumentato di 1 (2)");
        Assertions.assertEquals(2025, banca.anno, "L'anno dovrebbe essere lo stesso (2025)");
    }

    @Test
    void testAvanzaAnno() {
        banca.mese = 12;
        banca.anno = 2024;
        banca.avanzaTempo();

        Assertions.assertEquals(1, banca.mese, "Il mese dovrebbe essere 1");
        Assertions.assertEquals(2025, banca.anno, "L'anno dovrebbe essere aumentato di 1 (2025)");
    }

    @Test
    void testStipendia(){
        banca.avanzaTempo();
        Assertions.assertEquals(contoStipendiato, GestoreUtenti.utenti.elementAt(0).getContoPortafoglio(), "Dovrebbe essere aumentato di 1000");
    }

    @Test
    void testSottraiMese(){

        banca.avanzaTempo();
        Assertions.assertEquals(contoConInvestimento, GestoreUtenti.utenti.elementAt(0).getContoBanca(), "Dovrebbe essere aggiunto il rendimento dell'investimento");
    }

}