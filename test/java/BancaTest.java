
import main.java.Banca;
import main.java.Investimento;
import main.java.Utente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class BancaTest {

    double contoStipendiato;
    double contoConInvestimento;
    double contoBancaPrima;
    double contoPortafoglioPrima;

    @BeforeEach
    void setUp() {
        Utente u1 = new Utente("Giorgio", "1234");
        GestoreUtenti.utenti.add(u1);
        contoBancaPrima = GestoreUtenti.utenti.elementAt(0).getContoBanca();
        contoPortafoglioPrima = GestoreUtenti.utenti.elementAt(0).getContoPortafoglio();
        Investimento i1 = new Investimento(100, 1);
        GestoreUtenti.utenti.elementAt(0).investimenti.add(i1);
        contoStipendiato = contoPortafoglioPrima + 1000;
        contoConInvestimento = contoBancaPrima + GestoreUtenti.utenti.elementAt(0).investimenti.elementAt(0).getRendimento();
    }

    @Test
    void testAvanzaMese(){
        Banca.avanzaTempo();
        Assertions.assertEquals(LocalDate.of(2025, 2,1), Banca.dataAttuale, "Il mese dovrebbe essere aumentato di 1 (2)");
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