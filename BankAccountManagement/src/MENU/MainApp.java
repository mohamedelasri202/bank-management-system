package MENU;

import java.util.Scanner;

import utilitaire.CodeGenerator;
import métier.Comptelogic;

public class MainApp {
	
	
	public static void main(String[] arg) {
		
		
		
		   Scanner scanner = new Scanner(System.in);
		   Comptelogic comptelogic = new Comptelogic();
		   
		   int choix ;
		   
		   do {
			   System.out.println("please chose the prefered options ,by entering the number of the choice ");
			   System.out.println("1.create a current account");
			   System.out.println("2-create  a savings accoutn");
			   System.out.println("3-Make a deposit into an account");
			   System.out.println("4-Make a withdrawal from an account");
			   System.out.println("5-Make a transfer between accounts ");
			   System.out.println("6-Check the account balance");
			   System.out.println("7-View the list of transactions performed on an account");
			   System.out.println("8-exit");
		   
		   }while (!scanner.hasNextInt()) {
               System.out.println("Veuillez entrer un nombre valide !");
               scanner.next();
           }
		   
		   choix =scanner.nextInt();
		   scanner.nextLine();
		   
		   try {
			   switch(choix) {
			   case 1:System.out.println("please enter the intial sode you wanna have");
			   double solde=scanner.nextDouble();
			   double decouvert =scanner.nextDouble();
			   scanner.nextLine();
			   String Codecc = Comptelogic.
			   }
		   }
		   
		
		
		
		
		
	}
	
	

}
