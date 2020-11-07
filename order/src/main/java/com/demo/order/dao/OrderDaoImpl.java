package com.demo.order.dao;

import com.demo.order.domain.entity.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class OrderDaoImpl implements OrderDao {

    private EntityManager entityManager;

    @Override
    public Order getOrderById(int id) {
        return entityManager.find(Order.class, id);
    }

}
