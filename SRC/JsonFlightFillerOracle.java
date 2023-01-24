package SRC;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import static java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME;


/**
 * La classe JsonFlightFillerOracle est basé sur la classe Json fournis par Oracle.
 * La classe permet de récupérer des informations de l'API Avionstack 
 */
public class JsonFlightFillerOracle{

    /**
	 * La liste des avions.
	 * La liste est de type Avion,qui contient les objets 'Avion'.
	 * @see Flight
	 */
	private ArrayList<Flight> list_avion = new ArrayList<Flight>();

    /**
     * Construteur de la classe JsonFlightFillerOracle permettant de récupérer les vol à distanation ou au départ d'un aéroport
     * @param jsonString format de donnée JSON
     * @param w
     */
    public JsonFlightFillerOracle(String jsonString,World w){
        try {

        InputStream is = new ByteArrayInputStream(jsonString.getBytes(StandardCharsets.UTF_8));
        JsonReader rdr = Json.createReader(is);
        JsonObject obj = rdr.readObject();
        JsonArray results = obj.getJsonArray("data");

        for (JsonObject result : results.getValuesAs(JsonObject.class)) {
            try {

                //Récupération du numéro de l'avion, nom et code de l'avion
                Integer number = Integer.parseInt(result.getJsonObject("flight").getString("number"));
                String airLineCode = result.getJsonObject("airline").getString("iata");
                String airLinename = result.getJsonObject("airline").getString("name");

                //Récupération des codes IATA des aeroports de départ et d'arrivée
                String departureIATA = result.getJsonObject("departure").getString("iata");
                String arrivalIATA = result.getJsonObject("arrival").getString("iata");

                //Récupération des aeroports de départ et d'arrivée par code IATA
                Aeroport departure = w.findByCode(departureIATA);
                Aeroport arrival = w.findByCode(arrivalIATA);

                //Intialisation des heures de départ et d'arrivée
                LocalDateTime departTime = null;
                LocalDateTime arrivalTime = null;

                //Récupération de l'heure de départ 
                String schedule = result.getJsonObject("departure").getString("scheduled");
                
                //Intialisation de l'heure de dpéart avec formatage de donnée 
                //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                departTime = LocalDateTime.parse(schedule, ISO_OFFSET_DATE_TIME);

                //Récupération de l'heure d'arrivée avec formatage de donnée 
                String estimed = result.getJsonObject("arrival").getString("estimated");

                //Récupération de l'heure d'arrivée 
                arrivalTime = LocalDateTime.parse(estimed,ISO_OFFSET_DATE_TIME);

                //Ajout de l'avion trouvé dans la liste 
                list_avion.add(new Flight(number, airLineCode, airLinename, departure, departTime,arrival, arrivalTime));


            }
            catch(Exception e){
            e.printStackTrace();
            }
            }
        } catch (Exception e) {
        e.printStackTrace();
    }
    }
    /**
     * Fonction permettant d'afficher les avions trouvés
     */
    public void showFlights(){
        for (Flight f : list_avion) System.out.println(f);

    }

    /**
     * Test de la classe JsonFlightFillerOracle
     * @param args
     */
    public static void main (String[] args){
        try {

            // Test et instanciation des classes World, JsonFlightFillerOracle
            World w = new World ("./data/airport-codes_no_comma.csv");

            // Test en local  sur un fichier .txt contenant une requete 
            BufferedReader br = new BufferedReader(new FileReader("data/JsonOrly.txt"));
            String test = br.readLine();
            JsonFlightFillerOracle jSonFlightFiller = new JsonFlightFillerOracle(test,w);

            //Affichage sur la console
            jSonFlightFiller.showFlights();
            System.out.println("Extraction et affichage réussi");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}