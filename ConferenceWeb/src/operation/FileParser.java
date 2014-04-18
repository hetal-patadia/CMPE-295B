//parse the crawler returned file and store it to db
package operation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

public class FileParser {
	
	//Source file to read data from.
	private static String dataFileName = "C:/Users/VAHBHP/git/LocalConferenceRepo/ConferenceWeb/Files/latest conferences.txt";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
        try {
        	//Creating a buffered reader to read the file
			BufferedReader bReader = new BufferedReader(new FileReader(dataFileName));
			String line;
			
			while ((line = bReader.readLine()) != null) {
				 
	            //Splitting the content of tabbed separated line
	            String datavalue[] = line.split("\t");
	            String name = datavalue[0];
	            String date = datavalue[1];
	            String loc = datavalue[2];
	            String link = datavalue[3];
	            String keywords = datavalue[4];
	            String speakers = datavalue[5];
	            
	            //trim white spaces from speakers
	            speakers = speakers.trim();
	            //remove square brackets from speakers
	            StringBuilder sb = new StringBuilder(speakers);
	            //remove rightmost bracket from speakers
	            sb.deleteCharAt(sb.length()-1);
	            //remove leftmost bracket from speakers
	            sb.deleteCharAt(0);
	            String speakers1 = sb.toString();
	            //remove single quotes from the string of speakers
	            String speakers2 = speakers1.replaceAll("'", "");
	            
	            //trim white spaces from keywords
	            keywords = keywords.trim();
	            //remove square brackets from keywords
	            StringBuilder sb1 = new StringBuilder(keywords);
	            //remove rightmost bracket from keywords
	            sb1.deleteCharAt(sb1.length()-1);
	            //remove leftmost bracket from keywords
	            sb1.deleteCharAt(0);
	            String keywords1 = sb1.toString();
	            //remove single quotes from the string of keywords
	            String keywords2 = keywords1.replaceAll("'", "");
	           
	            //System.out.println(speakers2);
	            //Printing the value read from file to the console
	            //System.out.println(name + "\t\t" + date + "\t\t" + loc + "\t\t" + link + "\t\t" + speakers2);
	            
	            //DbOperation.insertRecordIntoConfTable("Test1", "test_date1", "test_loc1", "www.testlink1.com", "testspeaker1");
	            //Storing the values from the file to the db
	            DbOperation.insertRecordIntoConfTable(name, date, loc, link, keywords2, speakers2);
	            
	        }
			bReader.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}

}
