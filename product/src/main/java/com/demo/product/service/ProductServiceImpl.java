package com.demo.product.service;

import com.demo.product.dao.ProductDao;
import com.demo.product.domain.ProductItem;
import com.demo.product.domain.entity.Product;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private Mapper productMapper;

    @Override
    public ProductItem getProductItemById(int id) {

        Product product = productDao.getProductById(id);

        // business logic ...

        ProductItem productItem = productMapper.map(product, ProductItem.class);

        return productItem;
    }

}
