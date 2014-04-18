package recommender;

import operation.*;

import java.util.*;

public class CreateUserVector {
	
	LinkedHashSet<Integer> userSet = null;
	
	public CreateUserVector() { 
		userSet = new LinkedHashSet<Integer>();
	}

	public ArrayList<String> getConfKeyList(String userName) {
		ArrayList<String> confKeys = new ArrayList<String>();
		confKeys = DbOperation.getConfKeysFromUser(userName);
		return confKeys;
	}
	
	public ArrayList<Integer> getConfList(String userName) {
		ArrayList<Integer> confList = new ArrayList<Integer>();
		//Get Topic Vector
		LinkedHashSet<String> topicSet = new LinkedHashSet<String>();
		CreateTopicVector ctv = new CreateTopicVector();
		topicSet = ctv.getTopicSet();
		
		//Get Keywords List of Particular User
		ArrayList<String> keys = new ArrayList<String>();
		keys = getConfKeyList(userName);
		
		Iterator<String> itr = topicSet.iterator();
		
		while(itr.hasNext()){
        	String keyword = itr.next();
        	if (keys.contains(keyword))
        		confList.add(1);
        	else
        		confList.add(0);
		}
		
		return confList;
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> temp = new ArrayList<Integer>();
		CreateUserVector csv = new CreateUserVector();
		temp = csv.getConfList("testuser4");
		
		//Iterating over ArrayList using Iterator in Java
        Iterator<Integer> itr = temp.iterator();
        while(itr.hasNext()){
            System.out.println(" Topic: " + itr.next());
        }
	}

}
