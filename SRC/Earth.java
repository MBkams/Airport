package SRC;

import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

/**
 * La classe Earth est héritiaire de la classe Group.
 * Elle affiche un modèle de la Terre tournant sur elle-meme sur une fenetre JavaFx.
 * 
 */

public class Earth extends Group{

  //Le rayon de rotation de la terre
  private Rotate ry = new Rotate();

  /**
   * Obtient l'objet Sphere représentant la Terre.
   * @return l'objet Sphere représentant la Terre.
  */
  public Sphere getEarth(){
      return sph;
  };

  //L'objet Sphere representant la Terre
  private Sphere sph; 

  //Une liste de sphere jaunes 
  private ArrayList<Sphere> yellowSphere;

   /**
   * Contructeur de la classe Earth qui définit une représentation 3D sphérique de la Terre.
   * 
   */
    public Earth(){

      //Initialise la liste des spheres jaunes
      yellowSphere = new ArrayList<Sphere>();
      
      //Initialise la sphere représentant la Terre avec 300 pixels de rayon
      sph = new Sphere(300);

      //Création d'un matériau de type ombrage de Phong
      PhongMaterial material = new PhongMaterial();
      
      /*
      * Mapping de l'image, radiance, texture, réflexion sur le matériel
      */
      material.setDiffuseMap(new Image("file:./data/earth_lights_4800.png"));
      material.setBumpMap(new Image("file:./data/earth_lights_4800.png"));
      material.setSpecularMap(new Image("file:./data/earth_lights_4800.png"));
      material.setSelfIlluminationMap(new Image("file:./data/earth_lights_4800.png"));

      //Mapping du matériel sur le sphère
      sph.setMaterial(material);

      //Ajout de la classe construteur Earth au objet Group
      this.getChildren().add(sph);

      //Ajout du rayon sur la classe Earth
      this.getTransforms().add(ry);

      /*
       * Mise en rotation de la Terre avec la classe AnimatationTimer
       */
      AnimationTimer animationTimer = new AnimationTimer() {
        @Override
        public void handle(long time) {
          //System.out.println("Valeur de time : " + time);
          // Rotation sur l'axe Y pour obtenir une revolution
          ry.setAxis(new Point3D(0, 1, 0));

          //Vitesse de rotation de l'angle par rapport au temps
          ry.setAngle(time/(15*Math.pow(10, 7)));
        }
        };
        animationTimer.start();

    }
    
    /**
       * Crée une nouvelle sphère avec une couleur et l'ajoute à la scène
       * @param a : Objet Aeroport qui contient les valeurs de latitude et de longitude
       * @param color : La couleur de la sphère
       * @return : L'objet sphère créé
       * 
       */
      public Sphere createSphere(Aeroport a,Color color){
        
        //Création d'un matériau de type ombrage de Phong
        PhongMaterial mat = new PhongMaterial();
        
        /*
         * Mapping de la couleur sur le matériel
         */
        mat.setSpecularColor(color);
        mat.setDiffuseColor(color);

        //Creation d'un sphere de rayon 2
        Sphere coloredSphere = new Sphere(2);

        //Mapping du matériel sur le sphère
        coloredSphere.setMaterial(mat);

        //Récupération des coordonées GPS
        double latitude=Math.toRadians(a.getLatitude()-13);
        double longitude=Math.toRadians(a.getLongitude());

        /**
         * Correspondance aux valeurs de latitude et de longitude de l'aéroport
         * @param latitude : La valeur de latitude en radians
         * @param longitude : La valeur de longitude en radians
         * @param coloredSphere : L'objet sphère à transformer
         */
        Translate Axez= new Translate(300.*Math.cos(latitude)*Math.sin(longitude),-300*Math.sin(latitude),-300*Math.cos(latitude)*Math.cos(longitude));
        coloredSphere.getTransforms().add(Axez);

        /**
          * Rotation de la sphère autour de l'axe X pour correspondre à la latitude de l'aéroport
          * @param Rotx : Rotation autour de l'axe X avec un angle de 40 degrés
          */
        Rotate Rotx = new Rotate (40,Rotate.X_AXIS);
        coloredSphere.getTransforms().add(Rotx);

       /**
        * Rotation de la sphère autour de l'axe Y pour correspondre à la longitude de l'aéroport
        * @param Roty : Rotation autour de l'axe Y avec un angle de 73 degrés
        */
        Rotate Roty = new Rotate (73,Rotate.Y_AXIS);
        coloredSphere.getTransforms().add(Roty);

        return coloredSphere;
      }

      public void displayRedSphere(Aeroport a){
        this.getChildren().add(createSphere(a,Color.RED));
    }
   

}
