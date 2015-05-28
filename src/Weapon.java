public class Weapon extends Item{
	private int damagePoints;
	
	public Weapon(String level,String name, int weight, int damagePoints) {
		super(name, weight,level);
		setDamagePoints(damagePoints);

	}

	private void setDamagePoints(int damagePoints) {
		this.damagePoints = damagePoints;
	}
	
	public int getPoints() {
		return damagePoints * -1;
	}
}
