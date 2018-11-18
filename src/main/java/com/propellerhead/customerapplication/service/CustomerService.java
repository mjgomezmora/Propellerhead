package com.propellerhead.customerapplication.service;

import java.util.List;

import com.propellerhead.customerapplication.model.Customer;

public interface CustomerService {

	Customer findById(int id);
	
	void saveCustomer(Customer customer);
	
	void updateCustomer(Customer customer);
	
	void deleteCustomerByCode(String code);

	List<Customer> findAllCustomers(); 
	
	Customer findCustomerByCode(String code);

	boolean isCustomerCodeUnique(Integer id, String code);
	
}
