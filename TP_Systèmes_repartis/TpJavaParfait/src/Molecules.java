import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Molecules {
	
	public static HashMap<String, Integer> readInventory(String content) throws IOException{
		HashMap<String, Integer> inventory = new HashMap<String, Integer>();
		
		List<String> l = Files.readAllLines(Paths.get(content));
		
		for (String line : l)
		{
			if(line.equals(""))
			{
				continue;
			}
			
			if(line.startsWith("#"))
			{
				continue;
			}
			
			line = line.split("#")[0];
			String name = line.split(" ")[0];
			Integer number = Integer.parseInt(line.split(" ")[1]);
			inventory.put(name, number);
			
		}	
		return inventory;
		
	}
	
	
	public static HashMap<String, HashMap<String, Integer>> readFormulas(String content) throws IOException{
		
		HashMap<String, HashMap<String, Integer>> formulas = new HashMap<>();
		List<String> file =  Files.readAllLines(Paths.get(content));
		
		for(String line : file)
		{
			if(line.equals("")) {
				continue;
			}
			
			if(line.startsWith("#")) {
				continue;
			}
			
			line = line.split("#")[0];
			String key = line.split(" : ")[0];
			String[] formStr = line.split(" : ")[1].split(" ");
			
			HashMap<String, Integer> form = new HashMap<>();
			
			for(int i = 0; i < formStr.length; i += 2)
			{
				String nameAtom = formStr[i];
				Integer numberAt = Integer.parseInt(formStr[i+1]);
				
				form.put(nameAtom,  numberAt);
			}
			
			formulas.put(key,  form);
		
			
		}
		
		return formulas;
	}
	
	public static ArrayList<String> makeable(HashMap<String, Integer> inventory, HashMap<String, HashMap<String, Integer>> formulas)
	{
		ArrayList<String> res = new ArrayList<String>();

		for (String molName : formulas.keySet()) {
		    HashMap<String, Integer> f = formulas.get(molName);
		    boolean canMakeIt = true;

		    for (String atomName : f.keySet()) {
			Integer neededAtoms = f.get(atomName);
			Integer availableAtoms = inventory.get(atomName);
			
			if (availableAtoms == null) {
			    availableAtoms = 0;
			}

			canMakeIt = canMakeIt && neededAtoms <= availableAtoms;
		    }

		    if (canMakeIt) {
			res.add(molName);
		    }
		}

		return res;
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		HashMap<String, Integer> inventory = readInventory("atomes.txt");
		System.out.println(inventory);
		
		HashMap<String, HashMap<String, Integer>> formulas = readFormulas("recettes.txt");
		
		System.out.println(formulas);
		
		ArrayList<String> make = makeable(inventory, formulas);
		System.out.println(make);

	}

}
