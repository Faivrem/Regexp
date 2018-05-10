
public class Transition {
	char lettre;
	boolean next;
	
	public char getLettre() {
		return lettre;
	}

	public void setLettre(char lettre) {
		this.lettre = lettre;
	}

	

	

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public Transition(char c_lettre , boolean c_next ) {
		this.lettre = c_lettre;
		this.next  = c_next;
		
	}

	@Override
	public String toString() {
		return "Transition [lettre=" + String.valueOf(lettre) + ", next=" + String.valueOf(next) + "]";
	}
	
}
