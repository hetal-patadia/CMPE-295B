package operation;
import java.sql.*;

public class DbOperation {
	
	/**
	 * @param args
	 */
	
	private static final String url = "jdbc:mysql://localhost:3306/";
	private static final String dbName = "conference_search";
	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String userName = "root";
	private static final String password = "root";
	
	protected static Connection getDBConnection() {
		Connection conn = null;
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url+dbName,userName,password);
		}
		catch (Exception e) {
			 
			System.out.println(e.getMessage());
 
		}
		return conn;
	}
	
	public static int insertRecordIntoUserTable(String firstname, String lastname, String email_id, String username, String password, String domain1, String domain2, String domain3) throws SQLException {
		Connection conn = null;
		Statement st = null;
		int val = 0;
		String insertData = "INSERT into user_profile (firstname, lastname, email_id, username, password, interest1, interest2, interest3) VALUES('" + firstname + "','" + lastname + "','" + email_id + "','" + username + "','" + password +  "','" + domain1 +  "','" + domain2 +  "','" + domain3 + "')";
		try{
			conn = getDBConnection();
			st = conn.createStatement();
			// execute insert SQL statement
			val = st.executeUpdate(insertData);
			//return val;
			/*if(val==1)
                System.out.print("Successfully inserted value\n");
			else 
				System.out.print("Error in insertion\n");*/
		}
		catch (SQLException e) {
			 
			System.out.println(e.getMessage());
 
		} finally {
 
			if (st != null) {
				st.close();
			}
 
			if (conn != null) {
				conn.close();
			}
			
		}
		return val;
	}
	
	private static void showRecordFromUserTable() throws SQLException {
		Connection conn = null;
		Statement st = null;
		try{
			conn = getDBConnection();
			st = conn.createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM  user_profile");
	          while (res.next()) {
	              int id = res.getInt("user_id");
	              String firstname = res.getString("firstname");
	              String lastname = res.getString("lastname");
	              String email_id = res.getString("email_id");
	              String username = res.getString("username");
	              String pass = res.getString("password");
	              System.out.println(id + "\t" + firstname + "\t" + lastname + "\t" + email_id + "\t" + username + "\t" + pass);
	          }
		}
		catch (SQLException e) {
			 
			System.out.println(e.getMessage());
 
		} finally {
 
			if (st != null) {
				st.close();
			}
 
			if (conn != null) {
				conn.close();
			}
 
		}
	}
	
	static void insertRecordIntoConfTable(String conf_name, String date, String location, String link, String keywords, String speakers) throws SQLException {
		Connection conn = null;
		Statement st = null;
		String insertData = "INSERT into conf_details (name, date, venue, link, keywords, speakers) VALUES('" + conf_name + "','" + date + "','" + location + "','" + link + "','" + keywords + "','" + speakers + "')";
		try{
			conn = getDBConnection();
			st = conn.createStatement();
			// execute insert SQL statement
			int val = st.executeUpdate(insertData);
			if(val==1)
                System.out.print("Successfully inserted values\n");
			else 
				System.out.print("Error in insertion\n");
		}
		catch (SQLException e) {
			 
			System.out.println(e.getMessage());
 
		} finally {
 
			if (st != null) {
				st.close();
			}
 
			if (conn != null) {
				conn.close();
			}
 
		}
	}
		
		
		static void insertRecordIntoTopicTable(String topic_name) throws SQLException {
			Connection conn = null;
			Statement st = null;
			String insertData = "INSERT into topics (topic_name) VALUES('" + topic_name + "')";
			try{
				conn = getDBConnection();
				st = conn.createStatement();
				// execute insert SQL statement
				int val = st.executeUpdate(insertData);
				
			}
			catch (SQLException e) {
				 
				System.out.println(e.getMessage());
	 
			} finally {
	 
				if (st != null) {
					st.close();
				}
	 
				if (conn != null) {
					conn.close();
				}
	 
			}

	}
	
	private static void showRecordFromConfTable() throws SQLException {
		Connection conn = null;
		Statement st = null;
		try{
			conn = getDBConnection();
			st = conn.createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM  conf_details");
	          while (res.next()) {
	              int id = res.getInt("conf_id");
	              String name = res.getString("name");
	              String date = res.getString("date");
	              String venue = res.getString("venue");
	              String link = res.getString("link");
	              String speakers = res.getString("speakers");
	              System.out.println(id + "\t" + name + "\t" + date + "\t" + venue + "\t" + link + "\t" + speakers);
	          }
		}
		catch (SQLException e) {
			 
			System.out.println(e.getMessage());
 
		} finally {
 
			if (st != null) {
				st.close();
			}
 
			if (conn != null) {
				conn.close();
			}
 
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			//insertRecordIntoConfTable();
			showRecordFromConfTable();
 
		} catch (SQLException e) {
 
			System.out.println(e.getMessage());
 
		}
	}

}
