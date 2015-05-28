public class Beast extends GameCreature{
	private String type;
	
	public Beast(String name,int hp, Bag bag,String type) {
		super(name,hp,bag);
		setType(type);
	}
	
	private void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}

	@Override
	public boolean attack(GameCreature enemy, Item item) {
		if (item == null && !(item instanceof Weapon)) return false;
		int damage = (int) (item.getPoints());
		enemy.injured(damage);
		return true;
	}

	@Override
	public boolean heal(Item item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pickup(Item item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean drop(Item item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void dropAll() {
		// TODO Auto-generated method stub
		
	}


}
