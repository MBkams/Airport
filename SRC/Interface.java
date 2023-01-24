package SRC;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.scene.paint.Color;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

import javafx.geometry.Point2D;
import javafx.geometry.Point3D;


/**
 * La classe Interface affiche une fenêtre avec un modèle 3D de la Terre en rotation.
 * Elle utilise la classe Earth pour créer un modèle 3D de la Terre, 
 * ajoute une perspective de caméra pour permettre une vue en 3D,
 * et gère les événements de la souris pour permettre une interaction avec l'utilisateur.
 * 
 */ 
public class Interface extends Application {

    ArrayList<Flight> listOfFlight = new ArrayList<Flight>();
    
    //La translation de la Terre sur l'axe Z
    Translate TransAxeZ = new Translate();

    //Variabe de position horizontal X de la souris 
    private double PosMouseX;

    //Variable de position vertical Y de la souris
    private double PosMouseY;

    @Override
    public void start(Stage primaryStage) throws Exception{

    // Instanciation de la classe World
    World w = new World("./data/airport-codes_no_comma.csv");

    // Afichage de la fenetre
    primaryStage.setTitle("Bilal");

    // Instanciation de la classe Earth
    Earth earth = new Earth();
    
    // Creation de la scene
    Scene theScene = new Scene(earth, 600, 400,true);
    theScene.setFill(Color.web("#000011"));

    // Ajout d'une perspective de camera avec un recul de 300 pixels
    PerspectiveCamera camera = new PerspectiveCamera(true);
    camera.setTranslateZ(-1000);
    camera.setNearClip(0.1);
    camera.setFarClip(3000.0);
    camera.setFieldOfView(50);
    theScene.setCamera(camera);

     /*
     * Gestion des evenemnts de la  souris (Clique Gauche)
     * Permet une translation sur l'axe Z avec le clique gauche enfoncé de la souris
     */
    theScene.addEventHandler(MouseEvent.ANY, event -> {
        if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
            System.out.println("Clicked on : ("+ event.getSceneX() + ", "+ event.getSceneY()+")");

        }
        if (event.getButton() == MouseButton.PRIMARY && event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
            //Translation sur l'axe Z avc  une vitesse relative selon l'affichage
            TransAxeZ.setZ((event.getSceneY() - PosMouseY)*0.001);
            camera.getTransforms().add(TransAxeZ);
        }
    });


    /**
     * Gestion des evenemnts de la  souris (Clique Droit)
     * Affichage de l'aeroport le plus proche selon le clique droit de la souris
     */
    theScene.addEventFilter(MouseEvent.ANY, event -> {
        if (event.getButton() == MouseButton.SECONDARY && event.getEventType() == MouseEvent.MOUSE_CLICKED)
        {
            PickResult pickResult = event.getPickResult();
            if (pickResult.getIntersectedNode() != null)
            {
                //Point d'intersection des coordoonées du point cliqués sur la souris
                Point2D click = pickResult.getIntersectedTexCoord();

                /**
                 * Conversion en coordonées GPS des points cliqués à un instant t avec 
                 * les méthodes getX,getY de la classe javafx.geometry.Point2D
                 */
                double latitude=2*Math.toDegrees(Math.atan(Math.exp((0.5-click.getY())/0.2678))-(Math.PI/4));
                double longitude=360*(click.getX()-0.5);
                
                //Recherche dans l'objet w de la classe World de l'aeeroport le plus proche.
                Aeroport apt_near = w.findNearestAirport(latitude, longitude);

                earth.displayRedSphere(apt_near);

                //Affichage sur la console de l'aeroport le plus proche
                System.out.println(apt_near.toString());

                
  
            }
        }

    });


    //Affichage de la scène 
    primaryStage.setScene(theScene);
    primaryStage.show();

    }

/**
 * Point d'entrée de l'application
 * @param args paramètres de lancement de l'application
 */
    public static void main(String[] args) {
    launch(args);
    }
}

