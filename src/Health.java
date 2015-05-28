public class Health {
	private int healthPoints;
	private boolean alive;
	
	public Health () {
		setHealth(100);
		setAlive(true);
	}
	public Health (int hp) {
		setHealth(hp);
		setAlive(true);
	}
	
	private void setHealth(int hp) {
		if (hp >= 1) {
			this.healthPoints = hp;
		}
		else {
			setAlive(false);
		}
	}
	
	private void setAlive(boolean a) {
		this.alive = a;
	}
	
	public int getHealthPoints() {
		return this.healthPoints;
	}
	public boolean getAlive() {
		return this.alive;
	}
	
	public void hit(int hitPoints) {
		setHealth(getHealthPoints() + hitPoints);
	}
	public void heal(int healPoints) {
		setHealth (getHealthPoints() + healPoints);
	}
}
