package coms.ejbs;

import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Stateless
@Entity

public class Order {
	
	private int orderId;
	
	private User user;
	
	private Set <Item>  item;
	
	public Set<Item> getItem() {
		return item;
	}
	public void setItem(Set<Item> item) {
		this.item = item;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
/*	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}*/



}
