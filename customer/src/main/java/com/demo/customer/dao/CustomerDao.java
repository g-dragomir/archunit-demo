package com.demo.customer.dao;

import com.demo.customer.domain.entity.Customer;

public interface CustomerDao {

    Customer getCustomerById(int id);
}
