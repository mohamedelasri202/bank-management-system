package utilitaire;

public class InputValidator {
	
	public static boolean isValidCompteCode(String code) {
		if (code == null) return false;
		
		return code.matches("CPT-\\d{5}");
		}


		public static boolean isPositiveAmount(double amount) {
		return amount > 0;
		}
		}


