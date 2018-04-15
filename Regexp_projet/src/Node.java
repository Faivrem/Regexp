
public class Node {
	String letters;
	boolean beginPattern;
	String indicator;

	public Node(String letters, boolean beginPattern, String indicator){
		this.letters=letters; // a,b
		this.indicator=indicator;  // ? ou *
		this.beginPattern=beginPattern; // Si présence de ^
	}
	
	@Override
	public String toString(){
		String affichage="";
		affichage = this.letters+" : repeté ";
		if(this.indicator.equals("?")){
			affichage+="1 unique fois";
		}else if (this.indicator.equals("*")){
			affichage+="1 ou plusieurs fois";
		}
		if (this.beginPattern){
			affichage+=" en début de chaine";
		}
		return affichage;
	}
	public String graphics(){
		String affichage="";
		if (this.indicator.equals("?")){
			affichage=this.letters+"-->(   )";
		}
		if (this.indicator.equals("*")){
			affichage="(   )"+"--\n  ^	|\n  ^-"+this.letters+"--|\n";
		}
		return affichage;
	}
}
