package com.propellerhead.customerapplication.dao;

import java.util.List;

import com.propellerhead.customerapplication.model.Customer;

public interface CustomerDao {

	Customer findById(int id);

	void saveCustomer(Customer customer);
	
	public void saveOrUpdate(Customer customer);
	
	void deleteCustomerByCode(String code);
	
	List<Customer> findAllCustomers();

	Customer findCustomerByCode(String code);

}
