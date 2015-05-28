public class Human extends GameCreature{
	
	public Human(String name, int hp, Bag emptyBag) {
		super(name, hp, emptyBag);
		this.bag.addItem(new Weapon("Standard","Crowbar", 5, 30));
		// TODO Auto-generated constructor stub
	}
	
	public boolean pickup(Item item) {
		return this.bag.addItem(item);
		
	}

	public boolean drop(Item item) {
		return this.bag.dropItem(item);
		
	}
	
	public void dropAll() {
		this.bag.dropItems();
		
	}
	
	@Override
	public boolean attack(GameCreature enemy, Item item) {
		if (item == null || !(item instanceof Weapon)) return false;
		enemy.injured(item.getPoints() * 2);
		return true;
	}

	@Override
	public boolean heal(Item item) {
		
		if (item == null || !(item instanceof Healer)) return false;
		this.health.heal(item.getPoints());
		return true;
	}

}
