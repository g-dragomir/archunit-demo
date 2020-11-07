package com.demo.product.dao;

import com.demo.product.domain.entity.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class ProductDaoImpl implements ProductDao {

    private EntityManager entityManager;

    @Override
    public Product getProductById(int id) {
        return entityManager.find(Product.class, id);
    }

}
