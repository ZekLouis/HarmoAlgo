package carnet.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Personne implements Serializable{
	private StringProperty nom;
	private StringProperty prenom;
	private StringProperty adresse;
	
	public Personne() {
		this(null, null, null);
	}

	public Personne(String nom, String prenom, String adresse) {
		this.nom = new SimpleStringProperty(nom);
		this.prenom = new SimpleStringProperty(prenom);
		this.adresse = new SimpleStringProperty(adresse);
	}
	
	public String getNom() {
		return nom.get();
	}

	public StringProperty getNomProperty() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom.set(nom);
	}
	
	public String getPrenom() {
		return prenom.get();
	}
	
	public StringProperty getPrenomProperty() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom.set(prenom);
	}

	public String getAdresse() {
		return adresse.get();
	}
	
	public StringProperty getAdresseProperty() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse.set(adresse);
	}

	@Override
	public String toString() {
		return "Personne [nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + "]";
	}
	
}
