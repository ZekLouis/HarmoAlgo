package paint.view;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import paint.MainApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;

public class HomeController {
	
	@FXML
    private ImageView imageView;
	
	@FXML
	private ScrollPane scrollPane;

    private MainApp mainApp;
    
    private boolean okClicked = false;

    DoubleProperty zoomProperty = new SimpleDoubleProperty(200);
    
    private ColorAdjust grayscale;
    
    private Image image;
    
    private String fileName;
    @FXML
    private AnchorPane anchorPaneViewParent;
    final Rectangle rectBound = new Rectangle(0, 0);
    

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
    }
    
    @FXML
    private void handleCharger() {
		FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilterJpg = new FileChooser.ExtensionFilter("Images (*.jpg)", "*.jpg");
        fileChooser.getExtensionFilters().add(extFilterJpg);
        
        FileChooser.ExtensionFilter extFilterPng = new FileChooser.ExtensionFilter("Images (*.png)", "*.png");
        fileChooser.getExtensionFilters().add(extFilterPng);
        
        FileChooser.ExtensionFilter extFilterJpeg = new FileChooser.ExtensionFilter("Images (*.jpeg)", "*.jpeg");
        fileChooser.getExtensionFilters().add(extFilterJpeg);
        
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        fileName = file.getName();
        
        zoomProperty.addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable arg0) {
                imageView.setFitWidth(zoomProperty.get() * 4);
                imageView.setFitHeight(zoomProperty.get() * 3);
            }
        });

        scrollPane.addEventFilter(ScrollEvent.ANY, new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                if (event.getDeltaY() > 0) {
                    zoomProperty.set(zoomProperty.get() * 1.1);
                } else if (event.getDeltaY() < 0) {
                    zoomProperty.set(zoomProperty.get() / 1.1);
                }
            }
        });

        if (file != null) {
    		image = new Image(file.toURI().toString());
    		imageView.setImage(image);
    		mainApp.setNomFenetre(file.getName());
            imageView.setFitHeight(scrollPane.getHeight());
            imageView.setFitWidth(scrollPane.getWidth());
            imageView.setPreserveRatio(true);
            scrollPane.setContent(imageView);
            scrollPane.setPannable(true);
            
            // reset color effect
            if(imageView.getEffect() == null) {
    			grayscale = new ColorAdjust();
    		} else {
    			grayscale = (ColorAdjust) imageView.getEffect();
    		}
    		
			grayscale.setSaturation(0.0);
    		
	        imageView.setEffect(grayscale);
        }
    }
    
    @FXML
    public void handleBlackAndWhite() {
    	if (imageView.getImage() == null) {
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Erreur");
	        alert.setHeaderText("Aucune image");
	        alert.setContentText("Action impossible, aucune image n'est ouverte.");
	
	        alert.showAndWait();
    	} else {
    		if(imageView.getEffect() == null) {
        		grayscale = new ColorAdjust();
    		} else {
    			grayscale = (ColorAdjust) imageView.getEffect();
    		}
    		
    		if (grayscale.getSaturation() == 0.0) {
    			grayscale.setSaturation(-1.0);
    		} else {
    			grayscale.setSaturation(0.0);
    		}
            imageView.setEffect(grayscale);
    	}
    }
    
    @FXML
    private void handleResetZoom() {
    	if (imageView.getImage() == null) {
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Erreur");
	        alert.setHeaderText("Aucune image");
	        alert.setContentText("Action impossible, aucune image n'est ouverte.");
	
	        alert.showAndWait();
    	} else {
    		imageView.setScaleX(1);
    		imageView.setScaleY(1);
    		imageView.setFitHeight(scrollPane.getHeight());
            imageView.setFitWidth(scrollPane.getWidth());
            imageView.setPreserveRatio(true);
            zoomProperty.set(200);
    	}
    }
    
    @FXML
    private void handleSave() {
    	if (imageView.getImage() == null) {
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Erreur");
	        alert.setHeaderText("Aucune image");
	        alert.setContentText("Action impossible, aucune image n'est ouverte.");
	
	        alert.showAndWait();
    	} else {
        	FileChooser fileChooser = new FileChooser();
        	fileChooser.setInitialFileName(fileName);
        	fileChooser.setTitle("Sauvegarder l'image");
        	FileChooser.ExtensionFilter extFilterJpg = new FileChooser.ExtensionFilter("Images (*.jpg)", "*.jpg");
            fileChooser.getExtensionFilters().add(extFilterJpg);
            
            FileChooser.ExtensionFilter extFilterPng = new FileChooser.ExtensionFilter("Images (*.png)", "*.png");
            fileChooser.getExtensionFilters().add(extFilterPng);
            
            FileChooser.ExtensionFilter extFilterJpeg = new FileChooser.ExtensionFilter("Images (*.jpeg)", "*.jpeg");
            fileChooser.getExtensionFilters().add(extFilterJpeg);
        	File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());
        	Image image = imageView.snapshot(null, null);
    	    	
        	try {
    			ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}

    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    

    
}