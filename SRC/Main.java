package SRC;

/**
 * Class main pour tester l'ensemble des classes
 * 
 * @author Bilal MARECAR
 * @version 2.0
 */

public class Main {

	public static void main(String[] args) {

		// Test et création d'une instance de la classe Aeroprt 
		Aeroport apt = new Aeroport("01","CDG","France",500,600);
		System.out.println(apt.toString());

		// Test et création des instance de la classe Word et Aeroport
		World w = new World ("./data/airport-codes_no_comma.csv");
		Aeroport paris = w.findNearestAirport(48.866,2.316);
		Aeroport ory = w.findByCode("ORY");

		double distance = w.distance(48.866,2.316,paris.getLatitude(),paris.getLongitude());
		double distanceORY = w.distance(48.866,2.316,ory.getLatitude(),ory.getLongitude());

		//Affichage sur la console
		System.out.println("Found "+w.getList().size()+" airports.");
		System.out.println(paris);
		System.out.println(distance);
		System.out.println(ory);
		System.out.println(distanceORY);

	

	}

}
