import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		CarnetAdresses carnet = null;

		FileInputStream fin = null;
		ObjectInputStream ois = null;
		String carnetFile = "carnet.ser";
		/*
		 * On charge notre carnet d'adresses depuis le fichier, on le crée s'il n'existe pas
		 */
		try {
			fin = new FileInputStream("/tmp/" + carnetFile);
			ois = new ObjectInputStream(fin);
			carnet = (CarnetAdresses) ois.readObject();
			System.out.println("Carnet "+ carnet.getNom() + " chargé depuis le fichier : " + carnetFile );
		} catch (Exception ex) {
			System.out.println("Aucun carnet trouvé, création d'un nouveau carnet ...");
			carnet = new CarnetAdresses("Test",3);
		} finally {

			if (fin != null) {
				try {
					fin.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		
		// Affichage et navigation dans le menu
		String saisie = "";
		do {
			Main.afficherMenu();
			saisie = scanner.nextLine();
			
			switch(saisie) {
				case "1":
					Main.ajouterUneFiche(carnet);
					break;
					
				case "2":
					Main.supprimerUneFiche(carnet);
					break;
					
				case "3":
					Main.rechercherUneFiche(carnet);
					break;
					
				case "4":
					Main.trierLeTableau(carnet);
					break;
					
				case "6":
					Main.afficherTableau(carnet);
					break;
					
				case "5":
					System.out.println("Bye !");
					break;
					
				default:
					System.out.println("Option invalide");
					
			}
			try {
				carnet.sauvegarder();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}while(saisie != "5");
	}
	
	/**
	 * Cette fonction permet d'afficher le menu
	 */
	public static void afficherMenu() {
		System.out.println("1 - Ajouter une fiche");
		System.out.println("2 - Supprimer une fiche");
		System.out.println("3 - Rechercher une fiche");
		System.out.println("4 - Trier le tableau");
		System.out.println("5 - Quitter");
		// System.out.println("6 - Faire afficher le carnet");
	}
	
	/**
	 * Cette fonction permet de faire la saisie et l'ajout d'une nouvelle fiche
	 * @param carnet le carnet dans lequel on ajoute la nouvelle personne
	 */
	public static void ajouterUneFiche(CarnetAdresses carnet) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Saisir nom :");
		String nom = scanner.nextLine();
		
		System.out.println("Saisir prénom :");
		String prenom = scanner.nextLine();
		
		System.out.println("Saisir adresse :");
		String adresse = scanner.nextLine();
		
		Personne personne = new Personne(nom, prenom, adresse);
		carnet.add(personne);	
	}
	
	/**
	 * Cette fonction permet de faire saisir l'index et de supprimer une fiche depuis un carnet d'adresses
	 * @param carnet
	 */
	public static void supprimerUneFiche(CarnetAdresses carnet) {
		if(carnet.getNbPersonnes() == 0) {
			System.out.println("Suppression impossible, le carnet est vide");
		} else {
			Scanner delScan = new Scanner(System.in);
			
			System.out.println("Saisir l'index de la personne a supprimer :");
			int index = delScan.nextInt();
			
			carnet.remove(index);
		}
	}
	
	/**
	 * Saisie et recherche dans le carnet d'adresses
	 * @param carnet
	 */
	public static void rechercherUneFiche(CarnetAdresses carnet) {
		if(carnet.isEmpty()) {
			System.out.println("Recherche impossible, le carnet est vide");
		} else {
			System.out.println("Recherche : ");
			Scanner scanner = new Scanner(System.in);
			String recherche = scanner.nextLine();
			carnet.recherche(recherche);
		}
	}
	
	public static void trierLeTableau(CarnetAdresses carnet) {
		if(carnet.getNbPersonnes() == 0) {
			System.out.println("Tri impossible, le carnet est vide.");
		} else {
			carnet.tri();
		}
	}
	
	public static void afficherTableau(CarnetAdresses carnet) {
		System.out.println(carnet.toString());
	}

}
