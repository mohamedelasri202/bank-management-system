package métier;



 import java.util.HashMap;
 import java.util.ArrayList;
 import java.util.List;

public class AccountRepository {
	
	private final HashMap<String, Compte> comptes;


	public AccountRepository() {
		
	this.comptes = new HashMap<>();
	
	}


	public void addCompte(Compte compte) {
		
	comptes.put(compte.getCode(), compte);
	
	}
	
	public boolean exists(String code) {
		
	return comptes.containsKey(code);
	
	}

	
	public Compte findByCode(String code) throws AccountNotFoundException {
		Compte c = comptes.get(code);
		
		if (c == null) throw new AccountNotFoundException("Compte non trouvé: " + code);
		
	return c;
	}


	public List<Compte> findAll() {
		
	return new ArrayList<Compte>(comptes.values());
	
	}

}
