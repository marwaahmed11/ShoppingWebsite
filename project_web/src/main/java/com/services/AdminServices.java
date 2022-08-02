package com.services;

import java.util.List;
import java.util.Set;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import coms.ejbs.Item;
import coms.ejbs.Order;
import coms.ejbs.User;

@Stateless
@RolesAllowed("admin")
@Path("adminservice")
public class AdminServices implements AdminServicesInterface{

	/*@EJB
	User user;*/
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
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
	@Path("loginAsAdmin")
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public String LoginAsAdmin(User user) {
		// TODO Auto-generated method stub
		try
		{
			user=entityManager.find(User.class, user.getId());
			if (user != null)
			{
				user.setRole("Admin");
				return "User is logged as Admin.";
			}
			else
			{
				return "User doesn't register.";
			}
		}
		catch(PersistenceException e)
		{
			throw new PersistenceException(e);
		}		
	}
	
	@POST
	@Path("createAnItem")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public String createAnItem(User user,Item item) {
		// TODO Auto-generated method stub
		try
		{	if (user.getRole()=="Admin")
		    {
				entityManager.persist(item);
				return "Item is created Successfully.";
		    }
			else
			{
				return "Customer can't create item.";
			}
		}
		catch(PersistenceException e)
		{
			throw new PersistenceException(e);
		}	
	}
	
	@POST
	@Path("viewOrdersByCustomerId/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public Set <Order> viewOrdersByCustomerId(@PathParam("id")int id) {
		// TODO Auto-generated method stub
		return entityManager.find(User.class, id).getAllCustomerOrders();
		//return entityManager.find(Order.class,user.getId()).getAllItems(); // return item
	}
	
	@POST
	@Path("viewAllOrders")
	@Consumes(MediaType.APPLICATION_JSON)
	//@QueryParam("")
	@Override
	public List<Order> viewAllOrders() {
		 //TODO Create a query to select all the orders in order by whether or not they are done
		 TypedQuery<Order> query = entityManager.createQuery("SELECT i FROM Order i ORDER BY i.done" , Order.class);
		 return query.getResultList();
		}
	
	@POST
	@Path("blockCustomer")
	@Consumes(MediaType.APPLICATION_JSON)	
	@Override
	public String blockCustomer(User user) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			user=entityManager.find(User.class, user.getId());
			if (user != null)
			{
				if (user.getRole() == "Customer")
				{
					entityManager.remove(user);
					return "Customer is blocked Successfully.";	
				}
				else
				{
					return "User can't be blocked as it is admin.";
				}
			}
			else
			{
				return "Customer is not found.";
			}		
		}
		catch (Exception e)
		{
			throw new Exception(e);
		}	
	}
	
	
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
				return "User doesn't register.";
			}			
		}
		catch (Exception e)
		{
			throw new Exception(e);
		}
	}
	

}
