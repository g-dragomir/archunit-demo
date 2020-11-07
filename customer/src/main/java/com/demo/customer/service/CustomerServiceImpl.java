package com.demo.customer.service;

import com.demo.customer.dao.CustomerDao;
import com.demo.customer.domain.CustomerInfo;
import com.demo.customer.domain.entity.Customer;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private Mapper customerMapper;

    public CustomerInfo getCustomerInfoById(int id) {

        Customer customer = customerDao.getCustomerById(id);

        // business logic ...

        CustomerInfo customerInfo = customerMapper.map(customer, CustomerInfo.class);

        return customerInfo;
    }

}
