package carnet.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import carnet.model.Personne;
import carnet.model.CarnetAdresses;
import carnet.model.CompPersonne;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import carnet.MainApp;

public class HomeController {
    @FXML
    private TableView<Personne> personnesTable;
    @FXML
    private TableColumn<Personne, String> prenomColumn;
    @FXML
    private TableColumn<Personne, String> nomColumn;
    @FXML
    private TableColumn<Personne, String> adresseColumn;

    @FXML
    private Label prenomLabel;
    @FXML
    private Label nomLabel;
    @FXML
    private Label adresseLabel;
    @FXML
    private TextField prenomField;
    @FXML
    private TextField nomField;
    @FXML
    private TextField adresseField;
    private CompPersonne c = new CompPersonne();
    
    @FXML
    private TextField rechercheField;

    // Reference to the main application.
    private MainApp mainApp;
    
    private boolean okClicked = false;
    

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public HomeController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
    	prenomColumn.setCellValueFactory(cellData -> cellData.getValue().getPrenomProperty());
    	nomColumn.setCellValueFactory(cellData -> cellData.getValue().getNomProperty());
    	adresseColumn.setCellValueFactory(cellData -> cellData.getValue().getAdresseProperty());
    	
    	// Clear person details.
        showPersonDetails(null);

        // Listen for selection changes and show the person details when changed.
        personnesTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        personnesTable.setItems(mainApp.getPersonnesData());
    }
    
    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     *
     * @param person the person or null
     */
    private void showPersonDetails(Personne personne) {
        if (personne != null) {
            // Fill the labels with info from the person object.
            prenomLabel.setText(personne.getPrenom());
            nomLabel.setText(personne.getNom());
            adresseLabel.setText(personne.getAdresse());

            // TODO: We need a way to convert the birthday into a String!
            // birthdayLabel.setText(...);
        } else {
            // Person is null, remove all the text.
        	prenomLabel.setText("Prénom");
        	nomLabel.setText("Nom");
        	adresseLabel.setText("Adresse");
        }
    }
    
    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeletePerson() {
    	personnesTable.setItems(mainApp.getPersonnesData());
        int selectedIndex = personnesTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
        	personnesTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Aucune sélection");
            alert.setHeaderText("Aucune sélection");
            alert.setContentText("Merci de sélectionner une personne de la liste.");

            alert.showAndWait();
        }
    }
    
    @FXML
    private void handleRecherche() {
    	personnesTable.setItems(mainApp.getPersonnesData());
    	String recherche = rechercheField.getText();
    	System.out.println(recherche);
    	if(recherche == null || recherche == "") {
    		Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Champs non valides");
            alert.setContentText("La recherche ne peut pas être vide");
            alert.showAndWait();
            
    	} else {
    		ObservableList<Personne> recherchePersonnes = FXCollections.observableArrayList();
        	for(Personne personne : mainApp.getPersonnesData()) {
        		if(personne.getNom().matches("(?i)("+recherche+").*")){
            		System.out.println("Index : " + mainApp.getPersonnesData().indexOf(personne) + " - " + personne.toString());
            		recherchePersonnes.add(personne);
        		} else if(personne.getPrenom().matches("(?i)("+recherche+").*")) {
            		System.out.println("Index : " + mainApp.getPersonnesData().indexOf(personne) + " - " + personne.toString());
            		recherchePersonnes.add(personne);
    	        }
        	}
    		personnesTable.setItems(recherchePersonnes);
    	}
    }
    
    @FXML
    private void handleReset() {
    	personnesTable.setItems(mainApp.getPersonnesData());
    }
    
    @FXML
    private void handleCharger() {
    	
    	FileChooser fileChooser = new FileChooser();
    	FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Fichier XML (*.xml)", "*.xml");
    	fileChooser.getExtensionFilters().add(filter);
    	fileChooser.setTitle("Ouvrir un carnet");
    	File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
    	
    	if( file != null ) {
    		mainApp.loadPersonDataFromFile(file);
    	}

    }
    
    @FXML
    public void handleSauvegarder() {
    	FileChooser fileChooser = new FileChooser();
    	FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Fichier XML (*.xml)", "*.xml");
    	fileChooser.getExtensionFilters().add(filter);
    	fileChooser.setTitle("Sauvegarder un carnet");
    	File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());
    	
    	if( file != null ) {
            // Make sure it has the correct extension
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            mainApp.savePersonDataToFile(file);
    	}
    	
    }
    
    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }
    
    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleAjouter() {
    	personnesTable.setItems(mainApp.getPersonnesData());
        if (isInputValid()) {
        	
        	Personne personne = new Personne(nomField.getText(), prenomField.getText(), adresseField.getText());

            okClicked = true;
            mainApp.getPersonnesData().add(personne);
            
            nomField.setText("");
            prenomField.setText("");
            adresseField.setText("");
        }
    }
    
    @FXML
    private void handleTrier() {
    	personnesTable.setItems(mainApp.getPersonnesData());
    	mainApp.getPersonnesData().sort(c);
    }
    
    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (nomField.getText() == null || nomField.getText().length() == 0) {
            errorMessage += "Le champ nom n'est pas valide!\n";
        }
        if (prenomField.getText() == null || prenomField.getText().length() == 0) {
            errorMessage += "Le champ prénom n'est pas valide!\n";
        }
        if (adresseField.getText() == null || adresseField.getText().length() == 0) {
            errorMessage += "La champ adresse n'est pas valide!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {        	
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Champs non valides");
            alert.setHeaderText("Veuillez corriger ces champs : ");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}