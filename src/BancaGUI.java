import javax.swing.*;

public class BancaGUI {
    public static void main(String[] args) {
        GestoreUtenti.caricaUtenti();
        SwingUtilities.invokeLater(() -> new LoginFrame());
    }
}
