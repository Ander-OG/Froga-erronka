package Aplikazioa;

import java.util.Scanner;

public class menuKudeatzailea {
	public static void kudeatzaileaMenua() {
		Scanner sc = new Scanner(System.in);
		int kudeaketak = 0;
		do  {
			System.out.println("\nHauek dira kudeatzeko aukerak:");
			System.out.println("1. Bezeroak kudeatu");
			System.out.println("2. Erosketak kudeatu");
			System.out.println("3. Hornitzaileak kudeatu");
			System.out.println("4. Langileak kudeatu");
			System.out.println("5. Produktuak kudeatu");
			System.out.println("6. Salmentak kudeatu");
			System.out.println("7. Soporteak kudeatu");
			System.out.println("8. Irten");	    
			System.out.print("Zure aukera: ");
			kudeaketak = Aplikazioa.sc.nextInt();
            Aplikazioa.sc.nextLine();
			
			switch (kudeaketak) {
			case 1:
				Bezeroak bezeroa = new Bezeroak();
				bezeroa.bezeroaKudeatu(sc);
				break;
			case 2:
				Erosketak erosketa = new Erosketak();
				erosketa.erosketaKudeatu(sc);
				break;
			case 3:
				Hornitzaileak hornitzailea = new Hornitzaileak();
				hornitzailea.hornitzaileaKudeatu(sc);
				break;
			case 4:
				Langileak langilea = new Langileak();
				langilea.langileaKudeatu(sc);
				break;
			case 5:
				Produktuak produktua = new Produktuak();
				produktua.produktuaKudeatu(sc);
				break;
			case 6:
				Salmentak salmenta = new Salmentak();
				salmenta.salmentaKudeatu(sc);
				break;
			case 7:
				Soporteak soportea = new Soporteak();
				soportea.soporteaKudeatu(sc);
				break;
			case 8:
				System.out.println("Agur!");
				sc.close();
				break;
			default:
				System.out.println("Aukera okerra sartu duzu. Saiatu berriro!");
			}
		} while (kudeaketak != 8);
		

	}
}

	
	




