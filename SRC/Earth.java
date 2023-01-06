package SRC;

/**
 * La classe Earth est héritiaire de la classe Group.
 * Elle affiche un modèle de la Terre tournant sur elle-meme sur une fenetre JavaFx.
 */

public class Earth extends Group{

//Le rayon de rotation de la terre
private Rotate ry;

//L'objet Sphere representant la Terre
private Sphere sph;

//Une liste de sphere 
private ArrayList<Sphere> yellowSphere = new ArrayList<Sphere>();

   /**
   * Contructeur de la classe Earth
   *
   * @param rayon Le rayon de rotation de la Terre autour de son axe
   * @param sphere L'objet Sphere représentant la Terre
   */
   public Earth(Rotate rayon,Sphere sphere){
      this.ry = rayon;
      this.sph = sphere;

      //Ajout de la classe construteur Earth au objet Group
      this.getChildren().add(sph);

        // Modification de l'objet Rotate sur l'axe Y
       ry.setAxis(ROTATE.Y_AXIS);

       AnimationTimer animationTimer = new AnimationTimer() {
       @Override
       public void handle(long time) {
         System.out.println("Valeur de time : " + time);
         ry.setAngle(...); // A compl ́eter
       }
       };
       animationTimer.start();

   }

     // Création d'un matériau Phong
     PhongMaterial material = new PhongMaterial();
     material.setDiffuseMap(new Image(getClass().getResourceAsStream("earth_lights_4800.png")));
     
     sph.setMaterial(material);

      

}
