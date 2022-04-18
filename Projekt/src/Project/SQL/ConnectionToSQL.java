package Project.SQL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionToSQL {
	private static volatile Connection dbConnection;
	public ConnectionToSQL() {}

	//PRIPOJENI K SQL
	public static Connection getConnection() {
	    if (dbConnection == null) {
	        synchronized (ConnectionToSQL.class) {
	          if (dbConnection == null) {
	            try {
	              Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	              dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdatabase","root","12345678");
	            } catch (SQLException | ClassNotFoundException e) {
	            	System.out.println("Tady je chyba");
	            	e.printStackTrace(); // log it
	            }
	          }
	        }
	      }
	      return dbConnection;
		
	}
	
	//ODPOJENI OD SQL
	public static void closeConnection() {
		    try {
		      dbConnection.close();
		    } catch (SQLException e) {
		      e.printStackTrace();
		    }
		  }
	
	
	
	
}
