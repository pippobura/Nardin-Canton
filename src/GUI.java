import javax.swing.*;
import java.awt.*;

class LoginFrame extends JFrame {
    public LoginFrame() {
        setTitle("Login Banca");
        setSize(500, 300);
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
        lblSaldoBanca.setFont(new Font("Arial", Font.ITALIC, 25));
        JLabel lblSaldoPortafoglio = new JLabel("Saldo Portafoglio: " + utente.getContoPortafoglio());
        lblSaldoPortafoglio.setFont(new Font("Arial", Font.ITALIC, 25));
        JLabel lblData = new JLabel("Data: " + Banca.dataAttuale);
        lblData.setFont(new Font("Arial", Font.ITALIC, 25));
        JButton btnDeposita = new JButton("Deposita");
        btnDeposita.setFont(new Font("Arial", Font.BOLD, 25));
        JButton btnPreleva = new JButton("Preleva");
        btnPreleva.setFont(new Font("Arial", Font.BOLD, 25));
        JButton btnInvesti = new JButton("Investi");
        btnInvesti.setFont(new Font("Arial", Font.BOLD, 25));
        JButton btnNextDay = new JButton("Mese Successivo");
        btnNextDay.setFont(new Font("Arial", Font.BOLD, 25));
        JButton btnInvestimenti = new JButton("Investimenti in Corso");
        btnInvestimenti.setFont(new Font("Arial", Font.BOLD, 25));
        JButton btnStorico = new JButton("Storico Transazioni");
        btnStorico.setFont(new Font("Arial", Font.BOLD, 25));

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

        btnDeposita.addActionListener(e -> {
            String input;
            double importo;
            try {
                do{
                    input = JOptionPane.showInputDialog(this, "Inserisci l'importo da depositare:");
                    importo = Double.parseDouble(input);
                } while (importo > utente.getContoPortafoglio());
                utente.deposita(importo);
                lblSaldoBanca.setText("Saldo Banca: " + utente.getContoBanca());
                lblSaldoPortafoglio.setText("Saldo Portafoglio: " + utente.getContoPortafoglio());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Inserisci un importo valido!", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnPreleva.addActionListener(e -> {
            String input;
            double importo;
            try {
                do{
                    input = JOptionPane.showInputDialog(this, "Inserisci l'importo da prelevare:");
                    importo = Double.parseDouble(input);
                } while(importo > utente.getContoBanca());
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
            lblData.setText("Data: " + Banca.dataAttuale);

        });

        btnInvestimenti.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,utente.investimenti , "Investimenti in Corso", JOptionPane.INFORMATION_MESSAGE);
        });

        btnStorico.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,utente.storicoTransizioni , "Storico Transazioni", JOptionPane.INFORMATION_MESSAGE);
        });

        setVisible(true);
    }
}
