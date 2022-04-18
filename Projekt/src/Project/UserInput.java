package Project;

import java.util.Scanner;

public class UserInput {
	static Scanner sc = new Scanner(System.in);
	
	//NACTENI INT OD UZIVATELE
	public static int celaCislaInput() {
		int cislo = 0;
		while (!sc.hasNextInt()) {
			System.out.println("zadejte prosim cele cislo ");
			sc.next();
		}
		cislo = sc.nextInt();
		return cislo;
	}
	
	//NACTENI FLOAT OD UZIVATELE
	static float cislaInput() {
		float cislo = 0;
		while (!sc.hasNextFloat()) {
			sc.next();
			System.out.println("zadejte cele nebo desetinne cislo ");
		}
			cislo = sc.nextFloat();
		return cislo;
	}
	
	//NACTENI STRINGU OD UZIVATELE
	public static String stringInput() {
		String input;
		input = sc.nextLine();
		return input;
	}
	
	
	
	
}
