import java.util.*;
import java.io.*;

public class Dictionary{
	private Set<String> dictionary;
	private BufferedReader in;
 
	public Dictionary(String filename) throws IOException{
		dictionary = new HashSet<String>();
		in = new BufferedReader(new FileReader(filename));
		String data = in.readLine();
		
		while(data!=null){
			dictionary.add(data);
			data = in.readLine();
		}
  
		in.close();
	}
 
	public boolean isValid(String word){
		Iterator<String> itr = dictionary.iterator(); 
		boolean validity = false;
		while(itr.hasNext()){
			if(((String) itr.next()).equalsIgnoreCase(word)){
				validity = true;
				break;
			}
		}
		return validity;
	} 
}
