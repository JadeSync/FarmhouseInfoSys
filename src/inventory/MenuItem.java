package inventory;

import java.util.List;

public class MenuItem {
	
	private String item;
	private int price;
	private Boolean isDish;
	
	public MenuItem() {
		this.item = "";
		this.price = 0;
		this.isDish = null;
	}
	
	public MenuItem( String item, int price, Boolean isDish ) {
		this.item = item;
		this.price = price;
		this.isDish = isDish;
	}
	
	public String getName() {
		return this.item;
	}
	
	public int getPrice() {
		return this.price;
	}
	
	public Boolean isDish() {
		return this.isDish;
	}
	
	public void setName(String name) { 
		this.item = name;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public void setDish(Boolean isDish) {
		this.isDish = isDish;
	}
	
}
