package operation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.HashSet;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConferenceKeywords {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement st = null;
		String fileName = "C:/Users/VAHBHP/git/LocalConferenceRepo/ConferenceWeb/Files/keywords.txt";
		String fileName2 = "C:/Users/VAHBHP/git/LocalConferenceRepo/ConferenceWeb/Files/uniqueKeywords.txt";
		
		try{
			File tempfile = new File(fileName);
			// if file doesn't exists, then create it
						if (!tempfile.exists()) {
							tempfile.createNewFile();
						}
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(tempfile.getAbsoluteFile()));
						
			conn = DbOperation.getDBConnection();
			st = conn.createStatement();
			
			ResultSet res = st.executeQuery("SELECT keywords FROM  conf_details");
			
			 
	          while (res.next()) {
	              String keywords = res.getString("keywords");
	              String datavalue[] = keywords.split(", ");
	              int count = 0;
	              while (count < datavalue.length) {
	            	  //System.out.println(datavalue[count]);
	            	  bw.write(datavalue[count]+"\n");
	            	  count++;
	              }
	              
	          }
	          
	          System.out.println("Done1");
	          	HashSet<String> set=new HashSet<String>();
				BufferedReader br=new BufferedReader(new FileReader(fileName));
				String line=null;
				
				File tempfile2 = new File(fileName2);
				// if file doesn't exists, then create it
							if (!tempfile2.exists()) {
								tempfile2.createNewFile();
							}
				
				BufferedWriter bw1 = new BufferedWriter(new FileWriter(tempfile2.getAbsoluteFile()));
							
	          while ((line=br.readLine())!=null)
	          {
	            if (!set.contains(line))
	            {
	              set.add(line);
	              System.out.println(line);
	              bw1.write(line+"\n");
	            }
	          }
	          
	          br.close();
	          bw.close();
	          bw1.close();
	          st.close();
	          conn.close();
	          System.out.println("Done2");
		}
		catch (SQLException e) {
			 
			System.out.println(e.getMessage());
 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
 
		}
	}

