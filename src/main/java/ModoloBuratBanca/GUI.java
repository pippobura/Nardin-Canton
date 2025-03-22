import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

class LoginFrame extends JFrame {
  public LoginFrame() {
    setTitle("Login Banca");
    setSize(500, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    ImageIcon icon = new ImageIcon("data/icona.jpg");
    setIconImage(icon.getImage());

    ImageIcon imageIcon = new ImageIcon("logoBanca.jpg");
    JLabel lblImage = new JLabel(imageIcon);
    lblImage.setHorizontalAlignment(SwingConstants.CENTER);
    JPanel panel = new JPanel(new GridLayout(3, 2));
    JLabel lblUser = new JLabel("Username:");
    JTextField txtUser = new JTextField();
    JLabel lblPass = new JLabel("Password:");
    JPasswordField txtPass = new JPasswordField();
    JButton btnLogin = new JButton("Login");
    JButton btnRegister = new JButton("Registrati");

    panel.add(lblImage);
    panel.add(lblUser);
    panel.add(txtUser);
    panel.add(lblPass);
    panel.add(txtPass);
    panel.add(btnLogin);
    panel.add(btnRegister);
    add(panel);

    btnLogin.addActionListener(
        e -> {
          String username = txtUser.getText();
          String password = new String(txtPass.getPassword());
          Utente utente = GestoreUtenti.login(username, password);
          if (utente != null) {
            new DashboardFrame(utente);
            dispose();
          } else {
            JOptionPane.showMessageDialog(
                this, "Credenziali errate", "Errore", JOptionPane.ERROR_MESSAGE);
          }
        });

    btnRegister.addActionListener(
        e -> {
          String username = txtUser.getText();
          String password = new String(txtPass.getPassword());

          if (password.length() < 5) {
            JOptionPane.showMessageDialog(
                this,
                "La password deve essere di almeno 5 caratteri",
                "Errore",
                JOptionPane.ERROR_MESSAGE);
            return;
          }

          GestoreUtenti.registraUtente(username, password);
          JOptionPane.showMessageDialog(this, "Registrazione completata!");
        });

    setVisible(true);
  }
}

class DashboardFrame extends JFrame {
  public DashboardFrame(Utente utente) {
    setTitle("Dashboard - " + utente.getNome());
    setSize(900, 600);
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    setLocationRelativeTo(null);
    ImageIcon icon = new ImageIcon("data/icona.jpg");
    setIconImage(icon.getImage());

    JPanel panel = new JPanel(new GridLayout(3, 2));
    JLabel lblSaldoBanca = new JLabel("Saldo Banca: " + utente.getContoBanca());
    lblSaldoBanca.setFont(new Font("Arial", Font.ITALIC, 15));
    JLabel lblSaldoPortafoglio = new JLabel("Saldo Portafoglio: " + utente.getContoPortafoglio());
    lblSaldoPortafoglio.setFont(new Font("Arial", Font.ITALIC, 15));
    JLabel lblData = new JLabel("Data: " + Banca.dataAttuale);
    lblData.setFont(new Font("Arial", Font.ITALIC, 15));
    JButton btnDeposita = new JButton("Deposita");
    btnDeposita.setFont(new Font("Arial", Font.BOLD, 15));
    JButton btnPreleva = new JButton("Preleva");
    btnPreleva.setFont(new Font("Arial", Font.BOLD, 15));
    JButton btnInvesti = new JButton("Investi");
    btnInvesti.setFont(new Font("Arial", Font.BOLD, 15));
    JButton btnNextDay = new JButton("Mese Successivo");
    btnNextDay.setFont(new Font("Arial", Font.BOLD, 15));
    JButton btnInvestimenti = new JButton("Investimenti in Corso");
    btnInvestimenti.setFont(new Font("Arial", Font.BOLD, 15));
    JButton btnStorico = new JButton("Storico Transazioni");
    btnStorico.setFont(new Font("Arial", Font.BOLD, 15));
    JButton btnLogout = new JButton("Logout");
    btnLogout.setFont(new Font("Arial", Font.BOLD, 15));

    panel.add(lblData);
    panel.add(lblSaldoBanca);
    panel.add(lblSaldoPortafoglio);
    panel.add(btnDeposita);
    panel.add(btnPreleva);
    panel.add(btnInvesti);
    panel.add(btnNextDay);
    panel.add(btnInvestimenti);
    panel.add(btnStorico);
    add(panel);

    btnDeposita.addActionListener(
        e -> {
          double importo;
          String input = JOptionPane.showInputDialog(this, "Inserisci l'importo da depositare:");

          if (input == null || input.trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Operazione annullata o valore non valido.",
                "Attenzione",
                JOptionPane.WARNING_MESSAGE);
            return;
          }
          try {
            importo = Double.parseDouble(input.trim());

          } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                this, "Inserisci un importo valido!", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
          }
          if (importo > utente.getContoPortafoglio()) {
            JOptionPane.showMessageDialog(
                this, "Valore troppo alto.", "Attenzione", JOptionPane.WARNING_MESSAGE);
            return;
          }

          utente.deposita(importo);
          lblSaldoBanca.setText("Saldo Banca: " + utente.getContoBanca());
          lblSaldoPortafoglio.setText("Saldo Portafoglio: " + utente.getContoPortafoglio());
        });

    btnPreleva.addActionListener(
        e -> {
          double importo;
          String input = JOptionPane.showInputDialog(this, "Inserisci l'importo da prelevare:");

          if (input == null || input.trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Operazione annullata o valore non valido.",
                "Attenzione",
                JOptionPane.WARNING_MESSAGE);
            return;
          }
          try {
            importo = Double.parseDouble(input.trim());

          } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                this, "Inserisci un importo valido!", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
          }
          if (importo > utente.getContoBanca()) {
            JOptionPane.showMessageDialog(
                this, "Valore troppo alto.", "Attenzione", JOptionPane.WARNING_MESSAGE);
            return;
          }

          utente.preleva(importo);
          lblSaldoBanca.setText("Saldo Banca: " + utente.getContoBanca());
          lblSaldoPortafoglio.setText("Saldo Portafoglio: " + utente.getContoPortafoglio());
        });

    btnInvesti.addActionListener(
        e -> {
          double importo;
          int durata;
          int risultato = 0;

          String input = JOptionPane.showInputDialog(this, "Inserisci l'importo da investire:");

          if (input == null || input.trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Operazione annullata o valore non valido.",
                "Attenzione",
                JOptionPane.WARNING_MESSAGE);
            return;
          }

          try {
            importo = Double.parseDouble(input.trim());
          } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                this, "Inserisci un importo valido!", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
          }

          if (importo > utente.getContoBanca()) {
            JOptionPane.showMessageDialog(
                this, "Valore troppo alto.", "Attenzione", JOptionPane.WARNING_MESSAGE);
            return;
          }

          String[] opzioni = {"3 Mesi", "6 Mesi", "12 Mesi"};
          durata =
              JOptionPane.showOptionDialog(
                  null,
                  "Scegli una durata per l'investimento:",
                  "Menu Durata",
                  JOptionPane.DEFAULT_OPTION,
                  JOptionPane.INFORMATION_MESSAGE,
                  null,
                  opzioni,
                  opzioni[0]);

          if (durata == 0) {
            risultato = 3;
          } else if (durata == 1) {
            risultato = 6;
          } else if (durata == 2) {
            risultato = 12;
          }

          if (risultato > 0) {
            utente.investi(importo, risultato);
            lblSaldoBanca.setText("Saldo : " + utente.getContoBanca());
            lblSaldoPortafoglio.setText("Saldo Portafoglio: " + utente.getContoPortafoglio());
          } else {
            JOptionPane.showMessageDialog(
                this, "Durata non valida.", "Errore", JOptionPane.ERROR_MESSAGE);
          }
        });

    btnNextDay.addActionListener(
        e -> {
          Banca.avanzaTempo();
          lblSaldoBanca.setText("Saldo Banca: " + utente.getContoBanca());
          lblSaldoPortafoglio.setText("Saldo Portafoglio: " + utente.getContoPortafoglio());
          lblData.setText("Data: " + Banca.dataAttuale);
        });

    btnInvestimenti.addActionListener(
        e -> {
          StringBuilder sb = new StringBuilder("Investimenti in Corso:\n");
          for (Investimento i : utente.investimenti) {
            sb.append(i).append("\n");
          }
          JOptionPane.showMessageDialog(
              this, sb.toString(), "Investimenti in Corso", JOptionPane.INFORMATION_MESSAGE);
        });

    btnStorico.addActionListener(
        e -> {
          StringBuilder sb = new StringBuilder("Storico Transizioni:\n");
          for (String s : utente.storicoTransizioni) {
            sb.append(s).append("\n");
          }
          JOptionPane.showMessageDialog(
              this, sb.toString(), "Storico Transazioni", JOptionPane.INFORMATION_MESSAGE);
        });

    addWindowListener(
        new WindowAdapter() {
          @Override
          public void windowClosing(WindowEvent e) {
            int scelta =
                JOptionPane.showConfirmDialog(
                    DashboardFrame.this,
                    "Sei sicuro di voler uscire?",
                    "Conferma uscita",
                    JOptionPane.YES_NO_OPTION);

            if (scelta == JOptionPane.YES_OPTION) {
              GestoreUtenti.salvaUtenti();
              Banca.salvaData("data/data.txt");
              dispose();
              System.exit(0);
            }
          }
        });

    setVisible(true);
  }
}
