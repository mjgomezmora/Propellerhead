package com.propellerhead.customerapplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.propellerhead.customerapplication.dao.CustomerDao;
import com.propellerhead.customerapplication.model.Customer;


@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao dao;
	
	public Customer findById(int id) {
		return dao.findById(id);
	}

	public void saveCustomer(Customer customer) {
		customer.setStatus("prospective");
		dao.saveCustomer(customer);
	}

	public void updateCustomer(Customer customer) {
		Customer entity = dao.findById(customer.getId());
		if(entity!=null){
			entity.setCode(customer.getCode());
			entity.setName(customer.getName());
			entity.setAddress(customer.getAddress());
//			dao.saveOrUpdate(customer);
		}
	}

	public void deleteCustomerByCode(String code) {
		dao.deleteCustomerByCode(code);
	}
	
	public List<Customer> findAllCustomers() {
		return dao.findAllCustomers();
	}

	public Customer findCustomerByCode(String code) {
		return dao.findCustomerByCode(code);
	}

	public boolean isCustomerCodeUnique(Integer id, String code) {
		Customer customer = findCustomerByCode(code);
		return ( customer == null || ((id != null) && (customer.getId() == id)));
	}
	
}
