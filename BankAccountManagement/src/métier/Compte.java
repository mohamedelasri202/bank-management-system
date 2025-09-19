package métier;

import java.util.ArrayList;
import java.util.List;



public abstract class Compte {
	
	protected final String code;
	protected double solde;
	protected final ArrayList<Operation> listeOperations;


	public Compte(String code, double soldeInitial) {
		this.code = code;
		
		this.solde = soldeInitial;
		
		this.listeOperations = new ArrayList<>();
}


	public String getCode() { return code; }
	
	public double getSolde() { return solde; }
	
	public List<Operation> getListeOperations() { return listeOperations; }


	public void ajouterOperation(Operation op) {
		
		this.listeOperations.add(op);
}



	public abstract void retirer(double montant, String destination) throws Exception;
	
	public abstract double calculerInteret();
	
	public abstract void afficherDetails();
}