import java.io.*;


public class SpellCheck {
	public static final int INVALID=0;
	public static final int IGNORE=1;
	public static final int REPLACE_USR=2;
	public static final int REPLACE_MAP=3;
 
	private Dictionary dic;
	private Corrections cor;
	private Document doc;
	private BufferedWriter out;
 
	public SpellCheck(String corrFileName, String dictFileName) throws IOException{
		dic = new Dictionary(dictFileName);
		cor = new Corrections(corrFileName);
	}
 
	public void checkDocument(String inFileName, InputStream input, String outFileName) throws IOException{
		doc = new Document(inFileName);
		File output = new File(outFileName);
		out = new BufferedWriter(new FileWriter(output));
		boolean exist=false;
		BufferedReader bf;
  
		bf = new BufferedReader(new InputStreamReader(input));
  
		String word = doc.getNextToken();
		while(word!=null){
			if(!doc.isWordCharacter(word.charAt(0))){
				out.write(word);
			}
			else if(dic.isValid(word)){
				out.write(word);
			}
			else{
				System.out.println("The word: \""+word+"\" is not in the dictionary. Please enter the number corresponding with the appropriate action");
				System.out.println("0: Ignore and continue");
				System.out.println("1: Replace with another word");
				if(cor.getMap(word)!=null){
					exist=true;
					System.out.println("2: Replace with \""+cor.getMap(word)+"\"");
				}
				String line;
				while(true){
					line = bf.readLine(); 
					if(line.equals("0")){
						out.write(word);
						break;
					}
					else if(line.equals("1")){
						System.out.println("Please enter the new word:");
						String newWord =bf.readLine();
						out.write(newWord);
						break;
					}
					else if(line.equals("2")&&exist){
						out.write(cor.getMap(word));
						break;
					}
					else if(line==null){
						break;
					}
					else{
						System.out.println("Wrong number input. Please enter the number again:");
					}
				}
			}
			word = doc.getNextToken();
		}
		bf.close();
		doc.closeInput();
		out.close();
}
 
	public static void main(String[] args) throws IOException{
		SpellCheck s=new SpellCheck(args[0],args[1]);
		s.checkDocument(args[2], System.in, args[3]);
	}
}
