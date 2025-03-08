import java.util.Vector;

public class Banca {

    static int mese = 1;
    static int anno = 2025;

    public static void avanzaTempo() {

        if (mese == 12) {
            mese = 1;
            anno++;
        } else {
            mese++;
        }
        sottraiMese();
        stipendia();
    }

    private static void sottraiMese(){
        for(int i=0;i<GestoreUtenti.utenti.size();i++){
            Vector<Investimento> investimentiDaRimuovere = new Vector<>();
            for(int j=0;j<GestoreUtenti.utenti.elementAt(i).investimenti.size();j++){
                GestoreUtenti.utenti.elementAt(i).investimenti.elementAt(j).sottraiMese();
                if(GestoreUtenti.utenti.elementAt(i).investimenti.elementAt(j).getDurata() == 0){
                    double rendimento = GestoreUtenti.utenti.elementAt(i).investimenti.elementAt(j).getRendimento();
                    GestoreUtenti.utenti.elementAt(i).aggiungiBanca(rendimento);
                    investimentiDaRimuovere.add(GestoreUtenti.utenti.elementAt(i).investimenti.elementAt(j));
                }
            } //provare se funziona
            GestoreUtenti.utenti.elementAt(i).investimenti.removeAll(investimentiDaRimuovere);
        }
    }

    private static void stipendia(){
        for (int i = 0; i < GestoreUtenti.utenti.size(); i++) {
            GestoreUtenti.utenti.elementAt(i).aggiungiPortafoglio(1000);
        }
    }

}
