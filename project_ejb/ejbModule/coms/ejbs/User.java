package coms.ejbs;


import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Stateless  // bean
@Entity     //db

public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@OneToMany // one customer make many orders
	private Set<Order> orders;
	
	@OneToOne // user should be customer or user
	private String role;	
	
	@OneToMany // admin can make more than one item
	private Set<Item> items;
	
	private Order order;
	private String username;
	private String password;
	
	public Set<Item> getAllItems() {
		return items;
	}
	public void setAllItems(Set<Item> items) {
		this.items = items;
	}
	public Set<Order> getAllCustomerOrders() {
		return orders;
	}
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	/*public String getmsg() {
		return "Say Hello from user bean.";
	}*/
    

}
