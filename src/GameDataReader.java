import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class GameDataReader {
	
	
	
	public ArrayList<String> getGameCommands(String filePath) {
		BufferedReader reader = null;
		ArrayList<String> commands = new ArrayList<String>();
		try {
		    File file = new File(filePath);
		    reader = new BufferedReader(new FileReader(file));

		    String line;
		    while ((line = reader.readLine()) != null) {
		    	if (line.isEmpty()) continue; 
		    	commands.add(line);
		    }

		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    try {
		        reader.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
		return commands;
	}

	public ArrayList<Item> getGameItems(String filePath) {
		BufferedReader reader = null;
		ArrayList<Item> items = new ArrayList<Item>();
		try {
		    File file = new File(filePath);
		    reader = new BufferedReader(new FileReader(file));

		    String line;
		    String[] splitString = null;
		    while ((line = reader.readLine()) != null) {
		    	splitString = line.split(" |,"); // Split on space and ,
		    	
		    	if (splitString[0].equalsIgnoreCase("WEAPON")) {
					Weapon w = new Weapon(splitString[1], splitString[2],Integer.parseInt(splitString[3]),
							Integer.parseInt(splitString[4]));
					items.add(w);
				}
				else if (splitString[0].equalsIgnoreCase("HEALER")) {
					Healer h = new Healer(splitString[1], splitString[2],Integer.parseInt(splitString[3]),
							Integer.parseInt(splitString[4]));
					items.add(h);
				}
		    }

		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    try {
		        reader.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}

		return items;
	}

	public ArrayList<Beast> getGameBeasts(String filePath) {
		BufferedReader reader = null;
		ArrayList<Beast> beasts = new ArrayList<Beast>();
		try {
		    File file = new File(filePath);
		    reader = new BufferedReader(new FileReader(file));

		    String line;
		    String[] splitString = null;
		    while ((line = reader.readLine()) != null) {
		    	splitString = line.split(" |,"); // Split on spaces and comma
		    	Bag bag = new Bag();
		    	bag.addItem(new Weapon(splitString[3],splitString[4], Integer.parseInt(splitString[5]),
		    			Integer.parseInt(splitString[6])));
		    	beasts.add(new Beast(splitString[1],
		    			Integer.parseInt(splitString[2]), bag, splitString[0]));
		    }

		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    try {
		        reader.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}

		return beasts;
	}

}
