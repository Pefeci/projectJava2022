package Project;


public class funkceProMain {
	
	// Funkce je zde aby zbyteène nezavazela v run app
	//FUNKCE PRO ZADANI PRUMERU
	static float zadejPrumer() {
		System.out.println("Zadej známku: ");
		float znamka = UserInput.cislaInput();
		while (znamka > 5 || znamka < 1) {System.out.println("Zadej reálné èíslo od 1 do 5: ");
			znamka = UserInput.cislaInput();
		}
		return znamka;
	}



}
