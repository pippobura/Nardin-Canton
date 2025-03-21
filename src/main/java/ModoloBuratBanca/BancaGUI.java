import javax.swing.*;

public class BancaGUI {
  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
    } catch (Exception e) {
      e.printStackTrace();
    }
    GestoreUtenti.caricaUtenti();
    Banca.caricaData("data/data.txt");
    SwingUtilities.invokeLater(LoginFrame::new);
  }
}
