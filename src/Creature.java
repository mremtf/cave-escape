public interface Creature {
	public abstract boolean pickup (Item item);
	public abstract boolean drop (Item item);
	public abstract void dropAll ();
	public abstract CreatureResponse processCommand (String commands, GameCreature gameCreature, Item item);
	public abstract void injured(int damagePoints);
	public abstract boolean attack(GameCreature enemy, Item item);
	public abstract boolean heal(Item item);
}
