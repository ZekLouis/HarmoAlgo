import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;


class CompPersonne implements Comparator<Personne> {
	@Override
	public int compare(Personne o1, Personne o2) {
		int res = o1.getNom().compareTo(o2.getNom());
		if(res == 0) {
			return o1.getPrenom().compareTo(o2.getPrenom());
		} else {
			return res;
		}
	}
}

public class CarnetAdresses implements Serializable{
	
	private String nom;
	private ArrayList<Personne> personnes;
	
	public CarnetAdresses(String nom) {
		this.nom = nom;
		this.personnes = new ArrayList<Personne>();
	}
	
	/**
	 * cette fonction permet de supprimer une personne à un index donné
	 * @param index
	 */
	public void remove(int index) {
		this.personnes.remove(index);
	}
	
	/**
	 * Cette fonction permet de trier le carnet d'adresses
	 */
	public void tri() {
		CompPersonne c = new CompPersonne();
		this.personnes.sort(c);
	}
	
	/**
	 * Cette fonction permet d'ajouter une personne au carnet d'adresse
	 * @param personne
	 */
	public void add(Personne personne) {
		this.personnes.add(personne);
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
        for (Personne personne : this.personnes) {
            if(personne.getNom().matches("(?i)("+recherche+").*")){
        		System.out.println("Index : " + this.personnes.indexOf(personne) + " - " + personne.toString());
            } else if(personne.getPrenom().matches("(?i)("+recherche+").*")) {
        		System.out.println("Index : " + this.personnes.indexOf(personne) + " - " + personne.toString());
            }
        }
	}

	@Override
	public String toString() {
		return "CarnetAdresses [nom=" + nom + ", personnes=" + personnes + "]";
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
	
	public boolean isEmpty() { 
		return this.personnes.isEmpty();
	}
	
	public ArrayList<Personne> getPersonnes(){
		return this.personnes;
	}
	
	public int size() {
		return this.personnes.size();
	}
	
}
