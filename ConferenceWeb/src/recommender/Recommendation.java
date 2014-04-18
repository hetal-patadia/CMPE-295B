package recommender;

import java.util.*;

import operation.DbOperation;

public class Recommendation {
	
	public Recommendation () {}
	
	public Map recommendConferences(String userName) {
		System.out.println("In finding recom. method");
		//Get user vector based on user name passed
		ArrayList<Integer> userVector = new ArrayList<Integer>();
		CreateUserVector csv = new CreateUserVector();
		userVector = csv.getConfList(userName);
		
		//Find similarity with all the conferences present in the table
		JaccardSimilarity js = new JaccardSimilarity();
		HashMap<Integer, Double> similarityMap = new HashMap<Integer, Double>();
		for (int i=1; i < 1317; i++) {
			ArrayList<Integer> confVector = new ArrayList<Integer>();
			CreateConferenceVector ccv = new CreateConferenceVector();
			confVector = ccv.getConfList(i);
			double sim = js.findJaccardSimilarity(userVector, confVector);
			if (sim == 0.0)
				continue;
			else
				similarityMap.put(i, sim);
		}
		
		
		List list = new LinkedList(similarityMap.entrySet());
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o2)).getValue())
                                       .compareTo(((Map.Entry) (o1)).getValue());
			}
		});
		
		Map sortedMap = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		
		
		return sortedMap;
	}
	
	
	public void storeTopRecommendations (String userName) {
		System.out.println("In storing of recom. method");
		Map<Integer, Double> recomMap = recommendConferences(userName);
		String recomString = "";
		//Taking top 20 recommendations
		int counter = 0;
		for (Map.Entry entry : recomMap.entrySet()) {
			if (counter == 10)
				break;
			else {
				recomString = recomString + entry.getKey() + ", ";
				counter++;
			}
			
		}
			
		System.out.println(recomString);
		//add this string to recommendation table
		DbOperation.addRecomConf(userName, recomString);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Recommendation r = new Recommendation();
		
		/*
		Map<Integer, Double> temp = r.recommendConferences("testuser5");
		for (Map.Entry entry : temp.entrySet()) {
			System.out.println("ConfID : " + entry.getKey() 
                                   + " Sim : " + entry.getValue());
		}
		*/
		
		r.storeTopRecommendations("testuser5");
	}

}
