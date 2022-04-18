package Project.Database;

public class KomStudent extends Student {

	//KONSTRUKTOR
	public KomStudent(String jmeno, String prijmeni, int rokNarozeni, int index) {
		super(jmeno, prijmeni, rokNarozeni, index);
	}

	//ABILITA STUDENTA
	@Override
	public void studentSchopnost() {
		boolean prestupRok = false;
		if (rokNarozeni % 4 == 0) {
			if (rokNarozeni % 100 == 0) {
				if (rokNarozeni % 400 == 0) {prestupRok = true;}
			prestupRok = false;	
			}else prestupRok = true;
			prestupRok = true;
		}
		if (prestupRok) {System.out.print("\n" + Prijmeni +" "+ Jmeno + " se narodil(a) v pøestupném roce ");}else{System.out.print("\n" + Prijmeni +" "+ Jmeno + " se narodil(a) v nepøestupném roce " );}
		String zverokruh = null;
		
		if (rokNarozeni % 12 == 0)zverokruh = "Krysa";
		if (rokNarozeni % 12 == 1)zverokruh = "Buvol";
		if (rokNarozeni % 12 == 2)zverokruh = "Tygr";
		if (rokNarozeni % 12 == 3)zverokruh = "Zajic";
		if (rokNarozeni % 12 == 4)zverokruh = "Drak";
		if (rokNarozeni % 12 == 5)zverokruh = "Had";
		if (rokNarozeni % 12 == 6)zverokruh = "Kun";
		if (rokNarozeni % 12 == 7)zverokruh = "Koza";
		if (rokNarozeni % 12 == 8)zverokruh = "Opice";
		if (rokNarozeni % 12 == 9)zverokruh = "Kohout";
		if (rokNarozeni % 12 == 10)zverokruh = "Pes";
		if (rokNarozeni % 12 == 11)zverokruh = "Vepr";
		System.out.print("a ve znameni " + zverokruh);
		

	}

}
