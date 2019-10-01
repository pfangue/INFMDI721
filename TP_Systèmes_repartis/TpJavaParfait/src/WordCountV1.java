import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.io.*;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Iterator;

/* Out of memory error:
 * Dans la run configuration : Ajouter l’option -Xms1000m aux VM arguments
 * pour avoir 1Go de mémoire pour la machine virtuelle java (qui exécute votre programme)*/

public class WordCountV1 {

public static HashMap<String, Integer> sequentCount(String content) throws IOException
{

	HashMap<String, Integer> countSentence = new HashMap<>();
	
	List<String> file = Files.readAllLines(Paths.get(content));
	for (String sentence : file) 
	{	
		sentence.trim();	
		for (String word : sentence.split(" ") ) {
			word = word.trim().toLowerCase();		
			if (countSentence.containsKey(word)) {
				countSentence.put(word,countSentence.get(word)+1);
				//System.out.println(countSentence);
			} else {
				countSentence.put(word, 1);
			}
			
		}
	}
	return countSentence;

}

private static HashMap<String, Integer> triValue(HashMap<String, Integer> map)
{
    List linkedlist = new LinkedList(map.entrySet());
    Collections.sort(linkedlist, new Comparator() {
         public int compare(Object o1, Object o2) {
            return ((Comparable) ((Map.Entry) (o2)).getValue())
               .compareTo(((Map.Entry) (o1)).getValue());
         }
    });
    
    HashMap sortedHashMap = new LinkedHashMap();
    for (Iterator it = linkedlist.iterator(); it.hasNext();) {
           Map.Entry entry = (Map.Entry) it.next();
           sortedHashMap.put(entry.getKey(), entry.getValue());
    } 
    return sortedHashMap;

}

private static HashMap<String, Integer> triAlpha(HashMap<String, Integer> map)
{
    List linkedlist = new LinkedList(map.entrySet());
    Collections.sort(linkedlist, new Comparator() {
         public int compare(Object o1, Object o2) {
            return ((Comparable) ((Map.Entry) (o1)).getKey())
               .compareTo(((Map.Entry) (o2)).getKey());
         }
    });
    
    HashMap sortedHashMap = new LinkedHashMap();
    for (Iterator it = linkedlist.iterator(); it.hasNext();) {
           Map.Entry entry = (Map.Entry) it.next();
           sortedHashMap.put(entry.getKey(), entry.getValue());
    } 
    return sortedHashMap;

}
	

	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		HashMap<String, Integer> sequent = sequentCount("sante_publique.txt");
		HashMap<String, Integer> triSequent  = triValue(sequent);
		HashMap<String, Integer> triAlp  = triAlpha(sequent);
		System.out.println("Comptage Sequentiel Pur " + sequent);
		System.out.println("Comptage Tri Séquentiel par valeur " + triSequent);
		System.out.println("Comptage Tri par ordre alphabétique " + triAlp);
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("Temps d execution " + totalTime);

		
	
	}

}
