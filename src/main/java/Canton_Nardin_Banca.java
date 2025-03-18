package main.java;
import java.util.Scanner;

public class Canton_Nardin_Banca {

	public static void presentazione() {

		System.out.println("BENVENUTO/A NELLA BANCA\n");
		System.out.println("I tuoi soldi saranno divisi tra portafoglio e banca:\n"
				+ "il tuo stipendio che verra' dato mensilmente, \n"
				+ "andra' nel tuo conto in banca che parte inizialmente con 0 euro, \n"
				+ "e ogni mese 100 euro del tuo stipendio andranno nel tuo portafoglio, \n"
				+ "che parte inizialmente con 100 euro.\n");
	}

	// menu
	public static void menu() {

		System.out.println("*****MENU*****\n");
		System.out.println("1. Visualizza conto in banca");
		System.out.println("2. Visualizza conto nel portafoglio\n");
		System.out.println("3. Investi");
		System.out.println("4. Deposita");
		System.out.println("5. Preleva\n");
		System.out.println("6. Avanza nel tempo\n");

		System.out.println("0. ESCI\n");

		System.out.print("Fai la tua scelta (0...6) : ");
	}

	// sotto menu investimento durata
	public static void menuDurata() {

		System.out.println("\n1. Investi in breve durata (3 mesi)");
		System.out.println("2. Investi in media durata (6 mesi)");
		System.out.println("3. Investi in lunga durata (12 mesi)\n");

		System.out.print("Fai la tua scelta (1...3) : ");
	}

	// sotto menu investimento rischio
	public static void menuRischio() {

		System.out.println("\n1. Investimenti di basso rischio e basso guadagno");
		System.out.println("2. Investimenti di medio rischio e medio guadagno");
		System.out.println("3. Investimenti di alto rischio e alto guadagno.\n");

		System.out.print("Fai la tua scelta (1...3) : ");
	}

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

	public static double Investi(double soldi) {
		int n = 0;
		int scelta1;
		do {
			menuRischio();
			Scanner tastiera = new Scanner(System.in);
			try {
				scelta1 = tastiera.nextInt();
			} // try
			catch (Exception e) {
				System.out.println("Input non valido\n");
				scelta1 = -1;
			} // catch

		} while ((scelta1 < 1) || (scelta1 > 3));// do-while

		switch (scelta1) {

		case 1: {
			n = (int) (Math.random() * 100);
			if (n > 0 && n < 76) {
				soldi =soldi + (double) ((0.25) * soldi);
				
				return soldi;
			} // if
			else {
				soldi = (double) -((soldi + ((0.25) * soldi)));

				return soldi;
			} // else

		} // case 1

		case 2: {
			n = (int) (Math.random() * 100);
			if (n > 0 && n < 51) {
				soldi += (double) ((0.5) * soldi);
				return soldi;
			} // if
			else {
				soldi = (double) -((soldi + ((0.5) * soldi)));
				return soldi;
			} // else

		} // case 2

		case 3: {
			n = (int) (Math.random() * 100);
			if (n > 0 && n < 26) {
				soldi += (double) ((0.75) * soldi);
				
				return soldi;
			} // if
			else {
				soldi = (double) -((soldi + ((0.75) * soldi)));
				
				return soldi;

			} // else
		} // case 3

		default:
			break;
		}

		return soldi;

	}

	public static double stipendio(double stip, double contoPortafoglio) {
		// stip = Math.random() * 300 + 1000;
		stip = 1000;
		return stip;
	}

	// pausa
	public static void Pause() {
		System.out.println("Clicca per continuare");
		new Scanner(System.in).nextLine();
	}

	// main
	public static void main(String[] args) {

		presentazione();

		final int DIM = 2;

		boolean ok = false;
		double stip = 0;
		double soldi = 0;
		double contoBanca = 0;
		double contoPortafoglio = 100;
		int n = 0, temp = 0;
		int mese = 1;
		int anno = 2024;
		double d = 0;

		// menu
		int scelta;
		do {

			if (ok) {
				temp++;
				if (temp == n) {
					if (d > 0) {

						System.out.println("Congratulazioni il tuo investimento e' andato a buon fine!");
						System.out.println("Hai guadagnato: " + (d-soldi) + "€");
						System.out.println("Conto in banca prima: " + contoBanca);
						contoBanca = contoBanca + (d-soldi);
						System.out.println("Conto in banca dopo: " + contoBanca);
						d = 0;
						n = 0;
						temp=0;
						ok=false;
					} // if
					else if (d < 0) {
						System.out.println("Mi dispiace il tuo investimento non e' andato a buon fine!");
						System.out.println("Hai perso: " + (d) + "€");
						System.out.println("Conto in banca prima: " + contoBanca);
						contoBanca = contoBanca + d;
						System.out.println("Conto in banca dopo: " + contoBanca);
						d = 0;
						n = 0;
						temp=0;
						ok=false;
					} // else
				} // if
			} // if

			do {
				System.out.println("Data: " + mese + " " + anno);
				menu();
				Scanner tastiera = new Scanner(System.in);
				try {
					scelta = tastiera.nextInt();
				} catch (Exception e) {
					System.out.println("Input non valido\n");
					scelta = -1;
				}

			} while ((scelta < 0) || (scelta > 6));
			switch (scelta) {
			case 1: {

				System.out.println("Il tuo conto in banca e' di : " + contoBanca + "€");

				Pause();
				break;
			}
			case 2: {

				System.out.println("Il tuo conto nel portafoglio e' di : " + contoPortafoglio + "€");
				Pause();
				break;
			}

			case 3: {
				ok = true;
				n = 0;

				if (contoBanca <= 0) {
					System.out.println("Non hai i soldi necessari per investire!");
				} // if
				else {
					Scanner tastiera = new Scanner(System.in);
					// System.out.print("Quanti soldi vuoi investire: ");
					// soldi = tastiera.nextDouble();
					boolean vero;
					do {
						vero = true;
						System.out.print("Quanti soldi vuoi investire: ");
						String double1 = tastiera.nextLine();

						double1.trim();
						try {

							soldi = (double) Double.parseDouble(double1);

						} catch (NumberFormatException e) {
							System.out.print("ERRORE!! FORMATO NON DOUBLE!!\n");
							vero = false;
						}
					} while (!vero);

					while (soldi > contoBanca) {
						System.out.println("Importo troppo alto\n");
						System.out.print("Quanti soldi vuoi investire: ");
						soldi = tastiera.nextDouble();
					} // while

					int scelta1;
					
					do {
						menuDurata();
						String intero = tastiera.nextLine();

						intero.trim();
						try {

							scelta1 = (int) Integer.parseInt(intero);

						} catch (NumberFormatException e) {
							System.out.print("ERRORE!! FORMATO NON INTERO!!\n");
							scelta1=-1;
						}
					} while ((scelta1 < 1) || (scelta1 > 3));
					switch (scelta1) {

					case 1: {
						n = 4;
						d = Investi(soldi);					
						break;
					}

					case 2: {
						n = 7;
						d = Investi(soldi);
						break;
					}

					case 3: {
						n = 13;
						d = Investi(soldi);
						break;
					}

					}
				} // else
				Pause();
				break;
			} // case 3

			case 4: {
				Scanner tastiera = new Scanner(System.in);
				double dep = 0;
				if (contoPortafoglio <= 0) {
					System.out.println("Non hai soldi nel portafoglio!");
				} else {
					boolean vero;
					do {
						vero = true;
						System.out.print("Quanti soldi vuoi depositare: ");
						String double1 = tastiera.nextLine();

						double1.trim();
						try {

							dep = (double) Double.parseDouble(double1);

						} catch (NumberFormatException e) {
							System.out.print("ERRORE!! FORMATO NON DOUBLE!!\n");
							vero = false;
						}
					} while (!vero);

					while (dep > contoPortafoglio) {
						System.out.println("Importo troppo alto!");
						System.out.println("Il tuo portafoglio contiene : " + contoPortafoglio);
						System.out.print("Quanti soldi vuoi depositare: ");
						dep = tastiera.nextDouble();
					} // while
					contoBanca += dep;
					contoPortafoglio -= dep;
					System.out.println("Soldi depositati con successo!");
				}

				Pause();
				break;
			} // case

			case 5: {
				Scanner tastiera = new Scanner(System.in);
				double pre = 0;
				boolean vero;
				do {
					vero = true;
					System.out.print("Quanti soldi vuoi prelevare : ");

					String double1 = tastiera.nextLine();
					try {

						pre = (double) Double.parseDouble(double1);

					} catch (NumberFormatException e) {
						System.out.print("ERRORE!! FORMATO NON DOUBLE!!\n");
						vero = false;
					}
				} while (!vero);

				while (pre > contoBanca) {
					System.out.println("Importo troppo alto!");
					System.out.println("Il tuo conto in banca contiene : " + contoBanca);
					System.out.print("Quanti soldi vuoi prelevare : ");
					pre = tastiera.nextDouble();
				} // while
				contoBanca -= pre;
				contoPortafoglio += pre;
				System.out.println("Soldi prelevati con successo!");

				Pause();
				break;
			}

			case 6: {
				int tempo[] = avanzaTempo(mese, anno);

				mese = tempo[0];
				anno = tempo[1];

				System.out.println(mese + " " + anno + "\n");
				contoPortafoglio = contoPortafoglio + stipendio(stip, contoPortafoglio);
				System.out.println("Stipendio del mese " + (mese) + ": " + stipendio(stip, contoPortafoglio));
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
