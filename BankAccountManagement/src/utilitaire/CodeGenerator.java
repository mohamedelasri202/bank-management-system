package utilitaire;
import java.util.Random;
public class CodeGenerator {
	
	private static final Random RAND = new Random();
	
		public static String generateCompteCode() {
			
		int number = 10000 + RAND.nextInt(90000); 
		
		return "CPT-" + number;
		
		}
		
		
		}


