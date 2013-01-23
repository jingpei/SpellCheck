import java.io.*;
import java.util.*;


public class Corrections {
	private Map<String, String> corrections;
	private BufferedReader in;
 
	public Corrections(String filename) throws IOException{
		corrections = new HashMap<String, String>();
		in = new BufferedReader(new FileReader(filename));
		String data = in.readLine();

		while(data!=null){
			if(data.lastIndexOf(",")==-1){
				throw new IOException();
			}
			corrections.put(data.substring(0,data.lastIndexOf(",")),data.substring(data.lastIndexOf(",")+1, data.length()));
   
			data = in.readLine();
		}
		in.close();
	}
 
	public String getMap(String wrong){
		boolean cases = Character.isUpperCase(wrong.charAt(0));
		String correction=null;
 
		if(corrections.containsKey(wrong.toLowerCase())){
			correction=corrections.get(wrong.toLowerCase());
		}
		if(cases&&correction!=null){
			String capCorrection=correction.substring(0,1).toUpperCase()+correction.substring(1,correction.length());
			return capCorrection;
		}
		else{
			return correction;
		}
	}
}
   

