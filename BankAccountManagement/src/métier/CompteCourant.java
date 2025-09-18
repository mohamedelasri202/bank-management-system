package métier;


import utilitaire.InsufficientFundsException;


 public class CompteCourant extends Compte {
	 private final double decouvert;


	public CompteCourant(String code, double soldeInitial, double decouvert) {
		super(code, soldeInitial);
		this.decouvert = decouvert;
}


	public double getDecouvert() { return decouvert; }



	public void retirer(double montant, String destination) throws InsufficientFundsException, IllegalArgumentException {
		
		if (montant <= 0) throw new IllegalArgumentException("Le montant doit être positif.");
		
		double finalSolde = this.solde - montant;
		
		if (finalSolde < -this.decouvert) {
			
			throw new InsufficientFundsException("Retrait refusé : découvert autorisé dépassé.");
			
		}
		
		this.solde = finalSolde;
		
		Retrait retrait = new Retrait(montant, destination);
		
		this.ajouterOperation(retrait);
}


		public double calculerInteret() {
			return 0.0; 
}


		public void afficherDetails() {
			
			System.out.println("--- Compte Courant ---");
			
			System.out.println("Code : " + this.code);
			
			System.out.printf("Solde : %.2f - Découvert autorisé : %.2f%n", this.solde, this.decouvert);
}
}