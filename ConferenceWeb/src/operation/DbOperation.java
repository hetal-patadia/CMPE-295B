package operation;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

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
	
	public static int insertRecordIntoUserTable(String firstname, String lastname, String email_id, String username, String password, String domain1, String domain2, String domain3, String interests) throws SQLException {
		Connection conn = null;
		Statement st = null;
		int val = 0;
		String insertData = "INSERT into user_profile (firstname, lastname, email_id, username, password, interest1, interest2, interest3, interests) VALUES('" + firstname + "','" + lastname + "','" + email_id + "','" + username + "','" + password +  "','" + domain1 +  "','" + domain2 +  "','" + domain3 + "','" + interests +"')";
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
		
		
		static void insertRecordIntoTopicTable(String topic_name, Integer counter) throws SQLException {
			Connection conn = null;
			Statement st = null;
			String insertData = "INSERT into topics (topic_name, occurence) VALUES('" + topic_name + "', " + counter + ")";
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
	
	private static void showRecordFromConfTable(int conf_id) throws SQLException {
		Connection conn = null;
		Statement st = null;
		try{
			conn = getDBConnection();
			st = conn.createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM  conf_details where conf_id = " + conf_id );
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
	
	
	public static LinkedHashSet<String> getTopicData() {
		Connection conn = null;
		Statement st = null;
		ResultSet res = null;
		LinkedHashSet<String> topicData = new LinkedHashSet<String>();
		try{
			conn = getDBConnection();
			st = conn.createStatement();
			res = st.executeQuery("SELECT topic_name FROM  topics order by topic_name asc");
			while (res.next()) {
			    String topicName = res.getString("topic_name");
			    //topicData.add(topicName);
			    topicData.add(topicName);
			}
			st.close();
			conn.close();
			//return res;
		}
		catch (SQLException e) {
			 
			System.out.println(e.getMessage());
 
		}
		return topicData;
		
	}
	
	public static ArrayList<String> getConfKeys(int confId) {
		ArrayList<String> confKeys = new ArrayList<String>();
		Connection conn = null;
		Statement st = null;
		ResultSet res = null;
		String keywords = null;
		conn = getDBConnection();
		try {
		st = conn.createStatement();
		res = st.executeQuery("SELECT keywords FROM  conf_details where conf_id = " + confId);
		if (res.next()) {
			keywords = res.getString("keywords");
		}
		String datavalue[] = keywords.split(", ");
        int count = 0;
        while (count < datavalue.length) {
      	  //System.out.println(datavalue[count]);
      	  String cleanedWord = datavalue[count].replaceAll("[-+#^:,\"]","").toLowerCase();
      	  confKeys.add(cleanedWord);
      	  count++;
        }
        st.close();
        conn.close();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return confKeys;
		
	}
	
	
	public static ArrayList<String> getConfKeysFromUser(String userName) {
		ArrayList<String> confKeys = new ArrayList<String>();
		Connection conn = null;
		Statement st = null;
		ResultSet res = null;
		String keywords = null;
		conn = getDBConnection();
		try {
		st = conn.createStatement();
		res = st.executeQuery("SELECT interests FROM  user_profile where username = '" + userName + "'");
		if (res.next()) {
			keywords = res.getString("interests");
		}
		String datavalue[] = keywords.split(", ");
        int count = 0;
        while (count < datavalue.length) {
      	  //System.out.println(datavalue[count]);
      	  String cleanedWord = datavalue[count].replaceAll("[-+#^:,\"]","").toLowerCase();
      	  confKeys.add(cleanedWord);
      	  count++;
        }
        st.close();
        conn.close();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return confKeys;
		
	}
	
	
	public static void appendSearchStringToUserProfile(String userName, String searchQuery) {
		//System.out.println(userName);
		//System.out.println(searchQuery);
		Connection conn = null;
		Statement st = null;
		try{
			conn = getDBConnection();
			st = conn.createStatement();
			String interests = null;
			boolean updateRequired = true;
			ResultSet res = st.executeQuery("SELECT interests FROM  user_profile where username = '" + userName + "'");
	          if (res.next()) {
	              interests = res.getString("interests");
	              //interests = interests + ", " + searchQuery;
	              //res.updateString("interests", interests);
	          }
	          
	          //check whether the search query is already present or not
	          String datavalue[] = interests.split(", ");
              int count = 0;
              
              while (count < datavalue.length) {
            	  //System.out.println(datavalue[count]);
            	  //String cleanedWord = datavalue[count].replaceAll("[-+#^:,\"]","").toLowerCase();
            	  //System.out.println(datavalue[count]);
            	  if (datavalue[count].equalsIgnoreCase(searchQuery)) {
            		  updateRequired = false;
            		  System.out.println("Not updating... already exists");
            		  break;
            	  }
            	  count++;
              }
              
              if (updateRequired) {
            	  interests = interests + ", " + searchQuery;
            	  Connection conn1 = getDBConnection();
            	  Statement st1 = conn1.createStatement();
            	  String sql = "UPDATE user_profile SET interests = '" + interests + "' where username = '" + userName + "'";
            	  st1.executeUpdate(sql);
            	  st1.close();
            	  conn1.close();
            	  System.out.println("Successfully Added");
              }

              st.close();
	          conn.close();
		}
		catch (SQLException e) {
			 
			System.out.println(e.getMessage());
 
		}
		
	}
	
	
	public static ArrayList<String[]> searchConf(String searchQuery) {
		Connection conn = null;
		Statement st = null;
		ArrayList<String[]> results = new ArrayList<String[]>();
		try {
			conn = getDBConnection();
			st = conn.createStatement();
			ResultSet res = st.executeQuery("SELECT name, date, venue, link, keywords, speakers FROM  conf_details where keywords regexp '" + searchQuery + "'");
			while (res.next()) {
	              String name = res.getString("name");
	              String date = res.getString("date");
	              String venue = res.getString("venue");
	              String link = res.getString("link");
	              String keywords = res.getString("keywords");
	              String speakers = res.getString("speakers");
	              String[] temp = {name, date, venue, link, keywords, speakers};
	              results.add(temp);
	          }
			st.close();
	          conn.close();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return results;
	}
	
	
	public static void addRecomConf (String userName, String recomString) {
		System.out.println("In adding recom. method");
		Connection conn = null;
		Statement st = null;
		try{
			conn = getDBConnection();
			st = conn.createStatement();
			
			boolean userNameExists = false;
			ResultSet res = st.executeQuery("SELECT user_name FROM  user_recom_conf where user_name = '" + userName + "'");
	          if (res.next()) {
	              userNameExists = true;
	          }
	          
	          if (userNameExists) {
	        	  String sql = "UPDATE user_recom_conf SET recom_conf = '" + recomString + "' where user_name = '" + userName + "'";
	        	  st.executeUpdate(sql);
	        	  System.out.println("Updated");
	          }
	          else {
	        	  String sql = "INSERT into user_recom_conf (user_name, recom_conf) VALUES('" + userName + "','" + recomString + "')";
	        	  st.executeUpdate(sql);
	        	  System.out.println("Added");
	          }
	          st.close();
	          conn.close();
		}
		catch (SQLException e) {
			 
			System.out.println(e.getMessage());
 
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		try {
			//insertRecordIntoConfTable();
			showRecordFromConfTable(664);
 
		} catch (SQLException e) {
 
			System.out.println(e.getMessage());
 
		}
		*/
		
		/*
		Set<String> displaySet = new LinkedHashSet<String>();
		displaySet = getTopicData();
		
		//Iterating over HashSet using Iterator in Java
        Iterator<String> itr = displaySet.iterator();
        while(itr.hasNext()){
            System.out.println(" Topic: " + itr.next());
        }
        */
		
		/*
		ArrayList<String[]> temp = new ArrayList<String[]>();
		temp = searchConf("web services");
		Iterator<String[]> itr = temp.iterator();
		while (itr.hasNext()) {
			String[] abc = itr.next();
			System.out.println(abc[0]);
			System.out.println(abc[1]);
			System.out.println(abc[2]);
			System.out.println(abc[3]);
			System.out.println(abc[4]);
			System.out.println(abc[5]);
			System.out.println("------------");
		}
		*/
	}

}
