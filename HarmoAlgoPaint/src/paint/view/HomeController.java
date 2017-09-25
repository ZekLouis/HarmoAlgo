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

    final DoubleProperty zoomProperty = new SimpleDoubleProperty(200);
    
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
    
    private void initDrawEvents2() {
    		rectBound.setFill(Color.TRANSPARENT);
        rectBound.setStroke(Color.GOLD);
        ScrollPane scp = new ScrollPane();
        HBox root = new HBox(15);
        scp.setContent(root);
       

        ///////////////////////////////////////////////
        anchorPaneViewParent.addEventFilter(MouseEvent.ANY, new EventHandler<MouseEvent>() {
           @Override
               public void handle(MouseEvent event) {

                   if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
                       if (rectBound.getParent() == null) {
                           rectBound.setWidth(0.0); rectBound.setHeight(0.0);
                           rectBound.setLayoutX(event.getX()); rectBound.setLayoutY(event.getY()); // setX or setY
                           anchorPaneViewParent.getChildren().add(rectBound);
                       }
                   } else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {

                   } else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                       rectBound.setWidth(event.getX() - rectBound.getLayoutX());
                       rectBound.setHeight(event.getY() - rectBound.getLayoutY());
                   } else if (event.getEventType() == MouseEvent.MOUSE_CLICKED
                           && event.getButton() == MouseButton.SECONDARY) {
                       if (rectBound.getParent() != null) {
                    	   	anchorPaneViewParent.getChildren().remove(rectBound);
                       }
                   } else if (event.getEventType() == MouseEvent.MOUSE_CLICKED
                           && event.getButton() == MouseButton.PRIMARY && event.getClickCount() > 1) {
                       //////////////// i crop here //////////////
                       /*PixelReader reader = imageView1.getImage().getPixelReader();
                       WritableImage newImage = new WritableImage(reader, (int) rectBound.getLayoutX(),
                               (int) rectBound.getLayoutY(),
                               (int) rectBound.getWidth(),
                               (int) rectBound.getHeight());

                       root.getChildren().add(new ImageView(newImage));*/
                   }

               }
       });
        //////////////////////////////////////////////
        root.getChildren().add(anchorPaneViewParent);
    }
    
    private void initDrawEvents() {
        final double maxX = imageView.getImage().getWidth();
        final double maxY = imageView.getImage().getHeight();
        //imageView.setVisible(false);
        //AnchorPane anchorRoot = anchorPaneViewParent;

        
        imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                System.out.println("Dragged, x:" + me.getSceneX() + " y:" + me.getSceneY());
            		Rectangle rect = new Rectangle(me.getX(),me.getY(),100,100);
            		rect.setFill(Color.RED);
            		rect.setStroke(Color.RED);
            		rect.setStrokeWidth(2);
                /*Line line = new Line(initX, initY, me.getSceneX(), me.getSceneY());
                line.setFill(null);
                line.setStroke(Color.RED);
                line.setStrokeWidth(2);*/
            		rect.setVisible(true);
            		anchorPaneViewParent.getChildren().add(rect);

            }
        });
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
            //scrollPane.setPannable(true);
            
            // reset color effect
            if(imageView.getEffect() == null) {
        			grayscale = new ColorAdjust();
	    		} else {
	    			grayscale = (ColorAdjust) imageView.getEffect();
	    		}
	    		
    			grayscale.setSaturation(0.0);
	    		
	        imageView.setEffect(grayscale);
	        
	        this.initDrawEvents2();
        }
    }
    
    @FXML
    public void handleBlackAndWhite() {
    		
    		
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
    
    @FXML
    private void handleResetZoom() {
    		imageView.setScaleX(1);
		imageView.setScaleY(1);
    		imageView.setFitHeight(scrollPane.getHeight());
        imageView.setFitWidth(scrollPane.getWidth());
        imageView.setPreserveRatio(true);
    }
    
    @FXML
    private void handleSave() {
	    	FileChooser fileChooser = new FileChooser();
	    	fileChooser.setInitialFileName(fileName);
	    	fileChooser.setTitle("Sauvegarder l'image");
	    	File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());
	    	Image image = imageView.snapshot(null, null);
	    	
	    	try {
			ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    private void handleDragEntered() {
    		System.out.println("drag entered");
    }
    
    @FXML
    private void handleDragExited() {
    		System.out.println("drag exited");
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