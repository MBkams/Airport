package SRC;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Interface extends Application {

    private Earth earth;
    private Rotate ry;
    private Sphere sph;

@Override
public void start(Stage primaryStage) throws Exception{

    earth = new Earth(ry,sph):
    
    Scene theScene = new Scene(new Pane(earth), 600, 400,true);

    // Afichage de la fenetre
    primaryStage.setTitle("Hello world");
    primaryStage.setScene(theScene);
    primaryStage.show();

    // Ajout d'une perspective de camera 
    PerspectiveCamera camera = new PerspectiveCamera(true);
    camera.setTranslateZ(-1000);
    camera.setNearClip(0.1);
    camera.setFarClip(2000.0);
    camera.setFieldOfView(35);
    theScene.setCamera(camera);

    theScene.addEventHandler(MouseEvent.ANY, event -> {
        if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
            System.out.println("Clicked on : (" event.getSceneX()+ ", "+ event.getSceneY()+")");
        }
        if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
            // Translation sur l'axe Z à vérifier
            camera.getTransforms().add(new Translate(0,0,50)); 
        }
    };

}
    public static void main(String[] args) {
    launch(args);
}
}

