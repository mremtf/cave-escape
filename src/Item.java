public abstract class Item {
	private String name;
	private int weight;
	private String level;
	
	public Item(String name, int weight, String level) {
		setName(name);
		setWeight(weight);
		setLevel(level);
		
	}
	
	private void setLevel(String level) {
		this.level = level;
	}
	
	private void setName(String name) {
		this.name = name;
	}
	
	private void setWeight(int w) {
		if (w >= 0) {
			this.weight = w;
		}
		else {
			this.weight = 0;
		}
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getWeight() {
		return this.weight;
	}
	
	public String getLevel() {
		return this.level;
	}
	
	public abstract int getPoints();
}
