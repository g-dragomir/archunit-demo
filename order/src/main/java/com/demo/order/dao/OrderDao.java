package com.demo.order.dao;

import com.demo.order.domain.entity.Order;

public interface OrderDao {

    Order getOrderById(int id);
}
