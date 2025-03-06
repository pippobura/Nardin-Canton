
import java.util.Vector;

public class Utente {

    private String nome;
    private String password;
    private double contoBanca = 0;
    private double contoPortafoglio = 1000;
    public Vector<Investimento> investimenti;
    Menu mRischio = new Menu(3,"menuRischio.txt");

    public Utente(String nome, String password){
        this.nome = nome;
        this.password = password;
        this.investimenti = new Vector<>();
    }

    public void investi(double soldi, int durata) {
        if (soldi > contoBanca) {
            System.out.println("Non hai i soldi necessari per investire!");
        }
        contoBanca -= soldi;
        Investimento nuovoInvestimento = new Investimento(soldi, durata);
        investimenti.add(nuovoInvestimento);
        System.out.println("Investimento avviato con successo!");

    }

    public void mostraInvestimenti(){
        if (investimenti.isEmpty()) {
            System.out.println("Nessun investimento attivo.");
            return;
        }
        System.out.println("Investimenti attuali:");
        for (Investimento inv : investimenti) {
            System.out.println("Capitale: " + inv.getCapitale() + " Durata: " + inv.getDurata() + " mesi");
        }
    }

    public void deposita(double dep) {
        contoBanca += dep;
        contoPortafoglio -= dep;
        System.out.println("Soldi depositati con successo!");
    }

    public void preleva(double pre) {
        contoBanca -= pre;
        contoPortafoglio += pre;
        System.out.println("Soldi prelevati con successo!");
    }

    public double getContoPortafoglio() {
        return contoPortafoglio;
    }

    public void aggiungiSoldi(double n) {
        contoPortafoglio += n;
    }

    public double getContoBanca() {
        return contoBanca;
    }

    public String getNome(){
        return nome;
    }

    public String getPassword(){
        return password;
    }
}