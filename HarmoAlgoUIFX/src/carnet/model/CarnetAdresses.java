package carnet.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CarnetAdresses implements Serializable{
	public ObservableList<Personne> personnes = FXCollections.observableArrayList();
	private String nom;
	private String path;
	
	public CarnetAdresses(String nom, String path) {
		this.nom = nom;
		this.path = path;
	}

	public ObservableList<Personne> getPersonnes() {
		return personnes;
	}

	public void setPersonnes(ObservableList<Personne> personnes) {
		this.personnes = personnes;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
