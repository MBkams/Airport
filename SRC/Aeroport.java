package SRC;

public class Aeroport {
	
	private String IATA;
	private String Name;
	private String country;
	private double latitude;
	private double longitude;
	
	
/* Contructeur Aeroport*/ 
	public Aeroport(String IATA, String Name, String cnty, double lat, double lgt) {
		this.IATA = IATA;
		this.Name = Name;
		this.country= cnty;
		this.latitude = lat;
		this.longitude = lgt;
	}
	
	public String getIATA() {
		return IATA;
	}
	public String getLatitude() {
		return Name;
	}
	public String getLontitude() {
		return country;
	}
	
/* Redefintion de la méthode toString */
	@Override
	public String toString() {
		return "N°" + IATA + " aéroport " + Name+ " se situe à " + country + "." + "Coordonées GPS " + latitude +" " + longitude;
	}
}
