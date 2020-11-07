package com.demo.payment.dao;

import com.demo.payment.domain.entity.Payment;

public interface PaymentDao {

    Payment getPaymentById(int id);

    // Other CRUD operations
}
