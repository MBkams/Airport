package SRC;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;


/**
 * La classe World contient une liste de tous les aéroports du monde entier.
 * Elle est crée par la lecture d'un fichier CSV.
 * Elle permet de retrouver un aeroport par deux moyens: les coordonées GPS, Code IATA
 * @see Aeroport
 */

public class World {
	
	/**
	 * La liste de tous les aeroports.
	 * 
	 * La liste est de type Aeroport,qui contient les objets 'Aeroport'.
	 * @see Aeroport 
	 */
	public ArrayList<Aeroport> list_apt = new ArrayList<Aeroport>();

	
    /**
     * Constructeur de la classe World pointant vers le fichier contenant la liste des aeroports.
	 * Seuls les aéroports de type "large_airport" sont ajoutés à la liste.
     * @param filename le nom du fichier
     */
	public World (String fileName){
			
		try {
			BufferedReader buf = new BufferedReader(new FileReader(fileName));
			String s = buf.readLine();
			while(s!=null){
				s=s.replaceAll("\"","");
				//Enleve les guillemets qui s ́eparent les champs de donnees GPS.
				String fields[]=s.split(",");
				// Une bonne idee : placer un point d'arret ici pour du debuggage.
				if (fields[1].equals("large_airport")){
					list_apt.add(new Aeroport(fields[9],fields[2],fields[5],Double.parseDouble(fields[12]),Double.parseDouble(fields[11])));
				}
				s = buf.readLine();
			}
		} catch (Exception e){	
			System.out.println("Maybe the file isn't there ?");
			System.out.println(list_apt.get(list_apt.size()-1));
			e.printStackTrace();
		}
	}


	/**
	 * Retourne la distance en en kilomètres entre deux aeroports, en utilisant leur coordonées GPS.
	 * 
	 * @param lat_one la latitude du premier aeroport
	 * @param lgt_one la longitude du premier aeroport
	 * @param lat_two la latitude du premier aeroport
	 * @param lgt_two la longitude du second aeroport
	 * @return la distance en kilomèetres entre deux aeroports
	 */
	public double distance(double lat_one, double lgt_one, double lat_two, double lgt_two){
		return (Math.pow(lat_two - lat_one,2) + Math.pow((lgt_two-lgt_one)*Math.cos((lat_two + lat_one)/2),2));
	}

	/**
	 * Retroune l'aeroport le plus proche selon les coordonées GPS specifiés.
	 * La méthode parcout la liste des aeroports et determine la distance à chaque aeroport à l'aide d'un calcul de distance entre deux coordonées.
	 * L'aeroport avec la distance la plus petite est retourné.
	 * 
	 * @param latitude la latitude des coordonées GPS
	 * @param longitude la longitude des coordonées GPS
	 * @return l'aeroport la plus proche des cordonées spécifiés
	 */
	public Aeroport findNearestAirport(double latitude, double longitude){
		Aeroport apt_nearest = list_apt.get(0);
		double dist_min = distance(latitude, longitude, apt_nearest.getLatitude(), apt_nearest.getLongitude());

		for(Aeroport apt_one : list_apt)
		{
			if(distance(latitude, longitude, apt_one.getLatitude(), apt_one.getLongitude())< dist_min){
				apt_nearest = apt_one;
				dist_min = distance(latitude, longitude, apt_one.getLatitude(), apt_one.getLongitude());
			}

		}
		return apt_nearest;
	}

	/**
	 * Retroune l'aeroport selon le code IATA spécifié.
	 * La méthode parcout la liste des aeroports et recherche le code IATA spéciifé.
	 * 
	 * @param code IATA le code IATA 
	 * @return l'aeroport du code IATA spécofié
	 */
	public Aeroport findByCode(String code_IATA){
		for(Aeroport apt : list_apt)
		{
			if(code_IATA.equals(apt.getIATA())){
				return apt;	
			}
		}
		return null;
	}

	/**
	 * Retourne la liste des aeroports du monde.
	 * 
	 * @return la liste de aeroports
	 */
	public ArrayList<Aeroport> getList(){
		return list_apt;
	}


}
