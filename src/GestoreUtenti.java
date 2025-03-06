import java.util.Vector;

class GestoreUtenti {
    public static Vector<Utente> utenti = new Vector<>();

    public static void registraUtente(String nome, String password) {
        utenti.add(new Utente(nome, password));
    }

    public static Utente login(String nome, String password) {
        for(int i=0;i<utenti.size();i++){
            if(nome.equals(utenti.elementAt(i).getNome())){
                if(password.equals(utenti.elementAt(i).getPassword())){
                    return utenti.elementAt(i);
                }
            }
        }
        return null;
    }
}

