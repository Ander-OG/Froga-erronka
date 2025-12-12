package Aplikazioa;

import java.util.Scanner;

public class menuLangilea {
	public static void langileaMenua() {
		Scanner sc = new Scanner(System.in);
		int kudeaketak3 = 0;
		do {
			System.out.println("\nHauek dira kudeatzeko aukerak:");
			System.out.println("1. Bezeroak kudeatu");
			System.out.println("2. Erosketak kudeatu");
			System.out.println("3. Produktuak kudeatu");
			System.out.println("4. Salmentak kudeatu");
			System.out.println("5. Soporteak kudeatu");
			System.out.println("6. Irten");
			System.out.print("Zure aukera: ");
			kudeaketak3 = sc.nextInt();

			
			switch (kudeaketak3) {
			case 1:
				Bezeroak bezeroa = new Bezeroak();
				bezeroa.bezeroaKudeatu(sc);
				break;
			case 2:
				Erosketak erosketa = new Erosketak();
				erosketa.erosketaKudeatu3(sc);
				break;
			case 3:
				Produktuak produktua = new Produktuak();
				produktua.produktuaKudeatu(sc);
				break;
			case 4:
				Salmentak salmenta = new Salmentak();
				salmenta.salmentaKudeatu3(sc);
				break;
			case 5:
				Soporteak soportea = new Soporteak();
				soportea.soporteaKudeatu(sc);
				break;
			case 6:
				System.out.println("Agur!");
				sc.close();
				break;
			default:
				System.out.println("Aukera okerra sartu duzu. Saiatu berriro!");
			}
		} while (kudeaketak3 != 6);
		

	}
}
