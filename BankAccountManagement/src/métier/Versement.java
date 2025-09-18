package métier;


 public class Versement extends Operation {
	 private final String source;


	 public Versement(double montant, String source) {
		 super(montant);
		 this.source = source == null ? "Inconnu" : source;
	 }


	 public String getSource() { return source; }



	public String toString() {
	
	return String.format("Versement | id=%s | date=%s | montant=%.2f | source=%s",
			
			getNumero().toString(), getDate().toString(), getMontant(), source);
}
}