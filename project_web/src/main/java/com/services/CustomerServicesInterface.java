package com.services;

import java.util.Set;

import coms.ejbs.Item;
import coms.ejbs.Order;
import coms.ejbs.User;

public interface CustomerServicesInterface {

	public String register(User user);
	public String LoginAsCustomer(User user);
	public String createOrder(User user,Order o) throws Exception;
	public Set<Item> viewOrderById(int id);
	
	
	public Set<Order> viewAllCustomerOrders(User user);
	public String getRoleOfCurrentUser(User user) throws Exception;
	
}
