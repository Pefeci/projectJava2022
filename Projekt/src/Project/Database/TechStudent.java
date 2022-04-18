package Project.Database;

public class TechStudent extends Student{

	//KONSTRUKTOR
	public TechStudent(String jmeno, String prijmeni, int rokNarozeni, int index) {
		super(jmeno, prijmeni, rokNarozeni, index);
	}

	
	
	
	// ABILITA STUDENTA
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
		if (prestupRok) {System.out.println(Prijmeni +" "+ Jmeno + " se narodil(a) v pøestupném roce");}else{System.out.println(Prijmeni + " " + Jmeno + " se nenarodil(a) v pøestupném roce" );}
		
	}



}
