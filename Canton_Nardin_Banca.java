
import java.util.Scanner;

public class Canton_Nardin_Banca {

	public static void presentazione() {

		System.out.println("BENVENUTO/A NELLA BANCA\n");
		System.out.println("I tuoi soldi saranno divisi tra portafoglio e banca:\n"
				+ "il tuo stipendio che verr� dato mensilmente, \n"
				+ "andr� nel tuo conto in banca che parte inizialmente con 0 euro, \n"
				+ "e ogni mese 100 euro del tuo stipendio andranno nel tuo portafoglio, \n"
				+ "che parte inizialmente con 100 euro.\n");
	}

	// menu
	public static void menu() {

		System.out.println("*****MENU*****");
		System.out.println("1. Visualizza conto in banca");
		System.out.println("2. Visualizza conto nel portafoglio\n");
		System.out.println("3. Deposita");
		System.out.println("4. Preleva");
		System.out.println("5. Investi");// mettere sotto menu con quanto tempo vuoi investire e con quanto rischio
		System.out.println("6. Avanza nel tempo\n");

		System.out.println("0. ESCI\n");

		System.out.print("Fai la tua scelta (0...6) : ");
	}

	// sotto menu investimento durata
	public static void menuDurata() {

		System.out.println("\n1. Investi in breve durata");
		System.out.println("2. Investi in media durata");
		System.out.println("3. Investi in lunga durata\n");

		System.out.print("Fai la tua scelta (0...3) : ");
	}

	// sotto menu investimento rischio
	public static void menuRischio() {

		System.out.println("\n1. Investimenti di basso rischio e basso guadagno");
		System.out.println("2. Investimenti di medio rischio e medio guadagno");
		System.out.println("3. Investimenti di alto rischio e alto guadagno.\n");

		System.out.print("Fai la tua scelta (0...3) : ");
	}

	public static 
	public static int[] avanzaTempo(int mese, int anno) {
		int tempo[] = new int[2];

		tempo[0] = mese;
		tempo[1] = anno;

		if (mese == 12) {
			tempo[0] = 1;
			tempo[1]++;
		} else {
			tempo[0]++;
		}
		return tempo;
	}

	// pausa
	public static void Pause() {
		System.out.println("Clicca per continuare");
		new Scanner(System.in).nextLine();
	}

	// main
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner tastiera = new Scanner(System.in);

		presentazione();

		final int DIM = 2;

		double contoBanca = 0;
		double contoPortafoglio = 100;

		int mese = 0;
		int anno = 2024;

		int n = 0;

		// menu
		int scelta;
		do {
			do {
				System.out.println("Data: " + mese + " " + anno);
				menu();

				try {
					scelta = tastiera.nextInt();
				} catch (Exception e) {
					System.out.println("Input non valido\n");
					scelta = -1;
				}

			} while ((scelta < 0) || (scelta > 8));
			switch (scelta) {
			case 1: {

				System.out.println("Il tuo conto in bamca e' di : " + contoBanca + "€");

				Pause();
				break;
			}
			case 2: {

				System.out.println("Il tuo conto nel portafoglio e' di : " + contoPortafoglio + "€");
				Pause();
				break;
			}
			case 3: {

				double soldi = 0;
				boolean ok;
				do {
					ok = true;
					System.out.print("Quanti soldi vuoi depositare?: ");
					tastiera.nextLine();
					String double1 = tastiera.nextLine();
					double1.trim();
					try {

						soldi = (double) Double.parseDouble(double1);

					} catch (NumberFormatException e) {
						System.out.print("ERRORE!! FORMATO NON DOUBLE!!\n");
						ok = false;
					}
				} while (!ok);

				if (contoPortafoglio > soldi) {
					contoBanca = contoBanca + soldi;
					contoPortafoglio = contoPortafoglio - soldi;
				} else {
					System.out.print("ERRORE!! NON HAI ABBASTANZA SOLDI NEL PORTAFOGLI: " + contoPortafoglio + "\n");
				}

				Pause();
				break;
			}

			case 4: {

				double soldi = 0;
				boolean ok;
				do {
					ok = true;
					System.out.print("Quanti soldi vuoi prelevare?: ");
					tastiera.nextLine();
					String double1 = tastiera.nextLine();
					try {

						soldi = (double) Double.parseDouble(double1);

					} catch (NumberFormatException e) {
						System.out.print("ERRORE!! FORMATO NON DOUBLE!!\n");
						ok = false;
					}
				} while (!ok);

				if (contoBanca > soldi) {
					contoBanca = contoBanca - soldi;
					contoPortafoglio = contoPortafoglio + soldi;
				} else {
					System.out.print("ERRORE!! NON HAI ABBASTANZA SOLDI NEL CONTO IN BANCA: " + contoBanca + "\n");
				}

				Pause();
				break;
			}
			case 5: {

				int investimentoBassa=3;
				int investimentoMedia=6;
				int investimentoLunga=12;
				Pause();
				break;
			}
			case 6: {
				int tempo[] = avanzaTempo(mese, anno);

				mese = tempo[0];
				anno = tempo[1];

				System.out.println(mese + " " + anno);
				Pause();
				break;

			}

			case 0: {
				System.out.println("Arrivedorci\n");
				break;
			}
			default:
				System.out.println("Input non valido");
			}
		} while (scelta != 0);

	}

}
