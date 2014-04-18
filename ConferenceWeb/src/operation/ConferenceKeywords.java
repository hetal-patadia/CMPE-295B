//Extracts all the keywords from the conference table and store it into a file
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
import java.util.*;

public class ConferenceKeywords {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement st = null;
		String fileName = "C:/Users/VAHBHP/git/LocalConferenceRepo/ConferenceWeb/Files/keywords.txt";
		//String fileName2 = "C:/Users/VAHBHP/git/LocalConferenceRepo/ConferenceWeb/Files/uniqueKeywords.txt";
		
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
	            	  String cleanedWord = datavalue[count].replaceAll("[-+#^:,\"]","").toLowerCase();
	            	  bw.write(cleanedWord+"\n");
	            	  count++;
	              }
	              
	          }
	          
	          System.out.println("Done1");
	          /*
	          BufferedReader br=new BufferedReader(new FileReader(fileName));
	          HashSet<String> set=new HashSet<String>();
				
				String line=null;
				
				File tempfile2 = new File(fileName2);
				// if file doesn't exists, then create it
							if (!tempfile2.exists()) {
								tempfile2.createNewFile();
							}
				*/
	          
	        /*  
	          //HashMap Implementation to include the word count
	          HashMap<String, Integer> map = new HashMap<String, Integer>();
	          
	          String word = null;
	          String lowerWord = null;
	          while ((word = br.readLine()) != null) {
	        	  lowerWord = word.toLowerCase();
	        	  if (map.containsKey(lowerWord)) {
	        		  int counter = map.get(lowerWord);
	        		  counter++;
	        	  }
	        	  else
	        		  map.put(lowerWord, 1);
	        	  
	          }
	        
	          //Sort map using Treemap
	          Map<String, Integer> treeMap = new TreeMap<String, Integer>(map);
	          
	          
	          //Loop the map to display results
	          Iterator iter = treeMap.entrySet().iterator();
	          
				while (iter.hasNext()) {
					Map.Entry mEntry = (Map.Entry) iter.next();
					System.out.println(mEntry.getKey() + " : " + mEntry.getValue());
				}
	          */
	          
	          /*
			  BufferedWriter bw1 = new BufferedWriter(new FileWriter(tempfile2.getAbsoluteFile()));
							
	          while ((line=br.readLine())!=null)
	          {
	        	  String result = line.replaceAll("[-+#^:,\"]","");
	        	  String cleanedLine = result.toLowerCase();
	        	  
	            if ((!set.contains(cleanedLine)) && (cleanedLine != null))
	            {
	              set.add(cleanedLine);
	              System.out.println(cleanedLine);
	              bw1.write(cleanedLine+"\n");
	            }
	          }
	          
	          */
	          
	          
	          
	          //br.close();
	          bw.close();
	          //bw1.close();
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

