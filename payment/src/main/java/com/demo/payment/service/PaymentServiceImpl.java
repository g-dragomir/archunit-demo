package com.demo.payment.service;

import com.demo.customer.domain.CustomerInfo;
import com.demo.customer.service.CustomerService;
import com.demo.payment.dao.PaymentDao;
import com.demo.payment.domain.PaymentItem;
import com.demo.payment.domain.entity.Payment;
import com.demo.payment.exception.PaymentNotFoundException;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

//    private final static Logger LOGGER = Logger.getLogger(PaymentServiceImpl.class.getName());

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PaymentDao paymentDao;

    @Autowired
    private Mapper paymentMapper;

    @Inject
    private Boolean foreignPaymentEnabled;

    @Override
    public PaymentItem getPaymentById(int id) {
        Payment payment = paymentDao.getPaymentById(id);

        if(payment == null) {
            throw new PaymentNotFoundException("Payment not found!");
        }

        PaymentItem paymentItem = paymentMapper.map(payment, PaymentItem.class);
        return paymentItem;
    }

    @Override
    public List<PaymentItem> getAllPaymentsForCustomer(int customerId) {
        // business logic

        return new ArrayList<PaymentItem>();
    }

    @Override
    public void processPaymentForCustomer(int paymentId, int customerId) {
        Payment payment = paymentDao.getPaymentById(paymentId);
        CustomerInfo customerInfo = customerService.getCustomerInfoById(customerId);

        // business logic
    }

    // Other business methods

}