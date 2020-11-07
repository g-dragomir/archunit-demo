package com.demo.payment.dao;

import com.demo.payment.domain.entity.Payment;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class PaymentDaoImpl implements PaymentDao {

    private EntityManager entityManager;

    @Override
    public Payment getPaymentById(int id) {
        return entityManager.find(Payment.class, id);
    }
}
