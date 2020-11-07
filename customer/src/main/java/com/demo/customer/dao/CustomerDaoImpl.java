package com.demo.customer.dao;

import com.demo.customer.domain.entity.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class CustomerDaoImpl implements CustomerDao {

    private EntityManager entityManager;

    @Override
    public Customer getCustomerById(int id) {
        return entityManager.find(Customer.class, id);
    }
}
