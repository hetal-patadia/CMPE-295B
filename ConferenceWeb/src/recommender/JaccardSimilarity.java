package recommender;

import java.util.*;

public class JaccardSimilarity {
	
	public JaccardSimilarity() { }
	
	public double findJaccardSimilarity (ArrayList<Integer> item1, ArrayList<Integer> item2) {
		double sim = 0.0;
		
		/*
		
		//Retains all elements in item1 that are contained in item2 i.e. intersection
		//Hence, item1 is now intersection of item1 and item2
		item1.retainAll(item2);
		
		//Keeping all unique elements in item2
		item2.removeAll(item1);
		
		int size2 = item2.size();
		
		//finding union
		int union = size1 + size2;
		int intersection = item1.size();
		
		*/
		
		int union = 0;
		int intersection = 0;
		
		Iterator<Integer> itr1 = item1.iterator();
		Iterator<Integer> itr2 = item2.iterator();
		
		while (itr1.hasNext() && itr2.hasNext()) {
			int temp1 = itr1.next();
			int temp2 = itr2.next();
			
			if (temp1 == 0 && temp2 == 0) {
				continue;
			}
			else if (temp1 == temp2) {
				//System.out.println("Found int : " + temp1 + " & " + temp2);
				intersection += 1;
				union += 1;
			}
			else {
				//System.out.println("Found union" + temp1 + " & " + temp2);
				union += 2;
			}
			
			/*
			if (temp1 == temp2) {
				System.out.println("Found int : " + temp1 + " & " + temp2);
				intersection += 1;
				union += 1;
			}
			else {
				System.out.println("Found union" + temp1 + " & " + temp2);
				union += 2;
			}
			*/
			
		}
		
		//System.out.println("Union: " + union);
		//System.out.println("Intersection: " + intersection);
		
		sim = (double)intersection/union;
		
		return sim;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList<Integer> conf6 = new ArrayList<Integer>();
		ArrayList<Integer> conf9 = new ArrayList<Integer>();
		
		
		CreateConferenceVector ccv = new CreateConferenceVector();
		conf6 = ccv.getConfList(6);
		//conf9 = ccv.getConfList(9);
		
		CreateUserVector csv = new CreateUserVector();
		conf9 = csv.getConfList("testuser5");
		
		/*
		conf6.add(0);
		conf6.add(1);
		conf6.add(0);
		conf6.add(1);
		conf6.add(1);
		conf6.add(0);
		
		conf9.add(1);
		conf9.add(1);
		conf9.add(1);
		conf9.add(1);
		conf9.add(0);
		conf9.add(1);
		*/
		
		JaccardSimilarity js = new JaccardSimilarity();
		
		System.out.println("Jaccard similarity between conf. 6 and 9 is: " + js.findJaccardSimilarity(conf6, conf9));
		
	}

}
