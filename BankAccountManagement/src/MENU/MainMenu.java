package MENU;

import utilitaire.InputValidator;
import java.util.List;
import java.util.Scanner;
import métier.*;

import métier.AccountRepository;


public class MainMenu {
	
	    private Scanner scanner;
	    private AccountServ service;
	    private AccountRepository repo;

	    public MainMenu() {
	    	
	        this.scanner = new Scanner(System.in);
	        this.repo = new AccountRepository();
	        this.service = new AccountServ(repo);
	    }

	    public void start() {
	        boolean running = true;
	        while (running) {
	            printMenu();
	            String choice = scanner.nextLine();
	            try {
	                switch (choice) {
	                    case "1": createCompteCourant(); break;
	                    case "2": createCompteEpargne(); break;
	                    case "3": faireVersement(); break;
	                    case "4": faireRetrait(); break;
	                    case "5": faireVirement(); break;
	                    case "6": consulterSolde(); break;
	                    case "7": afficherOperations(); break;
	                    case "8": listerComptes(); break;
	                    case "9": running = false; System.out.println("Au revoir."); break;
	                    default: System.out.println("Choix invalide."); break;
	                }
	            } catch (Exception e) {
	                System.out.println("Erreur : " + e.getMessage());
	            }
	            System.out.println();
	        }
	        scanner.close();
	    }

	    private void printMenu() {
	        System.out.println("=== Système de Gestion de Comptes Bancaires ===");
	        System.out.println("1. Créer un compte courant");
	        System.out.println("2. Créer un compte épargne");
	        System.out.println("3. Effectuer un versement");
	        System.out.println("4. Effectuer un retrait");
	        System.out.println("5. Effectuer un virement");
	        System.out.println("6. Consulter le solde d'un compte");
	        System.out.println("7. Consulter la liste des opérations d'un compte");
	        System.out.println("8. Lister tous les comptes");
	        System.out.println("9. Quitter");
	        System.out.print("Choix: ");
	    }

	    private void createCompteCourant() {
	    	
	        System.out.print("Solde initial: ");
	        
	        double solde = readDouble();
	        
	        System.out.print("Découvert autorisé: ");
	        
	        double decouvert = readDouble();
	        
	        
	        String code = service.createCompteCourant(solde, decouvert);
	        
	        System.out.println("Compte courant créé avec code: " + code);
	    }

	    private void createCompteEpargne() {
	    	
	        System.out.print("Solde initial: ");
	        
	        double solde = readDouble();
	        
	        System.out.print("Taux d'intérêt (en %): ");
	        
	        double taux = readDouble();
	        
	        String code = service.createCompteEpargne(solde, taux);
	        
	        System.out.println("Compte épargne créé avec code: " + code);
	        
	    }

	    private void faireVersement() throws Exception {
	    	
	        System.out.print("Code du compte destinataire (format CPT-12345): ");
	        
	        String code = scanner.nextLine().trim();
	        
	        if (!InputValidator.isValidCompteCode(code)) { System.out.println("Format code invalide."); return; }
	        
	        System.out.print("Montant: ");
	        
	        double montant = readDouble();
	        
	        System.out.print("Source (ex: Salaire): ");
	        
	        String source = scanner.nextLine();
	        
	        service.versement(code, montant, source);
	        
	        System.out.println("Versement effectué.");
	    }

	    private void faireRetrait() throws Exception {
	    	
	        System.out.print("Code du compte (format CPT-12345): ");
	        
	        String code = scanner.nextLine().trim();
	        
	        if (!InputValidator.isValidCompteCode(code)) { System.out.println("Format code invalide."); return; }
	        
	        System.out.print("Montant: ");
	        
	        double montant = readDouble();
	        
	        System.out.print("Destination (ex: ATM): ");
	        
	        String dest = scanner.nextLine();
	        
	        service.retrait(code, montant, dest);
	        
	        System.out.println("Retrait effectué.");
	    }

	    private void faireVirement() throws Exception {
	    	
	        System.out.print("Code du compte émetteur: ");
	        
	        String from = scanner.nextLine().trim();
	        
	        if (!InputValidator.isValidCompteCode(from)) { System.out.println("Format code invalide."); return; }
	        
	        System.out.print("Code du compte destinataire: ");
	        
	        String to = scanner.nextLine().trim();
	        
	        if (!InputValidator.isValidCompteCode(to)) { System.out.println("Format code invalide."); return; }
	        
	        System.out.print("Montant: ");
	        
	        double montant = readDouble();
	        
	        service.virement(from, to, montant);
	        
	        System.out.println("Virement effectué.");
	    }

	    private void consulterSolde() throws Exception {
	        System.out.print("Code du compte: ");
	        
	        String code = scanner.nextLine().trim();
	        
	        if (!InputValidator.isValidCompteCode(code)) { System.out.println("Format code invalide."); return; }
	        
	        double solde = service.getSolde(code);
	        
	        System.out.printf("Solde du compte %s : %.2f%n", code, solde);
	    }

	    private void afficherOperations() throws Exception {
	    	
	        System.out.print("Code du compte: ");
	        
	        String code = scanner.nextLine().trim();
	        
	        if (!InputValidator.isValidCompteCode(code)) { System.out.println("Format code invalide."); return; }
	        
	        Compte c = repo.findByCode(code);
	        
	        System.out.println("Opérations du compte " + code + ":");
	        
	        if (c.getListeOperations().isEmpty()) {
	        	
	            System.out.println("Aucune opération pour ce compte.");
	        } else {
	            for (Operation op : c.getListeOperations()) {
	            	
	                System.out.println(op.toString());
	            }
	        }
	    }

	    private void listerComptes() {
	    	
	        List<Compte> comptes = repo.findAll();
	        
	        if (comptes.isEmpty()) { System.out.println("Aucun compte."); return; }
	        
	        System.out.println("Liste des comptes :");
	        
	        for (Compte c : comptes) {
	        	
	            c.afficherDetails();
	            System.out.println();
	        }
	    }

	    private double readDouble() {
	        while (true) {
	            try {
	                String line = scanner.nextLine();
	                
	                return Double.parseDouble(line);
	                
	            } catch (NumberFormatException e) {
	            	
	                System.out.print("Entrée invalide. Entrez un nombre: ");
	            }
	        }
	    }

	    public static void main(String[] args) {
	        new MainMenu().start();
	    }
	}
