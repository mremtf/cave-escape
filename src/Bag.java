import java.util.ArrayList;

public class Bag {
	private ArrayList<Item> items;
	private int weight;
	
	public Bag () {
		initBag();
	}
	
	private void initBag() {
		this.items = new ArrayList<Item>();
		this.weight = 0;
	}
	
	public boolean addItem(Item item) {
		if (this.weight + item.getWeight() <= 20 ){
			this.items.add(item);
			this.weight += item.getWeight();
			return true;
		}
		return false;
	}
	
	public boolean dropItem(Item item) {
			if (this.items.remove(item)) {
				this.weight -= item.getWeight();
				return true;
			}
		return false;
	}
	
	public ArrayList<Item> getItems() {
		return this.items;
	}
	
	public int getSize() {
		return this.items.size();
	}
	
	public Item getItem(String name) {
		for (int i = 0; i < this.items.size(); ++i) {
			if (this.items.get(i).getName().equalsIgnoreCase(name)) {
				return this.items.get(i);
			}
		}
		return null;
	}
	
	public Item getItem(int itemIdx) {
		return this.items.get(itemIdx);
	}

	public void dropItems() {
		this.items.clear();
		this.weight = 0;
	}
}
