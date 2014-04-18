package recommender;

import operation.*;

import java.util.*;

public class CreateConferenceVector {
	
	LinkedHashSet<Integer> confSet = null;
	
	public CreateConferenceVector() {
		confSet = new LinkedHashSet<Integer>();
	}
	
	public ArrayList<String> getConfKeyList(int confId) {
		ArrayList<String> confKeys = new ArrayList<String>();
		confKeys = DbOperation.getConfKeys(confId);
		return confKeys;
	}
	
	/*
	@Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        return false;
        
        //String str = (String) obj;
        //return (str.equals(obj));
    }
	*/
	
	public ArrayList<Integer> getConfList(int confId) {
		ArrayList<Integer> confList = new ArrayList<Integer>();
		//Get Topic Vector
		LinkedHashSet<String> topicSet = new LinkedHashSet<String>();
		CreateTopicVector ctv = new CreateTopicVector();
		topicSet = ctv.getTopicSet();
		
		//Get Keywords List of Particular Conference
		ArrayList<String> keys = new ArrayList<String>();
		keys = getConfKeyList(confId);
		
		/*
		//Iterating over ArrayList using Iterator in Java
        Iterator<String> itr1 = keys.iterator();
        while(itr1.hasNext()){
            System.out.println(" Topic: " + itr1.next());
        }
		
		*/
		
		//LinkedHashSet<String> temp = new LinkedHashSet<String>();		
		//Iterating over HashSet using Iterator in Java
        Iterator<String> itr = topicSet.iterator();
        //int counter = 1;
        while(itr.hasNext()){
        	String keyword = itr.next();
        	//System.out.println("For element: " + keyword);
        	//System.out.println("In loop - " + counter);
            if (keys.contains(keyword)) {
            	//temp.add(keyword);
            	//System.out.println("added in counter - " + counter);
            	//System.out.println("True");
            	confList.add(1);
            	
            }
            else {
            	//temp.add("none");
            	//System.out.println("added none");
            	//System.out.println("False");
            	confList.add(0);
            }
            //counter++;
        }
        
		
		/*
        for(String str : topicSet){
            //System.out.println(" Looping over HashSet in Java element : " + str);
        	if (keys.contains(str))
        		System.out.println("True");
        	else
        		System.out.println("False");
        }
        */
        //System.out.println(temp.size());
		//return confSet;
        /*
        for (String str: temp) {
        	System.out.println(str);
        }
        */
        
        return confList;
		
	}

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		ArrayList<Integer> temp = new ArrayList<Integer>();
		CreateConferenceVector ccv = new CreateConferenceVector();
		temp = ccv.getConfList(1);
		
		//Iterating over ArrayList using Iterator in Java
        Iterator<Integer> itr = temp.iterator();
        while(itr.hasNext()){
            System.out.println(" Topic: " + itr.next());
        }
		
		
		/*
		//Iterating over HashSet using Iterator in Java
        Iterator<Integer> itr = temp.iterator();
        int counter = 1;
        while(itr.hasNext()){
            System.out.println(counter + " : " + itr.next());
            counter++;
        }
        */
	}

}


/*

import java.util.*;

/*
 * Jaccard Similarity is a similarity function which is calculated by 
 * first tokenizing the strings into sets and then taking the ratio of
 * (weighted) intersection to their union 
 */

/*
public class JaccardSimilarity {

	public static double jaccardSimilarity(String similar1, String similar2){
		HashSet<String> h1 = new HashSet<String>();
		HashSet<String> h2 = new HashSet<String>();
		
		for(String s: similar1.split("\\s+")){
		h1.add(s);		
		}
		System.out.println("h1 "+ h1);
		for(String s: similar2.split("\\s+")){
		h2.add(s);		
		}
		System.out.println("h2 "+ h2);
		
		int sizeh1 = h1.size();
		//Retains all elements in h3 that are contained in h2 ie intersection
		h1.retainAll(h2);
		//h1 now contains the intersection of h1 and h2
		System.out.println("Intersection "+ h1);
		
			
		h2.removeAll(h1);
		//h2 now contains unique elements
		System.out.println("Unique in h2 "+ h2);
		
		//Union 
		int union = sizeh1 + h2.size();
		int intersection = h1.size();
		
		return (double)intersection/union;
		
	}
	public static void main(String args[]){
		System.out.println(jaccardSimilarity("153 West Squire Dr","147 West Squire Dr"));
		
	}
}




*/

