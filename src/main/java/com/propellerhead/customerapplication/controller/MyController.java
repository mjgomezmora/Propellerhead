package com.propellerhead.customerapplication.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.propellerhead.customerapplication.model.Customer;
import com.propellerhead.customerapplication.service.CustomerService;

@Controller
@RequestMapping("/")
public class MyController {

	@Autowired
	CustomerService service;
	
	@Autowired
	MessageSource messageSource;

	/*
	 * List all existing Customers.
	 */
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listCustomers(ModelMap model) {

		List<Customer> customers = service.findAllCustomers();
		model.addAttribute("customers", customers);
		return "allcustomers";
	}

	/*
	 * Add a new Customer.
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String newCustomer(ModelMap model) {
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		model.addAttribute("edit", false);
		return "registration";
	}

	/*
	 * Handling POST request for validating the user input and saving Customer in database.
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String saveCustomer(@Valid Customer customer, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "registration";
		}
		
		if(!service.isCustomerCodeUnique(customer.getId(), customer.getCode())){
			FieldError codeError =new FieldError("Customer","code",messageSource.getMessage("non.unique.code", new String[]{customer.getCode()}, Locale.getDefault()));
		    result.addError(codeError);
			return "registration";
		}
		
		service.saveCustomer(customer);

		model.addAttribute("success", "Customer " + customer.getName() + " registered successfully");
		return "success";
	}


	/*
	 * Provide the existing Customer for updating.
	 */
	@RequestMapping(value = { "/edit-{code}-customer" }, method = RequestMethod.GET)
	public String editCustomer(@PathVariable String code, ModelMap model) {
		Customer customer = service.findCustomerByCode(code);
		model.addAttribute("customer", customer);
		model.addAttribute("edit", true);
		return "registration";
	}
	
	/*
	 * Handling POST request for validating the user input and updating Customer in database.
	 */
	@RequestMapping(value = { "/edit-{code}-customer" }, method = RequestMethod.POST)
	public String updateCustomer(@Valid Customer customer, BindingResult result,
			ModelMap model, @PathVariable String code) {

		if (result.hasErrors()) {
			return "registration";
		}

		if(!service.isCustomerCodeUnique(customer.getId(), customer.getCode())){
			FieldError codeError =new FieldError("Customer","code",messageSource.getMessage("non.unique.code", new String[]{customer.getCode()}, Locale.getDefault()));
		    result.addError(codeError);
			return "registration";
		}

		service.updateCustomer(customer);

		model.addAttribute("success", "Customer " + customer.getName()	+ " updated successfully");
		return "success";
	}

	
	/*
	 * Delete an Customer by it's CODE value.
	 */
	@RequestMapping(value = { "/delete-{code}-customer" }, method = RequestMethod.GET)
	public String deleteCustomer(@PathVariable String code) {
		service.deleteCustomerByCode(code);
		return "redirect:/list";
	}

}
