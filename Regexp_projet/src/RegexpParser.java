
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.*;

public class RegexpParser {

	private Pattern pattern;
	private Matcher matcher;
	private char[] tab_char;
	private ArrayList<String> alphabet;
	private String[] interceptor;
	private String[] indicator;
	private ArrayList<Node> automate;
	private ArrayList<Noeud> auto = null;
	
	
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
			this.auto = new ArrayList<Noeud>();
			
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
	public void creation() {
		int i =0;
		int num=1;
		String letters="";
		String indicateur="";
		boolean begin = false;
		while ( i<this.tab_char.length) {
			String value = String.valueOf(this.tab_char[i]);
			if (this.getType(value).equals("begin")){
				begin = true;
				i++;
			}
			else if (this.getType(value).equals("intercepteur")){
						i++;
						value = String.valueOf(this.tab_char[i]);
					while(!this.getType(value).equals("intercepteur")){
						letters+= value;
						i++;
						value = String.valueOf(this.tab_char[i]);	
					}
					i++;
					value = String.valueOf(this.tab_char[i]);
					
			}
			else if (this.getType(value).equals("indicateur")){
				indicateur = value;
				ArrayList<Transition> listeTransitions  = new ArrayList<Transition>() ;
				if (indicateur.equals("?")){
					char[] tabLetter = letters.toCharArray();
					for (int j =0;j<tabLetter.length;j++) {
						Transition transition = new Transition(tabLetter[j],false);
						listeTransitions.add(transition);
					}
				}
				else if (indicateur.equals("*")){
					char[] tabLetter = letters.toCharArray();
					for (int j =0;j<tabLetter.length;j++) {
							Transition transition = new Transition(tabLetter[j],true);
							listeTransitions.add(transition);
					}
				}
			
				if (begin == true) {
					Noeud noeud = new Noeud(num,true,false,listeTransitions);
					auto.add(noeud);
					begin = false;
					letters ="";
					num++;
					
					
				}
				else if (begin == false) {
					Noeud noeud = new Noeud(num,false,false,listeTransitions);	
					auto.add(noeud);
					begin = false;
					letters ="";
					num++;
				}
			i++;
			}
		}
		
	}
	public void affichage () {
		String[][] table = new String[auto.size()+2][alphabet.size()+1];
		 
		for (int i=0;i<alphabet.size();i++) {
			table[0][i+1] = " " +alphabet.get(i)+ " ";
			
		}
		for (int i=0;i<auto.size();i++) {
			table[i+1][0] =" "+ String.valueOf(auto.get(i).getNumero() +" ");
		}
		for (int i=1;i<table.length-1;i++) {
			ArrayList<Transition> trans = auto.get(i-1).getTransitions();
			for (int j=1;j<table[0].length;j++) {
				boolean vide = true;
				for (int k=0;k< trans.size();k++) {
					if (alphabet.get(j-1).equals(String.valueOf(trans.get(k).getLettre()))) {
						if (trans.get(k).isNext() == true)
						{
							table[i][j] = String.valueOf(auto.get(i-1).getNumero()) + "," + String.valueOf(auto.get(i-1).getNumero()+1);
							vide = false;
						}
						else if  (trans.get(k).isNext() == false) {
							table[i][j] = " "+String.valueOf(auto.get(i-1).getNumero()+1 + " ");
							vide = false;
						}
						}
					
					}
				if ( vide == true)
				{
					table[i][j] = " / ";
				}

			}	
			}
		for (int j=1;j<table[0].length;j++) {
			table[table.length-1][j] =" / ";
		}
		table[table.length-1][0] = " "+String.valueOf(table.length-1)+ " ";
		table[0][0] = " / ";
	
	for (int i=0;i<table.length;i++) {
		for (int j=0;j<table[0].length;j++) {
			
			System.out.print("|  " + table[i][j] + "  |");
		}
		System.out.println("");
	}
}	
	
	
	
	
	@Override
	public String toString() {
		return "RegexpParser [auto=" + auto.toString() + "]";
	}
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
	    System.out.print("Entrer une regex :");
	    String test =   sc.next();
		String testa = "[ze]*[xqdi]?[v]*[g]?[d]*";
		RegexpParser parser = new RegexpParser(test);
		parser.readRegexp();
		parser.makeAlphabet();
		parser.creation();
		System.out.println(parser);
		parser.affichage();
	}
}
