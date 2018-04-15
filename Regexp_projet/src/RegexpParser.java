
import java.util.ArrayList;
import java.util.regex.*;

public class RegexpParser {

	private Pattern pattern;
	private Matcher matcher;
	private char[] tab_char;
	private ArrayList<String> alphabet;
	private String[] interceptor;
	private String[] indicator;
	private ArrayList<Node> automate;
	
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
			String[] interceptor = {"[","]"};
			String[] indicator = {"?","*"};
			this.interceptor = interceptor;
			this.indicator = indicator;
			this.automate = new ArrayList<Node>();
			
		}catch(Exception e){
			System.out.println("Invalid pattern");
			pattern = null;
			this.interceptor = null;
			this.indicator = null;
		}
		this.tab_char = pattern_string.toCharArray();	
	}
	public void makeAlphabet(){
		ArrayList<String> alphabet2 = new ArrayList<String>();
		for (char c : this.tab_char){
			boolean isLetter = true;
			for (String intercep : this.interceptor){
				if(String.valueOf(c).equals(intercep)){
					isLetter = false;
					break;
				}
			}
			for (String indic : this.indicator){
				if(String.valueOf(c).equals(indic)){
					isLetter = false;
					break;
				}
			}
			if (isLetter && !String.valueOf(c).equals("^")){
				alphabet2.add(String.valueOf(c));
			}
		}
		this.alphabet=alphabet2;
		System.out.println("Alphabet :");
		for (String letter : this.alphabet){
			System.out.println(letter);
		}
	}
	
	public String getType(String p){
		for (String indic : this.indicator){
			if(p.equals(indic)){
				return "indicateur";
			}
		}
		for (String intercep : this.interceptor){
			if(p.equals(intercep)){
				return "intercepteur";
			}
		}
		if(p.equals("^")){
			return "begin";
		}
		return "alphabet";
	}
	 
	public void readRegexp(){
		for (char c: this.tab_char){
			String s = String.valueOf(c);
			System.out.println(s+" : "+this.getType(s));
		}
	}
	public void makeAutomate(){
		for (int position=0; position<this.tab_char.length;position++){
			Node node;
			boolean beginPattern=false;
			String s_read = String.valueOf(this.tab_char[position]);
			String letters="";
			String indicateur="";
			if (this.getType(s_read).equals("begin")){
				beginPattern=true;
				position=position+1;
				s_read = String.valueOf(this.tab_char[position]);
			}
			if (this.getType(s_read).equals("intercepteur")){
				position=position+1;
				s_read = String.valueOf(this.tab_char[position]);
				while(!this.getType(s_read).equals("intercepteur")){
					letters+=s_read;
					position=position+1;
					s_read = String.valueOf(this.tab_char[position]);
				}
				try {
					position=position+1;
					s_read = String.valueOf(this.tab_char[position]);
					if(this.getType(s_read).equals("indicateur")){
						indicateur=s_read;
					}
				}
				catch(ArrayIndexOutOfBoundsException e) {
					indicateur="?";
				}
				
			}
			if(this.getType(s_read).equals("alphabet")){
				letters+=s_read;
				try{
					position=position+1;
					s_read = String.valueOf(this.tab_char[position]);
					if(this.getType(s_read).equals("indicateur")){
						indicateur=s_read;
					}
				}
				catch(ArrayIndexOutOfBoundsException e) {
					indicateur="?";
				}
			}
			node = new Node(letters,beginPattern,indicateur);
			this.automate.add(node);
		}
		System.out.println(this.automate);
	}
	@Override
	public String toString(){
		String affichage ="";
		int indice =0;
		for (Node c : this.automate){
			affichage+="\n"+indice+")"+c.graphics();
			indice++;
		}
		return affichage;
	}
	public static void main(String[] args) {	
		String test = "^[ab]*[c]?z*k?";
		RegexpParser parser = new RegexpParser(test);
		parser.readRegexp();
		parser.makeAlphabet();
		parser.makeAutomate();
		System.out.println(parser);
	}
}
