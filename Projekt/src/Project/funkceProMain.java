package Project;


public class funkceProMain {
	
	// Funkce je zde aby zbyte�ne nezavazela v run app
	//FUNKCE PRO ZADANI PRUMERU
	static float zadejPrumer() {
		System.out.println("Zadej zn�mku: ");
		float znamka = UserInput.cislaInput();
		while (znamka > 5 || znamka < 1) {System.out.println("Zadej re�ln� ��slo od 1 do 5: ");
			znamka = UserInput.cislaInput();
		}
		return znamka;
	}



}
