import java.util.ArrayList;

public class Noeud {
	int numero;
	boolean debut;
	boolean fin;
	ArrayList<Transition> transitions;
	
	public Noeud(int c_numero, boolean c_debut, boolean c_fin,ArrayList<Transition> c_transitions ) {
		this.numero = c_numero;
		this.debut = c_debut;
		this.fin = c_fin;
		this.transitions = c_transitions;
	}
	public Noeud(int c_numero) {
		this.numero = c_numero;
			}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public boolean isDebut() {
		return debut;
	}

	public void setDebut(boolean debut) {
		this.debut = debut;
	}

	public boolean isFin() {
		return fin;
	}

	public void setFin(boolean fin) {
		this.fin = fin;
	}

	public ArrayList<Transition> getTransitions() {
		return transitions;
	}

	public void setTransitions(ArrayList<Transition> transitions) {
		this.transitions = transitions;
	}
	@Override
	public String toString() {
		return "Noeud " + String.valueOf(this.numero) + " transitions=" + transitions.toString();
	}
}