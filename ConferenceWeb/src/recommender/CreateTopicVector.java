package recommender;

import operation.*;

import java.util.*;


public class CreateTopicVector {

	LinkedHashSet<String> topicSet = null;
	
	public CreateTopicVector() {
		topicSet = new LinkedHashSet<String>();
	}
	
	
	public LinkedHashSet<String> getTopicSet() {
		topicSet = DbOperation.getTopicData();
		return topicSet;
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			LinkedHashSet<String> displaySet = new LinkedHashSet<String>();
			CreateTopicVector ctv = new CreateTopicVector();
			displaySet = ctv.getTopicSet();
			
			//Iterating over HashSet using Iterator in Java
	        Iterator<String> itr = displaySet.iterator();
	        while(itr.hasNext()){
	            System.out.println(" Topic: " + itr.next());
	        }
			
	}

}
