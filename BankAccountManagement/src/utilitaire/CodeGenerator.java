package utilitaire;

public class CodeGenerator {
	
	private String code;
	
	public  CodeGenerator(String code) {
		this.code =code;
	}
	
	public String getcode() {
		return code;
	}
	
	public static String generatecode(String type) {
		 
	  int number =(int)(Math.random()*1000);
		
		 return type +"-"+number;
	}

}
