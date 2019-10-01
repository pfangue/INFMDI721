import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Lexique {

	
	public static HashMap<String, Integer> countSentencesWithWord(List<String> french)
	{

		HashMap<String, Integer> countSentence = new HashMap<>();
		
		for (String sentence : french) 
		{
			HashSet<String> countWord = new HashSet<>();
			
			for (String word : sentence.split(" ") )
			{
				countWord.add(word);
	
			}
			
			//System.out.println(countWord);
			//HashSet<String> uniqWord = new HashSet<String>(Arrays.asList(sentence.split(" ")));
			
			for (String word : countWord) {
				
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
	
	public static HashMap<String,HashMap<String, Integer>> buildContTable(String  fr, String en)
	{

		HashMap<String,HashMap<String, Integer>> frTable = new HashMap<>();
		HashMap<String, Integer> enTable = new HashMap<>();
		
		System.out.println(fr);

		String[] frCorpus = fr.split("\n");
		
		String[] enCorpus = en.split("\n");
			
		
		for (int i=0; i < frCorpus.length; i++) 
		{
			HashSet<String> uniqWordFr = new HashSet<String>(Arrays.asList(frCorpus[i].split(" ")));
			HashSet<String> uniqWordEn = new HashSet<String>(Arrays.asList(enCorpus[i].split(" ")));
			

			System.out.println(uniqWordFr);
			System.out.println(frCorpus.length);
			System.out.println(uniqWordEn);

			//HashSet<String> uniqWord = new HashSet<String>(Arrays.asList(sentence.split(" ")));
			
			for (String wordFr : uniqWordFr) {
				
				if (frTable.containsKey(wordFr)) {
					for (String wordEn : uniqWordEn){
						if (frTable.get(wordFr).containsKey(wordEn)) {
							System.out.println("wordFr " + wordFr +  " " + wordEn +" it " + frTable.get(wordFr).get(wordEn));

							frTable.get(wordFr).put(wordEn, frTable.get(wordFr).get(wordEn)+1);
							System.out.println("wordFr " + wordFr +  " " + wordEn +" it " + frTable.get(wordFr).get(wordEn));
						} else {
							frTable.get(wordFr).put(wordEn,1);
						}					
					}
					//System.out.println(countSentence);
				} else {
					
					//Rajouter for sur wordEn
					for(String wordEn : uniqWordEn) {
						enTable.put(wordEn, 1);
						frTable.put(wordFr, enTable);
					}
				}
				
			}
		}
		return frTable;

	}
	
	
	
	
	public static String readFile(String file) throws IOException {
		/*String res = "";
		try {
			File Readfile = new File(File);
			Scanner input = new Scanner(Readfile);
			while (input.hasNext()) {
			res = res + input.nextLine();
			}
			return res;
		} catch (IOException e) {
			System.out.println("Erreur du fichier : " + File);
			System.exit(1);
		}
		return null;*/
		return new String((Files.readAllBytes(Paths.get(file))));
	}
	

	public static void main(String[] args) throws IOException{
		
		//String fileFr = readFile("french.txt");
		//String fileEn = readFile("anglais.txt")
		;
		List<String> fileFr = Files.readAllLines(Paths.get("french.txt"));
		List<String> fileEn = Files.readAllLines(Paths.get("anglais.txt"));
		
		
		String fr = readFile("french.txt");
		String en = readFile("anglais.txt");
		//System.out.println(fileFr);
		
		HashMap<String, Integer> frOcc = countSentencesWithWord(fileFr);
		HashMap<String, Integer> EnOcc = countSentencesWithWord(fileEn);
		
		HashMap<String,HashMap<String, Integer>> cooTable = buildContTable(fr,en);
		//HashMap<String, HashMap<String, Integer>> test =  buildCoocTable(frOcc, EnOcc);
		System.out.println(frOcc);
		System.out.println(EnOcc);
		System.out.println("cooTable " + cooTable);
		
		

	}

}
