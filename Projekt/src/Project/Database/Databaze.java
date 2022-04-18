package Project.Database;
import Project.SQL.SQLprace;
import Project.SQL.ConnectionToSQL;
import Project.UserInput;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class Databaze {

	//KONSTRUKTOR
	public Databaze() {}
	
	
	//PRETYPOVANI ATRIBUTU
	private String indexToString(int index) {
		int toStringIndex = index;
		Integer temp = new Integer(toStringIndex);
		return temp.toString();
	}

	private static int stringToInt(String cislo) {
		int num = 0;
		try {
			num = Integer.parseInt(cislo);	
		}catch(NumberFormatException e) {e.printStackTrace();}
		return num;
	} 

	private static float stringToFloat(String Cislo) {
		float num;
		num = Float.parseFloat(Cislo);
		return num;
	}
	
	
	//POMOCNE
	public Student getStudent(int index)
	{
		String stringIndex = indexToString(index);
		if (hashDatabaze.containsKey(stringIndex)) {
			return hashDatabaze.get(stringIndex);
		}else {
			return null;
		}
		
	}
	
	public void vypisIDaJmena() {
		Set <String> klice = hashDatabaze.keySet();
		for (String klic: klice) {
			System.out.println(klic +": " + hashDatabaze.get(klic).getPrijmeni());
		}
		
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public int lastID() {
		int num = hashDatabaze.size();
		return num;
	}
	
	
	//1. PRVNI FUKNCE PRIDANI STUDENTA
	public void setStudent() {
		String stringIndex = indexToString(index);
		int choice = 0;
		String jmeno = null;
		String prijimeni = null;
		int rok = 0;
		UserInput ui = new UserInput();
		System.out.println("Vyber studijní obor: ");
		System.out.println("1 .. Technický obor");
		System.out.println("2 .. Humanitární obor");
		System.out.println("3 .. Kombinovaný obor");
		try {
			choice = ui.celaCislaInput();
			while (choice < 1 || choice > 3) {System.out.println("Zadej prosím èíslo od 1 do 3");
				choice = ui.celaCislaInput();
			}
			System.out.println("Zadej jmeno, pøijímení, rok narození: ");
			jmeno = ui.stringInput();
			prijimeni = ui.stringInput();
			rok = ui.celaCislaInput();
		} catch (Exception e) {
			System.out.println("¨Nastala vyjímka typu: " + e.toString());
			setStudent();
		}

		switch (choice) {
			case 1:
				hashDatabaze.put(stringIndex, new TechStudent(jmeno, prijimeni, rok, index) );
				break;
			case 2:
				hashDatabaze.put(stringIndex, new HumStudent(jmeno, prijimeni, rok, index) );
				break;
			case 3:
				hashDatabaze.put(stringIndex, new KomStudent(jmeno, prijimeni, rok, index) );
				break;
		}
	}	
	
	//2. DRUHA FUNKCE UDELENI ZNAMKY
	public boolean setZnamku(int ID, float prumer) {
		String stringID = indexToString(ID);	
			if (hashDatabaze.containsKey(stringID)) {
				hashDatabaze.get(stringID).setZnamku(prumer);		
			return true;
			}
			return false;
		}
		
	//3. TRETI FUNKCE VYMAZANI STUDENTA
	public boolean deleteStudent(int ID) 	{
		String stringID = indexToString(ID);
		if (hashDatabaze.containsKey(stringID)) {
			hashDatabaze.remove(stringID);
			System.out.println("Student byl odebran");
			return true;
		}else {
			System.out.println("Student nebyl nalezen");
			return false;
		}	
	}
	
	//4. CTVRTA FUNKCE VYPIS O STUDENTOVI
	public boolean vypisStudenta(int ID) {
		String stringID = indexToString(ID);	
		
		if (hashDatabaze.containsKey(stringID)) {
			System.out.print("\nID: " + hashDatabaze.get(stringID).getStudentID() + " Jmeno: "+ hashDatabaze.get(stringID).getJmeno()+" prijimeni: "+hashDatabaze.get(stringID).getPrijmeni() +" rok narození: " + hashDatabaze.get(stringID).getRokNarozeni());
			if(hashDatabaze.get(stringID).getZnamku() == 0) {
				System.out.print(" Znamka: Beze znamky");
			}else {System.out.print(" Znamka: " + hashDatabaze.get(stringID).getZnamku());}	
			if(hashDatabaze.get(stringID) instanceof TechStudent) {
				System.out.print(" Obor: Technický");
			}else if(hashDatabaze.get(stringID) instanceof HumStudent) {
				System.out.print(" Obor: Humanitární");
			}else if(hashDatabaze.get(stringID) instanceof KomStudent) {
				System.out.print(" Obor: Kombinovany");
			}	
		return true;
		}
		return false;
	}

	//5. PATA FUNKCE SPUSTENI ABILITY STUDENTA
	public boolean spusteniStudenta(int ID) {
	String stringID = indexToString(ID);
	if (hashDatabaze.containsKey(stringID)) {
		hashDatabaze.get(stringID).studentSchopnost();
		return true;
	}else {
		return false;
		}
	}
	
	//6. SESTA FUNKCE VYPIS STUDENTU DLE ABECEDY
	public void vypisDleAbecedy() {
		TreeMap<String, Student> treeMap = new TreeMap<>();
		Set<String> klice = hashDatabaze.keySet();
		for (String klic : klice) {
			treeMap.put(hashDatabaze.get(klic).getPrijmeni(), hashDatabaze.get(klic));	
		}
		Set<String> treeKlice = treeMap.keySet();
		for(String treeKlic : treeKlice) {
			System.out.print("\n"+treeMap.get(treeKlic).getStudentID() + ", " + treeMap.get(treeKlic).getPrijmeni() + ", " + treeMap.get(treeKlic).getJmeno() + ", " + treeMap.get(treeKlic).getRokNarozeni() + ", ");
			if(treeMap.get(treeKlic).getZnamku()==0) {
				System.out.print("Nema znamku");
			}else {System.out.print(", " + treeMap.get(treeKlic).getZnamku());}	
		}	
	}
		
	//7. SEDMA FUNKCE VYPIS PRUMERNE ZNAMKY V OBORU
	public void prumerZnamkaOboru() {
		float techPrum = 0, humPrum = 0, komPrum = 0;
		int techCount = 0,humCount = 0, komCount = 0;
		Set<String> Klice = hashDatabaze.keySet();
		for(String klic : Klice) {
			if (hashDatabaze.get(klic) instanceof TechStudent) {
				techCount++;techPrum += hashDatabaze.get(klic).getZnamku();
			}
			if (hashDatabaze.get(klic) instanceof HumStudent) {
				humCount++;humPrum += hashDatabaze.get(klic).getZnamku();
			}
			if (hashDatabaze.get(klic) instanceof KomStudent) {
				komCount++;komPrum += hashDatabaze.get(klic).getZnamku();
			}
		}
		if(techCount != 0) {
			techPrum = (techPrum / (float)techCount);
			System.out.printf("\nPrumer znamky Technickeho oboru je: %.2f", techPrum); 
		}
		if(humCount != 0) {
			humPrum = (humPrum / (float)humCount);
			System.out.printf("\nPrumer znamky Humanitniho oboru je: %.2f", humPrum); 
		}
		if(komCount != 0) {
			komPrum = (komPrum / (float)komCount);
			System.out.printf("\nPrumer znamky Kombinovaneho oboru je: %.2f", komPrum); 
		}
		return;
		
	}	
	
	//8. OSMA FUNKCE POCET STUDENTU V OBORU
	public void pocetStudentuVOboru() {
		int techCount = 0, humCount = 0, komCount = 0;
		
		Set<String> klice = hashDatabaze.keySet();
		for (String klic : klice) {
			if(hashDatabaze.get(klic) instanceof TechStudent) {
				techCount++;
			}else if(hashDatabaze.get(klic) instanceof HumStudent) {
				humCount++;
			}else if(hashDatabaze.get(klic) instanceof KomStudent) {
				komCount++;
			}
		}
		System.out.println("V databazi je studentu kteri chodi na technický obor: " + techCount + ", humanitarni obor: " + humCount + ", kombinovaný obor: " + komCount);
	}
	
	//9. DEVATA FUNKCE NACTENI DAT ZE SOUBORU
	public void cteniZeSouboru() {
		Path pathToFile = Paths.get(filename);
		File file = new File(filename);
		if (file.exists()) {
			try (BufferedReader br = Files.newBufferedReader(pathToFile,StandardCharsets.US_ASCII)){
				String line = br.readLine();
				while (line != null) {
					String[] atributy = line.split(",");
					Student student = prepisStudentaZeSouboru(atributy);
					hashDatabaze.put(atributy[0], student);
					line = br.readLine();
					
				}
			} catch(IOException e) {e.printStackTrace();}
				System.out.println("Data ze souboru byli uspesne nahrany do databaze");
		}else {
			System.out.println("Soubor neexistuje nejdøív nahrajte data do souboru");
		}	
	}
	private static Student prepisStudentaZeSouboru(String[] dataZeSouboru) {
		Student student;
		
		int ID = stringToInt(dataZeSouboru[0]);
		String jmeno = dataZeSouboru[1];
		String prijimeni = dataZeSouboru[2];
		int rokNarozeni = stringToInt(dataZeSouboru[3]);
		float znamka = stringToFloat(dataZeSouboru[4]);
		
		if (stringToInt(dataZeSouboru[5]) == 0 ) {
			student = new TechStudent(jmeno,prijimeni,rokNarozeni,ID);
			student.setZnamku(znamka);
			return (TechStudent) student;
	
		}if (stringToInt(dataZeSouboru[5]) == 1) {
			student = new HumStudent(jmeno,prijimeni,rokNarozeni,ID);
			student.setZnamku(znamka);		
			return (HumStudent) student;
		}if (stringToInt(dataZeSouboru[5]) == 2) {
			student = new KomStudent(jmeno,prijimeni,rokNarozeni,ID);
			student.setZnamku(znamka);
			return (KomStudent) student;
		}
	return null;	
	}

	
	//10. DESATA FUNKCE ULOZENI DAT DO SOUBORU
	public void ulozeniDoSouboru() {
		int type = -1;
		File file = new File(filename);
		try (FileWriter filewriter = new FileWriter(filename, false)) {
			BufferedWriter bwr = new BufferedWriter(filewriter);
			if(!file.exists()){file.createNewFile(); System.out.println("Slozka vytvorena!");}
			Set <String> klice = hashDatabaze.keySet();
			for (String klic: klice) {
				if (hashDatabaze.get(klic) instanceof TechStudent) {
					type = 0;
				}if (hashDatabaze.get(klic) instanceof HumStudent) {
					type = 1;
				}if (hashDatabaze.get(klic) instanceof KomStudent) {
					type = 2;
				}if (type == -1) {
					System.out.println("Nastala chyba zápisu");
					return;
				}
			bwr.write(hashDatabaze.get(klic).getStudentID() +","+hashDatabaze.get(klic).getJmeno() +","+ hashDatabaze.get(klic).getPrijmeni() +"," + hashDatabaze.get(klic).getRokNarozeni() + "," + hashDatabaze.get(klic).getZnamku() +","  + type + "\n");
			System.out.println("Uspesne zapsano do souboru");
			}
			bwr.close();
			filewriter.close();
		}catch(IOException e){System.out.println("Error do dupy: " + e.toString());}
	}

	//11. JEDENACTA FUNKCE ULOZENI DO SQL DATABAZE
	public void insertDatabaseToSQL() {
		int role = 0;
		int intKlic;
		SQLprace praceSQL = new SQLprace();
		Set<String> klice = hashDatabaze.keySet();
		for (String klic : klice) {
			if(hashDatabaze.get(klic) instanceof TechStudent) {
				role = 0;
			}
			if(hashDatabaze.get(klic) instanceof HumStudent) {
				role = 1;
			}
			if(hashDatabaze.get(klic) instanceof KomStudent) {
				role = 2;
			}
			intKlic = stringToInt(klic);
			praceSQL.insertToSQL(intKlic, hashDatabaze.get(klic).getJmeno(),hashDatabaze.get(klic).getPrijmeni(),hashDatabaze.get(klic).getRokNarozeni(),hashDatabaze.get(klic).getZnamku(),role);	
			System.out.println("byl pøidán student " + hashDatabaze.get(klic).getPrijmeni());
		}
		this.index = praceSQL.countItemsSQL();
	}
	
	//12. DVANACTA FUNKCE NACTENI DAT Z SQL DATABAZE
	public void loadDatabaseFromSQL() {
		SQLprace praceSQL = new SQLprace();	
		String stringIndex;
		int numIndex =praceSQL.countItemsSQL();
		System.out.println("Poèet v SQL: " + numIndex);
		for(int i = 1; i <= numIndex;i++) {
			stringIndex = indexToString(i);
			hashDatabaze.put(stringIndex,(Student)praceSQL.loadFromSQL(i));
			System.out.println("Naèten student: " + hashDatabaze.get(stringIndex).getPrijmeni() + " S ID: " + i );
		}
	}


//Pomocne pro DEBUG
	public int debugDatabaze(int index) {
		String stringIndex = indexToString(index);
		hashDatabaze.put(stringIndex, new TechStudent("Petr", "Cirtek", 2002, index));
		setZnamku(index, (float)2.3);
		index++;
		stringIndex = indexToString(index);
		hashDatabaze.put(stringIndex, new TechStudent("Karel", "Omacka", 2001, index));
		setZnamku(index, (float)1.8);
		index++;
		stringIndex = indexToString(index);
		hashDatabaze.put(stringIndex, new TechStudent("Jiri", "Vocechul", 2000, index));
		setZnamku(index, (float)4.2);
		index++;
		stringIndex = indexToString(index);
		hashDatabaze.put(stringIndex, new HumStudent("Pavlina", "Oskurska", 1994, index));
		setZnamku(index, (float)3.5);
		index++;
		stringIndex = indexToString(index);
		hashDatabaze.put(stringIndex, new HumStudent("Adela", "Tvaruzkova", 2003, index));
		setZnamku(index, (float)2.1);
		index++;
		stringIndex = indexToString(index);
		hashDatabaze.put(stringIndex, new HumStudent("Johanka", "Purgertova", 2014, index));
		setZnamku(index, (float)1.2);
		index++;
		stringIndex = indexToString(index);
		hashDatabaze.put(stringIndex, new KomStudent("PavPetr", "Cirturska", 1902, index));
		setZnamku(index, (float)3.2);
		index++;
		stringIndex = indexToString(index);
		hashDatabaze.put(stringIndex, new KomStudent("Karela", "Omazkova", 2003, index));
		setZnamku(index, (float)2.7);
		index++;
		stringIndex = indexToString(index);
		hashDatabaze.put(stringIndex, new KomStudent("Jihanka", "Vocertova", 2014, index));
		setZnamku(index, (float)4.2);
		
		return index;
		
	}

	public void vypis(){
		Set <String> klice = hashDatabaze.keySet();
		for (String klic: klice) {
			System.out.println("ID: " + hashDatabaze.get(klic).getStudentID() +", Prijimeni: "+hashDatabaze.get(klic).getPrijmeni() +", Jmeno: "+ hashDatabaze.get(klic).getJmeno() +", Rok narození: " + hashDatabaze.get(klic).getRokNarozeni() );
		}
			
	}
	
	public void deleteAllInDatabaseSQL() {
		SQLprace praceSQL = new SQLprace();
		praceSQL.deleteAllfromSQL();
	}  
	
	
	

	private Map<String, Student> hashDatabaze = new HashMap<>();
	public int index;
	String filename = "db.csv";
}
