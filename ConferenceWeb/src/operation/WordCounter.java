//Count occurences of keywords from the file and store the data to db
package operation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class WordCounter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String fileName = "C:/Users/VAHBHP/git/LocalConferenceRepo/ConferenceWeb/Files/keywords.txt";
		
		try {
			BufferedReader br=new BufferedReader(new FileReader(fileName));
			//System.out.println("cp-1");
			//HashMap Implementation to include the word count
	          HashMap<String, Integer> map = new HashMap<String, Integer>();
	          
	          String word = null;
	          //System.out.println("cp-2");
	          while (((word = br.readLine()) != null)) {
	        	  if (word.length() == 0)
	        		  continue;
	        	  if (map.containsKey(word)) {
	                  map.put(word, map.get(word) + 1);
	              } else {
	                  map.put(word, 1);
	              }
	        	  
	          }
	        
	          //Sort map using Treemap
	          Map<String, Integer> treeMap = new TreeMap<String, Integer>(map);
	          
	          
	          //Loop the map to display results
	          Iterator iter = treeMap.entrySet().iterator();
	          
				while (iter.hasNext()) {
					Map.Entry mEntry = (Map.Entry) iter.next();
					//if (mEntry.getKey() == "\n")
					//System.out.println(mEntry.getKey() + " : " + mEntry.getValue());
					
					String keyword = (String) mEntry.getKey();
					Integer counter = (Integer) mEntry.getValue();
					DbOperation.insertRecordIntoTopicTable(keyword, counter);
					
					
				}
				System.out.println("Done");
				br.close();
		}
		
		catch (FileNotFoundException fe) {
			fe.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
