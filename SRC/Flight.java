package SRC;

import java.time.LocalDateTime;

/**
 * La classe Flight définit ce qu'est un avion.
 * La défintion comporte un numéro et nom de la compagnie d'avion, le départ et l'heure d'arrivée.
 * 
 */
public class Flight {
    
    //Le numéro de la compagnie de l'avion
    private String airLineCode;

    //Le nom de la compagnie de l'avion
    private String airLineName;

    //L'heure d'arrivée de l'avion
    private LocalDateTime arrivalTime;

    //L'heure de départ de l'avion
    private LocalDateTime departureTime;

    //Le numéro de l'avion
    private int number;

    //L'aeroport de depart
    private Aeroport departure;

    //L'aeroport d'arivé
    private Aeroport arrival;

    
    /**
     * Construteur de la classe Flight
     * 
     * @param number Un nombre
     * @param airLineCode le numéro de la compagnie de l'avion
     * @param airLineName Le nom de la compagnie de l'avion
     * @param departure l'aeroport de depart
     * @param departureTime l'heure de départ de l'avion
     * @param arrival l'aeroport d'arivé
     * @param arrivalTime l'heure d'arrivée de l'avion
     * 
     */
    public Flight(int num, String airLineCode, String airlineName, Aeroport departure, LocalDateTime deptime, Aeroport arrival, LocalDateTime arrtime){
        this.number = num;
        this.airLineCode = airLineCode;
        this.airLineName = airlineName;
        this.departure = departure;
        this.departureTime = deptime;
        this.arrival = arrival;
        this.arrivalTime = arrtime;

    }

     /**
     * Retourne l'aroport de départ de l'avion
     *  
     * @return departure, sous forme d'un type data-time
     */
    public Aeroport getDeparture(){
        return departure;
    }
    

    /**
     * Retourne l'aroport d'arrivée de l'avion
     * 
     * @return arrival, sous forme d'un type data-time
     */
    public Aeroport getArrival(){
        return arrival;
    }
    

    /** 
	 * Redefintion de la méthode toString pour afficher les informations de l'avion.
	 * 
	 * @return une chaine de caractère contenant le numéro, nom, le départ et l'heure d'arrivée de l'avion
	 */
	@Override
	public String toString() {
		return "N°" + airLineCode + " avion " + airLineName + "Départ de " + departure + " Heure de départ : " + departureTime + " Arrivé à " + arrival + " Heure d'arrivé : " + arrivalTime;
	}
}
