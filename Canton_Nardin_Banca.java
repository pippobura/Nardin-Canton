

	import java.util.Scanner;
	public class Canton_Nardin_Banca{
		
		public static void presentazione() {
			
			System.out.println("BENVENUTO/A NELLA BANCA\n");
			System.out.println("I tuoi soldi saranno divisi tra portafoglio e banca:\n"
					+ "il tuo stipendio che verra' dato mensilmente, \n"
					+ "andra' nel tuo conto in banca che parte inizialmente con 0 euro, \n"
					+ "e ogni mese 100 euro del tuo stipendio andranno nel tuo portafoglio, \n"
					+ "che parte inizialmente con 100 euro.\n");
		}
		//menu
		public static void menu() {
			
			
			System.out.println("*****MENU*****");
			System.out.println("1. Visualizza conto in banca");
			System.out.println("2. Visualizza conto nel portafoglio\n");
			System.out.println("3. Investi");// mettere sotto menu con quanto tempo vuoi investire e con quanto rischio
			System.out.println("4. Avanza nel tempo\n");
			
			System.out.println("0. ESCI\n");
			
			System.out.print("Fai la tua scelta (0...8) : ");
		}
		
		//sotto menu investimento durata
		public static void menuDurata() {
			
			
			System.out.println("\n1. Investi in breve durata");
			System.out.println("2. Investi in media durata");
			System.out.println("3. Investi in lunga durata\n");
			
			System.out.print("Fai la tua scelta (0...3) : ");
		}
		//sotto menu investimento rischio
		public static void menuRischio() {
					
					
			System.out.println("\n1. Investimenti di basso rischio e basso guadagno;");
			System.out.println("2. Investimenti di medio rischio e medio guadagno;");
			System.out.println("3. Investimenti di alto rischio e alto guadagno.\n");
					
			System.out.print("Fai la tua scelta (0...3) : ");
		}

		
		
		
		//pausa
		public static void Pause() {
			System.out.println("Clicca per continuare");
			new Scanner(System.in).nextLine();
			
		}
		//main
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			
			presentazione();
			
			final int DIM=10;
			
	        int contoBanca=0;
			int contoPortafoglio=100;
			
			int n=0;
			
			
			//menu
			int scelta;
			do {
				do {
					menu();
					Scanner tastiera=new Scanner(System.in);
					try {
						scelta= tastiera.nextInt();
					}catch(Exception e){
						System.out.println("Input non valido\n");
						scelta = -1;
					}
					
				}while((scelta< 0) || (scelta > 8));
				switch (scelta) {
					case 1 : {
						
						System.out.println("Il tuo conto in bamca e' di : " + contoBanca + "€");
						
						Pause();
						break;
					}
					case 2 : {
						
						System.out.println("Il tuo conto nel portafoglio e' di : " + contoPortafoglio + "€");
						Pause();
						break;
					}
					case 3 : {
						
						Pause();
						break;
					}
					
					case 4 : {
						
						Pause();
						break;
					}
					case 5 : {
						
						Pause();
						break;
					}
					
					case 0:{
						System.out.println("Arrivedorci\n");
						break;
					}	
					default : System.out.println("Input non valido");
				}
			}while(scelta!= 0);
			
		}

	}


