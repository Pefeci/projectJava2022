package Project.Database;

public abstract class Student {
	String Jmeno;
	String Prijmeni;
	int rokNarozeni;
	private int ID;
	float znamka;
	

	//KONSTRUKTOR
	public Student(String jmeno, String prijmeni, int rokNarozeni, int index) {
		this.Jmeno = jmeno;
		this.Prijmeni = prijmeni;
		this.rokNarozeni = rokNarozeni;
		this.ID = index;
	}
	
	
	//SETTERY:
	public void setZnamku(float znamka) {
		this.znamka = znamka;
	}
	public void setJmeno(String jmeno) {
		this.Jmeno = jmeno;
	}
	public void setPrijmeni(String prijmeni) {
		this.Prijmeni = prijmeni;
	}
	public void setRokNarozeni(int rokNarozeni) {
		this.rokNarozeni = rokNarozeni;
	}
	public void setStudentID(int index) {
		this.ID = index;
	}
	
	//GETTERY:
	public float getZnamku() {
		return znamka;
	}
	public String getJmeno() {
		return Jmeno;
	}
	public String getPrijmeni() {
		return Prijmeni;
	}
	public int getRokNarozeni() {
		return rokNarozeni;
	}
	public int getStudentID() {
		return ID;
	}

	//ABSTRAKT METODA PRO ABILITY:
	public abstract void studentSchopnost();
	
	
	
	
	
	
}
