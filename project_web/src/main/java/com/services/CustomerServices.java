package com.services;


import java.util.Set;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import coms.ejbs.Item;
import coms.ejbs.Order;
import coms.ejbs.User;

@Stateless
@RolesAllowed("customer")
@Path("customerservice")
public class CustomerServices implements CustomerServicesInterface {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	/*@EJB
	User user;*/
	
/*	@GET
	@Path("getmsg/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getmsg(@PathParam("id") int id) {
		return user.getmsg()+ " "+id;
	}	*/
	
	@POST
	@Path("register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public String register(User user) {
		// TODO Auto-generated method stub
		try
		{
			entityManager.persist(user);
			return "Registered Successfully.";	
		}
		catch(PersistenceException e)
		{
			throw new PersistenceException(e);
		}	
	}
    
	@POST
	@Path("LoginAsCustomer")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public String LoginAsCustomer(User user) {
		// TODO Auto-generated method stub
		try
		{
			user=entityManager.find(User.class, user.getId());
			if (user != null)
			{
				user.setRole("Customer");
				return "User is logged as Customer.";
			}
			else
			{
				return "User doesn't register";
			}
		}
		catch(PersistenceException e)
		{
			throw new PersistenceException(e);
		}		
	}

	@POST
	@Path("createOrder")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public String createOrder(User user,Order order) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			if (user.getRole()=="Customer")
		    {
				 entityManager.persist(order);	
				 return "Order is created Successfully.";
		    }
			else
			{
				return "Admin can't create order.";
			}		
		}
		catch(Exception e)
		{
			throw new Exception(e);
		}	
	}
    
	@POST
	@Path("viewOrderById/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public Set <Item> viewOrderById(@PathParam("id")int id) {
		// TODO Auto-generated method stub
		return entityManager.find(Order.class,id ).getItem();
	}

	@POST
	@Path("viewAllCustomerOrders")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public Set<Order> viewAllCustomerOrders(User user) {
		// TODO Auto-generated method stub
		return entityManager.find(User.class,user.getId()).getAllCustomerOrders();
	
	}
	
	@POST
	@Path("getRoleOfCurrentUser")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public String getRoleOfCurrentUser(User user) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			user=entityManager.find(User.class, user.getId());
			if (user != null)
			{
				return "Current user role is " + user.getRole();
			}
			else
			{
				return "User is not found.";
			}			
		}
		catch (Exception e)
		{
			throw new Exception(e);
		}	
		
	}

}
