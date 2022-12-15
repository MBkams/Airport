package SRC;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class World {
	
	public ArrayList<Aeroport> list_apt = new ArrayList<Aeroport>();
	
/* Constructeur pointant sur le fichier CSC */
	public World (String fileName){
			try{
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
			}
			catch (Exception e){
			System.out.println("Maybe the file isn't there ?");
			System.out.println(list_apt.get(list_apt.size()-1));
			e.printStackTrace();
			}
		
	}

	
	

}
