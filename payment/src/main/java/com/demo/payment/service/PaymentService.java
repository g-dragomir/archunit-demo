package com.demo.payment.service;

import com.demo.payment.domain.PaymentItem;

import java.util.List;

public interface PaymentService {

    PaymentItem getPaymentById(int id);

    List<PaymentItem> getAllPaymentsForCustomer(int customerId);

    void processPaymentForCustomer(int paymentId, int customerId);
}
