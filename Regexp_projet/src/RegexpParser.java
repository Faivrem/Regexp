
import java.util.ArrayList;
import java.util.regex.*;

public class RegexpParser {

	private Pattern pattern;
	private Matcher matcher;
	private char[] tab_char;
	private ArrayList<String> alphabet;
	
	/*
	 *  les lettres - chiffres
	 *  ^
	 *  [ ]
	 *  ?
	 *  *
	 */
	public RegexpParser(String pattern_string){
		try {
			pattern = Pattern.compile(pattern_string);
		}catch(Exception e){
			System.out.println("Invalid pattern");
			pattern = null;
		}
		this.tab_char = pattern_string.toCharArray();	
	}
	
	
	public char[] recupLetters(String p){
		return null;
	}
	 
	public void lireRegexp(){
		for(int i=0;i<this.tab_char.length;i++){
			
		}
		for (char c: this.tab_char){
			
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
