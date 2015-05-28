import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class RoomEscape {
	
	private static Scanner userInput;
	private static ArrayList<Item> gameItems;
	private static ArrayList<Beast> gameBeasts;
	private static ArrayList<Beast> currentRoomBeasts;
	private static Human currentPlayer;
	private static Random randomGenerator;
	
	public static void main(String[] args) {
		
		initGameData();
		System.out.print("Enter Character Name: ");
		String name = userInput.nextLine();
		System.out.print("Enter number of levels: ");
		int maxRooms = Integer.parseInt(userInput.nextLine());
		
		currentPlayer = new Human(name,100, new Bag());
		int roomsBeat = 0;
		int flees_left = 5;
		while (roomsBeat < maxRooms) {
			/*
			 * Create room bad guys and items
			 */
			System.out.println("Entering Room " + (roomsBeat + 1));
			System.out.println(" ");
			initCurrentRoomBeasts();
			int creaturesDestroyed = 0;
			
			while (currentPlayer.isLiving() && creaturesDestroyed < 2) {
				int encounterProb = randomGenerator.nextInt(100);
				Item foundItem = null;
				Beast foundEnemy = null;
				/*
				 * 60 percent chance of being a Beast.
				 */
				if (encounterProb <= 40) {
					foundItem = findItem();
					System.out.println("You have discoverd the item " + foundItem.getLevel() + " " + foundItem.getName());
					System.out.println(" ");
					while (noBattle(foundItem)); 
					
				}
				else {
					foundEnemy = findEnemy();
					System.out.println("You have encounterd a(n) " + foundEnemy.getType() + " " + foundEnemy.getName());
					int result = battle (foundEnemy);
					if ( result == 1) {
						creaturesDestroyed++;
					}
					else if (result == 0 || flees_left == 0) {
						System.out.println("You are dead!");
						return;
					}
					else {
						flees_left--;
					}
					System.out.println(" ");
					
				}
			}
			roomsBeat++;
		}
		System.out.println("You have beat RoomEscape!");
	}
	
	/**
	 * Allows the current player to engage in combat with the incoming beast. The player uses 
	 * item from his bag to kill the enemy. 
	 * 
	 * @param battlingBeast The found beast the player has encountered
	 * @return Whether the currentPlayer is dead being false or alive being true.
	 */
	private static int battle (Beast battlingBeast) {
		
		while (currentPlayer.isLiving()) {
			System.out.println("Player Health: " + currentPlayer.currentHealthPoints());
			displayBagContents();
			//String attackString = "attack " + battlingBeast.getName() + " with ";
			//System.out.print(attackString);
			System.out.print("Command: ");
			String input = userInput.nextLine();
			System.out.println(" ");
			
			//attackString += input;
			CreatureResponse response = currentPlayer.processCommand(input, battlingBeast,null);
			while (!response.getValidAction()) {
				System.out.print(response.getResponse() + ", Try Again: ");
				input = userInput.nextLine();
				response = currentPlayer.processCommand(input, battlingBeast,null);
			}
			System.out.println(response.getResponse());
			System.out.println(" ");
			
			if (response.getResponse().equals("fleed and dropped all items")) {
				return 2;
			}
			
			if (currentPlayer.isLiving() && !battlingBeast.isLiving()) {
				System.out.println(battlingBeast.getName() + " is killed");
				System.out.println(" ");
				return 1;
			}
			else {
				System.out.println(battlingBeast.getName() + " HP: " + battlingBeast.currentHealthPoints());
			}
			battlingBeast.attack(currentPlayer,battlingBeast.bag.getItem(0));
			System.out.println(battlingBeast.getType() + " " + battlingBeast.getName() + " attacked you with " 
					+ battlingBeast.bag.getItem(0).getLevel() + " " + battlingBeast.bag.getItem(0).getName());
			System.out.println(" ");
		}
		return 0;
	}
	
	private static void displayBagContents () {
		System.out.println("Contents of Bag:");
		System.out.printf("%-9s%-20s%-20s%-20s", "Type", "Name", "Points","Weight");
		System.out.println(" ");
		Bag playerBag = currentPlayer.getBag();
		int index = 1;
		for (Item i : playerBag.getItems()) {
			if (i instanceof Weapon) {
				System.out.printf(index + ") WEAPON:  %-20s%-20d%-20d\n", i.getLevel() + " " + i.getName(),i.getPoints(),i.getWeight());
			}
			else {
				System.out.printf(index + ") HEALER:  %-20s%-20d%-20d\n", i.getLevel() + " " + i.getName(),i.getPoints(),i.getWeight());
			}
			++index;
		}
		System.out.println(" ");
	}
	
	/**
	 * Allows the player to run the following game commands "pickup" on the found item, view the player bag contents,
	 * "drop" an item from there bag, show the game commands using "help", "ignore" current item, and "quit" the game.
	 *  
	 * 
	 * @param foundItem An instantiated Item that the player has found
	 */
	
	private static boolean noBattle (Item foundItem) {
		System.out.println("Player Health: " + currentPlayer.currentHealthPoints());
		displayBagContents();
		System.out.print("Command: ");
		String input = userInput.nextLine();
		if (input.isEmpty()) {
			return false;
		}
		CreatureResponse response = currentPlayer.processCommand(input, null,foundItem);
		while (!response.getValidAction()) {
			System.out.print(response.getResponse() + ", Try Again: ");
			input = userInput.nextLine();
			response = currentPlayer.processCommand(input, null,foundItem);		
			if (input.isEmpty()) {
				return false;
			}
		}	
		System.out.println(response.getResponse());
		return true;
	
	}
	
	private static Item findItem() {
		int itemIdx = randomGenerator.nextInt(gameItems.size());
		int move_iterations = randomGenerator.nextInt(3) + 1;
		
		int i = 0;
		do{
			System.out.println("Searching area...");
			
		} while(i++ < move_iterations);
		
		return gameItems.get(itemIdx);
	}

	private static Beast findEnemy() {
		int beastIdx = randomGenerator.nextInt(currentRoomBeasts.size());
		int move_iterations = randomGenerator.nextInt(3) + 1;
		
		int i = 0;
		do{
			System.out.println("Searching area...");
			
		} while(i++ < move_iterations);
		
		Beast foundBeast = currentRoomBeasts.get(beastIdx);
		while (!foundBeast.isLiving()){
			foundBeast = currentRoomBeasts.get(beastIdx);
		} 
		
		return currentRoomBeasts.get(beastIdx);
	}

	private static void initCurrentRoomBeasts () {
		currentRoomBeasts = new ArrayList<Beast>();
		for (Beast b : gameBeasts) {
			currentRoomBeasts.add(new Beast(b.getName(),b.currentHealthPoints(),b.getBag(), b.getType())); 
		}
	}
	
	private static void initGameData() {
		userInput = new Scanner(System.in);
		randomGenerator = new Random(1337);
		GameDataReader gameDataReader = new GameDataReader();
		gameItems = gameDataReader.getGameItems("GameData/GameItems.csv");
		gameBeasts = gameDataReader.getGameBeasts("GameData/GameCreatures.csv");
		
	}
}
