package métier;
import utilitaire.CodeGenerator;
import utilitaire.InputValidator;

public class AccountServ {
	
	private AccountRepository repo;
	



	public AccountServ(AccountRepository repo) {
		
			this.repo =repo;
		
		}

		
	public String createCompteCourant(double soldeInitial, double decouvert) {
			
		String code;
		
	do {
			
	  code = CodeGenerator.generateCompteCode();
		
		} while (repo.exists(code));
		
		CompteCourant cc = new CompteCourant(code, soldeInitial, decouvert);
		
		  repo.addCompte(cc);
		 
		return code;
		}


		public String createCompteEpargne(double soldeInitial, double tauxInteret) {
		String code;
		do {
			
		code = CodeGenerator.generateCompteCode();
		
		} while (repo.exists(code));
		
		CompteEpargne ce = new CompteEpargne(code, soldeInitial, tauxInteret);
		
		repo.addCompte(ce);
		
		return code;
		}


	
	public void versement(String code, double montant, String source) throws AccountNotFoundException, IllegalArgumentException {
			
		if (!InputValidator.isPositiveAmount(montant)) throw new IllegalArgumentException("Montant doit être positif.");
		
		  Compte c = repo.findByCode(code);
		
		c.solde += montant;
		
		Versement v = new Versement(montant, source);
		
		c.ajouterOperation(v);
		}


		
		public void retrait(String code, double montant, String destination) throws Exception {
		Compte c = repo.findByCode(code);
		c.retirer(montant, destination);
		}


		
		public void virement(String fromCode, String toCode, double montant) throws Exception {
			
		if (!InputValidator.isPositiveAmount(montant)) throw new IllegalArgumentException("Montant doit être positif.");
		
		Compte sender = repo.findByCode(fromCode);
		
		Compte receiver = repo.findByCode(toCode);


		
		sender.retirer(montant, "Virement sortant vers " + toCode);


		
		receiver.solde += montant;
		receiver.ajouterOperation(new Versement(montant, "Virement entrant depuis " + fromCode));
		}


		public double getSolde(String code) throws AccountNotFoundException {
		Compte c = repo.findByCode(code);
		return c.getSolde();
		}
		}




