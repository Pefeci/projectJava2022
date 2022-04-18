package Project.SQL;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Project.Database.HumStudent;
import Project.Database.KomStudent;
import Project.Database.Student;
import Project.Database.TechStudent;

public class SQLprace {

	//KOSTRUKTOR
	public SQLprace() {}
	
	//ULOZENI DAT DO DATABAZE SQL
	public void insertToSQL(int id, String jmeno, String prijimeni, int narozeni, float znamka, int role) {
		Connection conn = ConnectionToSQL.getConnection();
		String sql = "INSERT INTO STUDENT" + "(id, jmeno, prijimeni,narozeni,znamka,role)" + "VALUES(?,?,?,?,?,?)";
		try(PreparedStatement prStmt = conn.prepareStatement(sql)) {
			prStmt.setInt(1,id);
			prStmt.setString(2,jmeno);
			prStmt.setString(3, prijimeni);
			prStmt.setInt(4,narozeni);
			prStmt.setFloat(5,znamka);
			prStmt.setInt(6,role);
			prStmt.executeUpdate();
			System.out.println("Uzivatel s ID: "+ id + " byl pøidan do sql databaze");
		} catch (SQLException e) {
			System.out.println("Uživatel s ID: "+ id + " už je v databazi");
		}

	}
	
	//NACTENI DAT Z DATABAZE SQL
	public Student loadFromSQL(int id) {
		Student student;
		Connection conn = ConnectionToSQL.getConnection();
		String sql = "SELECT id, jmeno, prijimeni, narozeni, znamka,role FROM student WHERE id = ?";
		try(PreparedStatement prStmt = conn.prepareStatement(sql)) {
			prStmt.setInt(1,id);
			ResultSet rs = prStmt.executeQuery();
			if (rs.next()) {
				if(rs.getInt("role") == 0) {
					student = new TechStudent(rs.getString("jmeno"),rs.getString("prijimeni"),rs.getInt("narozeni"),id);
					student.setZnamku(rs.getFloat("znamka"));
					return (TechStudent)student;
				}if(rs.getInt("role") == 1) {
					student = new HumStudent(rs.getString("jmeno"),rs.getString("prijimeni"),rs.getInt("narozeni"),id);
					student.setZnamku(rs.getFloat("znamka"));
					return (HumStudent)student;
				}if(rs.getInt("role") == 2) {
					student = new KomStudent(rs.getString("jmeno"),rs.getString("prijimeni"),rs.getInt("narozeni"),id);
					student.setZnamku(rs.getFloat("znamka"));
					return (KomStudent)student;
				}
			}
		} catch (Exception e) {
			System.out.println("HEntu?");
			e.printStackTrace();
		}
		return null;
	}
	
	//ZJISTENI POCTU PRVKU V DATABAZI SQL
	public int countItemsSQL() {
		int count = 0;
		Connection conn = ConnectionToSQL.getConnection();
		String sql = "SELECT count(*) FROM student";
		try(PreparedStatement prStmt = conn.prepareStatement(sql)) {
			ResultSet rs = prStmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return count;
	} 
	

	//DEBUG POMUCKA PRO VYMAZANI SQL DATABAZE
	public void deleteAllfromSQL() {
		 Connection conn = ConnectionToSQL.getConnection();
		   String userToDelete = "DELETE FROM student";
		    try (PreparedStatement prStmt = conn.prepareStatement(userToDelete);) {
		      int rowsDeleted = prStmt.executeUpdate();
		      System.out.println("Vaším pøíkazem byl vymazán následující poèet uživatelù: " + rowsDeleted);
		    } catch (SQLException e) {
		      e.printStackTrace();
		    }
		
		
	}


}
