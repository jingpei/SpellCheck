import java.io.*;
import java.util.*;

public class Document {
	private BufferedReader in;
 
	public Document(String inFileName) throws IOException{
		in = new BufferedReader(new FileReader(inFileName));
		in.mark(5);
	}
	
	public boolean isWordCharacter(char c){
		return Character.isLetter(c)||c==39;
	}
 
	public void closeInput() throws IOException{
		in.close();
	}	
 
	public String getNextToken()throws IOException{
		in.reset();
		StringBuffer bf=new StringBuffer();
		char ch = (char)in.read();

		if(ch==65535){
			return null;
		}
		else if(!isWordCharacter(ch)){
			bf.append(ch);
			in.mark(18);
			return bf.toString();
		}
		else{
			while(isWordCharacter(ch)){
				bf.append(ch);
				in.mark(18);
				ch = (char)in.read();
			}
			return bf.toString();
		}
	}
}
