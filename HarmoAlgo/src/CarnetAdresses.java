import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CarnetAdresses implements Serializable{
	
	private String nom;
	private Personne[] personnes;
	private int nbPersonnes;
	
	public CarnetAdresses(String nom, int size) {
		this.nom = nom;
		this.personnes = new Personne[size];
	}
	
	/**
	 * cette fonction permet de supprimer une personne à un index donné
	 * @param index
	 */
	public void remove(int index) {
		if(index >= 0 && index < this.nbPersonnes) {
			if(index == this.nbPersonnes-1) {
				this.personnes[index] = null;
			} else {
				for(int i = index; i < this.nbPersonnes-1; i++) {
					this.personnes[i] = this.personnes[i+1];
				}
				this.personnes[this.nbPersonnes-1] = null;
			}
			nbPersonnes--;
		} else {
			System.out.println("Index invalide !");
		}	
	}
	
	/**
	 * Cette fonction permet de comparer deux chaines de caractères manuellement
	 * @param string1
	 * @param string2
	 * @return 0 si égale, 1 si la première est plus grande, -1 si la deuxième est plus grande
	 */
	public static int greaterThan(String string1, String string2) {
		int length = string1.length() <= string2.length() ? string1.length() : string2.length();
		
		for(int i = 0; i<length ; i++) {	
			if(string1.charAt(i)>string2.charAt(i)) {
				return 1;
			}else if(string1.charAt(i)<string2.charAt(i)) {
				return -1;
			}
		}
		System.out.println("0");
		return 0;

	}
	
	/**
	 * Cette méthode permet d'échanger deux valeurs dans un tableau
	 * @param personnes
	 * @param index
	 */
	public static void swap(Personne[] personnes, int index) {
		Personne tmp = personnes[index];
		personnes[index] = personnes[index+1];
		personnes[index+1] = tmp;
	}
	
	/**
	 * Cette fonction permet de trier le carnet d'adresses
	 */
	public void tri() {
		Boolean tab_en_ordre = false;
		
		while(!tab_en_ordre){
		    tab_en_ordre = true;
		    
		    for(int i=0 ; i < nbPersonnes-1 ; i++){
		    		int res = CarnetAdresses.greaterThan(personnes[i].getNom(), personnes[i+1].getNom());
		        if(res == 1) {
		            CarnetAdresses.swap(personnes,i);
		            tab_en_ordre = false;
		        }else if(res == 0 && CarnetAdresses.greaterThan(personnes[i].getPrenom(), personnes[i+1].getPrenom()) == 1) {
		        		CarnetAdresses.swap(personnes,i);
		            tab_en_ordre = false;
		        }
		    }
		}
	}
	
	/**
	 * Cette fonction permet d'ajouter une personne au carnet d'adresse
	 * @param personne
	 */
	public void add(Personne personne) {
		if(this.nbPersonnes < personnes.length) {
			this.personnes[nbPersonnes] = personne;
			nbPersonnes++;
		} else {
			System.out.println("Le carnet d'adresses est plein !");
		}
	}
	
	public void list() {
		for(Personne pers : this.personnes) {
			System.out.println(pers.toString());
		}
	}
	
	/**
	 * Cette fonction permet de rechercher dans le carnet d'adresses
	 * @param recherche
	 */
	public void recherche(String recherche) {
		Boolean trouve = false;
		for(int i = 0; i < this.nbPersonnes; i++) {
			if(this.personnes[i].getNom().equals(recherche) || this.personnes[i].getPrenom().equals(recherche)) {
				System.out.println("Index : " + i + " - " + this.personnes[i].toString());
				trouve = true;
			}
		}
		
		if(!trouve) {
			System.out.println("Aucune fiche ne correspond à cette recherche");
		}
	}
	
	@Override
	public String toString() {
		return "CarnetAdresses [nom=" + nom + ", personnes=" + Arrays.toString(personnes) + ", nbPersonnes="
				+ nbPersonnes + "]";
	}

	/**
	 * Cette fonction permet de sauvegarder le carnet d'adresses
	 * @throws IOException
	 */
	public void sauvegarder() throws IOException {
		FileOutputStream fout;
		try {
			fout = new FileOutputStream("/tmp/carnet.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(this);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Personne[] getPersonnes() {
		return personnes;
	}

	public void setPersonnes(Personne[] personnes) {
		this.personnes = personnes;
	}

	public int getNbPersonnes() {
		return nbPersonnes;
	}

	public void setNbPersonnes(int nbPersonnes) {
		this.nbPersonnes = nbPersonnes;
	}
	
	public boolean isEmpty() {
		return this.nbPersonnes == 0;
	}

}
