package com.demo.customer.rest;

import com.demo.customer.domain.CustomerInfo;
import com.demo.customer.service.CustomerService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Customer API", tags = "Customer APIs")
@RestController
@RequestMapping(path = "customers",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerResource.class);

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
    public ResponseEntity getCustomerById(@PathVariable("customerId") int customerId) {

        try {
            CustomerInfo customerInfo = customerService.getCustomerInfoById(customerId);

            return ResponseEntity.ok().body(customerInfo);

        } catch (Exception ex) {
            LOGGER.error("Error getting customer with id: " + customerId, ex);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // other APIs ...
}
