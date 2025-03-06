import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BancaTest {

    private Banca banca;

    @BeforeEach
    void setUp() {
        banca = new Banca();
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
}