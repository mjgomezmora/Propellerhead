package com.propellerhead.customerapplication.dao;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.propellerhead.customerapplication.model.Customer;

@Repository("customerDao")
public class CustomerDaoImpl extends AbstractDao<Integer, Customer> implements CustomerDao {

	public Customer findById(int id) {
		return getByKey(id);
	}

	public void saveCustomer(Customer customer) {
		Calendar calendar = Calendar.getInstance();
		java.util.Date now = calendar.getTime();
		customer.setCreateTime(new java.sql.Timestamp(now.getTime()));
		persist(customer);
	}
	
	public void saveOrUpdate(Customer customer){
		super.saveOrUpdate(customer);
	}
	
	public void deleteCustomerByCode(String code) {
		Query query = getSession().createSQLQuery("delete from Customer where code = :code");
		query.setString("code", code);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Customer> findAllCustomers() {
		Criteria criteria = createEntityCriteria();
		return (List<Customer>) criteria.list();
	}

	public Customer findCustomerByCode(String code) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("code", code));
		return (Customer) criteria.uniqueResult();
	}
}
