package com.services;

import java.util.List;
import java.util.Set;

import coms.ejbs.Item;
import coms.ejbs.Order;
import coms.ejbs.User;

public interface AdminServicesInterface {
	
	public String register(User user);
	public String LoginAsAdmin(User user);
	
	public String createAnItem(User user,Item i);
	public Set <Order> viewOrdersByCustomerId(int id);
	public List <Order> viewAllOrders();
	public String blockCustomer(User user) throws Exception;
	public String getRoleOfCurrentUser(User user) throws Exception;
	
	
	
	

}
