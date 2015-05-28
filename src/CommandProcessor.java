import java.util.ArrayList;

public class CommandProcessor {
	ArrayList<String> commands;
	
	public CommandProcessor() {
		this.setGameCommands();
	}
	
	private void setGameCommands() {
		GameDataReader gameDataReader = new GameDataReader();
		this.commands = gameDataReader.getGameCommands("GameData/Commands.txt");
	}
	
	public boolean validateUserCommand(String target) {
		for (String c : commands) {
			if (c.equals(target)) {
				return true;
			}
		}
		return false;
	}
	public ArrayList<String> getCommands() {
			return this.commands;
		
	}
}
