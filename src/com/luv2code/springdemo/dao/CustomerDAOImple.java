package com.luv2code.springdemo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.context.spi.CurrentSessionContext;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImple implements CustomerDAO {

	// inject sessionfactory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomers() {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		// creat a query and sort by last name
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by lastName", Customer.class);
		// execute query and get results
		List<Customer> customers = theQuery.getResultList();
		// return the result
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		// get current session
		Session currentSession = sessionFactory.getCurrentSession();
		// save or update customer to database
		currentSession.saveOrUpdate(theCustomer);
	}

	@Override
	public Customer getCustomer(int theId) {
		// get current session
		Session currentSession = sessionFactory.getCurrentSession();
		// get customer from database
		Customer theCustomer = currentSession.get(Customer.class, theId);
		return theCustomer;

	}

	@Override
	public void deleteCustomer(int theId) {
		// get current session
		Session currentSession = sessionFactory.getCurrentSession();		
		// get and delete customer
		currentSession.delete(currentSession.get(Customer.class, theId));
	}

}
