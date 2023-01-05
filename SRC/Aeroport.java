package SRC;

/**
 * La classe Aeroport définit ce qu'est un aeroport.
 * La défintion comporte un nom, une localisation, des coordonées GPS et un code IATA.
 * Le code IATA est un code unique attribué à chaque aeroport dans le monde.
 */

public class Aeroport {
	
	//Le code IATA de l'aeorport
	private String IATA;

	//Le nom de l'aeroport
	private String Name;

	//Le pays ou se situe l'aeroport
	private String country;

    //La latitude GPS de l'aeroport
	private double latitude;

    //La longitude GPS de l'aeroport
	private double longitude;
	
   /**
     * Constructeur de la classe Aeroport.
     *
     * @param IATA le code IATA de l'aeroport
     * @param Name le nom de l'aeroport
     * @param cnty le pays où se situe l'aeroport
     * @param lat la latitude GPS de l'aeroport
     * @param lgt la longitude GPS de l'aeroport
     */
	public Aeroport(String IATA, String Name, String cnty, double lat, double lgt) {
		this.IATA = IATA;
		this.Name = Name;
		this.country= cnty;
		this.latitude = lat;
		this.longitude = lgt;
	}
	
	
	/** 
	 * Retoune le code IATA l'aeroport.
	 * 
	 * @return Le code IATA, sous forme d'une chaine de caractères.
	 */
	public String getIATA() {
		return IATA;
	}
	
	/** 
	 * Retourne la latitude GPS de l'aeroport.
	 * 
	 * @return la latitude GPS, sous forme d'un double.
	 */
	public double getLatitude() {
		return latitude;
	}
	
	/** 
	 * Retourne la longitude GPS de l'aeroport.
	 * 
	 * @return la longitude GPS, sous forme d'un double.
	 */
	public double getLongitude() {
		return longitude;
	}
	

	/** 
	 * Redefintion de la méthode toString pour afficher les informations de l'aeroport.
	 * 
	 * @return une chaine de caractère contenant le code IATA, le nom, la localisation, 
	 * 		   les cordonnées GPS de l'aeroport  
	 */
	@Override
	public String toString() {
		return "N°" + IATA + " aéroport " + Name+ " se situe à " + country + "." + "Coordonées GPS " + latitude +" " + longitude;
	}
}
