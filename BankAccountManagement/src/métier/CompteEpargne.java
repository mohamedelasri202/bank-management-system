package métier;

import utilitaire.InsufficientFundsException;

public class CompteEpargne extends Compte {
		
	private final double tauxInteret; 


		public CompteEpargne(String code, double soldeInitial, double tauxInteret) {
			
		super(code, soldeInitial);
		
		this.tauxInteret = tauxInteret;
		
		}


		public double getTauxInteret() { return tauxInteret; }


		
		public void retirer(double montant, String destination) throws InsufficientFundsException, IllegalArgumentException {
		
			if (montant <= 0) throw new IllegalArgumentException("Le montant doit être positif.");
		
			if (this.solde < montant) {
				
				throw new InsufficientFundsException("Retrait refusé : solde insuffisant.");
		}
			this.solde -= montant;
			
			Retrait retrait = new Retrait(montant, destination);
			
			this.ajouterOperation(retrait);
		}


		
		public double calculerInteret() {
			
		return this.solde * (this.tauxInteret / 100.0);
		
		}


		
		public void afficherDetails() {
			
			System.out.println("--- Compte Épargne ---");
		
		System.out.println("Code : " + this.code);
		
			System.out.printf("Solde : %.2f - Taux d'intérêt : %.2f%%%n", this.solde, this.tauxInteret);
		}
		
		}
		





