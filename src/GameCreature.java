import java.util.ArrayList;

public abstract class GameCreature implements Creature{
	private String name;
	protected Bag bag;
	protected Health health;
	private CommandProcessor commandProcessor;
	
	public GameCreature(String name,int hp,Bag bag) {
		initBag(bag);
		setName(name);
		createHealth(hp);
		initCommandProcessor();
	}

	private void initCommandProcessor() {
		this.commandProcessor = new CommandProcessor();
	}

	private void createHealth(int hp) {
		// TODO Auto-generated method stub
		Health h = new Health(hp);
		this.health = h;
		
	}

	private void setName(String name) {
		if (name.equals(null) || name.isEmpty()){
			name = "Dangerous Bat";
		}
		else {
			this.name = name;
		}
		
	}
	
	public String getName() {
		return this.name;
	}

	private void initBag(Bag bag) {
		this.bag = bag;
	}

	public CreatureResponse processCommand (String commands, GameCreature gameCreature, Item item) {
		String[] commandsSplit = commands.split(" ");
		String response = "";
		boolean validAction = true;
	
		if(! commandProcessor.validateUserCommand(commandsSplit[0])) {
			return new CreatureResponse("Invalid game command", false);
		}
		
		if (commandsSplit[0].equals("attack") && commandsSplit.length >= 3 && 
				commandsSplit[1].equals("with")) {
				Item weapon = this.bag.getItem(commandsSplit[2]);
				if (!this.attack(gameCreature,weapon)) {
					response = "Not a valid weapon";
					validAction = false;
				}
				else {
					response = "Attack successful";
				}
			
		}
		else if (commandsSplit[0].equals("pickup")) {
			if (this.pickup(item)) {
				response = "Item added successfully";
			}
			else {					
				response = "Item not added successfully";
				validAction = false;
			}
		}
		else if (commandsSplit[0].equals("drop") && commandsSplit.length >= 2) {
			Item dontNeed;
			if (commandsSplit.length >= 3) {
				dontNeed = this.bag.getItem(Integer.parseInt(commandsSplit[2]) - 1);
			}
			else {
				dontNeed = this.bag.getItem(commandsSplit[1]);
			}
			if (this.drop(dontNeed)) {
				response = "Item successfully dropped";
			}
			else {					
				response = "Item not successfully added";
				validAction = false;
			}
		}
		else if (commandsSplit[0].equals("help")) {
			ArrayList<String> humanCommands = commandProcessor.getCommands();
			for (String c : humanCommands) {
				response += c + "\n";
			}
		}
		else if (commandsSplit[0].equals("heal") && commandsSplit.length >= 3 && commandsSplit[1].equals("with")) {
		
			Item healerItem; 
			if (commandsSplit.length >= 4) {
				healerItem = this.bag.getItem(Integer.parseInt(commandsSplit[3]) - 1);
			}
			else {
				healerItem = this.bag.getItem(commandsSplit[2]);
			}
			if (this.heal(healerItem)) {
				this.drop(healerItem); // remove item
				response = "Item successfully used for healing";
			}
			else {					
				response = "Item not successfully used for healing";
				validAction = false;
			}
		}
		else if (commandsSplit[0].equals("runaway")) {
			response = "fleed and dropped all items";
			this.dropAll();
		}
		return new CreatureResponse (response,validAction);
	}

	public Bag getBag() {
		return this.bag;
	
	}
		
	//public abstract boolean attack(GameCreature enemy, Item item);
	//public abstract boolean heal(Item item);

	public void injured(int damagePoints) {
		this.health.hit(damagePoints);
		
	}
	
	public int currentHealthPoints() {
		return this.health.getHealthPoints();
	}
	
	public boolean isLiving() {
		return this.health.getAlive();
	}
}
