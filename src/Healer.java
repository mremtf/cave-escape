public class Healer extends Item{
	private int healPoints;
	
	public Healer(String level,String name,int weight, int healPoints) {
		super(name, weight,level);
		setHealPoints(healPoints);
		
	}
	
	private void setHealPoints(int healPoints) {
		this.healPoints = healPoints;
	}
	
	public int getPoints() {
		return healPoints;
	}


}
