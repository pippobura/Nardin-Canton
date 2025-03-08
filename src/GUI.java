import javax.swing.*;
import java.awt.*;

class LoginFrame extends JFrame {
    public LoginFrame() {
        setTitle("Login Banca");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2));
        JLabel lblUser = new JLabel("Username:");
        JTextField txtUser = new JTextField();
        JLabel lblPass = new JLabel("Password:");
        JPasswordField txtPass = new JPasswordField();
        JButton btnLogin = new JButton("Login");
        JButton btnRegister = new JButton("Registrati");

        panel.add(lblUser);
        panel.add(txtUser);
        panel.add(lblPass);
        panel.add(txtPass);
        panel.add(btnLogin);
        panel.add(btnRegister);
        add(panel);

        btnLogin.addActionListener(e -> {
            String username = txtUser.getText();
            String password = new String(txtPass.getPassword());
            Utente utente = GestoreUtenti.login(username, password);
            if (utente != null) {
                new DashboardFrame(utente);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Credenziali errate", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnRegister.addActionListener(e -> {
            String username = txtUser.getText();
            String password = new String(txtPass.getPassword());
            GestoreUtenti.registraUtente(username, password);
            JOptionPane.showMessageDialog(this, "Registrazione completata!");
        });

        setVisible(true);
    }
}

class DashboardFrame extends JFrame {
    public DashboardFrame(Utente utente) {
        setTitle("Dashboard - " + utente.getNome());
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2));
        JLabel lblSaldoBanca = new JLabel("Saldo Banca: " + utente.getContoBanca());
        JLabel lblSaldoPortafoglio = new JLabel("Saldo Portafoglio: " + utente.getContoPortafoglio());
        JLabel lblMese = new JLabel("Mese: " + Banca.mese);
        JLabel lblAnno = new JLabel("Anno: " + Banca.anno);
        JButton btnDeposita = new JButton("Deposita");
        JButton btnPreleva = new JButton("Preleva");
        JButton btnInvesti = new JButton("Investi");
        JButton btnNextDay = new JButton("Mese Successivo");
        JButton btnInvestimenti = new JButton("Investimenti in Corso");
        JButton btnStorico = new JButton("Storico Transazioni");

        panel.add(lblMese);
        panel.add(lblAnno);
        panel.add(lblSaldoBanca);
        panel.add(lblSaldoPortafoglio);
        panel.add(btnDeposita);
        panel.add(btnPreleva);
        panel.add(btnInvesti);
        panel.add(btnNextDay);
        panel.add(btnInvestimenti);
        panel.add(btnStorico);
        add(panel);

        btnDeposita.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(this, "Inserisci l'importo da depositare:");
            try {
                double importo = Double.parseDouble(input);
                utente.deposita(importo);
                lblSaldoBanca.setText("Saldo Banca: " + utente.getContoBanca());
                lblSaldoPortafoglio.setText("Saldo Portafoglio: " + utente.getContoPortafoglio());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Inserisci un importo valido!", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnPreleva.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(this, "Inserisci l'importo da prelevare:");
            try {
                double importo = Double.parseDouble(input);
                utente.preleva(importo);
                lblSaldoBanca.setText("Saldo Banca: " + utente.getContoBanca());
                lblSaldoPortafoglio.setText("Saldo Portafoglio: " + utente.getContoPortafoglio());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Inserisci un importo valido!", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnInvesti.addActionListener(e -> {
            String inputImporto = JOptionPane.showInputDialog(this, "Inserisci l'importo da investire:");
            String inputDurata = JOptionPane.showInputDialog(this, "Inserisci la durata dell'investimento: ");
            try {
                double importo = Double.parseDouble(inputImporto);
                int durata = Integer.parseInt(inputDurata);
                utente.investi(importo, durata); // Assumendo che tu abbia un metodo 'investi' nella classe Utente
                lblSaldoBanca.setText("Saldo Banca: " + utente.getContoBanca());
                lblSaldoPortafoglio.setText("Saldo Portafoglio: " + utente.getContoPortafoglio());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Inserisci un importo valido!", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnNextDay.addActionListener(e -> {
            Banca.avanzaTempo();
            lblSaldoBanca.setText("Saldo Banca: " + utente.getContoBanca());
            lblSaldoPortafoglio.setText("Saldo Portafoglio: " + utente.getContoPortafoglio());
        });

        btnInvestimenti.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, , "Investimenti in Corso", JOptionPane.INFORMATION_MESSAGE);
        });

        btnStorico.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, , "Storico Transazioni", JOptionPane.INFORMATION_MESSAGE);
        });

        setVisible(true);
    }
}
