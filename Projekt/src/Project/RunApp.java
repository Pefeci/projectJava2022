/**
 * 
 */
package Project;
import Project.Database.Databaze;

/**
 * @author Pefeci
 *
 */


public class RunApp {
	public static void main(String[] args) {
		int choice = 0;
		boolean run = true;
		int indexID = 1;
		int tempIndex = indexID;
		float znamka = 0;
		Databaze databaze = new Databaze();
		
		//DEBUG nacteni prednastavenych studentu
        databaze.setIndex(indexID);
        indexID = databaze.debugDatabaze(indexID);
		System.out.println("Uspesne byli pridani studenti");
        databaze.vypis();
        indexID++;
		//KONEC DEBUGU pomucky
		
		while(run) {
	        System.out.println(System.lineSeparator());
	        System.out.println("Vyberte požadovanou èinnost:");
	        System.out.println("1 ... Pøidat studenta do databaze");
	        System.out.println("2 ... Udìlit studentu prùmìr ");
	        System.out.println("3 ... Vymazat studenta");
	        System.out.println("4 ... Vypsat informace o studentovi");
	        System.out.println("5 ... Spusteni studenta del ID");
	        System.out.println("6 ... Vypsat studenty dle abecedy");
	        System.out.println("7 ... Vpis prumerne znamky pro dany obor");
	        System.out.println("8 ... Vypis poètu studentu v oborech");
	        System.out.println("9 ... Naètení informaci ze souboru db.csv");
	        System.out.println("10 .. Zapis informaci do souboru db.csv");
	        System.out.println("11 .. Ulozeni Databaze do SQL");
	        System.out.println("12 .. Nacteni Databaze z SQL");
	        System.out.println("13 .. Ukonèení aplikace");

	        databaze.setIndex(indexID);
	        
	        choice = UserInput.celaCislaInput();
	        while (choice<1 || choice>14) {System.out.println("Zadej prosím èíslo od 1 do 13: ");
	        	choice = UserInput.celaCislaInput();
	        }
	   
	        switch (choice) {
	        	case 1:
	        		databaze.setStudent();
	        		databaze.vypis();
	        		indexID ++;
	        		break;
	        	case 2:
	        		do {
	        			System.out.println("ID a prijmeni");
	        			databaze.vypisIDaJmena();
		        		System.out.println("Zadej existujicí ID studenta, kteremu chces pridelit znamku: ");
		        		tempIndex = UserInput.celaCislaInput(); 
		        		znamka = funkceProMain.zadejPrumer();
	        		}while(!databaze.setZnamku(tempIndex,znamka));
	        		break;
	        	case 3:
	        		do {
	        			System.out.println("ID a prijmeni");
	        			databaze.vypisIDaJmena();
		        		System.out.println("Zadej existujicí ID studena, ktereho chces vymazat: ");
		        		tempIndex = UserInput.celaCislaInput(); 
		        		
	        		}while(!databaze.deleteStudent(tempIndex));
	        		break;
	        	case 4:
	        		do {
	        			System.out.println("ID a prijmeni");
	        			databaze.vypisIDaJmena();
		        		System.out.println("Zadej existujicí ID studena o kterem chces ziskat informace: ");
		        		tempIndex = UserInput.celaCislaInput(); 
		        		
	        		}while(!databaze.vypisStudenta(tempIndex));
	        		break;
	        	case 5:
	        		do {
	        			System.out.println("ID a prijmeni");
	        			databaze.vypisIDaJmena();
		        		System.out.println("Zadej existujicí ID studena u ktereho chces spustit vlastnost: ");
		        		tempIndex = UserInput.celaCislaInput(); 
		        		
	        		}while(!databaze.spusteniStudenta(tempIndex));
	        		
	        		break;
	        	case 6:
	        		databaze.vypisDleAbecedy();
	        		break;
	        	case 7:
	        		databaze.prumerZnamkaOboru();
	        		break;
	        	case 8:
	        		databaze.pocetStudentuVOboru();
	        		break;
	        	case 9:
	        		databaze.cteniZeSouboru();
	        		indexID = databaze.lastID();
	        		System.out.println(indexID);
	        		break;
	        	case 10:
	        		databaze.ulozeniDoSouboru();
	        		break;
	        	case 11:
	        		databaze.insertDatabaseToSQL();
	        		break;
	        	case 12:
	        		databaze.loadDatabaseFromSQL();
	        		break;
	        	case 13:
	        		run = false;
	        		break;
	        	case 14:
	        		databaze.deleteAllInDatabaseSQL();
	        		break;
	        	default:
	        		run = false;
	        		break;
	        }   
		}
	}
}
